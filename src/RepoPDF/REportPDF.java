/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RepoPDF;

import static Clases.ConsultaSql.ps;
import static Clases.ConsultaSql.resultado;
import DataBase.getConexion;
import Formularios.PDFgenerate;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.awt.Desktop;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javax.swing.SwingConstants;

/**
 *
 * @author tiend
 */
public class REportPDF {
    

    //PRIMER REPORTE DE PDF
    public void pdftable2(String ruta) {

        
        Connection conexion = null; //Varibale conexion para llamar la conexion ya creada

        try {

            conexion = getConexion.getConexion();
            String sentencia_busqueda = ("SELECT * FROM registro"); //Consulta para ver el registro de los usuarios
            ps = conexion.prepareStatement(sentencia_busqueda); //Preparamos la conexion con la consulta
            resultado = ps.executeQuery();

            OutputStream file = null;
            try {
                file = new FileOutputStream(new File(ruta + ".pdf"));
            } catch (FileNotFoundException ex) {
                System.out.println(ex);
            }
            Document document = new Document(PageSize.A4.rotate());

            PdfWriter.getInstance(document, file);

            document.open();
            PdfPTable tabla = new PdfPTable(7);
            Paragraph p = new Paragraph("ESTACIONAMIENTO AF\n\n", FontFactory.getFont("Arial", 16, Font.ITALIC, BaseColor.BLUE));
            Paragraph p1 = new Paragraph("REPORTE DE VEHICULOS\n\n", FontFactory.getFont("Arial", 16, Font.ITALIC, BaseColor.RED));
            Paragraph p2 = new Paragraph("VENTA TOTAL: \n\n", FontFactory.getFont("Verdana", 18, Font.ITALIC, BaseColor.BLACK));

            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);

            p1.setAlignment(Element.ALIGN_CENTER);
            document.add(p1);

            document.add(new Paragraph(""));

            float[] mediaCeldas = {3.10f, 3.50f, 3.50f, 3.70f, 3.70f, 3.50f, 3.70f};

            tabla.setWidths(mediaCeldas);
            tabla.addCell(new Paragraph("ID", FontFactory.getFont("Verdana", 13)));
            tabla.addCell(new Paragraph("No_placa", FontFactory.getFont("Verdana", 13)));
            tabla.addCell(new Paragraph("Fecha", FontFactory.getFont("Verdana", 13)));
            tabla.addCell(new Paragraph("Hora_Entrada", FontFactory.getFont("Verdana", 13)));
            tabla.addCell(new Paragraph("Costo", FontFactory.getFont("Verdana", 13)));
            tabla.addCell(new Paragraph("Tipo Vehiculo", FontFactory.getFont("Verdana", 13)));
            tabla.addCell(new Paragraph("Descuento", FontFactory.getFont("Verdana", 13)));

            while (resultado.next()) {
                tabla.addCell(new Paragraph(resultado.getString("id_registro"), FontFactory.getFont("Arial", 11)));
                tabla.addCell(new Paragraph(resultado.getString("No_placa"), FontFactory.getFont("Arial", 11)));
                tabla.addCell(new Paragraph(resultado.getString("fecha"), FontFactory.getFont("Arial", 11)));
                tabla.addCell(new Paragraph(resultado.getString("hora_E"), FontFactory.getFont("Arial", 11)));
                tabla.addCell(new Paragraph(resultado.getString("costo"), FontFactory.getFont("Arial", 11)));
                tabla.addCell(new Paragraph(resultado.getString("TipoV"), FontFactory.getFont("Arial", 11)));
                tabla.addCell(new Paragraph(resultado.getString("Descuento"), FontFactory.getFont("Arial", 11)));

            }

            document.add(tabla);

            p2.setAlignment(Element.ALIGN_LEFT);
            document.add(p2);
            document.close();
            file.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (DocumentException ex) {
            System.out.println(ex);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        try { //      Abrir Automaticamente el pdf creado
            File file = new File(ruta + ".pdf");
            Desktop.getDesktop().open(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//SEGUNDO REPORTE DE PDF
    public void Totalvehiculos(String ruta) {

        Connection conexion = null; //Varibale conexion para llamar la conexion ya creada

        try {

            conexion = getConexion.getConexion();
            String sentencia_busqueda = ("SELECT COUNT(TipoV) AS TipoV FROM registro"); //Consulta para ver el registro de los usuarios
            ps = conexion.prepareStatement(sentencia_busqueda); //Preparamos la conexion con la consulta
            resultado = ps.executeQuery();

            OutputStream file = null;
            try {
                file = new FileOutputStream(new File(ruta + ".pdf"));
            } catch (FileNotFoundException ex) {
                System.out.println(ex);
            }
            Document document = new Document(PageSize.A4.rotate());

            PdfWriter.getInstance(document, file);

            document.open();
            PdfPTable tabla = new PdfPTable(1);
            Paragraph p1 = new Paragraph("-----> REPORTE <------\n" + "TOTAL DE AUTOS INGRESADOS", FontFactory.getFont("Verdana", 20, Font.ITALIC, BaseColor.BLUE));
            Paragraph p2 = new Paragraph("TOTAL DE VEHICULOS: ", FontFactory.getFont("Verdana", 18, Font.ITALIC, BaseColor.BLACK));

            p1.setAlignment(Element.ALIGN_CENTER);
            document.add(p1);

            document.add(new Paragraph(""));

            float[] mediaCeldas = {3.70f};

            tabla.setWidths(mediaCeldas);
            tabla.addCell(new Paragraph("Cantidad", FontFactory.getFont("Verdana", 13)));

            while (resultado.next()) {
                tabla.addCell(new Paragraph(resultado.getString("TipoV"), FontFactory.getFont("Arial", 11)));

            }

            document.add(tabla);

            p2.setAlignment(Element.ALIGN_LEFT);
            document.add(p2);
            document.close();
            file.close();
        } catch (SQLException e) {
        } catch (DocumentException | IOException ex) {
            System.out.println(ex);
        }
        try { //      Abrir Automaticamente el pdf creado
            File file = new File(ruta + ".pdf");
            Desktop.getDesktop().open(file);
        } catch (IOException e) {
        }
    }

}
