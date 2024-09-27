package com.company.recetas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.company.recetas.useCase.UsuarioRepository;

@Controller
public class LoginController {
        @Autowired
    private PasswordEncoder passwordEncoder;
     private  final UsuarioRepository usuarioRepository;

     public LoginController(UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
     }

    
    @GetMapping("/login")
    public String mostrarLogin() {
        /*Usuario usuario =  new  Usuario();
        usuario.setRole("ADMIN");
        usuario.setUser("cristian");
        usuario.setPassword(passwordEncoder.encode("cris1234"));
        usuarioRepository.save(usuario);*/
        return "login";  // Devuelve el nombre del archivo HTML en templates/login.html
    }

    

}
