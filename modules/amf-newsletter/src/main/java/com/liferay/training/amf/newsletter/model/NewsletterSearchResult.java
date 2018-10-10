package com.liferay.training.amf.newsletter.model;

import java.util.ArrayList;

public class NewsletterSearchResult {

	public ArrayList<NewsletterIssue> get_newsletterIssues() {
		return _newsletterIssues;
	}

	public int get_totalCount() {
		return _totalCount;
	}

	public void set_newsletterIssues(ArrayList<NewsletterIssue> _newsletterIssues) {
		this._newsletterIssues = _newsletterIssues;
	}

	public void set_totalCount(int _totalCount) {
		this._totalCount = _totalCount;
	}

	private ArrayList<NewsletterIssue> _newsletterIssues;
	private int _totalCount;

}