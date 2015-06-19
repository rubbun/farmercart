package com.FarmersCart.Bean;

public class BuyingCategoryBean {
	private String mBuyingCategoryId;
	private String mBuyingCategoryName;
	private String mBuyingCategoryImage;
	
	public BuyingCategoryBean(String buyingCategoryId, String buyingCategoryName,String buyingCategoryImage){
		super();
		this.mBuyingCategoryId=buyingCategoryId;	
		this.mBuyingCategoryName=buyingCategoryName;
		this.mBuyingCategoryImage=buyingCategoryImage;
	}

	public String getmBuyingCategoryId() {
		return mBuyingCategoryId;
	}

	public void setmBuyingCategoryId(String mBuyingCategoryId) {
		this.mBuyingCategoryId = mBuyingCategoryId;
	}

	public String getmBuyingCategoryName() {
		return mBuyingCategoryName;
	}

	public void setmBuyingCategoryName(String mBuyingCategoryName) {
		this.mBuyingCategoryName = mBuyingCategoryName;
	}

	public String getmBuyingCategoryImage() {
		return mBuyingCategoryImage;
	}

	public void setmBuyingCategoryImage(String mBuyingCategoryImage) {
		this.mBuyingCategoryImage = mBuyingCategoryImage;
	}


	
	
}
