create table AMF_MONITOR_Event (
	uuid_ VARCHAR(75) null,
	eventId LONG not null primary key,
	createDate DATE null,
	eventType VARCHAR(75) null,
	userName VARCHAR(75) null,
	userId LONG,
	ipAddress VARCHAR(75) null
);