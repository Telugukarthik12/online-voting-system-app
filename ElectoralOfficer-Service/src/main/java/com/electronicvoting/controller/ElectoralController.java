package com.electronicvoting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.electronicvoting.dto.ResponseData;
import com.electronicvoting.dto.VoterRequestDTO;
import com.electronicvoting.service.ElectoralService;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/electoral")
public class ElectoralController {
	@Autowired
	ElectoralService electoralService;
	@GetMapping("/getvoterrequest")
	public ResponseEntity<ResponseData>getVoterRequest()
	{
		return electoralService.getVoterRequest();
	}
//----------------------verifying the voting request----------------------------------------
	
	@PutMapping("/verifyingvoter/{aadhar}/{status}")
	public ResponseEntity<String> verifyingVote(@PathVariable String aadhar,@PathVariable Integer status)
	{
		return electoralService.verifyId(aadhar,status);
	}
	

}
