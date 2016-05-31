package com.easemob.chatuidemo.myui;

import com.easemob.chatuidemo.R;

import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.ProgressDialog;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class FeedBackActivity extends Activity {
	private TextView text_commit;
	private EditText edit_text;
	ProgressDialog dialog;
	private Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			dialog.dismiss();
			Toast.makeText(FeedBackActivity.this, "提交成功", Toast.LENGTH_LONG).show();
			edit_text.setText("");
		};
	};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
        text_commit = (TextView) findViewById(R.id.text_commit);
        edit_text = (EditText) findViewById(R.id.edit_text);
        dialog = new ProgressDialog(FeedBackActivity.this);
        text_commit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
					String str = edit_text.getText().toString();
					if (str != null &&! "".equals(str)) {
								dialog.show();
								mHandler.sendEmptyMessageDelayed(1, 1000);
					}else {
						Toast.makeText(FeedBackActivity.this, "输入内容不能为空", Toast.LENGTH_LONG).show();
					}
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
    
}
