package com.liferay.training.amf.newsletter.web.portlet.command;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;

import amf.newsletter.web.constants.AmfNewsletterWebPortletKeys;

@Component(immediate = true,
property = {
		   "javax.portlet.name=" + AmfNewsletterWebPortletKeys.AmfNewsletterWeb,
		   "mvc.command.name=/newsletter/issue/redirect"
},
service = MVCActionCommand.class)
public class NewsletterIssueRedirectMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) 
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		long groupId = themeDisplay.getScopeGroupId();
		
		PortletURL redirectUrlForNewsletterIssueMVCRenderCommand = generateUrlForNewsletterIssueView(actionRequest, groupId);
		
		Integer newsletterIssueNumber = ParamUtil.getInteger(actionRequest, "newsletterIssueNumber");
		redirectUrlForNewsletterIssueMVCRenderCommand.setParameter("newsletterIssueNumber", newsletterIssueNumber.toString());
		redirectUrlForNewsletterIssueMVCRenderCommand.setParameter("mvcRenderCommandName", "/newsletter/issue");

		actionResponse.sendRedirect(redirectUrlForNewsletterIssueMVCRenderCommand.toString());
	}
	
	private PortletURL generateUrlForNewsletterIssueView(ActionRequest actionRequest, long groupId) {
		
		long plid = 0;
		try {
			plid = LayoutLocalServiceUtil.getFriendlyURLLayout(groupId, false, "/newsletter").getPlid();
		} catch (PortalException e) {
			_log.fatal(String.format("getFriendlyURLLayout - Exception = (%s)", e.getMessage()));
		} 
		_log.fatal(String.format("plid = %d", plid));
		
		PortletURL portletUrl = PortletURLFactoryUtil.create(actionRequest, AmfNewsletterWebPortletKeys.AmfNewsletterIssueWeb, plid, PortletRequest.RENDER_PHASE);
		_log.fatal(String.format("portletUrl = %s", portletUrl.toString()));
		
		return portletUrl;
	}
	
	private static final Log _log = LogFactoryUtil.getLog(NewsletterIssueRedirectMVCActionCommand.class);
}
