package com.liferay.training.amf.registration.portlet.command;

import javax.portlet.PortletException;
import javax.portlet.RenderRequest;
import javax.portlet.RenderResponse;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.ReferenceCardinality;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCRenderCommand;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.training.amf.registration.constants.AmfRegistrationPortletKeys;
import com.liferay.training.amf.registration.portlet.AmfRegistrationUtil;

@Component(immediate = true,
property = {
		   "javax.portlet.name=" + AmfRegistrationPortletKeys.AmfRegistration,
		   "mvc.command.name=/user/signedIn"
},
service = MVCRenderCommand.class)
public class SignedInUserMVCRenderCommand implements MVCRenderCommand {
	@Override
	public String render(RenderRequest renderRequest, RenderResponse renderResponse)
			throws PortletException {
	
		return "/user/signed_in_user.jsp";
	}

}
