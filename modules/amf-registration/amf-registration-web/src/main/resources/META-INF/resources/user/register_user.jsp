<%@ include file="/init.jsp"%>
  
<portlet:actionURL var="registerUserUrl"
				   name="/user/register">
	<portlet:param name="redirect" value="${param.redirect}" />
</portlet:actionURL>

<liferay-ui:success key="key-already-signed-in" message="already-signed-in" />
<liferay-ui:error key="key-first-name-blank" message="first-name-blank" />
<liferay-ui:error key="key-first-name-length-exceeded" message="first-name-length-exceeded" />
<liferay-ui:error key="key-first-name-not-alphanumeric" message="first-name-not-alphanumeric" />
<liferay-ui:error key="key-last-name-blank" message="last-name-blank" />
<liferay-ui:error key="key-last-name-length-exceeded" message="last-name-length-exceeded" />
<liferay-ui:error key="key-last-name-not-alphanumeric" message="last-name-not-alphanumeric" />
<liferay-ui:error key="key-email-blank" message="email-blank" />
<liferay-ui:error key="key-email-invalid" message="email-invalid" />
<liferay-ui:error key="key-email-length-exceeded" message="email-length-exceeded" />
<liferay-ui:error key="key-username-blank" message="username-blank" />
<liferay-ui:error key="key-username-length-invalid" message="username-length-invalid" />
<liferay-ui:error key="key-username-not-alphanumeric" message="username-not-alphanumeric" />
<liferay-ui:error key="key-min-age-exceeded" message="min-age-exceeded" />
<liferay-ui:error key="key-password-blank" message="password-blank" />
<liferay-ui:error key="key-password-invalid" message="password-invalid" />
<liferay-ui:error key="key-passwords-not-matching" message="passwords-not-matching" />
<liferay-ui:error key="key-home-phone-invalid" message="home-phone-invalid" />
<liferay-ui:error key="key-mobile-phone-invalid" message="mobile-phone-invalid" />
<liferay-ui:error key="key-address1-blank" message="address1-blank" />
<liferay-ui:error key="key-address1-length-exceeded" message="address1-length-exceeded" />
<liferay-ui:error key="key-address1-not-alphanumeric" message="address1-not-alphanumeric" />
<liferay-ui:error key="key-address2-length-exceeded" message="address2-length-exceeded" />
<liferay-ui:error key="key-address2-not-alphanumeric" message="address2-not-alphanumeric" />
<liferay-ui:error key="key-city-blank" message="city-blank" />
<liferay-ui:error key="key-city-not-alphanumeric" message="city-not-alphanumeric" />
<liferay-ui:error key="key-state-blank" message="state-blank" />
<liferay-ui:error key="key-zip-blank" message="zip-blank" />
<liferay-ui:error key="key-zip-length-invalid" message="zip-length-invalid" />
<liferay-ui:error key="key-security-question-blank" message="security-question-blank" />
<liferay-ui:error key="key-security-answer-blank" message="security-answer-blank" />
<liferay-ui:error key="key-terms-of-use-unaccepted" message="terms-of-use-unaccepted" />

<div class="container-fluid-1280">

	<h1>Registration Form</h1>
	
	<aui:form name="fm" action="${registerUserUrl}"  >
		
		<aui:input name="userId" field="userId" type="hidden" />	 
		<aui:fieldset-group markupView="lexicon">
			<aui:fieldset>
				<aui:input name="firstName">
					<aui:validator name="required" />
				</aui:input>
				
				<aui:input name="lastName">
					<aui:validator name="required" />
				</aui:input>
				
				<aui:input name="emailAddress">
					<aui:validator name="required" />
				</aui:input>
				
				<aui:input name="userName">
					<aui:validator name="required" />
				</aui:input>
				
				<aui:input name="male" type="checkbox"/>
			
				<aui:input name="birthday" type="Date">
					<aui:validator name="required" />
				</aui:input>
				
				<aui:input name="password">
					<aui:validator name="required" />
				</aui:input>
				
				<aui:input name="confirmPassword">
					<aui:validator name="required" />
				</aui:input>
				
			</aui:fieldset>
		</aui:fieldset-group> 
		
		<aui:fieldset-group markupView="lexicon">
			<aui:fieldset>
				<aui:input name="homePhone"/>
				<aui:input name="mobilePhone"/>
			</aui:fieldset>
		</aui:fieldset-group> 
		
		<aui:fieldset-group markupView="lexicon">
			<aui:fieldset>
				<aui:input name="address1">
					<aui:validator name="required" />
				</aui:input>
				<aui:input name="address2"/>
				<aui:input name="city">
					<aui:validator name="required" />
				</aui:input>
				<aui:input name="state">
					<aui:validator name="required" />
				</aui:input>
				<aui:input name="zip">
					<aui:validator name="required" />
				</aui:input>
			</aui:fieldset>
		</aui:fieldset-group> 
		
		<aui:fieldset-group markupView="lexicon">
			<aui:fieldset>
				<aui:select name="securityQuestion" label="Security Question">
					<aui:option value="What is your mother's maiden name?">What is your mother's maiden name?</aui:option>
        			<aui:option value="What is the make of your first car?">What is the make of your first car?</aui:option>
        			<aui:option value="What is your high school mascot?">What is your high school mascot?</aui:option>
        			<aui:option value="Who is your favorite actor?">Who is your favorite actor?</aui:option>
				</aui:select>
			
				<aui:input name="answer">
					<aui:validator name="required" />
				</aui:input>
				<aui:input name="termsOfUse" type="checkbox" label="I have read, understand, and agree with the Terms of Use governing my access to and use of the Acme Movie Fanatics web site.">
					<aui:validator name="required" />
				</aui:input>
			</aui:fieldset>
		</aui:fieldset-group> 
		
		<aui:button cssClass="btn-lg" value="Sign Up" type="Submit"/>
    </aui:form>
</div>

