package co.org.smart.corazonvaliente.web.util;

import co.org.smart.corazonvaliente.entidades.Ficha;
import co.org.smart.corazonvaliente.entidades.Sede;

public class FichaSedeDTO {

	private Ficha ficha;
	private Sede sede;

	public FichaSedeDTO() {
	}

	public FichaSedeDTO(Ficha ficha, Sede sede) {
		super();
		this.ficha = ficha;
		this.sede = sede;
	}

	public Ficha getFicha() {
		return ficha;
	}

	public void setFicha(Ficha ficha) {
		this.ficha = ficha;
	}

	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}

}
