package com.liferay.training.amf.newsletter.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.RenderRequest;
import javax.servlet.http.HttpServletRequest;

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
import com.liferay.training.amf.newsletter.model.NewsletterIssue;
import com.liferay.training.amf.newsletter.model.NewsletterSearchResult;

public class NewsletterSearchService {
	
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
			"issueNumber", "issueDate", Field.NAME);
	    
	    return searchContext; 
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
	
	public NewsletterSearchResult search(RenderRequest renderRequest, String searchKeyword,
			int startIndex, int endIndex) {
		
		HttpServletRequest httpServletRequest = PortalUtil.getHttpServletRequest(renderRequest);
		
		SearchContext keywordSearchContext = getSearchContext(httpServletRequest, searchKeyword, startIndex, endIndex);
		Indexer journalArticleIndexer = IndexerRegistryUtil.getIndexer(JournalArticle.class);
		Hits journalArticleHits = getHits(journalArticleIndexer, keywordSearchContext);
		
	    Map<Integer, NewsletterIssue> newsletterIssues = new HashMap<Integer, NewsletterIssue>();
	    NewsletterIssueSaxReaderMapper newsLetterIssueMapper = new NewsletterIssueSaxReaderMapper();
	    
        for (int i = 0; i < journalArticleHits.getDocs().length; i++) {
            Document doc = journalArticleHits.doc(i);

            Integer issueNumber = GetterUtil.getInteger(doc.get("issueNumber"));
            _log.fatal(String.format("issueNumber = %d", issueNumber));
            
			NewsletterIssue newsletterIssue = tryGetNewsletterIssueFromDocument(doc);
			if (newsletterIssue == null)
			{
			  	newsletterIssue = findNewsletterIssueWithIssueNumber(
			  			httpServletRequest, issueNumber, startIndex, endIndex);
			}
	          
	      	if (newsletterIssue == null) continue;
	      	
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
			totalHits = (int) journalArticleIndexer.searchCount(keywordSearchContext);
		} catch (SearchException e) {
			_log.fatal(String.format("Error during search count: %s", e.getMessage()));
		}
        newsletterSearchResult.set_totalCount(totalHits);
        
        return newsletterSearchResult;
	}
	
	private NewsletterIssue tryGetNewsletterIssueFromDocument(Document doc) {
		
		NewsletterIssue newsletterIssue = new NewsletterIssue();
		
		Integer issueNumber = GetterUtil.getInteger(doc.get("issueNumber"));
        _log.fatal(String.format("issueNumber = %d", issueNumber));
        
        Date issueDate = null;
        try {
			issueDate = GetterUtil.getDate(doc.getDate("issueDate"), new SimpleDateFormat("yyyy-MM-dd"));
			_log.fatal(String.format("issueDate = %s", issueDate.toString()));
		} catch (ParseException e) {
			_log.fatal(String.format("Error parsing issue date from search document: (%s)", e.getMessage()));
			return null;
		}
        
        newsletterIssue.set_issueNumber(issueNumber);
	  	newsletterIssue.set_issueDate(issueDate);
	  	
	  	return newsletterIssue;
	}

	private NewsletterIssue findNewsletterIssueWithIssueNumber(HttpServletRequest httpServletRequest, 
			Integer issueNumber, int startIndex, int endIndex)  {
		
		SearchContext issueNumberSearchContext = getSearchContext(
				httpServletRequest, issueNumber.toString(), startIndex, endIndex);
		Indexer journalArticleIndexerForIssueNumberSearch = IndexerRegistryUtil.getIndexer(JournalArticle.class);
		Hits journalArticleHitsForIssueNumberSearch = getHits(journalArticleIndexerForIssueNumberSearch, issueNumberSearchContext);
	  	if (journalArticleHitsForIssueNumberSearch == null) return null;
	  	
	  	NewsletterIssue newsletterIssueToReturn = null;
	  	for (int i = 0; i < journalArticleHitsForIssueNumberSearch.getDocs().length; i++) {
            Document doc = journalArticleHitsForIssueNumberSearch.doc(i);
            
            NewsletterIssue newsletterIssue = tryGetNewsletterIssueFromDocument(doc);
            if (newsletterIssue != null)
            {
            	newsletterIssueToReturn = newsletterIssue;
            	break;
            }
	  	}
	  	
	  	return newsletterIssueToReturn;
	}
	
	private static final Log _log = LogFactoryUtil.getLog(NewsletterSearchService.class);
}
