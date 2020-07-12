/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author tiend
 */
public class RenderPintar extends DefaultTableCellRenderer{

    @Override
    public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
        
        JLabel Labelresultado = (JLabel) super.getTableCellRendererComponent(jtable, o, bln, bln1, i, i1); 
        if(o instanceof String){
            String Dato = (String)o;
            if(Dato.equals("disponible")){
                Labelresultado.setBackground(Color.green);
                Labelresultado.setForeground(Color.black);
            }else{
                if(Dato.equals("ocupado")){
                    Labelresultado.setBackground(Color.red);
                    Labelresultado.setForeground(Color.black);
                }
            }
        }
        return Labelresultado;
    }
    
    
}
