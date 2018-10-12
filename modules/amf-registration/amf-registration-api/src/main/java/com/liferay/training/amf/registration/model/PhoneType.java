package com.liferay.training.amf.registration.model;

//TODO: not sure which service loads the types corresponding to these values (from listtype table) so going to add this for now
public enum PhoneType {

	PERSONAL(11011), MOBILE(11008);

	public long getPhoneTypeId() {
		return _phoneTypeId;
	}

	private PhoneType(long phoneTypeId) {
		this._phoneTypeId = _phoneTypeId;
	}

	private long _phoneTypeId;

}