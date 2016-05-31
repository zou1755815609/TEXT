package com.easemob.chatuidemo.myui;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.easemob.chatuidemo.R;
import com.google.android.gms.internal.el;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.DbUtils;
import com.lidroid.xutils.db.sqlite.Selector;
import com.lidroid.xutils.exception.DbException;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserInfoActivity extends Activity {
	public static final String PAMAS = "UserInfoActivity";
	TextView edit_user;
	TextView edit_name;
	TextView edit_phone;
	TextView edit_star;
	TextView edit_borther;
	TextView text_commit;
	String username;
	private RequestQueue rq = null;
	private UserData userData;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_info);
		edit_user = (TextView) findViewById(R.id.edit_user);
		edit_name = (TextView) findViewById(R.id.edit_name);
		edit_phone = (TextView) findViewById(R.id.edit_phone);
		edit_star = (TextView) findViewById(R.id.edit_star);
		edit_borther = (TextView) findViewById(R.id.edit_borther);
		text_commit = (TextView) findViewById(R.id.text_commit);
		text_commit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(UserInfoActivity.this,
						SetUserInfoActivity.class);
				intent.putExtra(SetUserInfoActivity.PAMAS, userData);
				startActivity(intent);
			}
		});
		username = getIntent().getStringExtra(PAMAS);
		// initData();
		findViewById(R.id.back).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
		findViewById(R.id.call).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (userData != null && userData.getPhone() != null) {
					Intent intent = new Intent();

					// 系统默认的action，用来打开默认的电话界面
					intent.setAction(Intent.ACTION_CALL);

					// 需要拨打的号码

					intent.setData(Uri.parse("tel:" + userData.getPhone()));
					startActivity(intent);
				} else {
					Toast.makeText(UserInfoActivity.this, "手机号码不存在",
							Toast.LENGTH_LONG).show();
				}
			}
		});
		findViewById(R.id.sms).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				if (userData != null && userData.getPhone() != null) {
					Uri smsToUri = Uri.parse("smsto:" + userData.getPhone());
					Intent mIntent = new Intent(Intent.ACTION_SENDTO, smsToUri);
					mIntent.putExtra("sms_body", "The SMS text");
					startActivity(mIntent);
				} else {
					Toast.makeText(UserInfoActivity.this, "手机号码不存在",
							Toast.LENGTH_LONG).show();
				}
			}
		});
		DbUtils db = DbUtils.create(UserInfoActivity.this);
		try {
			userData = db.findFirst(Selector.from(UserData.class).where(
					"username", "=", username));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (userData == null) {
			userData = new UserData();
			userData.setUsername(username);
		}
		setData(userData);
	}

	public static boolean isMobileNO(String mobiles) {
		/*
		 * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
		 * 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）
		 * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
		 */
		String telRegex = "[1][358]\\d{9}";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
		if (TextUtils.isEmpty(mobiles))
			return false;
		else
			return mobiles.matches(telRegex);
	}

	private void initData() {
		Map<String, String> map = new HashMap<String, String>();
		Log.e("username", username + "===");
		map.put("userId", username);
		JSONObject jsonObject = new JSONObject(map);
		String url = "http://119.29.98.34:8080/chat/user/user.do";
		rq = Volley.newRequestQueue(getApplicationContext());
		JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url,
				jsonObject, new Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						Log.e("response", response + "===");
						if (response != null) {
							Gson gson = new Gson();
							TypeToken<MyUserInfo> token = new TypeToken<MyUserInfo>() {
							};
							Type type = token.getType();
							MyUserInfo showMovieMessage = gson.fromJson(
									response.toString(), type);
							if (showMovieMessage != null
									&& showMovieMessage.getStatus() == 0
									&& showMovieMessage.getData() != null) {
								userData = showMovieMessage.getData();
								setData(showMovieMessage.getData());
							} else {
								userData = new UserData();
								userData.setUsername(username);
								setData(userData);
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

	private void setData(UserData info) {
		setText(edit_user, info.getUsername());
		setText(edit_name, info.getNickname());
		setText(edit_phone, info.getPhone());
		setText(edit_star, info.getConstellation());
		setText(edit_borther, info.getBirthday());
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		DbUtils db = DbUtils.create(UserInfoActivity.this);
		try {
			userData = db.findFirst(Selector.from(UserData.class).where(
					"username", "=", username));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (userData == null) {
			userData = new UserData();
			userData.setUsername(username);
		}
		setData(userData);
	}

	private void setText(TextView edit_user, String tag) {
		if (tag != null && !tag.equals("")) {
			edit_user.setText(tag);
		}
	}
}
