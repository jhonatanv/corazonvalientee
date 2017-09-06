package co.org.smart.corazonvaliente.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "CARGOS")
@NamedQueries({ @NamedQuery(name = Cargo.LISTAR_CARGOS, query = "select c from Cargo c") })
public class Cargo implements Serializable {
	 
		public static final String LISTAR_CARGOS = "Cargo.listarC"; 



	@Id
	@Column(name = "nombre_cargo")
	private String nombre;

	@Column(name = "descripcion_cargo")
	private String descripcion;

	public Cargo() {
	}

	public Cargo(String nombre, String descripcion) {
		super();
		this.nombre = nombre;
		this.descripcion = descripcion;
	}


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

}
