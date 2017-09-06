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
@Table(name = "APORTES_USUARIOS")
@NamedQueries({
		@NamedQuery(name = AporteUsuario.LISTAR_APORTES, query = "select a from AporteUsuario a where a.usuario.cedula=?1 and  a.sede.nombre=?2") })
public class AporteUsuario implements Serializable {

	public static final String LISTAR_APORTES = "listarAportes.usuario";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_aporteUsu")
	private int codigo;

	@ManyToOne
	@JoinColumn(name = "sede_aporteUsu")
	private Sede sede;

	@ManyToOne
	@JoinColumn(name = "usuario_aporteUsu")
	private Usuario usuario;

	@Temporal(TemporalType.DATE)
	@Column(name = "fecha_aporteUsu")
	private Date fechaAporte;

	@Column(name = "descripcion_aporteUsu")
	private String descripcion;

	@Column(name = "valor_aporteUsu")
	private double valorCancelado;

	public AporteUsuario() {
	}

	public AporteUsuario(Sede sede, Usuario usuario, Date fechaAporte, String descripcion, double valorCancelado) {
		super();
		this.sede = sede;
		this.usuario = usuario;
		this.fechaAporte = fechaAporte;
		this.descripcion = descripcion;
		this.valorCancelado = valorCancelado;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getFechaAporte() {
		return fechaAporte;
	}

	public void setFechaAporte(Date fechaAporte) {
		this.fechaAporte = fechaAporte;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public double getValorCancelado() {
		return valorCancelado;
	}

	public void setValorCancelado(double valorCancelado) {
		this.valorCancelado = valorCancelado;
	}

}
