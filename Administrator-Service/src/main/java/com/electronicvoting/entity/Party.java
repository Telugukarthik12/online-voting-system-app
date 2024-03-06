package com.electronicvoting.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "party")
public class Party {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Long id;
	String partyName;
	String leader;
	String symbol;
	
}