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

import com.electronicvoting.dto.CandidateDTO;
import com.electronicvoting.dto.ConstituencyWinner;
import com.electronicvoting.dto.ElectionDTO;
import com.electronicvoting.dto.PartyDTO;
import com.electronicvoting.entity.AdminEntity;
import com.electronicvoting.entity.Candidate;
import com.electronicvoting.entity.Party;
import com.electronicvoting.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	AdminService adminService;

	@PostMapping("/addelection")
	String addElection(@RequestBody ElectionDTO electionDTO) {
		adminService.addElection(electionDTO);
		return "election added succesfully";
	}

	@GetMapping("/getelection")
	public List<AdminEntity> getElection() {
		return adminService.getElection();

	}

//---------------------Party controller-----------------------
	@PostMapping("/addparty")
	public ResponseEntity<String> addparty(@RequestBody PartyDTO partyDTO) {
		return adminService.addparty(partyDTO);
	}

	@GetMapping("/getparty")
	public ResponseEntity<List<PartyDTO>> getparty() {
		return adminService.getparty();

	}

//-----------------candidate controller---------------------
	@PostMapping("/addcandidate")
	public ResponseEntity<String> addcandidate(@RequestBody CandidateDTO candidateDTO) {
		return adminService.addCandidate(candidateDTO);
	}

	@GetMapping("/getcandidate")
	public ResponseEntity<List<CandidateDTO>> getCandidate() {
		return adminService.getCandidate();
	}

//-------------------get candidate by user id and canstituency---------------------
	@GetMapping("/getcandidatebyconstituency/{consti}")
	public ResponseEntity<List<CandidateDTO>> getCandidateByConstituency(@PathVariable String consti) {
		return adminService.getCandidateByConstituency(consti);
	}

//-------------------------cast vote----------------------------------------------------
	@PutMapping("/castVote/{candidateName}")
	ResponseEntity<String> castVote(@PathVariable String candidateName) {
		return adminService.castVote(candidateName);
	}

//-----------------------who got highest votes----------------
	@GetMapping("/highestvotes")
	ResponseEntity<String> highestVotes() {
		return adminService.highestVotes();
	}

//-------------------highest votes as per the constituency-----------------
	@GetMapping("winneraspertheconstituency")
	ResponseEntity<List<ConstituencyWinner>> getWinners() {
		return adminService.getWinners();
	}

}
