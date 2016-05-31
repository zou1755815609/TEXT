package com.easemob.chatuidemo.myui;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.easemob.chatuidemo.R;
import com.easemob.chatuidemo.myui.MyData.FirendComment;
import com.easemob.chatuidemo.myui.MyData.FriendInfo;
import com.google.android.gms.internal.el;
import com.squareup.picasso.Picasso;


import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FiendListAdapt extends BaseAdapter implements OnItemClickListener {
	private List<FriendInfo> datas;
	private Context context;
	private FirendFragment fragment;

	public FiendListAdapt(List<FriendInfo> datas, Context context,FirendFragment fragment) {
		this.datas = datas;
		this.context = context;
		this.fragment = fragment;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return datas.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(
					R.layout.item_friend, null);
		}
		ImageView civ_lobbyquestions = (ImageView) convertView
				.findViewById(R.id.civ_lobbyquestions);
		ImageView img_push = (ImageView) convertView
				.findViewById(R.id.img_push);
		TextView tv_name = (TextView) convertView.findViewById(R.id.tv_name);
		TextView tv_content = (TextView) convertView
				.findViewById(R.id.tv_content);
		LinearLayout layout_comment = (LinearLayout) convertView
				.findViewById(R.id.layout_comment);
		TextView text_commit = (TextView) convertView
				.findViewById(R.id.tv_content);
		TextView tv__lobbyquestion_commentnum = (TextView) convertView
				.findViewById(R.id.tv__lobbyquestion_commentnum);
		TextView tv_lobbyquestion_time = (TextView) convertView
				.findViewById(R.id.tv_lobbyquestion_time);
		tv_name.setText(datas.get(position).getName());
		tv_content.setText(datas.get(position).getContent());
		tv_lobbyquestion_time.setText(getTime(datas.get(position).getTime()));
		if (datas.get(position).getTogetherShareCommentDetails() != null
				&& datas.get(position).getTogetherShareCommentDetails().size() > 0) {
			tv__lobbyquestion_commentnum.setText(datas.get(position)
					.getTogetherShareCommentDetails().size()+"");
			for (int i = 0; i < datas.get(position)
					.getTogetherShareCommentDetails().size(); i++) {
				FirendComment bean = datas.get(position)
						.getTogetherShareCommentDetails().get(i);
				layout_comment.addView(getTextView(bean));
			}

		}
		
		
		if (datas.get(position).getIcon() != null) {
			Picasso.with(context)
			  .load(datas.get(position).getIcon())
			  .into(civ_lobbyquestions);
		}
		if (datas.get(position).getImage() != null) {
			Picasso.with(context)
			  .load(datas.get(position).getImage())
			  .into(img_push);
			img_push.setVisibility(View.VISIBLE);
		}else{
			img_push.setVisibility(View.GONE);
		}
		return convertView;
	}

	private TextView getTextView(FirendComment bean) {
		TextView textView = new TextView(context);
		textView.setText(bean.getName() + " 回复：" + bean.getContent());
		return textView;
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

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		Intent intent = new Intent(context,FirendCommiit.class);
		intent.putExtra(FirendCommiit.PAMAS, datas.get(arg2));
		fragment.startActivityForResult(intent, 1);
	}
}
