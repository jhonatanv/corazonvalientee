package co.org.smart.corazonvaliente.web.util;

import co.org.smart.corazonvaliente.entidades.Seguimiento;

public class SeguimientoDTO {

	private Seguimiento seguimiento;
	private String user;

	public SeguimientoDTO() {
		// TODO Auto-generated constructor stub
	}

	public Seguimiento getSeguimiento() {
		return seguimiento;
	}

	public void setSeguimiento(Seguimiento seguimiento) {
		this.seguimiento = seguimiento;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

}
