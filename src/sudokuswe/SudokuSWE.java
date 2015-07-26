package sudokuswe;

import javax.swing.JFrame;
import presentacion.Login;

/**
 *
 * @author yarvis
 */
public class SudokuSWE {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Login login = new Login();
        login.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        login.setVisible(true);
    }
    
}
