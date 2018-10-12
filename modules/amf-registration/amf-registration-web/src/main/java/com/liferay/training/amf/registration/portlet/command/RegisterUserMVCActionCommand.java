package com.liferay.training.amf.registration.portlet.command;

import com.liferay.portal.kernel.exception.PortalException;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.model.Country;
import com.liferay.portal.kernel.model.Region;
import com.liferay.portal.kernel.model.User;
import com.liferay.portal.kernel.portlet.bridges.mvc.BaseMVCActionCommand;
import com.liferay.portal.kernel.portlet.bridges.mvc.MVCActionCommand;
import com.liferay.portal.kernel.service.AddressLocalServiceUtil;
import com.liferay.portal.kernel.service.CountryServiceUtil;
import com.liferay.portal.kernel.service.PhoneLocalServiceUtil;
import com.liferay.portal.kernel.service.RegionServiceUtil;
import com.liferay.portal.kernel.service.ServiceContext;
import com.liferay.portal.kernel.service.ServiceContextFactory;
import com.liferay.portal.kernel.service.UserLocalServiceUtil;
import com.liferay.portal.kernel.servlet.SessionErrors;
import com.liferay.portal.kernel.servlet.SessionMessages;
import com.liferay.training.amf.registration.constants.AmfRegistrationPortletKeys;
import com.liferay.training.amf.registration.model.PhoneType;
import com.liferay.training.amf.registration.model.Registration;
import com.liferay.training.amf.registration.portlet.AmfRegistrationUtil;
import com.liferay.training.amf.registration.service.impl.RegistrationValidationServiceImpl;

import java.time.LocalDate;
import java.time.ZoneId;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;
import javax.portlet.PortletSession;

import org.osgi.service.component.annotations.Component;

@Component(
		immediate = true,
		property = {
			"javax.portlet.name=" + AmfRegistrationPortletKeys.AmfRegistration, "mvc.command.name=/user/register"
	},
	service = MVCActionCommand.class
)
public class RegisterUserMVCActionCommand extends BaseMVCActionCommand {

	@Override
	protected void doProcessAction(ActionRequest actionRequest, ActionResponse actionResponse) throws Exception {
		ServiceContext serviceContext = ServiceContextFactory.getInstance(actionRequest);
		hideDefaultErrorMessage(actionRequest);

		//TODO: move logic to service layer

		Registration registration = AmfRegistrationUtil.createRegistration(actionRequest);

		List<String> errors = new ArrayList<>();

		RegistrationValidationServiceImpl validationService = new RegistrationValidationServiceImpl();

		if (validationService.isRegistrationValid(registration, errors)) {

			//TODO: break out into separate method
			long creatorUserId = 0;
			long companyId = 20115; //TODO: look up via service
			boolean autoPassword = false;
			boolean autoScreenName = false;
			String firstName = registration.get_firstName();
			String middleName = "";
			String lastName = registration.get_lastName();
			long prefixId = 0;
			long suffixId = 0;
			String emailAddress = registration.get_emailAddress();
			String userName = registration.get_userName();
			String password = registration.get_password();
			String confirmPassword = registration.get_confirmPassword();
			boolean isMale = registration.get_isMale();
			Locale locale = Locale.US;
			long facebookId = 0;
			String openId = null;
			String jobTitle = null;
			long[] groupIds = null;
			long[] orgIds = null;
			long[] roleIds = null;
			long[] userGroupIds = null;
			boolean sendEmail = false;

			Date birthday = registration.get_birthday();

			LocalDate birthdayLocalDate = birthday.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

			int birthdayMonth = birthdayLocalDate.getMonthValue();
			int birthdayDay = birthdayLocalDate.getDayOfMonth();
			int birthdayYear = birthdayLocalDate.getYear();

			User user = null;

			try {
				user = UserLocalServiceUtil.addUser(creatorUserId, companyId, autoPassword, password, confirmPassword, autoScreenName, userName, emailAddress,
						facebookId, openId, locale, firstName, middleName, lastName, prefixId, suffixId, isMale,
						birthdayMonth, birthdayDay, birthdayYear, jobTitle, groupIds, orgIds, roleIds, userGroupIds,
						sendEmail, serviceContext);

			} catch (Exception e) {

				SessionErrors.add(actionRequest, "keyUserSaveFailure");

				sendRedirect(actionRequest, actionResponse);

				return;
			}

			SessionMessages.add(actionRequest, "userUpdated");

			long userId = user.getUserId();
			String className = null;
			long classPK = 0;

			savePhoneNumber(
				userId, className, classPK, registration.get_homePhoneNumber(), PhoneType.PERSONAL, serviceContext);
			savePhoneNumber(
				userId, className, classPK, registration.get_mobilePhoneNumber(), PhoneType.MOBILE, serviceContext);

			//TODO: move security question to separate method
			String securityQuestion = registration.get_securityQuestion();
			String securityQuestionAnswer = registration.get_securityQuestionAnswer();

			UserLocalServiceUtil.updateReminderQuery(userId, securityQuestion, securityQuestionAnswer);

			boolean agreedToTermsOfUse = registration.get_acceptedTermsOfUse();
			UserLocalServiceUtil.updateAgreedToTermsOfUse(userId, agreedToTermsOfUse);

			//TODO: break out into separate method
			String street1 = registration.get_address1();
			String street2 = registration.get_address2();
			String street3 = null;
			String city = registration.get_city();
			String zip = registration.get_zipCode();
			Country unitedStates = CountryServiceUtil.getCountryByName("united-states");

			long countryId = unitedStates.getCountryId();
			Region caRegion = RegionServiceUtil.getRegion(countryId, "CA");

			long regionId = caRegion.getRegionId();
			long addressTypeId = 0;
			boolean isMailingAddress = false;
			boolean isPrimaryAddress = true;

			try {
				AddressLocalServiceUtil.addAddress(userId, className, classPK, street1, street2, street3, city, zip,
					regionId, countryId, addressTypeId, isMailingAddress, isPrimaryAddress, serviceContext);
			} catch (Exception e) {
				SessionErrors.add(actionRequest, "keyAddressSaveFailure");
			}

			PortletSession portletSession = actionRequest.getPortletSession();

			portletSession.setAttribute("usernameSignedIn", userName, PortletSession.APPLICATION_SCOPE);

			SessionMessages.add(actionRequest, "keyUserAddedSuccessfully");

			hideDefaultSuccessMessage(actionRequest);

			actionResponse.setRenderParameter("mvcRenderCommandName", "/user/view/signIn");

		} else {

			for (String error : errors) {
				SessionErrors.add(actionRequest, error);
			}

			actionResponse.setRenderParameter("mvcRenderCommandName", "/user/signup");
		}
	}

	private void savePhoneNumber(
		long userId, String className, long classPK, String phoneNumber, PhoneType phoneType,
		ServiceContext serviceContext) {

		if (phoneNumber == null || phoneNumber.isEmpty()) return;

		try {
			PhoneLocalServiceUtil.addPhone(
				userId, className, classPK, phoneNumber, null, phoneType.getPhoneTypeId(), true, serviceContext);
		} catch (PortalException e) {
			_log.fatal(String.format("honeLocalServiceUtil.addPhone exception: %s", e.getMessage()));
		}
	}

	private static final Log _log = LogFactoryUtil.getLog(RegisterUserMVCActionCommand.class);

}