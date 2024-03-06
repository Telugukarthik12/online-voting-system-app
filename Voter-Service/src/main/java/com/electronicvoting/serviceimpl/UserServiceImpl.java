package com.electronicvoting.serviceimpl;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.electronicvoting.UserRepo;
import com.electronicvoting.VoterRequestRepo;
import com.electronicvoting.dto.CandidateDTO;
import com.electronicvoting.dto.ElectionDTO;
import com.electronicvoting.dto.ResponseData;
import com.electronicvoting.dto.UserDTO;
import com.electronicvoting.dto.VoterRequestDTO;
import com.electronicvoting.entity.Status;
import com.electronicvoting.entity.User;
import com.electronicvoting.entity.VoterRequest;
import com.electronicvoting.service.UserService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	UserRepo userRepo;
	
	public ResponseEntity<String> addUser(UserDTO userDTO)
	{
		User user=new User();
		user.setName(userDTO.getName());
		user.setAddress(userDTO.getAddress());
		user.setDistrict(userDTO.getDistrict());
		user.setDob(userDTO.getDob());
		user.setGender(userDTO.getGender());
		user.setMobile(userDTO.getMobile());
		userRepo.save(user);
		return new ResponseEntity<String>("User Register! successfully",HttpStatus.CREATED);
	}
//-----------------Request for Vote service-------------------
	@Autowired
	VoterRequestRepo voterRequestRepo;
	public ResponseEntity<String> requestForVote(Long id,VoterRequestDTO voterRequestDTO)
	{
		VoterRequest voterRequest=new VoterRequest();
		voterRequest.setConstituency(voterRequestDTO.getConstituency());
		voterRequest.setAadharno(voterRequestDTO.getAadharno());
		voterRequest.setVoterId(id);
		Status s=Status.pending;
		voterRequest.setStatus(s.toString());
		voterRequestRepo.save(voterRequest);
		return new ResponseEntity<String>("added",HttpStatus.CREATED);
		
		
	}
	public ResponseEntity<List<VoterRequestDTO>> getVoterId()
	{
		List<VoterRequestDTO> listDTO=new ArrayList<>();
		List<VoterRequest> listVoter=voterRequestRepo.findAll();
		for(VoterRequest voterRequest:listVoter)
		{
			VoterRequestDTO voterRequestDTO=new VoterRequestDTO();
			voterRequestDTO.setAadharno(voterRequest.getAadharno());
			voterRequestDTO.setConstituency(voterRequest.getConstituency());
			voterRequestDTO.setStatus(voterRequest.getStatus());
			listDTO.add(voterRequestDTO);
		}
		return new ResponseEntity<List<VoterRequestDTO>>(listDTO,HttpStatus.OK);
	}
	
//---------------------view schedule-------------------------------------------
	@Autowired
	private WebClient.Builder webClient;
	public ResponseEntity<ResponseData> getSchedule() {
		
	    ResponseData responseData = new ResponseData();
	    Flux<ElectionDTO> flux = webClient.build()
	            .get()
	            .uri("http://localhost:8080/admin/getelection")
	            .retrieve()
	            .bodyToFlux(ElectionDTO.class); // Modify this line to handle the response as Flux<ElectionDTO> instead of Mono<ElectionDTO>
	    
	    List<ElectionDTO> electionList = flux.collectList().block(); // Collect the elements of the flux into a list
	    
	    responseData.setElectionDTOList(electionList); // Modify ResponseData class to include a list of ElectionDTO objects
	    return new ResponseEntity<>(responseData, HttpStatus.OK);
	}
//------------------------view candidate -------------------------
	@Autowired
	WebClient.Builder candidateclient;
	public ResponseEntity<ResponseData> getCandidate()
	{

	ResponseData responseData=new ResponseData();
	Flux<CandidateDTO> flux=candidateclient.build()
			.get()
			.uri("http://localhost:8080/admin/getcandidate")
			.retrieve()
			.bodyToFlux(CandidateDTO.class);
			
			List<CandidateDTO> candidateList=flux.collectList().block();
			responseData.setCandidateDTOList(candidateList);
			return new ResponseEntity<>(responseData,HttpStatus.OK);
	}
//---------------------verifying voter-------------------------------------
	
	public ResponseEntity<String> verifyId(String aadhar, Integer status)
	{
		System.out.println(aadhar+status);
		Optional<VoterRequest> optionalVoterRequest=voterRequestRepo.findByAadharno(aadhar);
		if(status==1) {
			if(optionalVoterRequest.isPresent())
			{
				VoterRequest voterRequest=optionalVoterRequest.get();
				Status s=Status.approved;
				voterRequest.setStatus(s.toString());
				voterRequestRepo.save(voterRequest);
				return new ResponseEntity<String>("electoral approved suucessfully",HttpStatus.CREATED);
			}
			else
			{
				return new ResponseEntity<String>("aadhar is not found",HttpStatus.NOT_FOUND);
			}
			}
		else if(status==0)
		{
			if(optionalVoterRequest.isPresent())
			{
				VoterRequest voterRequest=optionalVoterRequest.get();
				Status s=Status.rejected;
				voterRequest.setStatus(s.toString());
				voterRequestRepo.save(voterRequest);
				return new ResponseEntity<String>("electoral rejected your request",HttpStatus.CREATED);
			}
			else
			{
				return new ResponseEntity<String>("aadhar is not found",HttpStatus.NOT_FOUND);
			}
		}
		return null;
	}
//---------------------Get Candidate By Id---------------------------------------------------------------
	public ResponseEntity<ResponseData> getCandidateById(Long id)
	{
		VoterRequest voterRequest=voterRequestRepo.findByVoterId(id);
		String constituency=voterRequest.getConstituency();
		System.out.println(constituency);
		
		ResponseData response=new ResponseData();
		
		Flux<CandidateDTO> flux=webClient.build()
				.get()
				.uri("http://localhost:8080/admin/getcandidatebyconstituency/{constituency}",constituency)
				.retrieve()
				.bodyToFlux(CandidateDTO.class);
		List<CandidateDTO> candidateList=flux.collectList().block();
		response.setCandidateDTOList(candidateList);
		return new ResponseEntity<ResponseData>(response,HttpStatus.OK);	
		
	}
//-------------------cast vote-----------------------------------------------------------
	public ResponseEntity<String> castVote(String candidateName)
	{
		ResponseEntity<String> response=webClient.build()
				.put()
				.uri("http://localhost:8080/admin/castVote/{candidateName}",candidateName)
				.retrieve()
				.toEntity(String.class)
				.block();
		return response;
		
	}
			
			
	



}
