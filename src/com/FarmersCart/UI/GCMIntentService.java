package com.FarmersCart.UI;

import static com.FarmersCart.Util.CommonUtilities.SENDER_ID;
import static com.FarmersCart.Util.CommonUtilities.displayMessage;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.LocationManager;
import android.util.Log;

import com.FarmersCart.Util.ServerUtilities;
import com.google.android.gcm.GCMBaseIntentService;


public class GCMIntentService extends GCMBaseIntentService {

	private static final String TAG = "GCMIntentService";
	private static LocationManager locationManager;
	
	 public static String weiw_message = null;
	 public static SharedPreferences sharedPreferences;
	 public GCMIntentService() {
        super(SENDER_ID);
    }
    
  @Override
    protected void onRegistered(Context context, String registrationId) {
    	
    	
    	Log.i(TAG, "Device registered: regId = " + registrationId);
        displayMessage(context, "Your device registred with GCM");
        ServerUtilities.register(context,registrationId, "registrationId,app.getAppInfo().userId");
    }

    @Override
    protected void onUnregistered(Context context, String registrationId) {
        Log.i(TAG, "Device unregistered");
        displayMessage(context, getString(R.string.gcm_unregistered));
        ServerUtilities.unregister(context, registrationId);
    }

    @Override
    protected void onMessage(Context context, Intent intent) {
        Log.i(TAG, "Received message");
        String message = intent.getExtras().getString("price");        
        displayMessage(context, message);
        generateNotification(context, message);
    }

     @Override
    protected void onDeletedMessages(Context context, int total) {
        Log.i(TAG, "Received deleted messages notification");
        String message = getString(R.string.gcm_deleted, total);
        displayMessage(context, message);
        generateNotification(context, message);
    }

     @Override
    public void onError(Context context, String errorId) {
        Log.i(TAG, "Received error: " + errorId);
        displayMessage(context, getString(R.string.gcm_error, errorId));
    }

    @Override
    protected boolean onRecoverableError(Context context, String errorId) {
        Log.i(TAG, "Received recoverable error: " + errorId);
        displayMessage(context, getString(R.string.gcm_recoverable_error,   errorId));
        return super.onRecoverableError(context, errorId);
    }
	@SuppressWarnings("deprecation")
	private static void generateNotification(final Context context,String message) {
		
		Log.e("snomada", message);
		
	}
    
    public static boolean isGPSenabled(){
		return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
    }
    
   
   
}