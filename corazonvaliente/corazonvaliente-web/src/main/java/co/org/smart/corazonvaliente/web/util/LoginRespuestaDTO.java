package co.org.smart.corazonvaliente.web.util;

import co.org.smart.corazonvaliente.entidades.Sede;

public class LoginRespuestaDTO {

	private String user;
	private String token;
	private Sede sede;

	public LoginRespuestaDTO() {
	}

	public LoginRespuestaDTO(String user, String token, Sede sede) {
		super();
		this.user = user;
		this.token = token;
		this.sede = sede;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}

}
