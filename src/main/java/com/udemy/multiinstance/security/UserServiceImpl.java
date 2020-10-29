package com.udemy.multiinstance.security;

import com.udemy.multiinstance.model.DoctorUser;
import com.udemy.multiinstance.service.DoctorUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.Arrays;
import java.util.List;


@Service(value = "userService")
public class UserServiceImpl implements UserDetailsService {
	
	@Autowired
	private DoctorUserRepository doctorUserRepository;

	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {


		List<DoctorUser> doctorUsers = doctorUserRepository.findByUserName(userName);
		DoctorUser doctorUser = null;
		if(doctorUsers == null || doctorUsers.isEmpty()){
			throw new UsernameNotFoundException("Invalid user email or password.");
		}else{
			doctorUser = doctorUsers.get(0);

		}

		return new org.springframework.security.core.userdetails.User(doctorUser.getUserName(),(doctorUser.getPassword()), getAuthority(doctorUser));
	}

	private List<SimpleGrantedAuthority> getAuthority(DoctorUser doctorUser) {

		return Arrays.asList(new SimpleGrantedAuthority(doctorUser.getUserTypeEnum().name()));
	}


}
