package com.FarmersCart.Bean;

public class SellingCategoryBean {
	private String mSellingCategoryId;
	private String mSellingCategoryName;
	private String mSellingCategoryImage;
	
	public SellingCategoryBean(String sellingCategoryId, String sellingCategoryName,String sellingCategoryImage){
		super();
		this.mSellingCategoryId=sellingCategoryId;	
		this.mSellingCategoryName=sellingCategoryName;
		this.mSellingCategoryImage=sellingCategoryImage;
	}

	public String getmSellingCategoryId() {
		return mSellingCategoryId;
	}

	public void setmSellingCategoryId(String mSellingCategoryId) {
		this.mSellingCategoryId = mSellingCategoryId;
	}

	public String getmSellingCategoryName() {
		return mSellingCategoryName;
	}

	public void setmSellingCategoryName(String mSellingCategoryName) {
		this.mSellingCategoryName = mSellingCategoryName;
	}

	public String getmSellingCategoryImage() {
		return mSellingCategoryImage;
	}

	public void setmSellingCategoryImage(String mSellingCategoryImage) {
		this.mSellingCategoryImage = mSellingCategoryImage;
	}

	

	
	
}
