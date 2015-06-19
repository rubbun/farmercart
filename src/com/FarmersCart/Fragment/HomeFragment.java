package com.FarmersCart.Fragment;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.FarmersCart.Adapter.BuyingHistoryAdapter;
import com.FarmersCart.Adapter.SellingHistoryAdapter;
import com.FarmersCart.Adapter.SellingItemAdapter;
import com.FarmersCart.Bean.BuyingHistory;
import com.FarmersCart.Bean.SellingHistory;
import com.FarmersCart.Bean.SellingSubCategoryBean;
import com.FarmersCart.Constant.Constant;
import com.FarmersCart.Interface.IBase;
import com.FarmersCart.Network.FarmersFarmFresh2Home;
import com.FarmersCart.UI.BaseActivity;
import com.FarmersCart.UI.R;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class HomeFragment extends Fragment implements OnClickListener {
	private BaseActivity base;
	private TextView tv_buy,tv_sell ,tv_nothing_found;
	private ListView lv_buy_history,lv_sell_history ;
	private ArrayList<SellingHistory> mSellingHistory = new ArrayList<SellingHistory>() ;
	private ArrayList<BuyingHistory> mBuyingHistory= new ArrayList<BuyingHistory>() ;
	private IBase iBase ;
	public HomeFragment(BaseActivity base){
		this.base  = base;	
		this.iBase = (IBase) base;
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			
			View v = inflater.inflate(R.layout.fragment_home, container, false);
			
			tv_buy = (TextView)v.findViewById(R.id.tv_buy);
			tv_sell = (TextView)v.findViewById(R.id.tv_sell);
			tv_nothing_found = (TextView)v.findViewById(R.id.tv_nothing_found);
			
			lv_buy_history = (ListView)v.findViewById(R.id.lv_buy_history);
			lv_sell_history = (ListView)v.findViewById(R.id.lv_sell_history);
			
			tv_buy.setOnClickListener(this);
			tv_sell.setOnClickListener(this);
			
			new callTransaction().execute();
			
		    return v ;	
		}

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(arg0 == tv_buy){
				tv_buy.setBackgroundColor(Color.parseColor("#00a5e2"));
				tv_sell.setBackgroundColor(Color.parseColor("#ffffff"));
				tv_buy.setTextColor(Color.parseColor("#ffffff"));
				tv_sell.setTextColor(Color.parseColor("#000000"));
				lv_buy_history.setVisibility(View.VISIBLE);
				lv_sell_history.setVisibility(View.GONE);
				if(mBuyingHistory.size()==0){
					lv_buy_history.setVisibility(View.GONE);
					tv_nothing_found.setVisibility(View.VISIBLE);
					tv_nothing_found.setText("No Buying Prodct Found");
				}
			}
			if(arg0 == tv_sell){
				tv_sell.setBackgroundColor(Color.parseColor("#00a5e2"));
				tv_buy.setBackgroundColor(Color.parseColor("#ffffff"));
				tv_sell.setTextColor(Color.parseColor("#ffffff"));
				tv_buy.setTextColor(Color.parseColor("#000000"));
				lv_sell_history.setVisibility(View.VISIBLE);
				lv_buy_history.setVisibility(View.GONE);
				if(mSellingHistory.size()==0){
					lv_sell_history.setVisibility(View.GONE);
					tv_nothing_found.setVisibility(View.VISIBLE);
					tv_nothing_found.setText("No Selling Prodct Found");
				}
			}
		}
		
		
		
		public class callTransaction extends AsyncTask<String, Void, Boolean>{
			protected void onPreExecute() {
				base.showProgressDailog("Please wait...");
			}
			
			@Override
			protected Boolean doInBackground(String... params) {
			  	try {
			  		mBuyingHistory.clear();
			  		mSellingHistory.clear();
			  		JSONObject jsonObjSend = new JSONObject();
			  		jsonObjSend.put("user_id", base.app.getUserinfo().ID);
					Log.e("SEND", jsonObjSend.toString());
					JSONObject json_selling = FarmersFarmFresh2Home.SendHttpPost(Constant.URLS.SELLING_TRANSACTION.getURL(),jsonObjSend);
					boolean status_selling=json_selling.getBoolean("status");
					
					if(status_selling){
						JSONArray arr = json_selling.getJSONArray("selling_product");
						for(int i = 0 ; i<arr.length();i++){
							JSONObject obj = arr.getJSONObject(i);
							
							mSellingHistory.add(new SellingHistory(obj.getString("category_name"), obj.getString("subcategory_name"), obj.getString("subcategory_id"), obj.getString("subcategory_image"), obj.getString("subcategory_price"), obj.getString("quantity"),
									obj.getString("total_cost"), obj.getString("delivery_type")));
						}
					}
					
					JSONObject json_buying = FarmersFarmFresh2Home.SendHttpPost(Constant.URLS.BUYING_TRANSACTION.getURL(),jsonObjSend);
					boolean status_buying=json_buying.getBoolean("status");
					
					if(status_buying){
						JSONArray arr = json_buying.getJSONArray("buying_product");
						for(int i = 0 ; i<arr.length();i++){
							JSONObject obj = arr.getJSONObject(i);
							
							mBuyingHistory.add(new BuyingHistory(obj.getString("id"),obj.getString("category_name"), obj.getString("subcategory_name"), obj.getString("subcategory_id"), obj.getString("subcategory_image"),obj.getString("brand_id"),obj.getString("brand_name"), obj.getString("subcategory_price"), obj.getString("quantity"),
									obj.getString("total_cost"), obj.getString("delivery_type")));
						}
					}
					
					return status_selling;
						
					
				} catch (JSONException e) {
					e.printStackTrace(); 
					base.dismissProgressDialog();
					return false;
					
				}			
			}
			protected void onPostExecute(Boolean status) {	
				base.dismissProgressDialog();
				if(mSellingHistory.size()>0){
					lv_sell_history.setAdapter(new SellingHistoryAdapter(getActivity(), R.layout.each_selling_row, mSellingHistory));
				}
				
				if(mBuyingHistory.size()>0){
					lv_buy_history.setAdapter(new BuyingHistoryAdapter(getActivity(), R.layout.each_buy_row, mBuyingHistory));
				}
				
				iBase.getUnreadMessage();
			}
		}
}
