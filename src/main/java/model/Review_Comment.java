package model;

import java.util.Date;

public class Review_Comment {
	private int comment_id;
	private int post_id;
	private String user_id;
	private Date creationDate;
	private int parent;
	private String content;
	private String title;
	

	public Review_Comment(int comment_id, int post_id, String user_id, 
			Date creationDate, int parent, String content) {
		super();
		this.comment_id = comment_id;
		this.post_id = post_id;
		this.user_id = user_id;
		this.creationDate = creationDate;
		this.parent = parent;
		this.content = content;
	}
	
	public Review_Comment(int post_id, String user_id, int parent, 
			String content) {
		super();
		this.post_id = post_id;
		this.user_id = user_id;
		this.parent = parent;
		this.content = content;
	}

	public int getComment_id() {
		return comment_id;
	}


	public void setComment_id(int comment_id) {
		this.comment_id = comment_id;
	}


	public int getPost_id() {
		return post_id;
	}


	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}


	public String getUser_id() {
		return user_id;
	}


	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}


	public Date getCreationDate() {
		return creationDate;
	}


	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}


	public int getParent() {
		return parent;
	}


	public void setParent(int parent) {
		this.parent = parent;
	}


	public String getContent() {
		return content;
	}


	public void setContent(String content) {
		this.content = content;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
	
	

}
