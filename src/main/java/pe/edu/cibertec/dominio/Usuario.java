/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pe.edu.cibertec.dominio;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PostLoad;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import pe.edu.cibertec.dominio.base.EntidadBase;

/**
 *
 * @author Java-LM
 */
@Entity
public class Usuario extends EntidadBase {

	private String nombre;
	private String apellido;

	@Column(name = "fecha_nacimiento")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaNacimiento;

	@Transient
	private Integer edad;

	public Integer getEdad() {
		return edad;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	@PostLoad
	public void despuesCargarEntidad() {
		if (this.fechaNacimiento != null) {
			GregorianCalendar today = GregorianCalendar.from(ZonedDateTime.now());
			GregorianCalendar birthday = new GregorianCalendar();
			birthday.setTime(this.fechaNacimiento);

			int year = (today.get(Calendar.YEAR) - birthday.get(Calendar.YEAR));
			if (birthday.get(Calendar.MONTH) == today.get(Calendar.MONTH)) {
				if (birthday.get(Calendar.DAY_OF_MONTH) > today.get(Calendar.DAY_OF_MONTH)) {
					this.edad = year - 1;
				} else {
					this.edad = year;
				}
			} else if (birthday.get(Calendar.MONTH) > today.get(Calendar.MONTH)) {
				this.edad = year - 1;
			} else {
				this.edad = year;
			}
		}
	}

}
