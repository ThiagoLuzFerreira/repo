package com.thiago.cadastro2.security;

import com.thiago.cadastro2.dao.UsuarioDao;
import com.thiago.cadastro2.entity.Usuario;
import com.thiago.cadastro2.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public class ImplUserDetailService implements UserDetailsService {

    @Autowired
    private UsuarioService service;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario usuario = service.findByEmail2(email);
        if(usuario == null){
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        return new User(usuario.getUsername(), usuario.getPassword(), true, true, true, true, usuario.getAuthorities());
    }
}
