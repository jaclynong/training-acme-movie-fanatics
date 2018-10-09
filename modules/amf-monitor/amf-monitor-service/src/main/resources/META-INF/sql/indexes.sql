create index IX_B59824CE on AMF_MONITOR_Event (eventType[$COLUMN_LENGTH:75$]);
create index IX_9D51BFA4 on AMF_MONITOR_Event (userName[$COLUMN_LENGTH:75$], eventType[$COLUMN_LENGTH:75$]);
create index IX_7FAD9FDE on AMF_MONITOR_Event (uuid_[$COLUMN_LENGTH:75$]);