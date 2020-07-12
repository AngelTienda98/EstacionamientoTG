/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import Clases.ConsultaSql;
import static Clases.ConsultaSql.ps;
import Clases.RenderPintar;
import static DataBase.getConexion.getConexion;
import java.sql.PreparedStatement;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.ButtonModel;
import DataBase.getConexion;
import static Formularios.MenuOPC.escritorio;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.io.File;
import java.sql.SQLException;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author tiend
 */
public class RegistrarVehiculo extends javax.swing.JInternalFrame {

    //Variables globales
    public static ResultSet resultado;
    public static PreparedStatement ps;

    public static String sql;
    public static Statement st;
    public static int rsnum = 0;
    String hora, minutos, segundos,ampm;
    Thread hilo;

    String barra = File.separator;
    String CrearUbicacion = System.getProperty("user.dir") + barra + "Tickets" + barra;
    //CREAMO UNA CARPETA DONDE ESTARAN LOS TICKETS

    /**
     * Creates new form RegistrarVehiculo
     */
//------------------------------------------------------------------------------------------------------ 
//METODO CONSTRUCTOR      
    public RegistrarVehiculo() throws SQLException {
        initComponents();
        jdHora.setCalendar(calendario1);
       
    }
    
    ConsultaSql csla = new ConsultaSql();
//------------------------------------------------------------------------------------------------------ 
    //METODO PARA MOSTRAR LOS LUGARES

    void mostrarLugares() throws SQLException {
        Connection conexion = null;

        //Propiedad para no editar datos de la tabla
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int filas, int columnas) {
                if (columnas == 2) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        modelo.addColumn("Id_lugar");
        modelo.addColumn("Estado");
        tblugares.setModel(modelo);
        modelo.fireTableDataChanged();

        conexion = getConexion.getConexion();
        String sql = "SELECT Id_lugar,estados FROM estacion_1";
        //Crea un arreglo con los campos del query
        String datos[] = new String[2];
        Statement st;
        try {
            st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                modelo.addRow(datos);
            }
            tblugares.setModel(modelo);
            tblugares.getColumnModel().getColumn(1).setCellRenderer((TableCellRenderer) new RenderPintar());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

//------------------------------------------------------------------------------------------------------ 
    //METODO PARA MOSTRAR LOS LUGARES NUMERO 2
    void mostrarLugares2() throws SQLException {
        Connection conexion = null;

        //Propiedad para no editar datos de la tabla
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int filas, int columnas) {
                if (columnas == 2) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        modelo.addColumn("Id_lugar");
        modelo.addColumn("Estado");
        tblugares.setModel(modelo);
        modelo.fireTableDataChanged();

        conexion = getConexion.getConexion();
        String sql = "SELECT Id_lugar,estados FROM estacion_2";
        //Crea un arreglo con los campos del query
        String datos[] = new String[2];
        Statement st;
        try {
            st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                modelo.addRow(datos);
            } 
            tblugares.setModel(modelo);
            tblugares.getColumnModel().getColumn(1).setCellRenderer((TableCellRenderer) new RenderPintar());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

//------------------------------------------------------------------------------------------------------ 
    //METODO PARA MOSTRAR LOS LUGARES NUMERO 3
    void mostrarLugares3() throws SQLException {
        Connection conexion = null;

        //Propiedad para no editar datos de la tabla
        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int filas, int columnas) {
                if (columnas == 2) {
                    return true;
                } else {
                    return false;
                }
            }
        };
        modelo.addColumn("Id_lugar");
        modelo.addColumn("Estado");
        tblugares.setModel(modelo);
        modelo.fireTableDataChanged();

        conexion = getConexion.getConexion();
        String sql = "SELECT Id_lugar,estados FROM estacion_3";
        String datos[] = new String[2];//Crea un arreglo con los campos del query
        Statement st;
        try {
            st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                modelo.addRow(datos);
            }
            tblugares.setModel(modelo);
            tblugares.getColumnModel().getColumn(1).setCellRenderer((TableCellRenderer) new RenderPintar());
        } catch (Exception e) {
            System.out.println(e);
        }
    }

