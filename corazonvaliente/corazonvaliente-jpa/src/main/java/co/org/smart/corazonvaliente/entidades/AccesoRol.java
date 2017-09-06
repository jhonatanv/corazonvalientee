package  co.org.smart.corazonvaliente.entidades;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;


@Entity
@IdClass(value = AccesoRolPK.class)
public class AccesoRol implements Serializable {

	@Id
	@ManyToOne
	private Cargo rol;
	@Id
	@ManyToOne
	private Acceso acceso;

	/**
	 * constructor
	 */
	public AccesoRol() {
	}

	/**
	 * constructor.
	 * 
	 * @param rol
	 * @param acceso
	 */
	public AccesoRol(Cargo rol, Acceso acceso) {
		super();
		this.rol = rol;
		this.acceso = acceso;
	}

	/**
	 * @return the rol
	 */
	public Cargo getRol() {
		return rol;
	}

	/**
	 * @param rol
	 *            the rol to set
	 */
	public void setRol(Cargo rol) {
		this.rol = rol;
	}

	/**
	 * @return the acceso
	 */
	public Acceso getAcceso() {
		return acceso;
	}

	/**
	 * @param acceso
	 *            the acceso to set
	 */
	public void setAcceso(Acceso acceso) {
		this.acceso = acceso;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((acceso == null) ? 0 : acceso.hashCode());
		result = prime * result + ((rol == null) ? 0 : rol.hashCode());
		return result;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccesoRol other = (AccesoRol) obj;
		if (acceso == null) {
			if (other.acceso != null)
				return false;
		} else if (!acceso.equals(other.acceso))
			return false;
		if (rol == null) {
			if (other.rol != null)
				return false;
		} else if (!rol.equals(other.rol))
			return false;
		return true;
	}

}
