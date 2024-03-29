/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package interfaz;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import programa1.Atleta;
import programa1.Competencia;
import programa1.Configuracion;
import programa1.Disciplina;

/**
 * Interfaz para agregar una disciplina
 *@author Usuario
 */
public class AgregarDisciplina extends javax.swing.JFrame {
    private ArrayList<Atleta> atletas;
    private ArrayList<Competencia> competencias;
    private Configuracion config;
    private Competencia competencia;
    /**
     * Creates new form AgregarDisciplina
     * Carga las cajas de selección con las disciplinas y medidas
     */
    
    public AgregarDisciplina() {
        initComponents();
        setLocationRelativeTo(null);
        cN.addItem("Carreras de velocidad");
        cN.addItem("Medio Fondo");
        cN.addItem("Fondo");
        cN.addItem("Saltos");
        cN.addItem("Marcha");
        cN.addItem("Salto");
        cN.addItem("Lanzamiento");
        cajaMedida.addItem("s");
        cajaMedida.addItem("m");
    }
    /**
    * Hereda la lista de atletas
    *@param pAtletas Lista de Atletas
    */
     public void setAtletas(ArrayList<Atleta> pAtletas){
            atletas=pAtletas;
    }
    /**
    * Hereda la competencia a la que pertenece esta clase
    *@param pCompetencia Competencia
    */
    public void cargar(Competencia pCompetencia){
        
        competencia= pCompetencia;
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
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        agregar = new javax.swing.JButton();
        cajaMedida = new javax.swing.JComboBox<>();
        volver = new javax.swing.JButton();
        cN = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Nombre");

        jLabel2.setText("Medida");

        agregar.setText("Agregar");
        agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarActionPerformed(evt);
            }
        });

        cajaMedida.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { }));

        volver.setText("Volver");
        volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverActionPerformed(evt);
            }
        });

        cN.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {  }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(57, 57, 57)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(cN, 0, 264, Short.MAX_VALUE)
                            .addComponent(cajaMedida, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(volver, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(34, 34, 34)
                        .addComponent(agregar, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(191, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(cN, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(38, 38, 38)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(cajaMedida, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(agregar)
                    .addComponent(volver))
                .addGap(22, 22, 22))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarActionPerformed
        String nombre=cN.getSelectedItem().toString();
        String m=cajaMedida.getSelectedItem().toString();
        Disciplina at = new Disciplina(nombre,m);
        competencia.getDisciplinas().add(at);
        JOptionPane.showMessageDialog(this,"Elemento agregado\n"+at.toString());
        dispose();
        MenuDisciplina ventana = new  MenuDisciplina();
        ventana.setVisible(true);
        ventana.setAtletas(atletas);
        ventana.setCompetencias(competencias);
        ventana.setConfig(config);
        ventana.cargarPanel(competencia);
    }//GEN-LAST:event_agregarActionPerformed

    private void volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverActionPerformed
        dispose();
        MenuDisciplina ventana = new MenuDisciplina();
        ventana.setVisible(true);
        ventana.setAtletas(atletas);
        ventana.setCompetencias(competencias);
        ventana.setConfig(config);
        ventana.cargarPanel(competencia);
    }//GEN-LAST:event_volverActionPerformed

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
            java.util.logging.Logger.getLogger(AgregarDisciplina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AgregarDisciplina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AgregarDisciplina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AgregarDisciplina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AgregarDisciplina().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregar;
    private javax.swing.JComboBox<String> cN;
    private javax.swing.JComboBox<String> cajaMedida;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JButton volver;
    // End of variables declaration//GEN-END:variables
}
