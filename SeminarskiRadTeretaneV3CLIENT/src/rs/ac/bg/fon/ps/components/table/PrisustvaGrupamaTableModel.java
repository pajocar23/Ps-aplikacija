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
import rs.ac.bg.fon.ps.domain.PrisustvoTerminu;

/**
 *
 * @author Mr OLOGIZ
 */
public class PrisustvaGrupamaTableModel extends AbstractTableModel {

    private List<PrisustvoTerminu> prisustva;
    private String[] columnNames = new String[]{"ID prisustva", "Ime clana", "Prezime clana", "Datum termina", "Pocetak termina", "Kraj termina", "Naziv Grupe"};
    private Class[] columnClasses = new Class[]{Long.class, String.class, String.class, String.class, String.class, String.class, String.class,String.class};

    public PrisustvaGrupamaTableModel(List<PrisustvoTerminu> prisustva) {
        this.prisustva = prisustva;
    }

    @Override
    public int getRowCount() {
        if (prisustva == null) {
            return 0;
        }
        return prisustva.size();
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
        PrisustvoTerminu ps = prisustva.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return ps.getId();
            case 1:
                return ps.getNalogClana().getImeClana();
            case 2:
                return ps.getNalogClana().getPrezimeClana();
            case 3:
                return ps.getTermin().getDatum().toString();
            case 4:
                return ps.getTermin().getVremePocetka().toString();
            case 5:
                return ps.getTermin().getVremeKraja().toString();
            case 6:
                return ps.getTermin().getG().getNazivGrupe();
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
        /*NalogClana nc = nalozi.get(rowIndex);
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
                nc.setJBMGClana((Long) value);
                break;

        }*/
    }

    public PrisustvoTerminu getPrisustvoAt(int index) {
        return prisustva.get(index);
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
    public void refreshPrisustvaTerminu() {
        fireTableDataChanged();
    }

    public List<PrisustvoTerminu> getPrisustva() {
        return prisustva;
    }

    public void setPrisustvaTerminu(List<PrisustvoTerminu> prisustva) {
        this.prisustva = prisustva;
    }

}
