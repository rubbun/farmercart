package com.FarmersCart.Fragment;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.FarmersCart.Adapter.BuyingAdapter;
import com.FarmersCart.Adapter.MessageAdapter;
import com.FarmersCart.Bean.BuyingCategoryBean;
import com.FarmersCart.Bean.MessageBean;
import com.FarmersCart.Constant.Constant;
import com.FarmersCart.Interface.IBase;
import com.FarmersCart.Network.FarmersFarmFresh2Home;
import com.FarmersCart.UI.BaseActivity;
import com.FarmersCart.UI.R;
import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ListView;
import android.widget.TextView;

@SuppressLint("ValidFragment")
public class MessageFragment extends Fragment implements OnClickListener {
	private BaseActivity base;
	private IBase iBase ;
	private ListView lv_message;
	private TextView tv_no_message;
	private ArrayList<MessageBean> mMessageBean = new ArrayList<MessageBean>();
	
	public MessageFragment(BaseActivity base) {
		this.base = base;
		iBase =  (IBase) base ;
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment_message, container, false);
		
		lv_message = (ListView)v.findViewById(R.id.lv_message);
		tv_no_message = (TextView)v.findViewById(R.id.tv_no_message);
		
		new callMessage().execute();
		
		
		return v ;
	}
	
	
	public class callMessage extends AsyncTask<String, Void, Boolean>{
		protected void onPreExecute() {
			base.showProgressDailog("Please wait...");
		}
		
		@Override
		protected Boolean doInBackground(String... params) {
		  	try {
		  		JSONObject jsonObjSend = new JSONObject();
		  		jsonObjSend.put("user_id", base.app.getUserinfo().ID);
		  		jsonObjSend.put("flag", "1");
				Log.e("SEND", jsonObjSend.toString());
				JSONObject json = FarmersFarmFresh2Home.SendHttpPost(Constant.URLS.GET_MESSAGE.getURL(),jsonObjSend);
				boolean status=json.getBoolean("status");
				
				if(status){
					JSONArray arr = json.getJSONArray("message");
					for(int i = 0 ; i<arr.length();i++){
						JSONObject obj = arr.getJSONObject(i);
						mMessageBean.add(new MessageBean(obj.getString("user_id"), obj.getString("name"),
								obj.getString("message"),obj.getString("phone"),obj.getString("address"), obj.getString("create_date"), obj.getString("opening_flag")));
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
				tv_no_message.setVisibility(View.GONE);
				lv_message.setVisibility(View.VISIBLE);
				lv_message.setAdapter(new MessageAdapter(getActivity(), R.layout.each_grid, mMessageBean));
				/*for(int i = 0 ; i<mMessageBean.size();i++){
					mMessageBean.get(i).setmMessageOpenFlag("Y");
				}*/
			}else{
				tv_no_message.setVisibility(View.VISIBLE);
				lv_message.setVisibility(View.GONE);
			}
			
		}
	}
	
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}

}
