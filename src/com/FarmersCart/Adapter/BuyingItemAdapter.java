package com.FarmersCart.Adapter;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.FarmersCart.Bean.BrandBean;
import com.FarmersCart.Bean.BuyingSubCategoryBean;
import com.FarmersCart.Bean.SellingCategoryBean;
import com.FarmersCart.Bean.SellingSubCategoryBean;
import com.FarmersCart.Constant.Constant;
import com.FarmersCart.Interface.IBase;
import com.FarmersCart.Network.FarmersFarmFresh2Home;
import com.FarmersCart.UI.BaseActivity;
import com.FarmersCart.UI.R;
import com.FarmersCart.Util.ImageLoader;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.util.Log;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BuyingItemAdapter extends ArrayAdapter<BuyingSubCategoryBean>{
	
	private ArrayList<BuyingSubCategoryBean> arr= new ArrayList<BuyingSubCategoryBean>();
	private ArrayList<BrandBean> brand_list= new ArrayList<BrandBean>();
	private ArrayList<String> brand_name_list= new ArrayList<String>();
	private ViewHolder mHolder;
	private ImageLoader imageLoader;
	private Activity activity;	
	private IBase iBase ;
	public ProgressDialog prsDlg;
	public int mPosition=0 ;
	private BaseActivity base;
	
	
	public BuyingItemAdapter(Activity activity, int textViewResourceId, ArrayList<BuyingSubCategoryBean> items) {
		super(activity, textViewResourceId,items);
		// TODO Auto-generated constructor stub
		this.activity=activity;
		this.arr = items;
		imageLoader = new ImageLoader(activity);
		iBase = (IBase) activity;
		this.base = (BaseActivity) activity;
	}
	@SuppressLint("NewApi")
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if(v == null){
			LayoutInflater vi = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v=vi.inflate(R.layout.each_grid, null);
			mHolder=new ViewHolder();
			mHolder.each_image=(ImageView)v.findViewById(R.id.each_image);
			mHolder.tv_each_item=(TextView)v.findViewById(R.id.tv_each_item);
			mHolder.tv_each_item=(TextView)v.findViewById(R.id.tv_each_item);
			mHolder.rl_full=(RelativeLayout)v.findViewById(R.id.rl_full);
			v.setTag(mHolder);
			
		}else{
			mHolder=(ViewHolder)v.getTag();
		}
		
	
		
		imageLoader.DisplayImage(arr.get(position).getmBuyingSubCategoryImage(), R.drawable.loader, mHolder.each_image);
	
		mHolder.tv_each_item.setText(arr.get(position).getmBuyingSubCategoryName());
		
		/*mHolder.rl_full.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mHolder.rl_full.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
				mPosition = position;
//				iBase.goBuyingFragmentItemDetail(arr.get(position).getmBuyingSubCategoryId(),arr.get(position).getmBuyingSubCategoryName(),
//						arr.get(position).getmBuyingSubCategoryImage(),arr.get(position).getmBuyingSubCategoryPrice());
				//new callBrand().execute(arr.get(position).getmBuyingSubCategoryId());
			}
		});
		*/
		return v;
	}
	
	public class ViewHolder {	
		public RelativeLayout rl_full;
		public ImageView each_image;
		public TextView tv_each_item;
	}
	
	
	/*public class callBrand extends AsyncTask<String, Void, Boolean>{
		protected void onPreExecute() {
			showProgressDailog("Please wait...");
		}
		
		@Override
		protected Boolean doInBackground(String... params) {
		  	try {
		  		brand_list.clear();
		  		brand_name_list.clear();
		  		JSONObject jsonObjSend = new JSONObject();
		  		jsonObjSend.put("selling_subcategory_id", params[0]);
		  		jsonObjSend.put("user_id", base.app.getUserinfo().ID);
				Log.e("SEND", jsonObjSend.toString());
				JSONObject json = FarmersFarmFresh2Home.SendHttpPost(Constant.URLS.GET_BRAND.getURL(),jsonObjSend);
				boolean status=json.getBoolean("status");
				
				if(status){
					JSONArray arr = json.getJSONArray("brand_list");
					for(int i = 0 ; i<arr.length();i++){
						JSONObject obj = arr.getJSONObject(i);
						brand_list.add(new BrandBean(obj.getString("user_id"), obj.getString("name")));
						brand_name_list.add(obj.getString("name"));
					}
				}
				
				return status;
					
				
			} catch (JSONException e) {
				e.printStackTrace(); 
				dismissProgressDialog();
				return false;
				
			}			
		}
		protected void onPostExecute(Boolean status) {	
			dismissProgressDialog();
			if(status){
		        final CharSequence[] items = brand_name_list.toArray(new CharSequence[brand_name_list.size()]);
		        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
		        builder.setTitle("Choose your Brand");
		       
		        builder.setItems(items, new DialogInterface.OnClickListener() {
		            public void onClick(DialogInterface dialog, int item) {
		                // Do something with the selection
		                //mDoneButton.setText(items[item]);
		            	iBase.goBuyingFragmentItemDetail(arr.get(mPosition).getmBuyingSubCategoryId(),arr.get(mPosition).getmBuyingSubCategoryName(),
							arr.get(mPosition).getmBuyingSubCategoryImage(),arr.get(mPosition).getmBuyingSubCategoryPrice(),arr.get(mPosition).getmBuyingSubCategoryDescription(),brand_list.get(item).getmUserId(),brand_list.get(item).getmUserName());
		            }
		        });
		        AlertDialog alert = builder.create();
		        alert.show();
			}
		}
	}*/
	
	public void showProgressDailog(String msg) {
		prsDlg = new ProgressDialog(activity);
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

}
