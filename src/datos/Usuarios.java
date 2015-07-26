package datos;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author jeslev
 */
public class Usuarios {
    
    String url = "jdbc:mysql://127.0.0.1:3306/mydb";
    Connection con;
    
    public Usuarios(){
        try{
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(url,"root", "root");
        }catch(Exception ex){
            System.out.println("No se pudo conectar a la base de datos!");
            System.out.println(ex.toString());
        }
    }
    
    public int login(String user, String pass){
        int id=-1;
        try{
            ResultSet res = null;
            String query = "SELECT * FROM usuarios WHERE nombre='"+user+"' and pass='"+pass+"'";
            Statement st = con.createStatement();
            res = st.executeQuery(query);
            while(res.next()){
                id = res.getInt(1);
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        return id;
    }
    
    public int crearUsuario(String name, String pass,String pass2){
        int res=-1;
        if(!pass.equals(pass2)) return res;//res = "Passwords no coinciden!";
        else{
            //si existe usuarios revisar
            //sino
            int id=-1;
            String query = "INSERT INTO usuarios (nombre, pass) VALUES (?, ?)";
            try{
                PreparedStatement stmt = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, name);
                stmt.setString(2, pass);

                stmt.executeUpdate();
                ResultSet genKey = stmt.getGeneratedKeys();
                if(genKey.next()){
                    id = genKey.getInt(1);
                }
            }catch(Exception ex){
                ex.printStackTrace();
            }
            res = id;
            //res = "Usuario agregado correctamente!";
        }
        return res;
    }

    
    public void guardarPartida(int ID, int[][] juego,int[][] sol, int estado,int ptje, double time){
        String txtgame="",solgame="";
        for(int i=0;i<9;i++) for(int j=0;j<9;j++) txtgame+= ""+juego[i][j];
        for(int i=0;i<9;i++) for(int j=0;j<9;j++) solgame+= ""+sol[i][j];
        
        String query = "INSERT INTO mydb.partidas (juego, puntaje, tiempo, estado, fecha, usuarios_id,solucion) VALUES (?,?, ?, ?, CURRENT_DATE, ?,?)";
        try{
                PreparedStatement stmt = con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, txtgame);
                stmt.setInt(2, ptje);
                stmt.setDouble(3, time);
                stmt.setInt(4,estado);
                stmt.setInt(5,ID);
                stmt.setString(6, solgame);
                stmt.executeUpdate();
                ResultSet genKey = stmt.getGeneratedKeys();
                
            }catch(Exception ex){
                ex.printStackTrace();
            }
    }
    
    public ArrayList<JuegoSudoku> obtenerPartidasTotal(){
        ArrayList<JuegoSudoku> ret = new ArrayList<JuegoSudoku>();
        try{
            ResultSet res = null;
            String query = "SELECT * FROM partidas WHERE estado='1'";
            Statement st = con.createStatement();
            res = st.executeQuery(query);
            while(res.next()){
                
                String j1 = res.getString(2);
                int j2 = res.getInt(3);
                double time = res.getDouble(4);
                Date date = res.getDate(6);
                String j4 = res.getString(8);
                
                int[][] tmpgame= new int[9][9];
                char[] linec = j1.toCharArray();
                int cnt = 0;
                for(int i=0;i<9;i++) for(int j=0;j<9;j++)
                        tmpgame[i][j]=linec[cnt++]-'0';
                        
                int[][] solgame= new int[9][9];
                char[] lines = j4.toCharArray();
                cnt = 0;
                for(int i=0;i<9;i++) for(int j=0;j<9;j++)
                        solgame[i][j]=lines[cnt++]-'0';
                
                JuegoSudoku tmp = new JuegoSudoku(tmpgame);
                tmp.setPtje(j2); tmp.setTime(time); tmp.setDate(date);
                //tmp.setSolution(solgame);
                ret.add(tmp);
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        return ret;
    }
    
    public ArrayList<JuegoSudoku> obtenerPartidasUsuario(int id){
        ArrayList<JuegoSudoku> ret = new ArrayList<JuegoSudoku>();
        try{
            ResultSet res = null;
            String query = "SELECT * FROM partidas WHERE usuarios_id='"+id+"' and estado='1'";
            Statement st = con.createStatement();
            res = st.executeQuery(query);
            while(res.next()){
                
                String j1 = res.getString(2);
                int j2 = res.getInt(3);
                double time = res.getDouble(4);
                Date date = res.getDate(6);
                String j4 = res.getString(8);
                
                int[][] tmpgame= new int[9][9];
                char[] linec = j1.toCharArray();
                int cnt = 0;
                for(int i=0;i<9;i++) for(int j=0;j<9;j++)
                        tmpgame[i][j]=linec[cnt++]-'0';
                
                int[][] solgame= new int[9][9];
                char[] lines = j4.toCharArray();
                cnt = 0;
                for(int i=0;i<9;i++) for(int j=0;j<9;j++)
                        solgame[i][j]=lines[cnt++]-'0';
                
                JuegoSudoku tmp = new JuegoSudoku(tmpgame);
                tmp.setPtje(j2); tmp.setTime(time); tmp.setDate(date);
                //tmp.setSolution(solgame);
                ret.add(tmp);
                
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        return ret;
    }
    
    
}
