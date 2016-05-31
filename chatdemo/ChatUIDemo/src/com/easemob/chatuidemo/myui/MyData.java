package com.easemob.chatuidemo.myui;

import java.io.Serializable;
import java.util.List;

public class MyData implements Serializable {

	// "status": 0,
	// "message": "��ȡ�ɹ�",
	private int status;
	private String message;
	private List<FriendInfo> data;

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<FriendInfo> getData() {
		return data;
	}

	public void setData(List<FriendInfo> data) {
		this.data = data;
	}

	public class FriendInfo implements Serializable {

		public Long getTime() {
			return time;
		}

		public void setTime(Long time) {
			this.time = time;
		}

		// "id": 4,
		// "name": null,
		// "userId": 4,
		// "icon": null,
		// "content": "����Ǵ�ɵ����",
		// "time": "2016-05-14 23:20:38"
		private String name;
		private String content;
		private Long time;
		private String icon;
		private int id;
		private String image ;
		private String username ;
		public String getImage() {
			return image;
		}

		public void setImage(String image) {
			this.image = image;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		private int userId;
		private List<FirendComment> togetherShareCommentDetails;

		public List<FirendComment> getTogetherShareCommentDetails() {
			return togetherShareCommentDetails;
		}

		public void setTogetherShareCommentDetails(
				List<FirendComment> togetherShareCommentDetails) {
			this.togetherShareCommentDetails = togetherShareCommentDetails;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public String getIcon() {
			return icon;
		}

		public void setIcon(String icon) {
			this.icon = icon;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

	}

	public class FirendComment implements Serializable {
		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public Long getTime() {
			return time;
		}

		public void setTime(Long time) {
			this.time = time;
		}

		public String getIcon() {
			return icon;
		}

		public void setIcon(String icon) {
			this.icon = icon;
		}

		public String getReply() {
			return reply;
		}

		public void setReply(String reply) {
			this.reply = reply;
		}

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public int getUserId() {
			return userId;
		}

		public void setUserId(int userId) {
			this.userId = userId;
		}

		// "id": 1,
		// "content": "���?��һ��",
		// "time": 1463277862000,
		// "userId": 3,
		// "name": "������",
		// "icon": null,
		// "reply": ""
		private String name;
		private String content;
		private Long time;
		private String icon;
		private String reply;
		private int id;
		private int userId;
	}

}
