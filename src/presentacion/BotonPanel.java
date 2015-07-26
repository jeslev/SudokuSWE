package presentacion;

import datos.UpdateAct;
import static datos.UpdateAct.CHECK;
import static datos.UpdateAct.NEW_GAME;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import negocio.BotonControlador;
//import negocio.ControlBoton;

/**
 * This class draws the button panel and reacts to updates from the model.
 *
 * @author Eric Beijer
 */
public class BotonPanel extends JPanel implements Observer {
    JButton btnNew, btnCheck, btnExit, btnSave, btnSolve;
    JCheckBox cbHelp;               
    ButtonGroup bgNumbers;          
    JToggleButton[] btnNumbers;     

    
    public BotonPanel() {
        super(new BorderLayout());

        JPanel pnlAlign = new JPanel();
        pnlAlign.setLayout(new BoxLayout(pnlAlign, BoxLayout.PAGE_AXIS));
        add(pnlAlign, BorderLayout.CENTER);

        JPanel pnlOptions = new JPanel(new FlowLayout(FlowLayout.LEADING));
        pnlOptions.setBorder(BorderFactory.createTitledBorder(" Menu "));
        pnlAlign.add(pnlOptions);

        btnNew = new JButton("Nuevo");
        btnNew.setFocusable(false);
        pnlOptions.add(btnNew);

        btnCheck = new JButton("Revisar");
        btnCheck.setFocusable(false);
        pnlOptions.add(btnCheck);

        btnSave = new JButton("Guardar");
        btnSave.setFocusable(false);
        pnlOptions.add(btnSave);
        
        btnSolve = new JButton("Resolver");
        btnSolve.setFocusable(false);
        pnlOptions.add(btnSolve);
        
        
        btnExit = new JButton("Terminar");
        btnExit.setFocusable(false);
        pnlOptions.add(btnExit);

        JPanel pnlNumbers = new JPanel();
        pnlNumbers.setLayout(new BoxLayout(pnlNumbers, BoxLayout.PAGE_AXIS));
        pnlNumbers.setBorder(BorderFactory.createTitledBorder(" Numeros "));
        pnlAlign.add(pnlNumbers);

        JPanel pnlNumbersHelp = new JPanel(new FlowLayout(FlowLayout.LEADING));
        pnlNumbers.add(pnlNumbersHelp);

        cbHelp = new JCheckBox("Activar Ayuda", true);
        cbHelp.setFocusable(false);
        pnlNumbersHelp.add(cbHelp);

        JPanel pnlNumbersNumbers = new JPanel(new FlowLayout(FlowLayout.LEADING));
        pnlNumbers.add(pnlNumbersNumbers);

        bgNumbers = new ButtonGroup();
        btnNumbers = new JToggleButton[9];
        for (int i = 0; i < 9; i++) {
            btnNumbers[i] = new JToggleButton();
            btnNumbers[i].setText((i+1)+"");
            btnNumbers[i].setPreferredSize(new Dimension(30, 30));
            btnNumbers[i].setFocusable(false);
            Border border = new LineBorder(Color.GRAY, 1);
            btnNumbers[i].setBorder(border);
            bgNumbers.add(btnNumbers[i]);
            pnlNumbersNumbers.add(btnNumbers[i]);
        }
    }

    /**
     * Method called when model sends update notification.
     *
     * @param o     The model.
     * @param arg   The UpdateAction.
     */
    public void update(Observable o, Object arg) {
        switch ((UpdateAct)arg) {
            case NEW_GAME:
            case CHECK:
                bgNumbers.clearSelection();
                break;
        }
    }

    /**
     * Adds controller to all components.
     *
     * @param buttonController  Controller which controls all user actions.
     */
    public void setController(BotonControlador buttonController) {
        btnNew.addActionListener(buttonController);
        btnCheck.addActionListener(buttonController);
        btnSave.addActionListener(buttonController);
        btnSolve.addActionListener(buttonController);
        btnExit.addActionListener(buttonController);
        cbHelp.addActionListener(buttonController);
        for (int i = 0; i < 9; i++)
            btnNumbers[i].addActionListener(buttonController);
    }
}