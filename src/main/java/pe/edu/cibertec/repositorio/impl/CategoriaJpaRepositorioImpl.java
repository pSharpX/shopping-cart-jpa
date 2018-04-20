package pe.edu.cibertec.repositorio.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import pe.edu.cibertec.dominio.Categoria;
import pe.edu.cibertec.repositorio.CategoriaRepositorio;

public class CategoriaJpaRepositorioImpl implements CategoriaRepositorio {

	private EntityManager em;
	private static final String SELECT_CATEGORIAS = "SELECT c FROM Categoria c";
    
    public CategoriaJpaRepositorioImpl setEntityManager(EntityManager em){
        this.em = em;
        return this;
    }
    
	@Override
	public Categoria buscar(Long id) {
		// TODO Auto-generated method stub
		return this.em.find(Categoria.class, id);
	}

	@Override
	public List<Categoria> obtenerTodos() {
		// TODO Auto-generated method stub
		TypedQuery<Categoria> query = this.em.createQuery(SELECT_CATEGORIAS, Categoria.class);
        return query.getResultList();
	}

}
