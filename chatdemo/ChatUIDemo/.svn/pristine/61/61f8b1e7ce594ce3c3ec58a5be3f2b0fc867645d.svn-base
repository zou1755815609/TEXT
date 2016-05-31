package com.easemob.chatuidemo.myui;

import com.easemob.EMCallBack;
import com.easemob.chat.EMChatManager;
import com.easemob.chatuidemo.R;
import com.easemob.chatuidemo.activity.LoginActivity;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.bean.SHARE_MEDIA;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class MySettingsFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_settings, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);

		Button logoutButton = (Button) getView().findViewById(R.id.btn_logout);
		logoutButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				EMChatManager.getInstance().logout(new EMCallBack() {

					@Override
					public void onSuccess() {
						getActivity().finish();
						startActivity(new Intent(getActivity(),
								LoginActivity.class));
					}

					@Override
					public void onProgress(int progress, String status) {

					}

					@Override
					public void onError(int code, String error) {

					}
				});
			}
		});

		getView().findViewById(R.id.btn_share).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						final SHARE_MEDIA[] displaylist = new SHARE_MEDIA[] {
								SHARE_MEDIA.WEIXIN, SHARE_MEDIA.WEIXIN_CIRCLE,
						// SHARE_MEDIA.QQ,SHARE_MEDIA.QZONE
						};
						new ShareAction(getActivity())
								.setDisplayList(displaylist).withText("会聚")
								.withTitle("欢迎使用会聚app")
								.withTargetUrl("http://www.baidu.com").open();

					}
				});
		getView().findViewById(R.id.btn_idea).setOnClickListener(
				new OnClickListener() {

					@Override
					public void onClick(View v) {
						getActivity().startActivity(
								new Intent(getActivity(),
										FeedBackActivity.class));
					}
				});
	}
}
