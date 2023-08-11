/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import java.awt.Color;
import java.awt.Font;
import java.awt.SystemColor;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

/**
 *
 * @author Natalia S.R.S
 */
public class Interface extends javax.swing.JFrame {    
    private int aXa= 8;
    private boolean izquierda=false;

    public void setIzquierda(boolean izquierda) {
        this.izquierda = izquierda;
    }
 
    public String getNivel() {
        return nivel;
    }
    
    public void setGame(int aXa){
        this.aXa=aXa;
        generarJuego();
        generarBotones();
    }
    
    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public JButton getBtnBorrarJ() {
        return btnBorrarJ;
    }

    public JButton getBtnBorrarJda() {
        return btnBorrarJda;
    }

  

   


    public JButton getBtnCargarJ() {
        return btnCargarJ;
    }

    

    public JButton getBtnGuargarJ() {
        return btnGuargarJ;
    }

    

    public JButton getBtnIniciarJ() {
        return btnIniciarJ;
    }

    public JLabel getlCronometer() {
        return lCronometer;
    }

    public JLabel getLabH() {
        return labH;
    }

    public JLabel getLabM() {
        return labM;
    }

    public JLabel getLabS() {
        return labS;
    }

    
    public JButton getBtnRehacerJ() {
        return btnRehacerJ;
    }


    public JButton getBtnTerminarJ() {
        return btnTerminarJ;
    }


    public JButton getBtnTop10() {
        return btnTop10;
    }

    public JLabel getLevelLabel() {
        return levelLabel;
    }

    private String nivel = "";
    /**
     * Constructor
     */
    public Interface() {
        initComponents();
        this.getContentPane().setBackground(Color.lightGray);
        setLocationRelativeTo(null);
                
    }  
    /**
     * Genera todas las casillas del juego
     */
    public void setAXa(int pAXa){
        aXa=pAXa;
        generarJuego();
        generarBotones();
    }
    public void generarJuego(){
        if(nivel.equals("facil")){
            levelLabel.setText("Nivel: Fácil");
        }
        if(nivel.equals("intermedio")){
            levelLabel.setText("Nivel: Intermedio");
        }
        if(nivel.equals("dificil")){
            levelLabel.setText("Nivel: Difícil");
        }
        ArrayList<JTextField> tField= new ArrayList<JTextField>();
        Font fuente=new Font("Dialog", Font.BOLD, 16);
        for (int i=0; i<aXa; i++){
            for (int j=0; j<aXa; j++){
                tField.add(new JTextField());
            }
        }
        String posi="";
        int columna=0, fila=0, X=185,Y=98,H=50,W=60;
        int a =90, b=70;
        if(aXa>6){
            H=30;
            b=50;           
        }
        for (JTextField c : tField){
            posi="t"+fila+columna;
            c.setName(posi);
            c.setBounds(X,Y,W,H);
            c.setVisible(true);
            c.setEditable(false);
            c.setFont(fuente);
            c.setHorizontalAlignment(JTextField.CENTER);
            
            c.setBorder(new LineBorder(SystemColor.black));
            c.setAlignmentX((float) 0.5);
            c.setAlignmentY((float) 0.5);
            this.getContentPane().add(c);
            if(columna==aXa-1){
                fila++;
                Y+=b;
                columna=0;
                X=185;
            }
            else{
                columna++;
                X+=a;
            }
        
        }
        tField.clear();
        columna=0;
        fila=0;
        Y=100;
        X=185;
        for (int i=0; i<aXa; i++){
            for (int j=0; j<aXa-1; j++){
                tField.add(new JTextField());
            }
        }
        int h2=16;
        for (JTextField c : tField){
            posi="c"+fila+columna+"b";
            c.setName(posi);
            c.setBounds(X+(W/2)-8,Y+H-3,h2,h2);
            c.setVisible(true);
            c.setEditable(false);
            c.setText("v");
            c.setFont(fuente);
            c.setHorizontalAlignment(JTextField.CENTER);
            c.setBorder(null);
            c.setAlignmentX((float) 0.5);
            c.setAlignmentY((float) 0.5);
            c.setOpaque(false);
            this.getContentPane().add(c);
            if(columna==aXa-1){
                fila++;
                Y+=b;
                columna=0;
                X=185;
            }
            else{
                columna++;
                X+=a;
            }
        }
        tField.clear();
        columna=0;
        fila=0;
        Y=115;
        X=185;
        if (aXa==9){
            Y=105;
        }
        for (int i=0; i<aXa; i++){
            for (int j=0; j<aXa-1; j++){
                tField.add(new JTextField());
            }
        }
        for (JTextField c : tField){
            posi="c"+fila+columna+"a";
            c.setName(posi);
            c.setBounds(X+W+10,Y,h2,h2);
            c.setVisible(true);
            c.setEditable(false);
    
            c.setFont(fuente);
            c.setHorizontalAlignment(JTextField.CENTER);
            c.setBorder(null);
            c.setAlignmentX((float) 0.5);
            c.setAlignmentY((float) 0.5);
           
            c.setOpaque(false);
            this.getContentPane().add(c);
            if(columna==aXa-2){
                fila++;
                Y+=b;
                columna=0;
                X=185;
            }
            else{
                columna++;
                X+=a;
            }        
        }
    }
    
