package com.FarmersCart.Adapter;

import java.util.ArrayList;

import com.FarmersCart.Bean.SellingCategoryBean;
import com.FarmersCart.Interface.IBase;
import com.FarmersCart.UI.R;
import com.FarmersCart.Util.ImageLoader;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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

public class SellingAdapter extends ArrayAdapter<SellingCategoryBean>{
	
	private ArrayList<SellingCategoryBean> arr= new ArrayList<SellingCategoryBean>();
	private ViewHolder mHolder;
	private ImageLoader imageLoader;
	private Activity activity;	
	private IBase iBase ;
	
	public SellingAdapter(Activity activity, int textViewResourceId, ArrayList<SellingCategoryBean> items) {
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
		
	
		
		imageLoader.DisplayImage(arr.get(position).getmSellingCategoryImage(), R.drawable.loader, mHolder.each_image);
	
		mHolder.tv_each_item.setText(arr.get(position).getmSellingCategoryName());
		
		mHolder.rl_full.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				mHolder.rl_full.performHapticFeedback(HapticFeedbackConstants.VIRTUAL_KEY);
				iBase.goSellingFragmentItem(arr.get(position).getmSellingCategoryId(),arr.get(position).getmSellingCategoryName());
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
