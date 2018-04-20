package pe.edu.cibertec.repositorio;

import java.util.List;

import pe.edu.cibertec.dominio.Categoria;

public interface CategoriaRepositorio {
	Categoria buscar(Long id);
    List<Categoria> obtenerTodos();
}
