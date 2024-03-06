package com.electronicvoting.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ElectionDTO {
	
	String electionName;
	String state;
	String constituency;
	

}
