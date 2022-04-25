package com.aaron.dog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.aaron.dog.dto.DogBreedImageLink;
import com.aaron.dog.dto.DogList;
import com.aaron.dog.dto.DogSubBreed;
import com.aaron.dog.service.DogService;

@RestController
@RequestMapping()
public class DogController {
	
	@Autowired
	DogService dogService;
	
	//Question 1
	//Perform a API request to produce a list of all dog breeds
	//This API is to get the all dog breed list
	@GetMapping("/dog-breeds")
	public ResponseEntity<DogList> getAllDogBreeds(){
		return new ResponseEntity<DogList>(dogService.getAllDogBreeds(), HttpStatus.OK);
	} 
	
	//Question 2
	//Using code, verify “retriever” breed is within the list
	//breedName is “retriever” and check that “retriever” in the list
	@GetMapping("/dog-breeds/{breedName}")
	public ResponseEntity<String> verifyDogBreedExists(@PathVariable String breedName){
		return new ResponseEntity<String>(dogService.verifyBreedExists(breedName), HttpStatus.OK);
	} 
	
	//Question 3
	// Perform an API request to produce a list of sub-breeds for “retriever”.
	//breedName is “retriever” or we can get any breedName
	//This API to get the all dog sub-breed list
	@GetMapping("/dog-breed/{breedName}")
	public ResponseEntity<DogSubBreed> getParticularBreedName(@PathVariable String breedName){
		return new ResponseEntity<DogSubBreed>(dogService.getParticularSubBreed(breedName), HttpStatus.OK);
	} 
	
	//Question 4
	// Perform an API request to produce a random image / link for the sub-breed “golden”.
	//breedName is “retriever” or we can get any breedName
	//subBreedName is “golden” or we can get any sub-breedName
	//This API to get the random image / link for sub breed
	@GetMapping("/dog-breed/imagelink/{breedName}/{subBreedName}")
	public ResponseEntity<DogBreedImageLink> getParticularBreedImageLink(@PathVariable String breedName, @PathVariable String subBreedName){
		return new ResponseEntity<DogBreedImageLink>(dogService.getParticularBreedImageLink(breedName, subBreedName), HttpStatus.OK);
	} 
}
