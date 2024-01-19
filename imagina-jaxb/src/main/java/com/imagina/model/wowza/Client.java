package com.imagina.model.wowza;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = {"clientId", "flashVersion", "ipAddress", "referrer", "queryString", "timeRunning", "dateStarted", "URI", "protocol", "isSSL",
		"isEncrypted", "port", "lastValidateTime", "ioSessionBytesSent", "ioSessionBytesReceived", "ioSessionLastIo", "ioBytesSent", "ioBytesReceived"})
public class Client extends AbstractSession implements Serializable {

	private static final long	serialVersionUID	= 8871840931522735319L;
	
	private Integer		clientId;
	private String	flashVersion;
	private Boolean	sSSL;
	private Boolean	encrypted;
	private Long	lastValidateTime;
	private Long	ioSessionBytesSent;
	private Long	ioSessionBytesReceived;
	private Long	ioSessionLastIo;

	@XmlElement(name = "ClientId")
	public Integer getClientId() {
		return clientId;
	}

	public void setClientId(Integer clientId) {
		this.clientId = clientId;
	}

	@XmlElement(name = "FlashVersion")
	public String getFlashVersion() {
		return flashVersion;
	}

	public void setFlashVersion(String flashVersion) {
		this.flashVersion = flashVersion;
	}

	@XmlElement(name = "IsSSL")
	public Boolean getIsSSL() {
		return sSSL;
	}

	public void setIsSSL(Boolean isSSL) {
		this.sSSL = isSSL;
	}

	@XmlElement(name = "IsEncrypted")
	public Boolean getIsEncrypted() {
		return encrypted;
	}

	public void setIsEncrypted(Boolean isEncrypted) {
		this.encrypted = isEncrypted;
	}

	@XmlElement(name = "LastValidateTime")
	public Long getLastValidateTime() {
		return lastValidateTime;
	}

	public void setLastValidateTime(Long lastValidateTime) {
		this.lastValidateTime = lastValidateTime;
	}

	@XmlElement(name = "IoSessionBytesSent")
	public Long getIoSessionBytesSent() {
		return ioSessionBytesSent;
	}

	public void setIoSessionBytesSent(Long ioSessionBytesSent) {
		this.ioSessionBytesSent = ioSessionBytesSent;
	}

	@XmlElement(name = "IoSessionBytesReceived")
	public Long getIoSessionBytesReceived() {
		return ioSessionBytesReceived;
	}

	public void setIoSessionBytesReceived(Long ioSessionBytesReceived) {
		this.ioSessionBytesReceived = ioSessionBytesReceived;
	}

	@XmlElement(name = "IoSessionLastIo")
	public Long getIoSessionLastIo() {
		return ioSessionLastIo;
	}

	public void setIoSessionLastIo(Long ioSessionLastIo) {
		this.ioSessionLastIo = ioSessionLastIo;
	}

}
