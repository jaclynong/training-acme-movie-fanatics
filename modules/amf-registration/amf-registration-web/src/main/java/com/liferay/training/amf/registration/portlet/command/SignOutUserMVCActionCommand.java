package com.liferay.training.amf.registration.portlet.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.training.amf.registration.constants.AmfRegistrationPortletKeys;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletSession;

import org.osgi.service.component.annotations.Component;

@Component(
		immediate = true,
		property = {
			"javax.portlet.name=" + AmfRegistrationPortletKeys.AmfRegistration, "mvc.command.name=/user/signout"
	},
	service = MVCActionCommand.class
)
public class SignOutUserMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		PortletSession portletSession = actionRequest.getPortletSession();

		portletSession.setAttribute("usernameSignedIn", null, PortletSession.APPLICATION_SCOPE);
	}

}