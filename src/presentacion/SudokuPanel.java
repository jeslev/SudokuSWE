package presentacion;

import datos.UpdateAct;
import static datos.UpdateAct.CANDIDATES;
import static datos.UpdateAct.CHECK;
import static datos.UpdateAct.HELP;
import static datos.UpdateAct.NEW_GAME;
import static datos.UpdateAct.SELECTED_NUMBER;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import negocio.JuegoSudokuControlador;
import negocio.SudokuControlador;
//import negocio.ControlSudoku;

/**
 *
 * @author yarvis
 */
public class SudokuPanel extends JPanel implements Observer{
    
    private static final Color COLOR_CANDIDATE = new Color(102, 153, 255);
    
    private Field[][] fields;
    private JPanel[][] panels;      // Paneles (3x3).
   
    public SudokuPanel(){
        super(new GridLayout(3, 3));

        panels = new JPanel[3][3];
        for (int x= 0;x<3;x++) {
            for (int y=0;y<3;y++) {
                panels[x][y] = new JPanel(new GridLayout(3, 3));
                panels[x][y].setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
                add(panels[x][y]);
            }
        }

        fields = new Field[9][9];
        for (int x= 0;x<9;x++) {
            for (int y=0;y<9;y++) {
                fields[x][y] = new Field(x,y);
                fields[x][y].setText("");
                fields[x][y].setPreferredSize(new Dimension(30, 30));
                fields[x][y].setBorder(BorderFactory.createLineBorder(Color.GRAY));
                fields[x][y].setFont(new Font(Font.DIALOG, Font.PLAIN, 15));
                fields[x][y].setOpaque(true);
                panels[x / 3][y / 3].add(fields[x][y]);
            }
        }
    }
    
    @Override
    public void update(Observable o, Object arg){
        switch ((UpdateAct)arg) {
            case NEW_GAME:                
                setGame(((JuegoSudokuControlador)o).getGame());
                break;
            case CHECK:
                setGameCheck(((JuegoSudokuControlador)o));
                break;
            case SOLVE:
                setGame(((JuegoSudokuControlador)o).getSolution());
                break;
            case SELECTED_NUMBER:
            case CANDIDATES:
            case HELP:
                setCandidates((JuegoSudokuControlador)o);
                break;
            case EXIT:
                
                break;
        }
    }
    
    //Setea numero en celda
    /*public void setNumber(int number, boolean userInput,int x,int y) {
        setForeground(userInput ? Color.BLUE : Color.BLACK);
        fields[x][y].setText(number > 0 ? number + "" : "");
    }
    */
    private void setGameCheck(JuegoSudokuControlador game) {
        for (int x=0;x<9;x++) { 
            for (int y=0;y<9;y++) {
                fields[x][y].setBackground(Color.WHITE);
                if (fields[x][y].getForeground().equals(Color.BLUE))
                    fields[x][y].setBackground(game.isCheckValid(x, y) ? Color.GREEN : Color.RED);
                
            }
        }
    }
    
    public void setGame(int[][] game) {
        for(int x=0;x<9;x++) {
            for (int y=0;y<9;y++) {
                fields[x][y].setBackground(Color.WHITE);
                fields[x][y].setNumber(game[x][y], false);
            }
        }
    }
    
    public void setController(SudokuControlador sControl) {
        for (int x= 0; x< 3;x++) {
            for (int y= 0; y< 3;y++)
                panels[x][y].addMouseListener(sControl);
        }
    }
    
    private void setCandidates(JuegoSudokuControlador game) {
        for (int x=0;x<9;x++) {
            for (int y=0;y<9;y++) {
                fields[x][y].setBackground(Color.WHITE);
                if (game.isHelp() && game.isSelectedNumberCandidate(x, y))
                    fields[x][y].setBackground(COLOR_CANDIDATE);
            }
        }
    }
}
