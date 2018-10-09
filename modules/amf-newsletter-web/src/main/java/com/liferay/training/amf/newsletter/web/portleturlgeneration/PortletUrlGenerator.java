package com.liferay.training.amf.newsletter.web.portleturlgeneration;

import javax.portlet.ActionRequest;
import javax.portlet.PortletRequest;
import javax.portlet.PortletURL;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.portlet.PortletURLFactoryUtil;
import com.liferay.portal.kernel.service.LayoutLocalServiceUtil;

//TODO: move this to a shared module available for any web module
public class PortletUrlGenerator {

	public PortletURL generate(ActionRequest actionRequest, long groupId, String friendlyUrl, String portletKey) {
		
		long plid = 0;
		try {
			plid = LayoutLocalServiceUtil.getFriendlyURLLayout(groupId, false, friendlyUrl).getPlid();
		} catch (PortalException e) {
			_log.fatal(String.format("getFriendlyURLLayout - Exception = (%s)", e.getMessage()));
		} 

		return PortletURLFactoryUtil.create(actionRequest, portletKey, plid, PortletRequest.RENDER_PHASE);
	}
	
	private static final Log _log = LogFactoryUtil.getLog(PortletUrlGenerator.class);
}
