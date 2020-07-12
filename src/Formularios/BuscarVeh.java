/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Formularios;

import Clases.ConsultaSql;
import DataBase.getConexion;
import static Formularios.RegistrarVehiculo.jdHora;
import static Formularios.RegistrarVehiculo.jdfecha;
import static Formularios.RegistrarVehiculo.txt1;
import static Formularios.RegistrarVehiculo.txtplaca;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author tiend
 */
public final class BuscarVeh extends javax.swing.JInternalFrame {
    //Variable Globales

    public static ResultSet resultado;
    public static PreparedStatement ps;

    public static String sql;
    public static Statement st;
    public static int rsnum = 0;

    String barra = File.separator;
    String CrearUbicacion = System.getProperty("user.dir") + barra + "TicketSalida" + barra;

    /**
     * Creates new form BuscarVeh
     */
    public BuscarVeh() throws SQLException {
        initComponents();
        // mostrarLugares1();
        mostrarAutos();
        //dHora.setCalendar(calen);
    }
    ConsultaSql Cs = new ConsultaSql();

    //Metodo para calcular el total
    public void tarifa() {

    }

    //Metodo buscar lugares 1 
    void mostrarLugares1() throws SQLException {
        Connection conexion = null;

        //Crea una tabla con sus campos
        DefaultTableModel modelo;
        modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int filas, int columnas) {
                return columnas == 1;
            }
        };
        modelo.addColumn("Lugar");
        tabla1.setModel(modelo);

        conexion = getConexion.getConexion();
        String sql = "SELECT Id_lugar FROM estacion_1";
        String datos[] = new String[1];         //Crea un arreglo con los campos del query
        Statement st;
        try {
            st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString(1);
                modelo.addRow(datos);
            }
            tabla1.setModel(modelo);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //METODO PARA ACTUALIZAR LOS LUGARES 
    public void lugares1() {

        Connection conexion = null;

        try {
            conexion = getConexion.getConexion();
            String sql = ("UPDATE estacion_1 SET estado = 0,estados = 'disponible' WHERE  id_lugar = ? ");
            ps = conexion.prepareStatement(sql);
            ps.setString(1, txt12.getText());
            ps.executeUpdate();
            ps.close();
            conexion.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

//METODO PARA ACTUALIZAR LOS LUGARES NUMERO 2
    public void lugares2() {

        Connection conexion = null;

        try {
            conexion = getConexion.getConexion();
            String sql = ("UPDATE estacion_2 SET estado = 0,estados = 'disponible' WHERE  id_lugar = ? ");
            ps = conexion.prepareStatement(sql);
            ps.setString(1, txt12.getText());
            ps.executeUpdate();
            ps.close();
            conexion.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

//METODO PARA ACTUALIZAR LOS LUGARES NUMERO 3
    public void lugares3() {

        Connection conexion = null;

        try {
            conexion = getConexion.getConexion();
            String sql = ("UPDATE estacion_3 SET estado = 0,estados = 'disponible' WHERE  id_lugar = ? ");
            ps = conexion.prepareStatement(sql);
            ps.setString(1, txt12.getText());
            ps.executeUpdate();
            ps.close();
            conexion.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //Metodo para imprimir el TICKET de SALIDA
    private void SalidaT() {

        String archivo = txtid.getText() + ".txt";
        File CrearUbi = new File(CrearUbicacion);
        File CrearArchivo = new File(CrearUbicacion + archivo);

        if (txtid.getText().equals("")) {
            JOptionPane.showMessageDialog(rootPane, "No existe placa");
        } else {
            try {
                if (CrearArchivo.exists()) {
                    JOptionPane.showMessageDialog(rootPane, "La placa ya se encuentra registrada");
                } else {
                    CrearUbi.mkdirs();
                    try (Formatter CrearForma = new Formatter(CrearUbicacion + archivo)) {
                        CrearForma.format("\t----------------------------\n\tESTACIONAMIENTO AF\n"
                                + "\t----------------------------\n" 
                                + "\t%s\r\n\t%s\r\n\t%s\r\n\t%s\r\n\t%s\r\n\t%s\r\n\t%s\r\n\t%s\r\n\t%s\r\n",
                                "PLACA: " + txtp.getText(),
                                "FECHA: " + txtf.getText(),
                                "HORA DE ENTRADA: " + txthe.getText(),
                                "HORA SALIDA: " + ((JTextField) jdtS.getDateEditor().getUiComponent()).getText(),
                                "COSTO: " + txtc.getText(),
                                "LUGAR: " + txt12.getText(),
                                "ESTACIONAMIENTO: " + txtess.getText(),
                                "VEHICULO: " + txtt.getText(),
                                "TOTAL: " + "\t$ " + txtT.getText()+
                                "   IVA: "        
                                + "\n\t----------------------------\n"
                                + "\tESTADO DE MEXICO, TOLUCA 50200"
                                + "\n\t----------------------------\n");
                    }
                    JOptionPane.showMessageDialog(rootPane, "IMPRIMIENDO TICKET...");
                    JOptionPane.showMessageDialog(rootPane, "TICKET IMPRESO CORRECTAMENTE.");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(rootPane, "Ticket no creado.");
            }
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

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        txtpla = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        txtid = new javax.swing.JTextField();
        txtp = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtf = new javax.swing.JTextField();
        txthe = new javax.swing.JTextField();
        txtc = new javax.swing.JTextField();
        txtt = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jLabel11 = new javax.swing.JLabel();
        jdtS = new com.toedter.calendar.JDateChooser();
        txtT = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        txt12 = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla1 = new javax.swing.JTable();
        jLabel14 = new javax.swing.JLabel();
        txtess = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);

        jPanel1.setBackground(new java.awt.Color(102, 102, 255));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Sources/Banner_1.png"))); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 636, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(125, 125, 125))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(204, 204, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Placa:");

        txtpla.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtplaKeyReleased(evt);
            }
        });

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla);

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton2.setText("Eliminar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton1.setText("Limpiar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        txtid.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtid.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtid.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtid.setEnabled(false);

        txtp.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtp.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtp.setEnabled(false);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("ID:");

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel4.setText("Costo:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel5.setText("Placa:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel6.setText("Fecha:");

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel7.setText("Tipo Vehiculo:");

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel8.setText("Hora Entrada:");

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel9.setText("Total a pagar:");

        txtf.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtf.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtf.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtf.setEnabled(false);

        txthe.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txthe.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txthe.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txthe.setEnabled(false);

        txtc.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtc.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtc.setEnabled(false);

        txtt.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtt.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtt.setEnabled(false);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("Fecha:");

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel11.setText("Hora Salida:");

        jdtS.setDateFormatString("HH:mm:ss");

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel12.setText("Vehiculo buscado");

        jButton3.setText("Delete");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        tabla1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabla1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla1MouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tabla1);

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel14.setText("No. Estacion:");

        txtess.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        txtess.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtess.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        txtess.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtpla, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 523, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)
                    .addComponent(txt12))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jLabel4)
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8)
                                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                .addComponent(jLabel14)
                                                .addComponent(jLabel7)
                                                .addComponent(jLabel11)))
                                        .addComponent(jLabel6)
                                        .addComponent(jLabel5))
                                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jLabel12)
                                            .addGap(12, 12, 12))
                                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(txtc)
                                            .addComponent(txthe)
                                            .addComponent(txtf)
                                            .addComponent(txtp)
                                            .addComponent(txtid)
                                            .addComponent(txtt, javax.swing.GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                            .addComponent(jdtS, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(jButton3))
                                        .addComponent(txtess, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtT, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel1)
                        .addComponent(txtpla, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel10))
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel12)
                        .addComponent(txt12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(txtp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(txtf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(txthe, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(txtc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel7)
                            .addComponent(txtt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtess, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel11)
                            .addComponent(jdtS, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(txtT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton2)
                            .addComponent(jButton1))
                        .addGap(46, 46, 46))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 376, Short.MAX_VALUE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel3.setBackground(new java.awt.Color(153, 153, 255));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel13.setText("Todos los derechos de autor reservados © 2020-2025");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(263, 263, 263))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel13)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtplaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtplaKeyReleased
        // TODO add your handling code here:
        buscarPersona(txtpla.getText());
    }//GEN-LAST:event_txtplaKeyReleased

    private void tablaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaMouseClicked
        // TODO add your handling code here:
        //Pasa los registros de la tabla a los txt field
        int select = tabla.rowAtPoint(evt.getPoint());
        txtid.setText(String.valueOf(tabla.getValueAt(select, 0)));
        txtp.setText(String.valueOf(tabla.getValueAt(select, 1)));
        txtf.setText(String.valueOf(tabla.getValueAt(select, 2)));
        txthe.setText(String.valueOf(tabla.getValueAt(select, 3)));
        txtc.setText(String.valueOf(tabla.getValueAt(select, 4)));
        txtt.setText(String.valueOf(tabla.getValueAt(select, 5)));
        txtess.setText(String.valueOf(tabla.getValueAt(select, 7)));


    }//GEN-LAST:event_tablaMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        //Muestra la hora de salida
        jdtS.setCalendar(null);
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        String selec = txtess.getText();
        if(selec.equals("Estacionamiento1")){
                SalidaT();
                lugares1();
                String placa = txtp.getText();
                Cs.borrarCocheras(placa);
                mostrarAutos();
           }else if(selec.equals("Estacionamiento2")){
                SalidaT();
                lugares2();
                String placa = txtp.getText();
                Cs.borrarCocheras(placa);
                mostrarAutos();
           }else if(selec.equals("Estacionamiento3")){
               SalidaT();
                lugares3();
                String placa = txtp.getText();
                Cs.borrarCocheras(placa);
                mostrarAutos();
           } 
            
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tabla1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla1MouseClicked
        // TODO add your handling code here:
        int select1 = tabla1.rowAtPoint(evt.getPoint());
        txt12.setText(String.valueOf(tabla1.getValueAt(select1, 0)));

    }//GEN-LAST:event_tabla1MouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        Limpiar();
    }//GEN-LAST:event_jButton1ActionPerformed
    //Metodo para mostrar los vehiculos registrados en tiempo instantaneo
    public void mostrarAutos() {

        Connection conexion = null;

        DefaultTableModel modelo;
        modelo = new DefaultTableModel(){
            @Override
            public boolean isCellEditable(int filas, int columnas) {
                return columnas == 8;
            }
        };
        modelo.addColumn("Id");
        modelo.addColumn("Placa");
        modelo.addColumn("Fecha");
        modelo.addColumn("Hora_Entrada");
        modelo.addColumn("Costo");
        modelo.addColumn("Tipo");
        modelo.addColumn("Descuento");
        modelo.addColumn("Estacionamiento");
        tabla.setModel(modelo);
        try {
            mostrarLugares1();
        } catch (SQLException ex) {
            Logger.getLogger(BuscarVeh.class.getName()).log(Level.SEVERE, null, ex);
        }

       // conexion = getConexion.getConexion();
    }

    //Metodo que busca personas
    public void buscarPersona(String buscar) {
        ConsultaSql logica = new ConsultaSql();

        DefaultTableModel modelo = logica.buscarPersonas(buscar);

        tabla.setModel(modelo);
    }

    public void Limpiar() {

        txtid.setText("");
        txtp.setText("");
        txtf.setText("");
        txthe.setText("");
        txtc.setText("");
        txtt.setText("");
        txtT.setText("");
        txt12.setText("");
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private com.toedter.calendar.JDateChooser jdtS;
    private javax.swing.JTable tabla;
    private javax.swing.JTable tabla1;
    private javax.swing.JTextField txt12;
    private javax.swing.JTextField txtT;
    private javax.swing.JTextField txtc;
    private javax.swing.JTextField txtess;
    private javax.swing.JTextField txtf;
    private javax.swing.JTextField txthe;
    private javax.swing.JTextField txtid;
    private javax.swing.JTextField txtp;
    private javax.swing.JTextField txtpla;
    private javax.swing.JTextField txtt;
    // End of variables declaration//GEN-END:variables
}
