package com.FarmersCart.Adapter;

import java.util.ArrayList;

import com.FarmersCart.Bean.BuyingCategoryBean;
import com.FarmersCart.Bean.CartBean;
import com.FarmersCart.Bean.SellingCategoryBean;
import com.FarmersCart.Constant.Constant;
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

public class CartAdapter extends ArrayAdapter<CartBean>{
	
	private ArrayList<CartBean> arr= new ArrayList<CartBean>();
	private ViewHolder mHolder;
	private ImageLoader imageLoader;
	private Activity activity;	
	private IBase iBase ;
	
	public CartAdapter(Activity activity, int textViewResourceId, ArrayList<CartBean> items) {
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
			v=vi.inflate(R.layout.each_cart_row, null);
			mHolder=new ViewHolder();
			mHolder.iv_cart_image=(ImageView)v.findViewById(R.id.iv_cart_image);
			mHolder.iv_delete=(ImageView)v.findViewById(R.id.iv_delete);
			mHolder.tv_product_name=(TextView)v.findViewById(R.id.tv_product_name);
			mHolder.tv_product_cost=(TextView)v.findViewById(R.id.tv_product_cost);
			mHolder.tv_product_quantity=(TextView)v.findViewById(R.id.tv_product_quantity);
			mHolder.tv_total_cost=(TextView)v.findViewById(R.id.tv_total_cost);
			
			
			v.setTag(mHolder);
			
		}else{
			mHolder=(ViewHolder)v.getTag();
		}
		
	
		
		imageLoader.DisplayImage(arr.get(position).getmProductImage(), R.drawable.loader, mHolder.iv_cart_image);
	
		mHolder.tv_product_name.setText("Product Name : "+arr.get(position).getmProductName());
		mHolder.tv_product_cost.setText("Product Cost : "+arr.get(position).getmCost());
		mHolder.tv_product_quantity.setText("Quantity : "+arr.get(position).getmQuantity());
		mHolder.tv_total_cost.setText("Total Cost : "+arr.get(position).getmTotalCost());
		
		mHolder.iv_delete.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
				    @Override
				    public void onClick(DialogInterface dialog, int which) {
				        switch (which){
				        case DialogInterface.BUTTON_POSITIVE:
				            //Yes button clicked
				        	Constant.sCartBean.remove(position);
				        	iBase.goCart();
				            break;

				        case DialogInterface.BUTTON_NEGATIVE:
				            //No button clicked
				            break;
				        }
				    }
				};
				
				AlertDialog.Builder builder = new AlertDialog.Builder(activity);
				builder.setMessage("Are you want to delete this item?").setPositiveButton("Yes", dialogClickListener)
				    .setNegativeButton("No", dialogClickListener).show();
				
			}
		});
		
		
		return v;
	}
	
	public class ViewHolder {	
		public ImageView iv_cart_image,iv_delete;
		public TextView tv_product_name,tv_product_cost,tv_product_quantity,tv_total_cost;
	}

}
