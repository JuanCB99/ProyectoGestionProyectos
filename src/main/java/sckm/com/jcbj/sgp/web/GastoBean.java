/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sckm.com.jcbj.sgp.web;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.ServletContext;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import sckm.com.jcbj.sgp.domain.Fases;
import sckm.com.jcbj.sgp.domain.Gastos;
import sckm.com.jcbj.sgp.domain.Tareas;
import sckm.com.jcbj.sgp.servicio.GastoService;
import sckm.com.jcbj.sgp.utilis.ImageUtils;

/**
 *
 * @author Juan
 */
@Named("gastoBean")
@ViewScoped
public class GastoBean implements Serializable {

    @Inject
    private GastoService gastoService;
    @Inject
    private TareaBean tareaBean;

    private String idTarea;

    private List<Fases> fasesListRecuperada;

    private List<Tareas> tareasListRecuperada;

    private List<Gastos> gastosList;

    private Tareas tareaSeleccionada;

    private Map<String, Fases> fasesMap;

    private Map<String, Tareas> tareasMap;

    private static final String RUTA_DESTINO = "../../src/main/webapp/resources/images/imagesGastos/";

    private UploadedFile file = null;
    private Gastos gastoSeleccionado;
    //GastoImgSeleccionada, solo tiene la funcion de recibir la ruta desde un objeto de tipo gasto para poder mostrarlo en el modal
    private Gastos gastoImgSeleccionado;

    @PostConstruct
    public void inicializar() {
        gastoSeleccionado = new Gastos();
        System.out.println("GASTO INICIALIZADO = " + gastoSeleccionado);
    }

    public List<Gastos> getGastosList() {
        return gastosList;
    }

    public String getIdTarea() {
        return idTarea;
    }

    public void setIdTarea(String idTarea) {
        this.idTarea = idTarea;
    }

    public List<Fases> getFasesListRecuperada() {
        return fasesListRecuperada;
    }

    public void setFasesListRecuperada(List<Fases> fasesListRecuperada) {
        this.fasesListRecuperada = fasesListRecuperada;
    }

    public List<Tareas> getTareasListRecuperada() {
        return tareasListRecuperada;
    }

    public void setTareasListRecuperada(List<Tareas> tareasListRecuperada) {
        this.tareasListRecuperada = tareasListRecuperada;
    }

    public Tareas getTareaSeleccionada() {
        return tareaSeleccionada;
    }

    public void setTareaSeleccionada(Tareas tareaSeleccionada) {
        this.tareaSeleccionada = tareaSeleccionada;
    }

    public Map<String, Fases> getFasesMap() {
        return fasesMap;
    }

    public void setFasesMap(Map<String, Fases> fasesMap) {
        this.fasesMap = fasesMap;
    }

    public void setGastosList(List<Gastos> gastosList) {
        this.gastosList = gastosList;
    }

    public UploadedFile getFile() {
        return file;
    }

    public void setFile(UploadedFile file) {
        this.file = file;
    }

    public Gastos getGastoSeleccionado() {
        return gastoSeleccionado;
    }

    public void setGastoSeleccionado(Gastos gastoSeleccionado) {
        this.gastoSeleccionado = gastoSeleccionado;
    }

    public Gastos getGastoImgSeleccionado() {
        return gastoImgSeleccionado;
    }

    public void setGastoImgSeleccionado(Gastos gastoImgSeleccionado) {
        this.gastoImgSeleccionado = gastoImgSeleccionado;
    }

