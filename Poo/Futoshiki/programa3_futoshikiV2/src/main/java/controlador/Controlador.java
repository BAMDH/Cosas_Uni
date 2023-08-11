/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import Modelo.Model;
import Vista.InitialScreen;
import Vista.Interface;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JTextField;


public class Controlador{
    private Interface vista;
    private Model modelo;
    public Controlador(Interface vista, Model modelo){
        this.modelo=modelo;
        this.vista=vista;
        agregarListeners();
        
    }
    private void agregarListeners(){
            
         for (Component c: vista.getContentPane().getComponents()){
            if (c instanceof JButton){
                JButton e =(JButton)c;
                if (c.getName()!=null){
                    
                    e.addActionListener(new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent f) {
                            
                           modelo.modificar(e);
                          
                        }
                    });
                }
            }
        }
         for (Component c: vista.getContentPane().getComponents()){
            if (c instanceof JTextField){
                JTextField e =(JTextField)c;
                if (c.getName()!=null){
                    if((c.getName().charAt(0)+"").equals("t")){
                        e.addMouseListener(new MouseAdapter(){ 
                        @Override   
                        public void mouseClicked(MouseEvent evt) {
                           modelo.seleccionar(e);
                        }
                    });
                    }
                }
            }
        }
        JButton boton =vista.getBtnBorrarJda();
        boton.addActionListener(new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent e) {                            
                           modelo.borrarJugada();                          
                        }
        });
        boton =vista.getBtnTerminarJ();
        boton.addActionListener(new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent e) {                            
                           boolean hacer= modelo.terminar();
                           if(hacer){
                           InitialScreen interfaz = new InitialScreen();
                            interfaz.setVisible(true);
                            interfaz.setGame(modelo.getFutoshikis(),modelo.getConfi());
                            vista.dispose();
                           }
                        }
        });
        boton =vista.getBtnRehacerJ();
        boton.addActionListener(new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent e) {                            
                           modelo.rehacerJugada();
                        }
        });
        boton =vista.getBtnBorrarJ();
        boton.addActionListener(new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent e) {                            
                           modelo.borrarMovimiento();
                        }
        });
        boton =vista.getBtnCargarJ();
        boton.addActionListener(new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent e) {                            
                           modelo.cargar();
                        }
        });
        boton =vista.getBtnGuargarJ();
        boton.addActionListener(new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent e) {                            
                           modelo.guardar();
                        }
        });
        boton =vista.getSugerencias();
        boton.addActionListener(new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent e) {                            
                           modelo.sugerir();
                        }
        });
        boton =vista.getBtnTop10();
        boton.addActionListener(new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent e) {                            
                           modelo.verTop();
                        }
        });
        boton =vista.getBtnIniciarJ();
        boton.addActionListener(new ActionListener(){
                        @Override
                        public void actionPerformed(ActionEvent e) {                            
                           modelo.iniciar();
                        }
        });
    }
}
