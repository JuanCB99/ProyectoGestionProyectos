/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sckm.com.jcbj.sgp.web;

import java.io.IOException;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import sckm.com.jcbj.sgp.domain.Usuarios;
import sckm.com.jcbj.sgp.servicio.UsuarioService;

/**
 *
 * @author Juan
 */
@Named("usuarioBean")
@RequestScoped
public class UsuarioBean {

    @Inject
    private UsuarioService usuarioService;

    private List<Usuarios> usuariosList;
    private Usuarios usuarioSeleccionado;

    private FacesMessage msg;

    public UsuarioBean() {
    }

    @PostConstruct
    public void inicializar() {

        //usuariosList = usuarioService.listAllUsuarios();
        //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usariosTotal", usuariosList.size());
        this.usuarioSeleccionado = new Usuarios();
        //System.out.println("USUARIO INICIALIZADO: " + this.usuarioSeleccionado);
        //System.out.println("EJECUTANDO LA CONSULTA LISTAR USSUARIOS" + this.usuariosList);

    }

    public List<Usuarios> getUsuariosList() {
        return usuariosList;
    }

    public void setUsuariosList(List<Usuarios> usuariosList) {
        this.usuariosList = usuariosList;
    }

    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

    public void setUsuarioService(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    public Usuarios getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }

    public void setUsuarioSeleccionado(Usuarios usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }

    public void logIn() throws IOException {

        Usuarios usuarioRecuperado;
        System.out.println("OBJETO USUARIO CREADO: " + this.usuarioSeleccionado);
        //1-La forma correcta para redireccionar de un Bean a otro JSF(.xhtml)
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();

        //ejectamos el metodo buscar usuario por correo y contraseña, y almacenamos el resultado en una variable
        usuarioRecuperado = this.usuarioService.findUsuarioByEmailAndPassword(this.usuarioSeleccionado);

        if (usuarioRecuperado != null) {

            //se setean los atributos del usuario con nuevos valores sin relevacia, para proteger esos campos de cierta manera, dado que el unico atributo necesario
            //es el Id.
            usuarioRecuperado.setUsuPrimerNombre("usuOk");
            usuarioRecuperado.setUsuPrimerApellido("usuOk");
            usuarioRecuperado.setUsuPassword("usuOk");
            //Se almacena el inicio de sesión para poder mostrar la pagina en caso de que las credenciales sean correctas
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuarioRecuperado", usuarioRecuperado);
            //2-parte final para redireccionar a otro jsf
            externalContext.redirect("protected/dashBoardProyectos.xhtml");

        } else {

            //se manda un mensaje avisando que las credenciales son incorrectas
            this.msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error: Las credenciales no son correctas.", "");
            FacesContext.getCurrentInstance().addMessage(null, this.msg);

        }

        System.out.println("USUARIO COMPARTIDO DESDE EL LOGIN:" + usuarioRecuperado);

    }

    //este metodo se llama en cada pagina, para poder mostrar el contenido, en caso de que haya iniciado sesion
    public void verificarSesion() {

        FacesContext facesContex = FacesContext.getCurrentInstance();
        //1-La forma correcta para redireccionar de un Bean a otro JSF(.xhtml)
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();

        try {

            //se recupera el usuario que se guado al loguearse y si la variable es diferente de null quiere decir que si inicio sesion
            //de lo contrario se redirecciona a la pagina de error 401
            Usuarios usuarioVerificar = (Usuarios) facesContex.getExternalContext().getSessionMap().get("usuarioRecuperado");

            if (usuarioVerificar == null) {

                externalContext.redirect("../errorPage/401.xhtml");

            }

        } catch (Exception e) {

            e.printStackTrace(System.out);

        }

    }

    public void crearUsuario() {

        this.usuarioService.insertUsuario(this.usuarioSeleccionado);
        System.out.println("DATOS DE USUARIO PARA CREAR: " + this.usuarioSeleccionado);
        this.usuarioSeleccionado = null;
        msg = new FacesMessage("Se creo el usuario con exito, ya puede iniciar sesión.");
        FacesContext.getCurrentInstance().addMessage(null, msg);

    }

}