    public void nuevoDocumento(FileUploadEvent event) {

        ServletContext servletContext = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        //guardo la ruta dinamica del proyecto, la cual es retornada por el metodo getRealPath y le agrego el URL que es la parte de la ruta faltante
        String rutaDinamica = servletContext.getRealPath("/") + RUTA_DESTINO;
        System.out.println("RUTA DINAMICA" + rutaDinamica);

        file = event.getFile();

        gastoSeleccionado.setGastoImagen("../resources/images/imagesGastos/"
                + gastoSeleccionado.getGastoId() + ".png");

        try {

            this.gastoService.updateGastos(gastoSeleccionado);
            System.out.println("ARCHIVO RECIBIDO = " + file);

            ImageUtils.copyFile(gastoSeleccionado.getGastoId() + ".png", file.getInputStream(), rutaDinamica);
            System.out.println("IMAGEN GUARDADA");
            this.gastosList.remove(gastoSeleccionado);
            this.gastosList.add(gastoSeleccionado);
            this.gastoSeleccionado = null;
            this.gastoSeleccionado = new Gastos();

            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardado: Se guardo la imagen con exito!!", ""));

        } catch (Exception e) {

            e.printStackTrace(System.out);
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: No se pudo guardar la imagen", ""));
        } finally {
            file = null;
        }

    }

    public void insertarGasto() {

        try {

            this.gastoSeleccionado.setGastoTareaId(tareaSeleccionada);

            this.gastoSeleccionado.setGastoPrecioTotal(
                    this.gastoSeleccionado.getGastoCantidadProd()
                    * this.getGastoSeleccionado().getGastoPrecioProd());

            System.out.println("GASTO CON DATOS = " + gastoSeleccionado);
            gastoService.insertGastos(gastoSeleccionado);
            System.out.println("GASTO INSERTADO CON ID = " + gastoSeleccionado);
            this.gastosList.add(gastoSeleccionado);

            //Se manda un mensaje para notificar al usuario que el gasto se asigno con exito
            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Guardado: Se asigno el gasto a la tarea con exito!!", ""));

        } catch (Exception e) {

            e.printStackTrace(System.out);

            FacesContext.getCurrentInstance().
                    addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: No se pudo guardar la informacion", "" + e));

        }

    }

    //este metodo se llama atraves de AJAX para actualizar las fases que se muestran respecto al proyecto que selecciona el usuario
    //y trae el valor almacenado en el Map mediante el iD y lo guarda rn la variable proyectoSeleccionado,despues llama al metodo listar FasesByProyectoId
    public void onProyectoChange() {
        //se llama al metodo que recupera las fases apartir del proyecto seleccioando
        tareaBean.onProyectoChange();
        listFasesByProyectoId();
        this.gastosList = null;
    }

    //este metodo se llama atraves de AJAX para actualizar las fases que se muestran respecto al proyecto que selecciona el usuario
    //y trae el valor almacenado en el Map mediante el iD y lo guarda rn la variable proyectoSeleccionado,despues llama al metodo listar FasesByProyectoId
    public void onFaseChange() {
        //se llama al metodo que recupera las fases apartir del proyecto seleccioando
        tareaBean.onFaseChange();
        System.out.println("EL NULL POINTER FUE ACA");
        listTareasByFaseId();
        this.gastosList = null;
    }

    public void onTareaChange() {
        //se llama al metodo que recupera las fases apartir del proyecto seleccioando
        System.out.println("ENTRANDO AL METODO DE AJAX:");
        if (this.idTarea != null && !this.idTarea.equals("")) {

            this.tareaSeleccionada = this.tareasMap.get(this.idTarea);
            System.out.println("TAREA ENVIADA POR AJAX: " + this.tareaSeleccionada);
            listGastosByTareaId();

        } else {
            this.tareaSeleccionada = null;
            this.gastosList = null;
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

    //se recupera la lista de fases y se agrega a un Map para poder usarla despues
    public void listTareasByFaseId() {

        this.tareasListRecuperada = (List<Tareas>) FacesContext.getCurrentInstance().
                getExternalContext().getSessionMap().get("tareasList");
        System.out.println("TAREAS RECUPERADAS = " + tareasListRecuperada);
        //Se crea una variable de tipo Map para poder guardar cada objeto fase con una llave que es el ID respectivo de esa fase
        this.tareasMap = new HashMap<>();
        for (Tareas tarea : this.tareasListRecuperada) {
            this.tareasMap.put(Integer.toString(tarea.getTareaId()), tarea);
        }

    }

    private void listGastosByTareaId() {

        this.gastosList = this.gastoService.findGastosByIdTarea(tareaSeleccionada);
        System.out.println("GASTOS RECUPERADOS = " + this.gastosList);

    }

}
