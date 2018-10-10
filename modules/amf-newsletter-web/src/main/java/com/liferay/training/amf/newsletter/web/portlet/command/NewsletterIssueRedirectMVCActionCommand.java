package com.liferay.training.amf.newsletter.web.portlet.command;

import amf.newsletter.web.constants.AmfNewsletterWebPortletKeys;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.training.amf.newsletter.web.portleturlgeneration.PortletUrlGenerator;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true,
property = {
		"javax.portlet.name=" + AmfNewsletterWebPortletKeys.AmfNewsletterWeb,
		"javax.portlet.name=" + AmfNewsletterWebPortletKeys.AmfNewsletterSearchResultsWeb,
		"mvc.command.name=/newsletter/issue/redirect"
},
service = MVCActionCommand.class)
public class NewsletterIssueRedirectMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		long groupId = themeDisplay.getScopeGroupId();

		PortletURL redirectUrlForNewsletterIssueMVCRenderCommand = new PortletUrlGenerator().generate(actionRequest, groupId,
																	"/newsletter",
																	AmfNewsletterWebPortletKeys.AmfNewsletterIssueWeb);

		Integer newsletterIssueNumber = ParamUtil.getInteger(actionRequest, "newsletterIssueNumber");
		redirectUrlForNewsletterIssueMVCRenderCommand.setParameter(
			"newsletterIssueNumber", newsletterIssueNumber.toString());
		redirectUrlForNewsletterIssueMVCRenderCommand.setParameter("mvcRenderCommandName", "/newsletter/issue");

		actionResponse.sendRedirect(redirectUrlForNewsletterIssueMVCRenderCommand.toString());
	}

	private static final Log _log = LogFactoryUtil.getLog(NewsletterIssueRedirectMVCActionCommand.class);

}