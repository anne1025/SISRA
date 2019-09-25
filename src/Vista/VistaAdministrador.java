package Vista;

import Controladora.*;
import Modelo.Validaciones;
import java.awt.Color;
import java.awt.Component;
import java.awt.Event;
import java.awt.event.MouseEvent;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.JComboBox;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.DefaultEditorKit;
import java.awt.Cursor;
import java.io.File;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JList;
import javax.swing.filechooser.FileNameExtensionFilter;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.JLabel;




public class VistaAdministrador extends javax.swing.JFrame {
    
    //Declaracion de Variables Auxiliares
    ActionMap acciones ;
    Action accionCopiar;
    Action accionPegar;
    Action accionCortar;
    Action accionDeshacer;
    Validaciones validacionTotal; 
    Control controladora;
    private Image iconoVentana;
    private static VistaAdministrador INSTANCE = null;
      
//=======================================================================================================    
    /**
     * Constructor de Clase VistaAdministrador
     * @since VistaAdministrador.java
     */
    
    public VistaAdministrador() {
        
        initComponents();
        
        //Inicializacion de Variables
        acciones = jTextFieldBuscar.getActionMap();
        validacionTotal = new Validaciones();
        controladora = new Control();
     
        iconoVentana = Toolkit.getDefaultToolkit().getImage(getClass().getResource("/Imagenes/Logos/icono.png"));

        
        //Implementacion de Ajustes
        ajustesGraficos();
        ajustesFuncionales();                       
    }
    
 //=======================================================================================================
    /**
    * Metodo que permite crear una nueva instacia de la clase solo si ya no ha sido creada otra
    */
    
    private synchronized static void crearInstancia(){
        if(INSTANCE == null){
            INSTANCE = new VistaAdministrador();         
        }
    }
    
//=======================================================================================================   
    /**
    * Metodo que permite obtener la instancia de la clase que ha sido creada
    * @return El objeto de la instancia de la clase
    */
    
    public static VistaAdministrador getInstancia(){
        crearInstancia();
       return INSTANCE;
    }
//=======================================================================================================
    /**
     * Metodo que realiza ajustes visuales a la interfaz
     */
    
    public final void ajustesGraficos()
    {        
        this.setLocationRelativeTo(null);
        this.setIconImage(iconoVentana); 
        rSTablaProveedores.setDefaultEditor(Object.class, null);
        rSTablaClientes.setDefaultEditor(Object.class, null);
        rSPopuMenu2.add(jPanelMetro);
    }
    
    /**
     * Metodo que pone el color en un Jpanel
     * @param panel Variable que almacena el panel a modificar
     */
    public void ponerColor(JPanel panel)
    {
        panel.setBackground(new Color(197, 197, 197));
    }
    
//=======================================================================================================
    /**
     * Metodo que permite poner el color iniciar de un JPanel
     * @param panel Variable que almacena el panel a modificar
     */
    public void repintarColor(JPanel panel)
    {
        panel.setBackground(new Color(255,255,255));
    }
//=======================================================================================================
    /**
     * Metodo que permite ocultar todos los Panel que no crresponden al panel activo
     * @param panelPadre Variable que almacena el Root Panel de los Slider
     */
    public void ocultarSliders(JPanel panelPadre)
    {
        Component[] componentes = panelPadre.getComponents();
        for(int i=0 ; i<componentes.length;i++)
        {
            componentes[i].setVisible(false);
        }
                
    }
    
//======================================================================================================= 
    /**
     * Metodo para eliminar los datos de una tabla
     * @param tabla Variable que almacena la tabla que se desea borrar
     */
    public final void borrarTabla(JTable tabla)
    {
        DefaultTableModel modelo = (DefaultTableModel) tabla.getModel();        
        int cFilas = modelo.getRowCount();
        for (int i = cFilas-1; i >= 0; i--) {
            modelo.removeRow(i);            
        }       
    }
//=======================================================================================================    
    /**
     * Metodo para limpiar todos los componentes de un JPanel que contiene otros componentes
     * @param panelPadre Variable que almacena ela
     */
    private void limpiarComponentes(JPanel panelPadre) {
        
        Component[] componentes = panelPadre.getComponents();
        for(int i=0 ; i<componentes.length;i++)
        {
            if(componentes[i] instanceof JTextField)
            {
                JTextField jTextFieldAux = (JTextField) componentes[i];
                jTextFieldAux.setText("");
            }
            
            else if (componentes [i] instanceof JComboBox)
            {
                JComboBox jComboBoxAux = (JComboBox) componentes[i];
                jComboBoxAux.setSelectedIndex(-1);
            }
            
            else if (componentes [i] instanceof JTable)
            {
                JTable jTableAux = (JTable) componentes[i];
                borrarTabla(jTableAux);
            }   
           
        }
    }
    
//=======================================================================================================
    /**
     * Metodo que permite mostrar el PopMenu ingresando un evento del mouse
     * @param e Variable que almacena el evento del mouse que va inicializar la funcionalidad
     */
    public void mostrarPopupMenu(MouseEvent e) 
    {      
       if (e.isPopupTrigger()) 
       { 
            rSPopuMenu1.show(e.getComponent(),e.getX(), e.getY()); 
       }
    }
   
 
    public void mostrarPopupMenu1(MouseEvent e) 
    {      

       rSPopuMenu2.show(e.getComponent(),e.getX(), e.getY());   
    }
//=======================================================================================================    
    
    /**
     * Metodo que implementa ajustes en la funcionalidad de la interfaz
     */
    public final void ajustesFuncionales()
    {
        asignarMenuContextual();
    }
    
//=======================================================================================================    
    /**
     * Metodo que permite configurar la funcionalidad del Menu Contextual en componentes como JTextfield
     * y jTextArea
     */
    public void asignarMenuContextual()
    {
        
        accionCopiar = acciones.get(DefaultEditorKit.copyAction);
        accionPegar = acciones.get(DefaultEditorKit.pasteAction);       
        accionCortar = acciones.get(DefaultEditorKit.cutAction);
          
        accionCopiar.putValue(Action.NAME, "Copiar");
        accionCopiar.putValue(Action.ACCELERATOR_KEY,KeyStroke.getAWTKeyStroke('C', Event.CTRL_MASK)); 
 
        accionCortar.putValue(Action.NAME, "Cortar");
        accionCortar.putValue(Action.ACCELERATOR_KEY,KeyStroke.getAWTKeyStroke('X', Event.CTRL_MASK)); 
       
        accionPegar.putValue(Action.NAME, "Pegar");
        accionPegar.putValue(Action.ACCELERATOR_KEY,KeyStroke.getAWTKeyStroke('V', Event.CTRL_MASK)); 
        
        rSPopuMenu1.add(accionCopiar);
        rSPopuMenu1.add(accionCortar);
        rSPopuMenu1.add(accionPegar);        
    }
    
//======================================================================================================= 
    /**
     * Metodo que permite mostrar en la interfaz GUI los datos de un prenda existente
     * @param cedula Variable que almacena el ID del prenda encontrado
     */
    
    public void editarCliente(String cedula){

        ArrayList <String> cliente = (ArrayList)controladora.mostrarCliente(cedula);
        
        jTextFieldCedula.setText(cliente.get(0));
        jTextFieldNombre.setText(cliente.get(1));     
        jTextFieldDireccion2.setText(cliente.get(2));
        jTextFieldTelefono.setText(cliente.get(3));
        
    }
//=======================================================================================================    //=======================================================================================================    //=======================================================================================================    //=======================================================================================================    
    /**
     * Metodo que permite mostrar en la interfaz GUI los datos de un proveedor existente
     * @param cedula Variable que almacena el ID del prenda encontrado
     */
    
    public void editarProveedor(String cedula){

        ArrayList <String> paciente = (ArrayList)controladora.mostrarProveedor(cedula);
        
        jTextFieldCedula1.setText(paciente.get(0));
        jTextFieldNombre1.setText(paciente.get(1)); 
        jTextFieldEmail1.setText(paciente.get(2));
        jTextFieldDireccion1.setText(paciente.get(3));
        jTextFieldTelefono1.setText(paciente.get(4));
    }
//=======================================================================================================

      public void editarPrenda(String codigo) {
        ArrayList <String> prenda = (ArrayList)controladora.mostrarPrenda(codigo);
        
        jTextFieldCodigo2.setText(prenda.get(0));
        jTextFieldNombre4.setText(prenda.get(1));     
        jTextFieldTipo.setText(prenda.get(2));
        jTextFieldCantidad.setText(prenda.get(3));
        jTextFieldPrecio.setText(prenda.get(4));
        
     }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        rSPopuMenu1 = new rojerusan.RSPopuMenu();
        buttonGroupTipo = new javax.swing.ButtonGroup();
        buttonGroupFormato = new javax.swing.ButtonGroup();
        rSPopuMenu2 = new rojerusan.RSPopuMenu();
        jPanelMetro = new javax.swing.JPanel();
        jPanelArriba2 = new javax.swing.JPanel();
        rSButtonMetro1 = new rojerusan.RSButtonMetro();
        rSButtonMetro2 = new rojerusan.RSButtonMetro();
        rSButtonMetro3 = new rojerusan.RSButtonMetro();
        rSButtonMetro4 = new rojerusan.RSButtonMetro();
        rSButtonMetro5 = new rojerusan.RSButtonMetro();
        rSButtonMetro6 = new rojerusan.RSButtonMetro();
        rSButtonMetro7 = new rojerusan.RSButtonMetro();
        rSButtonMetro8 = new rojerusan.RSButtonMetro();
        rSButtonMetro9 = new rojerusan.RSButtonMetro();
        rSButtonMetro10 = new rojerusan.RSButtonMetro();
        rSButtonMetro11 = new rojerusan.RSButtonMetro();
        rSButtonMetro12 = new rojerusan.RSButtonMetro();
        rSButtonMetro13 = new rojerusan.RSButtonMetro();
        rSButtonMetro14 = new rojerusan.RSButtonMetro();
        rSButtonMetro15 = new rojerusan.RSButtonMetro();
        rSButtonMetro16 = new rojerusan.RSButtonMetro();
        rSButtonMetro17 = new rojerusan.RSButtonMetro();
        rSButtonMetro18 = new rojerusan.RSButtonMetro();
        rSButtonMetro19 = new rojerusan.RSButtonMetro();
        rSButtonMetro20 = new rojerusan.RSButtonMetro();
        rSButtonMetro21 = new rojerusan.RSButtonMetro();
        rSButtonMetro22 = new rojerusan.RSButtonMetro();
        rSButtonMetro23 = new rojerusan.RSButtonMetro();
        rSButtonMetro24 = new rojerusan.RSButtonMetro();
        rSButtonMetro25 = new rojerusan.RSButtonMetro();
        rSButtonMetro26 = new rojerusan.RSButtonMetro();
        rSButtonMetro27 = new rojerusan.RSButtonMetro();
        rSButtonMetro28 = new rojerusan.RSButtonMetro();
        rSButtonMetro29 = new rojerusan.RSButtonMetro();
        rSButtonMetro30 = new rojerusan.RSButtonMetro();
        rSButtonMetro31 = new rojerusan.RSButtonMetro();
        rSButtonMetro32 = new rojerusan.RSButtonMetro();
        rSButtonMetro33 = new rojerusan.RSButtonMetro();
        rSButtonMetro34 = new rojerusan.RSButtonMetro();
        rSButtonMetro35 = new rojerusan.RSButtonMetro();
        rSButtonMetro36 = new rojerusan.RSButtonMetro();
        rSButtonMetro37 = new rojerusan.RSButtonMetro();
        rSButtonMetro38 = new rojerusan.RSButtonMetro();
        rSButtonMetro39 = new rojerusan.RSButtonMetro();
        rSButtonMetro40 = new rojerusan.RSButtonMetro();
        rSButtonMetro41 = new rojerusan.RSButtonMetro();
        rSButtonMetro42 = new rojerusan.RSButtonMetro();
        rSButtonMetro43 = new rojerusan.RSButtonMetro();
        rSButtonMetro44 = new rojerusan.RSButtonMetro();
        rSButtonMetro45 = new rojerusan.RSButtonMetro();
        rSButtonMetro46 = new rojerusan.RSButtonMetro();
        rSButtonMetro47 = new rojerusan.RSButtonMetro();
        rSButtonMetro48 = new rojerusan.RSButtonMetro();
        rSButtonMetro49 = new rojerusan.RSButtonMetro();
        rSButtonMetro50 = new rojerusan.RSButtonMetro();
        rSButtonMetro51 = new rojerusan.RSButtonMetro();
        rSButtonMetro52 = new rojerusan.RSButtonMetro();
        rSButtonMetro53 = new rojerusan.RSButtonMetro();
        rSButtonMetro54 = new rojerusan.RSButtonMetro();
        rSButtonMetro55 = new rojerusan.RSButtonMetro();
        rSButtonMetro56 = new rojerusan.RSButtonMetro();
        rSButtonMetro57 = new rojerusan.RSButtonMetro();
        rSButtonMetro58 = new rojerusan.RSButtonMetro();
        rSButtonMetro59 = new rojerusan.RSButtonMetro();
        rSButtonMetro60 = new rojerusan.RSButtonMetro();
        rSButtonMetro61 = new rojerusan.RSButtonMetro();
        rSButtonMetro62 = new rojerusan.RSButtonMetro();
        rSButtonMetro63 = new rojerusan.RSButtonMetro();
        rSButtonMetro64 = new rojerusan.RSButtonMetro();
        rSButtonMetro65 = new rojerusan.RSButtonMetro();
        rSButtonMetro66 = new rojerusan.RSButtonMetro();
        rSButtonMetro67 = new rojerusan.RSButtonMetro();
        rSButtonMetro68 = new rojerusan.RSButtonMetro();
        rSButtonMetro69 = new rojerusan.RSButtonMetro();
        rSButtonMetro70 = new rojerusan.RSButtonMetro();
        rSButtonMetro71 = new rojerusan.RSButtonMetro();
        rSButtonMetro72 = new rojerusan.RSButtonMetro();
        rSButtonMetro73 = new rojerusan.RSButtonMetro();
        rSButtonMetro74 = new rojerusan.RSButtonMetro();
        rSButtonMetro75 = new rojerusan.RSButtonMetro();
        rSButtonMetro76 = new rojerusan.RSButtonMetro();
        rSButtonMetro77 = new rojerusan.RSButtonMetro();
        rSButtonMetro78 = new rojerusan.RSButtonMetro();
        rSButtonMetro79 = new rojerusan.RSButtonMetro();
        rSButtonMetro80 = new rojerusan.RSButtonMetro();
        rSButtonMetro81 = new rojerusan.RSButtonMetro();
        rSButtonMetro82 = new rojerusan.RSButtonMetro();
        jPanelAbajo2 = new javax.swing.JPanel();
        jLabelNombre6 = new javax.swing.JLabel();
        jTextFieldNombre6 = new javax.swing.JTextField();
        jTextFieldIngreso = new javax.swing.JTextField();
        rSButtonMetroIngresar = new rojerusan.RSButtonMetro();
        rSButtonMetroBorrar = new rojerusan.RSButtonMetro();
        rSButtonMetroBorrarTodo = new rojerusan.RSButtonMetro();
        rSButtonMetroFinalizar = new rojerusan.RSButtonMetro();
        jSeparatorEmpleado3 = new javax.swing.JSeparator();
        jPanelPpal = new javax.swing.JPanel();
        jPanelMenu = new javax.swing.JPanel();
        jPanelTitulo = new javax.swing.JPanel();
        jPanelPerfil = new javax.swing.JPanel();
        jLabelTitulo = new javax.swing.JLabel();
        jLabelimgPerfil = new javax.swing.JLabel();
        jPanelRol = new javax.swing.JPanel();
        jLabelRol2 = new javax.swing.JLabel();
        jPanelUsuarios = new javax.swing.JPanel();
        jLabelUsuarios = new javax.swing.JLabel();
        jLabelimgUsuarios = new javax.swing.JLabel();
        rSButtonCerrarSesion = new rojerusan.RSButtonPane();
        jLabelimgCerrarSesion = new javax.swing.JLabel();
        jPanelStaff = new javax.swing.JPanel();
        jPanelTituloUsuario = new javax.swing.JPanel();
        jLabelTituloUsuario = new javax.swing.JLabel();
        jLabelimgUsuario = new javax.swing.JLabel();
        jButtonSlider = new javax.swing.JButton();
        jPanelSlider = new javax.swing.JPanel();
        jLabelPaciente = new javax.swing.JLabel();
        jLabelEmpleado = new javax.swing.JLabel();
        jSeparatorPaciente = new javax.swing.JSeparator();
        jSeparatorEmpleado4 = new javax.swing.JSeparator();
        jSeparatorEmpleado6 = new javax.swing.JSeparator();
        rSButtonPaneHome = new rojerusan.RSButtonPane();
        jLabelHome = new javax.swing.JLabel();
        rSButtonAgregarProveedor = new rojerusan.RSButtonPane();
        jLabelAgregarPaciente = new javax.swing.JLabel();
        rSButtonModificarPaciente = new rojerusan.RSButtonPane();
        jLabelModificarProveedor = new javax.swing.JLabel();
        rSButtonAgregarCliente = new rojerusan.RSButtonPane();
        jLabelAgregarEmpleado = new javax.swing.JLabel();
        rSButtonModificarCliente = new rojerusan.RSButtonPane();
        jLabelModificarEmpleado = new javax.swing.JLabel();
        rSButtonAgregarPrenda = new rojerusan.RSButtonPane();
        jLabelAgregarEmpleado1 = new javax.swing.JLabel();
        rSButtonModificarPrenda = new rojerusan.RSButtonPane();
        jLabelModificarEmpleado1 = new javax.swing.JLabel();
        jLabelEmpleado1 = new javax.swing.JLabel();
        jPanelSliders = new javax.swing.JPanel();
        jPanelAgregarProveedor = new javax.swing.JPanel();
        jLabelNombre1 = new javax.swing.JLabel();
        jLabelCedula1 = new javax.swing.JLabel();
        jLabelDireccion1 = new javax.swing.JLabel();
        jLabelTelefono1 = new javax.swing.JLabel();
        jLabelActividad = new javax.swing.JLabel();
        jTextFieldNombre1 = new javax.swing.JTextField();
        jTextFieldCedula1 = new javax.swing.JTextField();
        jTextFieldDireccion1 = new javax.swing.JTextField();
        jTextFieldTelefono1 = new javax.swing.JTextField();
        jTextFieldEmail1 = new javax.swing.JTextField();
        rSButtonMetroGuardar1 = new rojerusan.RSButtonMetro();
        rSButtonMetroLimpiar1 = new rojerusan.RSButtonMetro();
        jPanelModificarProveedor = new javax.swing.JPanel();
        jLabelBuscar1 = new javax.swing.JLabel();
        jTextFieldBuscar1 = new javax.swing.JTextField();
        rSButtonMetroFiltrar1 = new rojerusan.RSButtonMetro();
        jScrollPaneTablaPacientes = new javax.swing.JScrollPane();
        rSTablaProveedores = new rojerusan.RSTableMetro();
        rSButtonMetroLimpiar12 = new rojerusan.RSButtonMetro();
        rSButtonMetroFiltrar7 = new rojerusan.RSButtonMetro();
        jPanelAgregarCliente = new javax.swing.JPanel();
        jLabelNombre = new javax.swing.JLabel();
        jLabelCedula = new javax.swing.JLabel();
        jLabelDireccion = new javax.swing.JLabel();
        jLabelTelefono = new javax.swing.JLabel();
        jTextFieldNombre = new javax.swing.JTextField();
        jTextFieldCedula = new javax.swing.JTextField();
        jTextFieldDireccion2 = new javax.swing.JTextField();
        jTextFieldTelefono = new javax.swing.JTextField();
        rSButtonMetroGuardar = new rojerusan.RSButtonMetro();
        rSButtonMetroLimpiar = new rojerusan.RSButtonMetro();
        jPanelModificarCliente = new javax.swing.JPanel();
        jLabelBuscar = new javax.swing.JLabel();
        jTextFieldBuscar = new javax.swing.JTextField();
        rSButtonMetroFiltrar = new rojerusan.RSButtonMetro();
        jScrollPaneTablaEmpleados = new javax.swing.JScrollPane();
        rSTablaClientes = new rojerusan.RSTableMetro();
        rSButtonMetroLimpiar11 = new rojerusan.RSButtonMetro();
        rSButtonMetroFiltrar8 = new rojerusan.RSButtonMetro();
        jPanelAgregarPrenda = new javax.swing.JPanel();
        jLabelNombre4 = new javax.swing.JLabel();
        jLabelCodigo2 = new javax.swing.JLabel();
        jTextFieldNombre4 = new javax.swing.JTextField();
        jTextFieldCodigo2 = new javax.swing.JTextField();
        rSButtonMetroGuardar4 = new rojerusan.RSButtonMetro();
        rSButtonMetroLimpiar4 = new rojerusan.RSButtonMetro();
        jTextFieldTipo = new javax.swing.JTextField();
        jLabelNombre5 = new javax.swing.JLabel();
        jTextFieldCantidad = new javax.swing.JTextField();
        jLabelNombre7 = new javax.swing.JLabel();
        jTextFieldPrecio = new javax.swing.JTextField();
        jLabelNombre8 = new javax.swing.JLabel();
        jPanelModificarPrenda = new javax.swing.JPanel();
        jLabelBuscar3 = new javax.swing.JLabel();
        jTextFieldBuscar3 = new javax.swing.JTextField();
        rSButtonMetroLimpiar13 = new rojerusan.RSButtonMetro();
        rSButtonMetroFiltrar3 = new rojerusan.RSButtonMetro();
        jScrollPaneTablaHabilidades = new javax.swing.JScrollPane();
        rSTablaModificarPrenda = new rojerusan.RSTableMetro();
        rSButtonMetroFiltrar9 = new rojerusan.RSButtonMetro();

