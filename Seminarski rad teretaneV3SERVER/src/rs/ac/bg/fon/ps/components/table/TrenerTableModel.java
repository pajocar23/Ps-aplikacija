/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.components.table;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.ps.domain.Trener;

/**
 *
 * @author Mr OLOGIZ
 */
public class TrenerTableModel extends AbstractTableModel{
    private List<Trener> ulogovaniTreneri;
    private String[] columnNames = new String[]{"ID Trenera", "Ime i prezime", "Broj telefona", "Pol", "JBMG"};
    private Class[] columnClasses = new Class[]{Long.class, String.class, Long.class, String.class, Long.class};

    public TrenerTableModel(List<Trener> ulogovaniTreneri) {
        this.ulogovaniTreneri = ulogovaniTreneri;
    }

    @Override
    public int getRowCount() {
        if (ulogovaniTreneri == null) {
            return 0;
        }
        return ulogovaniTreneri.size();
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
        Trener t = ulogovaniTreneri.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return t.getIDTrenera();
            case 1:
                return t.getImeTrenera()+" "+t.getPrezimeTrenera();
            case 2:
                return t.getBrojTelefonaTrenera();
            case 3:
                return t.getPolTrenera();
            case 4:
                return t.getJBMGTrenera();
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
       
    }

    public Trener getTrenerAt(int index) {
        return ulogovaniTreneri.get(index);
    }

    /*public void insertIspit(NalogClana nc) {
        this.ispiti.add(i);
        fireTableRowsInserted(ispiti.size() - 1, ispiti.size() - 1);
    }*/

   /* public Ispit deleteIspit(int row) {
        Ispit p = ispiti.remove(row);
        //u bazi
        fireTableRowsDeleted(row, row);
        return p;
    }*/

    public void refreshTermini() {
        fireTableDataChanged();
    }

    public List<Trener> getTreneri() {
        return ulogovaniTreneri;
    }

    public void setNalozi(List<Trener> treneri) {
        this.ulogovaniTreneri = ulogovaniTreneri;
    }
    
}
