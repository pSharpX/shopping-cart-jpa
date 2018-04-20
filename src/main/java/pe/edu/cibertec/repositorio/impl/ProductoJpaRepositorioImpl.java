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
import pe.edu.cibertec.dominio.Producto;
import pe.edu.cibertec.repositorio.ProductoRepositorio;

/**
 *
 * @author Java-LM
 */
public class ProductoJpaRepositorioImpl implements ProductoRepositorio {
    
    private EntityManager em;
    private static final String SELECT_PRODUCTOS = "SELECT p FROM Producto p";
    
    public ProductoJpaRepositorioImpl setEntityManager(EntityManager em){
        this.em = em;
        return this;
    }
    
    @Override
    public Producto buscar(Long id) {
        return this.em.find(Producto.class, id);
    }

    @Override
    public List<Producto> obtenerTodos() {
         TypedQuery<Producto> query = this.em.createQuery(SELECT_PRODUCTOS, Producto.class);
         return query.getResultList();
    }

    @Override
    public List<Producto> obtenerPorCategoria(Long idCategoria) {
        TypedQuery<Producto> query = this.em.createNamedQuery(
                Producto.NQ_OBTENER_PRODUCTO_POR_CATEGORIA, Producto.class);
        query.setParameter("idCategoria", idCategoria);
        return query.getResultList();
    }

    @Override
    public List<Producto> obtenerPorCategoriaCriteriaApi(Long idCategoria) {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Producto> cq = cb.createQuery(Producto.class);
        Root<Producto> producto = cq.from(Producto.class);
        cq
                .select(producto)
                .where(cb.equal(producto.get("categoria").get("id"), idCategoria))
                .orderBy(cb.asc(producto.get("nombre")));
        TypedQuery<Producto> query = this.em.createQuery(cq);
        return query.getResultList();
    }

	@Override
	public void crear(Producto producto) {
		this.em.persist(producto);		
	}

	@Override
	public void actualizar(Producto producto) {
		this.em.merge(producto);		
	}

	@Override
	public void eliminar(Producto producto) {
		this.em.remove(producto);		
	}
    
}
