package com.thiago.cadastro2.dao;

import com.thiago.cadastro2.entity.Usuario;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UsuarioDaoImpl implements UsuarioDao{

    @PersistenceContext
    private EntityManager em;

    @Override
    public void save(Usuario usuario) {em.persist(usuario);    }

    @Override
    public void upDate(Usuario usuario) {
        em.merge(usuario);
    }

    @Override
    public void delete(Long id) {
        em.remove(em.getReference(Usuario.class, id));
    }

    @Override
    public Usuario findById(Long id) {
        return em.find(Usuario.class, id);
    }

    @Override
    public List<Usuario> findAll() {
        return em.createQuery("select u from Usuario u", Usuario.class).getResultList();
    }

    @Override
    public List<Usuario> findByNomeOuSobrenome(String buscaNome) {
        String jpql = "from " +
                "Usuario u where u.nome like :nome or u.sobrenome like :sobrenome";
        TypedQuery<Usuario> query =em.createQuery(jpql, Usuario.class);
        query.setParameter("nome", "%"+buscaNome+"%");
        query.setParameter("sobrenome", "%"+buscaNome+"%");
        return query.getResultList();
    }

    @Override
    public List<Usuario> findByEmail(String buscaEmail) {
        TypedQuery<Usuario> query = em.createQuery("from Usuario u where u.email like :email", Usuario.class);
        query.setParameter("email", "%"+buscaEmail+"%");
        return query.getResultList();
    }
}
