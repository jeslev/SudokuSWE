package datos;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 *
 * @author yarvis
 */
public class JuegoSudoku {

    private Sudoku game;
    private Sudoku solution;
    private boolean[][] check;
    private int selectedNumber;     
    private boolean help;
    private double time_start;
    private double time_end;
    private double totalTime;
    private int puntaje;
    private int estado;
    private Date date;
    
    public JuegoSudoku(){
        game = new Sudoku();
        solution = new Sudoku();
        check = new boolean[9][9];
        puntaje = 0;
        estado = 0;
        time_end=time_start=0;
    }
    
    public JuegoSudoku(int [][] mat){
        game = new Sudoku(mat);
        solution = new Sudoku(mat);
        check = new boolean[9][9];
        puntaje = -1;
        estado = 0;
        time_end=time_start=0;
    }
    
    public Sudoku getSudokuGame() { return game;}
    public void setSudokuSolution(Sudoku sol) { solution=sol;}
    
    public int getGameNumber(int x,int y){
        return game.getValor(x, y);
    }
    
    public int getSelectedNumber(){ return selectedNumber;}

    public void setGameNumber(int x,int y,int num){ game.setValor(x, y, num);}
    
    public int[][] getGame(){ return game.getMatriz();  }
    
    public int[][] getSolution(){ return solution.getMatriz();  }
    
    public boolean isCheckValid(int x,int y) { return check[x][y];}
    
    public boolean isHelp() {   return help;   }
    
    public boolean isSelectedNumberCandidate(int x, int y) {
        int num = game.getValor(x, y);
        game.setValor(x, y,selectedNumber);
        boolean val = isPossibleNumber(game, x, y);
        game.setValor(x, y, num);
        return num==0 && val;
    }
    
    public void setPtje(int n) {puntaje=n;}
    
    
    public int getPuntaje(){
        int tot = 0;
        for(int i=0;i<9;i++) for(int j=0;j<9;j++) if(solution.getValor(j, j)==game.getValor(j, j)) tot++;
        puntaje = tot;
        return tot;
    }
    
    public double getTime(){
        totalTime= (time_end-time_start)/1000;
        if(time_end==0.0) totalTime=-1.0;
        return totalTime;
    }
    public void setTime(double time){ totalTime=time;}
    
    public void setDate(Date dt) {date=dt;}
    
    public String getDate() {return date.toString();}
    
    public void generarJuego(){
        solution = copy(generateSolution(new Sudoku(),0));
        //if(solution!=null) System.out.println("Termino generacion");
        game = copy(generateGame(copy(solution)));
    }
    
    public Sudoku generateSolution(Sudoku game, int index){
        
        for(int i=index;i<81;i++){
            int tx = index/9;
            int ty = index%9;
            if(game.getValor(tx, ty)==0) {index=i; break;}
        }
        
        if(index>80) return game;
        int x = index/9;
        int y = index%9;
        
        List<Integer> numbers = new ArrayList<Integer>();
        for (int i=1;i<=9;i++) numbers.add(i);
        Collections.shuffle(numbers);
        
        while(numbers.size()>0){
            int number = getNextPossibleNumber(game,x,y,numbers);
            if(number==-1) { game.setValor(x, y, 0); break; }
            
            Sudoku tmpgame = generateSolution(game,index+1);
            if(tmpgame!=null) return tmpgame;
            
            game.setValor(x, y, 0);
        }
        return null;
    }
    
    private int getNextPossibleNumber(Sudoku game, int x, int y, List<Integer> numbers) {
        while (numbers.size() > 0) {
            int number = numbers.remove(0);
            game.setValor(x, y, number);
            if (isPossibleX(game, x,y, number) &&
                    isPossibleY(game, x,y, number) && 
                    isPossibleBlock(game, x, y, number))
                return number;
        }
        return -1;
    }
    
    private boolean isPossibleNumber(Sudoku game, int x, int y) {
        int number = game.getValor(x, y);
        if (isPossibleX(game, x,y,number) && 
                isPossibleY(game, x,y,number) && 
                isPossibleBlock(game, x, y,number))
                return true;
        return false;
    }
    
    private boolean isPossibleX(Sudoku game, int x, int y0, int number) {
        for(int y=0;y<9;y++) {
            if ( (game.getValor(x, y)==number) && (y!=y0) )
                return false;
        }
        return true;
    }
    
    private boolean isPossibleY(Sudoku game, int x0, int y, int number) {
        for(int x=0; x<9;x++) {
            if ( (game.getValor(x, y)==number) && (x!=x0) )
                return false;
        }
        return true;
    }
    
    private boolean isPossibleBlock(Sudoku game, int x, int y, int number) {
        int x1 = ((x/3)*3);
        int y1 = ((y/3)*3);
        for (int xx=x1;xx<x1+3;xx++) {
            for (int yy=y1;yy<y1+3;yy++) {
                if ( (game.getValor(xx, yy)==number) && (xx!=x) && (yy!=y) )
                    return false;
            }
        }
        return true;
    }
    
    private Sudoku generateGame(Sudoku game) {
        List<Integer> positions = new ArrayList<Integer>();
        for (int i = 0; i < 81; i++) positions.add(i);
        Collections.shuffle(positions);
        return generateGame(game, positions);
    }
    
    private Sudoku generateGame(Sudoku game, List<Integer> positions) {
        while (positions.size() > 0) {
            int position = positions.remove(0);
            int x = position / 9;
            int y = position % 9;
            int temp = game.getValor(x, y);
            game.setValor(x, y, 0);

            if (!isValid(game)) game.setValor(x, y, temp);
        }

        return game;
    }
    
    private boolean isValid(Sudoku game) {
        return isValid(game, 0, new int[] { 0 });
    }
    
    private boolean isValid(Sudoku game, int index, int[] numberOfSolutions) {
        if (index > 80)
            return ++numberOfSolutions[0] == 1;

        int x = index / 9;
        int y = index % 9;

        if (game.getValor(x, y) == 0) {
            
            List<Integer> numbers = new ArrayList<Integer>();
            for (int i = 1; i <= 9; i++) numbers.add(i);
            
            while(numbers.size() > 0){
                int number = getNextPossibleNumber(game,x,y,numbers);
                if(number==-1) { game.setValor(x, y, 0); break; }

                if (!isValid(game, index + 1, numberOfSolutions)) {
                    game.setValor(x, y, 0);
                    return false;
                }
                game.setValor(x, y, 0);
            }
            
        } else if (!isValid(game, index + 1, numberOfSolutions))
            return false;

        return true;
    }

    
    public Sudoku copy(Sudoku game) {
        Sudoku copy = new Sudoku();
        for (int x=0;x<9;x++) {
            for (int y=0;y<9;y++)
                copy.setValor(x, y, game.getValor(x, y));
        }
        return copy;
    }
    
    public void print(){
        System.out.println("Game");
        game.print();
        System.out.println("Solution");
        solution.print();
    }
    
    
    /** Comandos Jugar **/
    public void newGame(){
        generarJuego();
        time_start = System.currentTimeMillis();
        //print();
    }
    
    public void checkGame(){
        for(int x=0;x<9;x++) {
            for (int y=0;y<9;y++)
                check[x][y] = (game.getValor(x, y) == solution.getValor(x, y));
        }
        selectedNumber=0;
        
    }
    
    public void setHelp(boolean stat){
        this.help = stat;
    }
    
    public void terminar(){
        time_end= System.currentTimeMillis();
    }

    public void setSelectedNumber(int snum){
        this.selectedNumber = snum;
    }
}