    /**
     * Genera todos los botones del juego
     */
    private void generarBotones(){
        ArrayList<JButton> botones= new ArrayList<JButton>(); 
        for (short i = 0;i<aXa; i++){
            botones.add(new JButton(""+(i+1)));
        }
        int xx= 0;
        int a =0;        
        switch(aXa){
            case 5: 
                xx=555;
                
                break;
            case 6:
                xx=643;
                break;
            case 7:
                xx=733;
                break;
            case 8:
                xx=823;
                break;
            case 9:
                xx=913;
                break;    
        }   
        if(izquierda){
            xx=0;
        }
        for (JButton c : botones){  
            c.setName(c.getText());
            c.setBounds(xx+70,90+a,68,26);
            JFrame Aux= this;            
            this.getContentPane().add(c);
            c.setVisible(true);
            a+=40;
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

        txtNombre = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        levelLabel = new javax.swing.JLabel();
        btnTop10 = new javax.swing.JButton();
        btnTerminarJ = new javax.swing.JButton();
        btnGuargarJ = new javax.swing.JButton();
        btnCargarJ = new javax.swing.JButton();
        lCronometer = new javax.swing.JLabel();
        labH = new javax.swing.JLabel();
        labS = new javax.swing.JLabel();
        labM = new javax.swing.JLabel();
        btnRehacerJ = new javax.swing.JButton();
        btnBorrarJda = new javax.swing.JButton();
        btnBorrarJ = new javax.swing.JButton();
        btnIniciarJ = new javax.swing.JButton();
        sugerencias = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 0, 204));
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        txtNombre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        txtNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNombreActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel2.setText("Nombre del jugador:");

