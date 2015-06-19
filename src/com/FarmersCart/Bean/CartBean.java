package com.FarmersCart.Bean;

public class CartBean {
	private String mUserName;
	private String mUserId;
	private String mProductId;
	private String mProductName;
	private String mProductImage;
	private String mCost;
	private String mTotalCost;
	private String mQuantity;
	private String mDeliveryType;
	
	public CartBean(String user_id, String user_name, String product_id, String product_name, String product_image, String cost,
			String total_cost,String quantity,String delivery_type){
		super();
		this.mUserId=user_id;	
		this.mUserName=user_name;
		this.mProductId=product_id;
		this.mProductName=product_name;
		this.mProductImage=product_image;
		this.mCost=cost;
		this.mTotalCost=total_cost;
		this.mQuantity=quantity;
		this.mDeliveryType=delivery_type;
	}

	public String getmUserName() {
		return mUserName;
	}

	public String getmDeliveryType() {
		return mDeliveryType;
	}

	public void setmDeliveryType(String mDeliveryType) {
		this.mDeliveryType = mDeliveryType;
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

	public String getmProductId() {
		return mProductId;
	}

	public void setmProductId(String mProductId) {
		this.mProductId = mProductId;
	}

	public String getmProductName() {
		return mProductName;
	}

	public void setmProductName(String mProductName) {
		this.mProductName = mProductName;
	}

	public String getmProductImage() {
		return mProductImage;
	}

	public void setmProductImage(String mProductImage) {
		this.mProductImage = mProductImage;
	}

	public String getmCost() {
		return mCost;
	}

	public void setmCost(String mCost) {
		this.mCost = mCost;
	}

	public String getmTotalCost() {
		return mTotalCost;
	}

	public void setmTotalCost(String mTotalCost) {
		this.mTotalCost = mTotalCost;
	}

	public String getmQuantity() {
		return mQuantity;
	}

	public void setmQuantity(String mQuantity) {
		this.mQuantity = mQuantity;
	}


	
}
