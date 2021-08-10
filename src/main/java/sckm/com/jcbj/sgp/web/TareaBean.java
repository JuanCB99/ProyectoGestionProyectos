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
import sckm.com.jcbj.sgp.domain.Tareas;
import sckm.com.jcbj.sgp.servicio.TareaService;

/**
 *
 * @author Juan
 */
@Named("tareaBean")
@ViewScoped
public class TareaBean implements Serializable {

    @Inject
    TareaService tareaService;
    @Inject
    FaseBean faseBean;

    private String idFase;

    private List<Tareas> tareasList;

    private Tareas tareaSeleccionada;

    private List<Fases> fasesListRecuperada;

    private Map<String, Fases> fasesMap;

    private Fases faseSeleccionada;

    @PostConstruct
    public void inicializar() {

        this.faseSeleccionada = new Fases();
        this.tareaSeleccionada = new Tareas();

    }

    public String getIdFase() {
        return idFase;
    }

    public void setIdFase(String idFase) {
        this.idFase = idFase;
    }

    public List<Tareas> getTareasList() {
        return tareasList;
    }

    public void setTareasList(List<Tareas> tareasList) {
        this.tareasList = tareasList;
    }

    public Tareas getTareaSeleccionada() {
        return tareaSeleccionada;
    }

    public void setTareaSeleccionada(Tareas tareaSeleccionada) {
        this.tareaSeleccionada = tareaSeleccionada;
    }

    public List<Fases> getFasesListRecuperada() {
        return fasesListRecuperada;
    }

    public void setFasesListRecuperada(List<Fases> fasesListRecuperada) {
        this.fasesListRecuperada = fasesListRecuperada;
    }

    public Map<String, Fases> getFasesMap() {
        return fasesMap;
    }

    public void setFasesMap(Map<String, Fases> fasesMap) {
        this.fasesMap = fasesMap;
    }

    public Fases getFaseSeleccionada() {
        return faseSeleccionada;
    }

    public void setFaseSeleccionada(Fases faseSeleccionada) {
        this.faseSeleccionada = faseSeleccionada;
    }

    //este metodo se llama atraves de AJAX para actualizar las fases que se muestran respecto al proyecto que selecciona el usuario
    //y trae el valor almacenado en el Map mediante el iD y lo guarda rn la variable proyectoSeleccionado,despues llama al metodo listar FasesByProyectoId
    public void onProyectoChange() {
        //se llama al metodo que recupera las fases apartir del proyecto seleccioando
        faseBean.onProyectoChange();
        listFasesByProyectoId();
        this.tareasList = null;
    }

    public void onFaseChange() {
        //se llama al metodo que recupera las fases apartir del proyecto seleccioando

        System.out.println("ENTRANDO AL METODO DE AJAX:");
        if (this.idFase != null && !this.idFase.equals("")) {

            this.faseSeleccionada = this.fasesMap.get(this.idFase);
            System.out.println("FASE ENVIADA POR AJAX: " + this.faseSeleccionada);
            listTareasByFaseId();

        } else {
            this.faseSeleccionada = null;
            this.tareasList = null;
        }

    }

    //se recupera la lista de fases y se agrega a un Map para poder usarla despues
    public void listFasesByProyectoId() {

        this.fasesListRecuperada = (List<Fases>) FacesContext.getCurrentInstance().
                getExternalContext().getSessionMap().get("fasesList");

        //Se crea una variable de tipo Map para poder guardar cada objeto fase con una llave que es el ID respectivo de esa fase
        this.fasesMap = new HashMap<>();
        for (Fases fase : this.fasesListRecuperada) {
            this.fasesMap.put(Integer.toString(fase.getFaseId()), fase);
        }

    }

    public void listTareasByFaseId() {

        this.tareasList = this.tareaService.findTareasByIdFase(faseSeleccionada);
                FacesContext.getCurrentInstance().getExternalContext()
                .getSessionMap().put("tareasList", this.tareasList);

    }

    public void insertarTarea() {

        try {

            this.tareaSeleccionada.setTareaFaseId(faseSeleccionada);
            System.out.println("TAREA PARA GUARDAR: " + this.tareaSeleccionada);
            this.tareaService.insertTarea(tareaSeleccionada);
            this.tareasList.add(this.tareaSeleccionada);
            this.tareaSeleccionada = new Tareas();
            //Se manda un mensaje para notificar al usuario que la tarea se asigno con exito
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardado: Se asigno la tarea a la fase con exito!!", ""));

        } catch (Exception e) {

            e.printStackTrace(System.out);

            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: No se pudo guardar la informacion", "" + e));

        }

    }

    public void editarTarea() {

        this.tareaSeleccionada.setTareaFaseId(this.faseSeleccionada);
        this.tareaService.updateTarea(this.tareaSeleccionada);
        this.tareasList.remove(this.tareaSeleccionada);
        this.tareasList.add(this.tareaSeleccionada);
        this.tareaSeleccionada = null;
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Actualizado: Se edito la tarea con exito!!", ""));

    }
    
    public void eliminarTarea(){
        
        this.tareaService.deleteTarea(this.tareaSeleccionada);
        this.tareasList.remove(this.tareaSeleccionada);
        this.tareaSeleccionada = null;
         FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Eliminado: Se elimino la tarea con exito!!", ""));
        
    }

}