        jLabel3.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 102, 153));
        jLabel3.setText("FUTOSHIKI");
        jLabel3.setToolTipText("");
        jLabel3.setFocusable(false);
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        levelLabel.setFont(new java.awt.Font("Rockwell", 3, 18)); // NOI18N
        levelLabel.setText("Nivel: Fácil");

        btnTop10.setText("<html><CENTER>TOP      10<html>");
        btnTop10.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        btnTop10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTop10ActionPerformed(evt);
            }
        });

        btnTerminarJ.setText("<html><CENTER>TERMINAR JUEGO<html>");
        btnTerminarJ.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        btnTerminarJ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTerminarJActionPerformed(evt);
            }
        });

        btnGuargarJ.setText("GUARDAR JUEGO");
        btnGuargarJ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuargarJActionPerformed(evt);
            }
        });

        btnCargarJ.setText("CARGAR JUEGO");
        btnCargarJ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarJActionPerformed(evt);
            }
        });

        lCronometer.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lCronometer.setText("00 : 00 : 00 : 00 ");

        labH.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labH.setText("Horas");

        labS.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labS.setText("Segundos");

        labM.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        labM.setText("Minutos");

        btnRehacerJ.setText("<html><CENTER>REHACER JUGADA<html>");
        btnRehacerJ.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        btnRehacerJ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRehacerJActionPerformed(evt);
            }
        });

        btnBorrarJda.setText("<html><CENTER>BORRAR JUEGO<html>");
        btnBorrarJda.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        btnBorrarJda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarJdaActionPerformed(evt);
            }
        });

        btnBorrarJ.setText("BORRAR JUGADA");
        btnBorrarJ.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnBorrarJ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarJActionPerformed(evt);
            }
        });

        btnIniciarJ.setText("<html><CENTER>INICIAR JUEGO<html>");
        btnIniciarJ.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        btnIniciarJ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarJActionPerformed(evt);
            }
        });

        sugerencias.setText("<html><CENTER>SUGERENCIAS<html>");
        sugerencias.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        sugerencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sugerenciasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(46, 46, 46)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 420, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(2, 2, 2)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(301, 301, 301)
                                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(labH)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(labM)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(labS)
                                            .addGap(114, 114, 114))
                                        .addComponent(lCronometer, javax.swing.GroupLayout.Alignment.LEADING)))))
                        .addGap(182, 476, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(levelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 401, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addComponent(jLabel1))
            .addGroup(layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(btnIniciarJ, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBorrarJda, javax.swing.GroupLayout.DEFAULT_SIZE, 94, Short.MAX_VALUE)
                    .addComponent(btnTerminarJ, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnBorrarJ, javax.swing.GroupLayout.DEFAULT_SIZE, 96, Short.MAX_VALUE)
                    .addComponent(btnRehacerJ, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btnGuargarJ, javax.swing.GroupLayout.DEFAULT_SIZE, 142, Short.MAX_VALUE)
                    .addComponent(btnCargarJ, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTop10, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(sugerencias, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(153, 153, 153))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(175, 175, 175)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(levelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 444, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labM)
                            .addComponent(labH)
                            .addComponent(labS))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnRehacerJ, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                            .addComponent(lCronometer)
                            .addComponent(btnGuargarJ, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnBorrarJda, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btnIniciarJ, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(btnTerminarJ, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnBorrarJ, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnCargarJ, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnTop10, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(sugerencias, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void btnTop10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTop10ActionPerformed
        
    }//GEN-LAST:event_btnTop10ActionPerformed

    private void btnIniciarJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarJActionPerformed
        

    }//GEN-LAST:event_btnIniciarJActionPerformed

    private void btnGuargarJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuargarJActionPerformed
       
    }//GEN-LAST:event_btnGuargarJActionPerformed

    private void btnCargarJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarJActionPerformed
       
    }//GEN-LAST:event_btnCargarJActionPerformed

    private void btnBorrarJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarJActionPerformed

    }//GEN-LAST:event_btnBorrarJActionPerformed

    private void btnRehacerJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRehacerJActionPerformed
        
    }//GEN-LAST:event_btnRehacerJActionPerformed
    
    private void btnBorrarJdaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarJdaActionPerformed
        
    }//GEN-LAST:event_btnBorrarJdaActionPerformed

    private void btnTerminarJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTerminarJActionPerformed
        
    }//GEN-LAST:event_btnTerminarJActionPerformed

    private void sugerenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sugerenciasActionPerformed
        
    }//GEN-LAST:event_sugerenciasActionPerformed

    public JButton getSugerencias() {
        return sugerencias;
    }

    
    
    
   
    
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
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interface.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interface().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnBorrarJ;
    public javax.swing.JButton btnBorrarJda;
    public javax.swing.JButton btnCargarJ;
    public javax.swing.JButton btnGuargarJ;
    private javax.swing.JButton btnIniciarJ;
    public javax.swing.JButton btnRehacerJ;
    public javax.swing.JButton btnTerminarJ;
    public javax.swing.JButton btnTop10;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lCronometer;
    private javax.swing.JLabel labH;
    private javax.swing.JLabel labM;
    private javax.swing.JLabel labS;
    public javax.swing.JLabel levelLabel;
    public javax.swing.JButton sugerencias;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables

    public JTextField getTxtNombre() {
        return txtNombre;
    }
}
