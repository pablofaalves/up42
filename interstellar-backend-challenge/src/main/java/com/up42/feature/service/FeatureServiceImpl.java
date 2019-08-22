package com.up42.feature.service;

import java.util.Collection;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.up42.feature.exception.FeatureNotFoundException;
import com.up42.feature.jsonobject.Feature;

/**
 * This service component is reponsible for handling the mapped features from the JSON file.
 * 
 * @author Pablo Alves
 */
@Service
public class FeatureServiceImpl implements IFeatureService {

	@Autowired
	private Map<String, Feature> featureMapping;
	
	/**
	 * Return all features.
	 * 
	 * @return Collection<Feature>
	 */
	public Collection<Feature> findAllFeatures() {
		return featureMapping.values();
	}
	
	/**
	 * Find a feature by its ID.
	 * 
	 * @param id
	 * @exception FeatureNotFoundException - if the ID param doesn't match any mapped feature
	 * @return Feature
	 */
	public Feature findFeatureById(String id) {
		Feature feature = featureMapping.get(id);
		
		if (feature == null) {
			throw new FeatureNotFoundException(id);
		}
		
		return feature;
	}
}
