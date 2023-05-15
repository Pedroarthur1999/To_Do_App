/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

/**
 *
 * @author eu
 */
public class ButtonCellRender extends DefaultTableCellRenderer {
    
    private String buttonType;

    public ButtonCellRender(String buttonType) {
        
        this.buttonType = buttonType;
        
    }
    
    
    public void setButtonType(String button){
        this.buttonType = button;
    }

    public String getButtonType() {
        return buttonType;
    }
   
    
    
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value,
            boolean isSelected, boolean hasFocus, int row, int column) {
        
        JLabel label;
        
        label = (JLabel) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        label.setHorizontalAlignment(CENTER);
        label.setIcon(new javax.swing.ImageIcon(getClass().getResource("/" + buttonType + ".png")));
       
        return label;
    }
    
}
