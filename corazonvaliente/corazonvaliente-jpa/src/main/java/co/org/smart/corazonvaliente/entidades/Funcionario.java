package co.org.smart.corazonvaliente.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedNativeQueries;
import javax.persistence.NamedNativeQuery;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "FUNCIONARIOS")
@NamedQueries({@NamedQuery(name = Funcionario.BUSCAR_FUNCIONARIO_USER_PASS, query = "select a from Funcionario a where a.usuario =?1 and a.contrasenia = ?2"),
	@NamedQuery(name = Funcionario.BUSCAR_FUNCIONARIO_USER, query = "select a from Funcionario a where a.usuario =?1")
})
@NamedNativeQueries({ @NamedNativeQuery(name = Funcionario.BUSCAR_FUNCIONARIOS_EJECUTIVOS, query = "select * from funcionarios a where a.cargo_funcionario LIKE '%presidente' or a.cargo_funcionario LIKE '%nacional%'", resultClass=Funcionario.class
)})
public class Funcionario implements Serializable {
	
	
	
	public static final String BUSCAR_FUNCIONARIO_USER_PASS = "Funcionario.BuscarUsuarioFuncionario";
	public static final String BUSCAR_FUNCIONARIO_USER = "Funcionario.BuscarUsuario";
	public static final String BUSCAR_FUNCIONARIOS_EJECUTIVOS = "Funcionario.ListarEjecuutivos";




	@Id
	@Column(name = "cedula_funcionario")
	private String cedula;

	@ManyToOne
	@JoinColumn(name = "cargo_funcionario")
	private Cargo cargo;

	@Column(name = "nombre_funcionario")
	private String nombre;

	@Column(name = "telefono_funcionario")
	private String telefono;

	@Temporal(TemporalType.DATE)
	@Column(name = "ingreso_funcionario")
	private Date fechaIngreso;

	@Column(name = "contrato_funcionario")
	private String tipoContrato;

	@Column(name = "eps_funcionario")
	private String eps;

	@Column(name = "salario_funcionario")
	private double salario;

	@Column(name = "usuario_funcionario")
	private String usuario;

	@Column(name = "contrasenia_funcionario")
	private String contrasenia;
	
	@Column(name = "estado_funcionario")
	private boolean estado;

	public Funcionario() {
	}

	public Funcionario(String cedula, Cargo cargo, String nombre, String telefono, Date fechaIngreso, String tipoContrato,
			String eps, double salario, String usuario, String contrasenia) {
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
	}
	
	

	public Funcionario(String cedula, Cargo cargo, String nombre, String telefono, Date fechaIngreso,
			String tipoContrato, String eps, double salario, String usuario, String contrasenia, boolean estado) {
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
		this.estado = estado;
	}

	
	
	public boolean isEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
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