        jPanelMetro.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelArriba2.setPreferredSize(new java.awt.Dimension(700, 300));
        jPanelArriba2.setLayout(new java.awt.GridLayout(12, 8, 1, 1));

        rSButtonMetro1.setText("Agencia");
        rSButtonMetro1.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro1.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro1.setInheritsPopupMenu(true);
        rSButtonMetro1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro1ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro1);

        rSButtonMetro2.setText("Almacen");
        rSButtonMetro2.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro2.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro2.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro2.setInheritsPopupMenu(true);
        rSButtonMetro2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro2ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro2);

        rSButtonMetro3.setText("Altillo");
        rSButtonMetro3.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro3.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro3.setInheritsPopupMenu(true);
        rSButtonMetro3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro3ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro3);

        rSButtonMetro4.setText("Apartado");
        rSButtonMetro4.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro4.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro4.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro4.setInheritsPopupMenu(true);
        rSButtonMetro4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro4ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro4);

        rSButtonMetro5.setText("Autopista");
        rSButtonMetro5.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro5.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro5.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro5.setInheritsPopupMenu(true);
        rSButtonMetro5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro5ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro5);

        rSButtonMetro6.setText("Avenida");
        rSButtonMetro6.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro6.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro6.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro6.setInheritsPopupMenu(true);
        rSButtonMetro6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro6ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro6);

        rSButtonMetro7.setText("Barrio");
        rSButtonMetro7.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro7.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro7.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro7.setInheritsPopupMenu(true);
        rSButtonMetro7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro7ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro7);

        rSButtonMetro8.setText("Bloque");
        rSButtonMetro8.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro8.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro8.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro8.setInheritsPopupMenu(true);
        rSButtonMetro8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro8ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro8);

        rSButtonMetro9.setText("Boulevar");
        rSButtonMetro9.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro9.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro9.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro9.setInheritsPopupMenu(true);
        rSButtonMetro9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro9ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro9);

        rSButtonMetro10.setText("Calle");
        rSButtonMetro10.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro10.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro10.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro10.setInheritsPopupMenu(true);
        rSButtonMetro10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro10ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro10);

        rSButtonMetro11.setText("Carretera");
        rSButtonMetro11.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro11.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro11.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro11.setInheritsPopupMenu(true);
        rSButtonMetro11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro11ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro11);

        rSButtonMetro12.setText("Carrera");
        rSButtonMetro12.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro12.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro12.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro12.setInheritsPopupMenu(true);
        rSButtonMetro12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro12ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro12);

        rSButtonMetro13.setText("Casa");
        rSButtonMetro13.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro13.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro13.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro13.setInheritsPopupMenu(true);
        rSButtonMetro13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro13ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro13);

        rSButtonMetro14.setText("Circular");
        rSButtonMetro14.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro14.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro14.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro14.setInheritsPopupMenu(true);
        rSButtonMetro14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro14ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro14);

        rSButtonMetro15.setText("Circunvalar");
        rSButtonMetro15.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro15.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro15.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro15.setInheritsPopupMenu(true);
        rSButtonMetro15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro15ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro15);

        rSButtonMetro16.setText("Ciudadela");
        rSButtonMetro16.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro16.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro16.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro16.setInheritsPopupMenu(true);
        rSButtonMetro16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro16ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro16);

        rSButtonMetro17.setText("Conjunto");
        rSButtonMetro17.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro17.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro17.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro17.setInheritsPopupMenu(true);
        rSButtonMetro17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro17ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro17);

        rSButtonMetro18.setText("Agencia");
        rSButtonMetro18.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro18.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro18.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro18.setInheritsPopupMenu(true);
        rSButtonMetro18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro18ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro18);

        rSButtonMetro19.setText("Corregimiento");
        rSButtonMetro19.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro19.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro19.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro19.setInheritsPopupMenu(true);
        rSButtonMetro19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro19ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro19);

        rSButtonMetro20.setText("Centro");
        rSButtonMetro20.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro20.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro20.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro20.setInheritsPopupMenu(true);
        rSButtonMetro20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro20ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro20);

        rSButtonMetro21.setText("Diagonal");
        rSButtonMetro21.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro21.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro21.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro21.setInheritsPopupMenu(true);
        rSButtonMetro21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro21ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro21);

        rSButtonMetro22.setText("Edificio");
        rSButtonMetro22.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro22.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro22.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro22.setInheritsPopupMenu(true);
        rSButtonMetro22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro22ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro22);

        rSButtonMetro23.setText("Entrada");
        rSButtonMetro23.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro23.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro23.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro23.setInheritsPopupMenu(true);
        rSButtonMetro23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro23ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro23);

        rSButtonMetro24.setText("Esquina");
        rSButtonMetro24.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro24.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro24.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro24.setInheritsPopupMenu(true);
        rSButtonMetro24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro24ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro24);

        rSButtonMetro25.setText("Etapa");
        rSButtonMetro25.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro25.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro25.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro25.setInheritsPopupMenu(true);
        rSButtonMetro25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro25ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro25);

        rSButtonMetro26.setText("Finca");
        rSButtonMetro26.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro26.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro26.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro26.setInheritsPopupMenu(true);
        rSButtonMetro26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro26ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro26);

        rSButtonMetro27.setText("Hacienda");
        rSButtonMetro27.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro27.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro27.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro27.setInheritsPopupMenu(true);
        rSButtonMetro27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro27ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro27);

        rSButtonMetro28.setText("Kilometro");
        rSButtonMetro28.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro28.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro28.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro28.setInheritsPopupMenu(true);
        rSButtonMetro28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro28ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro28);

        rSButtonMetro29.setText("Local");
        rSButtonMetro29.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro29.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro29.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro29.setInheritsPopupMenu(true);
        rSButtonMetro29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro29ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro29);

        rSButtonMetro30.setText("Lote");
        rSButtonMetro30.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro30.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro30.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro30.setInheritsPopupMenu(true);
        rSButtonMetro30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro30ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro30);

        rSButtonMetro31.setText("Manzana");
        rSButtonMetro31.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro31.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro31.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro31.setInheritsPopupMenu(true);
        rSButtonMetro31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro31ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro31);

        rSButtonMetro32.setText("Oficina");
        rSButtonMetro32.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro32.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro32.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro32.setInheritsPopupMenu(true);
        rSButtonMetro32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro32ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro32);

        rSButtonMetro33.setText("Parque");
        rSButtonMetro33.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro33.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro33.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro33.setInheritsPopupMenu(true);
        rSButtonMetro33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro33ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro33);

        rSButtonMetro34.setText("Pasaje");
        rSButtonMetro34.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro34.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro34.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro34.setInheritsPopupMenu(true);
        rSButtonMetro34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro34ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro34);

        rSButtonMetro35.setText("Piso");
        rSButtonMetro35.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro35.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro35.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro35.setInheritsPopupMenu(true);
        rSButtonMetro35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro35ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro35);

        rSButtonMetro36.setText("Planta");
        rSButtonMetro36.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro36.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro36.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro36.setInheritsPopupMenu(true);
        rSButtonMetro36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro36ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro36);

        rSButtonMetro37.setText("Salon");
        rSButtonMetro37.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro37.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro37.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro37.setInheritsPopupMenu(true);
        rSButtonMetro37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro37ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro37);

        rSButtonMetro38.setText("Sector");
        rSButtonMetro38.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro38.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro38.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro38.setInheritsPopupMenu(true);
        rSButtonMetro38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro38ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro38);

        rSButtonMetro39.setText("Torre");
        rSButtonMetro39.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro39.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro39.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro39.setInheritsPopupMenu(true);
        rSButtonMetro39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro39ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro39);

        rSButtonMetro40.setText("Transversal");
        rSButtonMetro40.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro40.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro40.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro40.setInheritsPopupMenu(true);
        rSButtonMetro40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro40ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro40);

        rSButtonMetro41.setText("Unidad");
        rSButtonMetro41.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro41.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro41.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro41.setInheritsPopupMenu(true);
        rSButtonMetro41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro41ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro41);

        rSButtonMetro42.setText("Vereda");
        rSButtonMetro42.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro42.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro42.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro42.setInheritsPopupMenu(true);
        rSButtonMetro42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro42ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro42);

        rSButtonMetro43.setText("Zona");
        rSButtonMetro43.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro43.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro43.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro43.setInheritsPopupMenu(true);
        rSButtonMetro43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro43ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro43);

        rSButtonMetro44.setText("Zona Franca");
        rSButtonMetro44.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro44.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro44.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro44.setInheritsPopupMenu(true);
        rSButtonMetro44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro44ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro44);

        rSButtonMetro45.setText("#");
        rSButtonMetro45.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro45.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro45.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro45.setInheritsPopupMenu(true);
        rSButtonMetro45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro45ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro45);

        rSButtonMetro46.setText("-");
        rSButtonMetro46.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro46.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro46.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro46.setInheritsPopupMenu(true);
        rSButtonMetro46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro46ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro46);

        rSButtonMetro47.setText("0");
        rSButtonMetro47.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro47.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro47.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro47.setInheritsPopupMenu(true);
        rSButtonMetro47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro47ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro47);

        rSButtonMetro48.setText("1");
        rSButtonMetro48.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro48.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro48.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro48.setInheritsPopupMenu(true);
        rSButtonMetro48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro48ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro48);

        rSButtonMetro49.setText("2");
        rSButtonMetro49.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro49.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro49.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro49.setInheritsPopupMenu(true);
        rSButtonMetro49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro49ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro49);

        rSButtonMetro50.setText("3");
        rSButtonMetro50.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro50.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro50.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro50.setInheritsPopupMenu(true);
        rSButtonMetro50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro50ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro50);

        rSButtonMetro51.setText("4");
        rSButtonMetro51.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro51.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro51.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro51.setInheritsPopupMenu(true);
        rSButtonMetro51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro51ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro51);

        rSButtonMetro52.setText("5");
        rSButtonMetro52.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro52.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro52.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro52.setInheritsPopupMenu(true);
        rSButtonMetro52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro52ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro52);

        rSButtonMetro53.setText("6");
        rSButtonMetro53.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro53.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro53.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro53.setInheritsPopupMenu(true);
        rSButtonMetro53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro53ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro53);

        rSButtonMetro54.setText("7");
        rSButtonMetro54.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro54.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro54.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro54.setInheritsPopupMenu(true);
        rSButtonMetro54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro54ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro54);

        rSButtonMetro55.setText("8");
        rSButtonMetro55.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro55.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro55.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro55.setInheritsPopupMenu(true);
        rSButtonMetro55.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro55ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro55);

        rSButtonMetro56.setText("9");
        rSButtonMetro56.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro56.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro56.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro56.setInheritsPopupMenu(true);
        rSButtonMetro56.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro56ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro56);

        rSButtonMetro57.setText("A");
        rSButtonMetro57.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro57.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro57.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro57.setInheritsPopupMenu(true);
        rSButtonMetro57.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro57ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro57);

        rSButtonMetro58.setText("B");
        rSButtonMetro58.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro58.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro58.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro58.setInheritsPopupMenu(true);
        rSButtonMetro58.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro58ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro58);

        rSButtonMetro59.setText("C");
        rSButtonMetro59.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro59.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro59.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro59.setInheritsPopupMenu(true);
        rSButtonMetro59.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro59ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro59);

        rSButtonMetro60.setText("D");
        rSButtonMetro60.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro60.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro60.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro60.setInheritsPopupMenu(true);
        rSButtonMetro60.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro60ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro60);

        rSButtonMetro61.setText("E");
        rSButtonMetro61.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro61.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro61.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro61.setInheritsPopupMenu(true);
        rSButtonMetro61.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro61ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro61);

        rSButtonMetro62.setText("F");
        rSButtonMetro62.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro62.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro62.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro62.setInheritsPopupMenu(true);
        rSButtonMetro62.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro62ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro62);

        rSButtonMetro63.setText("G");
        rSButtonMetro63.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro63.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro63.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro63.setInheritsPopupMenu(true);
        rSButtonMetro63.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro63ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro63);

        rSButtonMetro64.setText("H");
        rSButtonMetro64.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro64.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro64.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro64.setInheritsPopupMenu(true);
        rSButtonMetro64.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro64ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro64);

        rSButtonMetro65.setText("I");
        rSButtonMetro65.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro65.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro65.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro65.setInheritsPopupMenu(true);
        rSButtonMetro65.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro65ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro65);

        rSButtonMetro66.setText("J");
        rSButtonMetro66.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro66.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro66.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro66.setInheritsPopupMenu(true);
        rSButtonMetro66.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro66ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro66);

        rSButtonMetro67.setText("K");
        rSButtonMetro67.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro67.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro67.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro67.setInheritsPopupMenu(true);
        rSButtonMetro67.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro67ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro67);

        rSButtonMetro68.setText("L");
        rSButtonMetro68.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro68.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro68.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro68.setInheritsPopupMenu(true);
        rSButtonMetro68.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro68ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro68);

        rSButtonMetro69.setText("M");
        rSButtonMetro69.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro69.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro69.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro69.setInheritsPopupMenu(true);
        rSButtonMetro69.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro69ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro69);

        rSButtonMetro70.setText("N");
        rSButtonMetro70.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro70.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro70.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro70.setInheritsPopupMenu(true);
        rSButtonMetro70.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro70ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro70);

        rSButtonMetro71.setText("O");
        rSButtonMetro71.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro71.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro71.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro71.setInheritsPopupMenu(true);
        rSButtonMetro71.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro71ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro71);

        rSButtonMetro72.setText("P");
        rSButtonMetro72.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro72.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro72.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro72.setInheritsPopupMenu(true);
        rSButtonMetro72.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro72ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro72);

        rSButtonMetro73.setText("Q");
        rSButtonMetro73.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro73.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro73.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro73.setInheritsPopupMenu(true);
        rSButtonMetro73.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro73ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro73);

        rSButtonMetro74.setText("R");
        rSButtonMetro74.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro74.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro74.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro74.setInheritsPopupMenu(true);
        rSButtonMetro74.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro74ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro74);

        rSButtonMetro75.setText("S");
        rSButtonMetro75.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro75.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro75.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro75.setInheritsPopupMenu(true);
        rSButtonMetro75.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro75ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro75);

        rSButtonMetro76.setText("T");
        rSButtonMetro76.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro76.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro76.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro76.setInheritsPopupMenu(true);
        rSButtonMetro76.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro76ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro76);

        rSButtonMetro77.setText("U");
        rSButtonMetro77.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro77.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro77.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro77.setInheritsPopupMenu(true);
        rSButtonMetro77.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro77ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro77);

        rSButtonMetro78.setText("V");
        rSButtonMetro78.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro78.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro78.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro78.setInheritsPopupMenu(true);
        rSButtonMetro78.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro78ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro78);

        rSButtonMetro79.setText("X");
        rSButtonMetro79.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro79.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro79.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro79.setInheritsPopupMenu(true);
        rSButtonMetro79.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro79ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro79);

        rSButtonMetro80.setText("Y");
        rSButtonMetro80.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro80.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro80.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro80.setInheritsPopupMenu(true);
        rSButtonMetro80.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro80ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro80);

        rSButtonMetro81.setText("Z");
        rSButtonMetro81.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro81.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro81.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro81.setInheritsPopupMenu(true);
        rSButtonMetro81.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro81ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro81);

        rSButtonMetro82.setText("BIS");
        rSButtonMetro82.setFont(new java.awt.Font("Segoe UI Semibold", 2, 11)); // NOI18N
        rSButtonMetro82.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        rSButtonMetro82.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        rSButtonMetro82.setInheritsPopupMenu(true);
        rSButtonMetro82.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetro82ActionPerformed(evt);
            }
        });
        jPanelArriba2.add(rSButtonMetro82);

        jPanelMetro.add(jPanelArriba2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 800, 330));

        jLabelNombre6.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
        jLabelNombre6.setForeground(new java.awt.Color(0, 112, 192));
        jLabelNombre6.setText("Nombre:");

        jTextFieldNombre6.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldNombre6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 112, 192), 2));
        jTextFieldNombre6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNombre6ActionPerformed(evt);
            }
        });

        jTextFieldIngreso.setEditable(false);
        jTextFieldIngreso.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldIngreso.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldIngreso.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 112, 192), 2));

        rSButtonMetroIngresar.setText("Ingresar Nombre");
        rSButtonMetroIngresar.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
        rSButtonMetroIngresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroIngresarActionPerformed(evt);
            }
        });

        rSButtonMetroBorrar.setText("Borrar Ultimo");
        rSButtonMetroBorrar.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
        rSButtonMetroBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroBorrarActionPerformed(evt);
            }
        });

        rSButtonMetroBorrarTodo.setText("Borrar Todo");
        rSButtonMetroBorrarTodo.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
        rSButtonMetroBorrarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroBorrarTodoActionPerformed(evt);
            }
        });

        rSButtonMetroFinalizar.setText("Finalizar");
        rSButtonMetroFinalizar.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
        rSButtonMetroFinalizar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                rSButtonMetroFinalizarMousePressed(evt);
            }
        });
        rSButtonMetroFinalizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroFinalizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanelAbajo2Layout = new javax.swing.GroupLayout(jPanelAbajo2);
        jPanelAbajo2.setLayout(jPanelAbajo2Layout);
        jPanelAbajo2Layout.setHorizontalGroup(
            jPanelAbajo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAbajo2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanelAbajo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTextFieldIngreso)
                    .addGroup(jPanelAbajo2Layout.createSequentialGroup()
                        .addComponent(jLabelNombre6, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextFieldNombre6, javax.swing.GroupLayout.PREFERRED_SIZE, 272, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(rSButtonMetroIngresar, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rSButtonMetroBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rSButtonMetroBorrarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(rSButtonMetroFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanelAbajo2Layout.setVerticalGroup(
            jPanelAbajo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanelAbajo2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jTextFieldIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanelAbajo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rSButtonMetroIngresar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanelAbajo2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabelNombre6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTextFieldNombre6, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rSButtonMetroBorrarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rSButtonMetroBorrar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rSButtonMetroFinalizar, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );

        jPanelMetro.add(jPanelAbajo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 800, 80));

        jSeparatorEmpleado3.setForeground(new java.awt.Color(255, 255, 255));
        jSeparatorEmpleado3.setPreferredSize(new java.awt.Dimension(240, 4));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowActivated(java.awt.event.WindowEvent evt) {
                formWindowActivated(evt);
            }
        });

        jPanelPpal.setLayout(new java.awt.CardLayout());

        jPanelMenu.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelTitulo.setBackground(new java.awt.Color(51, 152, 219));
        jPanelTitulo.setOpaque(false);
        jPanelTitulo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelPerfil.setBackground(new java.awt.Color(51, 152, 219));
        jPanelPerfil.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelTitulo.setFont(new java.awt.Font("Segoe UI Light", 0, 48)); // NOI18N
        jLabelTitulo.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTitulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelTitulo.setText("Admin Portal");
        jLabelTitulo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanelPerfil.add(jLabelTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, 680, -1));

        jPanelTitulo.add(jPanelPerfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 60, 660, 100));

        jLabelimgPerfil.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu/Admin/admin_256px.png"))); // NOI18N
        jPanelTitulo.add(jLabelimgPerfil, new org.netbeans.lib.awtextra.AbsoluteConstraints(-30, 0, 240, 210));

        jPanelMenu.add(jPanelTitulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 910, 210));

        jPanelRol.setBackground(new java.awt.Color(52, 152, 219));
        jPanelRol.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelRol2.setBackground(new java.awt.Color(52, 152, 219));
        jLabelRol2.setFont(new java.awt.Font("Segoe UI Semilight", 0, 34)); // NOI18N
        jLabelRol2.setForeground(new java.awt.Color(255, 255, 255));
        jLabelRol2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelRol2.setText("Bienvenido");
        jPanelRol.add(jLabelRol2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 110, 210, 80));

        jPanelMenu.add(jPanelRol, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 210, 210, 390));

        jPanelUsuarios.setBackground(new java.awt.Color(255, 255, 255));
        jPanelUsuarios.setToolTipText("Gestión de usuarios");
        jPanelUsuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jPanelUsuariosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jPanelUsuariosMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jPanelUsuariosMouseExited(evt);
            }
        });
        jPanelUsuarios.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelUsuarios.setBackground(new java.awt.Color(255, 255, 255));
        jLabelUsuarios.setFont(new java.awt.Font("Segoe UI Semilight", 1, 18)); // NOI18N
        jLabelUsuarios.setForeground(new java.awt.Color(52, 152, 219));
        jLabelUsuarios.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelUsuarios.setText("Dashboard");
        jPanelUsuarios.add(jLabelUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 150, 30));

        jLabelimgUsuarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu/Admin/usuarios_100px.png"))); // NOI18N
        jPanelUsuarios.add(jLabelimgUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, 120));

        jPanelMenu.add(jPanelUsuarios, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 220, 150, 170));

        rSButtonCerrarSesion.setBackground(new java.awt.Color(52, 152, 219));
        rSButtonCerrarSesion.setToolTipText("Cerrar sesión");
        rSButtonCerrarSesion.setColorHover(new java.awt.Color(56, 166, 239));
        rSButtonCerrarSesion.setColorNormal(new java.awt.Color(52, 152, 219));
        rSButtonCerrarSesion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSButtonCerrarSesionMouseClicked(evt);
            }
        });
        rSButtonCerrarSesion.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelimgCerrarSesion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Global/salir_100px.png"))); // NOI18N
        rSButtonCerrarSesion.add(jLabelimgCerrarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, 120));

        jPanelMenu.add(rSButtonCerrarSesion, new org.netbeans.lib.awtextra.AbsoluteConstraints(740, 400, 150, 170));

        jPanelPpal.add(jPanelMenu, "card2");

        jPanelStaff.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanelTituloUsuario.setBackground(new java.awt.Color(51, 152, 219));
        jPanelTituloUsuario.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelTituloUsuario.setFont(new java.awt.Font("Segoe UI Light", 0, 36)); // NOI18N
        jLabelTituloUsuario.setForeground(new java.awt.Color(255, 255, 255));
        jLabelTituloUsuario.setText("DASHBOARD");
        jPanelTituloUsuario.add(jLabelTituloUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, 520, -1));

        jLabelimgUsuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu/Admin/administracionUser_100px.png"))); // NOI18N
        jPanelTituloUsuario.add(jLabelimgUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 0, 110, 90));

        jButtonSlider.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Global/menu_48px.png"))); // NOI18N
        jButtonSlider.setContentAreaFilled(false);
        jButtonSlider.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButtonSlider.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonSliderActionPerformed(evt);
            }
        });
        jPanelTituloUsuario.add(jButtonSlider, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 90, 70));

        jPanelStaff.add(jPanelTituloUsuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 900, 90));

        jPanelSlider.setBackground(new java.awt.Color(153, 153, 153));
        jPanelSlider.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelPaciente.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelPaciente.setForeground(new java.awt.Color(255, 255, 255));
        jLabelPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu/Admin/paciente_50px.png"))); // NOI18N
        jLabelPaciente.setText("PROVEEDORES");
        jPanelSlider.add(jLabelPaciente, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 50));

        jLabelEmpleado.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelEmpleado.setForeground(new java.awt.Color(255, 255, 255));
        jLabelEmpleado.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu/Admin/tarjetaempleado_50px.png"))); // NOI18N
        jLabelEmpleado.setText("CLIENTES");
        jPanelSlider.add(jLabelEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 150, 240, 50));

        jSeparatorPaciente.setForeground(new java.awt.Color(255, 255, 255));
        jSeparatorPaciente.setPreferredSize(new java.awt.Dimension(240, 4));
        jPanelSlider.add(jSeparatorPaciente, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, -1, -1));

        jSeparatorEmpleado4.setForeground(new java.awt.Color(255, 255, 255));
        jSeparatorEmpleado4.setPreferredSize(new java.awt.Dimension(240, 4));
        jPanelSlider.add(jSeparatorEmpleado4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, -1, -1));

        jSeparatorEmpleado6.setForeground(new java.awt.Color(255, 255, 255));
        jSeparatorEmpleado6.setPreferredSize(new java.awt.Dimension(240, 4));
        jPanelSlider.add(jSeparatorEmpleado6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 200, -1, -1));

        rSButtonPaneHome.setForeground(new java.awt.Color(255, 255, 255));
        rSButtonPaneHome.setColorHover(new java.awt.Color(102, 102, 102));
        rSButtonPaneHome.setColorNormal(new java.awt.Color(153, 153, 153));
        rSButtonPaneHome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSButtonPaneHomeMouseClicked(evt);
            }
        });
        rSButtonPaneHome.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelHome.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelHome.setForeground(new java.awt.Color(255, 255, 255));
        jLabelHome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Global/home_50px.png"))); // NOI18N
        jLabelHome.setText("INICIO");
        jLabelHome.setBorder(new javax.swing.border.SoftBevelBorder(1, null, new java.awt.Color(255, 255, 255), null, null));
        jLabelHome.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        rSButtonPaneHome.add(jLabelHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 40));

        jPanelSlider.add(rSButtonPaneHome, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, 240, -1));

        rSButtonAgregarProveedor.setForeground(new java.awt.Color(255, 255, 255));
        rSButtonAgregarProveedor.setColorHover(new java.awt.Color(102, 102, 102));
        rSButtonAgregarProveedor.setColorNormal(new java.awt.Color(153, 153, 153));
        rSButtonAgregarProveedor.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSButtonAgregarProveedorMouseClicked(evt);
            }
        });
        rSButtonAgregarProveedor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelAgregarPaciente.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
        jLabelAgregarPaciente.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAgregarPaciente.setText(" Agregar Proveedor");
        rSButtonAgregarProveedor.add(jLabelAgregarPaciente, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 40));

        jPanelSlider.add(rSButtonAgregarProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 60, 240, -1));

        rSButtonModificarPaciente.setForeground(new java.awt.Color(255, 255, 255));
        rSButtonModificarPaciente.setColorHover(new java.awt.Color(102, 102, 102));
        rSButtonModificarPaciente.setColorNormal(new java.awt.Color(153, 153, 153));
        rSButtonModificarPaciente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSButtonModificarPacienteMouseClicked(evt);
            }
        });
        rSButtonModificarPaciente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelModificarProveedor.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
        jLabelModificarProveedor.setForeground(new java.awt.Color(255, 255, 255));
        jLabelModificarProveedor.setText(" Modificar Proveedor");
        rSButtonModificarPaciente.add(jLabelModificarProveedor, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 240, 40));

        jPanelSlider.add(rSButtonModificarPaciente, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 100, 240, -1));

        rSButtonAgregarCliente.setForeground(new java.awt.Color(255, 255, 255));
        rSButtonAgregarCliente.setColorHover(new java.awt.Color(102, 102, 102));
        rSButtonAgregarCliente.setColorNormal(new java.awt.Color(153, 153, 153));
        rSButtonAgregarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSButtonAgregarClienteMouseClicked(evt);
            }
        });
        rSButtonAgregarCliente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelAgregarEmpleado.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
        jLabelAgregarEmpleado.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAgregarEmpleado.setText(" Agregar Cliente");
        rSButtonAgregarCliente.add(jLabelAgregarEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 240, 40));

        jPanelSlider.add(rSButtonAgregarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 220, 240, -1));

        rSButtonModificarCliente.setForeground(new java.awt.Color(255, 255, 255));
        rSButtonModificarCliente.setColorHover(new java.awt.Color(102, 102, 102));
        rSButtonModificarCliente.setColorNormal(new java.awt.Color(153, 153, 153));
        rSButtonModificarCliente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSButtonModificarClienteMouseClicked(evt);
            }
        });
        rSButtonModificarCliente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelModificarEmpleado.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
        jLabelModificarEmpleado.setForeground(new java.awt.Color(255, 255, 255));
        jLabelModificarEmpleado.setText(" Modificar Cliente");
        rSButtonModificarCliente.add(jLabelModificarEmpleado, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -10, 240, 40));

        jPanelSlider.add(rSButtonModificarCliente, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 260, 240, -1));

        rSButtonAgregarPrenda.setForeground(new java.awt.Color(255, 255, 255));
        rSButtonAgregarPrenda.setColorHover(new java.awt.Color(102, 102, 102));
        rSButtonAgregarPrenda.setColorNormal(new java.awt.Color(153, 153, 153));
        rSButtonAgregarPrenda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSButtonAgregarPrendaMouseClicked(evt);
            }
        });
        rSButtonAgregarPrenda.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelAgregarEmpleado1.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
        jLabelAgregarEmpleado1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelAgregarEmpleado1.setText(" Agregar Prenda");
        rSButtonAgregarPrenda.add(jLabelAgregarEmpleado1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 240, 40));

        jPanelSlider.add(rSButtonAgregarPrenda, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 340, 240, -1));

        rSButtonModificarPrenda.setForeground(new java.awt.Color(255, 255, 255));
        rSButtonModificarPrenda.setColorHover(new java.awt.Color(102, 102, 102));
        rSButtonModificarPrenda.setColorNormal(new java.awt.Color(153, 153, 153));
        rSButtonModificarPrenda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSButtonModificarPrendaMouseClicked(evt);
            }
        });
        rSButtonModificarPrenda.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelModificarEmpleado1.setFont(new java.awt.Font("Segoe UI Semilight", 1, 12)); // NOI18N
        jLabelModificarEmpleado1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelModificarEmpleado1.setText(" Modificar Prenda");
        rSButtonModificarPrenda.add(jLabelModificarEmpleado1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 240, 40));

        jPanelSlider.add(rSButtonModificarPrenda, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 380, 240, -1));

        jLabelEmpleado1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabelEmpleado1.setForeground(new java.awt.Color(255, 255, 255));
        jLabelEmpleado1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/Menu/Admin/icons8-ropa-vegana-50.png"))); // NOI18N
        jLabelEmpleado1.setText("PRENDAS");
        jPanelSlider.add(jLabelEmpleado1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 300, 240, 50));

        jPanelStaff.add(jPanelSlider, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 90, 240, 510));

        jPanelSliders.setLayout(new java.awt.CardLayout());

        jPanelAgregarProveedor.setToolTipText("");
        jPanelAgregarProveedor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelNombre1.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelNombre1.setForeground(new java.awt.Color(51, 152, 219));
        jLabelNombre1.setText("Nombre");
        jPanelAgregarProveedor.add(jLabelNombre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        jLabelCedula1.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelCedula1.setForeground(new java.awt.Color(51, 152, 219));
        jLabelCedula1.setText("Identificación");
        jPanelAgregarProveedor.add(jLabelCedula1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, -1, -1));

        jLabelDireccion1.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelDireccion1.setForeground(new java.awt.Color(51, 152, 219));
        jLabelDireccion1.setText("Dirección");
        jPanelAgregarProveedor.add(jLabelDireccion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 100, -1, -1));

        jLabelTelefono1.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelTelefono1.setForeground(new java.awt.Color(51, 152, 219));
        jLabelTelefono1.setText("Telefono");
        jPanelAgregarProveedor.add(jLabelTelefono1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, -1, -1));

        jLabelActividad.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelActividad.setForeground(new java.awt.Color(51, 152, 219));
        jLabelActividad.setText("Email");
        jPanelAgregarProveedor.add(jLabelActividad, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 190, -1, -1));

        jTextFieldNombre1.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldNombre1.setText("Digite el nombre del proveedor");
        jTextFieldNombre1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldNombre1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldNombre1FocusGained(evt);
            }
        });
        jTextFieldNombre1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldNombre1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextFieldNombre1MouseReleased(evt);
            }
        });
        jTextFieldNombre1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNombre1ActionPerformed(evt);
            }
        });
        jTextFieldNombre1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldNombre1KeyTyped(evt);
            }
        });
        jPanelAgregarProveedor.add(jTextFieldNombre1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 180, 30));

        jTextFieldCedula1.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldCedula1.setText("Digite la identificación del proveedor");
        jTextFieldCedula1.setToolTipText("");
        jTextFieldCedula1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldCedula1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldCedula1FocusGained(evt);
            }
        });
        jTextFieldCedula1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldCedula1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextFieldCedula1MouseReleased(evt);
            }
        });
        jTextFieldCedula1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCedula1ActionPerformed(evt);
            }
        });
        jTextFieldCedula1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldCedula1KeyTyped(evt);
            }
        });
        jPanelAgregarProveedor.add(jTextFieldCedula1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 120, 180, 30));

        jTextFieldDireccion1.setEditable(false);
        jTextFieldDireccion1.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldDireccion1.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldDireccion1.setText("Seleccione la dirección");
        jTextFieldDireccion1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldDireccion1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldDireccion1FocusGained(evt);
            }
        });
        jTextFieldDireccion1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldDireccion1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextFieldDireccion1MouseReleased(evt);
            }
        });
        jTextFieldDireccion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDireccion1ActionPerformed(evt);
            }
        });
        jTextFieldDireccion1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldDireccion1KeyTyped(evt);
            }
        });
        jPanelAgregarProveedor.add(jTextFieldDireccion1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 120, 180, 30));

        jTextFieldTelefono1.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldTelefono1.setText("Digite el teléfono del proveedor");
        jTextFieldTelefono1.setToolTipText("");
        jTextFieldTelefono1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldTelefono1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldTelefono1FocusGained(evt);
            }
        });
        jTextFieldTelefono1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldTelefono1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextFieldTelefono1MouseReleased(evt);
            }
        });
        jTextFieldTelefono1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldTelefono1KeyTyped(evt);
            }
        });
        jPanelAgregarProveedor.add(jTextFieldTelefono1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 180, 30));

        jTextFieldEmail1.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldEmail1.setText("Digite la ocupación del proveedor");
        jTextFieldEmail1.setToolTipText("");
        jTextFieldEmail1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldEmail1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldEmail1FocusGained(evt);
            }
        });
        jTextFieldEmail1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldEmail1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextFieldEmail1MouseReleased(evt);
            }
        });
        jTextFieldEmail1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldEmail1KeyTyped(evt);
            }
        });
        jPanelAgregarProveedor.add(jTextFieldEmail1, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 210, 180, 30));

        rSButtonMetroGuardar1.setBackground(new java.awt.Color(0, 204, 0));
        rSButtonMetroGuardar1.setText("Guardar");
        rSButtonMetroGuardar1.setToolTipText("Guardar datos ingresados");
        rSButtonMetroGuardar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroGuardar1ActionPerformed(evt);
            }
        });
        jPanelAgregarProveedor.add(rSButtonMetroGuardar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 410, 150, 30));

        rSButtonMetroLimpiar1.setBackground(new java.awt.Color(255, 200, 0));
        rSButtonMetroLimpiar1.setText("Limpiar");
        rSButtonMetroLimpiar1.setToolTipText("Limpiar datos ingresados");
        rSButtonMetroLimpiar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroLimpiar1ActionPerformed(evt);
            }
        });
        jPanelAgregarProveedor.add(rSButtonMetroLimpiar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 410, 150, 30));

        jPanelSliders.add(jPanelAgregarProveedor, "card2");

        jPanelModificarProveedor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelBuscar1.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabelBuscar1.setForeground(new java.awt.Color(51, 152, 219));
        jLabelBuscar1.setText("Identificación:");
        jPanelModificarProveedor.add(jLabelBuscar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, 30));

        jTextFieldBuscar1.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldBuscar1.setText(" Digite la identificacion del proveedor");
        jTextFieldBuscar1.setToolTipText("");
        jTextFieldBuscar1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldBuscar1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldBuscar1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextFieldBuscar1MouseReleased(evt);
            }
        });
        jTextFieldBuscar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldBuscar1ActionPerformed(evt);
            }
        });
        jTextFieldBuscar1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTextFieldBuscar1KeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldBuscar1KeyTyped(evt);
            }
        });
        jPanelModificarProveedor.add(jTextFieldBuscar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 20, 180, 30));

        rSButtonMetroFiltrar1.setBackground(new java.awt.Color(51, 152, 219));
        rSButtonMetroFiltrar1.setText("Buscar");
        rSButtonMetroFiltrar1.setToolTipText("Buscar paciente en el sistema");
        rSButtonMetroFiltrar1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroFiltrar1ActionPerformed(evt);
            }
        });
        jPanelModificarProveedor.add(rSButtonMetroFiltrar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 20, 110, 30));

        rSTablaProveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "cedula", "nombre", "email"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        rSTablaProveedores.setCellSelectionEnabled(true);
        rSTablaProveedores.setColorBackgoundHead(new java.awt.Color(52, 120, 219));
        rSTablaProveedores.setColorFilasForeground1(new java.awt.Color(52, 152, 219));
        rSTablaProveedores.setColorFilasForeground2(new java.awt.Color(52, 152, 219));
        rSTablaProveedores.setColorSelBackgound(new java.awt.Color(52, 152, 219));
        rSTablaProveedores.setRowHeight(25);
        rSTablaProveedores.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSTablaProveedoresMouseClicked(evt);
            }
        });
        jScrollPaneTablaPacientes.setViewportView(rSTablaProveedores);

        jPanelModificarProveedor.add(jScrollPaneTablaPacientes, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 620, 380));

        rSButtonMetroLimpiar12.setBackground(new java.awt.Color(255, 200, 23));
        rSButtonMetroLimpiar12.setText("Limpiar");
        rSButtonMetroLimpiar12.setToolTipText("Limpiar datos ingresados");
        rSButtonMetroLimpiar12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroLimpiar12ActionPerformed(evt);
            }
        });
        jPanelModificarProveedor.add(rSButtonMetroLimpiar12, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 445, 110, 30));

        rSButtonMetroFiltrar7.setBackground(new java.awt.Color(0, 204, 0));
        rSButtonMetroFiltrar7.setText("Mostrar todo");
        rSButtonMetroFiltrar7.setToolTipText("Mostrar todos los pacientes del sistema");
        rSButtonMetroFiltrar7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroFiltrar7ActionPerformed(evt);
            }
        });
        jPanelModificarProveedor.add(rSButtonMetroFiltrar7, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 20, 110, 30));

        jPanelSliders.add(jPanelModificarProveedor, "card2");

        jPanelAgregarCliente.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                jPanelAgregarClienteComponentShown(evt);
            }
        });
        jPanelAgregarCliente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelNombre.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelNombre.setForeground(new java.awt.Color(51, 152, 219));
        jLabelNombre.setText("Nombre");
        jPanelAgregarCliente.add(jLabelNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 100, -1, -1));

        jLabelCedula.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelCedula.setForeground(new java.awt.Color(51, 152, 219));
        jLabelCedula.setText("Identificación");
        jPanelAgregarCliente.add(jLabelCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 100, -1, -1));

        jLabelDireccion.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelDireccion.setForeground(new java.awt.Color(51, 152, 219));
        jLabelDireccion.setText("Dirección");
        jPanelAgregarCliente.add(jLabelDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 100, -1, -1));

        jLabelTelefono.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelTelefono.setForeground(new java.awt.Color(51, 152, 219));
        jLabelTelefono.setText("Telefono");
        jPanelAgregarCliente.add(jLabelTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 190, -1, -1));

        jTextFieldNombre.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldNombre.setText("Digite el nombre agregar cliente");
        jTextFieldNombre.setToolTipText("");
        jTextFieldNombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldNombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldNombreFocusGained(evt);
            }
        });
        jTextFieldNombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldNombreMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextFieldNombreMouseReleased(evt);
            }
        });
        jTextFieldNombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldNombreKeyTyped(evt);
            }
        });
        jPanelAgregarCliente.add(jTextFieldNombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 180, 30));

        jTextFieldCedula.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldCedula.setText("Digite la identificacion del cliente");
        jTextFieldCedula.setToolTipText("");
        jTextFieldCedula.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldCedula.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldCedulaFocusGained(evt);
            }
        });
        jTextFieldCedula.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldCedulaMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextFieldCedulaMouseReleased(evt);
            }
        });
        jTextFieldCedula.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldCedulaKeyTyped(evt);
            }
        });
        jPanelAgregarCliente.add(jTextFieldCedula, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 120, 180, 30));

        jTextFieldDireccion2.setEditable(false);
        jTextFieldDireccion2.setBackground(new java.awt.Color(255, 255, 255));
        jTextFieldDireccion2.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldDireccion2.setText("Seleccione la dirección");
        jTextFieldDireccion2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldDireccion2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldDireccion2FocusGained(evt);
            }
        });
        jTextFieldDireccion2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldDireccion2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextFieldDireccion2MouseReleased(evt);
            }
        });
        jTextFieldDireccion2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDireccion2ActionPerformed(evt);
            }
        });
        jTextFieldDireccion2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldDireccion2KeyTyped(evt);
            }
        });
        jPanelAgregarCliente.add(jTextFieldDireccion2, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 120, 180, 30));

        jTextFieldTelefono.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldTelefono.setText("Digite el teléfono del cliente");
        jTextFieldTelefono.setToolTipText("");
        jTextFieldTelefono.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldTelefono.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldTelefonoFocusGained(evt);
            }
        });
        jTextFieldTelefono.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldTelefonoMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextFieldTelefonoMouseReleased(evt);
            }
        });
        jTextFieldTelefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldTelefonoKeyTyped(evt);
            }
        });
        jPanelAgregarCliente.add(jTextFieldTelefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 180, 30));

        rSButtonMetroGuardar.setBackground(new java.awt.Color(0, 204, 0));
        rSButtonMetroGuardar.setText("Guardar");
        rSButtonMetroGuardar.setToolTipText("Guardar datos ingresados");
        rSButtonMetroGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroGuardarActionPerformed(evt);
            }
        });
        jPanelAgregarCliente.add(rSButtonMetroGuardar, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 410, 150, 30));

        rSButtonMetroLimpiar.setBackground(new java.awt.Color(255, 200, 0));
        rSButtonMetroLimpiar.setText("Limpiar");
        rSButtonMetroLimpiar.setToolTipText("Limpiar datos ingresados");
        rSButtonMetroLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroLimpiarActionPerformed(evt);
            }
        });
        jPanelAgregarCliente.add(rSButtonMetroLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 410, 150, 30));

        jPanelSliders.add(jPanelAgregarCliente, "card2");

        jPanelModificarCliente.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelBuscar.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabelBuscar.setForeground(new java.awt.Color(51, 152, 219));
        jLabelBuscar.setText("Identificación:");
        jPanelModificarCliente.add(jLabelBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, -1, 30));

        jTextFieldBuscar.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldBuscar.setText("Digite la identificación del cliente");
        jTextFieldBuscar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldBuscar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldBuscarMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextFieldBuscarMouseReleased(evt);
            }
        });
        jTextFieldBuscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldBuscarKeyTyped(evt);
            }
        });
        jPanelModificarCliente.add(jTextFieldBuscar, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 20, 180, 30));

        rSButtonMetroFiltrar.setBackground(new java.awt.Color(51, 152, 219));
        rSButtonMetroFiltrar.setText("Buscar");
        rSButtonMetroFiltrar.setToolTipText("Buscar empleado en el sistema");
        rSButtonMetroFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroFiltrarActionPerformed(evt);
            }
        });
        jPanelModificarCliente.add(rSButtonMetroFiltrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 20, 110, 30));

        rSTablaClientes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "cedula", "nombre", "telefono"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        rSTablaClientes.setColorBackgoundHead(new java.awt.Color(52, 120, 219));
        rSTablaClientes.setColorFilasForeground1(new java.awt.Color(52, 152, 219));
        rSTablaClientes.setColorFilasForeground2(new java.awt.Color(52, 152, 219));
        rSTablaClientes.setColorSelBackgound(new java.awt.Color(52, 152, 219));
        rSTablaClientes.setRowHeight(25);
        rSTablaClientes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSTablaClientesMouseClicked(evt);
            }
        });
        jScrollPaneTablaEmpleados.setViewportView(rSTablaClientes);

        jPanelModificarCliente.add(jScrollPaneTablaEmpleados, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 620, 380));

        rSButtonMetroLimpiar11.setBackground(new java.awt.Color(255, 200, 0));
        rSButtonMetroLimpiar11.setText("Limpiar");
        rSButtonMetroLimpiar11.setToolTipText("Limpiar datos ingresados");
        rSButtonMetroLimpiar11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroLimpiar11ActionPerformed(evt);
            }
        });
        jPanelModificarCliente.add(rSButtonMetroLimpiar11, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 445, 110, 30));

        rSButtonMetroFiltrar8.setBackground(new java.awt.Color(0, 204, 0));
        rSButtonMetroFiltrar8.setText("Mostrar todo");
        rSButtonMetroFiltrar8.setToolTipText("Mostrar todos los empleados del sistema");
        rSButtonMetroFiltrar8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroFiltrar8ActionPerformed(evt);
            }
        });
        jPanelModificarCliente.add(rSButtonMetroFiltrar8, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 20, 110, 30));

        jPanelSliders.add(jPanelModificarCliente, "card2");

        jPanelAgregarPrenda.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelNombre4.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelNombre4.setForeground(new java.awt.Color(51, 152, 219));
        jLabelNombre4.setText("Nombre");
        jPanelAgregarPrenda.add(jLabelNombre4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 40, -1, -1));

        jLabelCodigo2.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelCodigo2.setForeground(new java.awt.Color(51, 152, 219));
        jLabelCodigo2.setText("Codigo");
        jPanelAgregarPrenda.add(jLabelCodigo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 40, -1, -1));

        jTextFieldNombre4.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldNombre4.setText("Ingrese el nombre de la prenda");
        jTextFieldNombre4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldNombre4.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldNombre4FocusGained(evt);
            }
        });
        jTextFieldNombre4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldNombre4MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextFieldNombre4MouseReleased(evt);
            }
        });
        jTextFieldNombre4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNombre4ActionPerformed(evt);
            }
        });
        jTextFieldNombre4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldNombre4KeyTyped(evt);
            }
        });
        jPanelAgregarPrenda.add(jTextFieldNombre4, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 60, 180, 30));

        jTextFieldCodigo2.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldCodigo2.setText(" Ingrese el codigo del empleado");
        jTextFieldCodigo2.setToolTipText("");
        jTextFieldCodigo2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldCodigo2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldCodigo2FocusGained(evt);
            }
        });
        jTextFieldCodigo2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldCodigo2MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextFieldCodigo2MouseReleased(evt);
            }
        });
        jTextFieldCodigo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCodigo2ActionPerformed(evt);
            }
        });
        jTextFieldCodigo2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldCodigo2KeyTyped(evt);
            }
        });
        jPanelAgregarPrenda.add(jTextFieldCodigo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 60, 180, 30));

        rSButtonMetroGuardar4.setBackground(new java.awt.Color(0, 204, 0));
        rSButtonMetroGuardar4.setText("Guardar");
        rSButtonMetroGuardar4.setToolTipText("Guardar datos ingresados");
        rSButtonMetroGuardar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroGuardar4ActionPerformed(evt);
            }
        });
        jPanelAgregarPrenda.add(rSButtonMetroGuardar4, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 410, 150, 30));

        rSButtonMetroLimpiar4.setBackground(new java.awt.Color(255, 200, 0));
        rSButtonMetroLimpiar4.setText("Limpiar");
        rSButtonMetroLimpiar4.setToolTipText("Limpiar datos ingresados ");
        rSButtonMetroLimpiar4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroLimpiar4ActionPerformed(evt);
            }
        });
        jPanelAgregarPrenda.add(rSButtonMetroLimpiar4, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 410, 150, 30));

        jTextFieldTipo.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldTipo.setText("Ingrese el tipo de prenda");
        jTextFieldTipo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldTipo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldTipoFocusGained(evt);
            }
        });
        jTextFieldTipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldTipoMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextFieldTipoMouseReleased(evt);
            }
        });
        jTextFieldTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldTipoActionPerformed(evt);
            }
        });
        jTextFieldTipo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldTipoKeyTyped(evt);
            }
        });
        jPanelAgregarPrenda.add(jTextFieldTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, 180, 30));

        jLabelNombre5.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelNombre5.setForeground(new java.awt.Color(51, 152, 219));
        jLabelNombre5.setText("Tipo ");
        jPanelAgregarPrenda.add(jLabelNombre5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 170, -1, -1));

        jTextFieldCantidad.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldCantidad.setText("Ingrese la cantidad de prendas");
        jTextFieldCantidad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldCantidad.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldCantidadFocusGained(evt);
            }
        });
        jTextFieldCantidad.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldCantidadMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextFieldCantidadMouseReleased(evt);
            }
        });
        jTextFieldCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldCantidadActionPerformed(evt);
            }
        });
        jTextFieldCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldCantidadKeyTyped(evt);
            }
        });
        jPanelAgregarPrenda.add(jTextFieldCantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 190, 180, 30));

        jLabelNombre7.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelNombre7.setForeground(new java.awt.Color(51, 152, 219));
        jLabelNombre7.setText("Cantidad");
        jPanelAgregarPrenda.add(jLabelNombre7, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 170, -1, -1));

        jTextFieldPrecio.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldPrecio.setText("Ingrese el valor unitario por prenda");
        jTextFieldPrecio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldPrecio.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldPrecioFocusGained(evt);
            }
        });
        jTextFieldPrecio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldPrecioMousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextFieldPrecioMouseReleased(evt);
            }
        });
        jTextFieldPrecio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldPrecioActionPerformed(evt);
            }
        });
        jTextFieldPrecio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldPrecioKeyTyped(evt);
            }
        });
        jPanelAgregarPrenda.add(jTextFieldPrecio, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 300, 180, 30));

        jLabelNombre8.setFont(new java.awt.Font("Segoe UI Light", 1, 12)); // NOI18N
        jLabelNombre8.setForeground(new java.awt.Color(51, 152, 219));
        jLabelNombre8.setText("Precio");
        jPanelAgregarPrenda.add(jLabelNombre8, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 280, -1, -1));

        jPanelSliders.add(jPanelAgregarPrenda, "card2");

        jPanelModificarPrenda.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabelBuscar3.setFont(new java.awt.Font("Segoe UI Light", 1, 14)); // NOI18N
        jLabelBuscar3.setForeground(new java.awt.Color(51, 152, 219));
        jLabelBuscar3.setText("Codigo:");
        jPanelModificarPrenda.add(jLabelBuscar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 20, -1, 30));

        jTextFieldBuscar3.setForeground(new java.awt.Color(51, 152, 219));
        jTextFieldBuscar3.setText("Ingrese el codigo de la prenda");
        jTextFieldBuscar3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(32, 131, 255), 2));
        jTextFieldBuscar3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                jTextFieldBuscar3FocusGained(evt);
            }
        });
        jTextFieldBuscar3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTextFieldBuscar3MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTextFieldBuscar3MouseReleased(evt);
            }
        });
        jTextFieldBuscar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldBuscar3ActionPerformed(evt);
            }
        });
        jTextFieldBuscar3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                jTextFieldBuscar3KeyTyped(evt);
            }
        });
        jPanelModificarPrenda.add(jTextFieldBuscar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(130, 20, 180, 30));

        rSButtonMetroLimpiar13.setBackground(new java.awt.Color(255, 200, 0));
        rSButtonMetroLimpiar13.setText("Limpiar");
        rSButtonMetroLimpiar13.setToolTipText("Limpiar datos ingresados");
        rSButtonMetroLimpiar13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroLimpiar13ActionPerformed(evt);
            }
        });
        jPanelModificarPrenda.add(rSButtonMetroLimpiar13, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 445, 110, 30));

        rSButtonMetroFiltrar3.setBackground(new java.awt.Color(51, 152, 219));
        rSButtonMetroFiltrar3.setText("Buscar");
        rSButtonMetroFiltrar3.setToolTipText("Buscar función en el sistema");
        rSButtonMetroFiltrar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroFiltrar3ActionPerformed(evt);
            }
        });
        jPanelModificarPrenda.add(rSButtonMetroFiltrar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, 110, 30));

        rSTablaModificarPrenda.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "codigo", "nombre", "tipo", "cantidad", "precio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        rSTablaModificarPrenda.setColorBackgoundHead(new java.awt.Color(52, 120, 219));
        rSTablaModificarPrenda.setColorFilasForeground1(new java.awt.Color(52, 152, 219));
        rSTablaModificarPrenda.setColorFilasForeground2(new java.awt.Color(52, 152, 219));
        rSTablaModificarPrenda.setColorSelBackgound(new java.awt.Color(52, 152, 219));
        rSTablaModificarPrenda.setRowHeight(25);
        rSTablaModificarPrenda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                rSTablaModificarPrendaMouseClicked(evt);
            }
        });
        jScrollPaneTablaHabilidades.setViewportView(rSTablaModificarPrenda);

        jPanelModificarPrenda.add(jScrollPaneTablaHabilidades, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 620, 380));

        rSButtonMetroFiltrar9.setBackground(new java.awt.Color(0, 204, 0));
        rSButtonMetroFiltrar9.setText("Mostrar todo");
        rSButtonMetroFiltrar9.setToolTipText("Mostrar todos las funciones del sistema");
        rSButtonMetroFiltrar9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rSButtonMetroFiltrar9ActionPerformed(evt);
            }
        });
        jPanelModificarPrenda.add(rSButtonMetroFiltrar9, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 20, 110, 30));

        jPanelSliders.add(jPanelModificarPrenda, "card2");

        jPanelStaff.add(jPanelSliders, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, 660, 510));

        jPanelPpal.add(jPanelStaff, "card4");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 910, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanelPpal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanelPpal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        setBounds(0, 0, 910, 600);
    }// </editor-fold>//GEN-END:initComponents

    private void jPanelUsuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelUsuariosMouseClicked
        jPanelStaff.setVisible(true);
        jPanelMenu.setVisible(false);
    }//GEN-LAST:event_jPanelUsuariosMouseClicked

    private void jPanelUsuariosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelUsuariosMouseEntered
        ponerColor(jPanelUsuarios);
    }//GEN-LAST:event_jPanelUsuariosMouseEntered

    private void jPanelUsuariosMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jPanelUsuariosMouseExited
        repintarColor(jPanelUsuarios);
    }//GEN-LAST:event_jPanelUsuariosMouseExited

    private void rSButtonCerrarSesionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonCerrarSesionMouseClicked
        new jConfirmation(this, true, "<html><center>¿Esta seguro que desea salir ? </center></html>").setVisible(true);
        
        if(jConfirmation.opcion ==1)
        {
            this.setVisible(false);
            new VentanaLogin().setVisible(true);
        } 
    }//GEN-LAST:event_rSButtonCerrarSesionMouseClicked

    private void jButtonSliderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonSliderActionPerformed
        int posicion = jPanelSlider.getX();
        if(posicion > -1){
            Animacion.Animacion.mover_izquierda(0, -264, 1, 1, jPanelSlider);
        }else{
            Animacion.Animacion.mover_derecha(-264, 0, 1, 1, jPanelSlider);
        }
    }//GEN-LAST:event_jButtonSliderActionPerformed

    private void rSButtonPaneHomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonPaneHomeMouseClicked
       jPanelStaff.setVisible(false);
       jPanelMenu.setVisible(true);
    }//GEN-LAST:event_rSButtonPaneHomeMouseClicked

    private void rSButtonAgregarProveedorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonAgregarProveedorMouseClicked
        ocultarSliders(jPanelSliders);
        jPanelAgregarProveedor.setVisible(true);
    }//GEN-LAST:event_rSButtonAgregarProveedorMouseClicked

    private void rSButtonModificarPacienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonModificarPacienteMouseClicked
        ocultarSliders(jPanelSliders);
        jPanelModificarProveedor.setVisible(true);
    }//GEN-LAST:event_rSButtonModificarPacienteMouseClicked

    private void rSButtonAgregarClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonAgregarClienteMouseClicked
        ocultarSliders(jPanelSliders);
        jPanelAgregarCliente.setVisible(true);
    }//GEN-LAST:event_rSButtonAgregarClienteMouseClicked

    private void rSButtonModificarClienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonModificarClienteMouseClicked
        ocultarSliders(jPanelSliders);
        jPanelModificarCliente.setVisible(true);
    }//GEN-LAST:event_rSButtonModificarClienteMouseClicked

    private void rSButtonAgregarPrendaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonAgregarPrendaMouseClicked
        ocultarSliders(jPanelSliders);
        jPanelAgregarPrenda.setVisible(true);
    }//GEN-LAST:event_rSButtonAgregarPrendaMouseClicked

    private void rSButtonModificarPrendaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonModificarPrendaMouseClicked
        ocultarSliders(jPanelSliders);
        jPanelModificarPrenda.setVisible(true);
    }//GEN-LAST:event_rSButtonModificarPrendaMouseClicked

    private void rSButtonMetroLimpiar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroLimpiar1ActionPerformed
        limpiarComponentes(jPanelAgregarProveedor);
        
    }//GEN-LAST:event_rSButtonMetroLimpiar1ActionPerformed

    private void rSButtonMetroLimpiar12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroLimpiar12ActionPerformed
        limpiarComponentes(jPanelModificarProveedor);
        borrarTabla(rSTablaProveedores);
    }//GEN-LAST:event_rSButtonMetroLimpiar12ActionPerformed

    private void rSButtonMetroLimpiar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroLimpiar4ActionPerformed
        limpiarComponentes(jPanelAgregarPrenda);
       
    }//GEN-LAST:event_rSButtonMetroLimpiar4ActionPerformed

    private void jTextFieldNombre1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNombre1KeyTyped
        validacionTotal.limitarCantidadChar(evt, jTextFieldNombre1, 20, 0);
    }//GEN-LAST:event_jTextFieldNombre1KeyTyped

    private void jTextFieldNombre1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldNombre1MousePressed
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldNombre1MousePressed

    private void jTextFieldNombre1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldNombre1MouseReleased
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldNombre1MouseReleased

    private void jTextFieldCedula1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCedula1KeyTyped
        validacionTotal.limitarCantidadChar(evt, jTextFieldCedula1, 10, 0);
    }//GEN-LAST:event_jTextFieldCedula1KeyTyped

    private void jTextFieldCedula1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldCedula1MousePressed
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldCedula1MousePressed

    private void jTextFieldCedula1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldCedula1MouseReleased
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldCedula1MouseReleased

    private void jTextFieldDireccion1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldDireccion1KeyTyped
        validacionTotal.limitarCantidadChar(evt, jTextFieldDireccion1, 20, 0);
    }//GEN-LAST:event_jTextFieldDireccion1KeyTyped

    private void jTextFieldDireccion1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldDireccion1MousePressed
        mostrarPopupMenu1(evt);      
    }//GEN-LAST:event_jTextFieldDireccion1MousePressed

    private void jTextFieldDireccion1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldDireccion1MouseReleased
        mostrarPopupMenu1(evt);   
    }//GEN-LAST:event_jTextFieldDireccion1MouseReleased

    private void jTextFieldTelefono1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldTelefono1MousePressed
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldTelefono1MousePressed

    private void jTextFieldTelefono1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldTelefono1MouseReleased
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldTelefono1MouseReleased

    private void jTextFieldTelefono1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTelefono1KeyTyped
        validacionTotal.limitarCantidadChar(evt, jTextFieldTelefono1, 10, 0);
    }//GEN-LAST:event_jTextFieldTelefono1KeyTyped

    private void jTextFieldEmail1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldEmail1KeyTyped
        validacionTotal.limitarCantidadChar(evt, jTextFieldEmail1 , 20, 0);
    }//GEN-LAST:event_jTextFieldEmail1KeyTyped

    private void jTextFieldEmail1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldEmail1MousePressed
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldEmail1MousePressed

    private void jTextFieldEmail1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldEmail1MouseReleased
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldEmail1MouseReleased

    private void jTextFieldBuscar1KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscar1KeyTyped
        validacionTotal.limitarCantidadChar(evt, jTextFieldBuscar1, 20, 0);
    }//GEN-LAST:event_jTextFieldBuscar1KeyTyped

    private void jTextFieldBuscar1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldBuscar1MousePressed
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldBuscar1MousePressed

    private void jTextFieldBuscar1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldBuscar1MouseReleased
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldBuscar1MouseReleased

    private void jTextFieldBuscarKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscarKeyTyped
        validacionTotal.limitarCantidadChar(evt, jTextFieldBuscar, 20, 0);
    }//GEN-LAST:event_jTextFieldBuscarKeyTyped

    private void jTextFieldBuscarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldBuscarMousePressed
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldBuscarMousePressed

    private void jTextFieldBuscarMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldBuscarMouseReleased
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldBuscarMouseReleased

    private void jTextFieldNombre4KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNombre4KeyTyped
        validacionTotal.limitarCantidadChar(evt, jTextFieldNombre4, 20, 0);
    }//GEN-LAST:event_jTextFieldNombre4KeyTyped

    private void jTextFieldNombre4MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldNombre4MousePressed
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldNombre4MousePressed

    private void jTextFieldNombre4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldNombre4MouseReleased
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldNombre4MouseReleased

    private void jTextFieldCodigo2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCodigo2KeyTyped
        validacionTotal.limitarCantidadChar(evt, jTextFieldCodigo2, 10, 0);
    }//GEN-LAST:event_jTextFieldCodigo2KeyTyped

    private void jTextFieldCodigo2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldCodigo2MousePressed
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldCodigo2MousePressed

    private void jTextFieldCodigo2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldCodigo2MouseReleased
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldCodigo2MouseReleased

    private void jTextFieldBuscar3MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldBuscar3MousePressed
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldBuscar3MousePressed

    private void jTextFieldBuscar3MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldBuscar3MouseReleased
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldBuscar3MouseReleased

    private void jTextFieldBuscar3KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscar3KeyTyped
        validacionTotal.limitarCantidadChar(evt, jTextFieldBuscar3, 20, 0);
    }//GEN-LAST:event_jTextFieldBuscar3KeyTyped

    private void rSButtonMetroFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroFiltrarActionPerformed
        String filtro = jTextFieldBuscar.getText();    
        String arregloValidar1[] = {filtro};
        borrarTabla(rSTablaClientes);
        
        if (!(validacionTotal.validarExcepciones(arregloValidar1, 0, 1, 0).equals("")))
        {
           new jWarning(this, true,"<html><center>"+validacionTotal.validarExcepciones(arregloValidar1, 1, 0, 0)+"</center></html>").setVisible(true);                   
        }
        
        else
        {
            ArrayList <String> cliente = (ArrayList)controladora.mostrarCliente(filtro);
            if(cliente==null)
            {
                new jWarning(this, true,"<html><center>El cliente no existe, intente denuevo</center></html>").setVisible(true);
            }

            else
            {                
                DefaultTableModel modelo = (DefaultTableModel) rSTablaClientes.getModel();
                String cargo = (cliente.get(2));
                String nombre = (cliente.get(1));
                String cedula = (cliente.get(0));
                modelo.addRow (new Object[]{cedula,nombre,cargo}); 
            }
        }
    }//GEN-LAST:event_rSButtonMetroFiltrarActionPerformed

    private void rSTablaClientesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSTablaClientesMouseClicked
        String cadena="";
        DefaultTableModel modelo = (DefaultTableModel)rSTablaClientes.getModel();
        int row = rSTablaClientes.rowAtPoint(evt.getPoint());
        if (row >= 0 && rSTablaClientes.isEnabled())
        {
            for (int i=0; i < rSTablaClientes.getColumnCount();i++)
            {
               cadena=cadena + "," +  modelo.getValueAt(row,i).toString();
            }
        }
             
        String []cedula = cadena.split(",");
        new jConfirmation(this, true, "<html><center>¿Esta seguro que desea editar el cliente ? </center></html>").setVisible(true);      
        if(jConfirmation.opcion ==1)
        {
            ocultarSliders(jPanelSliders);
            jPanelAgregarCliente.setVisible(true);
            editarCliente(cedula[1]);           
        } 
    }//GEN-LAST:event_rSTablaClientesMouseClicked

    private void rSButtonMetroLimpiar11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroLimpiar11ActionPerformed
        limpiarComponentes(jPanelModificarCliente);
        borrarTabla(rSTablaClientes);
    }//GEN-LAST:event_rSButtonMetroLimpiar11ActionPerformed

    private void rSButtonMetroGuardar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroGuardar1ActionPerformed
       
            String nombre, nit, direccion,telefono, email;
            
       
            nombre = jTextFieldNombre1.getText();
            nit = jTextFieldCedula1.getText();
            direccion = jTextFieldDireccion1.getText();
            telefono = jTextFieldTelefono1.getText();   
            email = jTextFieldEmail1.getText();
    
            String arregloValidar1[] = {nombre,direccion,email,nit,telefono,};
            
            if (!(validacionTotal.validarExcepciones(arregloValidar1, 3, 2, 0).equals("")))
            {
               new jWarning(this, true,"<html><center>"+validacionTotal.validarExcepciones(arregloValidar1, 3, 2, 0)+"</center></html>").setVisible(true);                   
            }
     
            else
            {
                if(controladora.existeProveedor(nit)==false)
                {
                    controladora.guardarProveedor(nit, nombre, direccion, telefono,email);
                    new jInformation(this, true, "<html><center> El proveedor se guardo correctamente </center></html>").setVisible(true);
                    limpiarComponentes(jPanelAgregarProveedor);
                }
                
                else if(controladora.existeProveedor(nit))
                {
                    controladora.modificarProveedor(nombre, nit, direccion, telefono,email);
                    new jInformation(this, true, "<html><center> El proveedor se actualizo correctamente </center></html>").setVisible(true);
                }
                    

            }
  
    }//GEN-LAST:event_rSButtonMetroGuardar1ActionPerformed

    private void rSTablaProveedoresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSTablaProveedoresMouseClicked
        String cadena="";
        DefaultTableModel modelo = (DefaultTableModel)rSTablaProveedores.getModel();
        int row = rSTablaProveedores.rowAtPoint(evt.getPoint());
        if (row >= 0 && rSTablaProveedores.isEnabled())
        {
            for (int i=0; i < rSTablaProveedores.getColumnCount();i++)
            {
               cadena=cadena + "," +  modelo.getValueAt(row,i).toString();
            }
        }
             
        String []cedula = cadena.split(",");
        new jConfirmation(this, true, "<html><center>¿Esta seguro que desea editar el proveedor ? </center></html>").setVisible(true);      
        if(jConfirmation.opcion ==1)
        {
            ocultarSliders(jPanelSliders);
            jPanelAgregarProveedor.setVisible(true);
            editarProveedor(cedula[1]);
            
        } 
    }//GEN-LAST:event_rSTablaProveedoresMouseClicked

    private void rSButtonMetroFiltrar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroFiltrar1ActionPerformed
        String filtro = jTextFieldBuscar1.getText();    
        String arregloValidar1[] = {filtro};
        borrarTabla(rSTablaProveedores);
        if (!(validacionTotal.validarExcepciones(arregloValidar1, 0, 1, 0).equals("")))
        {
           new jWarning(this, true,"<html><center>"+validacionTotal.validarExcepciones(arregloValidar1, 0, 1, 0)+"</center></html>").setVisible(true);                   
        }
        
        else
        {
            ArrayList <String> proveedor = (ArrayList)controladora.mostrarProveedor(filtro);
            if(proveedor==null)
            {
                new jWarning(this, true,"<html><center>El proveedor no existe, intente denuevo</center></html>").setVisible(true);
            }

            else
            {                
                DefaultTableModel modelo = (DefaultTableModel) rSTablaProveedores.getModel();
                String cargo = (proveedor.get(2));
                String nombre = (proveedor.get(1));
                String cedula = (proveedor.get(0));
                modelo.addRow (new Object[]{cedula,nombre,cargo}); 
            }
        }
    }//GEN-LAST:event_rSButtonMetroFiltrar1ActionPerformed

    private void rSButtonMetroGuardar4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroGuardar4ActionPerformed
            String nombre = jTextFieldNombre4.getText();
            String codigo = jTextFieldCodigo2.getText();
            String tipo = jTextFieldTipo.getText();
            String cantidad = jTextFieldCantidad.getText();
            String precio = jTextFieldPrecio.getText();
            
            String arregloValidar1[] = {codigo,nombre,tipo,cantidad,precio};
            String arregloValidar2[] = {nombre};
            
            if (!(validacionTotal.validarExcepciones(arregloValidar1, 3, 1, 1).equals("")))
            {
               new jWarning(this, true,"<html><center>"+validacionTotal.validarExcepciones(arregloValidar1, 3, 1, 1)+"</center></html>").setVisible(true);                   
            }

            else if(!(validacionTotal.soloLetras(arregloValidar2).equals("")))
            {
                new jWarning(this, true,"<html><center>"+validacionTotal.soloLetras(arregloValidar2)+"</center></html>").setVisible(true);                   
            }
            
        else
        {
            
            if(controladora.existePrenda(codigo)==false)
                {
                    if(controladora.guardarPrenda(codigo, nombre, tipo, cantidad,precio))
                    {                      
                      
                        new jInformation(this, true, "<html><center> La prenda se guardo correctamente </center></html>").setVisible(true);
                        limpiarComponentes(jPanelAgregarPrenda);         
                    }
                    
                    else
                    {
                        new jWarning(this, true, "<html><center> La prenda no se guardo, ya existe un cliente con la misma identificacion</center></html>").setVisible(true);
                    }
                }
                
                else if(controladora.existePrenda(codigo))
                {
                    controladora.modificarPrenda(codigo, nombre, tipo, cantidad,precio);
                    new jInformation(this, true, "<html><center> La prenda se actualizo correctamente </center></html>").setVisible(true);
                }
        }
    
            
  
    }//GEN-LAST:event_rSButtonMetroGuardar4ActionPerformed

    private void jTextFieldBuscar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBuscar3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldBuscar3ActionPerformed

    private void rSButtonMetroFiltrar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroFiltrar3ActionPerformed
        borrarTabla(rSTablaModificarPrenda);
        String filtro = jTextFieldBuscar3.getText();    
        String arregloValidar1[] = {filtro};
        
        
        if (!(validacionTotal.validarExcepciones(arregloValidar1, 0, 1, 0).equals("")))
        {
           new jWarning(this, true,"<html><center>"+validacionTotal.validarExcepciones(arregloValidar1, 0, 1, 0)+"</center></html>").setVisible(true);                   
        }
        
        else
        {
            ArrayList <String> prenda = (ArrayList)controladora.mostrarPrenda(filtro);
            if(prenda==null)
            {
                new jWarning(this, true,"<html><center>La prenda no existe, intente denuevo</center></html>").setVisible(true);
            }
            else
            {                
                DefaultTableModel modelo = (DefaultTableModel) rSTablaModificarPrenda.getModel();
                String codigo= (prenda.get(0));
                String nombre = (prenda.get(1));
                String tipo =   (prenda.get(2));
                String cantidad = (prenda.get(3));
                String precio = (prenda.get(4));
                
                modelo.addRow (new Object[]{codigo,nombre,tipo,cantidad,precio}); 
            }
        }
    }//GEN-LAST:event_rSButtonMetroFiltrar3ActionPerformed

    private void rSTablaModificarPrendaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSTablaModificarPrendaMouseClicked
        String cadena="";
        DefaultTableModel modelo = (DefaultTableModel)rSTablaModificarPrenda.getModel();
        int row = rSTablaModificarPrenda.rowAtPoint(evt.getPoint());
        if (row >= 0 && rSTablaModificarPrenda.isEnabled())
        {
            for (int i=0; i < rSTablaModificarPrenda.getColumnCount();i++)
            {
               cadena=cadena + "," +  modelo.getValueAt(row,i).toString();
            }
        }
             
        String []cedula = cadena.split(",");
        new jConfirmation(this, true, "<html><center>¿Esta seguro que desea editar la habilidad? </center></html>").setVisible(true);      
        if(jConfirmation.opcion ==1)
        {
            ocultarSliders(jPanelSliders);
            jPanelAgregarPrenda.setVisible(true);
            editarPrenda(cedula[1]);       
        } 
    }//GEN-LAST:event_rSTablaModificarPrendaMouseClicked

    private void jTextFieldNombre4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNombre4ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNombre4ActionPerformed

    private void rSButtonMetroLimpiar13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroLimpiar13ActionPerformed
        limpiarComponentes(jPanelModificarPrenda);
    }//GEN-LAST:event_rSButtonMetroLimpiar13ActionPerformed

    private void formWindowActivated(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowActivated
      
    }//GEN-LAST:event_formWindowActivated

    private void jTextFieldBuscar1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldBuscar1KeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldBuscar1KeyReleased

    private void jTextFieldDireccion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDireccion1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDireccion1ActionPerformed

    private void rSButtonMetro1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro1ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro1.getText());
    }//GEN-LAST:event_rSButtonMetro1ActionPerformed

    private void rSButtonMetro2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro2ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro2.getText());
    }//GEN-LAST:event_rSButtonMetro2ActionPerformed

    private void rSButtonMetro3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro3ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro3.getText());
    }//GEN-LAST:event_rSButtonMetro3ActionPerformed

    private void rSButtonMetro4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro4ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro4.getText());
    }//GEN-LAST:event_rSButtonMetro4ActionPerformed

    private void rSButtonMetro5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro5ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro5.getText());
    }//GEN-LAST:event_rSButtonMetro5ActionPerformed

    private void rSButtonMetro6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro6ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro6.getText());
    }//GEN-LAST:event_rSButtonMetro6ActionPerformed

    private void rSButtonMetro7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro7ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro7.getText());
    }//GEN-LAST:event_rSButtonMetro7ActionPerformed

    private void rSButtonMetro8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro8ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro8.getText());
    }//GEN-LAST:event_rSButtonMetro8ActionPerformed

    private void rSButtonMetro9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro9ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro9.getText());
    }//GEN-LAST:event_rSButtonMetro9ActionPerformed

    private void rSButtonMetro10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro10ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro10.getText());
    }//GEN-LAST:event_rSButtonMetro10ActionPerformed

    private void rSButtonMetro11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro11ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro11.getText());
    }//GEN-LAST:event_rSButtonMetro11ActionPerformed

    private void rSButtonMetro12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro12ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro12.getText());
    }//GEN-LAST:event_rSButtonMetro12ActionPerformed

    private void rSButtonMetro13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro13ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro13.getText());
    }//GEN-LAST:event_rSButtonMetro13ActionPerformed

    private void rSButtonMetro14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro14ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro14.getText());
    }//GEN-LAST:event_rSButtonMetro14ActionPerformed

    private void rSButtonMetro15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro15ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro15.getText());
    }//GEN-LAST:event_rSButtonMetro15ActionPerformed

    private void rSButtonMetro16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro16ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro16.getText());
    }//GEN-LAST:event_rSButtonMetro16ActionPerformed

    private void rSButtonMetro17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro17ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro17.getText());
    }//GEN-LAST:event_rSButtonMetro17ActionPerformed

    private void rSButtonMetro18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro18ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro18.getText());
    }//GEN-LAST:event_rSButtonMetro18ActionPerformed

    private void rSButtonMetro19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro19ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro19.getText());
    }//GEN-LAST:event_rSButtonMetro19ActionPerformed

    private void rSButtonMetro20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro20ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro20.getText());
    }//GEN-LAST:event_rSButtonMetro20ActionPerformed

    private void rSButtonMetro21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro21ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro21.getText());
    }//GEN-LAST:event_rSButtonMetro21ActionPerformed

    private void rSButtonMetro22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro22ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro22.getText());
    }//GEN-LAST:event_rSButtonMetro22ActionPerformed

    private void rSButtonMetro23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro23ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro23.getText());
    }//GEN-LAST:event_rSButtonMetro23ActionPerformed

    private void rSButtonMetro24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro24ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro24.getText());
    }//GEN-LAST:event_rSButtonMetro24ActionPerformed

    private void rSButtonMetro25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro25ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro25.getText());
    }//GEN-LAST:event_rSButtonMetro25ActionPerformed

    private void rSButtonMetro26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro26ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro26.getText());
    }//GEN-LAST:event_rSButtonMetro26ActionPerformed

    private void rSButtonMetro27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro27ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro27.getText());
    }//GEN-LAST:event_rSButtonMetro27ActionPerformed

    private void rSButtonMetro28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro28ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro28.getText());
    }//GEN-LAST:event_rSButtonMetro28ActionPerformed

    private void rSButtonMetro29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro29ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro29.getText());
    }//GEN-LAST:event_rSButtonMetro29ActionPerformed

    private void rSButtonMetro30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro30ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro30.getText());
    }//GEN-LAST:event_rSButtonMetro30ActionPerformed

    private void rSButtonMetro31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro31ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro31.getText());
    }//GEN-LAST:event_rSButtonMetro31ActionPerformed

    private void rSButtonMetro32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro32ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro32.getText());
    }//GEN-LAST:event_rSButtonMetro32ActionPerformed

    private void rSButtonMetro33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro33ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro33.getText());
    }//GEN-LAST:event_rSButtonMetro33ActionPerformed

    private void rSButtonMetro34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro34ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro34.getText());
    }//GEN-LAST:event_rSButtonMetro34ActionPerformed

    private void rSButtonMetro35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro35ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro35.getText());
    }//GEN-LAST:event_rSButtonMetro35ActionPerformed

    private void rSButtonMetro36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro36ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro36.getText());
    }//GEN-LAST:event_rSButtonMetro36ActionPerformed

    private void rSButtonMetro37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro37ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro37.getText());
    }//GEN-LAST:event_rSButtonMetro37ActionPerformed

    private void rSButtonMetro38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro38ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro38.getText());
    }//GEN-LAST:event_rSButtonMetro38ActionPerformed

    private void rSButtonMetro39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro39ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro39.getText());
    }//GEN-LAST:event_rSButtonMetro39ActionPerformed

    private void rSButtonMetro40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro40ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro40.getText());
    }//GEN-LAST:event_rSButtonMetro40ActionPerformed

    private void rSButtonMetro41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro41ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro41.getText());
    }//GEN-LAST:event_rSButtonMetro41ActionPerformed

    private void rSButtonMetro42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro42ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro42.getText());
    }//GEN-LAST:event_rSButtonMetro42ActionPerformed

    private void rSButtonMetro43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro43ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro43.getText());
    }//GEN-LAST:event_rSButtonMetro43ActionPerformed

    private void rSButtonMetro44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro44ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro44.getText());
    }//GEN-LAST:event_rSButtonMetro44ActionPerformed

    private void rSButtonMetro45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro45ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro45.getText());
    }//GEN-LAST:event_rSButtonMetro45ActionPerformed

    private void rSButtonMetro46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro46ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro46.getText());
    }//GEN-LAST:event_rSButtonMetro46ActionPerformed

    private void rSButtonMetro47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro47ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro47.getText());
    }//GEN-LAST:event_rSButtonMetro47ActionPerformed

    private void rSButtonMetro48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro48ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro48.getText());
    }//GEN-LAST:event_rSButtonMetro48ActionPerformed

    private void rSButtonMetro49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro49ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro49.getText());
    }//GEN-LAST:event_rSButtonMetro49ActionPerformed

    private void rSButtonMetro50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro50ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro50.getText());
    }//GEN-LAST:event_rSButtonMetro50ActionPerformed

    private void rSButtonMetro51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro51ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro51.getText());
    }//GEN-LAST:event_rSButtonMetro51ActionPerformed

    private void rSButtonMetro52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro52ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro52.getText());
    }//GEN-LAST:event_rSButtonMetro52ActionPerformed

    private void rSButtonMetro53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro53ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro53.getText());
    }//GEN-LAST:event_rSButtonMetro53ActionPerformed

    private void rSButtonMetro54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro54ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro54.getText());
    }//GEN-LAST:event_rSButtonMetro54ActionPerformed

    private void rSButtonMetro55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro55ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro55.getText());
    }//GEN-LAST:event_rSButtonMetro55ActionPerformed

    private void rSButtonMetro56ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro56ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro56.getText());
    }//GEN-LAST:event_rSButtonMetro56ActionPerformed

    private void rSButtonMetro57ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro57ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro57.getText());
    }//GEN-LAST:event_rSButtonMetro57ActionPerformed

    private void rSButtonMetro58ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro58ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro58.getText());
    }//GEN-LAST:event_rSButtonMetro58ActionPerformed

    private void rSButtonMetro59ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro59ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro59.getText());
    }//GEN-LAST:event_rSButtonMetro59ActionPerformed

    private void rSButtonMetro60ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro60ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro60.getText());
    }//GEN-LAST:event_rSButtonMetro60ActionPerformed

    private void rSButtonMetro61ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro61ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro61.getText());
    }//GEN-LAST:event_rSButtonMetro61ActionPerformed

    private void rSButtonMetro62ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro62ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro62.getText());
    }//GEN-LAST:event_rSButtonMetro62ActionPerformed

    private void rSButtonMetro63ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro63ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro63.getText());
    }//GEN-LAST:event_rSButtonMetro63ActionPerformed

    private void rSButtonMetro64ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro64ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro64.getText());
    }//GEN-LAST:event_rSButtonMetro64ActionPerformed

    private void rSButtonMetro65ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro65ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro65.getText());
    }//GEN-LAST:event_rSButtonMetro65ActionPerformed

    private void rSButtonMetro66ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro66ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro66.getText());
    }//GEN-LAST:event_rSButtonMetro66ActionPerformed

    private void rSButtonMetro67ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro67ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro67.getText());
    }//GEN-LAST:event_rSButtonMetro67ActionPerformed

    private void rSButtonMetro68ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro68ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro68.getText());
    }//GEN-LAST:event_rSButtonMetro68ActionPerformed

    private void rSButtonMetro69ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro69ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro69.getText());
    }//GEN-LAST:event_rSButtonMetro69ActionPerformed

    private void rSButtonMetro70ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro70ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro70.getText());
    }//GEN-LAST:event_rSButtonMetro70ActionPerformed

    private void rSButtonMetro71ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro71ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro71.getText());
    }//GEN-LAST:event_rSButtonMetro71ActionPerformed

    private void rSButtonMetro72ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro72ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro72.getText());
    }//GEN-LAST:event_rSButtonMetro72ActionPerformed

    private void rSButtonMetro73ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro73ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro73.getText());
    }//GEN-LAST:event_rSButtonMetro73ActionPerformed

    private void rSButtonMetro74ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro74ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro74.getText());
    }//GEN-LAST:event_rSButtonMetro74ActionPerformed

    private void rSButtonMetro75ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro75ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro75.getText());
    }//GEN-LAST:event_rSButtonMetro75ActionPerformed

    private void rSButtonMetro76ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro76ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro76.getText());
    }//GEN-LAST:event_rSButtonMetro76ActionPerformed

    private void rSButtonMetro77ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro77ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro77.getText());
    }//GEN-LAST:event_rSButtonMetro77ActionPerformed

    private void rSButtonMetro78ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro78ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro78.getText());
    }//GEN-LAST:event_rSButtonMetro78ActionPerformed

    private void rSButtonMetro79ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro79ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro79.getText());
    }//GEN-LAST:event_rSButtonMetro79ActionPerformed

    private void rSButtonMetro80ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro80ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro80.getText());
    }//GEN-LAST:event_rSButtonMetro80ActionPerformed

    private void rSButtonMetro81ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro81ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro81.getText());
    }//GEN-LAST:event_rSButtonMetro81ActionPerformed

    private void rSButtonMetro82ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetro82ActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+rSButtonMetro82.getText());
    }//GEN-LAST:event_rSButtonMetro82ActionPerformed

    private void jTextFieldNombre6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNombre6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNombre6ActionPerformed

    private void rSButtonMetroIngresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroIngresarActionPerformed
        jTextFieldIngreso.setText(jTextFieldIngreso.getText()+" "+jTextFieldNombre6.getText());
    }//GEN-LAST:event_rSButtonMetroIngresarActionPerformed

    private void rSButtonMetroBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroBorrarActionPerformed
        int pos;
        String s = jTextFieldIngreso.getText().trim();
        pos = s.lastIndexOf(" ");
        if (pos != -1) {
            s = s.substring(0, pos);
        } else {
            s = "";
        }
        jTextFieldIngreso.setText(" "+s);
    }//GEN-LAST:event_rSButtonMetroBorrarActionPerformed

    private void rSButtonMetroBorrarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroBorrarTodoActionPerformed
        jTextFieldNombre.setText("");
        jTextFieldIngreso.setText("");
    }//GEN-LAST:event_rSButtonMetroBorrarTodoActionPerformed

    private void jTextFieldCedula1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCedula1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCedula1ActionPerformed

    private void rSButtonMetroFinalizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroFinalizarActionPerformed
      
    }//GEN-LAST:event_rSButtonMetroFinalizarActionPerformed

    private void rSButtonMetroFinalizarMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_rSButtonMetroFinalizarMousePressed
         jTextFieldDireccion1.setText(jTextFieldIngreso.getText().trim());
         rSPopuMenu2.setVisible(false);
    }//GEN-LAST:event_rSButtonMetroFinalizarMousePressed

    private void jTextFieldNombre1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNombre1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNombre1ActionPerformed

    private void rSButtonMetroFiltrar7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroFiltrar7ActionPerformed
        borrarTabla(rSTablaProveedores);
        try{
            
            controladora.consultarProveedores(rSTablaProveedores); 
        }
        catch(Exception e)
        {
            
        }
    }//GEN-LAST:event_rSButtonMetroFiltrar7ActionPerformed

    private void rSButtonMetroFiltrar8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroFiltrar8ActionPerformed
        borrarTabla(rSTablaClientes);
        try{
            
            controladora.consultarClientes(rSTablaClientes); 
        }
        catch(Exception e)
        {
            
        }
    }//GEN-LAST:event_rSButtonMetroFiltrar8ActionPerformed

    private void rSButtonMetroFiltrar9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroFiltrar9ActionPerformed
        borrarTabla(rSTablaModificarPrenda);
        try{
            
             controladora.consultarPrendas(rSTablaModificarPrenda); 
        }
        catch(Exception e)
        {
            
        }
    }//GEN-LAST:event_rSButtonMetroFiltrar9ActionPerformed

    private void jTextFieldNombre1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldNombre1FocusGained
        jTextFieldNombre1.setText("");
    }//GEN-LAST:event_jTextFieldNombre1FocusGained

    private void jTextFieldCedula1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldCedula1FocusGained
        jTextFieldCedula1.setText("");
    }//GEN-LAST:event_jTextFieldCedula1FocusGained

    private void jTextFieldDireccion1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldDireccion1FocusGained
        
    }//GEN-LAST:event_jTextFieldDireccion1FocusGained

    private void jTextFieldTelefono1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldTelefono1FocusGained
       jTextFieldTelefono1.setText("");
    }//GEN-LAST:event_jTextFieldTelefono1FocusGained

    private void jTextFieldEmail1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldEmail1FocusGained
        jTextFieldEmail1.setText("");
    }//GEN-LAST:event_jTextFieldEmail1FocusGained

    private void jTextFieldNombre4FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldNombre4FocusGained
        jTextFieldNombre4.setText("");
    }//GEN-LAST:event_jTextFieldNombre4FocusGained

    private void jTextFieldCodigo2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldCodigo2FocusGained
        jTextFieldCodigo2.setText("");
    }//GEN-LAST:event_jTextFieldCodigo2FocusGained

    private void jTextFieldBuscar3FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldBuscar3FocusGained
        jTextFieldBuscar3.setText("");
    }//GEN-LAST:event_jTextFieldBuscar3FocusGained

    private void jPanelAgregarClienteComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_jPanelAgregarClienteComponentShown
      
    }//GEN-LAST:event_jPanelAgregarClienteComponentShown

    private void rSButtonMetroLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroLimpiarActionPerformed
        limpiarComponentes(jPanelAgregarCliente);
    }//GEN-LAST:event_rSButtonMetroLimpiarActionPerformed

    private void rSButtonMetroGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rSButtonMetroGuardarActionPerformed
        String nombre, cedula, direccion, telefono, contrasena;

        cedula = jTextFieldCedula.getText();
        nombre = jTextFieldNombre.getText();
        direccion = jTextFieldDireccion2.getText();
        telefono = jTextFieldTelefono.getText();
        contrasena = jTextFieldCedula.getText();

        String arregloValidar1[] = {nombre,direccion,contrasena,cedula,telefono};
        String arregloValidar2[] = {nombre};

        if (!(validacionTotal.validarExcepciones(arregloValidar1, 3, 2, 0).equals("")))
        {
            new jWarning(this, true,"<html><center>"+validacionTotal.validarExcepciones(arregloValidar1, 3, 2, 0)+"</center></html>").setVisible(true);
        }

        else if(!(validacionTotal.soloLetras(arregloValidar2).equals("")))
        {
            new jWarning(this, true,"<html><center>"+validacionTotal.soloLetras(arregloValidar2)+"</center></html>").setVisible(true);
        }

        else
        {
            
            if(controladora.existeCliente(cedula)==false)
                {
                    if(controladora.guardarCliente(cedula, nombre, direccion, telefono,contrasena))
                    {                      
                      
                        new jInformation(this, true, "<html><center> El cliente se guardo correctamente </center></html>").setVisible(true);
                        limpiarComponentes(jPanelAgregarCliente);         
                    }
                    
                    else
                    {
                        new jWarning(this, true, "<html><center> El cliente no se guardo, ya existe un cliente con la misma identificacion</center></html>").setVisible(true);
                    }
                }
                
                else if(controladora.existeCliente(cedula))
                {
                    controladora.modificarCliente(cedula, nombre, direccion, telefono,contrasena);
                    new jInformation(this, true, "<html><center> El cliente se actualizo correctamente </center></html>").setVisible(true);
                }
        }

    }//GEN-LAST:event_rSButtonMetroGuardarActionPerformed

    private void jTextFieldTelefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTelefonoKeyTyped
        validacionTotal.limitarCantidadChar(evt, jTextFieldTelefono, 10, 0);
    }//GEN-LAST:event_jTextFieldTelefonoKeyTyped

    private void jTextFieldTelefonoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldTelefonoMouseReleased
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldTelefonoMouseReleased

    private void jTextFieldTelefonoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldTelefonoMousePressed
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldTelefonoMousePressed

    private void jTextFieldTelefonoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldTelefonoFocusGained
        jTextFieldTelefono.setText("");
    }//GEN-LAST:event_jTextFieldTelefonoFocusGained

    private void jTextFieldCedulaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCedulaKeyTyped
        validacionTotal.limitarCantidadChar(evt, jTextFieldCedula, 10, 0);
    }//GEN-LAST:event_jTextFieldCedulaKeyTyped

    private void jTextFieldCedulaMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldCedulaMouseReleased
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldCedulaMouseReleased

    private void jTextFieldCedulaMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldCedulaMousePressed
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldCedulaMousePressed

    private void jTextFieldCedulaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldCedulaFocusGained
        jTextFieldCedula.setText("");
    }//GEN-LAST:event_jTextFieldCedulaFocusGained

    private void jTextFieldNombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldNombreKeyTyped
        validacionTotal.limitarCantidadChar(evt, jTextFieldNombre, 20, 0);
    }//GEN-LAST:event_jTextFieldNombreKeyTyped

    private void jTextFieldNombreMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldNombreMouseReleased
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldNombreMouseReleased

    private void jTextFieldNombreMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldNombreMousePressed
        mostrarPopupMenu(evt);
    }//GEN-LAST:event_jTextFieldNombreMousePressed

    private void jTextFieldNombreFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldNombreFocusGained
        jTextFieldNombre.setText("");
    }//GEN-LAST:event_jTextFieldNombreFocusGained

    private void jTextFieldCodigo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCodigo2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCodigo2ActionPerformed

    private void jTextFieldTipoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldTipoFocusGained
        jTextFieldTipo.setText("");
    }//GEN-LAST:event_jTextFieldTipoFocusGained

    private void jTextFieldTipoMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldTipoMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTipoMousePressed

    private void jTextFieldTipoMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldTipoMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTipoMouseReleased

    private void jTextFieldTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldTipoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTipoActionPerformed

    private void jTextFieldTipoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldTipoKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldTipoKeyTyped

    private void jTextFieldCantidadFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldCantidadFocusGained
        jTextFieldCantidad.setText("");
    }//GEN-LAST:event_jTextFieldCantidadFocusGained

    private void jTextFieldCantidadMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldCantidadMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCantidadMousePressed

    private void jTextFieldCantidadMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldCantidadMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCantidadMouseReleased

    private void jTextFieldCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldCantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCantidadActionPerformed

    private void jTextFieldCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldCantidadKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldCantidadKeyTyped

    private void jTextFieldPrecioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldPrecioFocusGained
       jTextFieldPrecio.setText("");
    }//GEN-LAST:event_jTextFieldPrecioFocusGained

    private void jTextFieldPrecioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldPrecioMousePressed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPrecioMousePressed

    private void jTextFieldPrecioMouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldPrecioMouseReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPrecioMouseReleased

    private void jTextFieldPrecioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldPrecioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPrecioActionPerformed

    private void jTextFieldPrecioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldPrecioKeyTyped
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldPrecioKeyTyped

    private void jTextFieldBuscar1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldBuscar1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldBuscar1ActionPerformed

    private void jTextFieldDireccion2FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_jTextFieldDireccion2FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDireccion2FocusGained

    private void jTextFieldDireccion2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldDireccion2MousePressed
        mostrarPopupMenu1(evt);  
    }//GEN-LAST:event_jTextFieldDireccion2MousePressed

    private void jTextFieldDireccion2MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTextFieldDireccion2MouseReleased
        mostrarPopupMenu1(evt); 
    }//GEN-LAST:event_jTextFieldDireccion2MouseReleased

    private void jTextFieldDireccion2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDireccion2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDireccion2ActionPerformed

    private void jTextFieldDireccion2KeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTextFieldDireccion2KeyTyped
          validacionTotal.limitarCantidadChar(evt, jTextFieldDireccion1, 20, 0);
    }//GEN-LAST:event_jTextFieldDireccion2KeyTyped

