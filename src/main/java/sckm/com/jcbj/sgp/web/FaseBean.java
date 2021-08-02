/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sckm.com.jcbj.sgp.web;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import sckm.com.jcbj.sgp.domain.Fases;
import sckm.com.jcbj.sgp.domain.Proyectos;
import sckm.com.jcbj.sgp.servicio.FaseService;

/**
 *
 * @author Juan
 */
@Named("faseBean")
@ViewScoped
public class FaseBean implements Serializable {

    @Inject
    FaseService faseService;

    private String idProyecto;

    private List<Fases> fasesList;

    private Fases faseSeleccionada;

    private List<Proyectos> proyectosListRecuperados;

    private Map<String, Proyectos> proyectosMap;

    private Proyectos proyectoSeleccionado;

    public FaseBean() {
    }

    @PostConstruct
    public void inicializar() {

        this.faseSeleccionada = new Fases();
        //Se recupera la lista de proyectos relacionados con el usuario de la sesión actual
        this.proyectosListRecuperados = (List<Proyectos>) FacesContext.getCurrentInstance().
                getExternalContext().getSessionMap().get("proyectosList");

        //Se crea una variable de tipo Map para poder guardar cada objeto proyecto con una llave que es el ID respectivo de ese proyecto
        this.proyectosMap = new HashMap<>();
        for (Proyectos proyecto : this.proyectosListRecuperados) {
            this.proyectosMap.put(Integer.toString(proyecto.getProyectoId()), proyecto);
        }

    }

    //--------------------------------------------------------
    public Map<String, Proyectos> getProyectosMap() {
        return proyectosMap;
    }

    public void setProyectosMap(Map<String, Proyectos> proyectosMap) {
        this.proyectosMap = proyectosMap;
    }

    public String getIdProyecto() {
        return idProyecto;
    }

    public void setIdProyecto(String idProyecto) {
        this.idProyecto = idProyecto;
    }

    public List<Proyectos> getProyectosListRecuperados() {
        return proyectosListRecuperados;
    }

    public void setProyectosListRecuperados(List<Proyectos> proyectosListRecuperados) {
        this.proyectosListRecuperados = proyectosListRecuperados;
    }

    //----------------------------------------------------------
    public List<Fases> getFasesList() {
        return fasesList;
    }

    public void setFasesList(List<Fases> fasesList) {
        this.fasesList = fasesList;
    }

    public Fases getFaseSeleccionada() {
        return faseSeleccionada;
    }

    public void setFaseSeleccionada(Fases faseSeleccionada) {
        this.faseSeleccionada = faseSeleccionada;
    }

    public Proyectos getProyectoSeleccionado() {
        return proyectoSeleccionado;
    }

    public void setProyectoSeleccionado(Proyectos proyectoSeleccionado) {
        this.proyectoSeleccionado = proyectoSeleccionado;
    }

    //este metodo se llama atraves de AJAX para actualizar las fases que se muestran respecto al proyecto que selecciona el usuario
    //y trae el valor almacenado en el Map mediante el iD y lo guarda rn la variable proyectoSeleccionado,despues llama al metodo listar FasesByProyectoId
    public void onProyectoChange() {
        System.out.println("ENTRANDO AL METODO DE AJAX:");
        if (this.idProyecto != null && !this.idProyecto.equals("")) {

            this.proyectoSeleccionado = this.proyectosMap.get(this.idProyecto);
            System.out.println("PROYECTO ENVIADO POR AJAX: " + this.proyectoSeleccionado);
            listFasesByProyectoId();

        } else {
            this.proyectoSeleccionado = null;
            this.fasesList = null;
        }
    }

    public void listFasesByProyectoId() {

        this.fasesList = this.faseService.findFasesByIdProyecto(proyectoSeleccionado);
        //Se comparten las fases del proyecto seleccionado en el desplegable para actualizar la lista desplegable
        //de fases correspondientes a ese proyecto
        FacesContext.getCurrentInstance().getExternalContext()
                .getSessionMap().put("fasesList", this.fasesList);

    }

    //este metodo asigna el proyecto con el que se relaciona la fase que se va a insertar y despues se llama al metodo para insertar, enviando
    //como parametro faseSeleccionada que contiene la fase que se va a insertar
    public void insertarFase() {

        try {

            this.faseSeleccionada.setFaseProyectoId(this.proyectoSeleccionado);
            System.out.println("FASE ENVIADA PARA GUARDAR: " + this.faseSeleccionada);
            this.faseService.insertFase(faseSeleccionada);
            this.fasesList.add(this.faseSeleccionada);
            this.faseSeleccionada = null;
            //Se manda un mensaje para notificar al usuario que la fase se asigno con exito
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardado: Se asigno la fase al proyecto con exito!!", ""));

        } catch (Exception e) {

            e.printStackTrace(System.out);

            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: No se pudo guardar la informacion", "" + e));

        }

    }

    public void editarFase() {

        this.faseSeleccionada.setFaseProyectoId(this.proyectoSeleccionado);
        this.faseService.updateFase(faseSeleccionada);
        this.fasesList.remove(this.faseSeleccionada);
        this.fasesList.add(this.faseSeleccionada);
        this.faseSeleccionada = null;
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizado: Se edito la fase con exito!!", ""));

    }

    public void eliminarFase() {

        System.out.println("FASE SELECCIONADA PARA ELIMINAR: " + faseSeleccionada);
        this.faseService.deleteFase(this.faseSeleccionada);
        this.fasesList.remove(this.faseSeleccionada);
        this.faseSeleccionada = null;
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Eliminado: Se elimino la fase con exito!!", ""));

    }

}
