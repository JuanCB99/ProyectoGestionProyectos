/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sckm.com.jcbj.sgp.datos;

import java.util.List;
import sckm.com.jcbj.sgp.domain.Fases;
import sckm.com.jcbj.sgp.domain.Proyectos;

/**
 *
 * @author Juan
 */
public interface FaseDao {

    public List<Fases> listAllFases();

    public List<Fases> findFasesByIdProyecto(Proyectos proyecto);

    public Fases findFasesByIdAndName(Fases fase);

    public void insertFase(Fases fase);

    public void updateFase(Fases fase);

    public void deleteFase(Fases fase);

}
