package com.easemob.chatuidemo.myui;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class SharedPrefsUtil {

	public final static String userId = "userId";
	public final static String username = "username";

	public static void putUserId(Context context, String value) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				"configuration", 0);

		SharedPreferences.Editor editor = sharedPreferences.edit();

		editor.putString(userId, value);

		editor.commit();
	}

	public static String getUserId(Context context) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				"configuration", 0);

		String name = sharedPreferences.getString(userId, "0");
		return name;
	}
	public static void putUserName(Context context, String value) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				"configuration", 0);

		SharedPreferences.Editor editor = sharedPreferences.edit();

		editor.putString(username, value);

		editor.commit();
	}

	public static String getUserName(Context context) {
		SharedPreferences sharedPreferences = context.getSharedPreferences(
				"configuration", 0);

		String name = sharedPreferences.getString(username, "匿名");
		return name;
	}

	public static void putValue(Context context, String key, boolean value) {
		Editor sp = context.getSharedPreferences(userId, Context.MODE_PRIVATE)
				.edit();
		sp.putBoolean(key, value);
		sp.commit();
	}

	public static void putValue(Context context, String key, String value) {
		Editor sp = context.getSharedPreferences(userId, Context.MODE_PRIVATE)
				.edit();
		sp.putString(key, value);
		sp.commit();
	}

	public static int getValue(Context context, String key, int defValue) {
		SharedPreferences sp = context.getSharedPreferences(userId,
				Context.MODE_PRIVATE);
		int value = sp.getInt(key, defValue);
		return value;
	}

	public static boolean getValue(Context context, String key, boolean defValue) {
		SharedPreferences sp = context.getSharedPreferences(userId,
				Context.MODE_PRIVATE);
		boolean value = sp.getBoolean(key, defValue);
		return value;
	}

	public static String getValue(Context context, String key, String defValue) {
		SharedPreferences sp = context.getSharedPreferences(userId,
				Context.MODE_PRIVATE);
		String value = sp.getString(key, defValue);
		return value;
	}
}
