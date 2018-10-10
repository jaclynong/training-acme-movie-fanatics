package com.liferay.training.amf.newsletter.service;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.xml.Document;
import com.liferay.portal.kernel.xml.Node;

//TODO: make this implement a JournalArticle mapper interface
public class BaseSaxReaderMapper {

	protected String parseFieldFromDocument(Document doc, String nodeName) {
		Node node = doc.selectSingleNode(ARTICLE_CONTENT_XML_NODE_START + nodeName + ARTICLE_CONTENT_XML_NODE_END);

		if (node == null) return null;

		String value = node.getText();

		return value;
	}

	protected static final String ARTICLE_CONTENT_XML_NODE_END = "']/dynamic-content";

	protected static final String ARTICLE_CONTENT_XML_NODE_START = "/root/dynamic-element[@name='";

	protected static final Log _log = LogFactoryUtil.getLog(NewsletterIssueSaxReaderMapper.class);

}