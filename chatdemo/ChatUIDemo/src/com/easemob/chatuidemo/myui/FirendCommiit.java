package com.easemob.chatuidemo.myui;

import java.text.SimpleDateFormat;
import java.util.Date;
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
import com.easemob.chatuidemo.activity.LoginActivity;
import com.easemob.chatuidemo.myui.MyData.FirendComment;
import com.easemob.chatuidemo.myui.MyData.FriendInfo;
import com.squareup.picasso.Picasso;

import android.app.Activity;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class FirendCommiit extends Activity {
	public static final String PAMAS = "PAMAS";
	private FriendInfo info;

	private RequestQueue rq = null;
	private EditText edit;
	private Button send;
	LinearLayout layout_comment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_commit);
		info = (FriendInfo) getIntent().getSerializableExtra(PAMAS);
		if (info == null) {
			Toast.makeText(FirendCommiit.this, "该动态已不存在", Toast.LENGTH_LONG)
					.show();
			return;
		}
		 findViewById(R.id.back).setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					finish();
				}
			});
		ImageView img_push = (ImageView)findViewById(R.id.img_push);
		edit = (EditText) findViewById(R.id.edit);
		send = (Button) findViewById(R.id.send);
		ImageView civ_lobbyquestions = (ImageView) findViewById(R.id.civ_lobbyquestions);
		TextView tv_name = (TextView) findViewById(R.id.tv_name);
		TextView tv_content = (TextView) findViewById(R.id.tv_content);
		 layout_comment = (LinearLayout) findViewById(R.id.layout_comment);
		TextView text_commit = (TextView) findViewById(R.id.tv_content);
		TextView tv__lobbyquestion_commentnum = (TextView) findViewById(R.id.tv__lobbyquestion_commentnum);
		TextView tv_lobbyquestion_time = (TextView) findViewById(R.id.tv_lobbyquestion_time);
		tv_name.setText(info.getName());
		tv_content.setText(info.getContent());
		tv_lobbyquestion_time.setText(getTime(info.getTime()));
		if (info.getTogetherShareCommentDetails() != null
				&& info.getTogetherShareCommentDetails().size() > 0) {
			tv__lobbyquestion_commentnum.setText(info
					.getTogetherShareCommentDetails().size()+"");
			for (int i = 0; i < info.getTogetherShareCommentDetails().size(); i++) {
				FirendComment bean = info.getTogetherShareCommentDetails().get(
						i);
				layout_comment.addView(getTextView(bean));
			}

		}
		if (info.getIcon() != null) {
			Picasso.with(FirendCommiit.this)
			  .load(info.getIcon())
			  .into(civ_lobbyquestions);
		}
		if (info.getImage() != null) {
			Picasso.with(FirendCommiit.this)
			  .load(info.getImage())
			  .into(img_push);
			img_push.setVisibility(View.VISIBLE);
		}else{
			img_push.setVisibility(View.GONE);
		}
		send.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				String str = edit.getText().toString();
				if (str != null && !"".equals(str)) {
					initData(str, info.getId());
				} else {
					Toast.makeText(FirendCommiit.this, "",
							Toast.LENGTH_LONG).show();
				}
			}
		});
	}

	private String getTime(Long time) {
		Date date = new Date(time);
		String strs = "";
		try {
			// yyyy��ʾ��MM��ʾ��dd��ʾ��
			// yyyy-MM-dd�����ڵĸ�ʽ������2015-12-12�����Ҫ�õ�2015��12��12�վͻ���yyyy��MM��dd��
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			// ���и�ʽ��
			strs = sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strs;
	}

	private TextView getTextView(FirendComment bean) {
		TextView textView = new TextView(FirendCommiit.this);
		textView.setText(bean.getName() + " 回复:" + bean.getContent());
		return textView;
	}
	private TextView getTextView(String name,String content) {
		TextView textView = new TextView(FirendCommiit.this);
		textView.setText(name + " 回复:" + content);
		return textView;
	}

	private void initData(final String content, int id) {

		Map<String, String> map = new HashMap<String, String>();
		// userId �û�id
		// togetherShareId �۷���id
		// togetherShareCommentId �۷�������id(0��ʾֱ�ӻظ�¥������0��ʾ�ظ�����)
		// content ��������
		String userId = SharedPrefsUtil.getUserId(this);
		map.put("userId", userId);
		map.put("togetherShareId", id + "");
		map.put("togetherShareCommentId", 0 + "");
		map.put("content", content);
		// map.put("username","1515451");
		// map.put("password", 123456+"");

		JSONObject jsonObject = new JSONObject(map);
		// String url = "http://119.29.98.34:8080/chat/user/register.do";
		String url = "http://119.29.98.34:8080/chat/community/addTogetherShareComment.do";
		rq = Volley.newRequestQueue(getApplicationContext());
		JsonObjectRequest req = new JsonObjectRequest(Request.Method.POST, url,
				jsonObject, new Listener<JSONObject>() {
					@Override
					public void onResponse(JSONObject response) {
						Log.e("response", response + "");
						try {
							if (response.getInt("status") == 0) {
								edit.setText("");
								Toast.makeText(FirendCommiit.this, "回复成功",
										Toast.LENGTH_LONG).show();
								String name = SharedPrefsUtil.getUserName(FirendCommiit.this);
								 layout_comment.addView(getTextView(name,content));
							} else {
								Toast.makeText(FirendCommiit.this,
										"回复失败", Toast.LENGTH_LONG).show();
							}
						} catch (JSONException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						Log.e("response", "response");
					}

				}, new ErrorListener() {
					@Override
					public void onErrorResponse(VolleyError error) {
						Log.e("response", error + "");
						Toast.makeText(FirendCommiit.this, "回复失败",
								Toast.LENGTH_LONG).show();
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
