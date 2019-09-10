package com.up42.feature.controller;

import java.util.Base64;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.up42.feature.dto.FeatureResponseDTO;
import com.up42.feature.jsonobject.Feature;
import com.up42.feature.service.IFeatureService;

/**
 * REST Controller handling all '/features' endpoints.
 * 
 * @author Pablo Alves
 * @since 22/08/2019
 */
@RestController
@RequestMapping("/features")
public class FeatureController {

	@Autowired
	private IFeatureService service;

	/**
	 * GET method responsible to return a list with all features.
	 * @return List<FeatureResponseDTO>
	 */
	@GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<FeatureResponseDTO> listAllFeatures() {
		Collection<Feature> listFeatures = service.findAllFeatures();
		return listFeatures.stream().map(feature -> new FeatureResponseDTO(feature)).collect(Collectors.toList());
	}

	/**
	 * GET method responsible to return a single features according to the {id} in the URL path.
	 * 
	 * If no feature with the provided ID is found, returns a HTTP 404 response status.
	 * 
	 * @return FeatureResponseDTO
	 */
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public FeatureResponseDTO getFeature(@PathVariable String id) {
		return new FeatureResponseDTO(service.findFeatureById(id));
	}

	/**
	 * GET method responsible to return an image according to the {id} of the respective feature in the URL path.
	 * 
	 * If no feature with the provided ID is found, returns a HTTP 404 response status.
	 * 
	 * @return byte[] representation of a PNG image
	 */
	@GetMapping(value = "/{id}/quicklook", produces = MediaType.IMAGE_PNG_VALUE)
	public ResponseEntity<byte[]> getImage(@PathVariable String id) {
		return Optional.ofNullable(service.findFeatureById(id).getProperties().getQuicklook()).map((quicklook) -> {
			byte[] decodedImage = Base64.getDecoder().decode(quicklook);
			return new ResponseEntity<byte[]>(decodedImage, HttpStatus.OK);
		}).orElse(new ResponseEntity<byte[]>(new byte[0], HttpStatus.NOT_FOUND));
	}
}
