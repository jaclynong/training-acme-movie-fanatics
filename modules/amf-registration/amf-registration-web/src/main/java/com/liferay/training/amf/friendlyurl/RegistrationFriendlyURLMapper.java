package com.liferay.training.amf.friendlyurl;

import org.osgi.service.component.annotations.Component;
import com.liferay.portal.kernel.portlet.DefaultFriendlyURLMapper;
import com.liferay.portal.kernel.portlet.FriendlyURLMapper;
import com.liferay.training.amf.registration.constants.AmfRegistrationPortletKeys;

@Component(
	property = {
		"com.liferay.portlet.friendly-url-routes=META-INF/friendly-url-routes/routes.xml",
		"javax.portlet.name=" + AmfRegistrationPortletKeys.AmfRegistration
	},
	service = FriendlyURLMapper.class
)
public class RegistrationFriendlyURLMapper extends DefaultFriendlyURLMapper {

	@Override
	public String getMapping() {
		return _MAPPING;
	}

	private static final String _MAPPING = "signup";

}
