/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sckm.com.jcbj.sgp.servicio;

import java.util.List;
import sckm.com.jcbj.sgp.domain.Usuarios;

/**
 *
 * @author Juan
 */
public interface UsuarioService {

    public List<Usuarios> listAllUsuarios();
    
    public Usuarios findUsuarioByEmailAndPassword(Usuarios usuario);

    public void insertUsuario(Usuarios usuario);
    
}
