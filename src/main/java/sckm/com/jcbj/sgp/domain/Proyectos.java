/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sckm.com.jcbj.sgp.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan
 */
@Entity
@Table(name = "proyectos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Proyectos.findAll", query = "SELECT p FROM Proyectos p"),
    @NamedQuery(name = "Proyectos.findByProyectoUsuId", query = "SELECT p FROM Proyectos p WHERE p.proyectoUsuId = :proyectoUsuId"),
    @NamedQuery(name = "Proyectos.findByProyectoDescripcion", query = "SELECT p FROM Proyectos p WHERE p.proyectoDescripcion = :proyectoDescripcion"),
    @NamedQuery(name = "Proyectos.findByProyectoNombre", query = "SELECT p FROM Proyectos p WHERE p.proyectoNombre = :proyectoNombre"),
    @NamedQuery(name = "Proyectos.findByProyectoFechaInicio", query = "SELECT p FROM Proyectos p WHERE p.proyectoFechaInicio = :proyectoFechaInicio"),
    @NamedQuery(name = "Proyectos.findByProyectoFechaFin", query = "SELECT p FROM Proyectos p WHERE p.proyectoFechaFin = :proyectoFechaFin"),
    @NamedQuery(name = "Proyectos.findByProyectoEstado", query = "SELECT p FROM Proyectos p WHERE p.proyectoEstado = :proyectoEstado"),
    @NamedQuery(name = "Proyectos.findByProyectoDescripcion", query = "SELECT p FROM Proyectos p WHERE p.proyectoDescripcion = :proyectoDescripcion"),
    @NamedQuery(name = "Proyectos.findByProyectoPresupuesto", query = "SELECT p FROM Proyectos p WHERE p.proyectoPresupuesto = :proyectoPresupuesto")})
public class Proyectos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "proyecto_id")
    private Integer proyectoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "proyecto_nombre")
    private String proyectoNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "proyecto_fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date proyectoFechaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "proyecto_fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date proyectoFechaFin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "proyecto_estado")
    private String proyectoEstado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "proyecto_descripcion")
    private String proyectoDescripcion;
    @Basic(optional = false)
    @NotNull
    @Column(name = "proyecto_presupuesto")
    private double proyectoPresupuesto;
    @JoinColumn(name = "proyecto_usu_id", referencedColumnName = "usu_id")
    @OneToOne(optional = false)
    private Usuarios proyectoUsuId;
    
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "faseProyectoId")
    private Fases fases;

    public Proyectos() {
    }

    public Proyectos(Integer proyectoId) {
        this.proyectoId = proyectoId;
    }

    public Proyectos(Integer proyectoId, String proyectoNombre, Date proyectoFechaInicio, Date proyectoFechaFin, String proyectoEstado, String proyectoDescripcion, double proyectoPresupuesto) {
        this.proyectoId = proyectoId;
        this.proyectoNombre = proyectoNombre;
        this.proyectoFechaInicio = proyectoFechaInicio;
        this.proyectoFechaFin = proyectoFechaFin;
        this.proyectoEstado = proyectoEstado;
        this.proyectoDescripcion = proyectoDescripcion;
        this.proyectoPresupuesto = proyectoPresupuesto;
    }

    public Integer getProyectoId() {
        return proyectoId;
    }

    public void setProyectoId(Integer proyectoId) {
        this.proyectoId = proyectoId;
    }

    public String getProyectoNombre() {
        return proyectoNombre;
    }

    public void setProyectoNombre(String proyectoNombre) {
        this.proyectoNombre = proyectoNombre;
    }

    public Date getProyectoFechaInicio() {
        return proyectoFechaInicio;
    }

    public void setProyectoFechaInicio(Date proyectoFechaInicio) {
        this.proyectoFechaInicio = proyectoFechaInicio;
    }

    public Date getProyectoFechaFin() {
        return proyectoFechaFin;
    }

    public void setProyectoFechaFin(Date proyectoFechaFin) {
        this.proyectoFechaFin = proyectoFechaFin;
    }

    public String getProyectoEstado() {
        return proyectoEstado;
    }

    public void setProyectoEstado(String proyectoEstado) {
        this.proyectoEstado = proyectoEstado;
    }

    public String getProyectoDescripcion() {
        return proyectoDescripcion;
    }

    public void setProyectoDescripcion(String proyectoDescripcion) {
        this.proyectoDescripcion = proyectoDescripcion;
    }

    public double getProyectoPresupuesto() {
        return proyectoPresupuesto;
    }

    public void setProyectoPresupuesto(double proyectoPresupuesto) {
        this.proyectoPresupuesto = proyectoPresupuesto;
    }

    public Usuarios getProyectoUsuId() {
        return proyectoUsuId;
    }

    public void setProyectoUsuId(Usuarios proyectoUsuId) {
        this.proyectoUsuId = proyectoUsuId;
    }

    public Fases getFases() {
        return fases;
    }

    public void setFases(Fases fases) {
        this.fases = fases;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (proyectoId != null ? proyectoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Proyectos)) {
            return false;
        }
        Proyectos other = (Proyectos) object;
        if ((this.proyectoId == null && other.proyectoId != null) || (this.proyectoId != null && !this.proyectoId.equals(other.proyectoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Proyectos{" + "proyectoId=" + proyectoId + ", proyectoNombre=" + proyectoNombre + ", proyectoFechaInicio=" + proyectoFechaInicio + ", proyectoFechaFin=" + proyectoFechaFin + ", proyectoEstado=" + proyectoEstado + ", proyectoDescripcion=" + proyectoDescripcion + ", proyectoPresupuesto=" + proyectoPresupuesto + ", proyectoUsuId=" + proyectoUsuId + ", fases=" + fases + '}';
    }
    
}
