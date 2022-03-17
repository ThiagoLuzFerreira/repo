package com.thiago.cadastro2.dao;

import com.thiago.cadastro2.entity.Dependente;
import com.thiago.cadastro2.entity.Usuario;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

@Repository
public class DependenteDaoImpl implements DependenteDao{

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Dependente dependente) {
        em.persist(dependente);
    }

    @Override
    public void upDate(Dependente dependente) {
        em.merge(dependente);
    }

    @Override
    public void delete(Long id) {
        em.remove(em.getReference(Dependente.class, id));
    }

    @Override
    public Dependente findById(Long id) {
        return em.find(Dependente.class, id);
    }

    @Override
    public List<Dependente> findAll() {
        return em.createQuery("select d from Dependente d", Dependente.class).getResultList();
    }

    @Override
    public List<Dependente> findByUsuario(Long id) {
        return em.createQuery("select d from Dependente d where d.usuario.id = :id", Dependente.class)
                .setParameter("id", id)
                .getResultList();
    }
}
