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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
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
@Table(name = "tareas")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tareas.findAll", query = "SELECT t FROM Tareas t"),
    @NamedQuery(name = "Tareas.findByTareaId", query = "SELECT t FROM Tareas t WHERE t.tareaId = :tareaId"),
    @NamedQuery(name = "Tareas.findByTareaNombre", query = "SELECT t FROM Tareas t WHERE t.tareaNombre = :tareaNombre"),
    @NamedQuery(name = "Tareas.findByTareaFechaInicio", query = "SELECT t FROM Tareas t WHERE t.tareaFechaInicio = :tareaFechaInicio"),
    @NamedQuery(name = "Tareas.findByTareaFechaFin", query = "SELECT t FROM Tareas t WHERE t.tareaFechaFin = :tareaFechaFin"),
    @NamedQuery(name = "Tareas.findByTareaEstado", query = "SELECT t FROM Tareas t WHERE t.tareaEstado = :tareaEstado"),
    @NamedQuery(name = "Tareas.findByTareaDescripcion", query = "SELECT t FROM Tareas t WHERE t.tareaDescripcion = :tareaDescripcion")})
public class Tareas implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tarea_id")
    private Integer tareaId;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tarea_nombre")
    @Temporal(TemporalType.DATE)
    private Date tareaNombre;
    @Basic(optional = false)
    @NotNull
    @Column(name = "tarea_fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date tareaFechaInicio;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tarea_fecha_fin")
    private String tareaFechaFin;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "tarea_estado")
    private String tareaEstado;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "tarea_descripcion")
    private String tareaDescripcion;
    @JoinColumn(name = "tarea_fase_id", referencedColumnName = "fase_id")
    @ManyToOne(optional = false)
    private Fases tareaFaseId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "gastoTareaId")
    private Collection<Gastos> gastosCollection;

    public Tareas() {
    }

    public Tareas(Integer tareaId) {
        this.tareaId = tareaId;
    }

    public Tareas(Integer tareaId, Date tareaNombre, Date tareaFechaInicio, String tareaFechaFin, String tareaEstado, String tareaDescripcion) {
        this.tareaId = tareaId;
        this.tareaNombre = tareaNombre;
        this.tareaFechaInicio = tareaFechaInicio;
        this.tareaFechaFin = tareaFechaFin;
        this.tareaEstado = tareaEstado;
        this.tareaDescripcion = tareaDescripcion;
    }

    public Integer getTareaId() {
        return tareaId;
    }

    public void setTareaId(Integer tareaId) {
        this.tareaId = tareaId;
    }

    public Date getTareaNombre() {
        return tareaNombre;
    }

    public void setTareaNombre(Date tareaNombre) {
        this.tareaNombre = tareaNombre;
    }

    public Date getTareaFechaInicio() {
        return tareaFechaInicio;
    }

    public void setTareaFechaInicio(Date tareaFechaInicio) {
        this.tareaFechaInicio = tareaFechaInicio;
    }

    public String getTareaFechaFin() {
        return tareaFechaFin;
    }

    public void setTareaFechaFin(String tareaFechaFin) {
        this.tareaFechaFin = tareaFechaFin;
    }

    public String getTareaEstado() {
        return tareaEstado;
    }

    public void setTareaEstado(String tareaEstado) {
        this.tareaEstado = tareaEstado;
    }

    public String getTareaDescripcion() {
        return tareaDescripcion;
    }

    public void setTareaDescripcion(String tareaDescripcion) {
        this.tareaDescripcion = tareaDescripcion;
    }

    public Fases getTareaFaseId() {
        return tareaFaseId;
    }

    public void setTareaFaseId(Fases tareaFaseId) {
        this.tareaFaseId = tareaFaseId;
    }

    @XmlTransient
    public Collection<Gastos> getGastosCollection() {
        return gastosCollection;
    }

    public void setGastosCollection(Collection<Gastos> gastosCollection) {
        this.gastosCollection = gastosCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tareaId != null ? tareaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tareas)) {
            return false;
        }
        Tareas other = (Tareas) object;
        if ((this.tareaId == null && other.tareaId != null) || (this.tareaId != null && !this.tareaId.equals(other.tareaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sckm.com.jcbj.sgp.domain.Tareas[ tareaId=" + tareaId + " ]";
    }
    
}
