package negocio;

import datos.ComparatorJuego;
import datos.JuegoSudoku;
import datos.Usuarios;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author yarvis
 */
public class JuegoEstadisticas {

    private JuegoSesion sActual;
    
    public JuegoEstadisticas(JuegoSesion sesion){
        sActual=sesion;
    }
    
    public String[] getJuegosPropios(){
        String strings[] = new String[5];
        ArrayList<JuegoSudoku> juegos;
        if(sActual.getLogin()){
            Usuarios user = new Usuarios();
            juegos = user.obtenerPartidasUsuario(sActual.getID());
        }else{
            juegos =sActual.getJuegosArchivo();
        }
        
        Collections.sort(juegos, new ComparatorJuego());
        int cnt = 0;
        for(int i=0;i<juegos.size();i++){
            if(cnt>=5) break;
            JuegoSudoku tmp = juegos.get(i);
            strings[i] = (i+1)+" - "+tmp.getPuntaje()+" - "+tmp.getTime();
        }
        return strings;
    }
    
    public String[] getJuegosTotales(){
        String strings[] = new String[5];
        ArrayList<JuegoSudoku> juegos;
        Usuarios user = new Usuarios();
       juegos = user.obtenerPartidasTotal();
       Collections.sort(juegos, new ComparatorJuego());
        int cnt = 0;
        for(int i=0;i<juegos.size();i++){
            if(cnt>=5) break;
            JuegoSudoku tmp = juegos.get(i);
            strings[i] = (i+1)+" - "+tmp.getPuntaje()+" - "+tmp.getTime();
        }
        return strings;
    }
}
