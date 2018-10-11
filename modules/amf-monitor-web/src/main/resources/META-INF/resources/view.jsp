<%@ include file="/init.jsp" %>

<portlet:renderURL var="allEventTypeUrl">
		<portlet:param
			name="mvcRenderCommandName"
			value="/events/view"
		/>

		<portlet:param
			name="eventType"
			value="all"
		/>
</portlet:renderURL>

<portlet:renderURL var="registrationEventTypeUrl">
		<portlet:param
			name="mvcRenderCommandName"
			value="/events/view"
		/>

		<portlet:param
			name="eventType"
			value="registration"
		/>
</portlet:renderURL>

<portlet:renderURL var="logInEventTypeUrl">
		<portlet:param
			name="mvcRenderCommandName"
			value="/events/view"
		/>

		<portlet:param
			name="eventType"
			value="login"
		/>
</portlet:renderURL>

<div>
	<label class=aui-field-label">Filter on Event Type</label>
	<aui:input checked="true" label="All" name="eventTypeRadioButton" onclick="location.href='${allEventTypeUrl}'" type="radio" />
	<aui:input label="Registration" name="eventTypeRadioButton" onclick="location.href='${registrationEventTypeUrl}'" type="radio" />
	<aui:input label="Log In" name="eventTypeRadioButton" onclick="location.href='${logInEventTypeUrl}'" type="radio" />

	<liferay-ui:search-container orderByType="desc" searchContainer="${eventSearchContainer}">
		<liferay-ui:search-container-row
			className="com.liferay.training.amf.monitor.Event"
			keyProperty="userId"
			modelVar="event"
		>
			<liferay-ui:search-container-column-text name="event-create-date" orderable="true" orderableProperty="createDate">
				<fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${event.createDate}" />
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text name="event-user">
				${event.userName} (${event.userId})
			</liferay-ui:search-container-column-text>

			<liferay-ui:search-container-column-text name="event-ipAddress" property="ipAddress" />
			<liferay-ui:search-container-column-text name="event-eventType" property="eventType" />
		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator markupView="lexicon" paginate="true" />
	</liferay-ui:search-container>
</div>