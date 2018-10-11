package com.liferay.training.amf.registration.portlet.command;

import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.training.amf.registration.constants.AmfRegistrationPortletKeys;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;

@Component(immediate = true,
property = {"javax.portlet.name=" + AmfRegistrationPortletKeys.AmfRegistration, "mvc.command.name=/user/signedIn"},
service = MVCRenderCommand.class)
public class SignedInUserMVCRenderCommand implements MVCRenderCommand {

	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse) throws PortletException {
	return "/user/signed_in_user.jsp";
	}

}