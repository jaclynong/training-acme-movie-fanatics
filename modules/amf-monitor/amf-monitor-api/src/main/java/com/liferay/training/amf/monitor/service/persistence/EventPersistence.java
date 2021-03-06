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

package com.liferay.training.amf.monitor.service.persistence;

import aQute.bnd.annotation.ProviderType;

import com.liferay.portal.kernel.service.persistence.BasePersistence;

import com.liferay.training.amf.monitor.exception.NoSuchEventException;
import com.liferay.training.amf.monitor.model.Event;

/**
 * The persistence interface for the event service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see com.liferay.training.amf.monitor.service.persistence.impl.EventPersistenceImpl
 * @see EventUtil
 * @generated
 */
@ProviderType
public interface EventPersistence extends BasePersistence<Event> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link EventUtil} to access the event persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Returns all the events where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the matching events
	*/
	public java.util.List<Event> findByUuid(String uuid);

	/**
	* Returns a range of all the events where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of events
	* @param end the upper bound of the range of events (not inclusive)
	* @return the range of matching events
	*/
	public java.util.List<Event> findByUuid(String uuid, int start, int end);

	/**
	* Returns an ordered range of all the events where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of events
	* @param end the upper bound of the range of events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching events
	*/
	public java.util.List<Event> findByUuid(String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator);

	/**
	* Returns an ordered range of all the events where uuid = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param uuid the uuid
	* @param start the lower bound of the range of events
	* @param end the upper bound of the range of events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching events
	*/
	public java.util.List<Event> findByUuid(String uuid, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first event in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching event
	* @throws NoSuchEventException if a matching event could not be found
	*/
	public Event findByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator)
		throws NoSuchEventException;

	/**
	* Returns the first event in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching event, or <code>null</code> if a matching event could not be found
	*/
	public Event fetchByUuid_First(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator);

	/**
	* Returns the last event in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching event
	* @throws NoSuchEventException if a matching event could not be found
	*/
	public Event findByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator)
		throws NoSuchEventException;

	/**
	* Returns the last event in the ordered set where uuid = &#63;.
	*
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching event, or <code>null</code> if a matching event could not be found
	*/
	public Event fetchByUuid_Last(String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator);

	/**
	* Returns the events before and after the current event in the ordered set where uuid = &#63;.
	*
	* @param eventId the primary key of the current event
	* @param uuid the uuid
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next event
	* @throws NoSuchEventException if a event with the primary key could not be found
	*/
	public Event[] findByUuid_PrevAndNext(long eventId, String uuid,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator)
		throws NoSuchEventException;

	/**
	* Removes all the events where uuid = &#63; from the database.
	*
	* @param uuid the uuid
	*/
	public void removeByUuid(String uuid);

	/**
	* Returns the number of events where uuid = &#63;.
	*
	* @param uuid the uuid
	* @return the number of matching events
	*/
	public int countByUuid(String uuid);

	/**
	* Returns all the events where userName = &#63;.
	*
	* @param userName the user name
	* @return the matching events
	*/
	public java.util.List<Event> findByUserName(String userName);

	/**
	* Returns a range of all the events where userName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userName the user name
	* @param start the lower bound of the range of events
	* @param end the upper bound of the range of events (not inclusive)
	* @return the range of matching events
	*/
	public java.util.List<Event> findByUserName(String userName, int start,
		int end);

	/**
	* Returns an ordered range of all the events where userName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userName the user name
	* @param start the lower bound of the range of events
	* @param end the upper bound of the range of events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching events
	*/
	public java.util.List<Event> findByUserName(String userName, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator);

	/**
	* Returns an ordered range of all the events where userName = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userName the user name
	* @param start the lower bound of the range of events
	* @param end the upper bound of the range of events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching events
	*/
	public java.util.List<Event> findByUserName(String userName, int start,
		int end,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first event in the ordered set where userName = &#63;.
	*
	* @param userName the user name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching event
	* @throws NoSuchEventException if a matching event could not be found
	*/
	public Event findByUserName_First(String userName,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator)
		throws NoSuchEventException;

	/**
	* Returns the first event in the ordered set where userName = &#63;.
	*
	* @param userName the user name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching event, or <code>null</code> if a matching event could not be found
	*/
	public Event fetchByUserName_First(String userName,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator);

	/**
	* Returns the last event in the ordered set where userName = &#63;.
	*
	* @param userName the user name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching event
	* @throws NoSuchEventException if a matching event could not be found
	*/
	public Event findByUserName_Last(String userName,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator)
		throws NoSuchEventException;

	/**
	* Returns the last event in the ordered set where userName = &#63;.
	*
	* @param userName the user name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching event, or <code>null</code> if a matching event could not be found
	*/
	public Event fetchByUserName_Last(String userName,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator);

	/**
	* Returns the events before and after the current event in the ordered set where userName = &#63;.
	*
	* @param eventId the primary key of the current event
	* @param userName the user name
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next event
	* @throws NoSuchEventException if a event with the primary key could not be found
	*/
	public Event[] findByUserName_PrevAndNext(long eventId, String userName,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator)
		throws NoSuchEventException;

	/**
	* Removes all the events where userName = &#63; from the database.
	*
	* @param userName the user name
	*/
	public void removeByUserName(String userName);

	/**
	* Returns the number of events where userName = &#63;.
	*
	* @param userName the user name
	* @return the number of matching events
	*/
	public int countByUserName(String userName);

	/**
	* Returns all the events where userName = &#63; and eventType = &#63;.
	*
	* @param userName the user name
	* @param eventType the event type
	* @return the matching events
	*/
	public java.util.List<Event> findByUserNameAndEventType(String userName,
		String eventType);

	/**
	* Returns a range of all the events where userName = &#63; and eventType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userName the user name
	* @param eventType the event type
	* @param start the lower bound of the range of events
	* @param end the upper bound of the range of events (not inclusive)
	* @return the range of matching events
	*/
	public java.util.List<Event> findByUserNameAndEventType(String userName,
		String eventType, int start, int end);

	/**
	* Returns an ordered range of all the events where userName = &#63; and eventType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userName the user name
	* @param eventType the event type
	* @param start the lower bound of the range of events
	* @param end the upper bound of the range of events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching events
	*/
	public java.util.List<Event> findByUserNameAndEventType(String userName,
		String eventType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator);

	/**
	* Returns an ordered range of all the events where userName = &#63; and eventType = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param userName the user name
	* @param eventType the event type
	* @param start the lower bound of the range of events
	* @param end the upper bound of the range of events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of matching events
	*/
	public java.util.List<Event> findByUserNameAndEventType(String userName,
		String eventType, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Returns the first event in the ordered set where userName = &#63; and eventType = &#63;.
	*
	* @param userName the user name
	* @param eventType the event type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching event
	* @throws NoSuchEventException if a matching event could not be found
	*/
	public Event findByUserNameAndEventType_First(String userName,
		String eventType,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator)
		throws NoSuchEventException;

	/**
	* Returns the first event in the ordered set where userName = &#63; and eventType = &#63;.
	*
	* @param userName the user name
	* @param eventType the event type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching event, or <code>null</code> if a matching event could not be found
	*/
	public Event fetchByUserNameAndEventType_First(String userName,
		String eventType,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator);

	/**
	* Returns the last event in the ordered set where userName = &#63; and eventType = &#63;.
	*
	* @param userName the user name
	* @param eventType the event type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching event
	* @throws NoSuchEventException if a matching event could not be found
	*/
	public Event findByUserNameAndEventType_Last(String userName,
		String eventType,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator)
		throws NoSuchEventException;

	/**
	* Returns the last event in the ordered set where userName = &#63; and eventType = &#63;.
	*
	* @param userName the user name
	* @param eventType the event type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching event, or <code>null</code> if a matching event could not be found
	*/
	public Event fetchByUserNameAndEventType_Last(String userName,
		String eventType,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator);

	/**
	* Returns the events before and after the current event in the ordered set where userName = &#63; and eventType = &#63;.
	*
	* @param eventId the primary key of the current event
	* @param userName the user name
	* @param eventType the event type
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next event
	* @throws NoSuchEventException if a event with the primary key could not be found
	*/
	public Event[] findByUserNameAndEventType_PrevAndNext(long eventId,
		String userName, String eventType,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator)
		throws NoSuchEventException;

	/**
	* Removes all the events where userName = &#63; and eventType = &#63; from the database.
	*
	* @param userName the user name
	* @param eventType the event type
	*/
	public void removeByUserNameAndEventType(String userName, String eventType);

	/**
	* Returns the number of events where userName = &#63; and eventType = &#63;.
	*
	* @param userName the user name
	* @param eventType the event type
	* @return the number of matching events
	*/
	public int countByUserNameAndEventType(String userName, String eventType);

	/**
	* Caches the event in the entity cache if it is enabled.
	*
	* @param event the event
	*/
	public void cacheResult(Event event);

	/**
	* Caches the events in the entity cache if it is enabled.
	*
	* @param events the events
	*/
	public void cacheResult(java.util.List<Event> events);

	/**
	* Creates a new event with the primary key. Does not add the event to the database.
	*
	* @param eventId the primary key for the new event
	* @return the new event
	*/
	public Event create(long eventId);

	/**
	* Removes the event with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param eventId the primary key of the event
	* @return the event that was removed
	* @throws NoSuchEventException if a event with the primary key could not be found
	*/
	public Event remove(long eventId) throws NoSuchEventException;

	public Event updateImpl(Event event);

	/**
	* Returns the event with the primary key or throws a {@link NoSuchEventException} if it could not be found.
	*
	* @param eventId the primary key of the event
	* @return the event
	* @throws NoSuchEventException if a event with the primary key could not be found
	*/
	public Event findByPrimaryKey(long eventId) throws NoSuchEventException;

	/**
	* Returns the event with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param eventId the primary key of the event
	* @return the event, or <code>null</code> if a event with the primary key could not be found
	*/
	public Event fetchByPrimaryKey(long eventId);

	@Override
	public java.util.Map<java.io.Serializable, Event> fetchByPrimaryKeys(
		java.util.Set<java.io.Serializable> primaryKeys);

	/**
	* Returns all the events.
	*
	* @return the events
	*/
	public java.util.List<Event> findAll();

	/**
	* Returns a range of all the events.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of events
	* @param end the upper bound of the range of events (not inclusive)
	* @return the range of events
	*/
	public java.util.List<Event> findAll(int start, int end);

	/**
	* Returns an ordered range of all the events.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of events
	* @param end the upper bound of the range of events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of events
	*/
	public java.util.List<Event> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator);

	/**
	* Returns an ordered range of all the events.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link QueryUtil#ALL_POS} will return the full result set. If <code>orderByComparator</code> is specified, then the query will include the given ORDER BY logic. If <code>orderByComparator</code> is absent and pagination is required (<code>start</code> and <code>end</code> are not {@link QueryUtil#ALL_POS}), then the query will include the default ORDER BY logic from {@link EventModelImpl}. If both <code>orderByComparator</code> and pagination are absent, for performance reasons, the query will not have an ORDER BY clause and the returned result set will be sorted on by the primary key in an ascending order.
	* </p>
	*
	* @param start the lower bound of the range of events
	* @param end the upper bound of the range of events (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @param retrieveFromCache whether to retrieve from the finder cache
	* @return the ordered range of events
	*/
	public java.util.List<Event> findAll(int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator<Event> orderByComparator,
		boolean retrieveFromCache);

	/**
	* Removes all the events from the database.
	*/
	public void removeAll();

	/**
	* Returns the number of events.
	*
	* @return the number of events
	*/
	public int countAll();

	@Override
	public java.util.Set<String> getBadColumnNames();
}