/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sckm.com.jcbj.sgp.servicio;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import sckm.com.jcbj.sgp.datos.GastoDao;
import sckm.com.jcbj.sgp.domain.Gastos;
import sckm.com.jcbj.sgp.domain.Tareas;

/**
 *
 * @author Juan
 */
@Stateless
public class GastoServiceImpl implements GastoService{

    @Inject
    private GastoDao gastoDao; 
    
    @Override
    public List<Gastos> listAllGastos() {
        
        return gastoDao.listAllGastos();
        
    }

    @Override
    public List<Gastos> findGastosByIdTarea(Tareas tarea) {
        
        return gastoDao.findGastosByIdTarea(tarea);
        
    }

    @Override
    public Tareas findGastosByIdAndName(Gastos gasto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertGastos(Gastos gasto) {
        
        gastoDao.insertGastos(gasto);
        
    }

    @Override
    public void updateGastos(Gastos gasto) {
        
        gastoDao.updateGastos(gasto);
        
    }

    @Override
    public void deleteGastos(Gastos gasto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
