/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

//procedimientos
import DataBase.getConexion;
import static DataBase.getConexion.getConexion;
import Formularios.RegistrarVehiculo;
import static Formularios.RegistrarVehiculo.ps;
import com.sun.org.apache.regexp.internal.recompile;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author tienda
 */
public class ConsultaSql {
    
    public static getConexion conexion = new getConexion();
    
    public static PreparedStatement ps;
    public static ResultSet resultado;
    public static ResultSet resultado1;
    public static String sql;
    public static Statement st;
    public static int rsnum = 0;
 
//------------------------------------------------------------------------------------------------------     
    //Metodo para registrar usuarios con 3 parametros
    public int guardar(String nombre, String contraseña, String tipo){
        
        int resultado = 0; //Variable entera
        Connection conexion = null; //Varibale conexion para llamar la conexion ya creada
        
        String sentencia_save=("INSERT INTO moreuser(nombre, contra, tipo) VALUES(?,?,?)"); //Variable de tipo String contiene nuestra consulta SQL
        try {
            //Conectamos con nuestra clase getConexion
            conexion = getConexion.getConexion();
            ps = conexion.prepareStatement(sentencia_save); //Ejecutamos la consulta SQL 
            //insertamos los datos tal como los definimos en la creacion de las tablas
            ps.setString(1, nombre);
            ps.setString(2, contraseña);
            ps.setString(3, tipo);
            resultado = ps.executeUpdate(); //Resultado tiene valor o no
            ps.close(); //Cierra la consulta
           conexion.close(); //Cierra conexion
        
        } catch (Exception e) {
            System.out.println(e); //Este nos va a arrojar un error en la consulta
        }
        return resultado; //Retorna el valor
    }

//------------------------------------------------------------------------------------------------------ 
    
     //Metodo para seleccionar el nombre del usuario
    public static String buscarname(String name){
           String busqueda =  null;
           Connection conexion = null; //Varibale conexion para llamar la conexion ya creada
           
           try {
               conexion = getConexion.getConexion();
               String sentencia_busqueda = ("SELECT nombre FROM moreuser WHERE nombre= '"+ name +"'"); //Consulta para ver el registro de los usuarios
               ps = conexion.prepareStatement(sentencia_busqueda); //Preparamos la conexion con la consulta
               resultado = ps.executeQuery(); //Ejecutamos la consulta
               //Condicional para verificar que el usuario se muestre
               if(resultado.next()){
                   String nombre = resultado.getString("nombre");
                   busqueda = (nombre);
               }
               conexion.close(); //Cerrar la conexion
               
           } catch (Exception e) {
           System.out.println(e); //Imprime error en la consulta
           }
           return busqueda;//Nos retorna la busqueda
       } 

//------------------------------------------------------------------------------------------------------ 
    
     //Metodo publico para buscar usuario y saber si esta en la base de datos. 
    public static String buscaruser(String tipo, String contraseña){
           String busquedau = null;
           Connection conexion = null; //Varibale conexion para llamar la conexion ya creada
           try {
               conexion = getConexion.getConexion();//Ejecutamos la consulta SQL 
               //Variable que almacena la consulta SQL 
               String sentencia_busquedau = ("SELECT nombre, contra, tipo FROM moreuser WHERE tipo = '"+tipo+"' && contra = '"+contraseña+"'");
               ps = conexion.prepareStatement(sentencia_busquedau); //Prepara la consulta para despues ejecutarla.
               resultado = ps.executeQuery();//Ejecuta la consulta almacenada en ps
               //Condicional para validar si un usuario existe o no en la base de datos.
               if(resultado.next()){
                   busquedau = "Usuario Encontrado.";
               }else{
                   busquedau = "Usuario no encontrado.";
               }
               conexion.close();
           } catch (Exception e) {
               System.out.println(e); //Mensaje de error si no se hace la consulta
           }
           return busquedau;//Retorna la busqueda, hace un bucle.
       }
        
//------------------------------------------------------------------------------------------------------ 
    
