/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package interfaz;

import java.util.ArrayList;
import java.util.Scanner;
import programa1.Atleta;
import programa1.Competencia;
import programa1.Configuracion;

/**
 *Maneja las competencias
 * @author Usuario
 */
public class MenuCompetencia extends javax.swing.JFrame {
    private ArrayList<Atleta> atletas;
    private ArrayList<Competencia> competencias;
    private Configuracion config;
    /**
     * Creates new form MenuCompetencia
     */
    public MenuCompetencia() {
        initComponents();
        setLocationRelativeTo(null);
    }
    /**
    *Hereda la configuracion
    *@param pConfig Configuracion del sistema
    */
    public void setConfig(Configuracion pConfig){
        config = pConfig;
        
    }
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
            cargarPanel();
    }
    /**
    *Carga las cajas de seleccion
    */
    private void cargarPanel(){
        for(Competencia i : competencias){
            eCompetencia.addItem(i.getNombre()+" "+"#"+i.getIdCompetencia());
        } 
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
        irAgregarCompetencia = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        texto = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        eCompetencia = new javax.swing.JComboBox<>();
        buscar = new javax.swing.JButton();
        eliminarCompetencia = new javax.swing.JButton();
        irMenu = new javax.swing.JButton();
        irMDisciplina = new javax.swing.JButton();
        agregar = new javax.swing.JButton();
        quitar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Competencia");

        irAgregarCompetencia.setText("Agregar Competencia");
        irAgregarCompetencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                irAgregarCompetenciaActionPerformed(evt);
            }
        });

        texto.setEditable(false);
        texto.setColumns(20);
        texto.setRows(5);
        jScrollPane1.setViewportView(texto);

        jLabel2.setText("Información de la Competencia");

        eCompetencia.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { }));
        eCompetencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eCompetenciaActionPerformed(evt);
            }
        });

        buscar.setText("Buscar");
        buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarActionPerformed(evt);
            }
        });

        eliminarCompetencia.setText("Eliminar Competencia");
        eliminarCompetencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarCompetenciaActionPerformed(evt);
            }
        });

        irMenu.setText("Menu");
        irMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                irMenuActionPerformed(evt);
            }
        });

        irMDisciplina.setText("Disciplinas");
        irMDisciplina.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                irMDisciplinaActionPerformed(evt);
            }
        });

        agregar.setText("Inscribir Atleta");
        agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarActionPerformed(evt);
            }
        });

        quitar.setText("Eliminar Atleta");
        quitar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quitarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(irMDisciplina, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(eliminarCompetencia)
                            .addComponent(irAgregarCompetencia)))
                    .addComponent(eCompetencia, javax.swing.GroupLayout.PREFERRED_SIZE, 227, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(agregar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(quitar, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(irMenu, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(54, 54, 54)))
                        .addGap(123, 123, 123))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2)
                        .addGap(159, 159, 159))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(eCompetencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buscar)
                            .addComponent(eliminarCompetencia))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(irMDisciplina))
                            .addGroup(layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(irAgregarCompetencia))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(irMenu)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(agregar)
                        .addComponent(quitar)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void irAgregarCompetenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_irAgregarCompetenciaActionPerformed
        dispose();
        AgregarCompetencia ventana = new  AgregarCompetencia();
        ventana.setVisible(true);
        ventana.setAtletas(atletas);
        ventana.setCompetencias(competencias);
        ventana.setConfig(config);
    }//GEN-LAST:event_irAgregarCompetenciaActionPerformed

    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed
        String copy=eCompetencia.getSelectedItem().toString();
        Competencia at=buscarCompetencia(copy);
        texto.setText(at.toString());
    }//GEN-LAST:event_buscarActionPerformed
    private Competencia buscarCompetencia(String copy){
        Scanner delimitar = new Scanner(copy);
        delimitar.useDelimiter("\\s*#\\s*");
        delimitar.next();
        Competencia at = buscarCompetencia(Integer.parseInt(delimitar.next()));
        return at;
    }
    private void eliminarCompetenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarCompetenciaActionPerformed
        String copy=eCompetencia.getSelectedItem().toString();
        Competencia at=buscarCompetencia(copy);
        competencias.remove(at);
        dispose();
        MenuCompetencia ventanaCompetencia = new  MenuCompetencia();
        ventanaCompetencia.setVisible(true);
        ventanaCompetencia.setAtletas(atletas);
        ventanaCompetencia.setCompetencias(competencias);
        ventanaCompetencia.setConfig(config);
    }//GEN-LAST:event_eliminarCompetenciaActionPerformed
 /**
    * Busca un objeto a partir de un identificador, retorna el objeto encontrado perteneciente a la clase especificada
    * @param copy Identificador
    */
    private Competencia buscarCompetencia(int copy){
        int contador=0;
        boolean encontrado=false;
        while (contador< competencias.size()&&!encontrado){
            if (copy==competencias.get(contador).getIdCompetencia()) {
                contador=contador;
                encontrado=true;
            }
            else{
               contador++;
            }
        }
        return competencias.get(contador);
    }
    private void irMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_irMenuActionPerformed
        dispose();
        MenuPrincipal ventana = new  MenuPrincipal();
        ventana.setVisible(true);
        ventana.setAtletas(atletas);
        ventana.setCompetencias(competencias);
        ventana.setConfig(config);
    }//GEN-LAST:event_irMenuActionPerformed

    private void eCompetenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eCompetenciaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_eCompetenciaActionPerformed

    private void irMDisciplinaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_irMDisciplinaActionPerformed
        String copy=eCompetencia.getSelectedItem().toString();
        Competencia at=buscarCompetencia(copy);
        dispose();
        MenuDisciplina ventana = new  MenuDisciplina();
        ventana.setVisible(true);
        ventana.setAtletas(atletas);
        ventana.setCompetencias(competencias);
        ventana.setConfig(config);
        ventana.cargarPanel(at);
    }//GEN-LAST:event_irMDisciplinaActionPerformed

    private void agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarActionPerformed
        String copy=eCompetencia.getSelectedItem().toString();
        Competencia at=buscarCompetencia(copy);
        dispose();
        InscribirAtleta ventana = new  InscribirAtleta();
        ventana.setVisible(true);
        ventana.setAtletas(atletas);
        ventana.setCompetencias(competencias);
        ventana.setConfig(config);
        ventana.cargarPanel(at);
    }//GEN-LAST:event_agregarActionPerformed

    private void quitarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quitarActionPerformed
        String copy=eCompetencia.getSelectedItem().toString();
        Competencia at=buscarCompetencia(copy);
        dispose();
        quitarAtleta ventana = new  quitarAtleta();
        ventana.setVisible(true);
        ventana.setAtletas(atletas);
        ventana.setCompetencias(competencias);
        ventana.setConfig(config);
        ventana.cargarPanel(at);
    }//GEN-LAST:event_quitarActionPerformed

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
            java.util.logging.Logger.getLogger(MenuCompetencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuCompetencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuCompetencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuCompetencia.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuCompetencia().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregar;
    private javax.swing.JButton buscar;
    private javax.swing.JComboBox<String> eCompetencia;
    private javax.swing.JButton eliminarCompetencia;
    private javax.swing.JButton irAgregarCompetencia;
    private javax.swing.JButton irMDisciplina;
    private javax.swing.JButton irMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton quitar;
    private javax.swing.JTextArea texto;
    // End of variables declaration//GEN-END:variables
}
