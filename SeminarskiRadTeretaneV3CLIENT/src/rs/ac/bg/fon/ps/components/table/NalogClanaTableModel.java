/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.components.table;

import java.util.Date;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.ps.domain.NalogClana;

/**
 *
 * @author Mr OLOGIZ
 */
public class NalogClanaTableModel extends AbstractTableModel {

    private List<NalogClana> nalozi;
    private String[] columnNames = new String[]{"Ime", "Prezime", "Broj telefona", "Ulica i broj", "platio", "pol", "JBMG"};
    private Class[] columnClasses = new Class[]{String.class, String.class, Long.class, String.class, Boolean.class, String.class, Long.class};

    public NalogClanaTableModel(List<NalogClana> nalozi) {
        this.nalozi = nalozi;
    }

    @Override
    public int getRowCount() {
        if (nalozi == null) {
            return 0;
        }
        return nalozi.size();
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex > columnClasses.length) {
            return null;
        }
        return columnClasses[columnIndex];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        NalogClana nc = nalozi.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return nc.getImeClana();
            case 1:
                return nc.getPrezimeClana();
            case 2:
                return nc.getBrojTelefonaClana();
            case 3:
                return nc.getUlicaBroj();
            case 4:
                return nc.isPlatio();
            case 5:
                return nc.getPolClana();
            case 6:
                return nc.getJBMGClana();
            default:
                return "n/a";
        }
    }

    @Override
    public String getColumnName(int column) {
        if (column > columnNames.length) {
            return "n/a";
        }
        return columnNames[column];
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public void setValueAt(Object value, int rowIndex, int columnIndex) {
        NalogClana nc = nalozi.get(rowIndex);
        switch (columnIndex) {
            case 0:
                nc.setImeClana(String.valueOf(value));
                break;
            case 1:
                nc.setPrezimeClana(String.valueOf(value));
                break;
            case 2:
                nc.setBrojTelefonaClana((Long) value);
                break;
            case 3:
                nc.setUlicaBroj(String.valueOf(value));
                break;
            case 4:
                nc.setPlatio((boolean) value);
                break;
            case 5:
                nc.setPolClana(String.valueOf(value));
                break;
            case 6:
                nc.setJBMGClana((Long)value);
                break;

        }
    }

    public NalogClana getNalogAt(int index) {
        return nalozi.get(index);
    }

    public void insertNalog(NalogClana nc) {
        this.nalozi.add(nc);
        fireTableDataChanged();
    }

   /* public Ispit deleteIspit(int row) {
        Ispit p = ispiti.remove(row);
        //u bazi
        fireTableRowsDeleted(row, row);
        return p;
    }*/
    
    

    public void refreshNaloziClanova() {
        fireTableDataChanged();
    }

    public List<NalogClana> getNalozi() {
        return nalozi;
    }

    public void setNalozi(List<NalogClana> nalozi) {
        this.nalozi = nalozi;
    }
    
    

}
