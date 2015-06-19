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

public class RegistrationActivity  extends BaseActivity{
	private EditText et_reg_fname,et_reg_lname,et_reg_phone,et_reg_state,et_reg_city,et_reg_opt_phone ;
	private Spinner sp_reg_country ;
	private Button btn_reg_submit ;
	private String mUserId = "" ;
	String country = "" ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_registration);
		
		et_reg_fname = (EditText)findViewById(R.id.et_reg_fname);
		et_reg_lname = (EditText)findViewById(R.id.et_reg_lname);
		et_reg_phone = (EditText)findViewById(R.id.et_reg_phone);
		et_reg_opt_phone = (EditText)findViewById(R.id.et_reg_opt_phone);
		et_reg_state = (EditText)findViewById(R.id.et_state);
		et_reg_city = (EditText)findViewById(R.id.et_reg_city);
		sp_reg_country = (Spinner)findViewById(R.id.sp_reg_country);
		btn_reg_submit = (Button)findViewById(R.id.btn_reg_submit);
		
		btn_reg_submit.setOnClickListener(this);
		
		sp_reg_country.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				country = sp_reg_country.getSelectedItem().toString();
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	@Override
	public void onClick(View v) {
		if(v == btn_reg_submit){
			if(validateRegistration()){
				new callRegistration().execute(et_reg_fname.getText().toString().trim(),et_reg_lname.getText().toString().trim(),
						et_reg_phone.getText().toString().trim(),et_reg_opt_phone.getText().toString().trim(),country,et_reg_state.getText().toString().trim(),et_reg_city.getText().toString().trim());
			}
		}
	}


	private boolean validateRegistration() {
		// TODO Auto-generated method stub
		if(et_reg_fname.getText().toString().trim().length()==0){
			et_reg_fname.setError("Please enter your First Name");
			return false;
		}else if(et_reg_lname.getText().toString().trim().length()==0){
			et_reg_lname.setError("Please enter your Last Name");
			return false;
		}else if(et_reg_phone.getText().toString().trim().length()==0){
			et_reg_phone.setError("Please enter your Phone Number");
			return false;
		}else if(et_reg_state.getText().toString().trim().length()==0 ){
			et_reg_state.setError("Please enter your State");
			return false;
		}else if(et_reg_city.getText().toString().trim().length()==0){
			et_reg_city.setError("Please enter your City");
			return false;
		}
		return true;
	}
	
	
	public class callRegistration extends AsyncTask<String, Void, Boolean>{
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
		  		jsonObjSend.put("password", "1111");
		  		
				Log.e("SEND", jsonObjSend.toString());
				
				JSONObject json = FarmersFarmFresh2Home.SendHttpPost(Constant.URLS.REGISTRATION.getURL(),jsonObjSend);
				boolean status=json.getBoolean("status");
				
				if(status){
					mUserId = json.getString("id") ;
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
				//app.getUserinfo().setUserInfo(mUserId, et_reg_fname.getText().toString().trim(), et_reg_lname.getText().toString().trim(), et_reg_phone.getText().toString().trim(), true);
				Toast.makeText(RegistrationActivity.this,"Registration Successful...Please Login to Continue...", 
		                Toast.LENGTH_SHORT).show();
				Intent i = new Intent(RegistrationActivity.this,LoginActivity.class);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivity(i);
			}else{
				Toast.makeText(RegistrationActivity.this,"Phone Number is already exist, Try with another Number", 
		                Toast.LENGTH_SHORT).show();
			}
			
		}
	}
	
	
	public int genRandomNumber() {
	    Random r = new Random( System.currentTimeMillis() );
	    return ((1 + r.nextInt(2)) * 1000 + r.nextInt(1000));
	}
}
