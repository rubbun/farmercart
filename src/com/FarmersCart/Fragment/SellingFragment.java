package com.FarmersCart.Fragment;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.FarmersCart.Adapter.SellingAdapter;
import com.FarmersCart.Bean.SellingCategoryBean;
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
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class SellingFragment extends Fragment {
	private BaseActivity base;
	private DashBoardActivity mDashBoard;
	private GridView gv_selling ;
	private ArrayList<SellingCategoryBean> mSellingCategoryBean = new ArrayList<SellingCategoryBean>();
	private TextView tv_no_selling ;
	public SellingFragment(BaseActivity base){
		this.base  = base;		
		this.mDashBoard = (DashBoardActivity) base ;
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			
			View v = inflater.inflate(R.layout.fragment_selling, container, false);
			gv_selling = (GridView)v.findViewById(R.id.gv_selling);
			tv_no_selling = (TextView)v.findViewById(R.id.tv_no_selling);
			mDashBoard.setActionBarTitle("Selling");
			new callSelling().execute();
		    return v ;	
		}
		
		
		public class callSelling extends AsyncTask<String, Void, Boolean>{
			protected void onPreExecute() {
				base.showProgressDailog("Please wait...");
			}
			
			@Override
			protected Boolean doInBackground(String... params) {
			  	try {
			  		mSellingCategoryBean.clear();
			  		JSONObject jsonObjSend = new JSONObject();
					Log.e("SEND", jsonObjSend.toString());
					JSONObject json = FarmersFarmFresh2Home.SendHttpPost(Constant.URLS.GET_SELLING_CATEGORY.getURL(),jsonObjSend);
					boolean status=json.getBoolean("status");
					
					if(status){
						JSONArray arr = json.getJSONArray("selling_category");
						for(int i = 0 ; i<arr.length();i++){
							JSONObject obj = arr.getJSONObject(i);
							mSellingCategoryBean.add(new SellingCategoryBean(obj.getString("selling_category_id"), obj.getString("selling_category_name"), obj.getString("selling_category_image")));
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
					tv_no_selling.setVisibility(View.GONE);
					gv_selling.setVisibility(View.VISIBLE);
					gv_selling.setAdapter(new SellingAdapter(getActivity(), R.layout.each_grid, mSellingCategoryBean));
				}else{
					tv_no_selling.setVisibility(View.VISIBLE);
					gv_selling.setVisibility(View.GONE);
				}
				
			}
		}
}
