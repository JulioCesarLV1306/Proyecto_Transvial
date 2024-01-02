package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Conexion {
    Connection con;
     public Connection getConnection(){
         String url="jdbc:mysql://localhost:3306/proyectotransvial";
         String user="root";
         String pass="";
         try {
             Class.forName("com.mysql.cj.jdbc.Driver");
             con=DriverManager.getConnection(url,user,pass);
             System.out.println("SE conecto");
         } catch (Exception e) {
             System.err.println("no");
         }
        return con;
     
     }
      public void desconectar(){
        try {
            con.close();
            
        } catch (SQLException ex) {
           
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
   }
      /*public static void main(String[] args) {
        
        Conexion conexion = new Conexion();
        Connection con = conexion.getConnection();

        if (con != null) {
            System.out.println("Conexión exitosa a la base de datos");
            // Aquí puedes realizar otras operaciones con la base de datos
        } else {
            System.out.println("Error al conectar a la base de datos");
        }

        // Finalmente, cierra la conexión
        conexion.desconectar();
    
    }*/
}
