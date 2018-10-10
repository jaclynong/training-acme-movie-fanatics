package com.liferay.training.amf.newsletter.model;

import com.liferay.journal.model.JournalArticle;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;

import java.util.Date;
import java.util.List;

//TODO: make base class for common fields between this & NewsletterArticle
public class NewsletterIssue {

	public NewsletterIssue() {
	}

	public NewsletterIssue(JournalArticle journalArticle) {
		_journalArticle = journalArticle;
	}

	public String get_byline() {
		return _byline;
	}

	public String get_description() {
		return _description;
	}

	public Date get_issueDate() {
		return _issueDate;
	}

	public String get_issueMonth() {
		return getMonth(_issueDate);
	}

	public Integer get_issueNumber() {
		return _issueNumber;
	}

	public String get_issueYear() {
		return getYear(_issueDate);
	}

	public List<NewsletterArticle> get_newsletterArticles() {
		return _newsletterArticles;
	}

	private String _title;
	
	public String get_title() {
		if (_journalArticle != null) {
			return _journalArticle.getTitleCurrentValue();
		} else {
			return _title;
		}
	}
	
	public void set_title(String _title) {
		this._title = _title;
	}

	public void set_byline(String _byline) {
		this._byline = _byline;
	}

	public void set_description(String _description) {
		this._description = _description;
	}

	public void set_issueDate(Date _issueDate) {
		this._issueDate = _issueDate;
	}

	public void set_issueNumber(Integer _issueNumber) {
		this._issueNumber = _issueNumber;
	}

	public void set_newsletterArticles(List<NewsletterArticle> _newsletterArticles) {
		this._newsletterArticles = _newsletterArticles;
	}

	//TODO: move this method out to a separate class
	private String getMonth(Date date) {
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		Month month = localDate.getMonth();

		return month.name();
	}

	//TODO: move this method out to a separate class
	private String getYear(Date date) {
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		Integer yearAsInt = localDate.getYear();

		return String.valueOf(yearAsInt);
	}

	private String _byline;
	private String _description;
	private Date _issueDate;
	private Integer _issueNumber;
	private JournalArticle _journalArticle;
	private List<NewsletterArticle> _newsletterArticles;

}