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
import org.springframework.http.ResponseEntity;
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
		 user.setEmail("john.Smith@gmail.com");
		 user.setfName("john");
		 user.setlName("smith");
		 user.setId("df4567");
		 user.setPinCode(713325);
	}
	
	
	@Test
	public void testQuickNegative() throws Exception {
		Date birthDate = new Date("02-MAR-2080");
		user.setBirthDate(birthDate);
		 ResponseEntity st = service.createUser(user);
		assertEquals(400, st.getStatusCodeValue());
	}
	
	@Test
	public void testQuickPositive() throws Exception {
		Date birthDate = new Date("02-MAR-2011");
		user.setBirthDate(birthDate);
		 ResponseEntity st = service.createUser(user);
		 assertEquals(200, st.getStatusCodeValue());
	}
	
}
