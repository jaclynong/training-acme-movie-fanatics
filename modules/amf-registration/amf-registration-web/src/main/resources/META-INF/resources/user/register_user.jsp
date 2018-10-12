<%@ include file="/init.jsp" %>

<portlet:actionURL
	name="/user/register"
	var="registerUserUrl"
>
	<portlet:param name="redirect" value="${param.redirect}" />
</portlet:actionURL>

<liferay-ui:success key="keyAlreadySignedIn" message="already-signed-in" />
<liferay-ui:error key="keyFirstNameBlank" message="first-name-blank" />
<liferay-ui:error key="keyFirstNameLengthExceeded" message="first-name-length-exceeded" />
<liferay-ui:error key="keyFirstNameNotAlphanumeric" message="first-name-not-alphanumeric" />
<liferay-ui:error key="keyLastNameBlank" message="last-name-blank" />
<liferay-ui:error key="keyLastNameLengthExceeded" message="last-name-length-exceeded" />
<liferay-ui:error key="keyLastNameNotAlphanumeric" message="last-name-not-alphanumeric" />
<liferay-ui:error key="keyEmailBlank" message="email-blank" />
<liferay-ui:error key="keyEmailInvalid" message="email-invalid" />
<liferay-ui:error key="keyEmailLengthExceeded" message="email-length-exceeded" />
<liferay-ui:error key="keyEmailAlreadyExists" message="email-already-exists" />
<liferay-ui:error key="keyUsernameBlank" message="username-blank" />
<liferay-ui:error key="keyUsernameLengthInvalid" message="username-length-invalid" />
<liferay-ui:error key="keyUsernameNotAlphanumeric" message="username-not-alphanumeric" />
<liferay-ui:error key="keyUsernameAlreadyExists" message="username-already-exists" />
<liferay-ui:error key="keyMinAgeExceeded" message="min-age-exceeded" />
<liferay-ui:error key="keyPasswordBlank" message="password-blank" />
<liferay-ui:error key="keyPasswordInvalid" message="password-invalid" />
<liferay-ui:error key="keyPasswordsNotMatching" message="passwords-not-matching" />
<liferay-ui:error key="keyHomePhoneInvalid" message="home-phone-invalid" />
<liferay-ui:error key="keyMobilePhoneInvalid" message="mobile-phone-invalid" />
<liferay-ui:error key="keyAddress1Blank" message="address1-blank" />
<liferay-ui:error key="keyAddress1LengthExceeded" message="address1-length-exceeded" />
<liferay-ui:error key="keyAddress1NotAlphanumeric" message="address1-not-alphanumeric" />
<liferay-ui:error key="keyAddress2LengthExceeded" message="address2-length-exceeded" />
<liferay-ui:error key="keyAddress2NotAlphanumeric" message="address2-not-alphanumeric" />
<liferay-ui:error key="keyCityBlank" message="city-blank" />
<liferay-ui:error key="keyCityNotAlphanumeric" message="city-not-alphanumeric" />
<liferay-ui:error key="keyStateBlank" message="state-blank" />
<liferay-ui:error key="keyZipBlank" message="zip-blank" />
<liferay-ui:error key="keyZipLengthInvalid" message="zip-length-invalid" />
<liferay-ui:error key="keySecurityQuestionBlank" message="security-question-blank" />
<liferay-ui:error key="keySecurityAnswerBlank" message="security-answer-blank" />
<liferay-ui:error key="keyTermsOfUseUnaccepted" message="terms-of-use-unaccepted" />

<div class="container-fluid-1280">
	<h1>Registration Form</h1>

	<aui:form action="${registerUserUrl}" name="fm">

		<aui:input field="userId" name="userId" type="hidden" />

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

				<aui:input name="male" type="checkbox" />

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
				<aui:input name="homePhone" />
				<aui:input name="mobilePhone" />
			</aui:fieldset>
		</aui:fieldset-group>

		<aui:fieldset-group markupView="lexicon">
			<aui:fieldset>
				<aui:input name="address1">
					<aui:validator name="required" />
				</aui:input>

				<aui:input name="address2" />

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
				<aui:select label="Security Question" name="securityQuestion">
					<aui:option value="What is your mother's maiden name?">What is your mother's maiden name?</aui:option>
					<aui:option value="What is the make of your first car?">What is the make of your first car?</aui:option>
					<aui:option value="What is your high school mascot?">What is your high school mascot?</aui:option>
					<aui:option value="Who is your favorite actor?">Who is your favorite actor?</aui:option>
				</aui:select>

				<aui:input name="answer">
					<aui:validator name="required" />
				</aui:input>

				<aui:input label="I have read, understand, and agree with the Terms of Use governing my access to and use of the Acme Movie Fanatics web site." name="termsOfUse" type="checkbox">
					<aui:validator name="required" />
				</aui:input>
			</aui:fieldset>
		</aui:fieldset-group>

		<aui:button cssClass="btn-lg" type="Submit" value="Sign Up" />
	</aui:form>
</div>