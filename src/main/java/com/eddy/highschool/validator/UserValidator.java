package com.eddy.highschool.validator;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.eddy.highschool.models.User;
import com.eddy.highschool.services.UserServices;

@Component
public class UserValidator implements Validator {
	private UserServices uS;
	public UserValidator(UserServices uS) {
		this.uS = uS;
	}
	
	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}
	
	@Override
	public void validate(Object object, Errors errors) {
		User user= (User) object;
		
		if(!user.getConfirm().equals(user.getPassword())) {
			errors.rejectValue("confirm", "Match");
		}
		
		if(uS.findByUsername(user.getUsername()) != null) {
			errors.rejectValue("username", "Exists");
		}
		if(uS.findByEmail(user.getEmail()) != null) {
			errors.rejectValue("email", "Exists");
		}
		
	}
	
}