<%@ include file="/init.jsp" %>

<portlet:renderURL var="allEventTypeUrl">
		<portlet:param name="mvcRenderCommandName"
					   value="/events/view"/>
					   
	    <portlet:param name="eventType"
	   				   value="all"/>
</portlet:renderURL>

<portlet:renderURL var="registrationEventTypeUrl">
		<portlet:param name="mvcRenderCommandName"
					   value="/events/view"/>
					   
	    <portlet:param name="eventType"
	   				   value="registration"/>
</portlet:renderURL>

<portlet:renderURL var="logInEventTypeUrl">
		<portlet:param name="mvcRenderCommandName"
					   value="/events/view"/>
					   
	    <portlet:param name="eventType"
	   				   value="login"/>
</portlet:renderURL>

<div>

	<label class=aui-field-label">Filter on Event Type</label>
	<aui:input label="All" name="eventTypeRadioButton" type="radio" checked="true" onclick="location.href='${allEventTypeUrl}'" />
	<aui:input label="Registration" name="eventTypeRadioButton" type="radio" onclick="location.href='${registrationEventTypeUrl}'" />
	<aui:input label="Log In" name="eventTypeRadioButton" type="radio"onclick="location.href='${logInEventTypeUrl}'" />

	<liferay-ui:search-container searchContainer="${eventSearchContainer}" orderByType="desc">
		<liferay-ui:search-container-row
			className="com.liferay.training.amf.monitor.Event"
			keyProperty="userId"
			modelVar="event">

			<liferay-ui:search-container-column-text name="event-create-date" orderable="true" orderableProperty="createDate">
				<fmt:formatDate pattern="yyyy-MM-dd hh:mm:ss" value="${event.createDate}" /> 
			</liferay-ui:search-container-column-text>
			<liferay-ui:search-container-column-text name="event-user">
				${event.userName} (${event.userId})
			</liferay-ui:search-container-column-text>
			<liferay-ui:search-container-column-text name="event-ipAddress" property="ipAddress"/>
			<liferay-ui:search-container-column-text name="event-eventType" property="eventType"/>

		</liferay-ui:search-container-row>

		<liferay-ui:search-iterator markupView="lexicon" paginate="true"/>

	</liferay-ui:search-container>

</div>