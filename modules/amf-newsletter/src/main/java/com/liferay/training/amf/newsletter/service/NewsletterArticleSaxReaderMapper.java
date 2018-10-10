package com.liferay.training.amf.newsletter.service;

import com.liferay.journal.model.JournalArticle;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.DocumentException;
import com.liferay.portal.kernel.xml.SAXReaderUtil;
import com.liferay.training.amf.newsletter.model.NewsletterArticle;

public class NewsletterArticleSaxReaderMapper extends BaseNewsletterSaxReaderMapper {

	public NewsletterArticle map(JournalArticle journalArticle) {
		NewsletterArticle newsLetterArticle = new NewsletterArticle(journalArticle);

		String journalArticleContent = journalArticle.getContent();

		try {
			Document doc = SAXReaderUtil.read(journalArticleContent);

			Integer issueNumber = getIssueNumber(doc);

			newsLetterArticle.set_issueNumber(issueNumber);

			parseAndSetDescription(doc, newsLetterArticle);
			parseAndSetOrder(doc, newsLetterArticle);
			parseAndSetContent(doc, newsLetterArticle);

		} catch (DocumentException e) {
			_log.fatal(String.format("NewsletterArticleSaxReaderMapper.map(journalArticle) - " +
				"exception message=(%s)", e.getMessage()));
		}

		return newsLetterArticle;
	}

	private void parseAndSetContent(Document doc, NewsletterArticle newsLetterArticle) {
		String content = parseFieldFromDocument(doc, "Content");

		if (content == null) return;

		newsLetterArticle.set_content(content);
	}

	private void parseAndSetDescription(Document doc, NewsletterArticle newsLetterArticle) {
		String author = parseFieldFromDocument(doc, "Author");

		if (author == null) return;
		newsLetterArticle.set_author(author);
	}

	private void parseAndSetOrder(Document doc, NewsletterArticle newsLetterArticle) {
		String orderAsString = parseFieldFromDocument(doc, "Order");

		if (orderAsString == null) return;

		Integer order = Integer.parseInt(orderAsString);

		newsLetterArticle.set_order(order);
	}

	private static final String ARTICLE_CONTENT_XML_NODE_END = "']/dynamic-content";

	private static final String ARTICLE_CONTENT_XML_NODE_START = "/root/dynamic-element[@name='";

	private static final Log _log = LogFactoryUtil.getLog(NewsletterIssueSaxReaderMapper.class);

}