/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.components.table;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.ps.domain.Grupa;

/**
 *
 * @author Mr OLOGIZ
 */
public class GrupaTableModel extends AbstractTableModel {

    private List<Grupa> grupe;
    private String[] columnNames = new String[]{"Broj termina", "Naziv grupe"};
    private Class[] columnClasses = new Class[]{Integer.class, String.class};

    public GrupaTableModel(List<Grupa> grupe) {
        this.grupe = grupe;
    }

    @Override
    public int getRowCount() {
        if (grupe == null) {
            return 0;
        }
        return grupe.size();
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
        Grupa g = grupe.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return g.getBrojTermina();
            case 1:
                return g.getNazivGrupe();
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
        Grupa g = grupe.get(rowIndex);
        switch (columnIndex) {
            case 0:
                g.setBrojTermina((Integer)value);
                break;
            case 1:
                g.setNazivGrupe(String.valueOf(value));
                break;         
        }
    }

    public Grupa getGrupaAt(int index) {
        return grupe.get(index);
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
    public void refreshNaloziClanova() {
        fireTableDataChanged();
    }

    public List<Grupa> getGrupe() {
        return grupe;
    }

    public void setNalozi(List<Grupa> grupe) {
        this.grupe = grupe;
    }

}
