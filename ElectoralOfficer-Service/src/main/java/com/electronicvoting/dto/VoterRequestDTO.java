package com.electronicvoting.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VoterRequestDTO {
	
	
	String aadharno;
	String constituency;
	String status;
	

}