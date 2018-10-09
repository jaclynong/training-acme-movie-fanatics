package com.liferay.training.amf.newsletter.web.friendlyurlmapping;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.DefaultFriendlyURLMapper;
import com.liferay.portal.kernel.portlet.FriendlyURLMapper;

import amf.newsletter.web.constants.AmfNewsletterWebPortletKeys;

@Component(
	property = {
		"com.liferay.portlet.friendly-url-routes=META-INF/friendly-url-routes/routes.xml",
		"javax.portlet.name=" + AmfNewsletterWebPortletKeys.AmfNewsletterIssueWeb
	},
	service = FriendlyURLMapper.class
)
public class NewsletterIssueFriendlyUrlMapper extends DefaultFriendlyURLMapper  {

	@Override
	public String getMapping() {		
		return _MAPPING;
	}

	private static final String _MAPPING = "article-issue";
	private static final Log _log = LogFactoryUtil.getLog(NewsletterIssueFriendlyUrlMapper.class);
}
