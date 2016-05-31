package com.easemob.chatuidemo.myui;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.easemob.chatuidemo.R;
import com.easemob.chatuidemo.myui.MyData.FriendInfo;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

public class FirendFragment extends Fragment {
	ListView layout_listview;
	List<FriendInfo> datas = new ArrayList<FriendInfo>();
	private RequestQueue rq = null;

	private TextView push;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater
				.inflate(R.layout.fragment_friend, container, false);
		layout_listview = (ListView) view.findViewById(R.id.layout_listview);
		// list.setAdapter(new FiendListAdapt(datas, getActivity()));

		push = (TextView) view.findViewById(R.id.push);
		push.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				startActivityForResult(new Intent(getActivity(),
						PushFriendActivity.class),1);
			}
		});
		initData();
		return view;
	}

	@Override
	public void setUserVisibleHint(boolean isVisibleToUser) {
		if (isVisibleToUser) {
			initData();
		}
		super.setUserVisibleHint(isVisibleToUser);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
	}

	private void initData() {
		Map<String, String> map = new HashMap<String, String>();
		String userId = SharedPrefsUtil.getUserId(getActivity());
		map.put("userId", userId);
		JSONObject jsonObject = new JSONObject(map);
		String url = "http://119.29.98.34:8080/chat/community/getTogetherShares.do";
		rq = Volley.newRequestQueue(getActivity().getApplicationContext());
		JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url,
				jsonObject, new Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						Log.e("response", response + "===");
						if (response != null) {
							Gson gson = new Gson();
							TypeToken<MyData> token = new TypeToken<MyData>() {
							};
							Type type = token.getType();
							MyData showMovieMessage = gson.fromJson(
									response.toString(), type);
							if (showMovieMessage != null
									&& showMovieMessage.getData() != null) {
								datas.clear();
								datas.addAll(showMovieMessage.getData());
								FiendListAdapt adapt = new FiendListAdapt(
										datas, getActivity(),FirendFragment.this);
								layout_listview.setAdapter(adapt);
								layout_listview.setOnItemClickListener(adapt);
							}
						}
					}

				}, new ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						System.out.println("error--->" + error);
					}
				}) {
			@Override
			public Map<String, String> getHeaders() throws AuthFailureError {
				Map<String, String> headers = new HashMap<String, String>();
				headers.put("User-Agent", "123");
				return headers;
			}
		};
		rq.add(req);
		rq.start();
	}
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		initData();
	}
}
