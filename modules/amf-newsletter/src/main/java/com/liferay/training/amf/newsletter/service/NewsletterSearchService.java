package com.liferay.training.amf.newsletter.service;

import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Field;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.QueryConfig;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchContextFactory;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.training.amf.newsletter.model.JournalArticleType;
import com.liferay.training.amf.newsletter.model.NewsletterIssue;
import com.liferay.training.amf.newsletter.model.NewsletterSearchResult;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.RenderRequest;

import javax.servlet.http.HttpServletRequest;

public class NewsletterSearchService {

	public NewsletterSearchResult search(RenderRequest renderRequest, String searchKeyword,
			int startIndex, int endIndex) {

		HttpServletRequest httpServletRequest = PortalUtil.getHttpServletRequest(renderRequest);

		SearchContext keywordSearchContext = getSearchContext(httpServletRequest, searchKeyword, startIndex, endIndex);
		Indexer journalArticleIndexer = IndexerRegistryUtil.getIndexer(JournalArticle.class);

		Hits journalArticleHits = getHits(journalArticleIndexer, keywordSearchContext);

		Map<Integer, NewsletterIssue> newsletterIssues = new HashMap<>();
		NewsletterIssueSaxReaderMapper newsLetterIssueMapper = new NewsletterIssueSaxReaderMapper();

		for (int i = 0; i < journalArticleHits.getDocs().length; i++) {
			Document doc = journalArticleHits.doc(i);

			Integer issueNumber = GetterUtil.getInteger(doc.get("issueNumber"));
			String journalArticleType = doc.get("journalArticleType");

			NewsletterIssue newsletterIssue = null;

			if (journalArticleType.equals(JournalArticleType.NEWSLETTER_ISSUE.getDdmStructureKey()))
			{
				newsletterIssue = parseNewsletterIssueFromDocument(doc, issueNumber);
			} else {
				newsletterIssue = findParentNewsletterIssueWithIssueNumber(
					httpServletRequest, issueNumber, startIndex, endIndex);
			}

			if (newsletterIssue == null)continue;

			if (!newsletterIssues.containsKey(issueNumber))
			{
				_log.fatal(String.format("Adding newsletter issue (%d)", issueNumber));
				newsletterIssues.put(issueNumber, newsletterIssue);
			}
		}

		NewsletterSearchResult newsletterSearchResult = new NewsletterSearchResult();

		newsletterSearchResult.set_newsletterIssues(new ArrayList(newsletterIssues.values()));

		int totalHits = 0;

		try {
			totalHits = (int)journalArticleIndexer.searchCount(keywordSearchContext);
		} catch (SearchException e) {
			_log.fatal(String.format("Error during search count: %s", e.getMessage()));
		}

		newsletterSearchResult.set_totalCount(totalHits);

		return newsletterSearchResult;
	}

	private NewsletterIssue findParentNewsletterIssueWithIssueNumber(HttpServletRequest httpServletRequest,
			Integer issueNumber, int startIndex, int endIndex) {

		SearchContext issueNumberSearchContext = getSearchContext(
			httpServletRequest, issueNumber.toString(), startIndex, endIndex);
		Indexer journalArticleIndexerForIssueNumberSearch = IndexerRegistryUtil.getIndexer(JournalArticle.class);

		Hits journalArticleHitsForIssueNumberSearch = getHits(
			journalArticleIndexerForIssueNumberSearch, issueNumberSearchContext);

		if (journalArticleHitsForIssueNumberSearch == null) return null;

		NewsletterIssue newsletterIssueToReturn = null;

		for (int i = 0; i < journalArticleHitsForIssueNumberSearch.getDocs().length; i++) {
			Document doc = journalArticleHitsForIssueNumberSearch.doc(i);

			String journalArticleType = doc.get("journalArticleType");

			if (journalArticleType.equals(JournalArticleType.NEWSLETTER_ISSUE.getDdmStructureKey()))
			{
				newsletterIssueToReturn = parseNewsletterIssueFromDocument(doc);

				break;
			}
		}

		return newsletterIssueToReturn;
	}

	private Hits getHits(Indexer journalArticleIndexer, SearchContext searchContext) {
		Hits hits;

		try {
			hits = journalArticleIndexer.search(searchContext);
		} catch (SearchException e) {
			_log.fatal(String.format("Error during search: %s", e.getMessage()));

			return null;
		}

		_log.fatal(String.format("hits.Length = %d", hits != null ? hits.getLength() : 0));

		return hits;
	}

	private SearchContext getSearchContext(HttpServletRequest httpServletRequest, String searchKeyword,
			int startIndex, int endIndex) {

		_log.fatal(String.format("Entering searchNewsletters, doing a search on %s", searchKeyword));

		SearchContext searchContext = SearchContextFactory.getInstance(httpServletRequest);

		searchContext.setKeywords(searchKeyword);
		searchContext.setAttribute("paginationType", "more");
		searchContext.setStart(startIndex);
		searchContext.setEnd(endIndex);

		QueryConfig queryConfig = searchContext.getQueryConfig();

		queryConfig.addSelectedFieldNames(
			"issueNumber", "issueDate", "journalArticleType", "newsletterIssueTitle", Field.NAME);

		return searchContext;
	}

	//TODO: separate out to another class
	private NewsletterIssue parseNewsletterIssueFromDocument(Document doc) {
		return parseNewsletterIssueFromDocument(doc, 0);
	}

	//TODO: separate out to another class
	private NewsletterIssue parseNewsletterIssueFromDocument(Document doc, Integer issueNumber) {
		NewsletterIssue newsletterIssue = new NewsletterIssue();

		if (issueNumber == 0) {
			issueNumber = GetterUtil.getInteger(doc.get("issueNumber"));
		}

		newsletterIssue.set_issueNumber(issueNumber);

		newsletterIssue.set_title(doc.get("newsletterIssueTitle"));

		Date issueDate = null;

		try {
			issueDate = GetterUtil.getDate(doc.getDate("issueDate"), new SimpleDateFormat("yyyy-MM-dd"));
			_log.fatal(String.format("issueDate = %s", issueDate.toString()));
		} catch (ParseException e) {
			_log.fatal(String.format("Error parsing issue date from search document: (%s)", e.getMessage()));
		}

		newsletterIssue.set_issueDate(issueDate);

		return newsletterIssue;
	}

	private static final Log _log = LogFactoryUtil.getLog(NewsletterSearchService.class);

}