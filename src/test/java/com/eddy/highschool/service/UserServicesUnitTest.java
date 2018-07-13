package com.eddy.highschool.service;

import static org.junit.Assert.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import com.eddy.highschool.models.User;
import com.eddy.highschool.repositories.UserRepository;
import com.eddy.highschool.services.UserServices;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.NONE)
public class UserServicesUnitTest {

	@Mock
	UserRepository userRepository;
	
	@InjectMocks
	UserServices userServices;
	
	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void test() {
		User edd = new User();
		edd.setFirstName("Eddy");
		
		when(userRepository.save(any(User.class))).thenReturn(edd);
		
		userServices.saveWithTeacherRole(edd);
		User newUser = userServices.findById(edd.getId());
		
		//assertNotNull(user);
		//assertNotNull(user.getId());
		assertEquals("Eddy",newUser.getFirstName());
	}
}
