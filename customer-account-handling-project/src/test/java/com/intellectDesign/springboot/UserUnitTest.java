package com.intellectDesign.springboot;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import com.intellectDesign.springboot.model.User;
import com.intellectDesign.springboot.service.rs.RestService;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@ContextConfiguration(classes = { Application.class, TestConfiguration.class })
public class UserUnitTest {
	
	@Autowired
	RestService service;
	User user;
	@Before
	public void setUp() throws Exception {
		 user = new User();
		 
	}
	
	
	@Test
	public void testQuickNegative() throws Exception {
		Date birthDate = new Date("02-MAR-2080");
		user.setBirthDate(birthDate);
		 String st = service.createUser(user);
		assertEquals("234", st);
	}
	
	@Test
	public void testQuickPositive() throws Exception {
		Date birthDate = new Date("02-MAR-2011");
		user.setBirthDate(birthDate);
		 String st = service.createUser(user);
		assertEquals("123", st);
	}
	
}
