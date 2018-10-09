package com.liferay.training.amf.newsletter.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.portlet.RenderRequest;
import javax.servlet.http.HttpServletRequest;

import com.liferay.journal.model.JournalArticle;
import com.liferay.journal.service.JournalArticleLocalServiceUtil;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.exception.SystemException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.search.Document;
import com.liferay.portal.kernel.search.Hits;
import com.liferay.portal.kernel.search.Indexer;
import com.liferay.portal.kernel.search.IndexerRegistryUtil;
import com.liferay.portal.kernel.search.SearchContext;
import com.liferay.portal.kernel.search.SearchContextFactory;
import com.liferay.portal.kernel.search.SearchException;
import com.liferay.portal.kernel.util.GetterUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.training.amf.newsletter.model.NewsletterIssue;
import com.liferay.training.amf.newsletter.model.NewsletterSearchResult;

public class NewsletterSearchService {
	
	public NewsletterSearchResult searchNewsletters(RenderRequest renderRequest, long groupId, String searchKeyword,
			int startIndex, int endIndex) {
		
		HttpServletRequest httpServletRequest = PortalUtil.getHttpServletRequest(renderRequest);
		
		SearchContext searchContext = SearchContextFactory.getInstance(httpServletRequest);
	    searchContext.setKeywords(searchKeyword);
	    searchContext.setAttribute("paginationType", "more");
	    searchContext.setStart(startIndex);
	    searchContext.setEnd(endIndex);
	    
	    Indexer journalArticleIndexer = IndexerRegistryUtil.getIndexer(JournalArticle.class);
	    
	    Hits hits;
		try {
			hits = journalArticleIndexer.search(searchContext);
		} catch (SearchException e) {
			_log.fatal(String.format("Error during search: %s", e.getMessage()));
			return null;
		}
	    _log.fatal(String.format("hits.Length = %d", hits != null ? hits.getLength() : 0));

	    Map<Integer, NewsletterIssue> newsletterIssues = new HashMap<Integer, NewsletterIssue>();
	    NewsletterIssueSaxReaderMapper newsLetterIssueMapper = new NewsletterIssueSaxReaderMapper();
	    
        for (int i = 0; i < hits.getDocs().length; i++) {
            Document doc = hits.doc(i);

            String articleIdAsString = GetterUtil.getString(doc.get("articleId"));

            JournalArticle journalArticle = null;

            try {
            	journalArticle = JournalArticleLocalServiceUtil.getArticle(groupId, articleIdAsString);
            } 
            catch (PortalException pe) {
                _log.error(pe.getLocalizedMessage());
                continue;
            } 
            catch (SystemException se) {
                _log.error(se.getLocalizedMessage());
                continue;
            }
            
        	NewsletterIssue newsletterIssue = getNewsletterIssue(newsLetterIssueMapper, journalArticle);
        	if (newsletterIssue == null) continue;
        	
        	Integer issueNumber = newsletterIssue.get_issueNumber();
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
			totalHits = (int) journalArticleIndexer.searchCount(searchContext);
		} catch (SearchException e) {
			_log.fatal(String.format("Error during search count: %s", e.getMessage()));
		}
        newsletterSearchResult.set_totalCount(totalHits);
        
        return newsletterSearchResult;
	}
	
	//TODO: move to shared class
	private boolean isNewsletterIssue(JournalArticle journalArticle) {
		return structureKeyEqualsIgnoreCase(journalArticle, NEWSLETTER_ISSUE_STRUCTURE_KEY);
	}
	
	//TODO: move to shared class
	private boolean isNewsletterArticle(JournalArticle journalArticle) {
		return structureKeyEqualsIgnoreCase(journalArticle, NEWSLETTER_ARTICLE_STRUCTURE_KEY);
	}
	
	//TODO: move to shared class
	private boolean structureKeyEqualsIgnoreCase(JournalArticle journalArticle, String structureKey) {
		String structureKeyFromJournalArticle = journalArticle.getDDMStructureKey();
		_log.fatal(String.format("structureKey = %s", structureKey));
		return structureKeyFromJournalArticle.equalsIgnoreCase(structureKey);
	}
	
	private NewsletterIssue getNewsletterIssue(NewsletterIssueSaxReaderMapper newsLetterIssueMapper, JournalArticle journalArticle) {
		
		NewsletterIssue newsletterIssue = null;
        if (isNewsletterIssue(journalArticle))
			newsletterIssue = newsLetterIssueMapper.map(journalArticle);
        else if (isNewsletterArticle(journalArticle))
        	newsletterIssue = null; //TODO: search for parent newsletter issue
    	return newsletterIssue;
	}
	
	//TODO: look up structure instead of hard-coded structure keys
	private static final String NEWSLETTER_ISSUE_STRUCTURE_KEY = "45727";
	private static final String NEWSLETTER_ARTICLE_STRUCTURE_KEY = "45731";
		
	private static final Log _log = LogFactoryUtil.getLog(NewsletterSearchService.class);
}
