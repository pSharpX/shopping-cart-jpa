/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.cibertec.dominio;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import pe.edu.cibertec.dominio.base.EntidadBase;

/**
 *
 * @author Java-LM
 */
@Entity
@Table(name = "tab_producto")
@NamedQueries(
        @NamedQuery(
                name = Producto.NQ_OBTENER_PRODUCTO_POR_CATEGORIA,
                query = "SELECT p FROM Producto p WHERE p.categoria.id = :idCategoria"
        )
)
public class Producto extends EntidadBase {       
    private String nombre;
    private String descripcion;
    
    public static final String NQ_OBTENER_PRODUCTO_POR_CATEGORIA = "nq_obtener_producto_por_categoria";
    
    @Column(name = "precio", precision = 10, scale = 2)
    private BigDecimal precio;
    
    @Lob
    private byte[] imagen;
    
    @ManyToOne(optional = false)
    @JoinColumn(name = "id_categoria")
    Categoria categoria;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public byte[] getImagen() {
        return imagen;
    }

    public void setImagen(byte[] imagen) {
        this.imagen = imagen;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    
}
