package com.electronicvoting.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.electronicvoting.dto.CandidateDTO;
import com.electronicvoting.dto.ResponseData;
import com.electronicvoting.dto.UserDTO;
import com.electronicvoting.dto.VoterRequestDTO;
import com.electronicvoting.entity.VoterRequest;

public interface UserService {

	public ResponseEntity<String> addUser(UserDTO userDTO);

	public ResponseEntity<String> requestForVote(Long id,VoterRequestDTO voterRequestDTO);

	public ResponseEntity<List<VoterRequestDTO>> getVoterId();

	public ResponseEntity<ResponseData> getSchedule();

	public ResponseEntity<ResponseData> getCandidate();

	public ResponseEntity<String> verifyId(String aadhar, Integer status);

	public ResponseEntity<ResponseData> getCandidateById(Long id);


	public ResponseEntity<String> castVote(String candidateName);

}