//------------------------------------------------------------------------------------------------------ 
    //Metodo para crear TICKET DE ENTRADA y VALIDAR PLACAS YA REGISTRADAS(unicas).
    private void crear() {
        
        String Placa, Fecha, Hora_E, TipoV, Lugar;
        String estas = (String) jcbTP.getSelectedItem();

        Placa = txtplaca.getText();
        Lugar = txt1.getText();
        TipoV = (String) jcbtipo.getSelectedItem();
        Fecha = ((JTextField) jdfecha.getDateEditor().getUiComponent()).getText();
        Hora_E = ((JTextField) jdHora.getDateEditor().getUiComponent()).getText();
        
        String archivo = txtplaca.getText() + ".txt";
        File CrearUbi = new File(CrearUbicacion);
        File CrearArchivo = new File(CrearUbicacion + archivo);
        
        //VALIDA QUE LOS CAMPOS NO ESTEN VACIOS
        if (Placa.equals("") || Fecha.equals("") || Hora_E.equals("") || TipoV.equals("") || Lugar.equals("")) {
                        JOptionPane.showMessageDialog(null, "No pude dejar espacios en blanco", "Error al registrar.", JOptionPane.ERROR_MESSAGE);
                    }
        else if (txtplaca.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "No existe placa");
        } else {
            try {
                if (CrearArchivo.exists()) {
                    JOptionPane.showMessageDialog(rootPane, "La placa ya se encuentra registrada");
                    Limpiar3();
                } else {
                    //CREA EL TICKET RUTA: Estacionamiento\Tickets 
                    CrearUbi.mkdirs();
                    try (Formatter CrearForma = new Formatter(CrearUbicacion + archivo)) {
                        CrearForma.format("\t----------------------------\n\tESTACIONAMIENTO AF\n"
                                + "\t----------------------------\n"
                                + "\t%s\r\n\t%s\r\n\t%s\r\n\t%s\r\n\t%s\r\n\t%s\r\n\t%s\r\n",
                                "PLACA: " + txtplaca.getText(),
                                "TIPO VEHICULO: " + (String) jcbtipo.getSelectedItem(),
                                "ESTACIONAMIENTO : " + (String) jcbTP.getSelectedItem(),
                                "LUGAR: " + txt1.getText(),
                                "FECHA: " + ((JTextField) jdfecha.getDateEditor().getUiComponent()).getText(),
                                "HORA ENTRADA: " + ((JTextField) jdHora.getDateEditor().getUiComponent()).getText(),
                                "DESCUENTO: " + desc + "%"
                                + "\n\t----------------------------\n"
                                + "\tESTADO DE MEXICO, TOLUCA 50200"
                                + "\n\t----------------------------\n");
                    }
                    JOptionPane.showMessageDialog(rootPane, "GENERANDO TICKET...");
                    JOptionPane.showMessageDialog(rootPane, "TICKET CREADO CON EXITO.");        
                    RegVehiculo(); //LLAMADA AL METODO 
                    /*OPCIONES PARA GUARDAR LUGARES EN LOS ESTACIONAMINETOS*/
                    switch (estas) {
                        case "Estacionamiento1":
                            lugares();
                            setVisible(false);
                            break;
                        case "Estacionamiento2":
                            lugares2();
                            setVisible(false);
                            break;
                        case "Estacionamiento3":
                            lugares3();
                            setVisible(false);
                            break;
                        default:
                            break;
                    }
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, "Ticket no creado.");
            }
        }
    }

    /*------------------------------------------------------------------------------------------------------ 
------------------------------------------------------------------------------------------------------ */
    //MOSTRAR FECHA Y HORA EN EL SISTEMA
    int i;
    Calendar calendario1 = new GregorianCalendar();

    public static String fecha() {
        Date fecha = new Date();
        SimpleDateFormat formatofecha = new SimpleDateFormat("YYYY/MM/dd");
        return formatofecha.format(fecha);
    }

    public void hora1() {
        //Calendar calendario1 = new GregorianCalendar();
        Date horaactual = new Date();
        calendario1.setTime(horaactual);
        ampm= calendario1.get(Calendar.AM_PM)== Calendar.AM?"AM":"PM";
        
        if(ampm.equals("PM")){
          int ha=calendario1.get(Calendar.HOUR_OF_DAY)-12;
           hora= ha>9?" "+ha:"0"+ha;
        }else{
        hora = calendario1.get(Calendar.HOUR_OF_DAY) > 9 ? "" + calendario1.get(Calendar.HOUR_OF_DAY) : "0" + calendario1.get(Calendar.HOUR_OF_DAY);
        }
        minutos = calendario1.get(Calendar.MINUTE) > 9 ? "" + calendario1.get(Calendar.MINUTE) : "0" + calendario1.get(Calendar.MINUTE);
        segundos = calendario1.get(Calendar.SECOND) > 9 ? "" + calendario1.get(Calendar.SECOND) : "0" + calendario1.get(Calendar.SECOND);

    }

    public void run() {
        Thread current = Thread.currentThread();

        while (current == hilo) {
            RegVehiculo();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtplaca = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jcbtipo = new javax.swing.JComboBox<>();
        jLabel5 = new javax.swing.JLabel();
        jcbTP = new javax.swing.JComboBox<>();
        jLabel6 = new javax.swing.JLabel();
        txt1 = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jdHora = new com.toedter.calendar.JDateChooser();
        jdfecha = new com.toedter.calendar.JDateChooser();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblugares = new javax.swing.JTable();
        jLabel9 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        txtprecio = new javax.swing.JTextField();
        btned = new javax.swing.JButton();
        jLabel11 = new javax.swing.JLabel();
        DIEZ = new javax.swing.JRadioButton();
        QUINCE = new javax.swing.JRadioButton();
        VEINTE = new javax.swing.JRadioButton();
        CERO = new javax.swing.JRadioButton();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel1.setBackground(new java.awt.Color(102, 102, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sources/Banner_3.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(112, 112, 112)
                .addComponent(jLabel1)
                .addContainerGap(114, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, Short.MAX_VALUE)
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));

        jPanel3.setBackground(new java.awt.Color(102, 102, 255));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Todos los derechos de autor reservados © 2020-2025");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(257, 257, 257)
                .addComponent(jLabel13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Entrada Autos", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Consolas", 1, 14))); // NOI18N

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jButton1.setText("REGISTRAR");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Numero de Placa:");

        txtplaca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtplacaKeyReleased(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Tipo de Vehiculo:");

        jcbtipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar:", "Motocicleta", "Automovil", "Camioneta" }));
        jcbtipo.setMinimumSize(new java.awt.Dimension(109, 20));
        jcbtipo.setPreferredSize(new java.awt.Dimension(109, 20));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Estacionamiento:");

        jcbTP.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar:", "Estacionamiento1", "Estacionamiento2", "Estacionamiento3" }));
        jcbTP.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                jcbTPItemStateChanged(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Hora de Entrada:");

        txt1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txt1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt1.setEnabled(false);

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Lugar:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Fecha:");

        jdHora.setDateFormatString("HH:mm:ss");
        jdHora.setPreferredSize(new java.awt.Dimension(87, 20));

        jdfecha.setDateFormatString("YYYY/MM/dd");

        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Lugares", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Consolas", 1, 14))); // NOI18N

        tblugares.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tblugares.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblugaresMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblugares);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("INFORMACION:");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 0, 0));
        jLabel12.setText("Click para seleccionar un lugar.");

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 519, Short.MAX_VALUE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel12))
                .addContainerGap(91, Short.MAX_VALUE))
        );

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel10.setText("Costo:");

        txtprecio.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtprecio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtprecio.setText("16");
        txtprecio.setEnabled(false);

        btned.setText("Editar");
        btned.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnedActionPerformed(evt);
            }
        });

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Descuento:");

        DIEZ.setText("10%");
        DIEZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DIEZActionPerformed(evt);
            }
        });

        QUINCE.setText("15%");
        QUINCE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                QUINCEActionPerformed(evt);
            }
        });

        VEINTE.setText("20%");
        VEINTE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                VEINTEActionPerformed(evt);
            }
        });

        CERO.setText("NO");
        CERO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CEROActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(15, 15, 15)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(txtplaca, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(18, 18, 18)
                                .addComponent(jcbtipo, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(118, 118, 118))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel5)
                                        .addComponent(jLabel8)
                                        .addComponent(jLabel10)
                                        .addComponent(jLabel4)
                                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                                    .addComponent(jLabel11))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(CERO)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(DIEZ)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(QUINCE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(VEINTE)
                                        .addGap(14, 14, 14))
                                    .addComponent(jdfecha, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(txtprecio, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btned))
                                    .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jcbTP, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jdHora, javax.swing.GroupLayout.PREFERRED_SIZE, 216, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addGap(18, 18, 18)
                .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtplaca)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jcbtipo, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jcbTP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(txt1, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtprecio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btned))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4)
                    .addComponent(jdfecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jdHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(DIEZ)
                    .addComponent(QUINCE)
                    .addComponent(VEINTE)
                    .addComponent(CERO))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1)
                .addContainerGap())
            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

