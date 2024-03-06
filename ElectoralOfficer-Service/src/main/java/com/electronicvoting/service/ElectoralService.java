package com.electronicvoting.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.electronicvoting.dto.ResponseData;
import com.electronicvoting.dto.VoterRequestDTO;

import reactor.core.publisher.Mono;

public interface ElectoralService {

	ResponseEntity<ResponseData> getVoterRequest();

	ResponseEntity<String> verifyId(String aadhar, Integer status);


}
