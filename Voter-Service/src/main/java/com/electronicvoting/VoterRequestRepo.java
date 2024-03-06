package com.electronicvoting;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.electronicvoting.entity.VoterRequest;

public interface VoterRequestRepo extends JpaRepository<VoterRequest, Long> {


    Optional<VoterRequest> findByAadharno(String aadharno);

	VoterRequest findByVoterId(Long id);



}
