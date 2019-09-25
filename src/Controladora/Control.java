package Controladora;

import Modelo.DAOs.ProveedorDAO;
import Modelo.DAOs.PrendaDAO;
import Modelo.DAOs.UsuarioDAO;
import Modelo.Usuario;
import Modelo.Prenda;
import Modelo.Proveedor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import rojerusan.RSTableMetro;
import net.proteanit.sql.DbUtils;


public class Control {
        
    private final PrendaDAO daoPrenda;
    private final ProveedorDAO daoProveedor;
    private final UsuarioDAO daoUsuarios;

   
//=======================================================================================================
    /**
     * Constructor de la clase Control
     * @since Control.java
     */
   
    public Control() 
    {       
        daoProveedor = new ProveedorDAO();
        daoPrenda = new PrendaDAO();
        daoUsuarios = new UsuarioDAO();
       
    }
//=======================================================================================================
    /**
     * PERSONAS
    */
    public String loginUsuario(String usuario, String contrasena) 
    {
        return daoUsuarios.loginUsuarioDAO(usuario, contrasena);
    }
    
    
    public boolean guardarCliente(String cedula, String nombre, String direccion,String telefono, String contrasena){      
        Usuario persona = new Usuario(cedula,nombre,direccion,telefono,cedula);
        if(daoUsuarios.guardarUsuario(persona)){
            return true;
        }else{
            return false;
        }                  
    }
    
    public boolean existeCliente(String cedula) {
       return daoUsuarios.existeCliente(cedula);
    }

    public boolean modificarCliente(String cedula, String nombre, String direccion, String telefono, String contrasena) {
       if(daoUsuarios.modificarCliente(cedula, nombre, direccion, telefono)){
            return true;
        }else{
            return false;
        }
    }
    
    public ArrayList mostrarCliente(String cedula){
        return daoUsuarios.mostrarCliente(cedula); 
    }
//=======================================================================================================    
    
    /**
     * PROVEEDOR 
     */
    
    public boolean guardarProveedor(String cedula, String nombre, String direccion,String telefono, String email){      
        Proveedor proveedor = new Proveedor(cedula,nombre,direccion,telefono,email);
        if(daoProveedor.guardarProveedor(proveedor)){
            return true;
        }else{
            return false;
        }                  
    }
    
    public boolean existeProveedor(String cedula){
        return daoProveedor.existeProveedor(cedula);
    }
    
    public boolean modificarProveedor(String nombre, String cedula, String direccion, String telefono, String email){
        if(daoProveedor.modificarProveedor(cedula, nombre, direccion, telefono,email)){
            return true;
        }else{
            return false;
        }
    }
    
    public ArrayList mostrarProveedor(String cedula){
        return daoProveedor.mostrarProveedor(cedula); 
    }
    
//=======================================================================================================

     /**
     * PRENDA
     */
     
    public boolean existePrenda(String codigo) {
        return daoPrenda.existePrenda(codigo);
    }

    public boolean guardarPrenda(String codigo, String nombre, String tipo, String cantidad, String precio) {
        Prenda prenda = new Prenda(codigo,nombre,tipo,cantidad,precio);
        if(daoPrenda.guardarPrenda(prenda)){
            return true;
        }else{
            return false;
        }       
    }

    public boolean modificarPrenda(String codigo, String nombre, String tipo, String cantidad, String precio) {
        if(daoPrenda.modificarPrenda(codigo, nombre, tipo, cantidad,precio)){
            return true;
        }else{
            return false;
        }
    }
    
    public ArrayList mostrarPrenda(String codigo){
        return daoPrenda.mostrarPrenda(codigo); 
    }
    
 //=======================================================================================================  
   /**MODELS TABLE*/
   public void consultarProveedores ( RSTableMetro tabla) throws SQLException{
        ResultSet rs = null;
        rs = daoProveedor.consultarProveedores();       
        tabla.setModel(DbUtils.resultSetToTableModel(rs));
   }

    public void consultarClientes(RSTableMetro rSTablaClientes) {
        ResultSet rs = null;
        rs = daoUsuarios.consultarUsuarios();       
        rSTablaClientes.setModel(DbUtils.resultSetToTableModel(rs));
    }

    public void consultarPrendas(RSTableMetro rSTablaModificarPrenda) {
       ResultSet rs = null;
       rs = daoPrenda.consultarPrendas();       
       rSTablaModificarPrenda.setModel(DbUtils.resultSetToTableModel(rs));
    }





    
}
