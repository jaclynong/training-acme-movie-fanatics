package com.liferay.training.amf.newsletter.service;

import com.liferay.training.amf.newsletter.model.NewsletterIssue;

import java.util.ArrayList;
import java.util.List;

public class NewsletterIssueContainer {

	public static void clear() {
		_newsletterIssues.clear();
	}

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

	private static List<NewsletterIssue> _newsletterIssues = new ArrayList<>();

}