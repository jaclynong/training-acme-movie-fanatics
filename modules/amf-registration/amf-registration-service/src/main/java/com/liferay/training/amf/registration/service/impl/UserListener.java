package com.liferay.training.amf.registration.service.impl;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.exception.ModelListenerException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.BaseModelListener;
import com.liferay.portal.kernel.model.ModelListener;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.training.amf.monitor.model.Event;
import com.liferay.training.amf.monitor.service.EventLocalServiceUtil;

@Component( immediate = true, service = ModelListener.class )
public class UserListener extends BaseModelListener<User> {

	@Override 
	public void onAfterAddAssociation(Object classPK, String associationClassName, Object associationClassPK) throws ModelListenerException { 
		try { 
			long userId = Long.parseLong(classPK.toString());
			User user = UserLocalServiceUtil.getUser(userId);
			
			Date now = getDate(LocalDateTime.now());
			String userName = user.getScreenName();
			String ipAddress = "0.0.0.0";
			
			Event createdUserEvent = EventLocalServiceUtil.createEvent(-1);
			createdUserEvent.setUserId(userId);
			createdUserEvent.setCreateDate(now);
			createdUserEvent.setEventType("Registration"); //TODO: create enum for event type
			createdUserEvent.setUserName(userName);
			createdUserEvent.setIpAddress(ipAddress);
			
			EventLocalServiceUtil.addEvent(createdUserEvent);
		} catch(Exception ex) {
			_log.fatal(String.format("UserListener - onAfterAddAssociation - exception: %s", ex.getMessage()));
		} 
		super.onAfterAddAssociation(classPK, associationClassName, associationClassPK);
	}
	
	//TODO: put this method in shared util class
	private static Date getDate(LocalDateTime localDate) {
		Date in = new Date();
		LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
		Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
		return out;
	}
	
	private static final Log _log = LogFactoryUtil.getLog(UserListener.class);
}
