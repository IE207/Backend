package com.tananh.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tananh.config.JwtProvider;
import com.tananh.exception.UserException;
import com.tananh.modal.User;
import com.tananh.request.LoginRequest;
import com.tananh.response.AuthResponse;
import com.tananh.responsitory.UserResponsitory;
import com.tananh.service.CustomUserDetailServiceImplementation;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired PasswordEncoder passwordEncoder;
	@Autowired CustomUserDetailServiceImplementation customUserDetailServiceImplementation;
	@Autowired UserResponsitory userResponsitory;
	@Autowired JwtProvider jwtProvider;
	
	@PostMapping("/signup")
	public ResponseEntity<AuthResponse> createHanderUser(@RequestBody User user) throws UserException{
		String email= user.getEmail();
		String password=user.getPassword();
		String username=user.getUserName();
		String name=user.getName();
		String img=user.getImageURL();
		if(userResponsitory.findByEmail(email)!=null) {
			throw new UserException("Email đã được sử dụng");
		}
		User createUser= new User();
		createUser.setEmail(email);
		createUser.setPassword(passwordEncoder.encode(password));
		createUser.setUserName(username);
		createUser.setName(name);
		createUser.setImageURL(img);
		User UserSaved=userResponsitory.save(createUser);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(UserSaved.getEmail(),UserSaved.getPassword());
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		 String token = jwtProvider.genarateToken(authentication);
		 
		 AuthResponse authRes= new AuthResponse(token,true);
		 
		return new ResponseEntity<AuthResponse>(authRes,HttpStatus.CREATED);
	}
	
	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> loginHander(@RequestBody User user) throws UserException{
		String email=user.getEmail();
		String password=user.getPassword();
		
		Authentication authentication = authenticated(email, password);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		String token = jwtProvider.genarateToken(authentication);
		System.out.println("JWT token: " + token);
		 AuthResponse authRes= new AuthResponse(token,true);
		 
		return new ResponseEntity<AuthResponse>(authRes,HttpStatus.CREATED);
	}

	private Authentication authenticated(String email, String password) {
		UserDetails userDetails = customUserDetailServiceImplementation.loadUserByUsername(email);
		if(userDetails==null) {
			throw new BadCredentialsException("Tài khoản không đúng hoặc không chính xác!");
		}
		if(!passwordEncoder.matches(password, userDetails.getPassword()))
		{
			throw new BadCredentialsException("Mật khẩu không chính xác!");
		}
		return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
		
	}


}
