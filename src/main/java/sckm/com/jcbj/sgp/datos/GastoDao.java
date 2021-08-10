/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sckm.com.jcbj.sgp.datos;

import java.util.List;
import sckm.com.jcbj.sgp.domain.Gastos;
import sckm.com.jcbj.sgp.domain.Tareas;

/**
 *
 * @author Juan
 */
public interface GastoDao {

    public List<Gastos> listAllGastos();

    public List<Gastos> findGastosByIdTarea(Tareas tarea);

    public Tareas findGastosByIdAndName(Gastos gasto);

    public void insertGastos(Gastos gasto);

    public void updateGastos(Gastos gasto);

    public void deleteGastos(Gastos gasto);

}
