package com.FarmersCart.Bean;

public class MessageBean {
	private String mUserName;
	private String mUserId;
	private String mMessage;
	private String mTime;
	private String mMessageOpenFlag;
	
	public MessageBean(String id, String name, String message, String time, String open_flag){
		super();
		this.mUserName=name;	
		this.mUserId=id;
		this.mMessage=message;
		this.mTime=time;
		this.mMessageOpenFlag=open_flag;
	}

	public String getmMessage() {
		return mMessage;
	}

	public void setmMessage(String mMessage) {
		this.mMessage = mMessage;
	}

	public String getmTime() {
		return mTime;
	}

	public void setmTime(String mTime) {
		this.mTime = mTime;
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

	public String getmMessageOpenFlag() {
		return mMessageOpenFlag;
	}

	public void setmMessageOpenFlag(String mMessageOpenFlag) {
		this.mMessageOpenFlag = mMessageOpenFlag;
	}

	
	
	
}
