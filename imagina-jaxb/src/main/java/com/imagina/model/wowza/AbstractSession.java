package com.imagina.model.wowza;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlTransient;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.imagina.util.xml.jaxb.StringAdapter;

@XmlTransient
public abstract class AbstractSession {

	private String	ipAddress;
	private String	referrer;
	private String	queryString;
	private double	timeRunning;
	private String	dateStarted;
	private String	uRI;
	private String	protocol;
	private long	ioBytesSent;
	private long	ioBytesReceived;
	private int		port;

	@XmlElement(name = "Port")
	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	@XmlElement(name = "IpAddress")
	public String getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(String ipAddress) {
		this.ipAddress = ipAddress;
	}

	@XmlElement(name = "Referrer")
	@XmlJavaTypeAdapter(value = StringAdapter.class)
	public String getReferrer() {
		return referrer;
	}

	public void setReferrer(String referrer) {
		this.referrer = referrer;
	}

	@XmlElement(name = "QueryString")
	@XmlJavaTypeAdapter(value = StringAdapter.class)
	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

	@XmlElement(name = "TimeRunning")
	public double getTimeRunning() {
		return timeRunning;
	}

	public void setTimeRunning(double timeRunning) {
		this.timeRunning = timeRunning;
	}

	@XmlElement(name = "DateStarted")
	public String getDateStarted() {
		return dateStarted;
	}

	public void setDateStarted(String dateStarted) {
		this.dateStarted = dateStarted;
	}

	@XmlElement(name = "URI")
	@XmlJavaTypeAdapter(value = StringAdapter.class)
	public String getURI() {
		return uRI;
	}

	public void setURI(String uRI) {
		this.uRI = uRI;
	}

	@XmlElement(name = "Protocol")
	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	@XmlElement(name = "IoBytesSent")
	public long getIoBytesSent() {
		return ioBytesSent;
	}

	public void setIoBytesSent(long ioBytesSent) {
		this.ioBytesSent = ioBytesSent;
	}

	@XmlElement(name = "IoBytesReceived")
	public long getIoBytesReceived() {
		return ioBytesReceived;
	}

	public void setIoBytesReceived(long ioBytesReceived) {
		this.ioBytesReceived = ioBytesReceived;
	}

}
