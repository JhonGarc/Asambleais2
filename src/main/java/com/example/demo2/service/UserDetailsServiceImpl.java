package com.example.demo2.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.stereotype.Service;

import com.example.demo2.entity.Rol;
import com.example.demo2.entity.Usuario;
import com.example.demo2.repository.UsuarioRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	public UsuarioRepository repositorio;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario usuarios = repositorio.findByUsername(username);
		UserBuilder builder = null;
        if(usuarios!=null) {
        	builder=User.withUsername(username);
        	builder.disabled(false);
			builder.password(usuarios.getPassword());
			Rol rolUsuario = usuarios.getRoles();
			GrantedAuthority authority = new SimpleGrantedAuthority(rolUsuario.getRol());
			builder.authorities(authority);
        }else {
        	throw new UsernameNotFoundException("Usuario no encontrado");
        }
		return builder.build();
	}

	
	
}
