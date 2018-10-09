package com.liferay.training.amf.newsletter.web.portlet.command;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.training.amf.newsletter.web.portleturlgeneration.PortletUrlGenerator;

import amf.newsletter.web.constants.AmfNewsletterWebPortletKeys;

@Component(immediate = true,
property = {
		   "javax.portlet.name=" + AmfNewsletterWebPortletKeys.AmfNewsletterWeb,
		   "javax.portlet.name=" + AmfNewsletterWebPortletKeys.AmfNewsletterSearchResultsWeb,
		   "mvc.command.name=/newsletters/search/redirect"
},
service = MVCActionCommand.class)
public class SearchNewslettersRedirectMVCActionCommand extends BaseMVCActionCommand {
	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) 
		throws Exception {
		
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);
		long groupId = themeDisplay.getScopeGroupId();
		
		PortletURL redirectUrlForNewsletterSearchMVCRenderCommand = new PortletUrlGenerator().generate(actionRequest, groupId,
				"/newsletter/search", AmfNewsletterWebPortletKeys.AmfNewsletterSearchResultsWeb);
		redirectUrlForNewsletterSearchMVCRenderCommand.setParameter("mvcRenderCommandName", "/newsletters/search");
		
		String searchKeyword = ParamUtil.getString(actionRequest, "searchKeyword");
		redirectUrlForNewsletterSearchMVCRenderCommand.setParameter("searchKeyword", searchKeyword);
				
		_log.fatal(String.format("redirectUrlForNewsletterIssueMVCRenderCommand = %s", 
				redirectUrlForNewsletterSearchMVCRenderCommand.toString()));

		actionResponse.sendRedirect(redirectUrlForNewsletterSearchMVCRenderCommand.toString());
	}
	
	private static final Log _log = LogFactoryUtil.getLog(NewsletterIssueRedirectMVCActionCommand.class);
}
