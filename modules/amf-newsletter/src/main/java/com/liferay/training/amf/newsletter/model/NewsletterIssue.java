package com.liferay.training.amf.newsletter.model;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

import com.liferay.journal.model.JournalArticle;

//TODO: make base class for common fields between this & NewsletterArticle
public class NewsletterIssue {

	private JournalArticle _journalArticle;
	
	public NewsletterIssue() { }
	
	public NewsletterIssue(JournalArticle journalArticle) {
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
	
	private String _description;

	public String get_description() {
		return _description;
	}

	public void set_description(String _description) {
		this._description = _description;
	}
	
	private Date _issueDate;

	public Date get_issueDate() {
		return _issueDate;
	}
	
	public String get_issueYear() {
		return getYear(_issueDate);
	}

	public String get_issueMonth() {
		return getMonth(_issueDate);
	}
	
	public void set_issueDate(Date _issueDate) {
		this._issueDate = _issueDate;
	}
	
	private String _byline;

	public String get_byline() {
		return _byline;
	}

	public void set_byline(String _byline) {
		this._byline = _byline;
	}
	
	private List<NewsletterArticle> _newsletterArticles;

	public List<NewsletterArticle> get_newsletterArticles() {
		return _newsletterArticles;
	}

	public void set_newsletterArticles(List<NewsletterArticle> _newsletterArticles) {
		this._newsletterArticles = _newsletterArticles;
	}
	
	//TODO: move this method out to a separate class
	private String getYear(Date date) {
		
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		Integer yearAsInt = localDate.getYear();
		return Integer.toString(yearAsInt);
	}
	
	//TODO: move this method out to a separate class
	private String getMonth(Date date) {
		
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		Month month = localDate.getMonth();
		return month.name();
	}
}