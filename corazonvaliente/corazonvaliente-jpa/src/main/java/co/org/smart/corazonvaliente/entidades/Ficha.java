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
@Table(name = "FICHAS")
@NamedQueries({ @NamedQuery(name = Ficha.LISTAR_FICHAS, query = "select f from Ficha f where f.usuario.cedula=?1"),
		@NamedQuery(name = Ficha.LISTAR_ACTIVOS, query = "select f from Ficha f where f.usuario.cedula=?1 and f.estadoUsuario=true"),
		@NamedQuery(name = Ficha.LISTAR_USUARIOS, query = "select f from Ficha f where f.usuario.cedula=?1"),
		@NamedQuery(name = Ficha.LISTAR_USUARIOS_SEDE, query = "select f.usuario from Ficha f where f.sede=?1 and f.estadoUsuario=true") })
public class Ficha implements Serializable {

	public static final String LISTAR_FICHAS = "Ficha.listarCedula";
	public static final String LISTAR_ACTIVOS = "Ficha.listarActivos";
	public static final String LISTAR_USUARIOS = "Ficha.listarUsuarios";
	public static final String LISTAR_USUARIOS_SEDE = "Ficha.listarUsuariosSede";

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "codigo_ficha")
	private int codigo;

	@ManyToOne
	@JoinColumn(name = "usuario_ficha")
	private Usuario usuario;

	@ManyToOne
	@JoinColumn(name = "sede_ficha")
	private Sede sede;

	@Temporal(TemporalType.DATE)
	@Column(name = "entrada_ficha")
	private Date fechaEntrada;

	@Temporal(TemporalType.DATE)
	@Column(name = "salida_ficha")
	private Date fechaSalida;

	@Column(name = "observaciones_ficha")
	private String observacion;

	@Column(name = "estado_ficha")
	private boolean estadoUsuario;

	public Ficha() {
	}

	public Ficha(Usuario usuario, Date fechaEntrada, Date fechaSalida, String observacion, boolean estadoUsuario,
			Sede sede) {
		super();
		this.usuario = usuario;
		this.fechaEntrada = fechaEntrada;
		this.fechaSalida = fechaSalida;
		this.observacion = observacion;
		this.estadoUsuario = estadoUsuario;
		this.sede = sede;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getFechaEntrada() {
		return fechaEntrada;
	}

	public void setFechaEntrada(Date fechaEntrada) {
		this.fechaEntrada = fechaEntrada;
	}

	public Date getFechaSalida() {
		return fechaSalida;
	}

	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public boolean isEstadoUsuario() {
		return estadoUsuario;
	}

	public void setEstadoUsuario(boolean estadoUsuario) {
		this.estadoUsuario = estadoUsuario;
	}

	public Sede getSede() {
		return sede;
	}

	public void setSede(Sede sede) {
		this.sede = sede;
	}
}
