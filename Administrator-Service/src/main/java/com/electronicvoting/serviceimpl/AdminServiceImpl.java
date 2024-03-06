package com.electronicvoting.serviceimpl;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.client.RestClientAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.electronicvoting.dto.CandidateDTO;
import com.electronicvoting.dto.ConstituencyWinner;
import com.electronicvoting.dto.ElectionDTO;
import com.electronicvoting.dto.PartyDTO;
import com.electronicvoting.entity.AdminEntity;
import com.electronicvoting.entity.Candidate;
import com.electronicvoting.entity.Party;
import com.electronicvoting.repository.AdminRepo;
import com.electronicvoting.repository.CandidateRepo;
import com.electronicvoting.repository.PartyRepo;
import com.electronicvoting.service.AdminService;

@Service
public class AdminServiceImpl implements AdminService{
	
	
	@Autowired
	AdminRepo adminRepo;
	
	
	
	
	ElectionDTO electionDTO=new ElectionDTO();


	
	
	public String addElection(ElectionDTO electionDTO)
	{
		
		AdminEntity adminEntity=new AdminEntity();

		adminEntity.setElectionName(electionDTO.getElectionName());
		adminEntity.setState(electionDTO.getState());
		adminEntity.setConstituency(electionDTO.getConstituency());
		adminRepo.save(adminEntity);
		System.out.println(adminEntity);
		return "added sucesfully";
		
	}



	@Override
	public List<AdminEntity> getElection() {
		// TODO Auto-generated method stub
		
		return adminRepo.findAll();
		
		
//		electionDTO.setElectionName(adminEntity.getElectionName());
//		electionDTO.setConstituency(adminEntity.getConstituency());
//		electionDTO.setState(adminEntity.getState());
//		System.out.println(electionDTO);
//		return electionDTO;
		
	}
	//-------------------Party Service---------------------------------
	
	@Autowired
	PartyRepo partyRepo;
	
	public ResponseEntity<String> addparty(PartyDTO partyDTO)
	{
		
		
		Party party=new Party();
		party.setPartyName(partyDTO.getPartyName());
		party.setLeader(partyDTO.getLeader());
		party.setSymbol(partyDTO.getSymbol());
		partyRepo.save(party);
		return new ResponseEntity<>("Party added!v successfully",HttpStatus.CREATED);
		
	}
	public ResponseEntity<List<PartyDTO>> getparty()
	{
		List<PartyDTO> listDTO=new ArrayList<>();
		List<Party> listParty=partyRepo.findAll();
		for(Party party:listParty)
		{
			PartyDTO partyDTO=new PartyDTO();
			partyDTO.setPartyName(party.getPartyName());
			partyDTO.setLeader(party.getLeader());
			partyDTO.setSymbol(party.getSymbol());
			listDTO.add(partyDTO);
		}
		return new ResponseEntity<>(listDTO,HttpStatus.OK);
		
	}
	
//-------------------candidate service-----------------------
	@Autowired
	CandidateRepo candidateRepo;
	public ResponseEntity<String> addCandidate(CandidateDTO candidateDTO)
	{
		Candidate candidate=new Candidate();

		
		candidate.setCname(candidateDTO.getCname());
		candidate.setAddress(candidateDTO.getAddress());
		candidate.setAge(candidateDTO.getAge());
		candidate.setMobile(candidateDTO.getMobile());
		candidate.setParty(candidateDTO.getParty());
		candidate.setConstituency(candidateDTO.getConstituency());
		candidate.setVotes(0);
		candidateRepo.save(candidate);
		return new ResponseEntity<String>("candidate added! successfully",HttpStatus.CREATED);
		
		
	}
	public ResponseEntity<List<CandidateDTO>> getCandidate()
	{
		List<CandidateDTO> listDTO=new ArrayList<>();
		List<Candidate> listcandidates=candidateRepo.findAll();
		for(Candidate candidate:listcandidates)
		{
			CandidateDTO candidateDTO=new CandidateDTO();
			candidateDTO.setCname(candidate.getCname());
			candidateDTO.setAddress(candidate.getAddress());
			candidateDTO.setAge(candidate.getAge());
			candidateDTO.setMobile(candidate.getMobile());
			candidateDTO.setParty(candidate.getParty());
			candidateDTO.setConstituency(candidate.getConstituency());
			listDTO.add(candidateDTO);
		}
		return new ResponseEntity<List<CandidateDTO>>(listDTO,HttpStatus.OK);
	}
//------------------------get candidate by constituetion-----------------------------------
	
	public ResponseEntity<List<CandidateDTO>> getCandidateByConstituency(String consti)
	{
		List<CandidateDTO> list=new ArrayList<>();
		List<Candidate> listcandidate=candidateRepo.findByConstituency(consti);
		for(Candidate candidate:listcandidate)
		{
			CandidateDTO candidateDTO=new CandidateDTO();
			candidateDTO.setAddress(candidate.getAddress());
			candidateDTO.setAge(candidate.getAge());
			candidateDTO.setCname(candidate.getCname());
			candidateDTO.setConstituency(candidate.getConstituency());
			candidateDTO.setMobile(candidate.getMobile());
			candidateDTO.setParty(candidate.getParty());
			list.add(candidateDTO);
		}
		return new ResponseEntity<List<CandidateDTO>>(list,HttpStatus.OK);
	}
//--------------------cast vote------------------------------------------------------------------
	public ResponseEntity<String> castVote(String candidateName)
	{
		Candidate candidate=candidateRepo.findByCname(candidateName);
		Candidate candidates=candidate;
		candidates.setVotes(candidates.getVotes()+1);
		candidateRepo.save(candidates);
		return new ResponseEntity<String>("Voted successfully",HttpStatus.OK);
	}
//------------display who got highest votes-------------------
	public ResponseEntity<String> highestVotes() {
        List<Candidate> candidates = candidateRepo.findCandidatesWithHighestVotes();
        if (candidates.isEmpty()) {
            
            return ResponseEntity.ok("No winner found");
        } else if (candidates.size() == 1) {
            
            Candidate winner = candidates.get(0);
            String message = "The winner is " + winner.getCname() + " in " + winner.getConstituency() + " constituency with " + winner.getVotes() + " votes";
            System.out.println(message);
            return ResponseEntity.ok(message);
        } else {
            
            String winners = candidates.stream()
                                      .map(candidate -> candidate.getCname())
                                      .collect(Collectors.joining(" and "));
            String message = "The winners are " + winners + " in " + candidates.get(0).getConstituency() + " constituency with " + candidates.get(0).getVotes() + " votes each";
            System.out.println(message);
            return ResponseEntity.ok(message);
        }
    }
	
//--------------------------------get all winners as per the constituency--------------------------------------
	public ResponseEntity<List<ConstituencyWinner>> getWinners()
	{
		List<ConstituencyWinner> winners=candidateRepo.findWinnersPerConstituency();
		System.out.println(winners);
		return ResponseEntity.ok(winners);
	}
}






