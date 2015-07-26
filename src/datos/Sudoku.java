package datos;

/**
 *
 * @author yarvis
 */
public class Sudoku {

    private Cell[][] matriz;
    
    public Sudoku(){
        matriz = new Cell[9][9];
        for(int i=0;i<9;i++) for(int j=0;j<9;j++)
            matriz[i][j] = new Cell();
            
    }
    
    public Sudoku(int mat[][]){
        matriz = new Cell[9][9];
        for(int i=0;i<9;i++) for(int j=0;j<9;j++)
            matriz[i][j] = new Cell(mat[i][j]);
            
    }
    
    public int getValor(int x,int y){
        return matriz[x][y].getValor();
    }
    
    public void setValor(int x,int y,int val){
        matriz[x][y].setValor(val);
    }
    
    public int getCandidato(int x,int y){
        return matriz[x][y].getCandidato();
    }
    
    public void deleteCandidato(int x,int y,int val){
        matriz[x][y].deleteCandidato(val);
    }
    
    public int[][] getMatriz(){
        int[][] mat = new int[9][9];
        for(int i=0;i<9;i++) for(int j=0;j<9;j++) mat[i][j] = matriz[i][j].getValor();
        return mat;
    }
    
    public void print(){
        for(int i=0;i<9;i++){
            for(int j=0;j<9;j++) System.out.print(matriz[i][j].getValor());
            System.out.println();
        }
    }
}
