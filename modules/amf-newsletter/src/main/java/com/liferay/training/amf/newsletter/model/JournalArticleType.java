package com.liferay.training.amf.newsletter.model;

//TODO: look up structure instead of hard-coded structure keys
public enum JournalArticleType {

	NEWSLETTER_ISSUE("45727"), NEWSLETTER_ARTICLE("45731");

	public String getDdmStructureKey() {
		return _ddmStructureKey;
	}

	private JournalArticleType(String ddmStructureKey)
	{

		this._ddmStructureKey = ddmStructureKey;
	}

	private String _ddmStructureKey;

}