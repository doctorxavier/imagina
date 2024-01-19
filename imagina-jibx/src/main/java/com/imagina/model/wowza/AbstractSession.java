package com.imagina.model.wowza;


public abstract class AbstractSession {
	
	private String	dateStarted;
	private String	ipAddress;
	private String	referrer;
	private String	queryString;
	private double	timeRunning;
	private String	uRI;
	private String	protocol;
	private int		port;
	private long	ioBytesSent;
	private long	ioBytesReceived;

	public String getDateStarted() {
		return dateStarted;
	}

	public void setDateStarted(String dateStarted) {
		this.dateStarted = dateStarted;
	}

	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	public String getReferrer() {
		return referrer;
	}

	public void setReferrer(String referrer) {
		this.referrer = referrer;
	}

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	public double getTimeRunning() {
		return timeRunning;
	}

	public void setTimeRunning(double timeRunning) {
		this.timeRunning = timeRunning;
	}

	public String getURI() {
		return uRI;
	}

	public void setURI(String uRI) {
		this.uRI = uRI;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public long getIoBytesSent() {
		return ioBytesSent;
	}

	public void setIoBytesSent(long ioBytesSent) {
		this.ioBytesSent = ioBytesSent;
	}

	public long getIoBytesReceived() {
		return ioBytesReceived;
	}

	public void setIoBytesReceived(long ioBytesReceived) {
		this.ioBytesReceived = ioBytesReceived;
	}

}
