package model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Qna_Reply implements Serializable {
	
	private int reply_id;
	private int comment_no;
	private String reply_writer;
	private String reply_content;
	private Date reg_date;
	
	
	public Qna_Reply(int reply_id, int comment_no, String reply_writer, String reply_content, Date reg_date) {
		super();
		this.reply_id = reply_id;
		this.comment_no = comment_no;
		this.reply_writer = reply_writer;
		this.reply_content = reply_content;
		this.reg_date = reg_date;
	}

	public int getReply_id() {
		return reply_id;
	}
	public void setReply_id(int reply_id) {
		this.reply_id = reply_id;
	}
	public int getComment_no() {
		return comment_no;
	}
	public void setComment_no(int comment_no) {
		this.comment_no = comment_no;
	}
	public String getReply_writer() {
		return reply_writer;
	}
	public void setReply_writer(String reply_writer) {
		this.reply_writer = reply_writer;
	}
	public String getReply_content() {
		return reply_content;
	}
	public void setReply_content(String reply_content) {
		this.reply_content = reply_content;
	}
	public Date getReg_date() {
		return reg_date;
	}
	public void setReg_date(Date reg_date) {
		this.reg_date = reg_date;
	}
	@Override
	public String toString() {
		return "Reply [replyId=" + reply_id + ", commentNo=" + comment_no + ", replyWriter=" + reply_writer 
				+ ", replyContent=" + reply_content + ", regDate=" + reg_date + "]";
	}
}
