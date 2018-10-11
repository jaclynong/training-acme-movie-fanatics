package com.liferay.training.amf.monitor.service.event;

import com.liferay.portal.kernel.util.OrderByComparator;
import com.liferay.training.amf.monitor.model.Event;

import java.util.Date;

public class EventCreateDateComparator extends OrderByComparator<Event> {

	public static final String ORDER_BY_ASC = "amf_monitor_event.createDate ASC";

	public static final String ORDER_BY_DESC = "amf_monitor_event.createDate DESC";

	public static final String[] ORDER_BY_FIELDS = {"createDate"};

	public EventCreateDateComparator() {
		this(false);
	}

	public EventCreateDateComparator(boolean ascending) {
		_ascending = ascending;
	}

	@Override
	public int compare(Event event1, Event event2) {
		Date createDate1 = event1.getCreateDate();
		Date createDate2 = event2.getCreateDate();

		int value = createDate1.compareTo(createDate2);

		if (_ascending) {
			return value;
		}
		else {
			return -value;
		}
	}

	@Override
	public String getOrderBy() {
		if (_ascending) {
			return ORDER_BY_ASC;
		}
		else {
			return ORDER_BY_DESC;
		}
	}

	@Override
	public String[] getOrderByFields() {
		return ORDER_BY_FIELDS;
	}

	@Override
	public boolean isAscending() {
		return _ascending;
	}

	private final boolean _ascending;

}