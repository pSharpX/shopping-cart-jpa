/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.cibertec.repositorio.impl;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import pe.edu.cibertec.dominio.Usuario;
import pe.edu.cibertec.repositorio.UsuarioRepositorio;

/**
 *
 * @author Java-LM
 */
public class UsuarioJpaRepositorioImpl
        implements UsuarioRepositorio {
    
    private EntityManager em;

    public UsuarioJpaRepositorioImpl setEntityManager(EntityManager em) {
        this.em = em;
        return this;
    }

    @Override
    public Usuario buscar(Long id) {
        return this.em.find(Usuario.class, id);
    }

    @Override
    public void crear(Usuario usuario) {
        this.em.persist(usuario);
    }

    @Override
    public void actualizar(Usuario usuario) {
        this.em.merge(usuario);
    }

    @Override
    public void eliminar(Usuario usuario) {
        this.em.remove(usuario);
    }

	@Override
	public Usuario buscar(String username, String contraseña) {
		// TODO Auto-generated method stub
		CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Usuario> cq = cb.createQuery(Usuario.class);
        Root<Usuario> usuario = cq.from(Usuario.class);
        cq
                .select(usuario)
                .where(cb.and(cb.equal(usuario.get("nombre"), username), cb.equal(usuario.get("apellido"), contraseña)))
                .orderBy(cb.asc(usuario.get("nombre")));
        TypedQuery<Usuario> query = this.em.createQuery(cq);
        return query.getSingleResult();
	}

}
