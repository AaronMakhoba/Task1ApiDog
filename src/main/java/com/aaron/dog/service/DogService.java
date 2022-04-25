package com.aaron.dog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.aaron.dog.dto.DogBreedImageLink;
import com.aaron.dog.dto.DogList;
import com.aaron.dog.dto.DogSubBreed;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class DogService {
	
	@Value("${dog.baseurl}")
	private String dogApiUrl;
	
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	ObjectMapper objectMapper;
	
	public DogList getAllDogBreeds() {
		//forming URL is https://dog.ceo/api/breeds/list/all
		String url = UriComponentsBuilder.fromUriString(dogApiUrl)
					.pathSegment("breeds", "list","all").build().toString();
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
		String responseFromApi = response.getBody();
		
		return convertStringToDogList(responseFromApi);
	}
	
	public String verifyBreedExists(String breedName) {
		DogList dogList = getAllDogBreeds();
		if(dogList.getMessage().get(breedName) != null ) {
			return "pass";
		} else
			return "fail";
	}
	
	public DogSubBreed getParticularSubBreed(String name) {
		//forming URL is https://dog.ceo/api/breed/retriever/list
		String url = UriComponentsBuilder.fromUriString(dogApiUrl)
					.pathSegment("breed", name,"list").build().toString();
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
		String responseFromApi = response.getBody();
		
		return convertStringToDogSubBreed(responseFromApi);
	}
	
	public DogBreedImageLink getParticularBreedImageLink(String name, String subBreedName) {
		//forming URL is https://dog.ceo/api/breed/retriever/golden/images/random
		String url = UriComponentsBuilder.fromUriString(dogApiUrl)
					.pathSegment("breed", name, subBreedName, "images","random").build().toString();
		ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
		String responseFromApi = response.getBody();
		
		return convertStringToDogBreedImageLink(responseFromApi);
	}
	
	private DogList convertStringToDogList(String response) {
		try {
			return objectMapper.readValue(response, DogList.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private DogSubBreed convertStringToDogSubBreed(String response) {
		try {
			return objectMapper.readValue(response, DogSubBreed.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private DogBreedImageLink convertStringToDogBreedImageLink(String response) {
		try {
			return objectMapper.readValue(response, DogBreedImageLink.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return null;
	}

}
