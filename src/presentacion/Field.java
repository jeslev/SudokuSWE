package presentacion;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import static javax.swing.SwingConstants.CENTER;

/**
 *
 * @author jeslev
 */
public class Field extends JLabel{
        
    private int x,y;      
    
    public Field(int x, int y) {
        this.x = x;
        this.y = y;        
    }

    public void setNumber(int number, boolean userInput) {
        setForeground(userInput ? Color.BLUE : Color.BLACK);
        setText(number > 0 ? number + "" : "");
    }

    public int getFieldX() {
        return x;
    }

    public int getFieldY() {
        return y;
    }
}
