package com.liferay.training.amf.registration.portlet;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.training.amf.registration.model.Registration;

import java.text.SimpleDateFormat;

import javax.portlet.ActionRequest;

public class AmfRegistrationUtil {

	public static Registration createRegistration(ActionRequest request) throws PortalException
	{

		Registration registration = new Registration();

		registration.set_firstName(ParamUtil.getString(request, "firstName"));
		registration.set_lastName(ParamUtil.getString(request, "lastName"));
		registration.set_emailAddress(ParamUtil.getString(request, "emailAddress"));
		registration.set_userName(ParamUtil.getString(request, "userName"));
		registration.set_password(ParamUtil.getString(request, "password"));
		registration.set_confirmPassword(ParamUtil.getString(request, "confirmPassword"));
		registration.set_isMale(ParamUtil.getBoolean(request, "male"));
		registration.set_birthday(ParamUtil.getDate(request, "birthday", new SimpleDateFormat("yyyy-MM-dd")));
		registration.set_homePhoneNumber(ParamUtil.getString(request, "homePhone"));
		registration.set_mobilePhoneNumber(ParamUtil.getString(request, "mobilePhone"));
		registration.set_address1(ParamUtil.getString(request, "address1"));
		registration.set_address2(ParamUtil.getString(request, "address2"));
		registration.set_city(ParamUtil.getString(request, "city"));
		registration.set_state(ParamUtil.getString(request, "state"));
		registration.set_zipCode(ParamUtil.getString(request, "zip"));
		String securityQuestion = ParamUtil.getString(request, "securityQuestion");

		registration.set_securityQuestion(securityQuestion);
		registration.set_securityQuestionAnswer(ParamUtil.getString(request, "answer"));
		registration.set_acceptedTermsOfUse(ParamUtil.getBoolean(request, "termsOfUse"));

		return registration;
	}

}