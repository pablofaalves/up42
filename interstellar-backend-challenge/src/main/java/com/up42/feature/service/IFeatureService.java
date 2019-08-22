package com.up42.feature.service;

import java.util.Collection;

import com.up42.feature.jsonobject.Feature;

public interface IFeatureService {

	Collection<Feature> findAllFeatures();
	
	Feature findFeatureById(String id);
}
