<%@ include file="/init.jsp" %>

<div class="container-fluid-1280">

<portlet:actionURL
	name="/user/signIn"
	var="signInUserUrl"
>
	<portlet:param name="redirect" value="${param.redirect}" />
</portlet:actionURL>

<h1>Sign In</h1>

<aui:form action="${signInUserUrl}" name="fm">

	<aui:fieldset-group markupView="lexicon">
		<aui:fieldset>
			<aui:input name="userName">
				<aui:validator name="required" />
			</aui:input>

			<aui:input name="password">
				<aui:validator name="required" />
			</aui:input>
		</aui:fieldset>
	</aui:fieldset-group>

	<aui:button cssClass="btn-lg" type="Submit" value="Sign In" />
</aui:form>