//=======================================================================================================

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroupFormato;
    private javax.swing.ButtonGroup buttonGroupTipo;
    public static javax.swing.JButton jButtonSlider;
    private javax.swing.JLabel jLabelActividad;
    private javax.swing.JLabel jLabelAgregarEmpleado;
    private javax.swing.JLabel jLabelAgregarEmpleado1;
    private javax.swing.JLabel jLabelAgregarPaciente;
    private javax.swing.JLabel jLabelBuscar;
    private javax.swing.JLabel jLabelBuscar1;
    private javax.swing.JLabel jLabelBuscar3;
    private javax.swing.JLabel jLabelCedula;
    private javax.swing.JLabel jLabelCedula1;
    private javax.swing.JLabel jLabelCodigo2;
    private javax.swing.JLabel jLabelDireccion;
    private javax.swing.JLabel jLabelDireccion1;
    private javax.swing.JLabel jLabelEmpleado;
    private javax.swing.JLabel jLabelEmpleado1;
    private javax.swing.JLabel jLabelHome;
    private javax.swing.JLabel jLabelModificarEmpleado;
    private javax.swing.JLabel jLabelModificarEmpleado1;
    private javax.swing.JLabel jLabelModificarProveedor;
    private javax.swing.JLabel jLabelNombre;
    private javax.swing.JLabel jLabelNombre1;
    private javax.swing.JLabel jLabelNombre4;
    private javax.swing.JLabel jLabelNombre5;
    private javax.swing.JLabel jLabelNombre6;
    private javax.swing.JLabel jLabelNombre7;
    private javax.swing.JLabel jLabelNombre8;
    private javax.swing.JLabel jLabelPaciente;
    private javax.swing.JLabel jLabelRol2;
    private javax.swing.JLabel jLabelTelefono;
    private javax.swing.JLabel jLabelTelefono1;
    private javax.swing.JLabel jLabelTitulo;
    private javax.swing.JLabel jLabelTituloUsuario;
    private javax.swing.JLabel jLabelUsuarios;
    private javax.swing.JLabel jLabelimgCerrarSesion;
    private javax.swing.JLabel jLabelimgPerfil;
    private javax.swing.JLabel jLabelimgUsuario;
    private javax.swing.JLabel jLabelimgUsuarios;
    private javax.swing.JPanel jPanelAbajo2;
    private javax.swing.JPanel jPanelAgregarCliente;
    private javax.swing.JPanel jPanelAgregarPrenda;
    private javax.swing.JPanel jPanelAgregarProveedor;
    private javax.swing.JPanel jPanelArriba2;
    private javax.swing.JPanel jPanelMenu;
    private javax.swing.JPanel jPanelMetro;
    private javax.swing.JPanel jPanelModificarCliente;
    private javax.swing.JPanel jPanelModificarPrenda;
    private javax.swing.JPanel jPanelModificarProveedor;
    private javax.swing.JPanel jPanelPerfil;
    private javax.swing.JPanel jPanelPpal;
    private javax.swing.JPanel jPanelRol;
    private javax.swing.JPanel jPanelSlider;
    private javax.swing.JPanel jPanelSliders;
    private javax.swing.JPanel jPanelStaff;
    private javax.swing.JPanel jPanelTitulo;
    private javax.swing.JPanel jPanelTituloUsuario;
    private javax.swing.JPanel jPanelUsuarios;
    private javax.swing.JScrollPane jScrollPaneTablaEmpleados;
    private javax.swing.JScrollPane jScrollPaneTablaHabilidades;
    private javax.swing.JScrollPane jScrollPaneTablaPacientes;
    private javax.swing.JSeparator jSeparatorEmpleado3;
    private javax.swing.JSeparator jSeparatorEmpleado4;
    private javax.swing.JSeparator jSeparatorEmpleado6;
    private javax.swing.JSeparator jSeparatorPaciente;
    private javax.swing.JTextField jTextFieldBuscar;
    private javax.swing.JTextField jTextFieldBuscar1;
    private javax.swing.JTextField jTextFieldBuscar3;
    private javax.swing.JTextField jTextFieldCantidad;
    private javax.swing.JTextField jTextFieldCedula;
    private javax.swing.JTextField jTextFieldCedula1;
    private javax.swing.JTextField jTextFieldCodigo2;
    private javax.swing.JTextField jTextFieldDireccion1;
    private javax.swing.JTextField jTextFieldDireccion2;
    private javax.swing.JTextField jTextFieldEmail1;
    private javax.swing.JTextField jTextFieldIngreso;
    private javax.swing.JTextField jTextFieldNombre;
    private javax.swing.JTextField jTextFieldNombre1;
    private javax.swing.JTextField jTextFieldNombre4;
    private javax.swing.JTextField jTextFieldNombre6;
    private javax.swing.JTextField jTextFieldPrecio;
    private javax.swing.JTextField jTextFieldTelefono;
    private javax.swing.JTextField jTextFieldTelefono1;
    private javax.swing.JTextField jTextFieldTipo;
    private rojerusan.RSButtonPane rSButtonAgregarCliente;
    private rojerusan.RSButtonPane rSButtonAgregarPrenda;
    private rojerusan.RSButtonPane rSButtonAgregarProveedor;
    private rojerusan.RSButtonPane rSButtonCerrarSesion;
    private rojerusan.RSButtonMetro rSButtonMetro1;
    private rojerusan.RSButtonMetro rSButtonMetro10;
    private rojerusan.RSButtonMetro rSButtonMetro11;
    private rojerusan.RSButtonMetro rSButtonMetro12;
    private rojerusan.RSButtonMetro rSButtonMetro13;
    private rojerusan.RSButtonMetro rSButtonMetro14;
    private rojerusan.RSButtonMetro rSButtonMetro15;
    private rojerusan.RSButtonMetro rSButtonMetro16;
    private rojerusan.RSButtonMetro rSButtonMetro17;
    private rojerusan.RSButtonMetro rSButtonMetro18;
    private rojerusan.RSButtonMetro rSButtonMetro19;
    private rojerusan.RSButtonMetro rSButtonMetro2;
    private rojerusan.RSButtonMetro rSButtonMetro20;
    private rojerusan.RSButtonMetro rSButtonMetro21;
    private rojerusan.RSButtonMetro rSButtonMetro22;
    private rojerusan.RSButtonMetro rSButtonMetro23;
    private rojerusan.RSButtonMetro rSButtonMetro24;
    private rojerusan.RSButtonMetro rSButtonMetro25;
    private rojerusan.RSButtonMetro rSButtonMetro26;
    private rojerusan.RSButtonMetro rSButtonMetro27;
    private rojerusan.RSButtonMetro rSButtonMetro28;
    private rojerusan.RSButtonMetro rSButtonMetro29;
    private rojerusan.RSButtonMetro rSButtonMetro3;
    private rojerusan.RSButtonMetro rSButtonMetro30;
    private rojerusan.RSButtonMetro rSButtonMetro31;
    private rojerusan.RSButtonMetro rSButtonMetro32;
    private rojerusan.RSButtonMetro rSButtonMetro33;
    private rojerusan.RSButtonMetro rSButtonMetro34;
    private rojerusan.RSButtonMetro rSButtonMetro35;
    private rojerusan.RSButtonMetro rSButtonMetro36;
    private rojerusan.RSButtonMetro rSButtonMetro37;
    private rojerusan.RSButtonMetro rSButtonMetro38;
    private rojerusan.RSButtonMetro rSButtonMetro39;
    private rojerusan.RSButtonMetro rSButtonMetro4;
    private rojerusan.RSButtonMetro rSButtonMetro40;
    private rojerusan.RSButtonMetro rSButtonMetro41;
    private rojerusan.RSButtonMetro rSButtonMetro42;
    private rojerusan.RSButtonMetro rSButtonMetro43;
    private rojerusan.RSButtonMetro rSButtonMetro44;
    private rojerusan.RSButtonMetro rSButtonMetro45;
    private rojerusan.RSButtonMetro rSButtonMetro46;
    private rojerusan.RSButtonMetro rSButtonMetro47;
    private rojerusan.RSButtonMetro rSButtonMetro48;
    private rojerusan.RSButtonMetro rSButtonMetro49;
    private rojerusan.RSButtonMetro rSButtonMetro5;
    private rojerusan.RSButtonMetro rSButtonMetro50;
    private rojerusan.RSButtonMetro rSButtonMetro51;
    private rojerusan.RSButtonMetro rSButtonMetro52;
    private rojerusan.RSButtonMetro rSButtonMetro53;
    private rojerusan.RSButtonMetro rSButtonMetro54;
    private rojerusan.RSButtonMetro rSButtonMetro55;
    private rojerusan.RSButtonMetro rSButtonMetro56;
    private rojerusan.RSButtonMetro rSButtonMetro57;
    private rojerusan.RSButtonMetro rSButtonMetro58;
    private rojerusan.RSButtonMetro rSButtonMetro59;
    private rojerusan.RSButtonMetro rSButtonMetro6;
    private rojerusan.RSButtonMetro rSButtonMetro60;
    private rojerusan.RSButtonMetro rSButtonMetro61;
    private rojerusan.RSButtonMetro rSButtonMetro62;
    private rojerusan.RSButtonMetro rSButtonMetro63;
    private rojerusan.RSButtonMetro rSButtonMetro64;
    private rojerusan.RSButtonMetro rSButtonMetro65;
    private rojerusan.RSButtonMetro rSButtonMetro66;
    private rojerusan.RSButtonMetro rSButtonMetro67;
    private rojerusan.RSButtonMetro rSButtonMetro68;
    private rojerusan.RSButtonMetro rSButtonMetro69;
    private rojerusan.RSButtonMetro rSButtonMetro7;
    private rojerusan.RSButtonMetro rSButtonMetro70;
    private rojerusan.RSButtonMetro rSButtonMetro71;
    private rojerusan.RSButtonMetro rSButtonMetro72;
    private rojerusan.RSButtonMetro rSButtonMetro73;
    private rojerusan.RSButtonMetro rSButtonMetro74;
    private rojerusan.RSButtonMetro rSButtonMetro75;
    private rojerusan.RSButtonMetro rSButtonMetro76;
    private rojerusan.RSButtonMetro rSButtonMetro77;
    private rojerusan.RSButtonMetro rSButtonMetro78;
    private rojerusan.RSButtonMetro rSButtonMetro79;
    private rojerusan.RSButtonMetro rSButtonMetro8;
    private rojerusan.RSButtonMetro rSButtonMetro80;
    private rojerusan.RSButtonMetro rSButtonMetro81;
    private rojerusan.RSButtonMetro rSButtonMetro82;
    private rojerusan.RSButtonMetro rSButtonMetro9;
    private rojerusan.RSButtonMetro rSButtonMetroBorrar;
    private rojerusan.RSButtonMetro rSButtonMetroBorrarTodo;
    private rojerusan.RSButtonMetro rSButtonMetroFiltrar;
    private rojerusan.RSButtonMetro rSButtonMetroFiltrar1;
    private rojerusan.RSButtonMetro rSButtonMetroFiltrar3;
    private rojerusan.RSButtonMetro rSButtonMetroFiltrar7;
    private rojerusan.RSButtonMetro rSButtonMetroFiltrar8;
    private rojerusan.RSButtonMetro rSButtonMetroFiltrar9;
    private rojerusan.RSButtonMetro rSButtonMetroFinalizar;
    private rojerusan.RSButtonMetro rSButtonMetroGuardar;
    private rojerusan.RSButtonMetro rSButtonMetroGuardar1;
    private rojerusan.RSButtonMetro rSButtonMetroGuardar4;
    private rojerusan.RSButtonMetro rSButtonMetroIngresar;
    private rojerusan.RSButtonMetro rSButtonMetroLimpiar;
    private rojerusan.RSButtonMetro rSButtonMetroLimpiar1;
    private rojerusan.RSButtonMetro rSButtonMetroLimpiar11;
    private rojerusan.RSButtonMetro rSButtonMetroLimpiar12;
    private rojerusan.RSButtonMetro rSButtonMetroLimpiar13;
    private rojerusan.RSButtonMetro rSButtonMetroLimpiar4;
    private rojerusan.RSButtonPane rSButtonModificarCliente;
    private rojerusan.RSButtonPane rSButtonModificarPaciente;
    private rojerusan.RSButtonPane rSButtonModificarPrenda;
    private rojerusan.RSButtonPane rSButtonPaneHome;
    private rojerusan.RSPopuMenu rSPopuMenu1;
    private rojerusan.RSPopuMenu rSPopuMenu2;
    private rojerusan.RSTableMetro rSTablaClientes;
    private rojerusan.RSTableMetro rSTablaModificarPrenda;
    private rojerusan.RSTableMetro rSTablaProveedores;
    // End of variables declaration//GEN-END:variables

  

}    

