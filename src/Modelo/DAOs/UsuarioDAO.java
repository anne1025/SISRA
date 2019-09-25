package Modelo.DAOs;

import Modelo.Usuario;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class UsuarioDAO 
{   
    
    private final AccesoBD fachada;
    
    
    public UsuarioDAO()
    {
        fachada = new AccesoBD();
    }
    
    public String loginUsuarioDAO (String idCedula, String contrasena) 
    {       
        String tipo = "";     
        String sentenciaSQL = "SELECT * FROM usuario WHERE cedula = '" + idCedula + "' and contrasena = '"
                + contrasena + "'";
        
        try 
        {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            ResultSet resultado = sentencia.executeQuery(sentenciaSQL);
      
            
            if(resultado.next())
            {                
                tipo = resultado.getString("tipo_usuario");                
                if(tipo.equals("Administrador")){
                    return "Administrador";
                }
                
                else{
                
                    return "No";
              }
                
            }
            else
            {
                return "";
            }

        } 
        
        catch (Exception ex) {
            System.out.println("Error a la hora de loguearse en el sistema: "+ex.getMessage());
        }
        return "";
    }

    public boolean guardarUsuario(Usuario persona) {
        String sqlInsert, sqlLlave;
        ResultSet resultado = null;
 
        sqlInsert = "INSERT INTO usuario VALUES ('"+persona.getCedula()+"','"+persona.getNombre()+"','"+persona.getDireccion()+"','"+persona.getTelefono()+"','"+persona.getCedula()+"', 'Cliente');";
        sqlLlave = "SELECT * FROM usuario WHERE cedula = '"+persona.getCedula()+"';";
        
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();           
            resultado = sentencia.executeQuery(sqlLlave);
            if(resultado.next()){
                System.out.println("Ya existe un usuario con la misma cedula");
                return false;              
            }
            else{
                sentencia.executeUpdate(sqlInsert);
                return true;
                }
            }catch (SQLException ex) {
            System.out.println("Excepción en insertar usuario");
            ex.printStackTrace();            
        }
        return false;
    }

    public boolean existeCliente(String cedula) {
        String sql = "SELECT * FROM usuario WHERE cedula = '"+cedula+"';";
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

    public boolean modificarCliente(String cedula, String nombre, String direccion, String telefono) {
        String sql_updatePer;
        sql_updatePer = "UPDATE usuario SET nombre = '"+nombre+"', direccion = '"+direccion+"', telefono = '"
                +telefono+"' WHERE cedula = '"+cedula+"';";
        
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            sentencia.executeUpdate(sql_updatePer);

            return true;
        }catch (SQLException ex) {
            System.out.println("Excepción en modificar cliente");
        }
        return false;
    }

    public ArrayList <String> mostrarCliente(String cedula)
    {
        ArrayList<String> cliente = new ArrayList<>();
        String SQL;
        SQL = "SELECT * FROM usuario WHERE cedula = '"+cedula+"';";
             
        try {            
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();            
            if(existeCliente(cedula)){
                ResultSet resultado = sentencia.executeQuery(SQL);
                resultado.next();
                cliente.add(resultado.getString("cedula"));
                cliente.add(resultado.getString("nombre"));
                cliente.add(resultado.getString("telefono"));   
                cliente.add(resultado.getString("direccion")); 
            }else{
                System.out.println("No hay resulset en mostrar clientes(dao)");
                cliente = null;
            }
        }catch (SQLException ex) {
            System.out.println("Ocurrió un problema al traer los datos del cliente");            
        }      
        return cliente;
    }

    public ResultSet consultarUsuarios() {
        String SQL;
        ResultSet resultado = null;
        SQL = "SELECT cedula, nombre, telefono FROM usuario WHERE tipo_usuario ='Cliente';";
        
        try {
            Connection conexion = fachada.getConnetion();
            Statement sentencia = conexion.createStatement();
            resultado = sentencia.executeQuery(SQL);
            return resultado;
            
        }catch (SQLException ex) {
            System.out.println("Excepción en mostrar clientes en tablas");
        }
        return resultado;
    }
}
