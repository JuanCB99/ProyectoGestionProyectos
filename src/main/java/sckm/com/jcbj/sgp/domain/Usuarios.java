/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sckm.com.jcbj.sgp.domain;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Juan
 */
@Entity
@Table(name = "usuarios")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Usuarios.findAll", query = "SELECT u FROM Usuarios u"),
    @NamedQuery(name = "Usuarios.findByUsuId", query = "SELECT u FROM Usuarios u WHERE u.usuId = :usuId"),
    @NamedQuery(name = "Usuarios.findByUsuPrimerNombre", query = "SELECT u FROM Usuarios u WHERE u.usuPrimerNombre = :usuPrimerNombre"),
    @NamedQuery(name = "Usuarios.findByUsuPrimerApellido", query = "SELECT u FROM Usuarios u WHERE u.usuPrimerApellido = :usuPrimerApellido"),
    @NamedQuery(name = "Usuarios.findByUsuCorreoAndPassword", query = "SELECT u FROM Usuarios u WHERE u.usuCorreo = :usuCorreo AND u.usuPassword = :usuPassword"),
    @NamedQuery(name = "Usuarios.findByUsuPassword", query = "SELECT u FROM Usuarios u WHERE u.usuPassword = :usuPassword")})
public class Usuarios implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "usu_id")
    private Integer usuId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "usu_primer_nombre")
    private String usuPrimerNombre;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "usu_primer_apellido")
    private String usuPrimerApellido;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "usu_correo")
    private String usuCorreo;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "usu_password")
    private String usuPassword;

    public Usuarios() {
    }

    public Usuarios(Integer usuId) {
        this.usuId = usuId;
    }

    public Usuarios(Integer usuId, String usuPrimerNombre, String usuPrimerApellido, String usuCorreo, String usuPassword) {
        this.usuId = usuId;
        this.usuPrimerNombre = usuPrimerNombre;
        this.usuPrimerApellido = usuPrimerApellido;
        this.usuCorreo = usuCorreo;
        this.usuPassword = usuPassword;
    }

    public Integer getUsuId() {
        return usuId;
    }

    public void setUsuId(Integer usuId) {
        this.usuId = usuId;
    }

    public String getUsuPrimerNombre() {
        return usuPrimerNombre;
    }

    public void setUsuPrimerNombre(String usuPrimerNombre) {
        this.usuPrimerNombre = usuPrimerNombre;
    }

    public String getUsuPrimerApellido() {
        return usuPrimerApellido;
    }

    public void setUsuPrimerApellido(String usuPrimerApellido) {
        this.usuPrimerApellido = usuPrimerApellido;
    }

    public String getUsuCorreo() {
        return usuCorreo;
    }

    public void setUsuCorreo(String usuCorreo) {
        this.usuCorreo = usuCorreo;
    }

    public String getUsuPassword() {
        return usuPassword;
    }

    public void setUsuPassword(String usuPassword) {
        this.usuPassword = usuPassword;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuId != null ? usuId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Usuarios)) {
            return false;
        }
        Usuarios other = (Usuarios) object;
        if ((this.usuId == null && other.usuId != null) || (this.usuId != null && !this.usuId.equals(other.usuId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Usuarios{" + "usuId=" + usuId + ", usuPrimerNombre=" + usuPrimerNombre + ", usuPrimerApellido=" + usuPrimerApellido + ", usuCorreo=" + usuCorreo + ", usuPassword=" + usuPassword + '}';
    }

}
