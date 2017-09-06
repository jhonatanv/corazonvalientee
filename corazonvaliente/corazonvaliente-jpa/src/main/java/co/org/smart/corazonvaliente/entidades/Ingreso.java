package co.org.smart.corazonvaliente.entidades;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "INGRESOS")
@NamedNativeQueries({
		@NamedNativeQuery(name = Ingreso.LISTAR_INGRESOS, query = "SELECT * FROM ingresos e where e.sede_ingreso =?1 and TO_CHAR(e.fecha_ingreso,'YYYY-MM')=?2", resultClass = Ingreso.class) })
@NamedQueries({ @NamedQuery(name = Ingreso.LISTAR_TODOS_INGRESOS, query = "SELECT e FROM Ingreso e where e.sede.nombre=?1") })
public class Ingreso implements Serializable {

	public static final String LISTAR_INGRESOS = "Ingreso.listarI";
 	public static final String LISTAR_TODOS_INGRESOS = "Ingreso.ingresos"; 


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_ingreso")
	private int codigo;

	@ManyToOne
	@JoinColumn(name = "funcionario_ingreso")
	private Funcionario funcionario;

	@ManyToOne
	@JoinColumn(name = "sede_ingreso")
	private Sede sede;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_ingreso")
	private Date fechaIngreso;

	@Column(name = "valor_ingreso")
	private double valor;

	@Column(name = "descripcion_ingreso")
	private String descripcion;

	public Ingreso() {
	}

	public Ingreso(Funcionario funcionario, Sede sede, Date fechaIngreso, double valor, String descripcion) {
		super();
		this.funcionario = funcionario;
		this.sede = sede;
		this.fechaIngreso = fechaIngreso;
		this.valor = valor;
		this.descripcion = descripcion;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public Ingreso(int codigo, Funcionario funcionario, Sede sede, Date fechaIngreso, double valor,
			String descripcion) {
		super();
		this.codigo = codigo;
		this.funcionario = funcionario;
		this.sede = sede;
		this.fechaIngreso = fechaIngreso;
		this.valor = valor;
		this.descripcion = descripcion;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Date getFechaIngreso() {
		return fechaIngreso;
	}

	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

}
