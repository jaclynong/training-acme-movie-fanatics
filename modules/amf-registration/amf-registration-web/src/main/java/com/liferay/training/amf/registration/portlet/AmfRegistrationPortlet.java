package com.liferay.training.amf.registration.portlet;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCPortlet;
import com.liferay.training.amf.registration.constants.AmfRegistrationPortletKeys;

import javax.portlet.Portlet;

import org.osgi.service.component.annotations.Component;

/**
 * @author liferay
 * test
 */
@Component(
	immediate = true,
	property = {
		"com.liferay.portlet.add-default-resource=true", "com.liferay.portlet.display-category=category.sample",
		"com.liferay.portlet.instanceable=true", "javax.portlet.display-name=AMF Sign In & Registration",
		"javax.portlet.init-param.add-process-action-general-error-action=false",
		"javax.portlet.init-param.add-process-action-success-action=false", "javax.portlet.init-param.template-path=/",
		"javax.portlet.init-param.view-template=/view.jsp",
		"javax.portlet.name=" + AmfRegistrationPortletKeys.AmfRegistration,
		"javax.portlet.resource-bundle=content.Language", "javax.portlet.security-role-ref=power-user,user"
	},
	service = Portlet.class
)
public class AmfRegistrationPortlet extends MVCPortlet {

	private static final Log _log = LogFactoryUtil.getLog(AmfRegistrationPortlet.class);

}