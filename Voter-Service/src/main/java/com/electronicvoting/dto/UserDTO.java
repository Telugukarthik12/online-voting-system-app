package com.electronicvoting.dto;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UserDTO {
	String name;
	String address;
	String dob;
	String gender;
	String mobile;
	String district;

}