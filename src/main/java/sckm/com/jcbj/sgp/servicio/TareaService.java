/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sckm.com.jcbj.sgp.servicio;

import java.util.List;
import sckm.com.jcbj.sgp.domain.Fases;
import sckm.com.jcbj.sgp.domain.Tareas;

/**
 *
 * @author Juan
 */
public interface TareaService {

    public List<Tareas> listAllTareas();

    public List<Tareas> findTareasByIdFase(Fases fase);

    public Tareas findTareasByIdAndName(Tareas fase);

    public void insertTarea(Tareas tarea);

    public void updateTarea(Tareas tarea);

    public void deleteTarea(Tareas tarea);

}
