package com.FarmersCart.Fragment;

import org.json.JSONException;
import org.json.JSONObject;

import com.FarmersCart.Constant.Constant;
import com.FarmersCart.Interface.IBase;
import com.FarmersCart.Network.FarmersFarmFresh2Home;
import com.FarmersCart.UI.BaseActivity;
import com.FarmersCart.UI.R;
import com.FarmersCart.Util.ImageLoader;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
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

public class SellingEachItemFragment extends Fragment implements OnClickListener {
	private BaseActivity base;
	private Bundle bundle ;
	private String selling_each_item_title = "", selling_each_item_image = "",selling_each_item_price = "",selling_each_item_id = "",selling_each_item_description = "";
	private Button btn_submit,btn_cancel ;
	
	private TextView tv_selling_item_type,tv_selling_item_cost,tv_selling_item_total_cost,tv_selling_item_description;
	private ImageLoader imgloader ;
	private ImageView iv_selling_each_item_image ;
	private EditText et_selling_item_quantity ;
	private Spinner sp_selling_item_payment_type ;
	private IBase iBase ;
	
	public SellingEachItemFragment(BaseActivity base){
		this.base  = base;	
		iBase =  (IBase) base ;
		}
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			
			View v = inflater.inflate(R.layout.fragment_selling_each_item, container, false);
			
			tv_selling_item_type = (TextView)v.findViewById(R.id.tv_selling_item_type);
			tv_selling_item_cost = (TextView)v.findViewById(R.id.tv_selling_item_cost);
			tv_selling_item_total_cost = (TextView)v.findViewById(R.id.tv_selling_item_total_cost);
			tv_selling_item_description = (TextView)v.findViewById(R.id.tv_selling_item_description);
			
			et_selling_item_quantity = (EditText)v.findViewById(R.id.et_selling_item_quantity);
			
			iv_selling_each_item_image = (ImageView)v.findViewById(R.id.iv_selling_each_item_image);
			
			sp_selling_item_payment_type = (Spinner)v.findViewById(R.id.sp_selling_item_payment_type);
			
			btn_submit = (Button)v.findViewById(R.id.btn_submit);
			
			btn_cancel = (Button)v.findViewById(R.id.btn_cancel);
			
			btn_submit.setOnClickListener(this);
			btn_cancel.setOnClickListener(this);
			
			imgloader= new ImageLoader(getActivity().getApplicationContext());
			
			bundle = getArguments();
			
			selling_each_item_title = bundle.getString("selling_each_item_title");
			selling_each_item_image = bundle.getString("selling_each_item_image");
			selling_each_item_price = bundle.getString("selling_each_item_price");
			selling_each_item_id = bundle.getString("selling_each_item_id");
			selling_each_item_description  = bundle.getString("selling_each_item_description");
			
			
			tv_selling_item_type.setText(selling_each_item_title);
			tv_selling_item_cost.setText(selling_each_item_price);
			imgloader.DisplayImage(selling_each_item_image, R.drawable.loader, iv_selling_each_item_image);
			tv_selling_item_description.setText(selling_each_item_price);
			
			et_selling_item_quantity.addTextChangedListener(new TextWatcher() {
				
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
						Log.e("",""+Integer.parseInt(selling_each_item_price)*Float.parseFloat(arg0.toString()));
						String str = ""+Float.parseFloat(selling_each_item_price)*Float.parseFloat(arg0.toString());
						tv_selling_item_total_cost.setText(str);
					}else{
						tv_selling_item_total_cost.setText("");
					}
					
				}
			});
			
		    return v ;	
		}

		@Override
		public void onClick(View arg0) {
			// TODO Auto-generated method stub
			if(arg0 == btn_submit){
				new callAddSellingProduct().execute();
			}
			if(arg0 == btn_cancel){
				iBase.goSellingFragmentItem(Constant.sSelling_category_id,Constant.sSelling_category_title);
			}
		}
		
		
		
		
		public class callAddSellingProduct extends AsyncTask<String, Void, Boolean>{
			protected void onPreExecute() {
				base.showProgressDailog("Please wait...");
			}
			
			@Override
			protected Boolean doInBackground(String... params) {
			  	try {
			  		JSONObject jsonObjSend = new JSONObject();	
			  		jsonObjSend.put("user_id", base.app.getUserinfo().ID);
			  		jsonObjSend.put("selling_each_item_id", selling_each_item_id);
			  		jsonObjSend.put("quantity", et_selling_item_quantity.getText().toString().trim());
			  		jsonObjSend.put("total_cost", tv_selling_item_total_cost.getText().toString().trim());
			  		jsonObjSend.put("delivery_type", sp_selling_item_payment_type.getSelectedItem().toString());
			  		
					Log.e("SEND", jsonObjSend.toString());
					
					JSONObject json = FarmersFarmFresh2Home.SendHttpPost(Constant.URLS.ADD_SELLING_PRODUCT.getURL(),jsonObjSend);
					boolean status=json.getBoolean("status");
										
					
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
					//app.getUserinfo().setUserInfo(mUserId, et_reg_fname.getText().toString().trim(), et_reg_lname.getText().toString().trim(), et_reg_phone.getText().toString().trim(), true);
					Toast.makeText(getActivity(),"Product for Selling is successfully added..", 
			                Toast.LENGTH_SHORT).show();
					iBase.goSellingFragmentItem(Constant.sSelling_category_id,Constant.sSelling_category_title);
				}else{
					Toast.makeText(getActivity(),"Some internal error occour, Please try after sometime.", 
			                Toast.LENGTH_SHORT).show();
				}
				
			}
		}
}
