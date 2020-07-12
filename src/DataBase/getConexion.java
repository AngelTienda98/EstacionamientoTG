package DataBase;

//Clases de la libreria JDBC-MYSQL y predefinidas en java.
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

//CLASE PARA DEFINIR LA CONEXION JAVA - MYSQL
public class getConexion {
    //VARIABLES GLOBALES
    private static final String user="a6352c_estacio"; //Usuario AppServer
    private static final String pas="admin1234"; //Contraseña AppServer
    private static final String url="jdbc:mysql://mysql5014.site4now.net/db_a6352c_estacio"; //Conexion con la libreria JDBC y MYSQL en la ruta. Estacion es el nombre DataBase
  //  PreparedStatement ps; //Variable PrepareDStatement
  //  ResultSet rs; //Variable ResiltSet 
    
    //METODO CONSTRUCTOR
    public static Connection getConexion(){
        
        Connection cn = null; //Declaracion Variable null
        try {
            Class.forName("com.mysql.jdbc.Driver"); //Importacion de los drivers para la conexion
            cn = DriverManager.getConnection(url,user,pas); //Llamamos a las variables globales.
           //JOptionPane.showMessageDialog(null,"Conexion establecida..!");
        } catch (Exception e) {
            System.out.println(e); //Conexion fallida es una exception.
        }
        return cn; //Retorna la coneccion.
    }
}
