package amf.user.search.service.validator;

import com.liferay.portal.kernel.util.Validator;

public class ZipCodeValidator {

	public static boolean isZipCodeValid(String zipCode) {
		return !Validator.isBlank(zipCode) 
			   && zipCode.length() == 5 
			   && Validator.isNumber(zipCode);
	}
}
