package com.company.recetas;
import java.util.List;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.company.recetas.model.Usuario;
import com.company.recetas.useCase.UsuarioRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

        @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Obtener la lista de usuarios con el nombre de usuario proporcionado
        List<Usuario> usuarios =  (List<Usuario>)usuarioRepository.findByUser(username);

        // Si no se encuentra ningún usuario, lanzar excepción
        if (usuarios.isEmpty()) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }

        // Obtener el primer usuario de la lista (asumiendo que debe ser único)
        Usuario usuario = usuarios.get(0);

        // Convertir el Usuario a UserDetails de Spring Security
        return User.builder()
                .username(usuario.getUser())  // el nombre de usuario
                .password(usuario.getPassword())  // la contraseña ya debe estar encriptada
                .roles(usuario.getRole())  // asume que tienes un campo `role` en Usuario
                .build();
    }


    public void saveUser(Usuario usuario) {
        // Aquí se usa el passwordEncoder para encriptar la contraseña antes de guardar
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioRepository.save(usuario);
    }
}
