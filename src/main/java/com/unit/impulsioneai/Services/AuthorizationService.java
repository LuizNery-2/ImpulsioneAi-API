package com.unit.impulsioneai.Services;


import com.unit.impulsioneai.repositories.AdminRepository;
import com.unit.impulsioneai.repositories.EmpreendedoresRepository;
import com.unit.impulsioneai.repositories.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements UserDetailsService {
    @Autowired
    EmpreendedoresRepository empreendedoresRepository;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails empreendedor = empreendedoresRepository.findByEmail(username);
        UserDetails usuario = usuarioRepository.findByEmail(username);
        UserDetails admin = adminRepository.findByEmail(username);
        if (admin != null){
            return admin;
        }
        else if (empreendedor != null) {
            return empreendedor;
        }
        else if (usuario != null) {
            return usuario;
        }
        throw new UsernameNotFoundException("Usuario náo encontrado");
    }
}
