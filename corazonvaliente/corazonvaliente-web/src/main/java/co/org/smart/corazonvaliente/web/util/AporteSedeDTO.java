package co.org.smart.corazonvaliente.web.util;

import co.org.smart.corazonvaliente.entidades.AporteUsuario;
import co.org.smart.corazonvaliente.entidades.Sede;

public class AporteSedeDTO {

	private AporteUsuario aporte;
	private Sede sede;

	public AporteSedeDTO() {
	}

	public AporteSedeDTO(AporteUsuario aporte, Sede sede) {
		super();
		this.aporte = aporte;
		this.sede = sede;
	}

	public AporteUsuario getAporte() {
		return aporte;
	}

	public void setAporte(AporteUsuario aporte) {
		this.aporte = aporte;
	}

	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}

}
