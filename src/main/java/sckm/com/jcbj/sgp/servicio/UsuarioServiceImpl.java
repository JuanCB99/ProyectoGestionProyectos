package sckm.com.jcbj.sgp.servicio;

import java.util.List;
import javax.inject.Inject;
import sckm.com.jcbj.sgp.datos.UsuarioDao;
import sckm.com.jcbj.sgp.domain.Usuarios;

/**
 *
 * @author Juan
 */
public class UsuarioServiceImpl implements UsuarioService {

    @Inject
    private UsuarioDao usuarioDao;

    @Override
    public List<Usuarios> listAllUsuarios() {
        
        System.out.print("EJECUTANDO EL METODO LIST ALL CAPA DE SERVICIO");
        List<Usuarios> listPrueba = usuarioDao.findAllUsuarios();
        System.out.println("RESULTADO DESDE LA CAPA DE SERVICIO" + listPrueba);
        return listPrueba;
        
    }

    @Override
    public Usuarios findUsuarioByEmailAndPassword(Usuarios usuario) {
        
        return usuarioDao.findUsuarioByEmailAndPassword(usuario);
        
    }

    @Override
    public void insertUsuario(Usuarios usuario) {
        
        System.out.println("CREANDO EL USUARIO DESDE LA CAPA DE SERVICIO: " + usuario);
        usuarioDao.insertUsuario(usuario);
        
    }

}
