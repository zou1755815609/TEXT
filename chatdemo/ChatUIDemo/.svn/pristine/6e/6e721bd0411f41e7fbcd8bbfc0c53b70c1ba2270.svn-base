package com.easemob.chatuidemo.myui;

import java.io.File;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
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
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class PushFriendActivity extends Activity {
	public static final int REQUEST_CODE_LOCAL = 19;
	private TextView text_commit;
	private EditText edit_text;
	ProgressDialog dialog;
	private RequestQueue rq = null;
	Button add;
	ImageView img;
	Uri selectedImage;
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			dialog.dismiss();
			Toast.makeText(PushFriendActivity.this, "发布成功", Toast.LENGTH_LONG)
					.show();
			edit_text.setText("");
			finish();
		};
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_push);
		text_commit = (TextView) findViewById(R.id.text_commit);
		edit_text = (EditText) findViewById(R.id.edit_text);
		dialog = new ProgressDialog(PushFriendActivity.this);
		add = (Button) findViewById(R.id.add);
		img = (ImageView) findViewById(R.id.img);
		text_commit.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				String str = edit_text.getText().toString();
				if (str != null && !"".equals(str)) {
					if (selectedImage != null) {
						dialog.show();
						initData(str);
					} else {
						Toast.makeText(PushFriendActivity.this, "请添加图片",
								Toast.LENGTH_LONG).show();
					}
				} else {
					Toast.makeText(PushFriendActivity.this, "输入内容不能为空",
							Toast.LENGTH_LONG).show();
				}
			}
		});
		add.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				selectPicFromLocal();
			}
		});
		 findViewById(R.id.back).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					finish();
				}
			});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		return true;
	}

	private void initData(final String content) {
		String url = "http://119.29.98.34:8080/chat/community/addTogetherShare.do";
		upLoude(url,content);
//		new Thread(new Runnable() {
//
//			@Override
//			public void run() {
//				Map<String, String> map = new HashMap<String, String>();
//				String userId = SharedPrefsUtil
//						.getUserId(PushFriendActivity.this);
//				map.put("userId", userId);
//				map.put("content", content);
//				map.put("file", "===");
//				String url = "http://119.29.98.34:8080/chat/community/addTogetherShare.do";
//				// String str = UploadImage.postForm(url, map,
//				// getBitmapFromUri(selectedImage));
//				// Log.e("str", str);
//
//				UploadImage.uploadFile(url,
//						UploadImage.saveBitmap(getBitmapFromUri(selectedImage)));
//			}
//		}).start();

		// Map<String, String> map = new HashMap<String, String>();
		// String userId = SharedPrefsUtil.getUserId(this);
		// map.put("userId", userId);
		// map.put("content", content);
		// map.put("file", "===");
		// // map.put("username","1515451");
		// // map.put("password", 123456+"");
		//
		// JSONObject jsonObject = new JSONObject(map);
		// // String url = "http://119.29.98.34:8080/chat/user/register.do";
		// String url =
		// "http://119.29.98.34:8080/chat/community/addTogetherShare.do";
		// rq = Volley.newRequestQueue(getApplicationContext());
		// JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST,
		// url,
		// jsonObject, new Listener<JSONObject>() {
		// @Override
		// public void onResponse(JSONObject response) {
		// Log.e("response", response + "");
		// try {
		// if (response.getInt("status") == 0) {
		// mHandler.sendMessage(new Message());
		// } else {
		// Toast.makeText(PushFriendActivity.this,
		// "发布失败，请重新发布", Toast.LENGTH_LONG).show();
		// }
		// } catch (JSONException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// Log.e("response", "response");
		// }
		//
		// }, new ErrorListener() {
		// @Override
		// public void onErrorResponse(VolleyError error) {
		// Log.e("response", error + "");
		// Toast.makeText(PushFriendActivity.this, "发布失败，请重新发布",
		// Toast.LENGTH_LONG).show();
		// }
		// }) {
		// @Override
		// public Map<String, String> getHeaders() throws AuthFailureError {
		// Map<String, String> headers = new HashMap<String, String>();
		// headers.put("User-Agent", "123");
		// return headers;
		// }
		// };
		// rq.add(req);
		// rq.start();
	}

	/**
	 * 从图库获取图片
	 */
	public void selectPicFromLocal() {
		Intent intent;
		if (Build.VERSION.SDK_INT < 19) {
			intent = new Intent(Intent.ACTION_GET_CONTENT);
			intent.setType("image/*");

		} else {
			intent = new Intent(
					Intent.ACTION_PICK,
					android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		}
		startActivityForResult(intent, REQUEST_CODE_LOCAL);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		if (resultCode == RESULT_OK) {
			selectedImage = data.getData();
			if (selectedImage != null) {
				img.setImageURI(selectedImage);
			}
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	private Bitmap getBitmapFromUri(Uri uri) {
		try {
			BitmapFactory.Options options = new BitmapFactory.Options();
			options.inSampleSize = 10;// 图片大小，设置越大，图片越不清晰，占用空间越小
			Bitmap bitmap = BitmapFactory.decodeStream(this
					.getContentResolver().openInputStream(uri), null, options);
			// 读取uri所在的图片
			return bitmap;
		} catch (Exception e) {
			Log.e("[Android]", e.getMessage());
			Log.e("[Android]", "目录为：" + uri);
			e.printStackTrace();
			return null;
		}
	}

	private void upLoude(String url, String content) {
		dialog.show();
		File file = UploadImage.saveBitmap(getBitmapFromUri(selectedImage));
		RequestParams params = new RequestParams();
		// 只包含字符串参数时默认使用BodyParamsEntity，
		// 类似于UrlEncodedFormEntity（"application/x-www-form-urlencoded"）。
		String userId = SharedPrefsUtil.getUserId(PushFriendActivity.this);
		params.addBodyParameter("userId", userId);
		params.addBodyParameter("content", content);
		params.addBodyParameter("file", file);

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
				Log.e("responseInfo", responseInfo+"===");
				Toast.makeText(PushFriendActivity.this, "发布成功",
						 Toast.LENGTH_LONG).show();
				dialog.dismiss();
				finish();
			}

			@Override
			public void onFailure(HttpException error, String msg) {
				Toast.makeText(PushFriendActivity.this, "发布失败，请重新发布",
						 Toast.LENGTH_LONG).show();
				Log.e("responseInfo", msg);
			}
		});
	}
}
