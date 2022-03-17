package com.thiago.cadastro2.service;

import com.thiago.cadastro2.dao.DependenteDao;
import com.thiago.cadastro2.entity.Dependente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DependenteServiceImpl implements DependenteService{

    @Autowired
    private DependenteDao dpDao;

    @Override
    public void save(Dependente dependente) {
        dpDao.save(dependente);
    }

    @Override
    public void upDate(Dependente dependente) {
        dpDao.upDate(dependente);
    }

    @Override
    public void delete(Long id) {
        dpDao.delete(id);
    }

    @Override
    public Dependente findById(Long id) {
        return dpDao.findById(id);
    }

    @Override
    public List<Dependente> findAll() {
        return dpDao.findAll();
    }

    @Override
    public List<Dependente> findByUsuario(Long id) {
        return dpDao.findByUsuario(id);
    }
}
