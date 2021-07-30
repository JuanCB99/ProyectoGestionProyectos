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
import sckm.com.jcbj.sgp.datos.ProyectoDao;
import sckm.com.jcbj.sgp.domain.Proyectos;
import sckm.com.jcbj.sgp.domain.Usuarios;

/**
 *
 * @author Juan
 */
@Stateless
public class ProyectoServiceImpl implements ProyectoService {

    @Inject
    private ProyectoDao proyectoDao;

    @Resource
    private SessionContext contexto;

    @Override
    public List<Proyectos> listAllProyectos() {

        return proyectoDao.listAllProyectos();

    }

    @Override
    public List<Proyectos> findProyectosByIdUsuario(Usuarios usuario) {
        return proyectoDao.findProyectosByIdUsuario(usuario);
    }

    @Override
    public Proyectos findProyectoByIdAndName(Proyectos proyecto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertProyecto(Proyectos proyecto) {

        proyectoDao.insertProyecto(proyecto);

    }

    @Override
    public void updateProyecto(Proyectos proyecto) {

        try {

            proyectoDao.updateProyecto(proyecto);

        } catch (Throwable e) {

            contexto.setRollbackOnly();
            e.printStackTrace(System.out);

        }

    }

    @Override
    public void deleteProyecto(Proyectos proyecto) {
        proyectoDao.deleteProyecto(proyecto);
    }

}
