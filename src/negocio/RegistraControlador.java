package negocio;

import datos.Usuarios;

/**
 *
 * @author yarvis
 */
public class RegistraControlador {
    
    private int res;
    
    public RegistraControlador(String user, String pass, String pass2){
        res = -1;
        Usuarios usuario  = new Usuarios();
        res = usuario.crearUsuario(user, pass, pass2);
        
    }
    
    public int getEstado(){
        return res;
    }
}
