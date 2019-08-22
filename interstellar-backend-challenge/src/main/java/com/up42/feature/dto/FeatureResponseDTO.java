package com.up42.feature.dto;

import com.up42.feature.jsonobject.Feature;

public class FeatureResponseDTO {

	private String id;
	private Long timestamp;
	private Long beginViewingDate;
	private Long endViewingDate;
	private String missionName;
	
	public FeatureResponseDTO() {}
	
	public FeatureResponseDTO(Feature feature) {
		if (feature != null) {
			this.id = feature.getProperties().getId();
			this.timestamp = feature.getProperties().getTimestamp();
			this.beginViewingDate = feature.getProperties().getAcquisition().getBeginViewingDate();
			this.endViewingDate = feature.getProperties().getAcquisition().getEndViewingDate();
			this.missionName = feature.getProperties().getAcquisition().getMissionName();
		}
	}
	
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
	
	public Long getBeginViewingDate() {
		return beginViewingDate;
	}

	public void setBeginViewingDate(Long beginViewingDate) {
		this.beginViewingDate = beginViewingDate;
	}

	public Long getEndViewingDate() {
		return endViewingDate;
	}

	public void setEndViewingDate(Long endViewingDate) {
		this.endViewingDate = endViewingDate;
	}

	public String getMissionName() {
		return missionName;
	}

	public void setMissionName(String missionName) {
		this.missionName = missionName;
	}
}
