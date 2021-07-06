package com.project.wf.notice;

/**
 * 
 * @author 왕지민
 * 회원 직원간의 메시지 전달을 위한 클래스
 *
 */
public class FarmManagement_NoticeMessageList {
	
	private String seq;
	private String priod;
	private String sendContent;
	private String receiveContent;
	private String memberNum;
	
	/**
	 * 메시지 리스트를 위한 객체 초기값
	 * @param seq	메시지 번호
	 * @param priod	메시지 보낸 시간
	 * @param sendContent	보낸 메시지 내용
	 * @param receiveContent	받은 메시지 내용
	 * @param memberNum	회원 번호
	 */
	public FarmManagement_NoticeMessageList(String seq, String priod, String sendContent, String receiveContent,
			String memberNum) {
		this.seq = seq;
		this.priod = priod;
		this.sendContent = sendContent;
		this.receiveContent = receiveContent;
		this.memberNum = memberNum;
	}
	
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getPriod() {
		return priod;
	}
	public void setPriod(String priod) {
		this.priod = priod;
	}
	public String getSendContent() {
		return sendContent;
	}
	public void setSendContent(String sendContent) {
		this.sendContent = sendContent;
	}
	public String getReceiveContent() {
		return receiveContent;
	}
	public void setReceiveContent(String receiveContent) {
		this.receiveContent = receiveContent;
	}
	public String getMemberNum() {
		return memberNum;
	}
	public void setMemberNum(String memberNum) {
		this.memberNum = memberNum;
	}
	@Override
	public String toString() {
		return "FarmManagement_NoticeMessageList [seq=" + seq + ", priod=" + priod + ", sendContent=" + sendContent
				+ ", receiveContent=" + receiveContent + ", memberNum=" + memberNum + "]";
	}
	
	
	
	
	
}
