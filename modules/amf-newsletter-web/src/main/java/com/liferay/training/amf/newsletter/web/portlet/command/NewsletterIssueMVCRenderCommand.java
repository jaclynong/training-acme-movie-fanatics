package com.liferay.training.amf.newsletter.web.portlet.command;

import amf.newsletter.web.constants.AmfNewsletterWebPortletKeys;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.training.amf.newsletter.model.NewsletterIssue;
import com.liferay.training.amf.newsletter.service.NewsletterIssueContainer;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true,
property = {
		"javax.portlet.name=" + AmfNewsletterWebPortletKeys.AmfNewsletterIssueWeb, "mvc.command.name=/newsletter/issue"
},
service = MVCRenderCommand.class)
public class NewsletterIssueMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
		_log.fatal("Entered render method");

		String newsletterIssueNumberAsString = ParamUtil.getString(renderRequest, "newsletterIssueNumber");

		if (newsletterIssueNumberAsString == null) return VIEW_PATH;

		Integer newsletterIssueNumber = Integer.parseInt(newsletterIssueNumberAsString);

		NewsletterIssue newsletterIssue = NewsletterIssueContainer.getIssue(newsletterIssueNumber);

		renderRequest.setAttribute("newsletterIssue", newsletterIssue);

		return VIEW_PATH;
	}

	private static final String VIEW_PATH = "/newsletter-issue/newsletter_issue.jsp";

	private static final Log _log = LogFactoryUtil.getLog(NewsletterIssueMVCRenderCommand.class);

}