/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sckm.com.jcbj.sgp.datos;

import java.util.List;
import sckm.com.jcbj.sgp.domain.Usuarios;

/**
 *
 * @author Juan
 */
public interface UsuarioDao {
    
    public List<Usuarios> findAllUsuarios();
    
    public Usuarios findUsuarioByEmailAndPassword(Usuarios usuario);
    
    public void insertUsuario(Usuarios usuario);
    
}
