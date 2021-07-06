package com.project.wf.resource;

/**
 * 
 * @author 왕지민
 * 농작물 구매내역을 위한 클래스 객체 
 *
 */
public class FarmManagement_FVBuyList {

	private String memNum;
	private String fvType;
	private String fvName;
	private int fvCount;
	private int fvPrice;
	private int fvPeriod;
	
	/**
	 * 농작물 구매내역을 위한 객체 초기값 메소드
	 * @param memNum 	회원번호
	 * @param fvType	농작물 종류
	 * @param fvName	농작물 이름
	 * @param fvCount	농작물 구매 수량
	 * @param fvPrice	농작물 구매 가격
	 * @param fvPeriod	농작물 구매일
	 */
	public FarmManagement_FVBuyList(int memNum, String fvType, String fvName, int fvCount, int fvPrice,
			int fvPeriod) {
		this.memNum = String.format("X%03d", memNum);
		this.fvType = fvType;
		this.fvName = fvName;
		this.fvCount = fvCount;
		this.fvPrice = fvPrice;
		this.fvPeriod = fvPeriod;
	}
	
	public String getMemNum() {
		return memNum;
	}
	public void setMemNum(String memNum) {
		this.memNum = memNum;
	}
	public String getFvType() {
		return fvType;
	}
	public void setFvType(String fvType) {
		this.fvType = fvType;
	}
	public String getFvName() {
		return fvName;
	}
	public void setFvName(String fvName) {
		this.fvName = fvName;
	}
	public int getFvCount() {
		return fvCount;
	}
	public void setFvCount(int fvCount) {
		this.fvCount = fvCount;
	}
	public int getFvPrice() {
		return fvPrice;
	}
	public void setFvPrice(int fvPrice) {
		this.fvPrice = fvPrice;
	}
	public int getFvPeriod() {
		return fvPeriod;
	}
	public void setFvPeriod(int fvPeriod) {
		this.fvPeriod = fvPeriod;
	}
	@Override
	public String toString() {
		return "FarmManagement_FVBuyList [fvNum=" + memNum + ", fvType=" + fvType + ", fvName=" + fvName + ", fvCount="
				+ fvCount + ", fvPrice=" + fvPrice + ", fvPeriod=" + fvPeriod + "]";
	}

	
}
