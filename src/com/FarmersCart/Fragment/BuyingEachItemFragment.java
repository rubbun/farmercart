package com.FarmersCart.Fragment;

import com.FarmersCart.Bean.CartBean;
import com.FarmersCart.Constant.Constant;
import com.FarmersCart.Events.EventAddtoCart;
import com.FarmersCart.Interface.IBase;
import com.FarmersCart.UI.BaseActivity;
import com.FarmersCart.UI.LoginActivity;
import com.FarmersCart.UI.R;
import com.FarmersCart.Util.ImageLoader;
import de.greenrobot.event.EventBus;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("ValidFragment")
public class BuyingEachItemFragment extends Fragment implements OnClickListener {
	private BaseActivity base;
	private Bundle bundle ;
	private String buying_each_item_title = "", buying_each_item_image = "",buying_each_item_price = "",buying_each_item_id = "",brand_user_id=""
			,brand_user_name="",buying_each_item_description="";
	
	private TextView tv_buying_item_type,tv_buying_item_cost,tv_buying_item_total_cost,tv_brand,tv_buying_item_description;
	private ImageLoader imgloader ;
	private ImageView iv_buying_each_item_image ;
	private EditText et_buying_item_quantity ;
	private Spinner sp_buying_item_payment_type ;
	private Button btn_submit,btn_cancel ;
	
	public BuyingEachItemFragment(BaseActivity base){
		this.base  = base;	
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			
			View v = inflater.inflate(R.layout.fragment_each_buying_item, container, false);
			tv_buying_item_type = (TextView)v.findViewById(R.id.tv_buying_item_type);
			tv_buying_item_cost = (TextView)v.findViewById(R.id.tv_buying_item_cost);
			tv_buying_item_total_cost = (TextView)v.findViewById(R.id.tv_buying_item_total_cost);
			tv_brand = (TextView)v.findViewById(R.id.tv_brand);
			tv_buying_item_description = (TextView)v.findViewById(R.id.tv_buying_item_description);
			et_buying_item_quantity = (EditText)v.findViewById(R.id.et_buying_item_quantity);
			
			iv_buying_each_item_image = (ImageView)v.findViewById(R.id.iv_buying_each_item_image);
			
			sp_buying_item_payment_type = (Spinner)v.findViewById(R.id.sp_buying_item_payment_type);
			btn_submit =(Button)v.findViewById(R.id.btn_submit);
			btn_cancel =(Button)v.findViewById(R.id.btn_cancel);
			
			btn_submit.setOnClickListener(this);
			btn_cancel.setOnClickListener(this);
			
			imgloader= new ImageLoader(getActivity().getApplicationContext());
			
			bundle = getArguments();
			
			buying_each_item_title = bundle.getString("buying_each_item_title");
			buying_each_item_image = bundle.getString("buying_each_item_image");
			buying_each_item_price = bundle.getString("buying_each_item_price");
			buying_each_item_id = bundle.getString("buying_each_item_id");
			brand_user_id = bundle.getString("brand_user_id");
			brand_user_name = bundle.getString("brand_user_name");
			buying_each_item_description = bundle.getString("buying_each_item_description");
			
			tv_buying_item_type.setText(buying_each_item_title);
			tv_buying_item_cost.setText(buying_each_item_price);
			tv_brand.setText(brand_user_name);
			tv_buying_item_cost.setText(buying_each_item_price);
			tv_buying_item_description.setText(buying_each_item_description);
			
			imgloader.DisplayImage(buying_each_item_image, R.drawable.loader, iv_buying_each_item_image);
			
			
			et_buying_item_quantity.addTextChangedListener(new TextWatcher() {
				
				@Override
				public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
						int arg3) {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void afterTextChanged(Editable arg0) {
					// TODO Auto-generated method stub	
					if(!arg0.toString().equals("") && !arg0.toString().equals(".") ){
					String str = ""+Float.parseFloat(buying_each_item_price)*Float.parseFloat(arg0.toString());
					tv_buying_item_total_cost.setText(str);
					}else{
						tv_buying_item_total_cost.setText("");
					}
				}
			});
		    return v ;	
		}

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(arg0 == btn_submit){
				Constant.sCartBean.add(new CartBean(brand_user_id, brand_user_name, buying_each_item_id, buying_each_item_title, buying_each_item_image,
						buying_each_item_price, tv_buying_item_total_cost.getText().toString().trim(), et_buying_item_quantity.getText().toString().trim(),
						sp_buying_item_payment_type.getSelectedItem().toString().trim()));
				Toast.makeText(getActivity(),"Item added to Cart", 
		                Toast.LENGTH_SHORT).show();
				EventBus.getDefault().post(new EventAddtoCart());
			}
		}
}
