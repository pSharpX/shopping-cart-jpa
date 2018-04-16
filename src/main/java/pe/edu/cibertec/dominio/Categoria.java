/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.cibertec.dominio;

import javax.persistence.Entity;
import pe.edu.cibertec.dominio.base.EntidadBase;

/**
 *
 * @author Java-LM
 */
@Entity
public class Categoria extends EntidadBase{
    
    private String nombre;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
