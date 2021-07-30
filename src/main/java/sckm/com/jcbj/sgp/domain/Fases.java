/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sckm.com.jcbj.sgp.domain;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Juan
 */
@Entity
@Table(name = "fases")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fases.findAll", query = "SELECT f FROM Fases f"),
    @NamedQuery(name = "Fases.findFasesByIdProyecto", query = "SELECT f FROM Fases f WHERE f.faseProyectoId = :faseProyectoId"),
    @NamedQuery(name = "Fases.findByFaseId", query = "SELECT f FROM Fases f WHERE f.faseId = :faseId"),
    @NamedQuery(name = "Fases.findByFaseNombre", query = "SELECT f FROM Fases f WHERE f.faseNombre = :faseNombre"),
    @NamedQuery(name = "Fases.findByFaseFechaInicio", query = "SELECT f FROM Fases f WHERE f.faseFechaInicio = :faseFechaInicio"),
    @NamedQuery(name = "Fases.findByFaseFechaFin", query = "SELECT f FROM Fases f WHERE f.faseFechaFin = :faseFechaFin"),
    @NamedQuery(name = "Fases.findByFaseEstado", query = "SELECT f FROM Fases f WHERE f.faseEstado = :faseEstado"),
    @NamedQuery(name = "Fases.findByFaseDescripcion", query = "SELECT f FROM Fases f WHERE f.faseDescripcion = :faseDescripcion")})
public class Fases implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "fase_id")
    private Integer faseId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "fase_nombre")
    private String faseNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fase_fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date faseFechaInicio;
    @Basic(optional = false)
    @NotNull
    @Column(name = "fase_fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date faseFechaFin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "fase_estado")
    private String faseEstado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "fase_descripcion")
    private String faseDescripcion;
    @JoinColumn(name = "fase_proyecto_id", referencedColumnName = "proyecto_id")
    @OneToOne(optional = false)
    private Proyectos faseProyectoId;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tareaFaseId")
    private Collection<Tareas> tareasCollection;

    public Fases() {
    }

    public Fases(Integer faseId) {
        this.faseId = faseId;
    }

    public Fases(Integer faseId, String faseNombre, Date faseFechaInicio, Date faseFechaFin, String faseEstado, String faseDescripcion) {
        this.faseId = faseId;
        this.faseNombre = faseNombre;
        this.faseFechaInicio = faseFechaInicio;
        this.faseFechaFin = faseFechaFin;
        this.faseEstado = faseEstado;
        this.faseDescripcion = faseDescripcion;
    }

    public Integer getFaseId() {
        return faseId;
    }

    public void setFaseId(Integer faseId) {
        this.faseId = faseId;
    }

    public String getFaseNombre() {
        return faseNombre;
    }

    public void setFaseNombre(String faseNombre) {
        this.faseNombre = faseNombre;
    }

    public Date getFaseFechaInicio() {
        return faseFechaInicio;
    }

    public void setFaseFechaInicio(Date faseFechaInicio) {
        this.faseFechaInicio = faseFechaInicio;
    }

    public Date getFaseFechaFin() {
        return faseFechaFin;
    }

    public void setFaseFechaFin(Date faseFechaFin) {
        this.faseFechaFin = faseFechaFin;
    }

    public String getFaseEstado() {
        return faseEstado;
    }

    public void setFaseEstado(String faseEstado) {
        this.faseEstado = faseEstado;
    }

    public String getFaseDescripcion() {
        return faseDescripcion;
    }

    public void setFaseDescripcion(String faseDescripcion) {
        this.faseDescripcion = faseDescripcion;
    }

    @XmlTransient
    public Collection<Tareas> getTareasCollection() {
        return tareasCollection;
    }

    public void setTareasCollection(Collection<Tareas> tareasCollection) {
        this.tareasCollection = tareasCollection;
    }

    public Proyectos getFaseProyectoId() {
        return faseProyectoId;
    }

    public void setFaseProyectoId(Proyectos faseProyectoId) {
        this.faseProyectoId = faseProyectoId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (faseId != null ? faseId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fases)) {
            return false;
        }
        Fases other = (Fases) object;
        if ((this.faseId == null && other.faseId != null) || (this.faseId != null && !this.faseId.equals(other.faseId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Fases{" + "faseId=" + faseId + ", faseNombre=" + faseNombre + ", faseFechaInicio=" + faseFechaInicio + ", faseFechaFin=" + faseFechaFin + ", faseEstado=" + faseEstado + ", faseDescripcion=" + faseDescripcion + ", faseProyectoId=" + faseProyectoId.getProyectoId() + '}';
    }

}
