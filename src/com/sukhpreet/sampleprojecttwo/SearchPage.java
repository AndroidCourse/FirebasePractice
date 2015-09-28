package com.sukhpreet.sampleprojecttwo;

import java.util.ArrayList;
import java.util.Arrays;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

public class SearchPage extends ListActivity {

	private EditText et;
	private ListView lv;
	private final String[] listview_name = { "Angular", "Android", "Math" };

	private ArrayList<String> array_sort;
	int textlength = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_search_page);

		Firebase.setAndroidContext(this);
		et = (EditText) findViewById(R.id.EditText01);
		lv = (ListView) findViewById(android.R.id.list);

		// Setting Focus
		et.requestFocus();
		array_sort = new ArrayList<String>(Arrays.asList(listview_name));
		setListAdapter(new bsAdapter(this));

		et.addTextChangedListener(new TextWatcher() {
			@Override
			public void afterTextChanged(Editable s) {
				// Abstract Method of TextWatcher Interface.
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// Abstract Method of TextWatcher Interface.
			}

			@SuppressLint("DefaultLocale")
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				textlength = et.getText().length();
				array_sort.clear();
				for (int i = 0; i < listview_name.length; i++) {
					if (textlength <= listview_name[i].length()) {

						if (listview_name[i].toLowerCase().contains(et.getText().toString().toLowerCase().trim())) {
							array_sort.add(listview_name[i]);
						}
					}
				}
				AppendList(array_sort);
			}

		});
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					final int position, long arg3) {

				String baseurl = "https://amber-heat-1015.firebaseio.com/";

				Firebase ref = new Firebase(baseurl + array_sort.get(position));
				ref.addChildEventListener(new ChildEventListener() {

					@Override
					public void onChildRemoved(DataSnapshot arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onChildMoved(DataSnapshot arg0, String arg1) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onChildChanged(DataSnapshot arg0, String arg1) {
						// TODO Auto-generated method stub

					}

					@Override
//					public void onChildAdded(DataSnapshot arg0, String arg1) {
//						// TODO Auto-generated method stub
//						String tempImage = "";
//						if (arg0.getKey().toString() == "Image") {
//							tempImage = arg0.getValue().toString();
//
//						}
//						if (tempImage.equals("")) {
//
//						} else {
//							Intent activityIntent = new Intent(SearchPage.this,
//									MainDetailRecipePage.class);
//							activityIntent.putExtra("imageURL", tempImage);
//							activityIntent.putExtra("item",
//									array_sort.get(position));
//							activityIntent.putExtra("backButton", "backSearch");
//							startActivity(activityIntent);
//							finish();
//						}
//					}

				//	@Override
					public void onCancelled(FirebaseError arg0) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onChildAdded(DataSnapshot arg0, String arg1) {
						// TODO Auto-generated method stub
						
					}
				});

			}
		});
	}

	public void AppendList(ArrayList<String> str) {
		setListAdapter(new bsAdapter(this));
	}

	public class bsAdapter extends BaseAdapter {
		Activity cntx;

		public bsAdapter(Activity context) {
			// TODO Auto-generated constructor stub
			this.cntx = context;

		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return array_sort.size();
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return array_sort.get(position);
		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return array_sort.size();
		}

		@SuppressLint({ "ViewHolder", "InflateParams" })
		@Override
		public View getView(final int position, View convertView,
				ViewGroup parent) {
			View row = null;

			LayoutInflater inflater = cntx.getLayoutInflater();
			row = inflater.inflate(R.layout.searchlistitem, null);

			TextView tv = (TextView) row.findViewById(R.id.title);

			tv.setText(array_sort.get(position));


			return row;
		}
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.content, menu);
//		return true;
//	}

//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
//		if (id == R.id.action_contactus) {
//			Intent i = new Intent(SearchPage.this, ContactUs.class);
//			startActivity(i);
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		Intent activityIntent = new Intent(SearchPage.this,
				MainLandingPage.class);
		SearchPage.this.startActivity(activityIntent);
		finish();
	}

	}
