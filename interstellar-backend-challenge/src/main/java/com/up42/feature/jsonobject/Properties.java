package com.up42.feature.jsonobject;

public class Properties {

	private String id;
	private Long timestamp;
	private Acquisition acquisition;
	private String quicklook;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	public Acquisition getAcquisition() {
		return acquisition;
	}

	public void setAcquisition(Acquisition acquisition) {
		this.acquisition = acquisition;
	}

	public String getQuicklook() {
		return quicklook;
	}

	public void setQuicklook(String quicklook) {
		this.quicklook = quicklook;
	}
}