    //Metodo para mostrar una tabla
    public DefaultTableModel buscarPersonas(String buscar)
    {
        Connection conexion = null;
        
        int contador = 1; // Dedicado para acomular en número de registros que hay en la tabla

        String []  nombresColumnas = {"id","placa","fecha","Hora_Entrada","Costo","Tipo","Descuento","TipoES"};//Indica el nombre de las columnas en la tabla
        
        String [] registros = new String[8]; //Arreglo con el tamaño de campos de tabla
      
        DefaultTableModel modelo = new DefaultTableModel(null, nombresColumnas); //DEfinimos un modelo y la instanciamos 
        
        String sql = "SELECT * FROM registro WHERE id_registro LIKE '%"+buscar+"%' OR No_placa LIKE '%"+buscar+"%'"; //Variable que almacena la consulta
        
        //Connection cn = null;
        try
        {
           conexion = getConexion.getConexion();//Llamamos nuestra conexion y la almacenamos en la variable conexion
            
            ps = conexion.prepareStatement(sql);     //preparamos la consulta en ps(PreparentStatement)                  
            
            resultado = ps.executeQuery(); //Ejecutamos dicha consulta, y se alamacena en resultado.
            //Estructura repetitiva para mostar los n campos de nuestra tabla.
            while(resultado.next()) 
            {
                registros[0] = resultado.getString(1);
                
                registros[1] = resultado.getString(2);
                
                registros[2] = resultado.getString(3);
               
                registros[3] = resultado.getString(4);  
                
                registros[4] = resultado.getString(5);
                
                registros[5] = resultado.getString(6);
                
                registros[6] = resultado.getString(7);
                
                registros[7] = resultado.getString(8);
                
                modelo.addRow(registros); //Me lo agrega al modelo
                
                contador++; //incrementa el contador
                
            }
            conexion.close();
        }
        catch(SQLException e)
        {
            
            JOptionPane.showMessageDialog(null,"Error al conectar. "+e.getMessage()); //Error al conectar
            
        }
        finally
        {
            try
            {   //Valida que estos sean diferentes datos
                if (resultado != null) resultado.close();
                
                if (ps != null) ps.close();
                
                if(conexion != null) conexion.close();
             
            }
            catch(SQLException e)
            {
                JOptionPane.showMessageDialog(null,e);
            }
        }
         return modelo; //Retorna al modelo es decir a la tabla.
    }
    
//------------------------------------------------------------------------------------------------------   
    
   //Metodo publico para buscar el lugar ocupado y saber si esta en la base de datos. 
    public static int buscarLugar(int estado){
           int busqueda =  0;
           Connection conexion = null; //Varibale conexion para llamar la conexion ya creada
           
           try {
               conexion = getConexion.getConexion();
               String sentencia_busqueda = ("SELECT estado FROM estacion_1 WHERE estado= '"+ estado +"'"); //Consulta para ver el registro de los usuarios
               ps = conexion.prepareStatement(sentencia_busqueda); //Preparamos la conexion con la consulta
               resultado = ps.executeQuery(); //Ejecutamos la consulta
               //Condicional para verificar que el usuario se muestre
              /* if(resultado.next()){
                   String nombre = resultado.getString("estado");
                   busqueda = (nombre);
               }*/
               
               
           } catch (Exception e) {
           System.out.println(e); //Imprime error en la consulta
           }
          return busqueda;//Nos retorna la busqueda
       } 

//------------------------------------------------------------------------------------------------------ 
    
    //METODO PARA ELIMINAR AUTOS
        public void borrarCocheras(String placa) {
       Connection conexion = null; //Varibale conexion para llamar la conexion ya creada
            try {
                conexion = getConexion.getConexion();//Ejecutamos la consulta SQL 
                
                //COMENTE ESTE CODIGO PARA NO ELIMINAR LOS REGISTROS DE MI BASE DE DATOS
                
              //Variable que almacena la consulta SQL 
              /*  String delete = ("DELETE FROM registro WHERE No_placa = '" + placa + "'");
               ps = conexion.prepareStatement(delete); //Preparamos la conexion con la consulta
              int res = ps.executeUpdate(); //Ejecutamos la consulta
               //Condicional para verificar que el usuario se muestre
               if(res>0){
                   JOptionPane.showMessageDialog(null, "VEHICULO RETIRADO.");
               }
              
               conexion.close();*/
                JOptionPane.showMessageDialog(null, "VEHICULO RETIRADO.");
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"Error Vuelve a intentarlo."+e.getMessage()); //Error al conectar
            }
            
    }

//------------------------------------------------------------------------------------------------------ 
        
        //METODO PARA CALCULAR LAS TARIFAS 
        public double calcularImporte(double costo, long entrada, long salida) {
        double tiempo = salida - entrada;
        double segundos = tiempo / 1000;
        double minutos = segundos / 60;
        double horas = minutos / 60;
        if (horas < 1) {
            horas = 1; // aunque sea se cobra una hora que es el minimo
        }
        double importe = costo * horas;
        JOptionPane.showMessageDialog(null, importe);
        return importe;
    }
        
//------------------------------------------------------------------------------------------------------ 
        
        //METODO PARA ELIMINAR USUARIOS
        public void borrarUser(String Nombre) {
       Connection conexion = null; //Varibale conexion para llamar la conexion ya creada
            try {
                conexion = getConexion.getConexion();//Ejecutamos la consulta SQL 
               //Variable que almacena la consulta SQL 
               String delete = ("DELETE FROM moreuser WHERE nombre = '" + Nombre + "'");
               ps = conexion.prepareStatement(delete); //Preparamos la conexion con la consulta
              int res = ps.executeUpdate(); //Ejecutamos la consulta
               //Condicional para verificar que el usuario se muestre
               if(res>0){
                   JOptionPane.showMessageDialog(null, "USUARIO ELIMINADO");
               }
               conexion.close();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null,"ERROR: VUELVE A INTENTARLO"+e.getMessage()); //Error al conectar
            }   
    }
 //------------------------------------------------------------------------------------------------------ 
        
}
