package com.json.dto;

/**
 * 
 * @Description 行李信息补全舱位配置
 * @author lichaoxian
 * @date 2016年11月1日16:24:38
 */

public class BaggageInfoSupplementConfig{
	
	private Long id;
	
	private String baggageInfoSupplementId;//业务ID
	
	private String checkNumber;//托运件数
	
	private double checkTheMaxWeight;//托运最大重量(公斤)
	
	private String checkTheMaxSize;//托运最大尺寸
	
	private String portableNumber;//手提件数
	
	private double portableTheMaxWeight;//手提最大重量(公斤)
	
	private String portableTheMaxSize;//手提最大尺寸
	
	private String seatCodes;//舱位；多个用/隔开
	
	private String remark;//说明

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBaggageInfoSupplementId() {
		return baggageInfoSupplementId;
	}

	public void setBaggageInfoSupplementId(String baggageInfoSupplementId) {
		this.baggageInfoSupplementId = baggageInfoSupplementId;
	}

	public String getCheckNumber() {
		return checkNumber;
	}

	public void setCheckNumber(String checkNumber) {
		this.checkNumber = checkNumber;
	}

	public double getCheckTheMaxWeight() {
		return checkTheMaxWeight;
	}

	public void setCheckTheMaxWeight(double checkTheMaxWeight) {
		this.checkTheMaxWeight = checkTheMaxWeight;
	}

	public String getCheckTheMaxSize() {
		return checkTheMaxSize;
	}

	public void setCheckTheMaxSize(String checkTheMaxSize) {
		this.checkTheMaxSize = checkTheMaxSize;
	}

	public String getPortableNumber() {
		return portableNumber;
	}

	public void setPortableNumber(String portableNumber) {
		this.portableNumber = portableNumber;
	}

	public double getPortableTheMaxWeight() {
		return portableTheMaxWeight;
	}

	public void setPortableTheMaxWeight(double portableTheMaxWeight) {
		this.portableTheMaxWeight = portableTheMaxWeight;
	}

	public String getPortableTheMaxSize() {
		return portableTheMaxSize;
	}

	public void setPortableTheMaxSize(String portableTheMaxSize) {
		this.portableTheMaxSize = portableTheMaxSize;
	}

	public String getSeatCodes() {
		return seatCodes;
	}

	public void setSeatCodes(String seatCodes) {
		this.seatCodes = seatCodes;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
