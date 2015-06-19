package com.FarmersCart.UI;

import java.util.Random;

import org.json.JSONException;
import org.json.JSONObject;

import com.FarmersCart.Constant.Constant;
import com.FarmersCart.Network.FarmersFarmFresh2Home;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class SettingsActivity  extends BaseActivity{
	private EditText et_settings_fname,et_settings_lname,et_settings_phone,et_settings_state,et_settings_city,et_settings_opt_phone,
	et_settings_old_Password,et_settings_new_password,et_settings_conf_password;
	private Spinner sp_settings_country ;
	private Button btn_settings_submit ;
	private String mUserId = "" ,password ="";
	private String country = "" ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_settings);
		
		et_settings_fname = (EditText)findViewById(R.id.et_settings_fname);
		et_settings_lname = (EditText)findViewById(R.id.et_settings_lname);
		et_settings_phone = (EditText)findViewById(R.id.et_settings_phone);
		et_settings_opt_phone = (EditText)findViewById(R.id.et_settings_opt_phone);
		et_settings_state = (EditText)findViewById(R.id.et_state);
		et_settings_city = (EditText)findViewById(R.id.et_settings_city);
		et_settings_old_Password = (EditText)findViewById(R.id.et_settings_old_Password);
		et_settings_new_password = (EditText)findViewById(R.id.et_settings_new_password);
		et_settings_conf_password = (EditText)findViewById(R.id.et_settings_conf_password);
		sp_settings_country = (Spinner)findViewById(R.id.sp_settings_country);
		btn_settings_submit = (Button)findViewById(R.id.btn_settings_submit);
		mUserId = app.getUserinfo().ID ;
		btn_settings_submit.setOnClickListener(this);
		
		et_settings_fname.setText(app.getUserinfo().firstName);
		et_settings_lname.setText(app.getUserinfo().lastName);
		et_settings_phone.setText(app.getUserinfo().phoneNumber);
		et_settings_opt_phone.setText(app.getUserinfo().optionalPhoneNumber);
		et_settings_state.setText(app.getUserinfo().state);
		et_settings_city.setText(app.getUserinfo().city);
		et_settings_old_Password.setText(app.getUserinfo().password);
		
		password = app.getUserinfo().password ;
		
		String[] countryArray = getResources().getStringArray(R.array.country_array);
		
		for (int i = 0;i<countryArray.length;i++ ) {
		       if(countryArray[i].equals(app.getUserinfo().country)){
		    	   sp_settings_country.setSelection(i);
		    	   break;
		       }
		    }
		
		
		
		sp_settings_country.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				country = sp_settings_country.getSelectedItem().toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	@Override
	public void onClick(View v) {
		if(v == btn_settings_submit){
			if(validateRegistration()){
				new callSettings().execute(et_settings_fname.getText().toString().trim(),et_settings_lname.getText().toString().trim(),
						et_settings_phone.getText().toString().trim(),et_settings_opt_phone.getText().toString().trim(),country,et_settings_state.getText().toString().trim(),et_settings_city.getText().toString().trim());
			}
		}
	}


	private boolean validateRegistration() {
		// TODO Auto-generated method stub
		if(et_settings_fname.getText().toString().trim().length()==0){
			et_settings_fname.setError("Please enter your First Name");
			return false;
		}else if(et_settings_lname.getText().toString().trim().length()==0){
			et_settings_lname.setError("Please enter your Last Name");
			return false;
		}else if(et_settings_phone.getText().toString().trim().length()==0){
			et_settings_phone.setError("Please enter your Phone Number");
			return false;
		}else if(et_settings_state.getText().toString().trim().length()==0 ){
			et_settings_state.setError("Please enter your State");
			return false;
		}else if(et_settings_city.getText().toString().trim().length()==0){
			et_settings_city.setError("Please enter your City");
			return false;
		}
		
		if(!et_settings_old_Password.getText().toString().trim().equals(app.getUserinfo().password)){
			et_settings_old_Password.setError("Please enter your Correct Password");
			return false;
		}else if(et_settings_new_password.getText().toString().trim().length()>0 && et_settings_new_password.getText().toString().trim().length()<4){
			et_settings_new_password.setError("New Password should be atleast 4 digit");
			return false;
		}else if(!et_settings_conf_password.getText().toString().equals(et_settings_new_password.getText().toString().trim())){
			et_settings_conf_password.setError("Please confirm New Password");
			return false;
		}

		if(et_settings_old_Password.getText().toString().trim().equals(app.getUserinfo().password) && 
				et_settings_new_password.getText().toString().trim().equals(et_settings_conf_password.getText().toString().trim()) && et_settings_new_password.getText().toString().trim().length()>=4){
			password = et_settings_new_password.getText().toString().trim() ;
		}
		return true;
	}
	
	
	public class callSettings extends AsyncTask<String, Void, Boolean>{
		protected void onPreExecute() {
			showProgressDailog("Please wait...");
		}
		
		@Override
		protected Boolean doInBackground(String... params) {
		  	try {
		  		JSONObject jsonObjSend = new JSONObject();	
		  		jsonObjSend.put("fname", params[0]);
		  		jsonObjSend.put("lname", params[1]);
		  		jsonObjSend.put("phone", params[2]);
		  		jsonObjSend.put("opt_phone", params[2]);
		  		jsonObjSend.put("country", params[4]);
		  		jsonObjSend.put("state", params[5]);
		  		jsonObjSend.put("city", params[6]);
		  		jsonObjSend.put("password", password);
		  		jsonObjSend.put("user_id", app.getUserinfo().ID);
		  		
				Log.e("SEND", jsonObjSend.toString());
				
				JSONObject json = FarmersFarmFresh2Home.SendHttpPost(Constant.URLS.SETTINGS.getURL(),jsonObjSend);
				boolean status=json.getBoolean("status");
			
				
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
				app.getUserinfo().setUserInfo(mUserId, et_settings_fname.getText().toString().trim(), et_settings_lname.getText().toString().trim(), et_settings_phone.getText().toString().trim(),
						 et_settings_opt_phone.getText().toString().trim(),password,true, et_settings_city.getText().toString().trim(), et_settings_state.getText().toString().trim(), sp_settings_country.getSelectedItem().toString().trim());
				Toast.makeText(SettingsActivity.this,"Edit Successfull...", 
		                Toast.LENGTH_SHORT).show();
				SettingsActivity.this.finish();
			}else{
				Toast.makeText(SettingsActivity.this,"Some internal error occour...Please try after sometime...", 
		                Toast.LENGTH_SHORT).show();
			}
			
		}
	}
	

}
