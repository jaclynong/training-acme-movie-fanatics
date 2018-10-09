package com.liferay.training.amf.newsletter.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.training.amf.newsletter.model.NewsletterIssue;

public class NewsletterIssueSaxReaderMapper extends BaseNewsletterSaxReaderMapper {

	public NewsletterIssue map(JournalArticle journalArticle) {
		NewsletterIssue newsLetterIssue = new NewsletterIssue(journalArticle);
		
		String journalArticleContent = journalArticle.getContent();
		try {
			
			Document doc = SAXReaderUtil.read(journalArticleContent);
			
			Integer issueNumber = getIssueNumber(doc);
			newsLetterIssue.set_issueNumber(issueNumber);
			
			parseAndSetDescription(doc, newsLetterIssue);
			parseAndSetIssueDate(doc, newsLetterIssue);
			parseAndSetByline(doc, newsLetterIssue);

		} catch (DocumentException e) {
			_log.fatal(String.format("NewsletterIssueMapper.map(journalArticle) - "
					+ "exception message=(%s)", e.getMessage()));
		}
		
		return newsLetterIssue;
	} 
	
	private void parseAndSetDescription(Document doc, NewsletterIssue newsLetterIssue) {
		
		String description = parseFieldFromDocument(doc, "Description");
		if (description == null) return;
		newsLetterIssue.set_description(description);
	}
	
	private void parseAndSetIssueDate(Document doc, NewsletterIssue newsLetterIssue) {
		
		String issueDateAsString = parseFieldFromDocument(doc, "IssueDate");
		if (issueDateAsString == null) return;
		
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date issueDate = simpleDateFormat.parse(issueDateAsString);
			newsLetterIssue.set_issueDate(issueDate);
		} catch (ParseException e) {
			_log.fatal(String.format("NewsletterIssueMapper.map(journalArticle) - "
					+ "exception message=(%s)", e.getMessage()));
		}
	}
	
	private void parseAndSetByline(Document doc, NewsletterIssue newsLetterIssue) {
		
		String byLine = parseFieldFromDocument(doc, "Byline");
		if (byLine == null) return;
		newsLetterIssue.set_byline(byLine);
	}
}
