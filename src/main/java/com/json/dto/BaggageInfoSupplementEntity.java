package com.json.dto;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @Description 行李信息补全
 * @author lichaoxian
 * @date 2016年11月1日16:24:38
 */
public class BaggageInfoSupplementEntity{

	private Long id;
	
	private String baggageInfoSupplementId;//业务ID
	
	private String aircomIataCode = "";//航司二字码
	
	private String vendorId = "";//渠道ID;多个/分隔
	
	private int isVendorId;//渠道是否过滤；1-指定；2-排除
	
	private Date startDate;//旅行开始日期
	
	private Date endDate;//旅行结束日期
	
	private Date issueStartDate;//销售开始日期
	
	private Date issueEndDate;//销售结束日期
	
	private String routes = "";//航线范围<城市对SHABJS;多个/隔开>

	private String flightNo;//航班号;多个用/隔开
	
	private int isRoutes;//航线是否过滤；1-指定；2-排除

	private int isFlightNo;//航班是否过滤：1-指定；2-排除
	
	private int routesDirection;//航线是否限制方向；0-否；1-是
	
	private int delFlag;//删除标志位，默认：0，删除：1
	
	private int opUid;//操作人ID
	
	private String opName = "";//操作人姓名
	
	private Date opTime;//操作时间
	
	private int start;
	
	private int limit;

	private boolean isFromSupplier = false;

	public boolean isFromSupplier() {
		return isFromSupplier;
	}

	public void setFromSupplier(boolean fromSupplier) {
		isFromSupplier = fromSupplier;
	}

	//行李信息补全配置信息
	private List<BaggageInfoSupplementConfig> baggageInfoSupplementConfig = new ArrayList<BaggageInfoSupplementConfig>();

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

	public String getAircomIataCode() {
		return aircomIataCode;
	}

	public void setAircomIataCode(String aircomIataCode) {
		this.aircomIataCode = aircomIataCode;
	}

	public String getVendorId() {
		return vendorId;
	}

	public void setVendorId(String vendorId) {
		this.vendorId = vendorId;
	}

	public int getIsVendorId() {
		return isVendorId;
	}

	public void setIsVendorId(int isVendorId) {
		this.isVendorId = isVendorId;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Date getIssueStartDate() {
		return issueStartDate;
	}

	public void setIssueStartDate(Date issueStartDate) {
		this.issueStartDate = issueStartDate;
	}

	public Date getIssueEndDate() {
		return issueEndDate;
	}

	public void setIssueEndDate(Date issueEndDate) {
		this.issueEndDate = issueEndDate;
	}

	public String getRoutes() {
		return routes;
	}

	public void setRoutes(String routes) {
		this.routes = routes;
	}

	public int getIsRoutes() {
		return isRoutes;
	}

	public void setIsRoutes(int isRoutes) {
		this.isRoutes = isRoutes;
	}

	public int getRoutesDirection() {
		return routesDirection;
	}

	public void setRoutesDirection(int routesDirection) {
		this.routesDirection = routesDirection;
	}

	public int getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(int delFlag) {
		this.delFlag = delFlag;
	}

	public int getOpUid() {
		return opUid;
	}

	public void setOpUid(int opUid) {
		this.opUid = opUid;
	}

	public String getOpName() {
		return opName;
	}

	public void setOpName(String opName) {
		this.opName = opName;
	}

	public Date getOpTime() {
		return opTime;
	}

	public void setOpTime(Date opTime) {
		this.opTime = opTime;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public String getFlightNo() {
		return flightNo;
	}

	public void setFlightNo(String flightNo) {
		this.flightNo = flightNo;
	}

	public List<BaggageInfoSupplementConfig> getBaggageInfoSupplementConfig() {
		return baggageInfoSupplementConfig;
	}

	public void setBaggageInfoSupplementConfig(List<BaggageInfoSupplementConfig> baggageInfoSupplementConfig) {
		this.baggageInfoSupplementConfig = baggageInfoSupplementConfig;
	}

	public int getIsFlightNo() {
		return isFlightNo;
	}

	public void setIsFlightNo(int isFlightNo) {
		this.isFlightNo = isFlightNo;
	}
}