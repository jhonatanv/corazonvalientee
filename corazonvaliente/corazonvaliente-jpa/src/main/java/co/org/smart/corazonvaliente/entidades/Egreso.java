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
@Table(name = "EGRESOS")
@NamedNativeQueries({ @NamedNativeQuery(name = Egreso.LISTAR_EGRESOS, query = "SELECT * FROM egresos e where e.sede_egreso =?1 and TO_CHAR(e.fecha_egreso,'YYYY-MM')=?2", resultClass=Egreso.class) })
@NamedQueries({ @NamedQuery(name = Egreso.LISTAR_TODOS_EGRESOS, query = "SELECT e FROM Egreso e where e.sede.nombre=?1") })
public class Egreso implements Serializable {
	
    

	
 	public static final String LISTAR_EGRESOS = "SELECT * FROM egresos"; 
 	public static final String LISTAR_TODOS_EGRESOS = "Egreso.egresos"; 



	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_egreso")
	private int codigo;

	@ManyToOne
	@JoinColumn(name = "funcionario_egreso")
	private Funcionario funcionario;
	
	@ManyToOne
	@JoinColumn(name = "sede_egreso")
	private Sede sede;

	@Column(name = "numeroFac_egreso")
	private String numeroFactura;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_egreso")
	private Date fechaEgreso;

	@Column(name = "cuenta_egreso")
	private String cuenta;

	@Column(name = "nit_egreso")
	private String nit;

	@Column(name = "concepto_egreso")
	private String concepto;

	@Column(name = "valor_egreso")
	private double valor;

	public Egreso() {
	}

	

	

	public Egreso(Funcionario funcionario, Sede sede, String numeroFactura, Date fechaEgreso, String cuenta,
			String nit, String concepto, double valor) {
		super();
		this.funcionario = funcionario;
		this.sede = sede;
		this.numeroFactura = numeroFactura;
		this.fechaEgreso = fechaEgreso;
		this.cuenta = cuenta;
		this.nit = nit;
		this.concepto = concepto;
		this.valor = valor;
	}





	public Egreso(int codigo, Funcionario funcionario, Sede sede, String numeroFactura, Date fechaEgreso, String cuenta,
			String nit, String concepto, double valor) {
		super();
		this.codigo = codigo;
		this.funcionario = funcionario;
		this.sede = sede;
		this.numeroFactura = numeroFactura;
		this.fechaEgreso = fechaEgreso;
		this.cuenta = cuenta;
		this.nit = nit;
		this.concepto = concepto;
		this.valor = valor;
	}





	public Funcionario getFuncionario() {
		return funcionario;
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

	public String getNumeroFactura() {
		return numeroFactura;
	}

	public void setNumeroFactura(String numeroFactura) {
		this.numeroFactura = numeroFactura;
	}

	public Date getFechaEgreso() {
		return fechaEgreso;
	}

	public void setFechaEgreso(Date fechaEgreso) {
		this.fechaEgreso = fechaEgreso;
	}

	public String getCuenta() {
		return cuenta;
	}

	public void setCuenta(String cuenta) {
		this.cuenta = cuenta;
	}

	public String getNit() {
		return nit;
	}

	public void setNit(String nit) {
		this.nit = nit;
	}

	public String getConcepto() {
		return concepto;
	}

	public void setConcepto(String concepto) {
		this.concepto = concepto;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

}
