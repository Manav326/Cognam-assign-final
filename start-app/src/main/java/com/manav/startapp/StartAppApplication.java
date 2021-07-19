package com.manav.startapp;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import com.manav.startapp.dao.UserRepository;
import com.manav.startapp.entities.User;

@SpringBootApplication
public class StartAppApplication {

	public static void main(String[] args) {
		ApplicationContext context = (ApplicationContext) SpringApplication.run(StartAppApplication.class, args);
		/*
		 * UserRepository UserRepository = ((BeanFactory)
		 * context).getBean(UserRepository.class); User User1 = new User();
		 * User1.setUsername("Manav"); User1.setPassword("Abc@12");
		 * User1.setAddress("Delhi"); User retUser = UserRepository.save(User1);
		 * System.out.println(retUser);
		 */
	}

}
