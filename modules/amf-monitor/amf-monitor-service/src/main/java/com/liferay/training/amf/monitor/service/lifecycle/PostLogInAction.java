package com.liferay.training.amf.monitor.service.lifecycle;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.osgi.service.component.annotations.Component;

import com.liferay.portal.kernel.events.ActionException;
import com.liferay.portal.kernel.events.LifecycleAction;
import com.liferay.portal.kernel.events.LifecycleEvent;
import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.util.PortalUtil;
import com.liferay.training.amf.monitor.model.Event;
import com.liferay.training.amf.monitor.service.EventLocalServiceUtil;

@Component( immediate = true,
			property = {"key=login.events.post"}, 
			service = LifecycleAction.class 
)
public class PostLogInAction implements LifecycleAction {

	@Override
	public void processLifecycleEvent(LifecycleEvent lifecycleEvent) throws ActionException {
		HttpServletRequest request = lifecycleEvent.getRequest();
		
		logUserLoggedInEvent(request);
		
	}
	
	private void logUserLoggedInEvent(HttpServletRequest httpServletRequest) {
		
		Date now = getDate(LocalDateTime.now());
		long userId = 0;
		String userName = null;
		String ipAddress = null;
		
		try {
			User currentUser = PortalUtil.getUser(httpServletRequest);
			userId = currentUser.getUserId();
			userName = currentUser.getScreenName();
			ipAddress = currentUser.getLastLoginIP();
		} catch (PortalException e) {
			_log.fatal("SignInUserMVCActionCommand.logUserLoggedInEvent - Failed to get current user");
			return;
		}
		
		Event createdUserEvent = EventLocalServiceUtil.createEvent(-1);
		createdUserEvent.setUserId(userId);
		createdUserEvent.setCreateDate(now);
		createdUserEvent.setEventType("Login"); //TODO: create enum for event type
		createdUserEvent.setUserName(userName);
		createdUserEvent.setIpAddress(ipAddress);
		
		EventLocalServiceUtil.addEvent(createdUserEvent);
	}

	//TODO: put this method in shared util class
	private static Date getDate(LocalDateTime localDate) {
		Date in = new Date();
		LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
		Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
		return out;
	}
	
	private static final Log _log = LogFactoryUtil.getLog(PostLogInAction.class);
}
