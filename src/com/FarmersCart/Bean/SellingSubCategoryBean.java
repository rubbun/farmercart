package com.FarmersCart.Bean;

public class SellingSubCategoryBean {
	private String mSellingSubCategoryId;
	private String mSellingSubCategoryName;
	private String mSellingSubCategoryImage;
	private String mSellingSubCategoryDescription;
	private String mSellingSubCategoryPrice;
	
	public SellingSubCategoryBean(String sellingCategoryId, String sellingSubCategoryName ,String sellingCategoryImage,String sellingCategoryDescription,String sellingCategoryPrice){
		super();
		
		this.mSellingSubCategoryId=sellingCategoryId;
		this.mSellingSubCategoryName=sellingSubCategoryName;
		this.mSellingSubCategoryImage=sellingCategoryImage;
		this.mSellingSubCategoryDescription=sellingCategoryDescription;
		this.mSellingSubCategoryPrice=sellingCategoryPrice;
	}

	public String getmSellingSubCategoryId() {
		return mSellingSubCategoryId;
	}

	public String getmSellingSubCategoryDescription() {
		return mSellingSubCategoryDescription;
	}

	public void setmSellingSubCategoryDescription(
			String mSellingSubCategoryDescription) {
		this.mSellingSubCategoryDescription = mSellingSubCategoryDescription;
	}

	public void setmSellingSubCategoryId(String mSellingSubCategoryId) {
		this.mSellingSubCategoryId = mSellingSubCategoryId;
	}

	public String getmSellingSubCategoryName() {
		return mSellingSubCategoryName;
	}

	public void setmSellingSubCategoryName(String mSellingSubCategoryName) {
		this.mSellingSubCategoryName = mSellingSubCategoryName;
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

	
}
