package co.org.smart.corazonvaliente.dto;

import co.org.smart.corazonvaliente.entidades.Sede;
import co.org.smart.corazonvaliente.entidades.Usuario;

public class TrasladoUsuarioDTO {

	private Usuario usuario;
	private Sede sede;

	public TrasladoUsuarioDTO() {
	}

	public TrasladoUsuarioDTO(Usuario usuario, Sede sede) {
		super();
		this.usuario = usuario;
		this.sede = sede;
	}

	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
