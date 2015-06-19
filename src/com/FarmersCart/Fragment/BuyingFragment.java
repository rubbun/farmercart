package com.FarmersCart.Fragment;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.FarmersCart.Adapter.BuyingAdapter;
import com.FarmersCart.Adapter.SellingAdapter;
import com.FarmersCart.Bean.BuyingCategoryBean;
import com.FarmersCart.Bean.SellingCategoryBean;
import com.FarmersCart.Constant.Constant;
import com.FarmersCart.Fragment.SellingFragment.callSelling;
import com.FarmersCart.Interface.IBase;
import com.FarmersCart.Network.FarmersFarmFresh2Home;
import com.FarmersCart.UI.BaseActivity;
import com.FarmersCart.UI.DashBoardActivity;
import com.FarmersCart.UI.R;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.TextView;

public class BuyingFragment extends Fragment {
	private BaseActivity base;
	private DashBoardActivity mDashBoard;
	private GridView gv_selling ;
	private ArrayList<BuyingCategoryBean> mBuyingCategoryBean = new ArrayList<BuyingCategoryBean>();
	private TextView tv_no_buying ;
	public BuyingFragment(BaseActivity base){
		this.base  = base;	
		this.mDashBoard = (DashBoardActivity) base ;
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			
			View v = inflater.inflate(R.layout.fragment_buying, container, false);
			gv_selling = (GridView)v.findViewById(R.id.gv_buying);
			tv_no_buying = (TextView)v.findViewById(R.id.tv_no_buying);
			mDashBoard.setActionBarTitle("Buying");
			new callBuying().execute();
		    return v ;	
		}
		
		
		public class callBuying extends AsyncTask<String, Void, Boolean>{
			protected void onPreExecute() {
				base.showProgressDailog("Please wait...");
			}
			
			@Override
			protected Boolean doInBackground(String... params) {
			  	try {
					mBuyingCategoryBean.clear();
			  		JSONObject jsonObjSend = new JSONObject();
					Log.e("SEND", jsonObjSend.toString());
					JSONObject json = FarmersFarmFresh2Home.SendHttpPost(Constant.URLS.GET_BUYING_CATEGORY.getURL(),jsonObjSend);
					boolean status=json.getBoolean("status");
					
					if(status){
						JSONArray arr = json.getJSONArray("buying_category");
						for(int i = 0 ; i<arr.length();i++){
							JSONObject obj = arr.getJSONObject(i);
							mBuyingCategoryBean.add(new BuyingCategoryBean(obj.getString("buying_category_id"), obj.getString("buying_category_name"), obj.getString("buying_category_image")));
						}
					}
					
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
					tv_no_buying.setVisibility(View.GONE);
					gv_selling.setVisibility(View.VISIBLE);
					gv_selling.setAdapter(new BuyingAdapter(getActivity(), R.layout.each_grid, mBuyingCategoryBean));
				}else{
					tv_no_buying.setVisibility(View.VISIBLE);
					gv_selling.setVisibility(View.GONE);
				}
				
			}
		}
}
