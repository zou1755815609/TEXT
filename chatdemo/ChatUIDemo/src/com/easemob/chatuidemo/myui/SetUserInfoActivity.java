package com.easemob.chatuidemo.myui;

import java.io.File;
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
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.DbException;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SetUserInfoActivity extends Activity {
	public static final String PAMAS = "SetUserInfoActivity";
	TextView edit_user;
	EditText edit_name;
	EditText edit_phone;
	EditText edit_star;
	EditText edit_borther;
	TextView text_commit;
	private UserData userData;

	private RequestQueue rq = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_user_info);
		edit_user = (TextView) findViewById(R.id.edit_user);
		edit_name = (EditText) findViewById(R.id.edit_name);
		edit_phone = (EditText) findViewById(R.id.edit_phone);
		edit_star = (EditText) findViewById(R.id.edit_star);
		edit_borther = (EditText) findViewById(R.id.edit_borther);
		text_commit = (TextView) findViewById(R.id.text_commit);
		text_commit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String name = edit_name.getText().toString();
				String phone = edit_phone.getText().toString();
				String star = edit_star.getText().toString();
				String bother = edit_borther.getText().toString();
				if (phone != null && !"".equals(phone)) {
					if (isMobileNO(phone)) {
						userData.setPhone(phone);
					} else {
						Toast.makeText(SetUserInfoActivity.this, "手机号码格式不正确",
								Toast.LENGTH_LONG).show();
					}
					if (name != null && !"".equals(name)) {
						userData.setNickname(name);
					}
					if (star != null && !"".equals(star)) {
						userData.setConstellation(star);
					}
					if (bother != null && !"".equals(bother)) {
						userData.setBirthday(bother);
					}
					if (phone != null && !"".equals(phone)) {
						userData.setPhone(phone);
					}
				} else {
					if (name != null && !"".equals(name)) {
						userData.setNickname(name);
					}
					if (star != null && !"".equals(star)) {
						userData.setConstellation(star);
					}
					if (bother != null && !"".equals(bother)) {
						userData.setBirthday(bother);
					}
					if (phone != null && !"".equals(phone)) {
						userData.setPhone(phone);
					}
				}

				DbUtils db = DbUtils.create(SetUserInfoActivity.this);
				try {
					db.save(userData);
					Toast.makeText(SetUserInfoActivity.this,
							"修改用户备注信息成功", Toast.LENGTH_LONG)
							.show();
					finish();
				} catch (DbException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					Toast.makeText(SetUserInfoActivity.this,
							"修改用户备注信息失败", Toast.LENGTH_LONG)
							.show();
				}
			}
		});
		userData = (UserData) getIntent().getSerializableExtra(PAMAS);
		if (userData != null) {
			setData(userData);
		} else {
			Toast.makeText(SetUserInfoActivity.this, "用户信息不存在",
					Toast.LENGTH_LONG).show();
			finish();
		}
		findViewById(R.id.back).setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}

	public static boolean isMobileNO(String mobiles) {
		/*
		 * 移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
		 * 联通：130、131、132、152、155、156、185、186 电信：133、153、180、189、（1349卫通）
		 * 总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
		 */
		String telRegex = "[1][345789]\\d{9}";// "[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
		if (TextUtils.isEmpty(mobiles))
			return false;
		else
			return mobiles.matches(telRegex);
	}

	private void setData(UserData info) {
		if (info.getUsername() != null) {
			edit_user.setText(info.getUsername());
		}
		setText(edit_name, info.getNickname());
		setText(edit_phone, info.getPhone());
		setText(edit_star, info.getConstellation());
		setText(edit_borther, info.getBirthday());
	}

	private void setText(EditText edit_user, String tag) {
		if (tag != null && !tag.equals("")) {
			edit_user.setText(tag);
		}
	}

	private void upLoude(String url, String user, String phone, String star,
			String bother) {
		// File file = UploadImage.saveBitmap(getBitmapFromUri(selectedImage));
		// 只包含字符串参数时默认使用BodyParamsEntity，
		// 类似于UrlEncodedFormEntity（"application/x-www-form-urlencoded"）。
		String username = "13";
		// map.put("userId", username);
		// map.put("nickname", user);
		// map.put("constellation", star);
		// map.put("phone", phone);
		// map.put("birthday", bother);
		File file = new File(Environment.getExternalStorageDirectory()
				+ "/temp.jpg");
		RequestParams params = new RequestParams();
		params.addBodyParameter("userId", username);
		params.addBodyParameter("nickname", user);
		params.addBodyParameter("constellation", user);
		params.addBodyParameter("phone", phone);
		params.addBodyParameter("birthday", bother);
		params.addBodyParameter("file", file);
		// params.addBodyParameter("file", null);

		HttpUtils http = new HttpUtils();
		http.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {

			@Override
			public void onStart() {
			}

			@Override
			public void onLoading(long total, long current, boolean isUploading) {
			}

			@Override
			public void onSuccess(ResponseInfo<String> responseInfo) {
				Log.e("responseInfo", responseInfo.result + "===");
				Toast.makeText(SetUserInfoActivity.this, "修改成功",
						Toast.LENGTH_LONG).show();
				finish();
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				Toast.makeText(SetUserInfoActivity.this, "修改失败，请重新发布",
						Toast.LENGTH_LONG).show();
				Log.e("responseInfo", msg);
			}
		});
	}

	private void initData(String user, String phone, String star, String bother) {
		Map<String, String> map = new HashMap<String, String>();
		String username = "123";
		map.put("userId", username);
		map.put("nickname", user);
		map.put("constellation", star);
		map.put("phone", phone);
		map.put("birthday", bother);
		JSONObject jsonObject = new JSONObject(map);
		String url = "http://119.29.98.34:8080/chat/user/modify.do";
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
							if (showMovieMessage != null) {
								if (showMovieMessage.getStatus() == 0) {
									Toast.makeText(SetUserInfoActivity.this,
											"修改用户备注信息成功", Toast.LENGTH_LONG)
											.show();
									finish();
								}
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
}
