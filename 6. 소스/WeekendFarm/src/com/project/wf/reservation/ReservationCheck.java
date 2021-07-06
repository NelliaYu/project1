package com.project.wf.reservation;

/**
 * 방문 예약 정보를 담는 객체
 * @author 이미현
 *
 */
public class ReservationCheck {
	
	//회원번호/예약번호/텃밭번호/예약방문일/상태 
	
	private String memberNum;
	private String reservationNum;
	private String farmNum;
	private String date;
	private String status;
	
	
	//생성자
	public ReservationCheck() {
		
	}
	
	public ReservationCheck(String memberNum, String reservationNum, String farmNum, String date, String status) {
		super();
		this.memberNum = memberNum;
		this.reservationNum = reservationNum;
		this.farmNum = farmNum;
		this.date = date;
		this.status = status;
	}


	//Getter, Setter
	public String getMemberNum() {
		return memberNum;
	}


	public void setMemberNum(String memberNum) {
		this.memberNum = memberNum;
	}


	public String getReservationNum() {
		return reservationNum;
	}


	public void setReservationNum(String reservationNum) {
		this.reservationNum = reservationNum;
	}


	public String getFarmNum() {
		return farmNum;
	}


	public void setFarmNum(String farmNum) {
		this.farmNum = farmNum;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	@Override
	public String toString() {
		return "ReservationCheck [memberNum=" + memberNum + ", reservationNum=" + reservationNum + ", farmNum="
				+ farmNum + ", date=" + date + ", status=" + status + "]";
	}
	
	
	
}
