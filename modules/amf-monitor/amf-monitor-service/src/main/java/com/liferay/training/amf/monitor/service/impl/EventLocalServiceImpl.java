/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.training.amf.monitor.service.impl;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.security.auth.PrincipalException;
import com.liferay.portal.kernel.security.permission.PermissionChecker;
import com.liferay.portal.kernel.security.permission.PermissionThreadLocal;
import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.training.amf.monitor.model.Event;
import com.liferay.training.amf.monitor.service.base.EventLocalServiceBaseImpl;
import com.liferay.training.amf.monitor.service.permission.EventPermissionChecker;

import java.util.List;

/**
 * The implementation of the event local service.
 *
 * <p>
 * All custom service methods should be put in this class. Whenever methods are added, rerun ServiceBuilder to copy their definitions into the {@link com.liferay.training.amf.monitor.service.EventLocalService} interface.
 *
 * <p>
 * This is a local service. Methods of this service will not have security checks based on the propagated JAAS credentials because this service can only be accessed from within the same VM.
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see EventLocalServiceBaseImpl
 * @see com.liferay.training.amf.monitor.service.EventLocalServiceUtil
 */
public class EventLocalServiceImpl extends EventLocalServiceBaseImpl {

	/**
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never reference this class directly. Always use {@link com.liferay.training.amf.monitor.service.EventLocalServiceUtil} to access the event local service.
	 */
	@Override
	public Event addEvent(Event event) {
		long eventId = counterLocalService.increment(Event.class.getName());

		event.setEventId(eventId);

		return super.addEvent(event);
	}

	public List<Event> getEvents(long groupId, int start, int end, OrderByComparator<Event> comparator) {
		List<Event> events = null;

		try {
			if (EventPermissionChecker.contains(getPermissionChecker(), groupId, EventPermissionChecker.VIEW_EVENTS_FOR_ALL_USERS))
				events = eventPersistence.findAll(start, end, comparator);
			else _log.fatal("EventPermissionChecker.contains returned false");
		} catch (Exception e) {
			_log.fatal(String.format("EventLocalServiceImpl.getEvents: %s", e.getMessage()));
		}

		return events;
	}

	public List<Event> getEvents(String userName, int start, int end, OrderByComparator<Event> comparator) {
		return eventPersistence.findByUserName(userName, start, end, comparator);
	}

	public List<Event> getEvents(
		String userName, String eventType, int start, int end, OrderByComparator<Event> comparator) {

		return eventPersistence.findByUserNameAndEventType(userName, eventType, start, end, comparator);
	}

	@Override
	public List<Event> getEventsByEventType(String eventType, int start, int end) {

		// TODO Auto-generated method stub

		return null;
	}

	public PermissionChecker getPermissionChecker() throws PrincipalException {
		PermissionChecker permissionChecker = PermissionThreadLocal.getPermissionChecker();

		if (permissionChecker == null) {
			throw new PrincipalException("PermissionChecker not initialized");
		}

		return permissionChecker;
	}

	private static final Log _log = LogFactoryUtil.getLog(EventLocalServiceImpl.class);

}