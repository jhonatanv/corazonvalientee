package  co.org.smart.corazonvaliente.entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;


@Entity
@NamedQueries({ @NamedQuery(name = Acceso.LISTAR_ACCSESOS_POR_ROL, query = "SELECT acc FROM AccesoRol acrol JOIN acrol.rol ro JOIN acrol.acceso acc WHERE acrol.rol = ?1") })
public class Acceso implements Serializable {
	public static final String LISTAR_ACCSESOS_POR_ROL = "Acceso.listar";
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String url;

	private String nombre;

	/**
	 * constructor.
	 */
	public Acceso() {
	}
	
	

	public Acceso( String url, String nombre) {
		super();
		this.url = url;
		this.nombre = nombre;
	}



	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the url
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * @param url
	 *            the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre
	 *            the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
