package model;

import java.util.Date;

public class Review {
	private int post_id;
	private int animal_id;
	private String writer;
	private String title;
	private Date creationDate;
	private String content;
	private String image;
	
	// Review_Commnet
	private Date comment_creationDate;
	
	public Review(int post_id, int animal_id, String writer, String title, 
			String content, Date creationDate, String image) {
		super();
		this.post_id = post_id;
		this.animal_id = animal_id;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.creationDate = creationDate;
		this.image = image;
	}
	
	public Review( int animal_id, String writer, String title, 
			String content, Date creationDate, String image) {
		super();
		this.animal_id = animal_id;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.creationDate = creationDate;
		this.image = image;
	}
	
	public Review( int animal_id, String writer, 
			String title, String content) {
		super();
		this.animal_id = animal_id;
		this.writer = writer;
		this.title = title;
		this.content = content;
	}
	
	public Review( int animal_id, String writer, 
			String title, String content, String image) {
		super();
		this.animal_id = animal_id;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.image = image;
	}
	
	public Review( int post_id, int animal_id, String writer, 
			String title, String content) {
		super();
		this.post_id = post_id;
		this.animal_id = animal_id;
		this.writer = writer;
		this.title = title;
		this.content = content;
	}
	
	public Review( int post_id, int animal_id, String writer, 
			String title, String content, Date creationDate) {
		super();
		this.post_id = post_id;
		this.animal_id = animal_id;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.creationDate = creationDate;
	}
	
	
	public Review(int post_id, String title, String content) {
		super();
		this.post_id = post_id;
		this.title = title;
		this.content = content;
	}
	
	// findReviewCommentList에서 쓰임
	public Review(int post_id, String writer, String title, Date comment_creationDate) {
		super();
		this.post_id = post_id;
		this.writer = writer;
		this.title = title;
		this.comment_creationDate = comment_creationDate;
	}	

	public Review(Object parseInt) {
		// TODO Auto-generated constructor stub
	}

	public Review(int post_id, String title, String content, 
			java.sql.Date creationDate, String writer, int animal_id) {
		// TODO Auto-generated constructor stub
		super();
		this.post_id = post_id;
		this.title = title;
		this.content = content;
		this.creationDate = creationDate;
		this.writer = writer;
		this.animal_id = animal_id;
	}


	public int getPost_id() {
		return post_id;
	}
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	public int getAnimal_id() {
		return animal_id;
	}
	public void setAnimal_id(int animal_id) {
		this.animal_id = animal_id;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

	public Date getComment_creationDate() {
		return comment_creationDate;
	}

	public void setComment_creationDate(Date comment_creationDate) {
		this.comment_creationDate = comment_creationDate;
	}
	

}
