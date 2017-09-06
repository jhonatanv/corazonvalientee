package co.org.smart.corazonvaliente.dto;

import java.util.Date;

import co.org.smart.corazonvaliente.entidades.Cargo;
import co.org.smart.corazonvaliente.entidades.Sede;

public class FuncionarioDTO {

	private String cedula;

	private Cargo cargo;

	private String nombre;

	private String telefono;

	private Date fechaIngreso;

	private String tipoContrato;

	private String eps;

	private double salario;

	private String usuario;

	private String contrasenia;

	private Sede sede;

	public FuncionarioDTO() {

	}

	public FuncionarioDTO(String cedula, Cargo cargo, String nombre, String telefono, Date fechaIngreso,
			String tipoContrato, String eps, double salario,String usuario, String contrasenia, Sede sede ) {
		super();
		this.cedula = cedula;
		this.cargo = cargo;
		this.nombre = nombre;
		this.telefono = telefono;
		this.fechaIngreso = fechaIngreso;
		this.tipoContrato = tipoContrato;
		this.eps = eps;
		this.salario = salario;
		this.usuario = usuario;
		this.contrasenia = contrasenia;
		this.sede = sede;
		
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public String getTipoContrato() {
		return tipoContrato;
	}

	public void setTipoContrato(String tipoContrato) {
		this.tipoContrato = tipoContrato;
	}

	public String getEps() {
		return eps;
	}

	public void setEps(String eps) {
		this.eps = eps;
	}

	public double getSalario() {
		return salario;
	}

	public void setSalario(double salario) {
		this.salario = salario;
	}

	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

}
