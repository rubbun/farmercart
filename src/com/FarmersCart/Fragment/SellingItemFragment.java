package com.FarmersCart.Fragment;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.FarmersCart.Adapter.SellingAdapter;
import com.FarmersCart.Adapter.SellingItemAdapter;
import com.FarmersCart.Bean.SellingCategoryBean;
import com.FarmersCart.Bean.SellingSubCategoryBean;
import com.FarmersCart.Constant.Constant;
import com.FarmersCart.Fragment.SellingFragment.callSelling;
import com.FarmersCart.Interface.IBase;
import com.FarmersCart.Network.FarmersFarmFresh2Home;
import com.FarmersCart.UI.BaseActivity;
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

public class SellingItemFragment extends Fragment {
	private BaseActivity base;
	private Bundle bundle ;
	private String selling_category_id = "" ;
	
	private GridView gv_selling_item ;
	private ArrayList<SellingSubCategoryBean> mSellingSubCategoryBean = new ArrayList<SellingSubCategoryBean>();
	private TextView tv_no_selling_item ;
	
	public SellingItemFragment(BaseActivity base){
		this.base  = base;	
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			
			View v = inflater.inflate(R.layout.fragment_selling_item, container, false);
			
			gv_selling_item = (GridView)v.findViewById(R.id.gv_selling_item);
			tv_no_selling_item = (TextView)v.findViewById(R.id.tv_no_selling_item);
			
			bundle = getArguments();
			
			selling_category_id = bundle.getString("selling_category_id");
			
			Log.e("selling_category_id",selling_category_id);
			
			new callSellingItem().execute(selling_category_id);
			
		    return v ;	
		}
		
		
		public class callSellingItem extends AsyncTask<String, Void, Boolean>{
			protected void onPreExecute() {
				base.showProgressDailog("Please wait...");
			}
			
			@Override
			protected Boolean doInBackground(String... params) {
			  	try {
			  		mSellingSubCategoryBean.clear();
			  		JSONObject jsonObjSend = new JSONObject();
			  		jsonObjSend.put("selling_id", params[0]);
					Log.e("SEND", jsonObjSend.toString());
					JSONObject json = FarmersFarmFresh2Home.SendHttpPost(Constant.URLS.GET_SELLING_SUBCATEGORY.getURL(),jsonObjSend);
					boolean status=json.getBoolean("status");
					
					if(status){
						JSONArray arr = json.getJSONArray("selling_subcategory");
						for(int i = 0 ; i<arr.length();i++){
							JSONObject obj = arr.getJSONObject(i);
							mSellingSubCategoryBean.add(new SellingSubCategoryBean(obj.getString("selling_subcategory_id"), obj.getString("selling_subcategory_name"), obj.getString("selling_subcategory_image"), obj.getString("selling_subcategory_description"),obj.getString("selling_subcategory_price")));
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
					tv_no_selling_item.setVisibility(View.GONE);
					gv_selling_item.setVisibility(View.VISIBLE);
					gv_selling_item.setAdapter(new SellingItemAdapter(getActivity(), R.layout.each_grid, mSellingSubCategoryBean));
				}else{
					tv_no_selling_item.setVisibility(View.VISIBLE);
					gv_selling_item.setVisibility(View.GONE);
				}
				
			}
		}
}
