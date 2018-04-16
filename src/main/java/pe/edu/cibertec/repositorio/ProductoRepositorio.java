/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.cibertec.repositorio;

import java.util.List;
import pe.edu.cibertec.dominio.Producto;

/**
 *
 * @author Java-LM
 */
public interface ProductoRepositorio {
    Producto buscar(Long id);
    List<Producto> obtenerTodos();
    List<Producto> obtenerPorCategoria(Long idCategoria);
    List<Producto> obtenerPorCategoriaCriteriaApi(Long idCategoria);
}
