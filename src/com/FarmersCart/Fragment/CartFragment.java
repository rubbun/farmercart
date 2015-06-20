package com.FarmersCart.Fragment;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.FarmersCart.Adapter.CartAdapter;
import com.FarmersCart.Constant.Constant;
import com.FarmersCart.Events.EventGuestCheckout;
import com.FarmersCart.Interface.IBase;
import com.FarmersCart.Network.FarmersFarmFresh2Home;
import com.FarmersCart.UI.BaseActivity;
import com.FarmersCart.UI.R;

import de.greenrobot.event.EventBus;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ValidFragment")
public class CartFragment extends Fragment implements OnClickListener {

	private BaseActivity base;
	private TextView tv_no_cart;
	private ListView lv_cart;
	private Button btn_checkout;
	private JSONObject checkoutValues;
	private IBase iBase ;
	@SuppressLint("ValidFragment")
	public CartFragment(BaseActivity base) {
		this.base = base;
		iBase =  (IBase) base ;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment_cart, container, false);
		
		checkoutValues = new JSONObject();

		tv_no_cart = (TextView) v.findViewById(R.id.tv_no_cart);
		lv_cart = (ListView) v.findViewById(R.id.lv_cart);
		btn_checkout = (Button) v.findViewById(R.id.btn_checkout);

		if (Constant.sCartBean.size() == 0) {
			lv_cart.setVisibility(View.GONE);
			tv_no_cart.setVisibility(View.VISIBLE);
			btn_checkout.setVisibility(View.GONE);
		} else {
			lv_cart.setVisibility(View.VISIBLE);
			tv_no_cart.setVisibility(View.GONE);
			btn_checkout.setVisibility(View.VISIBLE);
			lv_cart.setAdapter(new CartAdapter(getActivity(),
					R.layout.each_cart_row, Constant.sCartBean));
		}
		
		btn_checkout.setOnClickListener(this);
		
		return v;

	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		if (arg0 == btn_checkout) {
			DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					switch (which) {
					case DialogInterface.BUTTON_POSITIVE:
						// Yes button clicked
						if(base.app.getUserinfo().isLoggedin){
							callCheckout();
						}else{
							EventBus.getDefault().post(new EventGuestCheckout());
						}
						
						break;

					case DialogInterface.BUTTON_NEGATIVE:
						// No button clicked
						break;
					}
				}

			};

			AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
			builder.setMessage("Are you sure to checkout?")
					.setPositiveButton("Yes", dialogClickListener)
					.setNegativeButton("No", dialogClickListener).show();
		}
	}

	private void callCheckout() {
		// TODO Auto-generated method stub
		try {
			

			JSONArray checkoutArray = new JSONArray();

			for (int i = 0; i < Constant.sCartBean.size(); i++) {
				JSONObject list = new JSONObject();

				list.put("buying_user_id", base.app.getUserinfo().ID);
				list.put("buying_user_name", base.app.getUserinfo().firstName+ " "+base.app.getUserinfo().lastName);
				list.put("selling_user_id", Constant.sCartBean.get(i)
						.getmUserId());
				list.put("selling_subcategory_id", Constant.sCartBean.get(i)
						.getmProductId());
				list.put("selling_subcategory_name", Constant.sCartBean.get(i)
						.getmProductName());
				list.put("quantity", Constant.sCartBean.get(i).getmQuantity());
				list.put("total_cost", Constant.sCartBean.get(i)
						.getmTotalCost());
				list.put("delivery_type", Constant.sCartBean.get(i)
						.getmDeliveryType());

				checkoutArray.put(list);
			}

			checkoutValues.put("schedule", checkoutArray);
			new checkout().execute(checkoutValues);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	

	}
	
	/*private void callCheckoutWithoutLogin() {
		// TODO Auto-generated method stub
		try {
			

			JSONArray checkoutArray = new JSONArray();

			for (int i = 0; i < Constant.sCartBean.size(); i++) {
				JSONObject list = new JSONObject();

				list.put("buying_user_id", base.app.getUserinfo().ID);
				//list.put("buying_user_name", base.app.getUserinfo().firstName+ " "+base.app.getUserinfo().lastName);
				list.put("selling_user_id", Constant.sCartBean.get(i)
						.getmUserId());
				list.put("selling_subcategory_id", Constant.sCartBean.get(i)
						.getmProductId());
				list.put("selling_subcategory_name", Constant.sCartBean.get(i)
						.getmProductName());
				list.put("quantity", Constant.sCartBean.get(i).getmQuantity());
				list.put("total_cost", Constant.sCartBean.get(i)
						.getmTotalCost());
				list.put("delivery_type", Constant.sCartBean.get(i)
						.getmDeliveryType());

				checkoutArray.put(list);
			}

			checkoutValues.put("schedule", checkoutArray);
			new checkout().execute(checkoutValues);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	

	}*/
	
	public class checkout extends AsyncTask<JSONObject, Void, Boolean>{
		protected void onPreExecute() {
			base.showProgressDailog("Please wait...");
		}
		
		@Override
		protected Boolean doInBackground(JSONObject... params) {
		  	try {
		  		JSONObject jsonObjSend = new JSONObject();	
		  		jsonObjSend.put("checkout_array", params[0]);
		  		
				Log.e("SEND", jsonObjSend.toString());
				
				JSONObject json = FarmersFarmFresh2Home.SendHttpPost(Constant.URLS.CHECKOUT.getURL(),jsonObjSend);
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
				Constant.sCartBean.clear();
				Toast.makeText(getActivity(),"Payment Successfull", 
		                Toast.LENGTH_SHORT).show();
				iBase.goHome();
			}else{
				Toast.makeText(getActivity(),"Some internal error occour, Please try after sometime.", 
		                Toast.LENGTH_SHORT).show();
			}
			
		}
	}

}
