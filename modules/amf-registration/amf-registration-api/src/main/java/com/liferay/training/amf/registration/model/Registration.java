package com.liferay.training.amf.registration.model;

import java.util.Date;

public class Registration {
	private String _firstName;

	public String get_firstName() {
		return _firstName;
	}

	public void set_firstName(String _firstName) {
		this._firstName = _firstName;
	}
	
	private String _lastName;

	public String get_lastName() {
		return _lastName;
	}

	public void set_lastName(String _lastName) {
		this._lastName = _lastName;
	}
	
	private String _emailAddress;

	public String get_emailAddress() {
		return _emailAddress;
	}

	public void set_emailAddress(String _emailAddress) {
		this._emailAddress = _emailAddress;
	}
	
	private String _userName;

	public String get_userName() {
		return _userName;
	}

	public void set_userName(String _userName) {
		this._userName = _userName;
	}
	
	private String _password;

	public String get_password() {
		return _password;
	}

	public void set_password(String _password) {
		this._password = _password;
	}
	
	private String _confirmPassword;

	public String get_confirmPassword() {
		return _confirmPassword;
	}

	public void set_confirmPassword(String _confirmPassword) {
		this._confirmPassword = _confirmPassword;
	}
	
	private boolean _isMale;

	public boolean get_isMale() {
		return _isMale;
	}

	public void set_isMale(boolean _isMale) {
		this._isMale = _isMale;
	}
	
	private Date _birthday;

	public Date get_birthday() {
		return _birthday;
	}

	public void set_birthday(Date _birthday) {
		this._birthday = _birthday;
	}
	
	private String _homePhoneNumber;

	public String get_homePhoneNumber() {
		return _homePhoneNumber;
	}

	public void set_homePhoneNumber(String _homePhoneNumber) {
		this._homePhoneNumber = _homePhoneNumber;
	}
	
	private String _mobilePhoneNumber;

	public String get_mobilePhoneNumber() {
		return _mobilePhoneNumber;
	}

	public void set_mobilePhoneNumber(String _mobilePhoneNumber) {
		this._mobilePhoneNumber = _mobilePhoneNumber;
	}
	
	private String _address1;

	public String get_address1() {
		return _address1;
	}

	public void set_address1(String _address1) {
		this._address1 = _address1;
	}
	
	private String _address2;

	public String get_address2() {
		return _address2;
	}

	public void set_address2(String _address2) {
		this._address2 = _address2;
	}
	
	private String _city;

	public String get_city() {
		return _city;
	}

	public void set_city(String _city) {
		this._city = _city;
	}
	
	private String _state;

	public String get_state() {
		return _state;
	}

	public void set_state(String _state) {
		this._state = _state;
	}
	
	private String _zipCode;

	public String get_zipCode() {
		return _zipCode;
	}

	public void set_zipCode(String _zipCode) {
		this._zipCode = _zipCode;
	}
	
	public String _securityQuestion;

	public String get_securityQuestion() {
		return _securityQuestion;
	}

	public void set_securityQuestion(String _securityQuestion) {
		this._securityQuestion = _securityQuestion;
	}
	
	public String _securityQuestionAnswer;

	public String get_securityQuestionAnswer() {
		return _securityQuestionAnswer;
	}

	public void set_securityQuestionAnswer(String _securityQuestionAnswer) {
		this._securityQuestionAnswer = _securityQuestionAnswer;
	}
	
	public boolean _acceptedTermsOfUse;

	public boolean get_acceptedTermsOfUse() {
		return _acceptedTermsOfUse;
	}

	public void set_acceptedTermsOfUse(boolean _acceptedTermsOfUse) {
		this._acceptedTermsOfUse = _acceptedTermsOfUse;
	}
}
