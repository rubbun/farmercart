package com.FarmersCart.Constant;

import java.util.ArrayList;

import com.FarmersCart.Bean.CartBean;

public class Constant {

	public static String sSelling_category_title = "" ;
	public static String sSelling_category_id = "";
	public static ArrayList<CartBean> sCartBean = new ArrayList<CartBean>();
	public static String user_id = "";
	public enum values {
		PREF_FIRST_LOGIN,USRINFO,ID,PASSWORD,LANG, FIRSTNAME, LASTNAME , PHONE_NUMBER,OPTIONAL_PHONE_NUMBER,IS_LOGGEDIN,IS_FIRST_LOGIN ,CITY,STATE,COUNTRY;

	}
	
	public enum URLS {			
		
		LOGIN("http://www.devlpconsole.com/farm2Home/login.php"),
		REGISTRATION("http://www.devlpconsole.com/farm2Home/registration.php"),
		CHANGE_PASSWORD("http://www.devlpconsole.com/farm2Home/change_password.php"),
		GET_SELLING_CATEGORY("http://www.devlpconsole.com/farm2Home/get_selling_category.php"),
		GET_BUYING_CATEGORY("http://www.devlpconsole.com/farm2Home/get_buying_category.php"),
		GET_SELLING_SUBCATEGORY("http://www.devlpconsole.com/farm2Home/get_selling_category_item.php"),
		GET_BUYING_SUBCATEGORY("http://www.devlpconsole.com/farm2Home/get_buying_category_item.php"),
		ADD_SELLING_PRODUCT("http://www.devlpconsole.com/farm2Home/add_selling_product.php"),
		SELLING_TRANSACTION("http://www.devlpconsole.com/farm2Home/selling_list.php"),
		BUYING_TRANSACTION("http://www.devlpconsole.com/farm2Home/buying_list.php"),
		SETTINGS("http://www.devlpconsole.com/farm2Home/settings.php"),
		GET_BRAND("http://www.devlpconsole.com/farm2Home/get_brand.php"),
		CHECKOUT("http://www.devlpconsole.com/farm2Home/checkout.php"),
		GET_MESSAGE("http://www.devlpconsole.com/farm2Home/get_message.php"),
		GCM("http://www.devlpconsole.com/farm2Home/gcm.php");
		
		public String mURL;
		
		URLS(String mURL) {
			this.mURL = mURL;
		}
		
		public String getURL() {
			return mURL;
		}
	}
}
