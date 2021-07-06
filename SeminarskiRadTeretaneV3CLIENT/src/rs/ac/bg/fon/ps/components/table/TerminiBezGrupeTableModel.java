/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.components.table;

import java.util.List;
import javax.swing.table.AbstractTableModel;
import rs.ac.bg.fon.ps.domain.Termin;

/**
 *
 * @author Mr OLOGIZ
 */
public class TerminiBezGrupeTableModel extends AbstractTableModel {

    private List<Termin> termini;
    private String[] columnNames = new String[]{"Datum", "Vreme pocetka", "Vreme kraja", "Trajanje", "Broj članova", "Max broj članova"};
    private Class[] columnClasses = new Class[]{String.class, String.class, String.class, String.class, Integer.class, Integer.class};

    public TerminiBezGrupeTableModel(List<Termin> termini) {
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
                String[] splite1 = t.getDatum().toString().split(" ");
                String x = splite1[0] + " " + splite1[1] + " " + splite1[2] + " " + splite1[5];
                return x;
            case 1:
                String[] splite2 = t.getVremePocetka().toString().split(" ");
                String x2 = splite2[3];
                return x2;
            case 2:
                String[] splite3 = t.getVremeKraja().toString().split(" ");
                String x3 = splite3[3];
                return x3;
            case 3:
                String[] splite4 = t.getTrajanje().toString().split(" ");
                String x4 = splite4[3];
                return x4;
            case 4:
                return t.getBrojClanova();
            case 5:
                return t.getMaksBrojClanova();
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

    public void dodajTermin(Termin t) {
        termini.add(t);
        refreshTermini();
    }

    public Termin getTerminAt(int index) {
        return termini.get(index);
    }

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
