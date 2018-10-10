package com.liferay.training.amf.newsletter.model;

import com.liferay.journal.model.JournalArticle;

//TODO: make base class for common fields between this & NewsletterIssue
public class NewsletterArticle {

	public NewsletterArticle(JournalArticle journalArticle) {
		_journalArticle = journalArticle;
	}

	public String get_author() {
		return _author;
	}

	public String get_content() {
		return _content;
	}

	public Integer get_issueNumber() {
		return _issueNumber;
	}

	public int get_order() {
		return _order;
	}

	public String get_title() {
		if (_journalArticle != null) {
			return _journalArticle.getTitleCurrentValue();
		}

		return null;
	}

	public void set_author(String _author) {
		this._author = _author;
	}

	public void set_content(String _content) {
		this._content = _content;
	}

	public void set_issueNumber(Integer _issueNumber) {
		this._issueNumber = _issueNumber;
	}

	public void set_order(int _order) {
		this._order = _order;
	}

	private String _author;
	private String _content;
	private Integer _issueNumber;
	private JournalArticle _journalArticle;
	private int _order;

}