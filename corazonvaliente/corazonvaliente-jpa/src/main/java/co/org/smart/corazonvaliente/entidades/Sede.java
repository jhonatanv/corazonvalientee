package co.org.smart.corazonvaliente.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "SEDES")
@NamedQueries({ @NamedQuery(name = Sede.LISTAR_SEDES, query = "select s from Sede s") })
public class Sede implements Serializable {

	public static final String LISTAR_SEDES = "Sede.listarS";

	@Id
	@Column(name = "nombre_sede")
	private String nombre;

	@ManyToOne
	@JoinColumn(name = "ciudad_sede")
	private Ciudad ciudad;

	@Column(name = "telefono_sede")
	private String telefono;

	@Column(name = "direccion_sede")
	private String direccion;

	public Sede() {
	}

	public Sede(Ciudad ciudad, String nombre, String telefono, String direccion) {
		super();
		this.ciudad = ciudad;
		this.nombre = nombre;
		this.telefono = telefono;
		this.direccion = direccion;
	}

	public Ciudad getCiudad() {
		return ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

}
