package com.imagina.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "info")
public class Info implements Serializable {

	private static final long		serialVersionUID	= -6575498456389799197L;

	private Integer					id;
	private Integer					sec;
	private Date					date;
	private List<DataCenterInfo>	dataCenterInfos		= new ArrayList<DataCenterInfo>(0);

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue
	public Integer getId() {
		return id;
	}
	
	@Column(name = "sec")
	public Integer getSec() {
		return sec;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public Date getDate() {
		return date;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "info", cascade = {CascadeType.DETACH, CascadeType.PERSIST})
	public List<DataCenterInfo> getDataCenterInfos() {
		return dataCenterInfos;
	}
	
	public void setSec(Integer sec) {
		this.sec = sec;
	}

	public void setDataCenterInfos(List<DataCenterInfo> dataCenterInfos) {
		this.dataCenterInfos = dataCenterInfos;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setDate(Date date) {
		this.date = date;
	}

}
