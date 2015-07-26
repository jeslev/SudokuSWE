
package datos;

import java.util.Comparator;
/**
 *
 * @author yarvis
 */
public class ComparatorJuego implements Comparator{
    
    

    @Override
    public int compare(Object o1, Object o2) {
        JuegoSudoku d1 = (JuegoSudoku)o1;
        JuegoSudoku d2 = (JuegoSudoku)o2;
        if(d1.getPuntaje()==d2.getPuntaje()){
            double res = d2.getTime()-d1.getTime();
            if(res>0) return 1;
            else return -1;
        }
        return d2.getPuntaje()-d1.getPuntaje();    
    }
}
