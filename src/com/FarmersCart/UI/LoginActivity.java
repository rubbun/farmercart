package com.FarmersCart.UI;

import org.json.JSONException;
import org.json.JSONObject;

import com.FarmersCart.Constant.Constant;
import com.FarmersCart.Network.FarmersFarmFresh2Home;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends BaseActivity{
	private EditText et_login_phone,et_login_password ;
	private Button btn_submit ,btn_log_registration;
	private String mUserId = "" , mUserFirstName = "", mUserLastNAme= "", mUserPhone="" ,mPassword="",mOptionalPhone="",mCity="",mState="",mCountry="";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		et_login_phone = (EditText)findViewById(R.id.et_login_phoneno);
		et_login_password = (EditText)findViewById(R.id.et_login_password);
		btn_submit = (Button)findViewById(R.id.btn_submit);
		btn_log_registration =  (Button)findViewById(R.id.btn_log_registration);
		
		btn_submit.setOnClickListener(this);
		btn_log_registration.setOnClickListener(this);
	}
	
	@Override
	public void onClick(View v) {
		if(v == btn_submit){
			if(validateLogin()){
				new callLogin().execute(et_login_phone.getText().toString().trim(),et_login_password.getText().toString().trim());
			}
		}
		if(v == btn_log_registration){
			Intent i = new Intent(LoginActivity.this,RegistrationActivity.class);
			startActivity(i);
		}
	}


	private boolean validateLogin() {
		// TODO Auto-generated method stub
		if(et_login_phone.getText().toString().trim().length()==0){
			et_login_phone.setError("Please enter your Phone Number");
			return false;
		}else if(et_login_password.getText().toString().trim().length()==0){
			et_login_password.setError("Please enter your Password");
			return false;
		}else if( et_login_password.getText().toString().trim().length()<4){
			et_login_password.setError("Password length should be at least 5");
			return false;
		}
		return true;
	}
	
	
	public class callLogin extends AsyncTask<String, Void, Boolean>{
		protected void onPreExecute() {
			showProgressDailog("Please wait...");
		}
		
		@Override
		protected Boolean doInBackground(String... params) {
		  	try {
		  		JSONObject jsonObjSend = new JSONObject();	
		  		jsonObjSend.put("phone", params[0]);
		  		jsonObjSend.put("password", params[1]);
				Log.e("SEND", jsonObjSend.toString());
				JSONObject json = FarmersFarmFresh2Home.SendHttpPost(Constant.URLS.LOGIN.getURL(),jsonObjSend);
				boolean status=json.getBoolean("status");
				
				if(status){
					mUserId = json.getString("id") ;
					mUserFirstName = json.getString("fname") ;
					mUserLastNAme = json.getString("lname") ;
					mUserPhone = json.getString("phone") ;
					mPassword = json.getString("password") ;
					mOptionalPhone = json.getString("optional_phone") ;
					mCity = json.getString("city") ;
					mState = json.getString("state") ;
					mCountry = json.getString("country") ;
				}
				
				return status;
					
				
			} catch (JSONException e) {
				e.printStackTrace(); 
				dismissProgressDialog();
				return false;
				
			}			
		}
		protected void onPostExecute(Boolean status) {	
			dismissProgressDialog();
			if(status){
				app.getUserinfo().setUserInfo(mUserId, mUserFirstName, mUserLastNAme, mUserPhone,mOptionalPhone,mPassword, true,mCity,mState,mCountry);
				Toast.makeText(LoginActivity.this,"Login Successful", 
		                Toast.LENGTH_SHORT).show();
				Intent i = new Intent(LoginActivity.this,DashBoardActivity.class);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivity(i);
			}else{
				Toast.makeText(LoginActivity.this,"Please Enter Correct Phone Number/Password", 
		                Toast.LENGTH_SHORT).show();
			}
			
		}
	}
}
