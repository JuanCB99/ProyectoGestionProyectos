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

import sckm.com.jcbj.sgp.domain.Gastos;
import sckm.com.jcbj.sgp.domain.Tareas;

/**
 *
 * @author Juan
 */
@Stateless
public class GastoDaoImpl implements GastoDao{

    @PersistenceContext(unitName = "GestionProyectosPU")
    EntityManager em;
    
    @Override
    public List<Gastos> listAllGastos() {
        
        return em.createNamedQuery("Gastos.findAll").getResultList();
        
    }

    @Override
    public List<Gastos> findGastosByIdTarea(Tareas tarea) {
        
        return em.createNamedQuery("Gastos.findGastosByIdTarea").setParameter("gastoTareaId", tarea).getResultList();
        
    }

    @Override
    public Tareas findGastosByIdAndName(Gastos gasto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertGastos(Gastos gasto) {
        
        em.persist(gasto);
        
    }

    @Override
    public void updateGastos(Gastos gasto) {
        
        em.merge(gasto);
        
    }

    @Override
    public void deleteGastos(Gastos gasto) {
        
        em.remove(em.merge(gasto));
        
    }
    
}
