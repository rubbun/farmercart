package com.FarmersCart.UI;

import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;

public class SplashActivity extends BaseActivity {
	
	private static final int WAIT_TIME = 3000;
	private static final int HANDLER_MSG = 1;
	private Handler handler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			switch (msg.what) {
			case HANDLER_MSG:	
				if(app.getUserinfo().isLoggedin){
					Intent i = new Intent(SplashActivity.this,DashBoardActivity.class);
					i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
					startActivity(i);
				}else{
					Intent i = new Intent(SplashActivity.this,LoginActivity.class);
					i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
					startActivity(i);
				}
				
				break;

			default:
				break;
			}
		}
	};	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	   
		
		final CharSequence[] options = { "English", "Hindi" };

		 

        AlertDialog.Builder builder = new AlertDialog.Builder(SplashActivity.this);

        builder.setTitle("Choose Language");
        builder.setCancelable(false);
        builder.setItems(options, new DialogInterface.OnClickListener() {

            @Override

            public void onClick(DialogInterface dialog, int item) {

                if (options[item].equals("English"))

                {

                    app.getUserinfo().setLanguage("en");
                    
                    handler.sendEmptyMessageDelayed(HANDLER_MSG, WAIT_TIME);
                    Resources res = getApplicationContext().getResources();
            	    // Change locale settings in the app.
            	    DisplayMetrics dm = res.getDisplayMetrics();
            	    android.content.res.Configuration conf = res.getConfiguration();
            	    conf.locale = new Locale(app.getUserinfo().lang);
            	    res.updateConfiguration(conf, dm);
            		
            		setContentView(R.layout.activity_splash);
                }

                else if (options[item].equals("Hindi"))

                {

                   
                	app.getUserinfo().setLanguage("hi");
 
                	handler.sendEmptyMessageDelayed(HANDLER_MSG, WAIT_TIME);
                	 Resources res = getApplicationContext().getResources();
             	    // Change locale settings in the app.
             	    DisplayMetrics dm = res.getDisplayMetrics();
             	    android.content.res.Configuration conf = res.getConfiguration();
             	    conf.locale = new Locale(app.getUserinfo().lang);
             	    res.updateConfiguration(conf, dm);
             		
             		setContentView(R.layout.activity_splash);
                }


            }

        });

        builder.show();	
		
		
		
	}

	/*@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.splash, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}*/
}
