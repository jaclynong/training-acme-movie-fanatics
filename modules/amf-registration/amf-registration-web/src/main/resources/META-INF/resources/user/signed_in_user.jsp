<%@ include file="/init.jsp" %>

<portlet:actionURL
	name="/user/signout"
	var="signOutUrl"
>
	<portlet:param name="redirect" value="${param.redirect}" />
</portlet:actionURL>

<p>You are signed in!</p>

<aui:button href="${signOutUrl}" value="Sign Out" />