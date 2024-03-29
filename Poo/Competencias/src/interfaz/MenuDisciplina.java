/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package interfaz;

import java.util.ArrayList;
import programa1.Atleta;
import programa1.Competencia;
import programa1.Configuracion;
import programa1.Disciplina;

/**
 *Maneja las disciplinas
 * @author Usuario
 */
public class MenuDisciplina extends javax.swing.JFrame {
    private ArrayList<Atleta> atletas;
    private ArrayList<Competencia> competencias;
    private Configuracion config;
    private Competencia competencia;
    /**
     * Creates new form MenuDisciplina
     */
    public MenuDisciplina() {
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

        jLabel1 = new javax.swing.JLabel();
        irAgregar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        texto = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();
        e = new javax.swing.JComboBox<>();
        buscar = new javax.swing.JButton();
        eliminar = new javax.swing.JButton();
        irMenu = new javax.swing.JButton();
        irPruebas = new javax.swing.JButton();
        volver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Disciplina");

        irAgregar.setText("Agregar Disciplina");
        irAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                irAgregarActionPerformed(evt);
            }
        });

        texto.setEditable(false);
        texto.setColumns(20);
        texto.setRows(5);
        jScrollPane1.setViewportView(texto);

        jLabel2.setText("Información de la Disciplina");

        e.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { }));

        buscar.setText("Buscar");
        buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarActionPerformed(evt);
            }
        });

        eliminar.setText("Eliminar Disciplina");
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });

        irMenu.setText("Menu");
        irMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                irMenuActionPerformed(evt);
            }
        });

        irPruebas.setText("Pruebas");
        irPruebas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                irPruebasActionPerformed(evt);
            }
        });

        volver.setText("Volver");
        volver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                volverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(e, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(buscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(irPruebas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(10, 10, 10)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(eliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(irAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(88, 88, 88)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 66, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(irMenu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(volver, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addContainerGap())
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(44, 44, 44)))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(e, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(buscar)
                            .addComponent(eliminar))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(irPruebas)
                            .addComponent(irAgregar))))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(irMenu)
                    .addComponent(volver))
                .addGap(93, 93, 93))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    /**
    * Hereda la lista de atletas
    *@param pAtletas
    */
    public void setAtletas(ArrayList<Atleta> pAtletas){
            atletas=pAtletas;
    }
    /**
    *Hereda la competencia a la que pertenece esta clase y carga las cajas de seleccion
    *@param pCompetencia Competencia
    */
    public void cargarPanel(Competencia pCompetencia){
        for(Disciplina i : pCompetencia.getDisciplinas()){
            e.addItem(i.getNombre());
        } 
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
    
    private void irAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_irAgregarActionPerformed
        dispose();
        AgregarDisciplina ventana = new  AgregarDisciplina();
        ventana.setVisible(true);
        ventana.setAtletas(atletas);
        ventana.setCompetencias(competencias);
        ventana.setConfig(config);
        ventana.cargar(competencia);
    }//GEN-LAST:event_irAgregarActionPerformed
    /**
    * Busca un objeto a partir de un identificador, retorna el objeto encontrado perteneciente a la clase especificada
    * @param copy Identificador
    */
    private Disciplina buscarDisciplina(String copy){
        int contador=0;
        boolean encontrado=false;
        while (contador< competencia.getDisciplinas().size()&&!encontrado){
            if (copy==competencia.getDisciplinas().get(contador).getNombre()) {
                contador=contador;
                encontrado=true;
            }
            else{
               contador++;
            }
        }
        return competencia.getDisciplinas().get(contador);
    }
    
    private void buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarActionPerformed
        String c=e.getSelectedItem().toString();
        Disciplina at = buscarDisciplina(c);
        texto.setText(at.toString());
    }//GEN-LAST:event_buscarActionPerformed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
        String c=e.getSelectedItem().toString();
        Disciplina at = buscarDisciplina(c);
        competencia.getDisciplinas().remove(at);
        dispose();
        MenuDisciplina ventana = new  MenuDisciplina();
        ventana.setVisible(true);
        ventana.setAtletas(atletas);
        ventana.setCompetencias(competencias);
        ventana.setConfig(config);
        ventana.cargarPanel(competencia);
        
    }//GEN-LAST:event_eliminarActionPerformed

    private void irMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_irMenuActionPerformed
        dispose();
        MenuPrincipal ventana = new  MenuPrincipal();
        ventana.setVisible(true);
        ventana.setAtletas(atletas);
        ventana.setCompetencias(competencias);
        ventana.setConfig(config);
    }//GEN-LAST:event_irMenuActionPerformed

    private void irPruebasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_irPruebasActionPerformed
        String c=e.getSelectedItem().toString();
        Disciplina at = buscarDisciplina(c);
        dispose();
        MenuPrueba ventana = new  MenuPrueba();
        ventana.setVisible(true);
        ventana.setAtletas(atletas);
        ventana.setCompetencias(competencias);
        ventana.setConfig(config);
        ventana.cargarPanel(competencia, at);
    }//GEN-LAST:event_irPruebasActionPerformed

    private void volverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_volverActionPerformed
        dispose();
        MenuCompetencia ventana = new MenuCompetencia();
        ventana.setVisible(true);
        ventana.setAtletas(atletas);
        ventana.setCompetencias(competencias);
        ventana.setConfig(config);
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
            java.util.logging.Logger.getLogger(MenuDisciplina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuDisciplina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuDisciplina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuDisciplina.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuDisciplina().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscar;
    private javax.swing.JComboBox<String> e;
    private javax.swing.JButton eliminar;
    private javax.swing.JButton irAgregar;
    private javax.swing.JButton irMenu;
    private javax.swing.JButton irPruebas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea texto;
    private javax.swing.JButton volver;
    // End of variables declaration//GEN-END:variables
}
