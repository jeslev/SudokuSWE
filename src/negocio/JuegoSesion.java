package negocio;

import datos.JuegoSudoku;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import presentacion.BotonPanel;
import presentacion.SudokuPanel;

/**
 *
 * @author yarvis
 */
public class JuegoSesion {
    
    private boolean login;
    private int uID;
    private JuegoSudokuControlador jActual;
    private ArrayList<JuegoSudokuControlador> juegosPrevios;
    private ArrayList<JuegoSudokuControlador> juegosArchivo;
    
    public JuegoSesion(boolean login){
        this.login = login;
        juegosArchivo = new ArrayList<JuegoSudokuControlador>();
        jActual = new JuegoSudokuControlador();
        if(login){
            juegosPrevios = new ArrayList<JuegoSudokuControlador>();
        }else{ //invitado
            juegosPrevios = null;
        }
    }
    
    public void setID(int x){ uID=x;}
    public int getID() {return uID;}
    
    public boolean getLogin() { return login;}
    
    public void archivarJuego(){        
        jActual.terminar();
        double time = jActual.getTime();
        int ptje = jActual.getPuntaje();
        juegosArchivo.add(jActual);
        JOptionPane.showMessageDialog(null, "Tiempo total: "+time+"\nPuntaje Total: "+ptje);
    }
    
    /**Comandos Jugar **/
    
    public void setObserver(BotonPanel bp, SudokuPanel sp){
        jActual.addObserver(bp);
        jActual.addObserver(sp);
    }
    
    public JuegoSudokuControlador getActual(){
        return jActual;
    }
    
    public ArrayList<JuegoSudoku> getJuegosArchivo() {
        ArrayList<JuegoSudoku> lista = new ArrayList<>();
        for(int i=0;i<juegosArchivo.size();i++){
            lista.add(juegosArchivo.get(i).getSudoku());
        }
        return lista;
    }
}
