package com.unit.impulsioneai.config.security;

import com.unit.impulsioneai.components.SecurityFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalAuthentication
public class SecurityConfigurations {
    @Autowired
    private SecurityFilter securityFilter;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers(HttpMethod.POST, "auth/login").permitAll()
                        .requestMatchers(HttpMethod.POST,"/admin").permitAll()
                        .requestMatchers(HttpMethod.POST, "/empreendedores").permitAll()
                        .requestMatchers(HttpMethod.POST,"/usuarios").permitAll()
                        .requestMatchers(HttpMethod.POST, "/produtos").permitAll()
                        .requestMatchers(HttpMethod.POST,"/endereco").permitAll()
                        .requestMatchers(HttpMethod.POST, "/endereco").permitAll()
                        .requestMatchers(HttpMethod.POST, "/email").permitAll()
                        .requestMatchers(HttpMethod.GET, "/verificaUsuarios").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/verificaUsuarios").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/editarSenha/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/editarSenha").permitAll()
                        .requestMatchers(HttpMethod.POST, "/editarSenha").permitAll()
                        .requestMatchers(HttpMethod.POST, "/cartao").permitAll()
                        .requestMatchers(HttpMethod.GET, "/cartao/**").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/deleteCartao/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/filtrarProdutos").permitAll()
                        .requestMatchers(HttpMethod.GET, "/filtrarEmpreendedores").permitAll()
                        .requestMatchers(HttpMethod.PUT,"/admin").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/empreendedores/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/empreendedoresPlano/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/editarBiografia/**").permitAll()
                        .requestMatchers(HttpMethod.PUT,"/usuarios").hasAnyRole("USUARIO")
                        .requestMatchers(HttpMethod.PUT, "/produtos").hasAnyRole("EMPREENDEDOR")
                        .requestMatchers(HttpMethod.PUT,"/endereco").hasAnyRole("EMPREENDEDOR")
                        .requestMatchers(HttpMethod.DELETE, "/empreendedores").hasAnyRole("EMPREENDEDOR")
                        .requestMatchers(HttpMethod.DELETE,"/usuarios").hasAnyRole("USUARIO")
                        .requestMatchers(HttpMethod.DELETE,"/admin").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/produtos").hasAnyRole("EMPREENDEDOR")
                        .requestMatchers(HttpMethod.DELETE,"/endereco").hasAnyRole("EMPREENDEDOR")
                        .requestMatchers(HttpMethod.GET).permitAll()
                        .requestMatchers(HttpMethod.GET,"/admin").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/nichos/**").permitAll()

                        .requestMatchers(HttpMethod.POST, "/assinaturas").permitAll()
                        .requestMatchers(HttpMethod.GET, "/assinaturas/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/assinaturas").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/assinaturas/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/assinaturas/**").permitAll()

                        .requestMatchers(HttpMethod.POST, "/depoimento").permitAll()
                        .requestMatchers(HttpMethod.GET, "/depoimento/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/depoimento").permitAll()
                        .requestMatchers(HttpMethod.DELETE, "/depoimento/**").permitAll()
                        .requestMatchers(HttpMethod.PUT, "/depoimento/**").permitAll()

                        .anyRequest().authenticated())
                .addFilterBefore(securityFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder(){
       return new BCryptPasswordEncoder();
    }
}
