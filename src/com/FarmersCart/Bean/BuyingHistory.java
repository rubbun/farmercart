package com.FarmersCart.Bean;

public class BuyingHistory {
	private String mBuyingTransactionId;
	private String mBuyingCategoryName; 
	private String mBuyingSubCategoryId; 
	private String mBuyingSubCategoryName; 
	private String mBuyingSubCategoryImage;
	private String mBuyingBrandId;
	private String mBuyingBrandName;
	private String mBuyingSubCategoryPrice;
	private String mBuyingTotalPrice;
	private String mBuyingProductQuantity;
	private String mBuyingProductDeliveryType;
	
	public BuyingHistory(String buyingTransactionId,String buyingCategoryName, String buyingSubcategoryId,String buyingSubcategoryName, String buyingSubcategoryImage,String buyingBrandId,String buyingBrandName
			,String buyingSubcategoryPrice,String quantity,String buyingTotalPrice,String delivery_type){
		super();
		this.mBuyingTransactionId=buyingTransactionId;	
		this.mBuyingSubCategoryId=buyingSubcategoryId;
		this.mBuyingSubCategoryName=buyingSubcategoryName;
		this.mBuyingSubCategoryImage=buyingSubcategoryImage;
		this.mBuyingBrandId=buyingBrandId;
		this.mBuyingBrandName=buyingBrandName;
		this.mBuyingSubCategoryPrice=buyingSubcategoryPrice;
		this.mBuyingProductQuantity=quantity;
		this.mBuyingTotalPrice=buyingTotalPrice;
		this.mBuyingCategoryName=buyingCategoryName;
		this.mBuyingProductDeliveryType=delivery_type;
	}

	public String getmBuyingCategoryName() {
		return mBuyingCategoryName;
	}

	public void setmBuyingCategoryName(String mBuyingCategoryName) {
		this.mBuyingCategoryName = mBuyingCategoryName;
	}

	public String getmBuyingProductDeliveryType() {
		return mBuyingProductDeliveryType;
	}

	public void setmBuyingProductDeliveryType(String mBuyingProductDeliveryType) {
		this.mBuyingProductDeliveryType = mBuyingProductDeliveryType;
	}

	public String getmBuyingProductQuantity() {
		return mBuyingProductQuantity;
	}

	public void setmBuyingProductQuantity(String mBuyingProductQuantity) {
		this.mBuyingProductQuantity = mBuyingProductQuantity;
	}

	public String getmBuyingTransactionId() {
		return mBuyingTransactionId;
	}

	public void setmBuyingTransactionId(String mBuyingTransactionId) {
		this.mBuyingTransactionId = mBuyingTransactionId;
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

	public String getmBuyingBrandId() {
		return mBuyingBrandId;
	}

	public void setmBuyingBrandId(String mBuyingBrandId) {
		this.mBuyingBrandId = mBuyingBrandId;
	}

	public String getmBuyingBrandName() {
		return mBuyingBrandName;
	}

	public void setmBuyingBrandName(String mBuyingBrandName) {
		this.mBuyingBrandName = mBuyingBrandName;
	}

	public String getmBuyingSubCategoryPrice() {
		return mBuyingSubCategoryPrice;
	}

	public void setmBuyingSubCategoryPrice(String mBuyingSubCategoryPrice) {
		this.mBuyingSubCategoryPrice = mBuyingSubCategoryPrice;
	}

	public String getmBuyingTotalPrice() {
		return mBuyingTotalPrice;
	}

	public void setmBuyingTotalPrice(String mBuyingTotalPrice) {
		this.mBuyingTotalPrice = mBuyingTotalPrice;
	}
	
	


}
