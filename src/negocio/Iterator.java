package negocio;

import datos.JuegoSudoku;
import java.util.ArrayList;

/**
 *
 * @author jeslev
 */
public class Iterator {
    ArrayList<JuegoSudoku> lista;
    int cur;
    
    public Iterator(){
        cur = 0;
    }
    
    public void setList(ArrayList<JuegoSudoku> ldj){
        lista = ldj;
    }
    
    public JuegoSudoku sgte(){
        cur++;
        //System.out.println(cur+" "+lista.size());
        if(cur>=lista.size()) cur=0;
        return lista.get(cur);
    }
    
    public JuegoSudoku previo(){
        cur--;
        if(cur<0) cur=lista.size()-1;
        return lista.get(cur);
    }
    
    public JuegoSudoku actual(){
        return lista.get(cur);
    }
}
