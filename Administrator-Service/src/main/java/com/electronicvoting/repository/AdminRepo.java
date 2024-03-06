package com.electronicvoting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.electronicvoting.entity.AdminEntity;

@Repository
public interface AdminRepo extends JpaRepository<AdminEntity, Double> {

}
