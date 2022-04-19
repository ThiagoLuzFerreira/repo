package com.thiago.cadastro2.service;

import com.thiago.cadastro2.dao.UsuarioDao;
import com.thiago.cadastro2.entity.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioDao dao;

    @Override
    public void save(Usuario usuario) {
        usuario.setDataCadastro(LocalDate.now());
        dao.save(usuario);
    }

    @Override
    public void upDate(Usuario usuario) {
        if(usuario.getDataCadastro() == null ){
            usuario.setDataCadastro(LocalDate.now());
        }
        dao.upDate(usuario);
    }

    @Override
    public void delete(Long id) {
        dao.delete(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario findById(Long id) {
        return dao.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> findAll() {
        return dao.findAll();
    }

    @Override
    public List<Usuario> findByNomeOuSobrenome(String buscaNome) {
        return dao.findByNomeOuSobrenome(buscaNome);
    }

    @Override
    public List<Usuario> findByEmail(String buscaEmail) {
        return dao.findByEmail(buscaEmail);
    }

    @Override
    public Usuario findByEmail2(String email) {
        return dao.findByEmail2(email);
    }

}
