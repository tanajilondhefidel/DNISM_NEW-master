package com.comlink.model;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DNIS implements Serializable{
	 private static final long serialVersionUID = 4678852901357132238L;

	private List<DNIS> dnis;
	private Map<DNIS, Object> map;
	private String recordid;
	private String DNIS;
	private String prefix;
	private String mnumber;
	private String cname;
	private String rip1;
	private String rip2;
	private String rip3;
	private String rip4;
	private String rip5;
	private String rip6;
	private String datec;
	private String datem;
	private String ticketno;
	private String dtfs;
	
	private String file;
	private String status;
	private String userId;
	private String didNumber;
	private String gatewayName;
	private String initialAction;
	private String currentAction;
	
	
	private String filename;
	private String type;
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getDtfs() {
		return dtfs;
	}

	public void setDtfs(String dtfs) {
		this.dtfs = dtfs;
	}

	public String getRip4() {
		return rip4;
	}

	public void setRip4(String rip4) {
		this.rip4 = rip4;
	}

	public String getRip5() {
		return rip5;
	}

	public void setRip5(String rip5) {
		this.rip5 = rip5;
	}

	public String getRip6() {
		return rip6;
	}

	public void setRip6(String rip6) {
		this.rip6 = rip6;
	}

	public String getDatec() {
		return datec;
	}

	public void setDatec(String datec) {
		this.datec = datec;
	}

	public String getDatem() {
		return datem;
	}

	public void setDatem(String datem) {
		this.datem = datem;
	}

	public String getTicketno() {
		return ticketno;
	}

	public void setTicketno(String ticketno) {
		this.ticketno = ticketno;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRecordid() {
		return recordid;
	}

	public void setRecordid(String recordid) {
		this.recordid = recordid;
	}

	public String getDNIS() {
		return DNIS;
	}

	@javax.xml.bind.annotation.XmlElement
	public void setDNIS(String dNIS) {
		DNIS = dNIS;
	}

	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public String getMnumber() {
		return mnumber;
	}

	public void setMnumber(String mnumber) {
		this.mnumber = mnumber;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getRip1() {
		return rip1;
	}

	public void setRip1(String rip1) {
		this.rip1 = rip1;
	}

	public String getRip2() {
		return rip2;
	}

	public void setRip2(String rip2) {
		this.rip2 = rip2;
	}

	public String getRip3() {
		return rip3;
	}

	public void setRip3(String rip3) {
		this.rip3 = rip3;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public List<DNIS> getDnis() {
		return dnis;
	}

	public void setDnis(List<DNIS> dnis) {
		this.dnis = dnis;
	}

	public Map<DNIS, Object> getMap() {
		return map;
	}

	public void setMap(Map<DNIS, Object> map) {
		this.map = map;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDidNumber() {
		return didNumber;
	}

	public void setDidNumber(String didNumber) {
		this.didNumber = didNumber;
	}

	public String getGatewayName() {
		return gatewayName;
	}

	public void setGatewayName(String gatewayName) {
		this.gatewayName = gatewayName;
	}

	public String getInitialAction() {
		return initialAction;
	}

	public void setInitialAction(String initialAction) {
		this.initialAction = initialAction;
	}

	public String getCurrentAction() {
		return currentAction;
	}

	public void setCurrentAction(String currentAction) {
		this.currentAction = currentAction;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	
}
