package com.FarmersCart.Bean;

public class BuyingSubCategoryBean {
	private String mBuyingSubCategoryId;
	private String mBuyingSubCategoryName;
	private String mBuyingSubCategoryImage;
	private String mBuyingSubCategoryPrice;
	private String mBuyingSubCategoryDescription;
	
	public BuyingSubCategoryBean(String buyingCategoryId, String buyingCategoryName,String buyingCategoryImage,String buyingCategoryPrice
			,String buyingCategoryDescription){
		super();
		this.mBuyingSubCategoryId=buyingCategoryId;	
		this.mBuyingSubCategoryName=buyingCategoryName;
		this.mBuyingSubCategoryImage=buyingCategoryImage;
		this.mBuyingSubCategoryPrice=buyingCategoryPrice;
		this.mBuyingSubCategoryDescription=buyingCategoryDescription;
	}

	public String getmBuyingSubCategoryDescription() {
		return mBuyingSubCategoryDescription;
	}

	public void setmBuyingSubCategoryDescription(
			String mBuyingSubCategoryDescription) {
		this.mBuyingSubCategoryDescription = mBuyingSubCategoryDescription;
	}

	public String getmBuyingSubCategoryId() {
		return mBuyingSubCategoryId;
	}

	public void setmBuyingSubCategoryId(String mBuyingSubCategoryId) {
		this.mBuyingSubCategoryId = mBuyingSubCategoryId;
	}

	public String getmBuyingSubCategoryName() {
		return mBuyingSubCategoryName;
	}

	public void setmBuyingSubCategoryName(String mBuyingSubCategoryName) {
		this.mBuyingSubCategoryName = mBuyingSubCategoryName;
	}

	public String getmBuyingSubCategoryImage() {
		return mBuyingSubCategoryImage;
	}

	public void setmBuyingSubCategoryImage(String mBuyingSubCategoryImage) {
		this.mBuyingSubCategoryImage = mBuyingSubCategoryImage;
	}

	public String getmBuyingSubCategoryPrice() {
		return mBuyingSubCategoryPrice;
	}

	public void setmBuyingSubCategoryPrice(String mBuyingSubCategoryPrice) {
		this.mBuyingSubCategoryPrice = mBuyingSubCategoryPrice;
	}

	

	
	
}
