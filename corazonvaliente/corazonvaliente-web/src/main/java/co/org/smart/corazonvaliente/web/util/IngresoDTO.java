package co.org.smart.corazonvaliente.web.util;

import co.org.smart.corazonvaliente.entidades.Ingreso;
import co.org.smart.corazonvaliente.entidades.Sede;

public class IngresoDTO {

	private Ingreso ingreso;
	private Sede sede;
	private String funcionario;


	public IngresoDTO() {
	}


	public IngresoDTO(Ingreso ingreso, Sede sede, String funcionario) {
		super();
		this.ingreso = ingreso;
		this.sede = sede;
		this.funcionario = funcionario;
	}


	public Ingreso getIngreso() {
		return ingreso;
	}


	public void setIngreso(Ingreso ingreso) {
		this.ingreso = ingreso;
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
