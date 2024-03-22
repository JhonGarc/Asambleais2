package com.example.demo2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClientController {
  
	@GetMapping("/auth/login")
    public String index(){
        return"index";
    }
	
	@GetMapping("/public/user")
    public String User(){
        return"user";
    }
	
	
    
    
}

