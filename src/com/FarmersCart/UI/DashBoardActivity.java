package com.FarmersCart.UI;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.FarmersCart.Bean.MessageBean;
import com.FarmersCart.Constant.Constant;
import com.FarmersCart.Events.EventAddtoCart;
import com.FarmersCart.Events.EventGuestCheckout;
import com.FarmersCart.Fragment.BuyingEachItemFragment;
import com.FarmersCart.Fragment.BuyingFragment;
import com.FarmersCart.Fragment.BuyingItemFragment;
import com.FarmersCart.Fragment.CartFragment;
import com.FarmersCart.Fragment.ChangePasswordFragment;
import com.FarmersCart.Fragment.HomeFragment;
import com.FarmersCart.Fragment.MessageFragment;
import com.FarmersCart.Fragment.SellingEachItemFragment;
import com.FarmersCart.Fragment.SellingFragment;
import com.FarmersCart.Fragment.SellingItemFragment;
import com.FarmersCart.Interface.IBase;
import com.FarmersCart.Network.FarmersFarmFresh2Home;

import de.greenrobot.event.EventBus;

public class DashBoardActivity extends BaseActivity implements IBase {
	private LinearLayout list_slidermenu;
	private DrawerLayout mDrawerLayout;
	private ActionBarDrawerToggle mDrawerToggle;
	private String selling_category_title = "", selling_category_id = "", buying_category_title = "", buying_category_id = "";
	private String selling_each_item_title = "", selling_each_item_image = "", selling_each_item_description = "", selling_each_item_price = "", selling_each_item_id = "";
	private String buying_each_item_title = "", buying_each_item_image = "", buying_each_item_price = "", buying_each_item_id = "", brand_user_id = "", brand_user_name = "", buying_each_item_description = "";

	private TextView tv_message;
	private int message_counter = 0;

