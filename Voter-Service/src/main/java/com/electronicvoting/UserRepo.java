package com.electronicvoting;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.electronicvoting.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

}
