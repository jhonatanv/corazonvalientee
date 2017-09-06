package co.org.smart.corazonvaliente.web.util;

import co.org.smart.corazonvaliente.entidades.Sede;

public class UsuarioBuscarDTO {

	private String cedula;
	private Sede sede;

	public UsuarioBuscarDTO() {
	}

	public UsuarioBuscarDTO(String cedula, Sede sede) {
		super();
		this.cedula = cedula;
		this.sede = sede;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}

}
