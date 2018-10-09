package com.liferay.training.amf.newsletter.service;

import java.util.ArrayList;
import java.util.List;

import com.liferay.training.amf.newsletter.model.NewsletterIssue;

public class NewsletterIssueContainer {

	private static List<NewsletterIssue> _newsletterIssues = new ArrayList<NewsletterIssue>();
	
	public static NewsletterIssue getIssue(Integer issueNumber) {
		
	    for (NewsletterIssue newsletterIssue : _newsletterIssues) {
	        if (newsletterIssue.get_issueNumber().equals(issueNumber)) {
	            return newsletterIssue;
	        }
	    }
	    return null;
	}
	
	public static void setIssue(NewsletterIssue newsletterIssue) {
		_newsletterIssues.add(newsletterIssue);
	}
	
	public static void setIssues(List<NewsletterIssue> newsletterIssues) {
		_newsletterIssues = newsletterIssues;
	}

	public static void clear() {
		_newsletterIssues.clear();
	}
}
