package sckm.com.jcbj.sgp.datos;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import sckm.com.jcbj.sgp.domain.Proyectos;
import sckm.com.jcbj.sgp.domain.Usuarios;

/**
 *
 * @author Juan
 */
@Stateless
public class ProyectoDaoImpl implements ProyectoDao {

    @PersistenceContext(unitName = "GestionProyectosPU")
    EntityManager em;

    @Override
    public List<Proyectos> listAllProyectos() {

        return em.createNamedQuery("Proyectos.findAll").getResultList();

    }

    @Override
    public List<Proyectos> findProyectosByIdUsuario(Usuarios usuario) {
        
        return em.createNamedQuery("Proyectos.findByProyectoUsuId").setParameter("proyectoUsuId",usuario).getResultList();
        
    }

    @Override
    public Proyectos findProyectoByIdAndName(Proyectos proyecto) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void insertProyecto(Proyectos proyecto) {
        em.persist(proyecto);
    }

    @Override
    public void updateProyecto(Proyectos proyecto) {
        em.merge(proyecto);
    }

    @Override
    public void deleteProyecto(Proyectos proyecto) {
        em.remove(em.merge(proyecto));
    }

}
