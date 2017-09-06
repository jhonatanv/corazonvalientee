package co.org.smart.corazonvaliente.web.util;

import co.org.smart.corazonvaliente.entidades.Egreso;
import co.org.smart.corazonvaliente.entidades.Sede;

public class EgresoDTO {

	private Egreso egreso;
	private Sede sede;
	private String funcionario;


	public EgresoDTO() {
	}


	public EgresoDTO(Egreso egreso, Sede sede, String funcionario) {
		super();
		this.egreso = egreso;
		this.sede = sede;
		this.funcionario = funcionario;
	}


	public Egreso getEgreso() {
		return egreso;
	}


	public void setEgreso(Egreso egreso) {
		this.egreso = egreso;
	}


	public Sede getSede() {
		return sede;
	}


	public void setSede(Sede sede) {
		this.sede = sede;
	}


	public String getFuncionario() {
		return funcionario;
	}


	public void setFuncionario(String funcionario) {
		this.funcionario = funcionario;
	}

	

}
