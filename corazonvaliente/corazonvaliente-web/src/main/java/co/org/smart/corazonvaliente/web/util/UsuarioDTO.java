package co.org.smart.corazonvaliente.web.util;

import co.org.smart.corazonvaliente.entidades.Sede;
import co.org.smart.corazonvaliente.entidades.Usuario;

public class UsuarioDTO {

	private Usuario usuario;
	private String user;
	private Sede sede;

	public UsuarioDTO() {
	}

	public UsuarioDTO(Usuario usuario, String user, Sede sede) {
		super();
		this.usuario = usuario;
		this.user = user;
		this.sede = sede;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}

}
