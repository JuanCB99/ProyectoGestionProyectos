/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sckm.com.jcbj.sgp.datos;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sckm.com.jcbj.sgp.domain.Fases;
import sckm.com.jcbj.sgp.domain.Tareas;

/**
 *
 * @author Juan
 */
@Stateless
public class TareaDaoImpl implements TareaDao{

    @PersistenceContext(unitName = "GestionProyectosPU")
    EntityManager em;
    
    @Override
    public List<Tareas> listAllTareas() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Tareas> findTareasByIdFase(Fases fase) {
             
        return em.createNamedQuery("Tareas.findTareasByIdFase").setParameter("tareaFaseId", fase).getResultList();
        
    }

    @Override
    public Tareas findTareasByIdAndName(Tareas fase) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertTarea(Tareas tarea) {
        
        em.persist(tarea);
        
    }

    @Override
    public void updateTarea(Tareas tarea) {
        
        em.merge(tarea);
        
    }

    @Override
    public void deleteTarea(Tareas tarea) {
        
        em.remove(em.merge(tarea));
        
    }
    
}
