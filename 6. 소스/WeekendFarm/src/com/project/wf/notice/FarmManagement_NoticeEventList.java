package com.project.wf.notice;

/**
 * 
 * @author 왕지민
 * 프로모션 리스트 클래스
 *
 */
public class FarmManagement_NoticeEventList {
	
	private String seq;
	private String name;
	private String type;
	private String priod;
	
	/**
	 * 프로모션 리스트 객체 초기값
	 * @param seq	프로모션 번호
	 * @param name	프로모션 제목
	 * @param type	프로모션 종류
	 * @param priod	프로모션 기간
	 * @param content	프로모션 내용
	 */
	public FarmManagement_NoticeEventList(String seq, String name, String type, String priod, String content) {
		this.seq = seq;
		this.name = name;
		this.type = type;
		this.priod = priod;
		this.content = content;
	}
	
	
	@Override
	public String toString() {
		return "FarmManagement_NoticeEventList [seq=" + seq + ", name=" + name + ", type=" + type + ", priod=" + priod
				+ ", content=" + content + "]";
	}
	private String content;

	

	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getPriod() {
		return priod;
	}
	public void setPriod(String priod) {
		this.priod = priod;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	


}
