package com.electronicvoting.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.electronicvoting.dto.CandidateDTO;
import com.electronicvoting.dto.ConstituencyWinner;
import com.electronicvoting.dto.ElectionDTO;
import com.electronicvoting.dto.PartyDTO;
import com.electronicvoting.entity.AdminEntity;
import com.electronicvoting.entity.Candidate;

public interface AdminService {

	public String addElection(ElectionDTO electionDTO);

	public List<AdminEntity> getElection();

	public ResponseEntity<String> addparty(PartyDTO partyDTO);

	public ResponseEntity<List<PartyDTO>> getparty();

	public ResponseEntity<String> addCandidate(CandidateDTO candidateDTO);

	public ResponseEntity<List<CandidateDTO>> getCandidate();

	public ResponseEntity<List<CandidateDTO>> getCandidateByConstituency(String consti);

	public ResponseEntity<String> castVote(String candidateName);

	public ResponseEntity<String> highestVotes();

	public ResponseEntity<List<ConstituencyWinner>> getWinners();


}
