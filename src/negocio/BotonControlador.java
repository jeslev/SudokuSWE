package negocio;

//import datos.DataJuego;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JCheckBox;

/**
 *
 * @author jeslev
 */
public class BotonControlador implements ActionListener{
    
    private JuegoSudokuControlador game;
    private JuegoSesion sesion;
    public BotonControlador(JuegoSesion game){
        this.sesion = game;
        this.game = game.getActual();
    }
    
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Nuevo")){
            game.newGame();       
        }
        else if (e.getActionCommand().equals("Revisar"))
            game.checkGame();
        else if (e.getActionCommand().equals("Guardar"))
            game.saveGame(sesion);
        else if (e.getActionCommand().equals("Resolver"))
            game.solveGame();
        else if (e.getActionCommand().equals("Activar Ayuda"))
            game.setHelp(((JCheckBox)e.getSource()).isSelected());
        else if (e.getActionCommand().equals("Terminar"))
            game.exit(sesion);
        else
            game.setSelectedNumber(Integer.parseInt(e.getActionCommand()));
    }
}
