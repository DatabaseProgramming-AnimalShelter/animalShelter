package model;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class Reply implements Serializable {
	private int replyId;
	private int commentNo;
	private String replyWriter;
	private String replyContent;
	private Date regDate;
	
	public Reply() { }
	
	public Reply(int replyId, int commentNo, String replyWriter, String replyContent, Date regDate) {
		super();
		this.replyId = replyId;
		this.commentNo = commentNo;
		this.replyWriter = replyWriter;
		this.replyContent = replyContent;
		this.regDate = regDate;
	}
	public int getReplyId() {
		return replyId;
	}
	public void setReplyId(int replyId) {
		this.replyId = replyId;
	}
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public String getReplyWriter() {
		return replyWriter;
	}
	public void setReplyWriter(String replyWriter) {
		this.replyWriter = replyWriter;
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	@Override
	public String toString() {
		return "Reply [replyId=" + replyId + ", commentNo=" + commentNo + ", replyWriter=" + replyWriter 
				+ ", replyContent=" + replyContent + ", regDate=" + regDate + "]";
	}
}
