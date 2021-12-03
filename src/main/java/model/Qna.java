package model;

public class Qna {

	int qna_id;
	int qna_category_id;
	String title;
	String qna_type;
	String content;
//	String password;
	String user_id;
	String user_name;
	
	//create
	public Qna(String title, String user_id, int qna_category_id, String content) {
		super();
		this.title = title;
		this.qna_category_id = qna_category_id;
		this.content = content;
		this.user_id = user_id;
	}
	//find review 
	public Qna(int qna_id, int category_id, String title, String user_id, String qna_type, String content,
			String password, String user_name) {
		super();
		this.qna_id = qna_id;
		this.qna_category_id = category_id;
		this.title = title;
		this.user_id = user_id;
		this.qna_type = qna_type;
		this.content = content;
		this.user_name = user_name;
	}

	public Qna(int qna_id, int qna_category_id, String title, String user_id, String content) {
		super();
		this.qna_id = qna_id;
		this.qna_category_id = qna_category_id;
		this.title = title;
		this.content = content;
		this.user_id = user_id;
	}
	public int getQna_id() {
		return qna_id;
	}
	public void setQna_id(int qna_id) {
		this.qna_id = qna_id;
	}
	public int getCategory_id() {
		return qna_category_id;
	}
	public void setCategory_id(int category_id) {
		this.qna_category_id = category_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getQna_type() {
		return qna_type;
	}
	public void setQna_type(String qna_type) {
		this.qna_type = qna_type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
//	public String getPassword() {
//		return password;
//	}
//	public void setPassword(String password) {
//		this.password = password;
//	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	
	
}
