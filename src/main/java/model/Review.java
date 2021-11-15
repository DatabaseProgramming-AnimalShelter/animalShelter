package model;

import java.util.Date;

public class Review {
	private int post_id;
	private String animal_id;
	private String writer;
	private String title;
	private String content;
	private Date creationDate;
	private String image;
	
	public Review(int post_id, String animal_id, String writer, String title, String content, Date creationDate,
			String image) {
		super();
		this.post_id = post_id;
		this.animal_id = animal_id;
		this.writer = writer;
		this.title = title;
		this.content = content;
		this.creationDate = creationDate;
		this.image = image;
	}
	
	public int getPost_id() {
		return post_id;
	}
	public void setPost_id(int post_id) {
		this.post_id = post_id;
	}
	public String getAnimal_id() {
		return animal_id;
	}
	public void setAnimal_id(String animal_id) {
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
	

}
