package com.example.listadapter.adapter;

import java.util.List;

import com.example.model.UserModel;
import com.example.telkomsel.R;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivityAdapter extends BaseAdapter {

	private List<UserModel> mUserModels;
	private Activity mActivity;
	
	public MainActivityAdapter(Activity activity, List<UserModel> models){
		this.mActivity = activity;
		this.mUserModels = models;
	}
	
	@Override
	public int getCount() {
		return mUserModels.size();	
	}

	@Override
	public Object getItem(int position) {
		return mUserModels.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View view = convertView;
		ViewHolder holder = null;
		
		if (view == null){
			view = mActivity.getLayoutInflater().inflate(R.layout.activity_main_list_item, parent, false);
			holder = new ViewHolder();
			holder.mId = (TextView)view.findViewById(R.id.tv_id);
			holder.mUsername = (TextView)view.findViewById(R.id.tv_username);
			holder.mAddress = (TextView)view.findViewById(R.id.tv_address);
			view.setTag(holder);
		}else{
			holder = (ViewHolder)view.getTag();
		}
		
		holder = (ViewHolder)view.getTag();
		holder.mId.setText(mUserModels.get(position).getId());
		holder.mUsername.setText(mUserModels.get(position).getUsername());
		holder.mAddress.setText(mUserModels.get(position).getAddress());
		
		view.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(mActivity, "HELLO " + mUserModels.get(position).getUsername(), Toast.LENGTH_SHORT).show();
			}
		});
		return view;
	}

	private static class ViewHolder{
		private TextView mId, mUsername, mAddress;
	}
}
