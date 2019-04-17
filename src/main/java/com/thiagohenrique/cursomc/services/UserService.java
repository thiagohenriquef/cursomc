package com.thiagohenrique.cursomc.services;

import org.springframework.security.core.context.SecurityContextHolder;

import com.thiagohenrique.cursomc.security.UserSS;

public class UserService {
	
	public static UserSS authenticated() {
		try {
			System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
			return (UserSS)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		}
		catch (Exception e) {
			System.out.println("excecao "+ e);
			return null;
		}
	}
}