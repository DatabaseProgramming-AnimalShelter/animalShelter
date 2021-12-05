package model;

import java.util.Date;

public class Qna {

	int qna_id;
	String qna_writer;
	String qna_title;
	String qna_content;
	String qna_password;
	Date qna_date;
	int qna_category_id;
	String qna_type;

	public Qna(int qna_id, String qna_writer, String qna_title, String qna_content, String qna_password, Date qna_date,
			int qna_category_id, String qna_type) {
		super();
		this.qna_id = qna_id;
		this.qna_writer = qna_writer;
		this.qna_title = qna_title;
		this.qna_content = qna_content;
		this.qna_password = qna_password;
		this.qna_date = qna_date;
		this.qna_category_id = qna_category_id;
		this.qna_type = qna_type;
	}

	//create	
	public Qna(String qna_writer, String qna_title, String qna_content, String qna_password, int qna_category_id) {
		super();
		this.qna_writer = qna_writer;
		this.qna_title = qna_title;
		this.qna_content = qna_content;
		this.qna_password = qna_password;
		this.qna_category_id = qna_category_id;
	}
	//update
	public Qna(int qna_id, String qna_writer, String qna_title, String qna_content, String qna_password,
			int qna_category_id) {
		super();
		this.qna_id = qna_id;
		this.qna_writer = qna_writer;
		this.qna_title = qna_title;
		this.qna_content = qna_content;
		this.qna_password = qna_password;
		this.qna_category_id = qna_category_id;
	}

	public int getQna_id() {
		return qna_id;
	}

	public void setQna_id(int qna_id) {
		this.qna_id = qna_id;
	}

	public String getQna_writer() {
		return qna_writer;
	}

	public void setQna_writer(String qna_writer) {
		this.qna_writer = qna_writer;
	}

	public String getQna_title() {
		return qna_title;
	}

	public void setQna_title(String qna_title) {
		this.qna_title = qna_title;
	}

	public String getQna_content() {
		return qna_content;
	}

	public void setQna_content(String qna_content) {
		this.qna_content = qna_content;
	}

	public String getQna_password() {
		return qna_password;
	}

	public void setQna_password(String qna_password) {
		this.qna_password = qna_password;
	}

	public Date getQna_date() {
		return qna_date;
	}

	public void setQna_date(Date qna_date) {
		this.qna_date = qna_date;
	}

	public int getQna_category_id() {
		return qna_category_id;
	}

	public void setQna_category_id(int qna_category_id) {
		this.qna_category_id = qna_category_id;
	}

	public String getQna_type() {
		return qna_type;
	}

	public void setQna_type(String qna_type) {
		this.qna_type = qna_type;
	}

}
