package com.FarmersCart.Adapter;

import java.util.ArrayList;

import com.FarmersCart.Bean.BuyingHistory;
import com.FarmersCart.Bean.SellingHistory;
import com.FarmersCart.Interface.IBase;
import com.FarmersCart.UI.R;
import com.FarmersCart.Util.ImageLoader;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BuyingHistoryAdapter extends ArrayAdapter<BuyingHistory>{
	
	private ArrayList<BuyingHistory> arr= new ArrayList<BuyingHistory>();
	private ViewHolder mHolder;
	private ImageLoader imageLoader;
	private Activity activity;	
	private IBase iBase ;
	
	public BuyingHistoryAdapter(Activity activity, int textViewResourceId, ArrayList<BuyingHistory> items) {
		super(activity, textViewResourceId,items);
		// TODO Auto-generated constructor stub
		this.activity=activity;
		this.arr = items;
		imageLoader = new ImageLoader(activity);
		iBase = (IBase) activity;
	}
	@SuppressLint("NewApi")
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if(v == null){
			LayoutInflater vi = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v=vi.inflate(R.layout.each_buy_row, null);
			mHolder=new ViewHolder();
			mHolder.iv_sell_image=(ImageView)v.findViewById(R.id.iv_sell_image);
			mHolder.tv_category=(TextView)v.findViewById(R.id.tv_category);
			mHolder.tv_sub_category=(TextView)v.findViewById(R.id.tv_sub_category);
			mHolder.tv_price=(TextView)v.findViewById(R.id.tv_price);
			mHolder.tv_quantity=(TextView)v.findViewById(R.id.tv_quantity);
			mHolder.tv_total_price=(TextView)v.findViewById(R.id.tv_total_price);
			mHolder.tv_delivery_type=(TextView)v.findViewById(R.id.tv_delivery_type);
			mHolder.tv_brand_name=(TextView)v.findViewById(R.id.tv_brand_nname);
			
			v.setTag(mHolder);
			
		}else{
			mHolder=(ViewHolder)v.getTag();
		}
		
	
		
		imageLoader.DisplayImage(arr.get(position).getmBuyingSubCategoryImage(), R.drawable.loader, mHolder.iv_sell_image);
	
		mHolder.tv_category.setText(activity.getResources().getString(R.string.category)+arr.get(position).getmBuyingCategoryName());
		mHolder.tv_sub_category.setText(activity.getResources().getString(R.string.sub_category)+arr.get(position).getmBuyingSubCategoryName());
		mHolder.tv_price.setText(activity.getResources().getString(R.string.price)+arr.get(position).getmBuyingSubCategoryPrice());
		mHolder.tv_quantity.setText(activity.getResources().getString(R.string.total_quantity)+arr.get(position).getmBuyingProductQuantity());
		mHolder.tv_total_price.setText(activity.getResources().getString(R.string.total_price)+arr.get(position).getmBuyingTotalPrice());
		mHolder.tv_delivery_type.setText(activity.getResources().getString(R.string.brand)+arr.get(position).getmBuyingBrandName());
		mHolder.tv_brand_name.setText(activity.getResources().getString(R.string.delivery_type)+arr.get(position).getmBuyingProductDeliveryType());
		
		
		return v;
	}
	
	public class ViewHolder {	
		public ImageView iv_sell_image;
		public TextView tv_category,tv_sub_category,tv_price,tv_quantity,tv_total_price,tv_delivery_type,tv_brand_name ;
	}

}
