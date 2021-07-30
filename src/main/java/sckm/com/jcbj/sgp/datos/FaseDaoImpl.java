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
import sckm.com.jcbj.sgp.domain.Proyectos;

/**
 *
 * @author Juan
 */
@Stateless
public class FaseDaoImpl implements FaseDao{

    @PersistenceContext(unitName = "GestionProyectosPU")
    EntityManager em;
    
    @Override
    public List<Fases> listAllFases() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Fases> findFasesByIdProyecto(Proyectos proyecto) {
        
        return em.createNamedQuery("Fases.findFasesByIdProyecto").setParameter("faseProyectoId", proyecto).getResultList();
        
    }

    @Override
    public Fases findFasesByIdAndName(Fases fase) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertFase(Fases fase) {
        
        em.persist(fase);
        
    }

    @Override
    public void updateFase(Fases fase) {
        
        em.merge(fase);
        
    }

    @Override
    public void deleteFase(Fases fase) {
        
        em.remove(em.merge(fase));
        
    }
    
}
