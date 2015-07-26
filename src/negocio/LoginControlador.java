package negocio;

import datos.Usuarios;

/**
 *
 * @author yarvis
 */
public class LoginControlador {
    
    private int status=-1;
    
    public LoginControlador(String user, String pass){
        Usuarios datos = new Usuarios();
        status  = datos.login(user, pass);
    }
    
    public int getEstado(){ return status;}
}
