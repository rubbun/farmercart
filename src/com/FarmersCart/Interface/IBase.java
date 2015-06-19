package com.FarmersCart.Interface;

public interface IBase {
	public void goHome() ;

	public void goSellingFragmentItem(String getmSellingCategoryId, String title);

	public void goBuyingFragmentItem(String getmBuyingCategoryId,
			String getmBuyingCategoryName);

	public void goSellingFragmentItemDetail(String getmSellingSubCategoryId,
			String getmSellingSubCategoryName, String string,String getmSellingSubCategoryDesc, String string2);

	public void goBuyingFragmentItemDetail(String getmBuyingSubCategoryId,
			String getmBuyingSubCategoryName,
			String getmBuyingSubCategoryImage, String getmBuyingSubCategoryPrice,String getmBuyingSubCategoryDescription, String user_id, String user_name);

	public void goCart();
	public void getUnreadMessage();
}
