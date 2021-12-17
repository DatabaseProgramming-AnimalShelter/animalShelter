package model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Qna_Comment implements Serializable {
	private int comment_no;
	private int qna_id; //해당 qna게시글 번호
	private Date reg_date;
	private String comment_content;

	public Qna_Comment(int qna_id,String comment_content) {
		super();
		this.qna_id = qna_id;
		this.comment_content = comment_content;
	}
	public int getComment_no() {
		return comment_no;
	}
	public void setComment_no(int comment_no) {
		this.comment_no = comment_no;
	}
	public int getQna_id() {
		return qna_id;
	}
	public void setQna_id(int qna_id) {
		this.qna_id = qna_id;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	public String getComment_content() {
		return comment_content;
	}
	public void setComment_content(String comment_content) {
		this.comment_content = comment_content;
	}
	@Override
	public String toString() {
		return "Comment [commentNo=" + comment_no + ", qna_id=" + qna_id + ", regDate=" + reg_date + ", commentContent="
				+ comment_content + "]";
	}	
}