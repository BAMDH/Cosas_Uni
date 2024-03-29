/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package interfaz;
 

import java.awt.Toolkit;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import programa1.Atleta;
import programa1.Categoria;
import programa1.Competencia;
import programa1.Configuracion;

/**
 *Menu Principal
 * @author Usuario
 */
public class MenuPrincipal extends javax.swing.JFrame {
    private ArrayList<Atleta> atletas;
    private ArrayList<Competencia> competencias;
    private Configuracion config;
    public MenuPrincipal() {
        initComponents();
        setLocationRelativeTo(null);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        irAtletas = new javax.swing.JButton();
        irCompetencias = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        irAtletas.setText("Manejar atletas");
        irAtletas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                irAtletasActionPerformed(evt);
            }
        });

        irCompetencias.setText("Manejar competencias");
        irCompetencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                irCompetenciasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(129, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(irCompetencias, javax.swing.GroupLayout.DEFAULT_SIZE, 164, Short.MAX_VALUE)
                    .addComponent(irAtletas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(107, 107, 107))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(irCompetencias)
                .addGap(38, 38, 38)
                .addComponent(irAtletas)
                .addContainerGap(127, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
    * Hereda la lista de atletas
    *@param pAtletas Lista de Atletas
    */
    public void setAtletas(ArrayList<Atleta> pAtletas){
            atletas=pAtletas;
    }
    /**
    * Hereda la lista de competencias
    * @param pCompetencias Lista de competencias
    */
    public void setCompetencias(ArrayList<Competencia> pCompetencias){
            competencias=pCompetencias;
    }
    /**
    *Hereda la configuracion
    *@param pConfig Configuracion del sistema
    */
    public void setConfig(Configuracion pConfig){
        config = pConfig;
    }
    
    private void irAtletasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_irAtletasActionPerformed
        dispose();
        MenuAtleta ventanaAtleta = new  MenuAtleta();
        ventanaAtleta.setVisible(true);
        ventanaAtleta.setAtletas(atletas);
        ventanaAtleta.setCompetencias(competencias);
        ventanaAtleta.setConfig(config);
    }//GEN-LAST:event_irAtletasActionPerformed

    private void irCompetenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_irCompetenciasActionPerformed
        dispose();
        MenuCompetencia ventana = new MenuCompetencia();
        ventana.setVisible(true);
        ventana.setAtletas(atletas);
        ventana.setCompetencias(competencias);
        ventana.setConfig(config);
    }//GEN-LAST:event_irCompetenciasActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton irAtletas;
    private javax.swing.JButton irCompetencias;
    // End of variables declaration//GEN-END:variables
}
