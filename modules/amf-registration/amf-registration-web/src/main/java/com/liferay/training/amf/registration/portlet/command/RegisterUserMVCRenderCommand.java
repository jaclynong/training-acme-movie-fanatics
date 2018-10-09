package com.liferay.training.amf.registration.portlet.command;

import javax.portlet.PortletException;
import javax.portlet.PortletSession;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.training.amf.registration.constants.AmfRegistrationPortletKeys;

@Component(immediate = true,
		   property = {
				   "javax.portlet.name=" + AmfRegistrationPortletKeys.AmfRegistration,
				   "mvc.command.name=/user/signup"
		   },
		   service = MVCRenderCommand.class)
public class RegisterUserMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws PortletException {		
		
		ThemeDisplay themeDisplay = (ThemeDisplay) renderRequest.getAttribute(WebKeys.THEME_DISPLAY);
		boolean signedIn = themeDisplay.isSignedIn();
		
		if (signedIn) {
			SessionMessages.add(renderRequest, "key-already-signed-in");
			return "/view.jsp";
		}
		
		PortletSession portletSession = renderRequest.getPortletSession();
		
		try {
			String userNameSignedIn = (String)portletSession.getAttribute("usernameSignedIn", PortletSession.APPLICATION_SCOPE);
			
			if (userNameSignedIn == null || userNameSignedIn.isEmpty())
				return "/user/register_user.jsp";
			else
				return "/user/signed_in_user.jsp";
			
		} catch (Exception e) {
			return "/view.jsp";
		} 
	}
	
	protected UserLocalServiceUtil _userService;
	private static final Log _log = LogFactoryUtil.getLog(
			RegisterUserMVCRenderCommand.class);
}


