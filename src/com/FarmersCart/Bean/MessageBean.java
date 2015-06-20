package com.FarmersCart.Bean;

public class MessageBean {
	private String mUserName;
	private String mUserId;
	private String mMessage;
	private String phone;
	private String address;
	private String mTime;
	private String mMessageOpenFlag;

	public MessageBean(String mUserName, String mUserId, String mMessage, String phone, String address, String mTime, String mMessageOpenFlag) {
		super();
		this.mUserName = mUserName;
		this.mUserId = mUserId;
		this.mMessage = mMessage;
		this.phone = phone;
		this.address = address;
		this.mTime = mTime;
		this.mMessageOpenFlag = mMessageOpenFlag;
	}

	public String getmUserName() {
		return mUserName;
	}

	public void setmUserName(String mUserName) {
		this.mUserName = mUserName;
	}

	public String getmUserId() {
		return mUserId;
	}

	public void setmUserId(String mUserId) {
		this.mUserId = mUserId;
	}

	public String getmMessage() {
		return mMessage;
	}

	public void setmMessage(String mMessage) {
		this.mMessage = mMessage;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getmTime() {
		return mTime;
	}

	public void setmTime(String mTime) {
		this.mTime = mTime;
	}

	public String getmMessageOpenFlag() {
		return mMessageOpenFlag;
	}

	public void setmMessageOpenFlag(String mMessageOpenFlag) {
		this.mMessageOpenFlag = mMessageOpenFlag;
	}

}
