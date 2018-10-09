<%@ include file="/init.jsp" %>

<portlet:actionURL var="searchUrl"
				   name="/user/search">
	<portlet:param name="redirect" value="${param.redirect}" />
</portlet:actionURL>

<div class="container-fluid-1280">
	<aui:form name="fm" action="${searchUrl}">
		<aui:input name="zipCode" label="Enter US Zip"/>
		<aui:button cssClass="btn-lg" value="Search" type="Submit"/>
	</aui:form>
</div>