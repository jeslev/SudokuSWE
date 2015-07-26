package negocio;

import datos.JuegoSudoku;
import datos.Sudoku;
import datos.UpdateAct;
import datos.Usuarios;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Observable;
import javax.swing.JOptionPane;

/**
 *
 * @author yarvis
 */
public class JuegoSudokuControlador extends Observable{

    public JuegoSudoku game;
    ArrayList<JuegoSudoku> juegosArchivo;
    
    public JuegoSudokuControlador(){
        game = new JuegoSudoku();
    }
    
    public JuegoSudoku getSudoku(){
        return game;
    }
    
    public void imprimirSudoku(){
        game.print();
    }
    
     /**Comandos Jugar **/
    public void newGame(){ 
        game.newGame();
        setChanged();
        notifyObservers(UpdateAct.NEW_GAME);
    }
    
    public void checkGame(){ 
        game.checkGame();
        setChanged();
        notifyObservers(UpdateAct.CHECK);
    }
    
    public void saveGame(JuegoSesion sesion){ 
        if(sesion.getLogin()){
            Usuarios usuario = new Usuarios();
            usuario.guardarPartida(sesion.getID(), game.getGame(),game.getSolution(),0,game.getPuntaje(),game.getTime() );
            JOptionPane.showMessageDialog(null, "Partida guardada!");
        }else{
            JOptionPane.showMessageDialog(null, "No esta registrado!");
        } 
    }
    
    public void saveGame(JuegoSudoku sudoku, JuegoSesion sesion){ 
        if(sesion.getLogin()){
            Usuarios usuario = new Usuarios();
            usuario.guardarPartida(sesion.getID(), sudoku.getGame(),sudoku.getSolution(),0,sudoku.getPuntaje(),sudoku.getTime() );
            JOptionPane.showMessageDialog(null, "Partida guardada!");
        }else{
            JOptionPane.showMessageDialog(null, "No esta registrado!");
        } 
    }
    
    public void solveGame(){ 
        setChanged();
        notifyObservers(UpdateAct.SOLVE); 
    }
    
    public void setHelp(boolean stat){ 
        game.setHelp(stat); 
        setChanged();
        notifyObservers(UpdateAct.HELP);
    }
    
    public void exit(JuegoSesion sesion){ 
        if(sesion.getLogin()){
            sesion.archivarJuego();
            Usuarios usuario = new Usuarios();
            usuario.guardarPartida(sesion.getID(), game.getGame(),game.getSolution(),1,game.getPuntaje(),game.getTime());
            JOptionPane.showMessageDialog(null, "Partida guardada!");
        }else{
            sesion.archivarJuego();
        }
        setChanged();
        notifyObservers(UpdateAct.EXIT);
    }
    
    public void terminar(){ game.terminar(); }
    public double getTime() { return game.getTime();}
    public int getPuntaje() { return game.getPuntaje();}
    
    public void setSelectedNumber(int snum){ 
        game.setSelectedNumber(snum);
        setChanged();
        notifyObservers(UpdateAct.SELECTED_NUMBER);
    }
    /****/
    
    public int getGameNumber(int x,int y){
        return game.getGameNumber(x, y);
    }
    
    public int getSelectedNumber(){
        return game.getSelectedNumber();
    }
    
    public void setNumber(int x,int y,int num){ game.setGameNumber(x,y,num);}
    
    public int[][] getGame(){return game.getGame(); }
    
    public int[][] getSolution() { return game.getSolution(); }
    
    public boolean isCheckValid(int x,int y) { return game.isCheckValid(x,y);}

    public boolean isHelp() { return game.isHelp();}
    
    public boolean isSelectedNumberCandidate(int x,int y) { return game.isSelectedNumberCandidate(x, y);}



    /**Resolucion de sudokus**/
    public void leerArchivo(String fileName){
        juegosArchivo = new ArrayList<JuegoSudoku>();
        try{
            BufferedReader br = new BufferedReader(new FileReader(fileName));
            String line ;
            int cnt = 0;
            int[][] juegotmp = new int[9][9];
            while((line = br.readLine()) != null){
                char[] linec = line.toCharArray();
                if(linec[0]=='G'){
                    cnt=0;
                }else{
                    for(int i=0;i<9;i++){
                        juegotmp[cnt][i]=linec[i]-'0';
                        
                    }
                    if(cnt==8) {
                        int[][] tt = new int[9][9];
                        for(int i=0;i<9;i++) for(int j=0;j<9;j++) tt[i][j]=juegotmp[i][j];
                        JuegoSudoku dj = new JuegoSudoku(tt);
                        juegosArchivo.add(dj);                       
                    }
                    cnt++;
                }
            }
        }catch(Exception ex){
        
        }
        
    }
    
    public void resolverJuegos(){
        
        for(int i=0;i<juegosArchivo.size();i++){
            System.out.println("juego "+i);
            JuegoSudoku dj = juegosArchivo.get(i);
            Sudoku game = dj.getSudokuGame();
            //for(int m=0;m<9;m++) for(int n=0;n<9;n++)System.out.print(game[m][n]);
            //System.out.println();
            Sudoku tmpgame = dj.copy(game);
            //int[] sudokuLine = new int[81];
            //int cnt=0;
            //for(int p=0;p<9;p++) for(int q=0;q<9;q++) sudokuLine[cnt++]=game[p][q];
            //dj.setSolution(game);
            tmpgame = dj.generateSolution(tmpgame,0);
            if(tmpgame==null) System.out.println("No solution");
            /*else{
             for(int m=0;m<9;m++) for(int n=0;n<9;n++)System.out.print(tmpgame[m][n]);
            }*/
            if(tmpgame==null) dj.setSudokuSolution(game);
            else dj.setSudokuSolution(tmpgame);
        }
    }
    
    public ArrayList<JuegoSudoku> getJuegosArchivo(){
        return juegosArchivo;
    }
    /***/
}
