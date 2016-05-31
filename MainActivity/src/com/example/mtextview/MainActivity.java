package com.example.mtextview;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Html;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.ImageSpan;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private MTextView mTextView;
	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.activity_main);
		mTextView = (MTextView) this.findViewById(R.id.mtextview);
		textView = (TextView) this.findViewById(R.id.textview);

		// Test();
		TestNormal();
		addForeColorSpan();
	}

	/**
	 * 文字颜色
	 */
	private void addForeColorSpan() {
		String htmlLinkText = "$gff$da156@9HHO酒店$覅$哦阿姐啊亟HG$$HH56541HFDS5621H54S14515211265124待破解jw-aj-文件-【阿娇JIUJIOJ12546KPAOFKAPOKNIOAN5561怒好害怕发票后就费劲啊放假啊姐啊我就15151论文【哦阿卡，,kvmaomom"
				+ "<p><a href=\"http://www.dreamdu.com/xhtml/\">超链接HTML入门</a>学习HTML!</p><p><font color=\"#aabb00\">颜色1";
		String s = "$gff$da156@9HHO酒店$覅$哦阿姐啊亟HG$$HH56541HFDS5621H54S14515211265124待破解jw-aj-文件-【阿娇JIUJIOJ12546KPAOFKAPOKNIOAN5561怒好害怕发票后就费劲啊放假啊姐啊我就15151论文【哦阿卡，,kvmaomom";
		SpannableString spanString = new SpannableString(s);
		for (int i = 0; i < (s.length() /3)-1; i++) {
			ForegroundColorSpan span = new ForegroundColorSpan(Color.RED);
			spanString.setSpan(span, 3+3 * i, 3+i * 3 + 3, 0);
		}
		mTextView.setMText(spanString);
		mTextView.setTextSize(15);
		mTextView.setTextColor(Color.GREEN);
		mTextView.invalidate();
		mTextView.setMovementMethod(LinkMovementMethod.getInstance());
		// CharSequence text = mTextView.getText();
		// if (text instanceof Spannable) {
		// int end = text.length();
		// Spannable sp = (Spannable) mTextView.getText();
		// URLSpan[] urls = sp.getSpans(0, end, URLSpan.class);
		// SpannableStringBuilder style = new SpannableStringBuilder(text);
		// style.clearSpans();// should clear old spans
		// for (URLSpan url : urls) {
		// CanClick myURLSpan = new CanClick();
		// style.setSpan(myURLSpan, sp.getSpanStart(url),
		// sp.getSpanEnd(url), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		// }
		// mTextView.setText(style);
		// }
	}

	private void Test() {
		mTextView.setBackgroundColor(Color.GREEN);
		String s = "$gff$da156@9HHO酒店$覅$哦阿姐啊亟待破解jw-aj-文件-【阿娇JIUJIOJ12546KPAOFKAPOKNIOAN5561怒好害怕发票后就费劲啊放假啊姐啊我就15151论文【哦阿卡，,kvmaomom";
		String source = "撒反对飞王瑞芳芳vfxdsdf司法所我日 35忍32534太 地方个的服务 34个的服务 34太过分的电饭锅电饭锅打三国杀个的服务 34太过分的电饭锅电饭锅打三国杀太过分的电饭锅电饭锅打三国杀水电费歌曲筒袜上课5乳房炎啊啊。";
		SpannableString ss = new SpannableString(source);

		int plus = 1;
		for (int i = 0; i < source.length() - 2; i += plus) {
			plus = (int) (Math.random() * 5);
			ImageSpan is = new ImageSpan(MainActivity.this, R.drawable.emoji_29);
			ss.setSpan(is, i, i + 1, 0);
		}
		BackgroundColorSpan span = new BackgroundColorSpan(Color.YELLOW);
		ss.setSpan(span, 10, 20, SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
		mTextView.setMText(ss);
		mTextView.invalidate();
		mTextView.setTextSize(15);
		mTextView.setMovementMethod(LinkMovementMethod.getInstance());
		CharSequence text = mTextView.getText();
		if (text instanceof Spannable) {
			int end = text.length();
			Spannable sp = (Spannable) mTextView.getText();
			URLSpan[] urls = sp.getSpans(0, end, URLSpan.class);
			SpannableStringBuilder style = new SpannableStringBuilder(text);
			style.clearSpans();// should clear old spans
			for (URLSpan url : urls) {
				CanClick myURLSpan = new CanClick();
				style.setSpan(myURLSpan, sp.getSpanStart(url),
						sp.getSpanEnd(url), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			}
			mTextView.setText(style);
		}

	}

	class CanClick extends ClickableSpan {

		@Override
		public void onClick(View widget) {
			Intent intent = new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_LAUNCHER);
			ComponentName cn = new ComponentName("com.easemob.chatuidemo",
					"com.easemob.chatuidemo.activity.RegisterActivity");
			intent.setComponent(cn);
			intent.setFlags(Intent.FILL_IN_DATA);
			intent.setAction("android.intent.action.VIEW");
			startActivity(intent);
			Log.e("onClick", "onClick");
			Toast.makeText(MainActivity.this, "onClick", Toast.LENGTH_LONG)
					.show();
		}

		@Override
		public void updateDrawState(TextPaint ds) {
			super.updateDrawState(ds);
			// Log.e("updateDrawState", "updateDrawState");
			// Toast.makeText(MainActivity.this, "updateDrawState",
			// Toast.LENGTH_LONG).show();
		}
	}

	private void TestNormal() {
		// String s =
		// "$gff$da156@9HHO酒店$覅$哦阿姐啊亟HG$$HH56541HFDS5621H54S14515211265124待破解jw-aj-文件-【阿娇JIUJIOJ12546KPAOFKAPOKNIOAN5561怒好害怕发票后就费劲啊放假啊姐啊我就15151论文【哦阿卡，,kvmaomom";
		// SpannableString spanString = new SpannableString(s);
		// ForegroundColorSpan span = new ForegroundColorSpan(Color.RED);
		// spanString.setSpan(span, 0, 9, 0);
		// BackgroundColorSpan span1 = new BackgroundColorSpan(Color.YELLOW);
		// spanString.setSpan(span1, 10, 20,
		// SpannableString.SPAN_EXCLUSIVE_EXCLUSIVE);
		// CanClick ca = new CanClick();
		// spanString.setSpan(ca, 10, 20, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		// textView.setText(spanString);
		// textView.setTextSize(15);
		// textView.setTextColor(Color.GREEN);
		// int firstIndexOf = s.indexOf("@");
		// if (firstIndexOf >= 0) {
		// textView.setClickable(true);
		// textView.setMovementMethod(LinkMovementMethod.getInstance());
		// } else {
		// textView.setClickable(false);
		// textView.setMovementMethod(null);
		// }
		String htmlLinkText = "$gff$da156@9HHO酒店$覅$哦阿姐啊亟HG$$HH56541HFDS5621H54S14515211265124待破解jw-aj-文件-【阿娇JIUJIOJ12546KPAOFKAPOKNIOAN5561怒好害怕发票后就费劲啊放假啊姐啊我就15151论文【哦阿卡，,kvmaomom"
				+ "<p><a href=\"http://www.dreamdu.com/xhtml/\">超链接HTML入门</a>学习HTML!</p><p><font color=\"#aabb00\">颜色1";
		textView.setText(Html.fromHtml(htmlLinkText));
		textView.setMovementMethod(LinkMovementMethod.getInstance());
		CharSequence text = textView.getText();
		if (text instanceof Spannable) {
			int end = text.length();
			Spannable sp = (Spannable) textView.getText();
			URLSpan[] urls = sp.getSpans(0, end, URLSpan.class);
			SpannableStringBuilder style = new SpannableStringBuilder(text);
			style.clearSpans();// should clear old spans
			for (URLSpan url : urls) {
				CanClick myURLSpan = new CanClick();
				style.setSpan(myURLSpan, sp.getSpanStart(url),
						sp.getSpanEnd(url), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
			}
			textView.setText(style);
		}
	}
	/**
	 * 获取股票名变蓝色的字符串
	 * 
	 * @param sb
	 * @return
	 */

	// public static SpannableStringBuilder formatStockString(String sb,
	// final Activity mContext) {
	// SpannableStringBuilder spannableStringBuilder;
	// if (TextUtils.isEmpty(sb)) {
	// spannableStringBuilder = new SpannableStringBuilder("");
	// return spannableStringBuilder;
	// }
	// int firstIndexOf = sb.indexOf("$");
	// if (SearchAllInfos.mGuPiaoInfoBean == null
	// || SearchAllInfos.mGuPiaoInfoBean.size() == 0) {
	// SearchAllInfos.mGuPiaoInfoBean = IOUtils.loadGuPiaoAllDatas();
	// }
	// String[] split = sb.split("\\$");
	// // String toSBC = ToSBC(sb);
	// spannableStringBuilder = new SpannableStringBuilder(sb);
	// int indexFirst;
	// int indexEnd;
	// class CanClick extends ClickableSpan {
	// private GuPiaoInfoBean bean;
	//
	// public CanClick(GuPiaoInfoBean bean) {
	// super();
	// this.bean = bean;
	// }
	//
	// @Override
	// public void onClick(View widget) {
	// Intent intent = new Intent(mContext,
	// GeguxiangqingActivity.class);
	// intent.putExtra("stockInfoId", bean.id);
	// intent.putExtra("stockCode", bean.code);
	// mContext.startActivity(intent);
	// mContext.overridePendingTransition(R.anim.trans_next_in,
	// R.anim.trans_next_out);
	// }
	//
	// @Override
	// public void updateDrawState(TextPaint ds) {
	// super.updateDrawState(ds);
	// ds.setColor(UIUtils.getColor(R.color.text_4A90E2));
	// ds.setUnderlineText(false);
	// }
	// }
	// if (firstIndexOf >= 0) {
	// for (int i = 0; i < split.length; i++) {
	// indexFirst = sb.indexOf(split[i]) - 1;
	// indexEnd = sb.indexOf(split[i]) + split[i].length();
	// if ((indexFirst >= 0) && (indexEnd <= sb.length())) {
	// for (GuPiaoInfoBean bean : SearchAllInfos.mGuPiaoInfoBean) {
	// if ((bean.name + "(" + bean.code + ")")
	// .equals(split[i])) {
	// //
	// highLightText.append("<canclick>").append("<font color=\"#4A90E2\">").append("$"+split[i]+"$").append("</font>").append("</canclick>");
	// ForegroundColorSpan foregroundColorSpan = new ForegroundColorSpan(
	// UIUtils.getColor(R.color.text_4A90E2));
	// spannableStringBuilder.setSpan(foregroundColorSpan,
	// indexFirst, indexEnd + 1,
	// Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
	// CanClick canClick = new CanClick(bean);
	// spannableStringBuilder.setSpan(canClick,
	// indexFirst, indexEnd + 1,
	// Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
	// break;
	// }
	// }
	// }
	// }
	// } else {
	// return spannableStringBuilder;
	// }
	// // LogUtils.e("spannableStringBuilder--"+spannableStringBuilder);
	// return spannableStringBuilder;
	// }
}
