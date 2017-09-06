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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "SEGUIMIENTOS")
@NamedQueries({
		@NamedQuery(name = Seguimiento.LISTAR_SEGUIMIENTOS, query = "select s from Seguimiento s where s.usuario.cedula=?1") })
public class Seguimiento implements Serializable {

	public static final String LISTAR_SEGUIMIENTOS = "listar.seguimientos";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_seguimiento")
	private int codigo;

	@ManyToOne
	@JoinColumn(name = "funcionario_seguimiento")
	private Funcionario funcionario;

	@ManyToOne
	@JoinColumn(name = "sede_seguimiento")
	private Sede sede;

	@ManyToOne
	@JoinColumn(name = "usuario_seguimiento")
	private Usuario usuario;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_seguimiento")
	private Date fecha;

	@Column(name = "observacion_seguimiento")
	private String observacion;

	public Seguimiento() {
	}

	public Seguimiento(Funcionario funcionario, Sede sede, Usuario usuario, Date fecha, String observacion) {
		super();
		this.funcionario = funcionario;
		this.sede = sede;
		this.usuario = usuario;
		this.fecha = fecha;
		this.observacion = observacion;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}

}
