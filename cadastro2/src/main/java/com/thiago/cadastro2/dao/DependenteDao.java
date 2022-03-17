package com.thiago.cadastro2.dao;

import com.thiago.cadastro2.entity.Dependente;
import com.thiago.cadastro2.entity.Usuario;

import java.util.List;

public interface DependenteDao {

    void save(Dependente dependente);
    void upDate(Dependente dependente);
    void delete (Long id);
    Dependente findById(Long id);
    List<Dependente> findAll();
    List<Dependente> findByUsuario(Long id);
}
