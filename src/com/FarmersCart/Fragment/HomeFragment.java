package com.FarmersCart.Fragment;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.FarmersCart.Adapter.BuyingHistoryAdapter;
import com.FarmersCart.Adapter.SellingHistoryAdapter;
import com.FarmersCart.Bean.BuyingHistory;
import com.FarmersCart.Bean.SellingHistory;
import com.FarmersCart.Constant.Constant;
import com.FarmersCart.Interface.IBase;
import com.FarmersCart.Network.FarmersFarmFresh2Home;
import com.FarmersCart.UI.BaseActivity;
import com.FarmersCart.UI.R;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class HomeFragment extends Fragment implements OnClickListener {
	private BaseActivity base;
	private TextView tv_buy,tv_sell ,tv_nothing_found;
	private ListView lv_buy_history,lv_sell_history ;
	private ArrayList<SellingHistory> mSellingHistory = new ArrayList<SellingHistory>() ;
	private ArrayList<BuyingHistory> mBuyingHistory= new ArrayList<BuyingHistory>() ;
	
	private ArrayList<SellingHistory> mSellingHistorySearch = new ArrayList<SellingHistory>() ;
	private ArrayList<BuyingHistory> mBuyingHistorySearch= new ArrayList<BuyingHistory>() ;
	private IBase iBase ;
	private EditText etSearch;
	private int i = 1; // 1->buying      2->selling
	
	
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
			etSearch = (EditText)v.findViewById(R.id.etSearch);
			
			tv_buy.setOnClickListener(this);
			tv_sell.setOnClickListener(this);
			
			new callTransaction().execute();
			
			
			etSearch.addTextChangedListener(new TextWatcher() {
				
				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count) {
					
					if(i==1){
						mBuyingHistorySearch.clear();
						if(mBuyingHistory.size()>0){
							for(int i=0; i<mBuyingHistory.size(); i++){
								if(mBuyingHistory.get(i).getmBuyingCategoryName().toLowerCase().contains(s.toString().toLowerCase())){
									mBuyingHistorySearch.add(mBuyingHistory.get(i));
								}else if(mBuyingHistory.get(i).getmBuyingBrandName().toLowerCase().contains(s.toString().toLowerCase())){
									mBuyingHistorySearch.add(mBuyingHistory.get(i));
								}
							}
							lv_buy_history.setAdapter(new BuyingHistoryAdapter(getActivity(), R.layout.each_buy_row, mBuyingHistorySearch));	
							
						}
					}else{

						mSellingHistorySearch.clear();
						if(mSellingHistory.size()>0){
							for(int i=0; i<mSellingHistory.size(); i++){
								if(mSellingHistory.get(i).getmSellingCategoryName().toLowerCase().contains(s.toString().toLowerCase())){
									mSellingHistorySearch.add(mSellingHistory.get(i));
								}else if(mSellingHistory.get(i).getmSellingSubCategoryName().toLowerCase().contains(s.toString().toLowerCase())){
									mSellingHistorySearch.add(mSellingHistory.get(i));
								}
							}
							lv_buy_history.setAdapter(new BuyingHistoryAdapter(getActivity(), R.layout.each_buy_row, mBuyingHistorySearch));	
							
						}
					
					}
				}
				
				@Override
				public void beforeTextChanged(CharSequence s, int start, int count, int after) {
					
					
				}
				
				@Override
				public void afterTextChanged(Editable s) {
					
					
				}
			});
			
		    return v ;	
		}

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(arg0 == tv_buy){
				etSearch.setText("");
				i = 1;
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
				}else{
					if(mBuyingHistory.size()>0){
						lv_buy_history.setAdapter(new BuyingHistoryAdapter(getActivity(), R.layout.each_buy_row, mBuyingHistory));
					}
				}
			}
			if(arg0 == tv_sell){
				etSearch.setText("");
				i = 2;
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
				}else{
					if(mSellingHistory.size()>0){
						lv_sell_history.setAdapter(new SellingHistoryAdapter(getActivity(), R.layout.each_selling_row, mSellingHistory));
					}
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
							System.out.println("!!!!!!!  "+ obj.getString("transaction_id"));
							
							mBuyingHistory.add(new BuyingHistory(obj.getString("id"),obj.getString("category_name"), obj.getString("subcategory_name"), obj.getString("subcategory_id"), obj.getString("subcategory_image"),obj.getString("brand_id"),obj.getString("brand_name"), obj.getString("subcategory_price"), obj.getString("quantity"),
									obj.getString("total_cost"), obj.getString("delivery_type"), obj.getString("transaction_id"), obj.getString("status")));
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
