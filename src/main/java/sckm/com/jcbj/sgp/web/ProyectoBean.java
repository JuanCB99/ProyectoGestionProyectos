/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sckm.com.jcbj.sgp.web;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import org.primefaces.event.RowEditEvent;
import sckm.com.jcbj.sgp.domain.Proyectos;
import sckm.com.jcbj.sgp.domain.Usuarios;
import sckm.com.jcbj.sgp.servicio.ProyectoService;

/**
 *
 * @author Juan
 */
@Named("proyectoBean")
@RequestScoped
public class ProyectoBean {

    @Inject
    private ProyectoService proyectoService;

    private List<Proyectos> proyectosList;

    private List<String> nombresProyectos;

    private Proyectos proyectoSeleccionado;

    private Usuarios usuarioActual;

    private FacesMessage msg;

    public ProyectoBean() {
    }

    //Este metodo es temporal, para inicilizar los componentes y probar que todo funcione
    @PostConstruct
    public void inicializar() {

        //se recupera el usuario que inicio sesión para mostrarle solo los proyectos que le corresponden
        FacesContext facesContex = FacesContext.getCurrentInstance();
        this.usuarioActual = (Usuarios) facesContex.getExternalContext().getSessionMap().get("usuarioRecuperado");

        this.proyectosList = this.proyectoService.findProyectosByIdUsuario(this.usuarioActual);

        //prueba para compartir los proyectos del usuario actual
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("proyectosList", this.proyectosList);

        this.proyectoSeleccionado = new Proyectos();
        System.out.println("PROYECTO INICIALIZADO: " + this.proyectoSeleccionado);

    }

    public List<Proyectos> getProyectosList() {
        return this.proyectosList;
    }

    public void setProyectosList(List<Proyectos> proyectosList) {
        this.proyectosList = proyectosList;
    }

    public Proyectos getProyectoSeleccionado() {
        return this.proyectoSeleccionado;
    }

    public void setProyectoSeleccionado(Proyectos proyectoSeleccionado) {
        this.proyectoSeleccionado = proyectoSeleccionado;
    }

    public List<String> getNombresProyectos() {
        return nombresProyectos;
    }

    public void setNombresProyectos(List<String> nombresProyectos) {
        this.nombresProyectos = nombresProyectos;
    }

    public void insertarProyecto() {

        try {

            this.proyectoSeleccionado.setProyectoUsuId(this.usuarioActual);
            System.out.println("PROYECTO ENVIADO PARA GUARDAR:  " + this.proyectoSeleccionado);
            this.proyectoService.insertProyecto(this.proyectoSeleccionado);
            this.proyectosList.add(this.proyectoSeleccionado);
            this.proyectoSeleccionado = null;
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardado: Se creo el proyecto con exito!!", ""));

        } catch (Exception e) {

            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: No se pudo guardar la informacion", "" + e));

        }

    }

    public void editListener(RowEditEvent event) {

        Proyectos proyectoEditar = (Proyectos) event.getObject();
        this.proyectoService.updateProyecto(proyectoEditar);

    }

    public void eliminarProyecto() {

        this.proyectoService.deleteProyecto(this.proyectoSeleccionado);
        this.proyectosList.remove(this.proyectoSeleccionado);
        this.proyectoSeleccionado = null;
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Eliminado: Se elimino el Proyecto con exito!!", ""));

    }

    public void totalProyectos() {

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("proyectosTotal", this.proyectosList.size());

    }

    public void sumaPresupuestos() {

        double sumaTotal = 0;

        for (Proyectos proyecto : this.proyectosList) {

            sumaTotal = proyecto.getProyectoPresupuesto() + sumaTotal;

        }

        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("presupuestoTotal", sumaTotal);

    }

    public void extraerNombresProyectos() {

        this.nombresProyectos = new ArrayList<>();

        for (Proyectos proyecto : this.proyectosList) {

            this.nombresProyectos.add(proyecto.getProyectoNombre());

        }

    }

}
