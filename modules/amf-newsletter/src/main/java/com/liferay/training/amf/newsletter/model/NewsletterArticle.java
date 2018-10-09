package com.liferay.training.amf.newsletter.model;

import com.liferay.journal.model.JournalArticle;

//TODO: make base class for common fields between this & NewsletterIssue
public class NewsletterArticle {

	private JournalArticle _journalArticle;
	
	public NewsletterArticle(JournalArticle journalArticle) {
		_journalArticle = journalArticle;
	}
	
	private Integer _issueNumber;

	public Integer get_issueNumber() {
		return _issueNumber;
	}

	public void set_issueNumber(Integer _issueNumber) {
		this._issueNumber = _issueNumber;
	}
	
	public String get_title() {
		return _journalArticle != null 
				? _journalArticle.getTitleCurrentValue()
				: null;
	}
	
	private String _author;

	public String get_author() {
		return _author;
	}

	public void set_author(String _author) {
		this._author = _author;
	}
	
	private int _order;

	public int get_order() {
		return _order;
	}

	public void set_order(int _order) {
		this._order = _order;
	}
	
	private String _content;

	public String get_content() {
		return _content;
	}

	public void set_content(String _content) {
		this._content = _content;
	}
}
