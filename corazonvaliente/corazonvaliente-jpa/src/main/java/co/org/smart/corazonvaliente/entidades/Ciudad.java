package co.org.smart.corazonvaliente.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "CIUDADES")
@NamedQueries({ @NamedQuery(name = Ciudad.LISTAR_CIUDADES, query = "select c from Ciudad c") })
public class Ciudad implements Serializable {
 
	public static final String LISTAR_CIUDADES = "Ciudad.listar"; 

	@Id
	@Column(name = "codigo_ciudad")
	private int codigo;

	@ManyToOne
	@JoinColumn(name = "departamento_ciudad")
	private Departamento departamento;

	@Column(name = "nombre_ciudad")
	private String nombre;

	public Ciudad() {
	}

	public Ciudad(int codigo, Departamento departamento, String nombre) {
		super();
		this.codigo = codigo;
		this.departamento = departamento;
		this.nombre = nombre;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
