/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sckm.com.jcbj.sgp.servicio;

import java.util.List;
import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Inject;
import sckm.com.jcbj.sgp.datos.FaseDao;
import sckm.com.jcbj.sgp.domain.Fases;
import sckm.com.jcbj.sgp.domain.Proyectos;

/**
 *
 * @author Juan
 */
@Stateless
public class FaseServiceImpl implements FaseService {

    @Inject
    private FaseDao faseDao;

    @Resource
    private SessionContext contexto;

    @Override
    public List<Fases> listAllFases() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Fases> findFasesByIdProyecto(Proyectos proyecto) {

        return faseDao.findFasesByIdProyecto(proyecto);

    }

    @Override
    public Fases findFasesByIdAndName(Fases fase) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertFase(Fases fase) {

        this.faseDao.insertFase(fase);

    }

    @Override
    public void updateFase(Fases fase) {

        try {

            this.faseDao.updateFase(fase);

        } catch (Throwable e) {

            contexto.setRollbackOnly();
            e.printStackTrace(System.out);

        }

    }

    @Override
    public void deleteFase(Fases fase) {
        
        this.faseDao.deleteFase(fase);
        
    }

}
