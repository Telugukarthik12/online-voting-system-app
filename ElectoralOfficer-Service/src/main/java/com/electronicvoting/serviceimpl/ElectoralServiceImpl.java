package com.electronicvoting.serviceimpl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.crypto.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.electronicvoting.dto.ResponseData;
import com.electronicvoting.dto.VoterRequestDTO;
import com.electronicvoting.service.ElectoralService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class ElectoralServiceImpl implements ElectoralService {

	
	@Autowired
	WebClient.Builder webClient;
	
	@Override
	public ResponseEntity<ResponseData> getVoterRequest() {
		// TODO Auto-generated method stub
		ResponseData response=new ResponseData();
		
		Flux<VoterRequestDTO> flux=webClient.build()
				.get()
				.uri("http://localhost:8090/user/getvoterid")
				.retrieve()
				.bodyToFlux(VoterRequestDTO.class);
		List<VoterRequestDTO> voterList=flux.collectList().block();
		response.setElectionDTOList(voterList);
		return new ResponseEntity<ResponseData>(response,HttpStatus.OK);
	}
//-------------------vote verifying service logic---------------------------------------
	@Override
	public ResponseEntity<String> verifyId(String aadhar, Integer status) {
	    // Send the Aadhar and status to the verifyId method of the VoterRequestRepo
	    ResponseEntity<String> responseEntity = webClient.build()
	            .put()
	            .uri("http://localhost:8090/user/verifyId/{aadhar}/{status}", aadhar, status)
	            .retrieve()
	            .toEntity(String.class)
	            .block(); // Block until the request completes

	    // Print the response string
	    if (responseEntity != null && responseEntity.getBody() != null) {
	        String responseBody = responseEntity.getBody();
	        System.out.println("Response from verifyId method: " + responseBody);
	    } else {
	        System.out.println("Response from verifyId method is null");
	    }

	    // Return the response entity received from verifyId method
	    return responseEntity;
	}
	

	 	

}
