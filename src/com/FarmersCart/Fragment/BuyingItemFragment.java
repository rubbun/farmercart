package com.FarmersCart.Fragment;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.FarmersCart.Adapter.BuyingItemAdapter;
import com.FarmersCart.Adapter.BuyingSubItemAdapter;
import com.FarmersCart.Bean.BrandBean;
import com.FarmersCart.Bean.BuyingSubCategoryBean;
import com.FarmersCart.Constant.Constant;
import com.FarmersCart.Interface.IBase;
import com.FarmersCart.Network.FarmersFarmFresh2Home;
import com.FarmersCart.UI.BaseActivity;
import com.FarmersCart.UI.R;

@SuppressLint("ValidFragment")
public class BuyingItemFragment extends Fragment implements OnItemClickListener {
	private BaseActivity base;
	private Bundle bundle;
	private String buying_category_id = "";
	private IBase iBase ;
	public int mPosition=0 ;

	private GridView gv_buying_item,gv_buying_sub_item;
	private ArrayList<BuyingSubCategoryBean> mBuyingSubCategoryBean = new ArrayList<BuyingSubCategoryBean>();
	private TextView tv_no_buying_item,tv_no_buying_subitem;
	public ProgressDialog prsDlg;
	private ArrayList<BrandBean> brand_list= new ArrayList<BrandBean>();
	private ArrayList<String> brand_name_list= new ArrayList<String>();
	
	private LinearLayout ll_main,ll_sub;
	

	@SuppressLint("ValidFragment")
	public BuyingItemFragment(BaseActivity base) {
		this.base = base;
		iBase = (IBase) getActivity();
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View v = inflater.inflate(R.layout.fragment_buying_item, container, false);

		gv_buying_item = (GridView) v.findViewById(R.id.gv_buying_item);
		gv_buying_sub_item = (GridView)v.findViewById(R.id.gv_buying_sub_item);
		gv_buying_item.setOnItemClickListener(this);
		tv_no_buying_item = (TextView) v.findViewById(R.id.tv_no_buying_item);
		tv_no_buying_subitem = (TextView)v.findViewById(R.id.tv_no_buying_subitem);
		
		ll_main = (LinearLayout)v.findViewById(R.id.ll_main);
		ll_main.setVisibility(View.VISIBLE);
		
		ll_sub = (LinearLayout)v.findViewById(R.id.ll_sub);
		ll_sub.setVisibility(View.VISIBLE);

		bundle = getArguments();

		buying_category_id = bundle.getString("buying_category_id");

		Log.e("buying_category_id", buying_category_id);

		new callBuyingItem().execute(buying_category_id);

		return v;
	}

	public class callBuyingItem extends AsyncTask<String, Void, Boolean> {
		protected void onPreExecute() {
			base.showProgressDailog("Please wait...");
		}

		@Override
		protected Boolean doInBackground(String... params) {
			try {
				mBuyingSubCategoryBean.clear();
				JSONObject jsonObjSend = new JSONObject();
				jsonObjSend.put("buying_id", params[0]);
				jsonObjSend.put("user_id", base.app.getUserinfo().ID);
				Log.e("SEND", jsonObjSend.toString());
				JSONObject json = FarmersFarmFresh2Home.SendHttpPost(Constant.URLS.GET_BUYING_SUBCATEGORY.getURL(), jsonObjSend);
				boolean status = json.getBoolean("status");

				if (status) {
					JSONArray arr = json.getJSONArray("buying_subcategory");
					for (int i = 0; i < arr.length(); i++) {
						JSONObject obj = arr.getJSONObject(i);
						mBuyingSubCategoryBean.add(new BuyingSubCategoryBean(obj.getString("buying_subcategory_id"), obj.getString("buying_subcategory_name"), obj.getString("buying_subcategory_image"), obj.getString("buying_subcategory_price"), obj.getString("buying_subcategory_description")));
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
			if (status) {
				tv_no_buying_item.setVisibility(View.GONE);
				gv_buying_item.setVisibility(View.VISIBLE);
				gv_buying_item.setAdapter(new BuyingItemAdapter(getActivity(), R.layout.each_grid, mBuyingSubCategoryBean));
			} else {
				tv_no_buying_item.setVisibility(View.VISIBLE);
				gv_buying_item.setVisibility(View.GONE);
			}
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		mPosition = position;
		ll_sub.setVisibility(View.VISIBLE);
		ll_main.setVisibility(View.GONE);
		new callBrand().execute(mBuyingSubCategoryBean.get(position).getmBuyingSubCategoryId());
	}

	public class callBrand extends AsyncTask<String, Void, Boolean> {
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
				JSONObject json = FarmersFarmFresh2Home.SendHttpPost(Constant.URLS.GET_BRAND.getURL(), jsonObjSend);
				boolean status = json.getBoolean("status");

				if (status) {
					JSONArray arr = json.getJSONArray("brand_list");
					for (int i = 0; i < arr.length(); i++) {
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
				/*final CharSequence[] items = brand_name_list.toArray(new CharSequence[brand_name_list.size()]);
				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
				builder.setTitle("Choose your Brand");

				builder.setItems(items, new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int item) {
						// Do something with the selection
						// mDoneButton.setText(items[item]);
						iBase.goBuyingFragmentItemDetail(mBuyingSubCategoryBean.get(mPosition).getmBuyingSubCategoryId(), mBuyingSubCategoryBean.get(mPosition).getmBuyingSubCategoryName(), mBuyingSubCategoryBean.get(mPosition).getmBuyingSubCategoryImage(), mBuyingSubCategoryBean.get(mPosition).getmBuyingSubCategoryPrice(), mBuyingSubCategoryBean.get(mPosition).getmBuyingSubCategoryDescription(), brand_list.get(item).getmUserId(), brand_list.get(item).getmUserName());
					}
				});
				AlertDialog alert = builder.create();
				alert.show();*/
				if (status) {
					tv_no_buying_subitem.setVisibility(View.GONE);
					gv_buying_sub_item.setVisibility(View.VISIBLE);
					gv_buying_sub_item.setAdapter(new BuyingSubItemAdapter(getActivity(), R.layout.each_grid, mBuyingSubCategoryBean.get(mPosition),brand_list));
				} else {
					tv_no_buying_subitem.setVisibility(View.VISIBLE);
					gv_buying_sub_item.setVisibility(View.GONE);
				}
			
		}
	}

	public void showProgressDailog(String msg) {
		prsDlg = new ProgressDialog(getActivity());
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
