package com.easemob.chatuidemo.myui;

import java.io.Serializable;


	public class UserData implements Serializable{
		public UserData(){}
		// userId 用户id
		// nickname 昵称（空则不修改）
		// constellation 星座（空则不修改）
		// phone 电话（空则不修改）
		// birthday 生日（空则不修改）
		// file 图片文件（空则不修改）
		private int id;
		private int userId;
		private String nickname;
		private String constellation;
		private String phone;
		private String birthday;
		private String icon;
		private String username;

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public String getNickname() {
			return nickname;
		}

		public void setNickname(String nickname) {
			this.nickname = nickname;
		}

		public String getConstellation() {
			return constellation;
		}

		public void setConstellation(String constellation) {
			this.constellation = constellation;
		}

		public String getPhone() {
			return phone;
		}

		public void setPhone(String phone) {
			this.phone = phone;
		}

		public String getBirthday() {
			return birthday;
		}

		public void setBirthday(String birthday) {
			this.birthday = birthday;
		}

		public String getIcon() {
			return icon;
		}

		public void setIcon(String icon) {
			this.icon = icon;
		}

	}
