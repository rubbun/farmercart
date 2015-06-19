package com.FarmersCart.Bean;

public class BrandBean {
	private String mUserName;
	private String mUserId;
	
	public BrandBean(String id, String name){
		super();
		this.mUserName=name;	
		this.mUserId=id;
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

	
	
	
}
