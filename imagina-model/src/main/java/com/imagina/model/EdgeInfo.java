package com.imagina.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "edge_info")
public class EdgeInfo implements Serializable {

	public enum Edge {
		EDGE01(1), EDGE02(2), EDGE03(3), EDGE04(4), EDGE05(5), EDGE06(6), EDGE07(7), EDGE08(8);

		private int	value;

		Edge(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}
	}

	private static final long	serialVersionUID	= -3509868663672015250L;

	private Integer				id;

	private Long				numCon;
	private Long				numConLive;
	private Long				numConVod;
	private DataCenterInfo		dataCenterInfo;
	private Integer				edge;

	@Id
	@Column(name = "id", unique = true, nullable = false)
	@GeneratedValue
	public Integer getId() {
		return id;
	}

	@Column(name = "id_edge")
	public Integer getEdge() {
		return edge;
	}

	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.DETACH, CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "id_data_center_info")
	public DataCenterInfo getDataCenterInfo() {
		return dataCenterInfo;
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

	public void setEdge(Integer edge) {
		this.edge = edge;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public void setDataCenterInfo(DataCenterInfo dataCenterInfo) {
		this.dataCenterInfo = dataCenterInfo;
	}

}
