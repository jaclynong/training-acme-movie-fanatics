package com.liferay.training.amf.registration.portlet.command;

import com.liferay.portal.kernel.model.CompanyConstants;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.security.auth.session.AuthenticatedSessionManagerUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.training.amf.registration.constants.AmfRegistrationPortletKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.osgi.service.component.annotations.Component;

@Component(
		immediate = true,
		property = {
			"javax.portlet.name=" + AmfRegistrationPortletKeys.AmfRegistration, "mvc.command.name=/user/signIn"
	},
	service = MVCActionCommand.class
)
public class SignInUserMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		HttpServletRequest request = PortalUtil.getOriginalServletRequest(
			PortalUtil.getHttpServletRequest(actionRequest));
		HttpServletResponse response = PortalUtil.getHttpServletResponse(actionResponse);

		String userName = ParamUtil.getString(actionRequest, "userName");
		String password = ParamUtil.getString(actionRequest, "password");
		String authType = CompanyConstants.AUTH_TYPE_SN;

		AuthenticatedSessionManagerUtil.login(request, response, userName, password, false, authType);

		sendRedirect(actionRequest, actionResponse);
	}

}