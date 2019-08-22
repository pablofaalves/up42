package com.up42.feature.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.up42.feature.dto.FeatureResponseDTO;
import com.up42.feature.test.AbstractTest;

/**
 * Test Class for FeatureController and its endpoints.
 * 
 * @author Pablo Alves
 * @since 22/08/2019
 */
public class FeatureControllerTest extends AbstractTest {

	/**
	 * Test for the "/features" endpoint.
	 * 
	 * @throws Exception
	 */
	@Test
	public void listAllFeaturesTest() throws Exception {
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/features/")).andReturn();
		assertEquals(result.getResponse().getStatus(), HttpStatus.OK.value());

		FeatureResponseDTO[] responseArray = jsonMapper.readValue(result.getResponse().getContentAsString(),
				FeatureResponseDTO[].class);
		assertEquals(responseArray.length, 6);
	}

	/**
	 * Test for the "/features/{id}" endpoint.
	 * 
	 * @throws Exception
	 */
	@Test
	public void getFeatureTestSucces() throws Exception {
		String featureId = "39c2f29e-c0f8-4a39-a98b-deed547d6aea";
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/features/" + featureId + "/")).andReturn();
		assertEquals(result.getResponse().getStatus(), HttpStatus.OK.value());

		FeatureResponseDTO response = jsonMapper.readValue(result.getResponse().getContentAsString(),
				FeatureResponseDTO.class);

		assertNotNull(response);
		assertEquals(response.getId(), featureId);
	}

	/**
	 * Failure test for the "/features/{id}" endpoint which checks for a HTTP 400 status response
	 * 
	 * @throws Exception
	 */
	@Test
	public void getFeatureTestFailure() throws Exception {
		String featureId = "999999";
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/features/" + featureId + "/")).andReturn();
		assertEquals(result.getResponse().getStatus(), HttpStatus.BAD_REQUEST.value());
	}

	/**
	 * Test for the "/features/{id}/quicklook" endpoint.
	 * 
	 * @throws Exception
	 */
	@Test
	public void getImageTest() throws Exception {
		String featureId = "cf5dbe37-ab95-4af1-97ad-2637aec4ddf0";

		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/features/" + featureId + "/quicklook")).andReturn();
		assertEquals(result.getResponse().getStatus(), HttpStatus.OK.value());

		assertNotNull(result.getResponse().getContentAsByteArray());
	}
}
