package com.manav.startapp.services;

import org.springframework.stereotype.Service;

import com.manav.startapp.entities.User;


@Service
public class UserDetailImpl implements UserDetail {
	public User getUserDetail() {
	  		return new User();
	}

}
