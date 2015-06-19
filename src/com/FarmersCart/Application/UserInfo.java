package com.FarmersCart.Application;

import com.FarmersCart.Constant.Constant;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class UserInfo {

	

	public String lang="",ID="",firstName = "",password="";
	public String lastName = "",phoneNumber="",optionalPhoneNumber="",city="",state="",country="";
	public boolean isLoggedin = false ,isFirstLoggedin = false ;
	public SharedPreferences preference = null;
	public SharedPreferences preference_first_login = null;

	public UserInfo(Context ctx) {

		preference_first_login = ctx.getSharedPreferences(Constant.values.PREF_FIRST_LOGIN.name(), Context.MODE_PRIVATE);
		
		preference = ctx.getSharedPreferences(Constant.values.USRINFO.name(), Context.MODE_PRIVATE);
		lang = preference.getString(Constant.values.LANG.name(), lang);
		password = preference.getString(Constant.values.PASSWORD.name(), password);
		ID = preference.getString(Constant.values.ID.name(), ID);
		firstName = preference.getString(Constant.values.FIRSTNAME.name(), firstName);
		lastName = preference.getString(Constant.values.LASTNAME.name(), lastName);
		phoneNumber = preference.getString(Constant.values.PHONE_NUMBER.name(), phoneNumber);
		optionalPhoneNumber = preference.getString(Constant.values.OPTIONAL_PHONE_NUMBER.name(), optionalPhoneNumber);
		city = preference.getString(Constant.values.CITY.name(), city);
		state = preference.getString(Constant.values.STATE.name(), state);
		country = preference.getString(Constant.values.COUNTRY.name(), country);
		isLoggedin = preference.getBoolean(Constant.values.IS_LOGGEDIN.name(), isLoggedin);
		isFirstLoggedin = preference_first_login.getBoolean(Constant.values.IS_FIRST_LOGIN.name(), isFirstLoggedin);
	}

	public void setUserInfo(String id,String first_name,String last_name,String phone_number,String opt_phone_number,String pass,
			Boolean is_log,String user_city,String user_state,String user_country) {
		this.ID = id;
		this.password = pass;
		this.firstName = first_name;
		this.lastName = last_name;
		this.phoneNumber = phone_number;
		this.city = user_city;
		this.state = user_state;
		this.country = user_country;
		this.optionalPhoneNumber = opt_phone_number;
		this.isLoggedin = is_log;
		Editor edit = preference.edit();
		edit.putString(Constant.values.ID.name(), ID);
		edit.putString(Constant.values.PASSWORD.name(), password);
		edit.putString(Constant.values.FIRSTNAME.name(), first_name);
		edit.putString(Constant.values.LASTNAME.name(), lastName);
		edit.putString(Constant.values.PHONE_NUMBER.name(), phoneNumber);
		edit.putString(Constant.values.OPTIONAL_PHONE_NUMBER.name(), optionalPhoneNumber);
		edit.putString(Constant.values.CITY.name(), city);
		edit.putString(Constant.values.STATE.name(), state);
		edit.putString(Constant.values.COUNTRY.name(), country);
		edit.putBoolean(Constant.values.IS_LOGGEDIN.name(), isLoggedin);
		edit.commit();
	}
	
	public void setFirstLoginInfo(Boolean is_first_log) {
		this.isFirstLoggedin = is_first_log;
		Editor edit = preference_first_login.edit();
		edit.putBoolean(Constant.values.IS_FIRST_LOGIN.name(), isFirstLoggedin);
		edit.commit();
	}
	
	public void setChangedPassword(String pass) {
		this.password = pass;
		Editor edit = preference_first_login.edit();
		edit.putString(Constant.values.PASSWORD.name(), password);
		edit.commit();
	}
	
	public void setLanguage(String la) {
		this.lang = la;
		Editor edit = preference_first_login.edit();
		edit.putString(Constant.values.LANG.name(), lang);
		edit.commit();
	}
	
	public void clearPref(){
		Editor editor = preference.edit();
		editor.clear().commit();
	}

}
