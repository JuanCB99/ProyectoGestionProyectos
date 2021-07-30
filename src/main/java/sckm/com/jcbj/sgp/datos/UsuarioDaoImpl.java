package sckm.com.jcbj.sgp.datos;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import sckm.com.jcbj.sgp.domain.Usuarios;

/**
 *
 * @author Juan
 */
@Stateless
public class UsuarioDaoImpl implements UsuarioDao {

    @PersistenceContext(unitName = "GestionProyectosPU")
    EntityManager em;

    @Override
    public List<Usuarios> findAllUsuarios() {

        return em.createNamedQuery("Usuarios.findAll").getResultList();

    }

    @Override
    public Usuarios findUsuarioByEmailAndPassword(Usuarios usuario) {

        try {
            Query query = em.createQuery("SELECT u FROM Usuarios u WHERE u.usuCorreo = :usuCorreo AND u.usuPassword = :usuPassword");
            query.setParameter("usuCorreo", usuario.getUsuCorreo());
            query.setParameter("usuPassword", usuario.getUsuPassword());

            return (Usuarios) query.getSingleResult();

        } catch (Exception e) {
            e.printStackTrace(System.out);
            return null;
        }

    }

    @Override
    public void insertUsuario(Usuarios usuario) {
        
        System.out.println("CREANDO USUARIO DESDE LA CAPA DE DATOS: " + usuario);
        em.persist(usuario);
        
    }
    

}
