package com.FarmersCart.UI;

import com.FarmersCart.Application.Appsettings;

import com.FarmersCart.Application.UserInfo;
import com.FarmersCart.Constant.Constant;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;

//8981657785

public class BaseActivity extends FragmentActivity implements OnClickListener {
	public ProgressDialog prsDlg;
	public Appsettings app = null;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		app = (Appsettings) getApplication();
		
		app.setUserinfo(new UserInfo(this));
	}

	public void showProgressDailog(String msg) {
		prsDlg = new ProgressDialog(this);
		prsDlg.setMessage(msg);
		prsDlg.setProgressStyle(ProgressDialog.STYLE_SPINNER);
		prsDlg.setIndeterminate(true);
		prsDlg.setCancelable(false);
		prsDlg.show();

	}

	public void dismissProgressDialog() {
		if (prsDlg.isShowing()) {
			prsDlg.dismiss();
		}
	}

	public void onClick(View v) {

	}
	

	
	public boolean isConnectingToInternet(){
		ConnectivityManager connectivity = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
		  if (connectivity != null) 
		  {
			  NetworkInfo[] info = connectivity.getAllNetworkInfo();
			  if (info != null) 
				  for (int i = 0; i < info.length; i++) 
					  if (info[i].getState() == NetworkInfo.State.CONNECTED)
					  {
						  return true;
					  }

		  }
		  return false;
	}
}
