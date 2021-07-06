package com.project.wf.notice;

/**
 * 
 * @author 왕지민
 * 알림함 공지사항 리스트 클래스
 *
 */
public class FarmManagement_NoticeBoardList {
	
	private String seq;
	private String name;
	private String priod;
	private String content;
	
	/**
	 * 공지사항 리스트 객체 초기값
	 * @param seq	공지사항 번호
	 * @param name	공지사항 제목
	 * @param priod	공지사항 기간
	 * @param content	공지사항 내용
	 */
	public FarmManagement_NoticeBoardList(String seq, String name, String priod, String content) {
		this.seq = seq;
		this.name = name;
		this.priod = priod;
		this.content = content;
	}
	@Override
	public String toString() {
		return "FarmManagement_NotioeBoardList [seq=" + seq + ", name=" + name + ", priod=" + priod + ", content="
				+ content + "]";
	}
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