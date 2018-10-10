package com.comlink.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Config {

	private String id;
	private String file;
	private List<Config> config;
	private List<DNIS> dnis;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	@XmlElement(name = "Config")
	public void setConfig(List<Config> con) {
		this.config = con;

	}

	public void add(Config con) {
		if (this.config == null) {
			this.config = new java.util.ArrayList<Config>();
		}

		this.config.add(con);

	}

	public List<Config> getConfig() {
		return config;
	}

	public List<DNIS> getDnis() {
		return dnis;
	}
	@XmlElement(name = "DNIS")
	public void setDnis(List<DNIS> dnis) {
		this.dnis = dnis;
	}
	public void add1(DNIS dni) {
		if (this.dnis == null) {
			this.dnis = new java.util.ArrayList<DNIS>();
		}

		this.dnis.add(dni);

	}
	
	

}
