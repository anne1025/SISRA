
package Modelo.DAOs;

import Modelo.Prenda;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



public class PrendaDAO {
    
    private final AccesoBD fachada;
    
    
    public PrendaDAO()
    {
        fachada = new AccesoBD();
    }

    public boolean existePrenda(String codigo) {
        String sql = "SELECT * FROM prenda WHERE codigo = '"+codigo+"';";
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(sql);
            if(resultado.next()){
                return true;
            }
        } catch (Exception e) {
        }
        return false;
    }

    public boolean guardarPrenda(Prenda prenda) {
        String sqlInsert, sqlLlave;
        ResultSet resultado = null;
 
        sqlInsert = "INSERT INTO prenda VALUES ('"+prenda.getCodigo()+"','"+prenda.getNombre()+"','"+prenda.getTipo()+"','"+prenda.getCantidad()+"','"+prenda.getPrecio()+"');";
        sqlLlave = "SELECT * FROM prenda WHERE codigo = '"+prenda.getCodigo()+"';";
        
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();           
            resultado = sentencia.executeQuery(sqlLlave);
            if(resultado.next()){
                System.out.println("Ya existe una prenda con el mismo codigo");
                return false;              
            }
            else{
                sentencia.executeUpdate(sqlInsert);
                return true;
                }
            }catch (SQLException ex) {
            System.out.println("Excepción en insertar prenda");
            ex.printStackTrace();            
        }
        return false;
    }

    public boolean modificarPrenda(String codigo, String nombre, String tipo, String cantidad, String precio) {
        String sql_updatePer;
        sql_updatePer = "UPDATE prenda SET nombre = '"+nombre+"', codigo = '"+codigo+"', tipo = '"
                +tipo+"', cantidad = '"+cantidad+"', precio = '"+precio+"' WHERE codigo = '"+codigo+"';";
        
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            sentencia.executeUpdate(sql_updatePer);

            return true;
        }catch (SQLException ex) {
            System.out.println("Excepción en modificar prenda");
        }
        return false;
    }

  public ArrayList <String> mostrarPrenda(String codigo)
    {
        ArrayList<String> prenda = new ArrayList<>();
        String SQL;
        SQL = "SELECT * FROM prenda WHERE codigo = '"+codigo+"';";
             
        try {            
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();            
            if(existePrenda(codigo)){
                ResultSet resultado = sentencia.executeQuery(SQL);
                resultado.next();
                prenda.add(resultado.getString("codigo"));
                prenda.add(resultado.getString("nombre"));
                prenda.add(resultado.getString("tipo"));    
                prenda.add(resultado.getString("cantidad"));
                prenda.add(resultado.getString("precio"));
            }else{
                System.out.println("No hay resulset en mostrar prendas(Dao)");
                prenda = null;
            }
        }catch (SQLException ex) {
            System.out.println("Ocurrió un problema al traer los datos del prendas");            
        }      
        return prenda;
    }


    public ResultSet consultarPrendas() {
       String sql;
        ResultSet resultado = null;
        sql = "SELECT * FROM prenda;";
        
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);
            return resultado;
            
        }catch (SQLException ex) {
            System.out.println("Excepción en mostrar prendas en tablas");
        }
        return resultado;
    }
    
}