	private ArrayList<MessageBean> mMessageBean = new ArrayList<MessageBean>();
	private Handler mHandle = new Handler();

	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		EventBus.getDefault().register(this);
		setContentView(R.layout.activity_dashboard);
		list_slidermenu = (LinearLayout) findViewById(R.id.list_slidermenu);

		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);

		View v;
		LayoutInflater vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		v = vi.inflate(R.layout.layout_slider, null);
		list_slidermenu.addView(v);
		TextView tv_logout = (TextView) v.findViewById(R.id.tv_logout);
		TextView tv_home = (TextView) v.findViewById(R.id.tv_home);
		TextView tv_selling = (TextView) v.findViewById(R.id.tv_selling);
		TextView tv_buying = (TextView) v.findViewById(R.id.tv_buying);
		TextView tv_settings = (TextView) v.findViewById(R.id.tv_settings);
		tv_message = (TextView) v.findViewById(R.id.tv_message);

		tv_home.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
					mDrawerLayout.closeDrawer(Gravity.LEFT);
				}
				displayView(1, false);
			}
		});
		tv_settings.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
					mDrawerLayout.closeDrawer(Gravity.LEFT);
				}
				Intent i = new Intent(DashBoardActivity.this, SettingsActivity.class);
				startActivity(i);
			}
		});
		tv_logout.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				app.getUserinfo().clearPref();
				Intent i = new Intent(DashBoardActivity.this, LoginActivity.class);
				i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivity(i);
			}
		});

		tv_selling.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
					mDrawerLayout.closeDrawer(Gravity.LEFT);
				}
				displayView(2, true);
			}
		});
		tv_buying.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
					mDrawerLayout.closeDrawer(Gravity.LEFT);
				}
				displayView(3, true);
			}
		});
		tv_message.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				if (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
					mDrawerLayout.closeDrawer(Gravity.LEFT);
				}
				displayView(9, true);
			}
		});

		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(app.getUserinfo().isLoggedin);

		mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, R.drawable.ic_drawer, // nav
																								// menu
																								// toggle
																								// icon
				R.string.app_name, // nav drawer open - description for
									// accessibility
				R.string.app_name // nav drawer close - description for
									// accessibility
		);/*
		 * {
		 * 
		 * @Override public boolean onOptionsItemSelected(MenuItem item) { if
		 * (item != null && item.getItemId() == android.R.id.home) { if
		 * (mDrawerLayout.isDrawerOpen(Gravity.LEFT)) {
		 * mDrawerLayout.closeDrawer(Gravity.LEFT); } else {
		 * mDrawerLayout.openDrawer(Gravity.LEFT); } } return false; }
		 * 
		 * public void onDrawerClosed(View view) { //
		 * getActionBar().setTitle(getResources().getString(R.string.home)); //
		 * calling onPrepareOptionsMenu() to show action bar icons
		 * invalidateOptionsMenu(); }
		 * 
		 * public void onDrawerOpened(View drawerView) { //
		 * getActionBar().setTitle(getResources().getString(R.string.menu)); //
		 * calling onPrepareOptionsMenu() to hide action bar icons
		 * invalidateOptionsMenu(); } };
		 */
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (!app.getUserinfo().isFirstLoggedin) {
			app.getUserinfo().setFirstLoginInfo(true);
			// showChangePasswordDialog();
			displayView(0, false);
		} else {
			if (app.getUserinfo().isLoggedin) {
				displayView(1, false);
			} else {
				displayView(3, true);
			}

		}

	}

	private void displayView(int position, boolean flag) {
		Fragment fragment = null;
		switch (position) {

		case 0:
			getActionBar().setTitle("Change Password");
			fragment = new ChangePasswordFragment(this);
			break;

		case 1:

			getActionBar().setTitle("Farmers-cart");
			fragment = new HomeFragment(this);
			break;

		case 2:
			getActionBar().setTitle("Selling");
			fragment = new SellingFragment(this);
			break;

		case 3:
			getActionBar().setTitle("Buying");
			fragment = new BuyingFragment(this);
			break;

		case 4:
			getActionBar().setTitle(selling_category_title);
			fragment = new SellingItemFragment(this);
			Bundle s = new Bundle();
			s.putString("selling_category_id", selling_category_id);
			fragment.setArguments(s);
			break;
		case 5:
			getActionBar().setTitle(buying_category_title);
			fragment = new BuyingItemFragment(this);
			Bundle b = new Bundle();
			b.putString("buying_category_id", buying_category_id);
			fragment.setArguments(b);
			break;

		case 6:
			getActionBar().setTitle(selling_each_item_title);
			fragment = new SellingEachItemFragment(this);
			Bundle selling_each_item = new Bundle();
			selling_each_item.putString("selling_each_item_id", selling_each_item_id);
			selling_each_item.putString("selling_each_item_title", selling_each_item_title);
			selling_each_item.putString("selling_each_item_price", selling_each_item_price);
			selling_each_item.putString("selling_each_item_image", selling_each_item_image);
			selling_each_item.putString("selling_each_item_description", selling_each_item_description);
			fragment.setArguments(selling_each_item);
			break;

		case 7:
			getActionBar().setTitle(selling_each_item_title);
			fragment = new BuyingEachItemFragment(this);
			Bundle buying_each_item = new Bundle();
			buying_each_item.putString("buying_each_item_id", buying_each_item_id);
			buying_each_item.putString("buying_each_item_title", buying_each_item_title);
			buying_each_item.putString("buying_each_item_price", buying_each_item_price);
			buying_each_item.putString("buying_each_item_image", buying_each_item_image);
			buying_each_item.putString("brand_user_id", brand_user_id);
			buying_each_item.putString("brand_user_name", brand_user_name);
			buying_each_item.putString("buying_each_item_description", buying_each_item_description);
			fragment.setArguments(buying_each_item);
			break;

		case 8:
			getActionBar().setTitle("Cart");
			fragment = new CartFragment(this);
			break;
		case 9:
			getActionBar().setTitle("Message");
			tv_message.setText("Message");
			fragment = new MessageFragment(this);
			break;
		}

		if (fragment != null) {
			FragmentManager fragmentManager = getSupportFragmentManager();
			FragmentTransaction ft = fragmentManager.beginTransaction();
			ft.replace(R.id.dashboard_frame, fragment);
			if (flag) {
				Log.e("MainActivity", "Reach++++++++++++++");
				ft.addToBackStack(null);
			}
			ft.commit();

		} else {
			Log.e("MainActivity", "Error in creating fragment");
		}
	}

	public void setActionBarTitle(String title) {
		getActionBar().setTitle(title);
	}

	private void showChangePasswordDialog() {
		// TODO Auto-generated method stub
		AlertDialog.Builder alert = new AlertDialog.Builder(this);

		alert.setTitle("Change Your Default Password");

		// Set an EditText view to get user input
		View v;
		LayoutInflater vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		v = vi.inflate(R.layout.change_password, null);
		EditText et_old_password = (EditText) v.findViewById(R.id.et_old_password);
		EditText et_new_password = (EditText) v.findViewById(R.id.et_new_password);
		EditText et_new_conf_password = (EditText) v.findViewById(R.id.et_new_conf_password);
		alert.setView(v);

		alert.setPositiveButton("Submit", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {

				// Do something with value!
			}
		});

		alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				// Canceled.
			}
		});

		alert.show();
	}

	/**
	 * When using the ActionBarDrawerToggle, you must call it during
	 * onPostCreate() and onConfigurationChanged()...
	 */

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	@Override
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
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		int id = item.getItemId();
		if (id == R.id.action_cart) {
			displayView(8, true);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void onClick(View v) {

	}

	public void goHome() {
		displayView(1, false);
	}

	@Override
	public void goSellingFragmentItem(String getmSellingCategoryId, String title) {
		// TODO Auto-generated method stub
		selling_category_title = title;
		selling_category_id = getmSellingCategoryId;
		Constant.sSelling_category_id = getmSellingCategoryId;
		Constant.sSelling_category_title = title;
		displayView(4, true);
	}

	@Override
	public void goBuyingFragmentItem(String getmBuyingCategoryId, String getmBuyingCategoryName) {
		// TODO Auto-generated method stub
		buying_category_title = getmBuyingCategoryName;
		buying_category_id = getmBuyingCategoryId;
		displayView(5, true);
	}

	@Override
	public void goSellingFragmentItemDetail(String getmSellingSubCategoryId, String getmSellingSubCategoryName, String string, String getmSellingSubCategoryDesc, String string2) {
		// TODO Auto-generated method stub
		selling_each_item_id = getmSellingSubCategoryId;
		selling_each_item_title = getmSellingSubCategoryName;
		selling_each_item_image = string;
		selling_each_item_description = getmSellingSubCategoryDesc;
		selling_each_item_price = string2;
		displayView(6, true);
	}

	@Override
	public void goBuyingFragmentItemDetail(String getmBuyingSubCategoryId, String getmBuyingSubCategoryName, String getmBuyingSubCategoryImage, String getmBuyingSubCategoryPrice, String getmBuyingSubCategoryDescription, String user_id, String user_name) {
		// TODO Auto-generated method stub
		buying_each_item_id = getmBuyingSubCategoryId;
		buying_each_item_title = getmBuyingSubCategoryName;
		buying_each_item_image = getmBuyingSubCategoryImage;
		buying_each_item_price = getmBuyingSubCategoryPrice;
		buying_each_item_description = getmBuyingSubCategoryDescription;
		brand_user_id = user_id;
		brand_user_name = user_name;
		displayView(7, true);
	}

	@Override
	public void goCart() {
		// TODO Auto-generated method stub
		displayView(8, true);
	}

	class T extends Thread implements Runnable {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			super.run();

			new callMessage().execute();

		}
	}

	@Override
	public void getUnreadMessage() {
		// TODO Auto-generated method stub
		mHandle.postDelayed(new T(), 60 * 1000);
	}

	public class callMessage extends AsyncTask<String, Void, Boolean> {
		protected void onPreExecute() {
		}

		@Override
		protected Boolean doInBackground(String... params) {
			try {
				JSONObject jsonObjSend = new JSONObject();
				jsonObjSend.put("user_id", app.getUserinfo().ID);
				jsonObjSend.put("flag", "0");
				Log.e("SEND", jsonObjSend.toString());
				JSONObject json = FarmersFarmFresh2Home.SendHttpPost(Constant.URLS.GET_MESSAGE.getURL(), jsonObjSend);
				boolean status = json.getBoolean("status");

				if (status) {
					JSONArray arr = json.getJSONArray("message");
					for (int i = 0; i < arr.length(); i++) {
						JSONObject obj = arr.getJSONObject(i);
						mMessageBean.add(new MessageBean(obj.getString("user_id"), obj.getString("name"), obj.getString("message"), obj.getString("create_date"), obj.getString("opening_flag")));
						if (obj.getString("opening_flag").equals("N")) {
							message_counter++;
						}
					}
				}

				return status;

			} catch (JSONException e) {
				e.printStackTrace();
				return false;

			}
		}

		protected void onPostExecute(Boolean status) {
			if (message_counter > 0) {
				tv_message.setText("Messages(" + message_counter + ")");
			}
			getUnreadMessage();
		}
	}

	public void onEvent(EventGuestCheckout event) {

		Intent intent = new Intent(DashBoardActivity.this, LoginActivity.class);
		startActivity(intent);
		// DashBoardActivity.this.finish();

	}

	public void onEvent(EventAddtoCart event) {

		displayView(8, true);

	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		EventBus.getDefault().unregister(this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(app.getUserinfo().isLoggedin);
	}
}
