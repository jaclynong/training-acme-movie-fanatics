package com.liferay.training.amf.newsletter.service;

import com.liferay.portal.kernel.xml.Document;

public class BaseNewsletterSaxReaderMapper extends BaseSaxReaderMapper {

	//TODO: add parsing methods for all Integers, Strings, Dates, etc., not just for specific fields to reduce duplicate code
	
	protected Integer getIssueNumber(Document doc) {
		
		String issueNumberAsString = parseFieldFromDocument(doc, "Issue");
		if (issueNumberAsString == null) return 0;
		
		return Integer.parseInt(issueNumberAsString);
	}
}
