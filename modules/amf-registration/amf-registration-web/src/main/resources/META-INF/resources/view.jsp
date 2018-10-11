<%@ include file="/init.jsp" %>

<portlet:renderURL var="signUpUrl">
	<portlet:param
		name="mvcRenderCommandName"
		value="/user/signup"
	/>
</portlet:renderURL>

<portlet:renderURL var="signInUrl">
	<portlet:param
		name="mvcRenderCommandName"
		value="/user/view/signIn"
	/>
</portlet:renderURL>

<liferay-ui:success key="keyUserAddedSuccessfully" message="user-added-successfully" />
<liferay-ui:success key="keyAlreadySignedIn" message="already-signed-in" />
<liferay-ui:error key="keyUserSaveFailure" message="user-save-failure" />

<h1>AMF Sign In</h1>
<aui:button href="${signUpUrl}" value="Sign Up" />
<aui:button href="${signInUrl}" value="Sign In" />