package com.electronicvoting.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.service.annotation.PutExchange;

import com.electronicvoting.dto.CandidateDTO;
import com.electronicvoting.dto.ResponseData;
import com.electronicvoting.dto.UserDTO;
import com.electronicvoting.dto.VoterRequestDTO;
import com.electronicvoting.entity.VoterRequest;
import com.electronicvoting.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@PostMapping("/register")
	public ResponseEntity<String> addUser(@RequestBody UserDTO userDTO)
	{
		return userService.addUser(userDTO); 
	}
	
//-------------------voter request controller----------------------
	
	@PostMapping("/requestforvote/{id}")
	public ResponseEntity<String> requestForVote(@PathVariable Long id, @RequestBody VoterRequestDTO voterRequestDTO)
	{
		return userService.requestForVote(id,voterRequestDTO);

	}
	@GetMapping("/getvoterid")
	public ResponseEntity<List<VoterRequestDTO>> getVoterId()
	{
		return userService.getVoterId();
		
	}
//---------------get election schedule---------------------
	@GetMapping("/viewschedule")
	ResponseEntity<ResponseData> getSchedule()
	{
		return userService.getSchedule();
	}
//-------------view candidates----------------------------------------
	@GetMapping("/viewcandidates")
	ResponseEntity<ResponseData> getCandidate()
	{
		return userService.getCandidate();
	}
//--------------Voter Id Veryfied By the electoral-----------------
	
	@PutMapping("/verifyId/{aadhar}/{status}")
	ResponseEntity<String> verifyId(@PathVariable String aadhar,@PathVariable Integer status)
	{
			return userService.verifyId(aadhar,status);
		
	}
//--------------------------------------------------------------------
	@GetMapping("/getcandidatebyid/{id}")
	ResponseEntity<ResponseData> getCandidateById(@PathVariable Long id)
	{
		return userService.getCandidateById(id);
		
	}
//----------------Voter Cast Vote Here---------------------------------------------
	@PutMapping("/castVote/{candidateName}")
	ResponseEntity<String>castVote(@PathVariable String candidateName)
	{
		return userService.castVote(candidateName);
	}

}
