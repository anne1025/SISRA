package Modelo.DAOs;

import Modelo.Proveedor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class ProveedorDAO 
{
    private AccesoBD fachada;

//=======================================================================================================
    
    public ProveedorDAO()
    {
        fachada = new AccesoBD();
    }

//=======================================================================================================    
    public boolean guardarProveedor(Proveedor proveedor){
        String sqlInsert, sqlLlave;
        ResultSet resultado = null;
 
        sqlInsert = "INSERT INTO proveedor VALUES ('"+proveedor.getNit()+"','"+proveedor.getNombre()+"','"+proveedor.getDireccion()+"','"+proveedor.getTelefono()+"','"+proveedor.getEmail()+"');";
        sqlLlave = "SELECT * FROM proveedor WHERE cedula = '"+proveedor.getNit()+"';";
        
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();           
            resultado = sentencia.executeQuery(sqlLlave);
            if(resultado.next()){
                System.out.println("Ya existe un proveedor con la misma cedula");
                return false;              
            }
            else{
                sentencia.executeUpdate(sqlInsert);
                return true;
                }
            }catch (SQLException ex) {
            System.out.println("Excepción en insertar proveedores");
            ex.printStackTrace();            
        }
        return false;
    }    
//=======================================================================================================    
    public boolean existeProveedor(String cedula){
        String sql = "SELECT * FROM proveedor WHERE cedula = '"+cedula+"';";
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
//=======================================================================================================    
    public boolean modificarProveedor(String cedula, String nombre, String direccion, String telefono, String email){
        String sql_updatePer;
        sql_updatePer = "UPDATE proveedor SET nombre = '"+nombre+"', direccion = '"+direccion+"', telefono = '"
                +telefono+"', email = '"+email+"' WHERE cedula = '"+cedula+"';";
        
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            sentencia.executeUpdate(sql_updatePer);

            return true;
        }catch (SQLException ex) {
            System.out.println("Excepción en modificar proveedor");
        }
        return false;
    }

//=======================================================================================================
    
    public ArrayList <String> mostrarProveedor(String cedula)
    {
        ArrayList<String> proveedor = new ArrayList<>();
        String proveedores;
        proveedores = "SELECT * FROM proveedor WHERE cedula = '"+cedula+"';";
             
        try {            
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();            
            if(existeProveedor(cedula)){
                ResultSet resultado = sentencia.executeQuery(proveedores);
                resultado.next();
                proveedor.add(resultado.getString("cedula"));
                proveedor.add(resultado.getString("nombre"));
                proveedor.add(resultado.getString("email"));    
                proveedor.add(resultado.getString("direccion"));
                proveedor.add(resultado.getString("telefono"));
            }else{
                System.out.println("No hay resulset en mostrar pacientes(Dao)");
                proveedor = null;
            }
        }catch (SQLException ex) {
            System.out.println("Ocurrió un problema al traer los datos del paciente");            
        }      
        return proveedor;
    }

//=======================================================================================================
   
    
    public ResultSet consultarProveedores(){
        String sql;
        ResultSet resultado = null;
        sql = "SELECT cedula, nombre, email FROM proveedor;";
        
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(sql);
            return resultado;
            
        }catch (SQLException ex) {
            System.out.println("Excepción en mostrar proveedores en tablas");
        }
        return resultado;
    }
}
    
    
        
   
            
    
    
    


