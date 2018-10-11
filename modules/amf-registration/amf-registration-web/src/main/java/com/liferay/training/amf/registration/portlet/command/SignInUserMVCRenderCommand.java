package com.liferay.training.amf.registration.portlet.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.training.amf.registration.constants.AmfRegistrationPortletKeys;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true,
			property = {
					"javax.portlet.name=" + AmfRegistrationPortletKeys.AmfRegistration,
					"mvc.command.name=/user/view/signIn"
			},
			service = MVCRenderCommand.class)
public class SignInUserMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
	ThemeDisplay themeDisplay = (ThemeDisplay)renderRequest.getAttribute(WebKeys.THEME_DISPLAY);

		boolean signedIn = themeDisplay.isSignedIn();

		if (signedIn) {
			SessionMessages.add(renderRequest, "keyAlreadySignedIn");

			return "/view.jsp";
		}

		return "/user/sign_in_user.jsp";
	}

}