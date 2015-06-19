package com.FarmersCart.Bean;

public class SellingHistory {
	private String mSellingCategoryName;
	private String mSellingSubCategoryName;
	private String mSellingSubCategoryId;
	private String mSellingSubCategoryImage;
	private String mSellingSubCategoryPrice;
	private String mQuantity;
	private String mTotalCost;
	private String mDeliveryType;
	
	public SellingHistory(String sellingCategoryName, String sellingSubCategoryName,String sellingCategoryId,String sellingSubCategoryImage,String sellingSubCategoryPrice,String quantity
			,String totalCost,String deliveryType){
		super();
		this.mSellingCategoryName=sellingCategoryName;	
		this.mSellingSubCategoryName=sellingSubCategoryName;
		this.mSellingSubCategoryId=sellingCategoryId;
		this.mSellingSubCategoryImage=sellingSubCategoryImage;
		this.mSellingSubCategoryPrice=sellingSubCategoryPrice;
		this.mQuantity=quantity;
		this.mTotalCost=totalCost;
		this.mDeliveryType=deliveryType;
	}

	public String getmSellingCategoryName() {
		return mSellingCategoryName;
	}

	public void setmSellingCategoryName(String mSellingCategoryName) {
		this.mSellingCategoryName = mSellingCategoryName;
	}

	public String getmSellingSubCategoryName() {
		return mSellingSubCategoryName;
	}

	public void setmSellingSubCategoryName(String mSellingSubCategoryName) {
		this.mSellingSubCategoryName = mSellingSubCategoryName;
	}

	public String getmSellingSubCategoryId() {
		return mSellingSubCategoryId;
	}

	public void setmSellingSubCategoryId(String mSellingSubCategoryId) {
		this.mSellingSubCategoryId = mSellingSubCategoryId;
	}

	public String getmSellingSubCategoryImage() {
		return mSellingSubCategoryImage;
	}

	public void setmSellingSubCategoryImage(String mSellingSubCategoryImage) {
		this.mSellingSubCategoryImage = mSellingSubCategoryImage;
	}

	public String getmSellingSubCategoryPrice() {
		return mSellingSubCategoryPrice;
	}

	public void setmSellingSubCategoryPrice(String mSellingSubCategoryPrice) {
		this.mSellingSubCategoryPrice = mSellingSubCategoryPrice;
	}

	public String getmQuantity() {
		return mQuantity;
	}

	public void setmQuantity(String mQuantity) {
		this.mQuantity = mQuantity;
	}

	public String getmTotalCost() {
		return mTotalCost;
	}

	public void setmTotalCost(String mTotalCost) {
		this.mTotalCost = mTotalCost;
	}

	public String getmDeliveryType() {
		return mDeliveryType;
	}

	public void setmDeliveryType(String mDeliveryType) {
		this.mDeliveryType = mDeliveryType;
	}
	
	

}
