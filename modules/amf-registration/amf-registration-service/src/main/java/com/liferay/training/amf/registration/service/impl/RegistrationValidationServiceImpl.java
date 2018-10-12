package com.liferay.training.amf.registration.service.impl;

import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.training.amf.registration.model.Registration;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;

import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegistrationValidationServiceImpl {

	public static boolean doesPasswordMeetCriteria(String password) {
		Matcher matcher = _passwordPattern.matcher(password);

		return matcher.matches();
	}

	//TODO: add unit tests
	public static boolean isRegistrationValid(Registration registration, long companyId, List<String> errors) {

		//TODO: the validation into smaller methods
		//Note: even though the UI already checks for blank fields via the aui validator,
		//		it's still good for this service layer method to check it since service layer shouldn't depend on UI.
		String firstName = registration.get_firstName();

		if (Validator.isBlank(firstName)) {
			errors.add("keyFirstNameBlank");

			return false;
		}

		if (!isLengthValid(firstName, MIN_LENGTH_REQUIRED, MAX_FIRST_NAME_LENGTH)) {
			errors.add("keyFirstNameLengthExceeded");

			return false;
		}

		if (!Validator.isAlphanumericName(firstName)) {
			errors.add("keyFirstNameNotAlphanumeric");

			return false;
		}

		String lastName = registration.get_lastName();

		if (Validator.isBlank(lastName)) {
			errors.add("keyLastNameBlank");

			return false;
		}

		if (!isLengthValid(lastName, MIN_LENGTH_REQUIRED, MAX_LAST_NAME_LENGTH)) {
			errors.add("keyLastNameLengthExceeded");

			return false;
		}

		if (!Validator.isAlphanumericName(lastName)) {
			errors.add("keyLastNameNotAlphanumeric");

			return false;
		}

		String emailAddress = registration.get_emailAddress();

		if (Validator.isBlank(emailAddress)) {
			errors.add("keyEmailBlank");

			return false;
		}

		if (!Validator.isEmailAddress(emailAddress)) {
			errors.add("keyEmailInvalid");

			return false;
		}

		if (!isLengthValid(emailAddress, MIN_LENGTH_REQUIRED, MAX_EMAIL_ADDRESS_LENGTH)) {
			errors.add("keyEmailLengthExceeded");

			return false;
		}

		User userWithSameEmailAddress = UserLocalServiceUtil.fetchUserByEmailAddress(companyId, emailAddress);

		if (userWithSameEmailAddress != null) {
			errors.add("keyEmailAlreadyExists");

			return false;
		}

		String username = registration.get_userName();

		if (Validator.isBlank(username)) {
			errors.add("keyUsernameBlank");

			return false;
		}

		if (!isLengthValid(username, MIN_USERNAME_LENGTH, MAX_USERNAME_LENGTH)) {
			errors.add("keyUsernameLengthInvalid");

			return false;
		}

		if (!Validator.isAlphanumericName(username)) {
			errors.add("keyUsernameNotAlphanumeric");

			return false;
		}

		User userWithSameUsername = UserLocalServiceUtil.fetchUserByScreenName(companyId, username);

		if (userWithSameUsername != null) {
			errors.add("keyUsernameAlreadyExists");

			return false;
		}

		Date birthday = registration.get_birthday();

		LocalDate birthdayLocalDate = birthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

		int age = Period.between(birthdayLocalDate, LocalDate.now()).getYears();

		if (age < MIN_AGE) {
			errors.add("keyMinAgeExceeded");

			return false;
		}

		String password = registration.get_password();

		if (Validator.isBlank(password)) {
			errors.add("keyPasswordBlank");

			return false;
		}

		if (!doesPasswordMeetCriteria(password)) {
			errors.add("keyPasswordInvalid");

			return false;
		}

		String confirmPassword = registration.get_confirmPassword();

		if (!password.equals(confirmPassword)) {
			errors.add("keyPasswordsNotMatching");

			return false;
		}

		String homePhoneNumber = registration.get_homePhoneNumber();

		if (!isPhoneNumberValid(homePhoneNumber)) {
			errors.add("keyHomePhoneInvalid");

			return false;
		}

		String mobilePhoneNumber = registration.get_mobilePhoneNumber();

		if (!isPhoneNumberValid(mobilePhoneNumber)) {
			errors.add("keyMobilePhoneInvalid");

			return false;
		}

		String address1 = registration.get_address1();

		if (Validator.isBlank(address1)) {
			errors.add("keyAddress1Blank");

			return false;
		}

		if (!isLengthValid(address1, MIN_LENGTH_REQUIRED, MAX_ADDRESS1_LENGTH)) {
			errors.add("keyAddress1LengthExceeded");

			return false;
		}

		if (!Validator.isAlphanumericName(address1)) {
			errors.add("keyAddress1NotAlphanumeric");

			return false;
		}

		String address2 = registration.get_address2();

		if (!isLengthValid(address2, MIN_LENGTH_REQUIRED, MAX_ADDRESS2_LENGTH)) {
			errors.add("keyAddress2LengthExceeded");

			return false;
		}

		if (!Validator.isAlphanumericName(address2)) {
			errors.add("keyAddress2NotAlphanumeric");

			return false;
		}

		String city = registration.get_city();

		if (Validator.isBlank(city)) {
			errors.add("keyCityBlank");

			return false;
		}

		if (!Validator.isAlphanumericName(city)) {
			errors.add("keyCityNotAlphanumeric");

			return false;
		}

		String state = registration.get_state();

		if (Validator.isBlank(state)) {
			errors.add("keyStateBlank");

			return false;
		}

		String zipCode = registration.get_zipCode();

		if (Validator.isBlank(zipCode)) {
			errors.add("keyZipBlank");

			return false;
		}

		if (zipCode.length() != ZIP_LENGTH) {
			errors.add("keyZipLengthInvalid");

			return false;
		}

		String securityQuestion = registration.get_securityQuestion();

		if (Validator.isBlank(securityQuestion)) {
			errors.add("keySecurityQuestionBlank");

			return false;
		}

		String securityQuestionAnswer = registration.get_securityQuestionAnswer();

		if (Validator.isBlank(securityQuestionAnswer)) {
			errors.add("keySecurityAnswerBlank");

			return false;
		}

		boolean termsOfUseAccepted = registration.get_acceptedTermsOfUse();

		if (!termsOfUseAccepted) {
			errors.add("keyTermsOfUseUnaccepted");

			return false;
		}

		return true;
	}

	private static boolean isLengthValid(String text, int minLength, int maxLength) {
		int length = text.length();

		if (length >= minLength && length <= maxLength) {
			return true;
		}

		return false;
	}

	private static boolean isPhoneNumberValid(String phoneNumber) {
		if (Validator.isBlank(phoneNumber)) return false;

		String phoneNumberDigits = StringUtil.extractDigits(phoneNumber);

		if (phoneNumberDigits.length() == PHONE_NUMBER_LENGTH && Validator.isPhoneNumber(phoneNumber)) {
			return true;
		}

		return false;
	}

	private static final int MAX_ADDRESS1_LENGTH = 255;

	private static final int MAX_ADDRESS2_LENGTH = 255;

	private static final int MAX_CITY_LENGTH = 255;

	private static final int MAX_EMAIL_ADDRESS_LENGTH = 255;

	private static final int MAX_FIRST_NAME_LENGTH = 50;

	private static final int MAX_LAST_NAME_LENGTH = 50;

	private static final int MAX_USERNAME_LENGTH = 16;

	private static final int MIN_AGE = 13;

	//TODO: put these in config instead of hard-coded
	private static final int MIN_LENGTH_REQUIRED = 1;

	private static final int MIN_USERNAME_LENGTH = 4;

	private static final String PASSWORD_REGEX = "";

	private static final int PHONE_NUMBER_LENGTH = 10;

	private static final int ZIP_LENGTH = 5;

	private static final Pattern _passwordPattern = Pattern.compile("(?=.*[A-Z])(?=.*\\d)(?=.*[#$^+=!*()@%&]).{6,}");

}