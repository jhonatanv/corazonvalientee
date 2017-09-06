package co.org.smart.corazonvaliente.web.util;

import java.io.Serializable;

import co.org.smart.corazonvaliente.entidades.Sede;

public class SedeUserDTO implements Serializable {

	private Sede sede;
	private String user;

	public SedeUserDTO() {

	}

	public SedeUserDTO(Sede sede, String user) {
		super();
		this.sede = sede;
		this.user = user;
	}

	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}
