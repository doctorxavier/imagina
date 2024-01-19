package com.imagina.model.wowza;


public abstract class AbstractConnectionCounter {

	private long				connectionsCurrent;
	private long				connectionsTotal;
	private long				connectionsTotalAccepted;
	private long				connectionsTotalRejected;
	private double				timeRunning;

	public long getConnectionsCurrent() {
		return connectionsCurrent;
	}

	public long getConnectionsTotal() {
		return connectionsTotal;
	}

	public long getConnectionsTotalAccepted() {
		return connectionsTotalAccepted;
	}

	public long getConnectionsTotalRejected() {
		return connectionsTotalRejected;
	}

	public double getTimeRunning() {
		return timeRunning;
	}

	public void setConnectionsCurrent(long connectionsCurrent) {
		this.connectionsCurrent = connectionsCurrent;
	}

	public void setConnectionsTotal(long connectionsTotal) {
		this.connectionsTotal = connectionsTotal;
	}

	public void setConnectionsTotalAccepted(long connectionsTotalAccepted) {
		this.connectionsTotalAccepted = connectionsTotalAccepted;
	}

	public void setConnectionsTotalRejected(long connectionsTotalRejected) {
		this.connectionsTotalRejected = connectionsTotalRejected;
	}

	public void setTimeRunning(double timeRunning) {
		this.timeRunning = timeRunning;
	}

}
