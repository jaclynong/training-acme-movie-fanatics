package com.liferay.training.amf.newsletter.service;

import com.liferay.training.amf.newsletter.model.NewsletterIssue;

import java.util.Comparator;

public class NewsletterIssueComparator implements Comparator<NewsletterIssue> {

	public NewsletterIssueComparator() {
		this(true);
	}

	public NewsletterIssueComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(NewsletterIssue newsletterIssue1, NewsletterIssue newsletterIssue2) {
		int returnValue = newsletterIssue1.get_issueDate().compareTo(newsletterIssue2.get_issueDate());

		if (_ascending) {
			return returnValue;
		}
		else {
			return -returnValue;
		}
	}

	private final boolean _ascending;

}