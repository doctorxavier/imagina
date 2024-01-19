package com.imagina.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.imagina.model.nagios.Bitrate;

public class BitrateInfo implements Serializable {

	private static final long	serialVersionUID	= -9083764656404496470L;

	private String				name;
	private double				upload;
	private double				download;
	private long				date;

	public String getName() {
		return name;
	}

	public double getUpload() {
		return upload;
	}

	public void setUpload(String upload) {
		this.upload = 0.0D;
		if (upload != null) {
			try {
				this.upload = Double.parseDouble(upload.replaceAll("(.*\\()(.+?)(Mbps.*)", "$2"));
			} catch (NumberFormatException e) { return; }
		}
	}

	public void setDownload(String download) {
		this.download = 0.0D;
		if (download != null) {
			try {
				this.download = Double.parseDouble(download.replaceAll("(.*/)(.+?)(Mbps.*)", "$2"));
			} catch (NumberFormatException e) { return; }
		}
	}

	public void setName(String name) {
		this.name = null;
		if (name != null) {
			this.name = name.replaceAll("(.*)(22A)(.*)", DataCenter.BARCELONA.toString());
			if (!(this.name.length() == 3)) {
				this.name = name.replaceAll("(.*)(VIR)(.*)", DataCenter.MADRID.toString());
			}
		}
	}

	public double getDownload() {
		return download;
	}

	public long getDate() {
		return date;
	}

	public void setDate(long date) {
		this.date = date;
	}
	
	public static final BitrateInfo parseBitrate(Bitrate bitrate) {
		
		BitrateInfo bitrateInfo = new BitrateInfo();
		bitrateInfo.setName(bitrate.getName());
		bitrateInfo.setDate(bitrate.getTime().getTime());
		bitrateInfo.setUpload(bitrate.getOutput());
		bitrateInfo.setDownload(bitrate.getOutput());
		
		return bitrateInfo;
		
	}
	
	public static final Map<String, BitrateInfo> parseBitrate(List<Bitrate> bitrates) {
		Map<String, BitrateInfo> bitrateInfos = new HashMap<String, BitrateInfo>(0);
		for (Bitrate bitrate : bitrates) {
			BitrateInfo bitrateInfo = new BitrateInfo();
			bitrateInfo.setName(bitrate.getName());
			bitrateInfo.setDate(bitrate.getTime().getTime());
			bitrateInfo.setUpload(bitrate.getOutput());
			bitrateInfo.setDownload(bitrate.getOutput());
			bitrateInfos.put(bitrateInfo.getName(), bitrateInfo);
		}
		return bitrateInfos;
	}

}
