package kr.or.connect.dto;

import java.sql.Date;

public class TodoDto {
	private long id;
	private String title;
	private String name;
	private int sequence;
	private String type;
//	private String regDate;
	private Date regDate;
	
	// 기본 생성자 만들기.
	public TodoDto() {
		this(0, "notitle", "noname", 0, "TODO", new Date(0));
	}

	public TodoDto(long id, String title, String name, int sequence, String type, Date regDate) {
		this.id = id;
		this.title = title;
		this.name = name;
		this.sequence = sequence;
		this.type = type;
		this.regDate = regDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() { // 메서드 순서 조정.
		return "Todo [id=" + id + ", title=" + title + ", name=" + name + ", sequence=" + sequence + ", type=" + type
			+ ", regDate=" + regDate + "]";
	} // 메서드 사이 공간.
	
}
