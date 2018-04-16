/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.cibertec.repositorio.impl;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import pe.edu.cibertec.dominio.DetalleCarrito;
import pe.edu.cibertec.repositorio.DetalleCarritoRepositorio;

/**
 *
 * @author Java-LM
 */
public class DetalleCarritoJpaRepositorioImpl 
        implements DetalleCarritoRepositorio{

    private EntityManager em;

    public DetalleCarritoJpaRepositorioImpl setEntityManager(EntityManager em) {
        this.em = em;
        return this;
    }
    @Override
    public DetalleCarrito buscar(Long id) {
        return this.em.find(DetalleCarrito.class, id);
    }

    @Override
    public List<DetalleCarrito> buscarPorProducto(Long idProducto) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<DetalleCarrito> cq = cb.createQuery(DetalleCarrito.class);
        Root<DetalleCarrito> detalle = cq.from(DetalleCarrito.class);
        cq
                .select(detalle)
                .where(cb.equal(detalle.get("producto").get("id"), idProducto))
                .orderBy(cb.asc(detalle.get("id")));
        TypedQuery<DetalleCarrito> query = this.em.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public List<DetalleCarrito> buscarPorCarrito(Long idCarrito) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<DetalleCarrito> cq = cb.createQuery(DetalleCarrito.class);
        Root<DetalleCarrito> detalle = cq.from(DetalleCarrito.class);
        cq
                .select(detalle)
                .where(cb.equal(detalle.get("carrito").get("id"), idCarrito))
                .orderBy(cb.asc(detalle.get("id")));
        TypedQuery<DetalleCarrito> query = this.em.createQuery(cq);
        return query.getResultList();
    }

    @Override
    public void crear(DetalleCarrito detalle) {
        this.em.persist(detalle);
    }

    @Override
    public void actualizar(DetalleCarrito detalle) {
        this.em.merge(detalle);
    }

    @Override
    public void eliminar(DetalleCarrito detalle) {
        this.em.remove(detalle);
    }
    
}
