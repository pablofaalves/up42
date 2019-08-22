package com.up42.feature.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.up42.feature.jsonobject.Feature;
import com.up42.feature.jsonobject.FeatureCollection;

/**
 * Configuration class responsible for the creation of the 'static datasource' using the JSON file
 * provided.
 * 
 * @author Pablo Alves
 * @since 22/08/2019
 */
@Configuration
public class StaticDataSourceConfig {

	private static final String JSON_FILE_NAME = "json/source-data.json";
		
	/**
	 * Creates a map of features as a spring bean.
	 * 
	 * @return Map<String, Feature>
	 * @throws IOException
	 */
	@Bean(name = "featuresMapping")
	public Map<String, Feature> readFeaturesFromJsonFile() throws IOException {
		InputStream is = new ClassPathResource(JSON_FILE_NAME).getInputStream();	
		
		// This command avoids error while mapping json to object as not all properties in JSON file have been mapped
		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		FeatureCollection[] listFeatures = mapper.readValue(is, FeatureCollection[].class);
		
		Map<String, Feature> featuresMapping = new HashMap<String, Feature>();
		Arrays.asList(listFeatures).stream().forEach(
				x -> x.getFeatures().stream().forEach(
						feature -> mapFeature(featuresMapping, feature)));
		
		return featuresMapping;
	}
	
	/**
	 * Put a new feature in the map of features
	 * 
	 * @param map
	 * @param feature
	 */
	private void mapFeature(Map<String, Feature> map, Feature feature) {
		map.put(feature.getProperties().getId(), feature);
	}
}
