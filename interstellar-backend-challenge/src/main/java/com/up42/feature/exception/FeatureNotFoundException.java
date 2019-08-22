package com.up42.feature.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class FeatureNotFoundException extends RuntimeException {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2674601956689295679L;

	public FeatureNotFoundException(String featureId) {
		super(String.format("Feature with id {%s} not found!", featureId));
	}
}
