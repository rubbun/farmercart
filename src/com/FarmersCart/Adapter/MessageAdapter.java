package com.FarmersCart.Adapter;

import java.util.ArrayList;

import com.FarmersCart.Bean.BuyingCategoryBean;
import com.FarmersCart.Bean.MessageBean;
import com.FarmersCart.Bean.SellingCategoryBean;
import com.FarmersCart.Fragment.MessageFragment;
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
import android.graphics.Typeface;
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

public class MessageAdapter extends ArrayAdapter<MessageBean>{
	
	private ArrayList<MessageBean> arr= new ArrayList<MessageBean>();
	private ViewHolder mHolder;
	private ImageLoader imageLoader;
	private Activity activity;	
	private IBase iBase ;
	private MessageFragment fragment;
	
	public MessageAdapter(Activity activity,MessageFragment fragment, int textViewResourceId, ArrayList<MessageBean> items) {
		super(activity, textViewResourceId,items);
		// TODO Auto-generated constructor stub
		this.activity=activity;
		this.arr = items;
		imageLoader = new ImageLoader(activity);
		iBase = (IBase) activity;
		this.fragment = fragment;
	}
	@SuppressLint("NewApi")
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View v = convertView;
		if(v == null){
			LayoutInflater vi = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v=vi.inflate(R.layout.each_message_row, null);
			mHolder=new ViewHolder();
			mHolder.tv_name=(TextView)v.findViewById(R.id.tv_name);
			mHolder.tv_time=(TextView)v.findViewById(R.id.tv_time);
			mHolder.tv_message=(TextView)v.findViewById(R.id.tv_message);
			mHolder.tv_phone=(TextView)v.findViewById(R.id.tv_phone);
			mHolder.tv_address=(TextView)v.findViewById(R.id.tv_address);
			mHolder.tv_status=(TextView)v.findViewById(R.id.tv_status);
			mHolder.tv_trans=(TextView)v.findViewById(R.id.tv_trans);
			mHolder.ll_status=(LinearLayout)v.findViewById(R.id.ll_status);
			mHolder.btnAccept=(Button)v.findViewById(R.id.btnAccept);
			mHolder.btnReject=(Button)v.findViewById(R.id.btnReject);
			v.setTag(mHolder);
			
		}else{
			mHolder=(ViewHolder)v.getTag();
		}
		
	
		
	
		mHolder.tv_name.setText("From : "+arr.get(position).getmUserName());		
		mHolder.tv_time.setText("Time : "+arr.get(position).getmTime());
		mHolder.tv_message.setText("Message : "+arr.get(position).getmMessage());
		mHolder.tv_phone.setText("Phone : "+arr.get(position).getPhone());
		mHolder.tv_address.setText("Address : "+arr.get(position).getAddress());
		mHolder.tv_trans.setText("Transaction Id : "+arr.get(position).getTransaction_id());
		
		if(arr.get(position).getmMessageOpenFlag().equals("N")){
			mHolder.tv_name.setTypeface(Typeface.DEFAULT_BOLD);
			mHolder.tv_time.setTypeface(Typeface.DEFAULT_BOLD);
			mHolder.tv_message.setTypeface(Typeface.DEFAULT_BOLD);
		}
		
		if(arr.get(position).getStatus().equalsIgnoreCase("0")){
			mHolder.tv_status.setText("STATUS: Waiting for your aprouval");
			mHolder.ll_status.setVisibility(View.VISIBLE);
		}else{
			mHolder.ll_status.setVisibility(View.GONE);
			if(arr.get(position).getStatus().equalsIgnoreCase("-1")){
				mHolder.tv_status.setText("STATUS: You have reject ");	
			}else if(arr.get(position).getStatus().equalsIgnoreCase("1")){
				mHolder.tv_status.setText("STATUS: You have Aprouved");
			}else{
				mHolder.tv_status.setText("STATUS: Dispatched");
			}
		}
		
		mHolder.btnAccept.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				fragment.onStatusChange(arr.get(position).getTransaction_id(), "1",position);
				
			}
		});
		
		mHolder.btnReject.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				fragment.onStatusChange(arr.get(position).getTransaction_id(), "-1",position);
				
			}
		});
		
		return v;
	}
	
	public class ViewHolder {	
		
		public TextView tv_name,tv_time,tv_message, tv_phone, tv_address,tv_status,tv_trans;
		public LinearLayout ll_status;
		public Button btnAccept,btnReject;
	}

}
