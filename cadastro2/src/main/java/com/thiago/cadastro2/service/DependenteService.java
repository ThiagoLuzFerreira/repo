package com.thiago.cadastro2.service;

import com.thiago.cadastro2.entity.Dependente;

import java.util.List;

public interface DependenteService {

    void save(Dependente dependente);
    void upDate(Dependente dependente);
    void delete (Long id);
    Dependente findById(Long id);
    List<Dependente> findAll();
    List<Dependente> findByUsuario(Long id);
}
