/**
 * Copyright (C) 2013-2014 EaseMob Technologies. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.easemob.chatuidemo.activity;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.VolleyError;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.easemob.EMError;
import com.easemob.chat.EMChatManager;
import com.easemob.chatuidemo.DemoApplication;
import com.easemob.chatuidemo.R;
import com.easemob.chatuidemo.myui.MyData;
import com.easemob.chatuidemo.myui.SharedPrefsUtil;
import com.easemob.chatuidemo.myui.UserInfo;
import com.easemob.exceptions.EaseMobException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * 注册页
 * 
 */
public class RegisterActivity extends BaseActivity {
	private EditText userNameEditText;
	private EditText passwordEditText;
	private EditText confirmPwdEditText;
	private RequestQueue rq = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_register);
		userNameEditText = (EditText) findViewById(R.id.username);
		passwordEditText = (EditText) findViewById(R.id.password);
		confirmPwdEditText = (EditText) findViewById(R.id.confirm_password);
	}

	/**
	 * 注册
	 * 
	 * @param view
	 */
	public void register(View view) {
		final String username = userNameEditText.getText().toString().trim();
		final String pwd = passwordEditText.getText().toString().trim();
		String confirm_pwd = confirmPwdEditText.getText().toString().trim();
		if (TextUtils.isEmpty(username)) {
			Toast.makeText(
					this,
					getResources()
							.getString(R.string.User_name_cannot_be_empty),
					Toast.LENGTH_SHORT).show();
			userNameEditText.requestFocus();
			return;
		} else if (TextUtils.isEmpty(pwd)) {
			Toast.makeText(
					this,
					getResources().getString(R.string.Password_cannot_be_empty),
					Toast.LENGTH_SHORT).show();
			passwordEditText.requestFocus();
			return;
		} else if (TextUtils.isEmpty(confirm_pwd)) {
			Toast.makeText(
					this,
					getResources().getString(
							R.string.Confirm_password_cannot_be_empty),
					Toast.LENGTH_SHORT).show();
			confirmPwdEditText.requestFocus();
			return;
		} else if (!pwd.equals(confirm_pwd)) {
			Toast.makeText(this,
					getResources().getString(R.string.Two_input_password),
					Toast.LENGTH_SHORT).show();
			return;
		}

		if (!TextUtils.isEmpty(username) && !TextUtils.isEmpty(pwd)) {
			final ProgressDialog pd = new ProgressDialog(this);
			pd.setMessage(getResources().getString(R.string.Is_the_registered));
			pd.show();

			new Thread(new Runnable() {
				public void run() {
					try {
						// 调用sdk注册方法
						EMChatManager.getInstance().createAccountOnServer(
								username, pwd);
						runOnUiThread(new Runnable() {
							public void run() {
								if (!RegisterActivity.this.isFinishing())
									pd.dismiss();
								initData(username, pwd);
								// 保存用户名
								DemoApplication.getInstance().setUserName(
										username);
								Toast.makeText(
										getApplicationContext(),
										getResources()
												.getString(
														R.string.Registered_successfully),
										0).show();
								finish();
							}
						});
					} catch (final EaseMobException e) {
						runOnUiThread(new Runnable() {
							public void run() {
								if (!RegisterActivity.this.isFinishing())
									pd.dismiss();
								int errorCode = e.getErrorCode();
								if (errorCode == EMError.NONETWORK_ERROR) {
									Toast.makeText(
											getApplicationContext(),
											getResources().getString(
													R.string.network_anomalies),
											Toast.LENGTH_SHORT).show();
								} else if (errorCode == EMError.USER_ALREADY_EXISTS) {
									Toast.makeText(
											getApplicationContext(),
											getResources()
													.getString(
															R.string.User_already_exists),
											Toast.LENGTH_SHORT).show();
								} else if (errorCode == EMError.UNAUTHORIZED) {
									Toast.makeText(
											getApplicationContext(),
											getResources()
													.getString(
															R.string.registration_failed_without_permission),
											Toast.LENGTH_SHORT).show();
								} else if (errorCode == EMError.ILLEGAL_USER_NAME) {
									Toast.makeText(
											getApplicationContext(),
											getResources().getString(
													R.string.illegal_user_name),
											Toast.LENGTH_SHORT).show();
								} else {
									Toast.makeText(
											getApplicationContext(),
											getResources()
													.getString(
															R.string.Registration_failed)
													+ e.getMessage(),
											Toast.LENGTH_SHORT).show();
								}
							}
						});
					}
				}
			}).start();

		}
	}

	public void back(View view) {
		finish();
	}

	private void initData(String username, String password) {

		Map<String, String> map = new HashMap<String, String>();
		map.put("username", username);
		map.put("password", password);

		JSONObject jsonObject = new JSONObject(map);
		String url = "http://119.29.98.34:8080/chat/user/register.do";
		rq = Volley.newRequestQueue(getApplicationContext());
		JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url,
				jsonObject, new Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						Log.e("response", response + "");
						Gson gson = new Gson();
						TypeToken<UserInfo> token = new TypeToken<UserInfo>() {
						};
						Type type = token.getType();
						UserInfo showMovieMessage = gson.fromJson(
								response.toString(), type);
						if (showMovieMessage.getStatus() == 0) {
							SharedPrefsUtil.putUserId(RegisterActivity.this,
									showMovieMessage.getData().getId()+"");
						} else {
						}
						Log.e("response", "response");
					}

				}, new ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						Log.e("response", error + "");
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
