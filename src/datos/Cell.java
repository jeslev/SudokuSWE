package datos;

import java.util.ArrayList;

/**
 *
 * @author yarvis
 */
public class Cell {

    private int valor,actual;
    private boolean[] candidatos;
    
    public Cell(){
        candidatos = new boolean[9];
        actual = 0;
        valor = 0;
        for(int i=0;i<9;i++) candidatos[i]=true;
    }
    
    public Cell(int val){
        candidatos = new boolean[9];
        actual = 0;
        valor = val;
        for(int i=0;i<9;i++) candidatos[i]=true;
    }
    
    public int getValor(){
        return valor;
    }
    
    public void setValor(int val){
        valor = val;
    }
    
    public void deleteCandidato(int val){
        candidatos[val-1]=false;
    }
    
    public int getCandidato(){
        for(int i=actual;i<9;i++) 
            if(candidatos[i]){
                actual++;
                return (i+1);
            }
        return -1;
    }
}
