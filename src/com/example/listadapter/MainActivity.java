package com.example.listadapter;

import java.util.ArrayList;
import java.util.List;

import com.example.listadapter.adapter.MainActivityAdapter;
import com.example.model.UserModel;
import com.example.telkomsel.R;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	private MainActivityAdapter mAdapter;
	private ListView mListView;
	private TextView mTextView;
	private EditText mEditText;
	private Button mButton;
	private List<UserModel> mUserModels;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		mListView = (ListView)findViewById(R.id.lv_list);
		mListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		mEditText = (EditText)findViewById(R.id.et_search);
		mButton = (Button)findViewById(R.id.btn_go);
		
		// TODO PREPARE DUMMY DATA
		new async().execute();
		
		mButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(mEditText.getText().toString().trim().length() > 0){
					UserModel model = getContent(mEditText.getText().toString());
					searchData(model);
				}else{
					new async().execute();
				}
			}
		});
	}
	
	private class async extends AsyncTask<Void, Void, List<UserModel>>{

		ProgressDialog dialog = new ProgressDialog(MainActivity.this);
		
		//TODO SEBELUM TASK
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog.setTitle("Downloading");
			dialog.setMessage("Download datas in progress ...");
			dialog.show();
		}
		
		//TODO SEDANG MELAKUKAN TASK
		@Override
		protected List<UserModel> doInBackground(Void... params) {
			return preparedDummiesData();
		}
		
		//TODO SETELAH SELESAI TASK
		@Override
		protected void onPostExecute(List<UserModel> result) {
			super.onPostExecute(result);
			dialog.dismiss();
			mAdapter = new MainActivityAdapter(MainActivity.this, result);
			mListView.setAdapter(mAdapter);
		}
	}
	
	private UserModel getContent(String id){
		UserModel model = null;
		for(int i = 0; i < mUserModels.size(); i++){
			if(mUserModels.get(i).getId().equalsIgnoreCase(id)){
				return mUserModels.get(i);
			}
		}
		return model;
	}
	
	private void searchData(UserModel model){
		if(model != null){
			mUserModels = new ArrayList<UserModel>();
			mUserModels.add(model);
			mAdapter = new MainActivityAdapter(this, mUserModels);
			mListView.setAdapter(mAdapter);
		}
	}
	
	private List<UserModel> preparedDummiesData(){
		mUserModels = new ArrayList<UserModel>();
		for(int i = 0; i < 100000; i++){
			UserModel model = new UserModel();
			model.setId("ID " + i);
			model.setUsername("RADITYA " + i);
			model.setAddress("JAKARTA " + i);
			mUserModels.add(model);
		}
		
		return mUserModels;
	}
}
