/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sckm.com.jcbj.sgp.servicio;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import sckm.com.jcbj.sgp.datos.TareaDao;
import sckm.com.jcbj.sgp.domain.Fases;
import sckm.com.jcbj.sgp.domain.Tareas;

/**
 *
 * @author Juan
 */
@Stateless
public class TareaServiceImpl implements TareaService{
    
    @Inject
    TareaDao tareaDao;

    @Override
    public List<Tareas> listAllTareas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Tareas> findTareasByIdFase(Fases fase) {
        
        return tareaDao.findTareasByIdFase(fase);
        
    }

    @Override
    public Tareas findTareasByIdAndName(Tareas fase) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertTarea(Tareas tarea) {
        
        tareaDao.insertTarea(tarea);
        
    }

    @Override
    public void updateTarea(Tareas tarea) {
        
        tareaDao.updateTarea(tarea);
        
    }

    @Override
    public void deleteTarea(Tareas tarea) {
        
        tareaDao.deleteTarea(tarea);
        
    }
    
}
