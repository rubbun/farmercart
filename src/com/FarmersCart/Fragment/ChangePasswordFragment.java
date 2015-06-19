package com.FarmersCart.Fragment;



import org.json.JSONException;
import org.json.JSONObject;

import com.FarmersCart.Constant.Constant;
import com.FarmersCart.Interface.IBase;
import com.FarmersCart.Network.FarmersFarmFresh2Home;
import com.FarmersCart.UI.BaseActivity;
import com.FarmersCart.UI.DashBoardActivity;
import com.FarmersCart.UI.LoginActivity;
import com.FarmersCart.UI.R;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ChangePasswordFragment extends Fragment implements OnClickListener{
	private BaseActivity base;
	private EditText et_old_password,et_new_password,et_new_conf_password ;
	private Button btn_submit, btn_cancel;
	private IBase iBase ;
	
	public ChangePasswordFragment(BaseActivity base){
		this.base  = base;	
		this.iBase = (IBase) base ;
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			
			View v = inflater.inflate(R.layout.change_password, container, false);
			
			 et_old_password = (EditText)v.findViewById(R.id.et_old_password);
			 et_new_password = (EditText)v.findViewById(R.id.et_new_password);
			 et_new_conf_password = (EditText)v.findViewById(R.id.et_new_conf_password);
			 btn_submit = (Button)v.findViewById(R.id.btn_submit);
			 btn_cancel = (Button)v.findViewById(R.id.btn_cancel);
			 
			 btn_submit.setOnClickListener(this);
			 btn_cancel.setOnClickListener(this);
			 
		    return v ;	
		}

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(arg0 == btn_submit){
				if(validate()){
					new callChangePassword().execute(base.app.getUserinfo().ID,et_new_password.getText().toString().trim());
				}
			}
			if(arg0 == btn_cancel){
				iBase.goHome();
			}
		}

		private boolean validate() {
			// TODO Auto-generated method stub
			if(et_old_password.getText().toString().trim().length()==0){
				et_old_password.setError("Please enter Old Password");
				return false ;
			}else if(!et_old_password.getText().toString().trim().equals(base.app.getUserinfo().password)){
				et_old_password.setError("Please enter Correct Password");
				return false ;
			}else if(et_new_password.getText().toString().trim().length()==0 || et_new_password.getText().toString().trim().length()<4){
				et_new_password.setError("Please enter at least 4 digit Password");
				return false ;
			}else if(!et_new_password.getText().toString().trim().equals(et_new_conf_password.getText().toString().trim())){
				et_new_conf_password.setError("Please re enter correct new Password");
				return false ;
			}
			return true;
		}
		
		public class callChangePassword extends AsyncTask<String, Void, Boolean>{
			protected void onPreExecute() {
				base.showProgressDailog("Please wait...");
			}
			
			@Override
			protected Boolean doInBackground(String... params) {
			  	try {
			  		JSONObject jsonObjSend = new JSONObject();	
			  		jsonObjSend.put("id", params[0]);
			  		jsonObjSend.put("password", params[1]);
					Log.e("SEND", jsonObjSend.toString());
					JSONObject json = FarmersFarmFresh2Home.SendHttpPost(Constant.URLS.CHANGE_PASSWORD.getURL(),jsonObjSend);
					boolean status=json.getBoolean("status");
					
					
					return status;
						
					
				} catch (JSONException e) {
					e.printStackTrace(); 
					base.dismissProgressDialog();
					return false;
					
				}			
			}
			protected void onPostExecute(Boolean status) {	
				base.dismissProgressDialog();
				if(status){
					base.app.getUserinfo().setChangedPassword(et_new_password.getText().toString().trim());;
					Toast.makeText(getActivity(),"Password Changed Successfully ", 
			                Toast.LENGTH_SHORT).show();
					iBase.goHome();
					
				}else{
					Toast.makeText(getActivity(),"Some Internal error occour.. Please try after sometime.", 
			                Toast.LENGTH_SHORT).show();
				}
				
			}
		}
}
