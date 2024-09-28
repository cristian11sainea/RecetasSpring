package com.company.recetas.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();  // Para encriptar las contraseñas
    }
     /*@Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();  // Desactivar la encriptación de contraseñas
    }
 


     @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("admin")
            .password("admin")
            .roles("USER")
            .build();

        return new InMemoryUserDetailsManager(user);
    }
    */
        
    

    
   

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authz) -> authz
                .requestMatchers("/login").permitAll()   // Permitir acceso al login
                .anyRequest().authenticated()           // Requiere autenticación para cualquier otra ruta
            )
            .formLogin((form) -> form
                .loginPage("/login")  // Página de login personalizada
                .permitAll()
                .defaultSuccessUrl("/recetas", true)
                .failureUrl("/login?error=true") 
            )
            .logout((logout) -> logout
                .logoutUrl("/logout")  // URL para el logout
                .logoutSuccessUrl("/login?logout=true")  // Redirigir a la página de login tras logout
                .permitAll()
            );
        return http.build();
    }

    // Configuración temporal de usuario en memoria


    /*
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/login").permitAll()  // Permitir acceso al login sin autenticación
                .anyRequest().authenticated()           // Cualquier otra solicitud requiere autenticación
            )
            .formLogin(form -> form
                .loginPage("/login")                    // Indicar tu página personalizada de login
                .defaultSuccessUrl("/recetas", true)     // Redireccionar a /recetas después de un login exitoso
                .permitAll()
            )
            .logout(logout -> logout
                .permitAll()
            );

        return http.build();
    } */
   
}
