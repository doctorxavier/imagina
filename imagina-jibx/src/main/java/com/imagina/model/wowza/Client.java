package com.imagina.model.wowza;

import java.io.Serializable;

public class Client extends AbstractSession implements Serializable {

	private static final long	serialVersionUID	= 419947926240067992L;
	
	private int		clientId;
	private String	flashVersion;
	private boolean	sSSL;
	private boolean	encrypted;
	private long	lastValidateTime;
	private long	ioSessionBytesSent;
	private long	ioSessionBytesReceived;
	private long	ioSessionLastIo;

	public int getClientId() {
		return clientId;
	}

	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	public String getFlashVersion() {
		return flashVersion;
	}

	public void setFlashVersion(String flashVersion) {
		this.flashVersion = flashVersion;
	}

	public boolean getIsSSL() {
		return sSSL;
	}

	public void setIsSSL(boolean isSSL) {
		this.sSSL = isSSL;
	}

	public boolean getIsEncrypted() {
		return encrypted;
	}

	public void setIsEncrypted(boolean isEncrypted) {
		this.encrypted = isEncrypted;
	}

	public long getLastValidateTime() {
		return lastValidateTime;
	}

	public void setLastValidateTime(long lastValidateTime) {
		this.lastValidateTime = lastValidateTime;
	}

	public long getIoSessionBytesSent() {
		return ioSessionBytesSent;
	}

	public void setIoSessionBytesSent(long ioSessionBytesSent) {
		this.ioSessionBytesSent = ioSessionBytesSent;
	}

	public long getIoSessionBytesReceived() {
		return ioSessionBytesReceived;
	}

	public void setIoSessionBytesReceived(long ioSessionBytesReceived) {
		this.ioSessionBytesReceived = ioSessionBytesReceived;
	}

	public long getIoSessionLastIo() {
		return ioSessionLastIo;
	}

	public void setIoSessionLastIo(long ioSessionLastIo) {
		this.ioSessionLastIo = ioSessionLastIo;
	}

}
