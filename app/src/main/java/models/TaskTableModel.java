/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class TaskTableModel extends AbstractTableModel {

    String[] colunas = {"Nome", "Descrição", "Prazo", "Notas","Tarefa Concluida", "Editar", "Excluir"};

    List<Tasks> tasks = new ArrayList<>();

    @Override
    public int getRowCount() {
        return tasks.size();
    }

    public String[] getColunas() {
        return colunas;
    }

    public void setColunas(String[] colunas) {
        this.colunas = colunas;
    }

    public List<Tasks> getTasks() {
        return tasks;
    }

    public void setTasks(List<Tasks> tasks) {
        this.tasks = tasks;
    }

    @Override
    public int getColumnCount() {
        return colunas.length;
    }

    @Override
    public String getColumnName(int column) {

        return colunas[column];

    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {

        return columnIndex == 4;
    }
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(tasks.isEmpty()){                    
        return Object.class;
        }
        
        return getValueAt(0, columnIndex).getClass();
    }
    @Override
     public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
         
         tasks.get(rowIndex).setTask_completed((Boolean) aValue);
         
    }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        switch (columnIndex) {
            case 0:
                return tasks.get(rowIndex).getName();
            case 1:
                return tasks.get(rowIndex).getDescription();
            case 2:
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

                return dateFormat.format(tasks.get(rowIndex).getCompleted());
            case 3:
                return tasks.get(rowIndex).getNotes();
            case 4:
                return tasks.get(rowIndex).getTask_completed();
            case 5:
                return "";
            case 6:
                return "";
            default:
                return "Dados não encontrados!";
        }

    }

}
