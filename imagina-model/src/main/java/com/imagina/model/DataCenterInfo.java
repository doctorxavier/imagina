package com.imagina.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "data_center_info")
@NamedQuery(name = "getAverage",
        query = "SELECT NEW DataCenterInfo(AVG(di.numCon), AVG(di.numConLive), AVG(di.numConVod), AVG(di.upload), AVG(di.download), di.dataCenter) "
        		+ "FROM DataCenterInfo di JOIN di.info i "
				+ "WHERE i.date >= :date AND i.sec = :sec "
				+ "GROUP BY di.dataCenter")
public class DataCenterInfo implements Serializable {

	public enum DataCenter {
		BARCELONA(1), MADRID(2);

		private int	value;

		DataCenter(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

	};

	private static final long	serialVersionUID	= 4192920313769537386L;

	private Integer				id;

	private Long				numCon;
	private Long				numConLive;
	private Long				numConVod;
	private Double				upload;
	private Double				download;
	private List<EdgeInfo>		edgeInfos			= new ArrayList<EdgeInfo>(0);
	private Integer				dataCenter;
	private Info				info;
	
	public DataCenterInfo() {
		
	}
	
	public DataCenterInfo(Double numCon, Double numConLive, Double numConVod, Double upload, Double download, Integer dataCenter) {
		this.numCon = numCon.longValue();
		this.numConLive = numConLive.longValue();
		this.numConVod = numConVod.longValue();
		this.upload = upload;
		this.download = download;
		this.dataCenter = dataCenter;
	}
	
	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	@Column(name = "id_data_center")
	public Integer getDataCenter() {
		return this.dataCenter;
	}

	@Column(name = "numCon")
	public Long getNumCon() {
		return numCon;
	}

	@Column(name = "numConLive")
	public Long getNumConLive() {
		return numConLive;
	}

	@Column(name = "numConVod")
	public Long getNumConVod() {
		return numConVod;
	}

	@Column(name = "upload")
	public Double getUpload() {
		return upload;
	}

	@Column(name = "download")
	public Double getDownload() {
		return download;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "dataCenterInfo", cascade = {CascadeType.DETACH, CascadeType.PERSIST})
	public List<EdgeInfo> getEdgeInfos() {
		return edgeInfos;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "id_info")
	public Info getInfo() {
		return info;
	}

	public void setInfo(Info info) {
		this.info = info;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setDataCenter(Integer dataCenter) {
		this.dataCenter = dataCenter;
	}

	public void setNumCon(Long numCon) {
		this.numCon = numCon;
	}

	public void setNumConLive(Long numConLive) {
		this.numConLive = numConLive;
	}

	public void setNumConVod(Long numConVod) {
		this.numConVod = numConVod;
	}

	public void setUpload(Double upload) {
		this.upload = upload;
	}

	public void setDownload(Double download) {
		this.download = download;
	}

	public void setEdgeInfos(List<EdgeInfo> edgeInfos) {
		this.edgeInfos = edgeInfos;
	}

}
