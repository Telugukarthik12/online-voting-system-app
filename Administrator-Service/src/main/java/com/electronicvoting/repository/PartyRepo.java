package com.electronicvoting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.electronicvoting.entity.Party;

@Repository
public interface PartyRepo extends JpaRepository<Party, Long> {
	

}
