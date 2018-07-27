package com.eddy.highschool.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.eddy.highschool.models.Role;
import com.eddy.highschool.models.User;
import com.eddy.highschool.repositories.UserRepository;

@Service
public class UserDetailsServiceImplementation implements UserDetailsService{
	private UserRepository uR;
	
	public UserDetailsServiceImplementation (UserRepository uR) {
		this.uR = uR;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
		User user = uR.findByUsername(username);
		if(user == null) throw new UsernameNotFoundException("User not found");
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), getAuthorities(user));
	}
	
	private List<GrantedAuthority> getAuthorities(User user){
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		for(Role role: user.getRoles()) {
			GrantedAuthority gA = new SimpleGrantedAuthority(role.getName());
			authorities.add(gA);
		}
		return authorities;
	}
}	
