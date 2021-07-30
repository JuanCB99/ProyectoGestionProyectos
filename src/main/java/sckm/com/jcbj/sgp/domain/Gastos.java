/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sckm.com.jcbj.sgp.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan
 */
@Entity
@Table(name = "gastos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gastos.findAll", query = "SELECT g FROM Gastos g"),
    @NamedQuery(name = "Gastos.findByGastoId", query = "SELECT g FROM Gastos g WHERE g.gastoId = :gastoId"),
    @NamedQuery(name = "Gastos.findByGastoConcepto", query = "SELECT g FROM Gastos g WHERE g.gastoConcepto = :gastoConcepto"),
    @NamedQuery(name = "Gastos.findByGastoFecha", query = "SELECT g FROM Gastos g WHERE g.gastoFecha = :gastoFecha"),
    @NamedQuery(name = "Gastos.findByGastoCantidadProd", query = "SELECT g FROM Gastos g WHERE g.gastoCantidadProd = :gastoCantidadProd"),
    @NamedQuery(name = "Gastos.findByGastoPrecioProd", query = "SELECT g FROM Gastos g WHERE g.gastoPrecioProd = :gastoPrecioProd"),
    @NamedQuery(name = "Gastos.findByGastoPrecioTotal", query = "SELECT g FROM Gastos g WHERE g.gastoPrecioTotal = :gastoPrecioTotal")})
public class Gastos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "gasto_id")
    private Integer gastoId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "gasto_concepto")
    private String gastoConcepto;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gasto_fecha")
    @Temporal(TemporalType.DATE)
    private Date gastoFecha;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gasto_cantidad_prod")
    private int gastoCantidadProd;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gasto_precio_prod")
    private double gastoPrecioProd;
    @Basic(optional = false)
    @NotNull
    @Column(name = "gasto_precio_total")
    private double gastoPrecioTotal;
    @JoinColumn(name = "gasto_tarea_id", referencedColumnName = "tarea_id")
    @ManyToOne(optional = false)
    private Tareas gastoTareaId;

    public Gastos() {
    }

    public Gastos(Integer gastoId) {
        this.gastoId = gastoId;
    }

    public Gastos(Integer gastoId, String gastoConcepto, Date gastoFecha, int gastoCantidadProd, double gastoPrecioProd, double gastoPrecioTotal) {
        this.gastoId = gastoId;
        this.gastoConcepto = gastoConcepto;
        this.gastoFecha = gastoFecha;
        this.gastoCantidadProd = gastoCantidadProd;
        this.gastoPrecioProd = gastoPrecioProd;
        this.gastoPrecioTotal = gastoPrecioTotal;
    }

    public Integer getGastoId() {
        return gastoId;
    }

    public void setGastoId(Integer gastoId) {
        this.gastoId = gastoId;
    }

    public String getGastoConcepto() {
        return gastoConcepto;
    }

    public void setGastoConcepto(String gastoConcepto) {
        this.gastoConcepto = gastoConcepto;
    }

    public Date getGastoFecha() {
        return gastoFecha;
    }

    public void setGastoFecha(Date gastoFecha) {
        this.gastoFecha = gastoFecha;
    }

    public int getGastoCantidadProd() {
        return gastoCantidadProd;
    }

    public void setGastoCantidadProd(int gastoCantidadProd) {
        this.gastoCantidadProd = gastoCantidadProd;
    }

    public double getGastoPrecioProd() {
        return gastoPrecioProd;
    }

    public void setGastoPrecioProd(double gastoPrecioProd) {
        this.gastoPrecioProd = gastoPrecioProd;
    }

    public double getGastoPrecioTotal() {
        return gastoPrecioTotal;
    }

    public void setGastoPrecioTotal(double gastoPrecioTotal) {
        this.gastoPrecioTotal = gastoPrecioTotal;
    }

    public Tareas getGastoTareaId() {
        return gastoTareaId;
    }

    public void setGastoTareaId(Tareas gastoTareaId) {
        this.gastoTareaId = gastoTareaId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gastoId != null ? gastoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gastos)) {
            return false;
        }
        Gastos other = (Gastos) object;
        if ((this.gastoId == null && other.gastoId != null) || (this.gastoId != null && !this.gastoId.equals(other.gastoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "sckm.com.jcbj.sgp.domain.Gastos[ gastoId=" + gastoId + " ]";
    }
    
}
