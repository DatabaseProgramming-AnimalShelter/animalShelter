package model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@SuppressWarnings("serial")
public class Qna_Comment implements Serializable {
	private int comment_no;
	private String qna_id; //해당 qna게시글 번호
	private Date reg_date;
	private String comment_content;
	private String comment_writer;  
	private Qna qna;
	private List<Qna_Reply> replies;	// Reply 객체들의 list 참조
	
	public int getComment_no() {
		return comment_no;
	}

	public void setComment_no(int comment_no) {
		this.comment_no = comment_no;
	}

	public String getQna_id() {
		return qna_id;
	}

	public void setQna_id(String qna_id) {
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

	public String getComment_writer() {
		return comment_writer;
	}

	public void setComment_writer(String comment_writer) {
		this.comment_writer = comment_writer;
	}

	public Qna getQna() {
		return qna;
	}

	public void setQna(Qna qna) {
		this.qna = qna;
	}

	public List<Qna_Reply> getReplies() {
		return replies;
	}

	public void setReplies(List<Qna_Reply> replies) {
		this.replies = replies;
	}

	@Override
	public String toString() {
		return "Comment [commentNo=" + comment_no + ", qna_id=" + qna_id + ", regDate=" + reg_date + ", commentContent="
				+ comment_content + ", \n user=" + comment_writer + ", replies=" + replies + "]";
	}	
}