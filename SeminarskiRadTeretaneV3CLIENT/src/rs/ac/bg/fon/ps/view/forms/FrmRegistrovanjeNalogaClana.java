/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package rs.ac.bg.fon.ps.view.forms;

import java.awt.event.ActionListener;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

/**
 *
 * @author Mr OLOGIZ
 */
public class FrmRegistrovanjeNalogaClana extends javax.swing.JDialog {

    /**
     * Creates new form FrmKreiranjeNalogaClana
     */
    public FrmRegistrovanjeNalogaClana(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Pol = new javax.swing.ButtonGroup();
        Platio = new javax.swing.ButtonGroup();
        panelBackground = new javax.swing.JPanel();
        lblPrezime = new javax.swing.JLabel();
        lblIme = new javax.swing.JLabel();
        lblUlicaBroj = new javax.swing.JLabel();
        lblPol = new javax.swing.JLabel();
        lblBrojTelefona = new javax.swing.JLabel();
        lblPlatio = new javax.swing.JLabel();
        lblJBMG = new javax.swing.JLabel();
        txtPrezime = new javax.swing.JTextField();
        txtIme = new javax.swing.JTextField();
        rbMuski = new javax.swing.JRadioButton();
        rbZenski = new javax.swing.JRadioButton();
        rbOstalo = new javax.swing.JRadioButton();
        txtBrojTelefona = new javax.swing.JTextField();
        txtUlicaBroj = new javax.swing.JTextField();
        txtJBMG = new javax.swing.JTextField();
        rbDa = new javax.swing.JRadioButton();
        rbNe = new javax.swing.JRadioButton();
        btnZapamtiNalogClana = new javax.swing.JButton();
        lblErrorMessage = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        panelBackground.setBackground(new java.awt.Color(0, 0, 0));
        panelBackground.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Registrovanje novog naloga", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Microsoft YaHei", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        panelBackground.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lblPrezime.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        lblPrezime.setForeground(new java.awt.Color(255, 255, 255));
        lblPrezime.setText("Prezime:");
        lblPrezime.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                lblPrezimeComponentAdded(evt);
            }
        });
        panelBackground.add(lblPrezime, new org.netbeans.lib.awtextra.AbsoluteConstraints(419, 31, -1, -1));

        lblIme.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        lblIme.setForeground(new java.awt.Color(255, 255, 255));
        lblIme.setText("Ime:");
        lblIme.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                lblImeComponentAdded(evt);
            }
        });
        panelBackground.add(lblIme, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 31, -1, -1));

        lblUlicaBroj.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        lblUlicaBroj.setForeground(new java.awt.Color(255, 255, 255));
        lblUlicaBroj.setText("Ulica i broj:");
        lblUlicaBroj.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                lblUlicaBrojComponentAdded(evt);
            }
        });
        panelBackground.add(lblUlicaBroj, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 295, -1, -1));

        lblPol.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        lblPol.setForeground(new java.awt.Color(255, 255, 255));
        lblPol.setText("Pol:");
        lblPol.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                lblPolComponentAdded(evt);
            }
        });
        panelBackground.add(lblPol, new org.netbeans.lib.awtextra.AbsoluteConstraints(35, 88, -1, -1));

        lblBrojTelefona.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        lblBrojTelefona.setForeground(new java.awt.Color(255, 255, 255));
        lblBrojTelefona.setText("Broj telefona:");
        lblBrojTelefona.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                lblBrojTelefonaComponentAdded(evt);
            }
        });
        panelBackground.add(lblBrojTelefona, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 153, -1, -1));

        lblPlatio.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        lblPlatio.setForeground(new java.awt.Color(255, 255, 255));
        lblPlatio.setText("Platio/Platila:");
        lblPlatio.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                lblPlatioComponentAdded(evt);
            }
        });
        panelBackground.add(lblPlatio, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 364, -1, -1));

        lblJBMG.setFont(new java.awt.Font("Microsoft YaHei", 0, 18)); // NOI18N
        lblJBMG.setForeground(new java.awt.Color(255, 255, 255));
        lblJBMG.setText("JBMG:");
        lblJBMG.addContainerListener(new java.awt.event.ContainerAdapter() {
            public void componentAdded(java.awt.event.ContainerEvent evt) {
                lblJBMGComponentAdded(evt);
            }
        });
        panelBackground.add(lblJBMG, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 227, -1, -1));

        txtPrezime.setBackground(new java.awt.Color(0, 0, 0));
        txtPrezime.setFont(new java.awt.Font("Microsoft YaHei", 0, 16)); // NOI18N
        txtPrezime.setForeground(new java.awt.Color(255, 255, 255));
        txtPrezime.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        panelBackground.add(txtPrezime, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 32, 238, -1));

        txtIme.setBackground(new java.awt.Color(0, 0, 0));
        txtIme.setFont(new java.awt.Font("Microsoft YaHei", 0, 16)); // NOI18N
        txtIme.setForeground(new java.awt.Color(255, 255, 255));
        txtIme.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        panelBackground.add(txtIme, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 32, 211, -1));

        rbMuski.setBackground(new java.awt.Color(0, 0, 0));
        Pol.add(rbMuski);
        rbMuski.setFont(new java.awt.Font("Microsoft YaHei", 1, 11)); // NOI18N
        rbMuski.setForeground(new java.awt.Color(255, 255, 255));
        rbMuski.setText("muski");
        rbMuski.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rbMuskiActionPerformed(evt);
            }
        });
        panelBackground.add(rbMuski, new org.netbeans.lib.awtextra.AbsoluteConstraints(184, 91, -1, -1));

        rbZenski.setBackground(new java.awt.Color(0, 0, 0));
        Pol.add(rbZenski);
        rbZenski.setFont(new java.awt.Font("Microsoft YaHei", 1, 11)); // NOI18N
        rbZenski.setForeground(new java.awt.Color(255, 255, 255));
        rbZenski.setText("zenski");
        panelBackground.add(rbZenski, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 90, -1, -1));

        rbOstalo.setBackground(new java.awt.Color(0, 0, 0));
        Pol.add(rbOstalo);
        rbOstalo.setFont(new java.awt.Font("Microsoft YaHei", 1, 11)); // NOI18N
        rbOstalo.setForeground(new java.awt.Color(255, 255, 255));
        rbOstalo.setText("ostalo");
        panelBackground.add(rbOstalo, new org.netbeans.lib.awtextra.AbsoluteConstraints(320, 90, -1, -1));

        txtBrojTelefona.setBackground(new java.awt.Color(0, 0, 0));
        txtBrojTelefona.setFont(new java.awt.Font("Microsoft YaHei", 0, 16)); // NOI18N
        txtBrojTelefona.setForeground(new java.awt.Color(255, 255, 255));
        txtBrojTelefona.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        panelBackground.add(txtBrojTelefona, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 154, 211, -1));

        txtUlicaBroj.setBackground(new java.awt.Color(0, 0, 0));
        txtUlicaBroj.setFont(new java.awt.Font("Microsoft YaHei", 0, 16)); // NOI18N
        txtUlicaBroj.setForeground(new java.awt.Color(255, 255, 255));
        txtUlicaBroj.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        panelBackground.add(txtUlicaBroj, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 296, 211, -1));

        txtJBMG.setBackground(new java.awt.Color(0, 0, 0));
        txtJBMG.setFont(new java.awt.Font("Microsoft YaHei", 0, 16)); // NOI18N
        txtJBMG.setForeground(new java.awt.Color(255, 255, 255));
        txtJBMG.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        panelBackground.add(txtJBMG, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 228, 211, -1));

        rbDa.setBackground(new java.awt.Color(0, 0, 0));
        Platio.add(rbDa);
        rbDa.setFont(new java.awt.Font("Microsoft YaHei", 1, 11)); // NOI18N
        rbDa.setForeground(new java.awt.Color(255, 255, 255));
        rbDa.setText("Da");
        panelBackground.add(rbDa, new org.netbeans.lib.awtextra.AbsoluteConstraints(182, 367, -1, -1));

        rbNe.setBackground(new java.awt.Color(0, 0, 0));
        Platio.add(rbNe);
        rbNe.setFont(new java.awt.Font("Microsoft YaHei", 1, 11)); // NOI18N
        rbNe.setForeground(new java.awt.Color(255, 255, 255));
        rbNe.setText("Ne");
        panelBackground.add(rbNe, new org.netbeans.lib.awtextra.AbsoluteConstraints(241, 367, -1, -1));

        btnZapamtiNalogClana.setFont(new java.awt.Font("Microsoft YaHei", 1, 12)); // NOI18N
        btnZapamtiNalogClana.setText("Zapamti podatke o nalogu clana");
        btnZapamtiNalogClana.setBorder(null);
        panelBackground.add(btnZapamtiNalogClana, new org.netbeans.lib.awtextra.AbsoluteConstraints(522, 432, 238, 51));

        lblErrorMessage.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        lblErrorMessage.setForeground(new java.awt.Color(255, 0, 51));
        panelBackground.add(lblErrorMessage, new org.netbeans.lib.awtextra.AbsoluteConstraints(33, 432, -1, -1));

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/rs/ac/bg/fon/ps/images/teg veliki.png"))); // NOI18N
        panelBackground.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 10, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBackground, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 775, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(panelBackground, javax.swing.GroupLayout.DEFAULT_SIZE, 496, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblPrezimeComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_lblPrezimeComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_lblPrezimeComponentAdded

    private void lblImeComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_lblImeComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_lblImeComponentAdded

    private void lblUlicaBrojComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_lblUlicaBrojComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_lblUlicaBrojComponentAdded

    private void lblPolComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_lblPolComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_lblPolComponentAdded

    private void lblBrojTelefonaComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_lblBrojTelefonaComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_lblBrojTelefonaComponentAdded

    private void lblPlatioComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_lblPlatioComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_lblPlatioComponentAdded

    private void lblJBMGComponentAdded(java.awt.event.ContainerEvent evt) {//GEN-FIRST:event_lblJBMGComponentAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_lblJBMGComponentAdded

    private void rbMuskiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rbMuskiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rbMuskiActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup Platio;
    private javax.swing.ButtonGroup Pol;
    private javax.swing.JButton btnZapamtiNalogClana;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lblBrojTelefona;
    private javax.swing.JLabel lblErrorMessage;
    private javax.swing.JLabel lblIme;
    private javax.swing.JLabel lblJBMG;
    private javax.swing.JLabel lblPlatio;
    private javax.swing.JLabel lblPol;
    private javax.swing.JLabel lblPrezime;
    private javax.swing.JLabel lblUlicaBroj;
    private javax.swing.JPanel panelBackground;
    private javax.swing.JRadioButton rbDa;
    private javax.swing.JRadioButton rbMuski;
    private javax.swing.JRadioButton rbNe;
    private javax.swing.JRadioButton rbOstalo;
    private javax.swing.JRadioButton rbZenski;
    private javax.swing.JTextField txtBrojTelefona;
    private javax.swing.JTextField txtIme;
    private javax.swing.JTextField txtJBMG;
    private javax.swing.JTextField txtPrezime;
    private javax.swing.JTextField txtUlicaBroj;
    // End of variables declaration//GEN-END:variables

    
    public void registrovanjeNalogaAddActionListener(ActionListener actionListener) {
        btnZapamtiNalogClana.addActionListener(actionListener);
    }

    public ButtonGroup getPlatio() {
        return Platio;
    }

    public void setPlatio(ButtonGroup Platio) {
        this.Platio = Platio;
    }

    public ButtonGroup getPol() {
        return Pol;
    }

    public void setPol(ButtonGroup Pol) {
        this.Pol = Pol;
    }

    public JButton getBtnZapamtiNalogClana() {
        return btnZapamtiNalogClana;
    }

    public void setBtnZapamtiNalogClana(JButton btnZapamtiNalogClana) {
        this.btnZapamtiNalogClana = btnZapamtiNalogClana;
    }

    public JLabel getLblBrojTelefona() {
        return lblBrojTelefona;
    }

    public void setLblBrojTelefona(JLabel lblBrojTelefona) {
        this.lblBrojTelefona = lblBrojTelefona;
    }

 

    public JLabel getLblIme() {
        return lblIme;
    }

    public void setLblIme(JLabel lblIme) {
        this.lblIme = lblIme;
    }

    public JLabel getLblJBMG() {
        return lblJBMG;
    }

    public void setLblJBMG(JLabel lblJBMG) {
        this.lblJBMG = lblJBMG;
    }



    public JLabel getLblPlatio() {
        return lblPlatio;
    }

    public void setLblPlatio(JLabel lblPlatio) {
        this.lblPlatio = lblPlatio;
    }


    public void setLblPol(JLabel lblPol) {
        this.lblPol = lblPol;
    }

 
 

    public JLabel getLblPrezime() {
        return lblPrezime;
    }

    public void setLblPrezime(JLabel lblPrezime) {
        this.lblPrezime = lblPrezime;
    }


    public JLabel getLblUlicaBroj() {
        return lblUlicaBroj;
    }

    public void setLblUlicaBroj(JLabel lblUlicaBroj) {
        this.lblUlicaBroj = lblUlicaBroj;
    }

 

    public JPanel getPanelBackground() {
        return panelBackground;
    }

    public void setPanelBackground(JPanel panelBackground) {
        this.panelBackground = panelBackground;
    }

    public JRadioButton getRbDa() {
        return rbDa;
    }

    public void setRbDa(JRadioButton rbDa) {
        this.rbDa = rbDa;
    }

    public JRadioButton getRbMuski() {
        return rbMuski;
    }

    public void setRbMuski(JRadioButton rbMuski) {
        this.rbMuski = rbMuski;
    }

    public JRadioButton getRbNe() {
        return rbNe;
    }

    public void setRbNe(JRadioButton rbNe) {
        this.rbNe = rbNe;
    }

    public JRadioButton getRbOstalo() {
        return rbOstalo;
    }

    public void setRbOstalo(JRadioButton rbOstalo) {
        this.rbOstalo = rbOstalo;
    }

    public JRadioButton getRbZenski() {
        return rbZenski;
    }

    public void setRbZenski(JRadioButton rbZenski) {
        this.rbZenski = rbZenski;
    }

    public JTextField getTxtBrojTelefona() {
        return txtBrojTelefona;
    }

    public void setTxtBrojTelefona(JTextField txtBrojTelefona) {
        this.txtBrojTelefona = txtBrojTelefona;
    }

    public JTextField getTxtIme() {
        return txtIme;
    }

    public void setTxtIme(JTextField txtIme) {
        this.txtIme = txtIme;
    }

    public JTextField getTxtJBMG() {
        return txtJBMG;
    }

    public void setTxtJBMG(JTextField txtJBMG) {
        this.txtJBMG = txtJBMG;
    }

    public JTextField getTxtPrezime() {
        return txtPrezime;
    }

    public void setTxtPrezime(JTextField txtPrezime) {
        this.txtPrezime = txtPrezime;
    }

    public JTextField getTxtUlicaBroj() {
        return txtUlicaBroj;
    }

    public void setTxtUlicaBroj(JTextField txtUlicaBroj) {
        this.txtUlicaBroj = txtUlicaBroj;
    }

    public javax.swing.JLabel getLblErrorMessage() {
        return lblErrorMessage;
    }

    public void setLblErrorMessage(javax.swing.JLabel lblErrorMessage) {
        this.lblErrorMessage = lblErrorMessage;
    }
}
