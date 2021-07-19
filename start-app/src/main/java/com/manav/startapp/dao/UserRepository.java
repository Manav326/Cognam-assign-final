package com.manav.startapp.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.manav.startapp.entities.User;

//public interface UserRepository extends JpaRepository{
public interface UserRepository extends JpaRepository<User, Long> {
	User findByUsername(String username);
	User findByToken(String token);
	User findByAccountNumber(Long account_number);

}
