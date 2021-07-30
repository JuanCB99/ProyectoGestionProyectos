/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sckm.com.jcbj.sgp.datos;

import java.util.List;
import sckm.com.jcbj.sgp.domain.Proyectos;
import sckm.com.jcbj.sgp.domain.Usuarios;

/**
 *
 * @author Juan
 */
public interface ProyectoDao {

    public List<Proyectos> listAllProyectos();
    
    public List<Proyectos> findProyectosByIdUsuario(Usuarios usuario);

    public Proyectos findProyectoByIdAndName(Proyectos proyecto);

    public void insertProyecto(Proyectos proyecto);

    public void updateProyecto(Proyectos proyecto);

    public void deleteProyecto(Proyectos proyecto);

}
