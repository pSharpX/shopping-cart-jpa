/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.cibertec.repositorio;

import java.util.List;
import pe.edu.cibertec.dominio.CarritoCompra;

/**
 *
 * @author Java-LM
 */
public interface CarritoCompraRepositorio {
    
    List<CarritoCompra> buscarPorUsuario(Long idUsuario);
}
