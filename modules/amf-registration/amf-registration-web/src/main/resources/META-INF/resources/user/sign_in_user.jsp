<%@ include file="/init.jsp"%>

<div class="container-fluid-1280">

<portlet:actionURL var="signInUserUrl"
				   name="/user/signIn">
	<portlet:param name="redirect" value="${param.redirect}" />
</portlet:actionURL>

<h1>Sign In</h1>

<aui:form name="fm" action="${signInUserUrl}"  >

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
	
	<aui:button cssClass="btn-lg" value="Sign In" type="Submit"/>

</aui:form>