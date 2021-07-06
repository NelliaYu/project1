package com.project.wf.resource;

/**
 * 
 * @author 왕지민
 * 농작물 재고 리스트 클래스
 *
 */
public class FarmManagement_FVList {
	
	private String fvNum;
	private String fvType;
	private String fvName;
	private int fvPrice;
	private String fvLevel;
	private int fvPeriod;
	private int fvRestCount;

	/**
	 * 농작물 객체 리스트 초기값
	 * @param fvNum		농작물 번호	
	 * @param fvType	농작물 종류
	 * @param fvName	농작물 이름
	 * @param fvPrice	농작물 가격
	 * @param fvLevel	농작물 재배 난이도
	 * @param fvPeriod	농작물 재배 기간
	 * @param fvRestCount	농작물 남은 수량
	 */
	public FarmManagement_FVList(int fvNum, String fvType, String fvName,
			int fvPrice, String fvLevel, int fvPeriod, int fvRestCount) {

		this.fvNum = String.format("A%03d", fvNum);
		this.fvType = fvType;
		this.fvName = fvName;
		this.fvPrice = fvPrice;
		this.fvLevel = fvLevel;
		this.fvPeriod = fvPeriod;
		this.fvRestCount = fvRestCount;

	}

	public String getFvNum() {
		return fvNum;
	}

	public void setFvNum(String fvNum) {
		this.fvNum = fvNum;
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

	public int getFvPrice() {
		return fvPrice;
	}

	public void setFvPrice(int fvPrice) {
		this.fvPrice = fvPrice;
	}

	public String getFvLevel() {
		return fvLevel;
	}

	public void setFvLevel(String fvLevel) {
		this.fvLevel = fvLevel;
	}

	public int getFvPeriod() {
		return fvPeriod;
	}

	public void setFvPeriod(int fvPeriod) {
		this.fvPeriod = fvPeriod;
	}
	
	public int getFvRestCount() {
		return fvRestCount;
	}

	public void setFvRestCount(int fvRestCount) {
		this.fvRestCount = fvRestCount;
	}


	@Override
	public String toString() {
		return "FarmManagement_List [fvNum=" + fvNum + ", fvType=" + fvType
				+ ", fvName=" + fvName + ", fvPrice=" + fvPrice + ", fvLevel="
				+ fvLevel + ", fvPeriod=" + fvPeriod + "]";
	}

}