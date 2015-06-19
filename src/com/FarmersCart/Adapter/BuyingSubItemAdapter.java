package com.FarmersCart.Adapter;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.view.HapticFeedbackConstants;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.FarmersCart.Bean.BrandBean;
import com.FarmersCart.Bean.BuyingSubCategoryBean;
import com.FarmersCart.Interface.IBase;
import com.FarmersCart.UI.BaseActivity;
import com.FarmersCart.UI.R;
import com.FarmersCart.Util.ImageLoader;

public class BuyingSubItemAdapter extends ArrayAdapter<BrandBean>{
	
	private BuyingSubCategoryBean arr;
	private ViewHolder mHolder;
	private ImageLoader imageLoader;
	private Activity activity;	
	private IBase iBase ;
	public int mPosition=0 ;
	private BaseActivity base;
	ArrayList<BrandBean> brand_list;
	
	public BuyingSubItemAdapter(Activity activity, int textViewResourceId, BuyingSubCategoryBean buyingSubCategoryBean, ArrayList<BrandBean> brand_list) {
		super(activity, textViewResourceId,brand_list);
		// TODO Auto-generated constructor stub
		this.activity=activity;
		this.arr = buyingSubCategoryBean;
		this.brand_list = brand_list;
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
		
		imageLoader.DisplayImage(arr.getmBuyingSubCategoryImage(), R.drawable.loader, mHolder.each_image);
	
		mHolder.tv_each_item.setText(brand_list.get(position).getmUserName());
		
		mHolder.rl_full.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mHolder.rl_full.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
				mPosition = position;
				iBase.goBuyingFragmentItemDetail(arr.getmBuyingSubCategoryId(),arr.getmBuyingSubCategoryName(),
						arr.getmBuyingSubCategoryImage(),arr.getmBuyingSubCategoryPrice(),arr.getmBuyingSubCategoryDescription(),brand_list.get(position).getmUserId(),brand_list.get(position).getmUserName());
				//new callBrand().execute(arr.get(position).getmBuyingSubCategoryId());
			}
		});
		
		return v;
	}
	
	public class ViewHolder {	
		public RelativeLayout rl_full;
		public ImageView each_image;
		public TextView tv_each_item;
	}
}
