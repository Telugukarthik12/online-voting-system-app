package com.electronicvoting.dto;

import java.util.List;

public class ResponseData {
    private List<ElectionDTO> electionDTOList;
    private List<CandidateDTO> candidateDTOList;
    private CandidateDTO candidateDTO;

    public List<CandidateDTO> getCandidateDTOList() {
		return candidateDTOList;
	}

	public void setCandidateDTOList(List<CandidateDTO> candidateDTOList) {
		this.candidateDTOList = candidateDTOList;
	}

	public List<ElectionDTO> getElectionDTOList() {
        return electionDTOList;
    }

    public void setElectionDTOList(List<ElectionDTO> electionDTOList) {
        this.electionDTOList = electionDTOList;
    }
	
	

}