//------------------------------------------------------------------------------------------------------ 
    //Metodo para registrar nuevos vehiculos
    public void RegVehiculo() {

        Connection conexion = null;
        try {
            conexion = getConexion.getConexion();
            String sql = ("INSERT INTO registro (No_placa,fecha,hora_E,costo,TipoV,Descuento,TipoES) VALUES(?,?,?,?,?,?,?)");
            ps = conexion.prepareStatement(sql);
            ps.setString(1, txtplaca.getText());
            ps.setString(2, ((JTextField) jdfecha.getDateEditor().getUiComponent()).getText());
            ps.setString(3, ((JTextField) jdHora.getDateEditor().getUiComponent()).getText());
            ps.setString(4, txtprecio.getText());
            ps.setString(5, (String) jcbtipo.getSelectedItem());
            ps.setString(6, desc);
            ps.setString(7, (String) jcbTP.getSelectedItem());
            ps.executeUpdate();
            JOptionPane.showMessageDialog(null, "¡VEHICULO REGISTRADO!");
            ps.close();
            //Cerrar la conexion
            conexion.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

//------------------------------------------------------------------------------------------------------ 
    //METODO REGISTRO DE LUGARES ESTACIONAMINETO 1
    public void lugares() {

        Connection conexion = null;
        try {
            conexion = getConexion.getConexion();
            String sql = ("UPDATE estacion_1 SET estado = 1,estados = 'ocupado' WHERE id_lugar = ? ");
            ps = conexion.prepareStatement(sql);
            ps.setString(1, txt1.getText());
            ps.executeUpdate();
            ps.close();
            conexion.close();
            setVisible(false);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

//------------------------------------------------------------------------------------------------------ 
    //METODO REGISTRO DE LUGARES ESTACIONAMINETO 2
    public void lugares2() {

        Connection conexion = null;
        try {
            conexion = getConexion.getConexion();
            String sql = ("UPDATE estacion_2 SET estado = 1,estados = 'ocupado' WHERE id_lugar = ? ");
            ps = conexion.prepareStatement(sql);
            ps.setString(1, txt1.getText());
            ps.executeUpdate();
            ps.close();
            conexion.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

//------------------------------------------------------------------------------------------------------ 
    //METODO REGISTRO DE LUGARES ESTACIONAMINETO 3
    public void lugares3() {

        Connection conexion = null;

        try {
            conexion = getConexion.getConexion();
            String sql = ("UPDATE estacion_3 SET estado = 1,estados = 'ocupado' WHERE id_lugar = ? ");
            ps = conexion.prepareStatement(sql);
            ps.setString(1, txt1.getText());
            ps.executeUpdate();
            ps.close();
            conexion.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

//------------------------------------------------------------------------------------------------------ 
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      //LLAMAMOS AL METODO PRINCIPAL
        crear();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tblugaresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblugaresMouseClicked
        //CODIGO PARA MOSTRAR DATOS DE UNA TABLA A UN TXTFIELD
        int select = tblugares.rowAtPoint(evt.getPoint());
        txt1.setText(String.valueOf(tblugares.getValueAt(select, 0)));
    }//GEN-LAST:event_tblugaresMouseClicked


    private void btnedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnedActionPerformed
        //EDITAR EL PRECIO POR HORA A PAGAR 
        txtprecio.setEnabled(true);

    }//GEN-LAST:event_btnedActionPerformed

    private void txtplacaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtplacaKeyReleased
        //TRANSFORMA A MAYUSCULAS LA PLACA
        txtplaca.setText(txtplaca.getText().toUpperCase());
    }//GEN-LAST:event_txtplacaKeyReleased

    private void DIEZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DIEZActionPerformed
        // TODO add your handling code here:
        desc = "10";
    }//GEN-LAST:event_DIEZActionPerformed

    private void QUINCEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_QUINCEActionPerformed
        // TODO add your handling code here:
        desc = "15";
    }//GEN-LAST:event_QUINCEActionPerformed

    private void VEINTEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_VEINTEActionPerformed
        // TODO add your handling code here:
        desc = "20";
    }//GEN-LAST:event_VEINTEActionPerformed

    private void jcbTPItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_jcbTPItemStateChanged
        //SELECCION DE TABLAS DE LOS LUGARES DE ESTACIONAMIENTOS
        String esta = (String) jcbTP.getSelectedItem();
        switch (esta) {
            case "Seleccionar:":
                JOptionPane.showMessageDialog(null, "DEBES SELECCIONAR UNA OPCION.");
                break;
            case "Estacionamiento1":
                try {
                    mostrarLugares(); //MUESTRA TABLA DE LUGARES 1
                } catch (SQLException ex) {
                    Logger.getLogger(RegistrarVehiculo.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "Estacionamiento2":
                try {
                    mostrarLugares2(); //MUESTRA TABLA DE LUGARES 1
                } catch (SQLException ex) {
                    Logger.getLogger(RegistrarVehiculo.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            case "Estacionamiento3":
                try {
                    mostrarLugares3(); //MUESTRA TABLA DE LUGARES 1
                } catch (SQLException ex) {
                    Logger.getLogger(RegistrarVehiculo.class.getName()).log(Level.SEVERE, null, ex);
                }
                break;
            default:
                break;
        }
    }//GEN-LAST:event_jcbTPItemStateChanged

    private void CEROActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CEROActionPerformed
        desc = "0";
    }//GEN-LAST:event_CEROActionPerformed
    //METODO PARA LIMPIAR CASILLAS DESPUES DE REGISTRAR VEHICULO.
    public void Limpiar3() {
        jdHora.setCalendar(null);
        jdfecha.setCalendar(null);
        txtplaca.setText("");
        jcbtipo.setSelectedIndex(0);
        jcbTP.setSelectedIndex(0);
        txt1.setText("");
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton CERO;
    private javax.swing.JRadioButton DIEZ;
    private javax.swing.JRadioButton QUINCE;
    private javax.swing.JRadioButton VEINTE;
    private javax.swing.JButton btned;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> jcbTP;
    private javax.swing.JComboBox<String> jcbtipo;
    public static com.toedter.calendar.JDateChooser jdHora;
    public static com.toedter.calendar.JDateChooser jdfecha;
    private javax.swing.JTable tblugares;
    public static javax.swing.JTextField txt1;
    public static javax.swing.JTextField txtplaca;
    private javax.swing.JTextField txtprecio;
    // End of variables declaration//GEN-END:variables
private String desc;
}
