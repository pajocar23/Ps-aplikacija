/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.components.table;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.ps.domain.NalogClana;
import rs.ac.bg.fon.ps.domain.Termin;

/**
 *
 * @author Mr OLOGIZ
 */
public class TerminTableModel extends AbstractTableModel{
    
    private List<Termin> termini;
    private String[] columnNames = new String[]{"ID Termina", "Broj članova", "Maksimalan broj članova", "Naziv grupe", "Ime trenera", "Prezime trenera"};
    private Class[] columnClasses = new Class[]{Long.class, Integer.class, Integer.class, String.class, String.class, String.class};

    public TerminTableModel(List<Termin> termini) {
        this.termini = termini;
    }

    @Override
    public int getRowCount() {
        if (termini == null) {
            return 0;
        }
        return termini.size();
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
        Termin t = termini.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return t.getIDTermina();
            case 1:
                return t.getBrojClanova();
            case 2:
                return t.getMaksBrojClanova();
            case 3:
                return t.getG().getNazivGrupe();
            case 4:
                return t.getG().getTrener().getImeTrenera();
            case 5:
                return t.getG().getTrener().getPrezimeTrenera();
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
        /*Termin t = termini.get(rowIndex);
        switch (columnIndex) {
            case 0:
                t.setImeClana(String.valueOf(value));
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

        }*/
    }

    public Termin getTerminAt(int index) {
        return termini.get(index);
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

    public List<Termin> getTermini() {
        return termini;
    }

    public void setNalozi(List<Termin> termini) {
        this.termini = termini;
    }
    
    
    
}
