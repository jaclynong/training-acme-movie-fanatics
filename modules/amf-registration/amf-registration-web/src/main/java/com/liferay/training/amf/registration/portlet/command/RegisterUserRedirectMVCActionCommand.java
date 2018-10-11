package com.liferay.training.amf.registration.portlet.command;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.theme.ThemeDisplay;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.training.amf.helper.PortletUrlGenerator;
import com.liferay.training.amf.registration.constants.AmfRegistrationPortletKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletURL;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true,
property = {
		"javax.portlet.name=" + AmfRegistrationPortletKeys.AmfRegistration, "mvc.command.name=/user/signup/redirect"
},
service = MVCActionCommand.class)
public class RegisterUserRedirectMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(WebKeys.THEME_DISPLAY);

		long groupId = themeDisplay.getScopeGroupId();

		PortletURL redirectUrlForRegisterUserMVCRenderCommand = new PortletUrlGenerator().generate(actionRequest, groupId,
			"/signup", AmfRegistrationPortletKeys.AmfRegistration);

		redirectUrlForRegisterUserMVCRenderCommand.setParameter("mvcRenderCommandName", "/user/signup");

		_log.fatal(String.format("redirectUrlForRegisterUserMVCRenderCommand = %s", redirectUrlForRegisterUserMVCRenderCommand));

		actionResponse.sendRedirect(redirectUrlForRegisterUserMVCRenderCommand.toString());
	}

	private static final Log _log = LogFactoryUtil.getLog(RegisterUserRedirectMVCActionCommand.class);

}