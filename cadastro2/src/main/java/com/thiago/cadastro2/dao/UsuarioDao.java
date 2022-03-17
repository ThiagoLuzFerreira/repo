package com.thiago.cadastro2.dao;

import com.thiago.cadastro2.entity.Usuario;

import java.util.List;

public interface UsuarioDao {

    void save(Usuario usuario);
    void upDate(Usuario usuario);
    void delete (Long id);
    Usuario findById(Long id);
    List<Usuario> findAll();
    List<Usuario> findByNomeOuSobrenome(String buscaNome);
    List<Usuario> findByEmail(String buscaEmail);

}
