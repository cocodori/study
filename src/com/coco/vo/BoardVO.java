package com.coco.vo;

import java.util.Date;

public class BoardVO {

	private int bno;
	private int p_bno, level;	//level은 답글의 개수다.
	private String title, content, imgName, id;
	private Date regdate;
	
	public BoardVO() {	}
	
	public BoardVO(String title, String content, String imgName, String id) {
		this.title = title;
		this.content = content;
		this.imgName = imgName;
		this.id = id;
	}
	
	public BoardVO(int bno, int p_bno, int level, String title, String content, String id, Date regdate) {
		super();
		this.bno = bno;
		this.p_bno = p_bno;
		this.level = level;
		this.title = title;
		this.content = content;
		this.id = id;
		this.regdate = regdate;
	}
	public BoardVO(int bno, int p_bno, int level, String title, String content, String imgName, String id,
			Date regdate) {
		super();
		this.bno = bno;
		this.p_bno = p_bno;
		this.level = level;
		this.title = title;
		this.content = content;
		this.imgName = imgName;
		this.id = id;
		this.regdate = regdate;
	}

	public int getBno() {
		return bno;
	}
	public void setBno(int bno) {
		this.bno = bno;
	}
	public int getP_bno() {
		return p_bno;
	}
	public void setP_bno(int p_bno) {
		this.p_bno = p_bno;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
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
	public String getImgName() {
		return imgName;
	}
	public void setImgName(String imgName) {
		this.imgName = imgName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	@Override
	public String toString() {
		return "BoardVO [bno=" + bno + ", p_bno=" + p_bno + ", level=" + level + ", title=" + title + ", content="
				+ content + ", imgName=" + imgName + ", id=" + id + ", regdate=" + regdate + "]";
	}
	
	
}
