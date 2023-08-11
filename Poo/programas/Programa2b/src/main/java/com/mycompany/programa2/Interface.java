/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.programa2;

import futoshiki.Casilla;
import futoshiki.Futoshiki;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author Natalia S.R.S
 */
public class Interface extends javax.swing.JFrame {
    private String seleccionado="";
    private Futoshiki futoshiki;
    private ListaDoblementeEnlazada<String> pila;
    private ListaDoblementeEnlazada<String> pila2;
    private boolean avanza=false;
    private boolean retrocede=false;
    private boolean jugar=false;
    private ArrayList<Futoshiki> futoshikis;
    private ArrayList<Integer> orden;
    private int posicion=0;
    private boolean ganado= false;
    private int aXa= 9;
    private ArrayList<JButton> botones= new ArrayList<JButton>();
   
    private ArrayList<info> Mejores=new ArrayList<info>();
    /**
     * Creates new form Interface
     */
  
    private boolean aparece;
    
    public Interface() {
        initComponents();
        this.getContentPane().setBackground(Color.lightGray);
        setLocationRelativeTo(null);
        t = new Timer(10, acciones);
        pila= new  ListaDoblementeEnlazada();
        pila2= new  ListaDoblementeEnlazada();
        orden = new ArrayList<Integer>();
        cargarT();
        
    }  
    /**
     * Genera todas las casillas del juego
     */
    private void generarJuego(){
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
        
        if(aXa==9){
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
            c.addMouseListener(new MouseAdapter(){ 
                @Override   
                public void mouseClicked(MouseEvent evt) {
                   seleccionado=c.getName();
                    
                   cambiarBorde();
                }
           
            });
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
    private void cargarBotones(){
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
        for (JButton c : botones){  
            c.setBounds(xx+70,90+a,68,26);
            JFrame Aux= this;
            c.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                   if(jugar){
                    retrocede= false;
                    avanza=false;
                    modificarCasilla(Integer.parseInt(c.getText()));
                  }
                   else{
                        JOptionPane.showMessageDialog(Aux,"No ha iniciado una partida","Error",JOptionPane.ERROR_MESSAGE);
                   }
                }
            });
            this.getContentPane().add(c);
            c.setVisible(true);
            a+=40;
        }
    }
    /**
     * Carga todos los parametros del juego
     * @param pFutoshikis Partidas
     * @param pAXa tamaño de las partidas a jugar
     */
    public void setGame(ArrayList<Futoshiki> pFutoshikis,int pAXa){
        futoshikis=pFutoshikis;
        aXa=pAXa;
        int max = futoshikis.size();
        int min = 0;
        int range = max - min;
        ArrayList<Integer> Aux=new ArrayList<Integer>();
        while(Aux.size() <futoshikis.size()){
            Random rand = new Random();
            int rand1 = rand.nextInt(max);
            if(!Aux.contains(rand1)){
                Aux.add(rand1);
                if(futoshikis.get(rand1).getaXa()==aXa){
                    orden.add(rand1);
                }
            }
        }
        futoshiki=futoshikis.get(orden.get(0));
        cargarBotones();
        generarJuego();
        llenarPlantilla();
    }
    /**
     * Carga el top 10
     */
    public void cargarT(){
        try{
            FileInputStream fi = new FileInputStream(new File("top.dat"));
            ObjectInputStream oi = new ObjectInputStream(fi);
            Mejores = (ArrayList<info>) oi.readObject();
            oi.close();
            fi.close();
        }
        catch (FileNotFoundException e) {
			System.out.println("File not found");
	} catch (IOException e) {
		System.out.println("Error initializing stream");
	} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
        }
    }
    /**
     * Carga la partida guardada
     */
    public void cargar(){
        Partida partida=null;
        try{
            FileInputStream fi = new FileInputStream(new File("futoshiki2022juegoactual.dat"));
            ObjectInputStream oi = new ObjectInputStream(fi);
            partida = (Partida) oi.readObject();
            oi.close();
            fi.close();
        }
        catch (FileNotFoundException e) {
			System.out.println("File not found");
	} catch (IOException e) {
		System.out.println("Error initializing stream");
	} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
        }
        if(partida!=null){
            futoshiki=partida.getFutoshiki();
            pila=partida.getPila();
            pila2=partida.getPila2();
            posicion=partida.getP();
            orden=partida.getOrden();
            int [] tiempo=partida.getT();
            cs=tiempo[0];
            s=tiempo[1];
            m=tiempo[2];
            h=tiempo[3];
            if (cs!=0||s!=0||m!=0||h!=0){
                aparece=true;
            }
            if (aparece &&!(t.isRunning())){
                t.start();
            }
            txtNombre.setText(partida.getN());
            llenarPlantilla();
            modificarColores();
            jugar=true;
            ganado=false;
        }
        
    
    }
    /**
     * Guarda el top 10
     */
    private void guardarT(){
         try {
                        
                        FileOutputStream f = new FileOutputStream(new File("top.dat"));
			ObjectOutputStream o = new ObjectOutputStream(f);                                    
			o.writeObject(Mejores);
			o.close();
			f.close();

			

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
                        
		} catch (IOException e) {
			System.out.println("Error initializing stream");
                        e.printStackTrace();
		} 
    }
    /**
     * Guarda la partida actual 
     */
    private void guardar(){
        int [] tiempo = new int[4];
        tiempo[0]=cs;
        tiempo[1]=s;
        tiempo[2]=m;
        tiempo[3]=h;
        Partida partida= new Partida(futoshiki,pila,pila2,orden,posicion,tiempo,txtNombre.getText());
        
        try {
                        
                        FileOutputStream f = new FileOutputStream(new File("futoshiki2022juegoactual.dat"));
			ObjectOutputStream o = new ObjectOutputStream(f);                                    
			o.writeObject(partida);
			o.close();
			f.close();

			

		} catch (FileNotFoundException e) {
			System.out.println("File not found");
                        
		} catch (IOException e) {
			System.out.println("Error initializing stream");
                        e.printStackTrace();
		} 
    }   
    /**
     * Llena todos los campos de la interfaz para poder visualizar la partida actual
     */
    private void llenarPlantilla(){
        for (Component c: this.getContentPane().getComponents()){
            JTextField campo;
            if (c instanceof JTextField){
                campo = (JTextField)c;               
                String nombre = c.getName();
                
                if (nombre!=null){
                    int fila = Integer.parseInt(nombre.charAt(1)+"");
                    int columna = Integer.parseInt(nombre.charAt(2)+"");
                    
                  
                      
                        Casilla Aux = futoshiki.getArray()[fila][columna];
                        if((nombre.charAt(0)+"").equals("t")){
                            campo.setBackground(Color.white);
                          if (Aux.getValor()!=0){
                                campo.setText(Aux.getValor()+"");
                            }
                            else{campo.setText("");}
                        }
                        
                        if((nombre.charAt(0)+"").equals("c")){
                             String condicion="";
                            if((nombre.charAt(3)+"").equals("b")){
                                condicion = Aux.getCondicion();
                                if (condicion==null){
                                    campo.setText("");
                                }
                                else{
                                    if(condicion.equals("z")){
                                        campo.setText("^");
                                        Font fuente=new Font("Dialog", Font.BOLD, 22);
                                        c.setFont(fuente);
                                    }
                                    else if(condicion.equals("y")){
                                        campo.setText("v");
                                        Font fuente=new Font("Dialog", Font.BOLD, 16);
                                        c.setFont(fuente);
                                    }
                                    else{
                                        campo.setText("");
                                    }
                                }
                                
                            }
                            if((nombre.charAt(3)+"").equals("a")){
                                condicion = Aux.getCondicion();
                                if (condicion==null){
                                    campo.setText("");
                                }
                                else{
                                    if(condicion.equals("a")){
                                        campo.setText(">");
                                    }
                                   else if(condicion.equals("b")){
                                        campo.setText("<");
                                    }
                                    else{
                                        campo.setText("");
                                    }
                                    Font fuente=new Font("Dialog", Font.BOLD, 16);
                                        c.setFont(fuente);
                                }
                            }
                        }
                   
                }
            }
            
        }
    
    }
    /**
     * Colorea todas las casillas de verde
     */
    private void colorearVerde(){
        for (Component c: this.getContentPane().getComponents()){
            JTextField campo;
            JTextField Aux;

            if (c instanceof JTextField){
                Aux = (JTextField)c;
                if (Aux.getName()!=null){                    
                    if ((Aux.getName().charAt(0)+"").equals("t")){
                        campo=(JTextField)c;                            
                        campo.setBackground(Color.green);
                    }                       
                }
            }   
        }
    }
    /**
     * Modifica los colores de las casillas acorde su valor y si incumple alguna condición
     * En caso de incumplir una condición, lo pinta de rojo, en caso contrario de blanco
     * Colorea todas las casillas de verde si no se encuentra ninguna incongruencia y todas poseen un valor
     */
    private void modificarColores(){
        boolean ceros=false;
        boolean condiciones=false;
        for (Component c: this.getContentPane().getComponents()){
                JTextField campo;
                JTextField Aux;

                if (c instanceof JTextField){
                    Aux = (JTextField)c;
                    if (Aux.getName()!=null){                    
                        if ((Aux.getName().charAt(0)+"").equals("t")){
                            campo=(JTextField)c;
                            String provisional=campo.getName();
                            int fila = Integer.parseInt(provisional.charAt(1)+"");
                            int columna = Integer.parseInt(provisional.charAt(2)+"");
                            Casilla[][] Aux3 = futoshiki.getArray();
                            Casilla Aux2=Aux3[fila][columna];
                            if(Aux2.getValor()==0){
                                ceros=true;
                            }
                            if(Aux2.confirmarConstante()){

                            }
                            else{

                                boolean cierto=false;
                                boolean condicion1=futoshiki.confirmarCondicion(fila, columna);
                                boolean condicion2=futoshiki.confirmarExistenciaX(fila, columna);
                                boolean condicion3=futoshiki.confirmarExistenciaY(fila, columna);
                                if(condicion2){
                                    
                                       condiciones=true;
                                       cierto= true;
                                    
                                }
                                if(condicion3){
                                   
                                       cierto=true;
                                       condiciones=true;
                                    
                                }

                                if(!condicion1){
                                    condiciones=true;
                                    cierto=true;

                                }
                                if(fila<=(aXa-1)&&fila>0){
                                    String condi=futoshiki.getArray()[fila-1][columna].getCondicion();
                                    if (condi!=null){
                                        if (condi.equals("z") ||condi.equals("y") ){
                                            if (!futoshiki.confirmarCondicion(fila-1, columna)){
                                                cierto=true;
                                                condiciones=true;
                                            }
                                        }
                                    }
                                }

                                if(columna<=(aXa-1)&&columna>0){
                                    String condi=futoshiki.getArray()[fila][columna-1].getCondicion();
                                    if (condi!=null){
                                        if (condi.equals("a") ||condi.equals("b") ){
                                            if (!futoshiki.confirmarCondicion(fila, columna-1)){
                                                condiciones=true;
                                                cierto=true;

                                            }
                                        }
                                    }
                                }

                                if(cierto){
                                    campo.setBackground(Color.RED);

                                }
                                else{
                                    campo.setBackground(Color.WHITE);
                                }
                            }
                        }
                    }
                }   
        }
        if(!ceros&&!condiciones){
            colorearVerde();
            ganado=true;
            jugar=false;
            t.stop();
            info tiempo = new info(cs,s,m,h,txtNombre.getText());
            agregarTop(tiempo);
            guardarT();
            JOptionPane.showMessageDialog(this,"¡Felicidades, has ganado!","Victoria",JOptionPane.INFORMATION_MESSAGE);
        }
    } 
    /**
     * Agrega un nuevo record al top, si no cumple con los requisitos
     * @param a Valor a analizar para saber si corresponde al top 10
     */
    private void agregarTop(info a){
        ArrayList<info> Aux = new ArrayList<info>();
        if (Mejores.size()==0){
            Aux.add(a);
          }
          else{   
            for(info t : Mejores){
                
                if(Aux.size()<10){
                
                if(t.getMedicion() > a.getMedicion()&&!Aux.contains(a)){
                    Aux.add(a);
                }
                if(!Aux.contains(t))    
                Aux.add(t);
                }
               if(Aux.size()>10)
                    Aux.remove(10);
                
        }
            if(Aux.size()<10){
                System.out.println(!Aux.contains(a));
                if(!Aux.contains(a)){
                    Aux.add(a);
                }
            }
            
         
    }
        Mejores=Aux;
 }
    /**
     * Modifica la casilla según el valor que recibe esta función
     * Cambia el color de las casillas si el valor se repite o no cumple con la condición
     * @param numero Numero a insertar en la casilla
     */
    private void modificarCasilla(int numero){
        for (Component c: this.getContentPane().getComponents()){
            JTextField campo;
            JTextField Aux;
            if (c instanceof JTextField){
                Aux = (JTextField)c;
                if (Aux.getName()!=null){                    
                    if (Aux.getName().equals(seleccionado)||Aux.getBackground()==Color.RED){
                        campo=(JTextField)c;
                        String provisional=campo.getName();
                        int fila = Integer.parseInt(provisional.charAt(1)+"");
                        int columna = Integer.parseInt(provisional.charAt(2)+"");
                        Casilla[][] Aux3 = futoshiki.getArray();
                        Casilla Aux2=Aux3[fila][columna];
                        if(Aux2.confirmarConstante()){
                            JOptionPane.showMessageDialog(this,"Este valor es una constante","Error",JOptionPane.ERROR_MESSAGE);
                        }
                        else{
                            if (provisional.equals(seleccionado)){
                                
                                Aux2.setValor(numero);
                                if (!retrocede || avanza){
                                    String valor=campo.getText();
                                    if(!avanza){
                                        pila2.clear();
                                    }
                                    if (valor.equals("")){
                                        pila.push(seleccionado+"n");
                                    }
                                    else{
                                        pila.push(seleccionado+valor);
                                    }
                                }
                                
                                
                            }
                            campo.setText(futoshiki.getArray()[fila][columna].getValor()+"");
                            boolean cierto=false;
                            boolean condicion1=futoshiki.confirmarCondicion(fila, columna);
                            boolean condicion2=futoshiki.confirmarExistenciaX(fila, columna);
                            boolean condicion3=futoshiki.confirmarExistenciaY(fila, columna);
                            if(condicion2){
                                 if (provisional.equals(seleccionado))
                                 JOptionPane.showMessageDialog(this,"Ya existe en la fila","Error",JOptionPane.ERROR_MESSAGE);
                                 cierto= true;
                            }
                            if(condicion3){
                                if (provisional.equals(seleccionado))
                                 JOptionPane.showMessageDialog(this,"Ya existe en la columna","Error",JOptionPane.ERROR_MESSAGE);
                                 cierto=true;
                            }
                            boolean condiciones=false;
                            if(!condicion1){
                               condiciones=true;
                               cierto=true;
                                System.out.println("1");
                            }
                            if(fila<=(aXa-1)&&fila>0){
                                if (!futoshiki.confirmarCondicion(fila-1, columna)){
                                    condiciones = true;
                                    cierto=true;
                                    
                                }
                            }
                            
                            if(columna<=(aXa-1)&&columna>0){
                                if (!futoshiki.confirmarCondicion(fila, columna-1)){
                                    condiciones = true;
                                    cierto=true;
                                    System.out.println("3");
                                }
                            }
                            if(condiciones){
                               if (provisional.equals(seleccionado))
                               JOptionPane.showMessageDialog(this,"No se da la condición","Error",JOptionPane.ERROR_MESSAGE);
                            }
                            if(cierto){
                                campo.setBackground(Color.RED);
                                
                            }
                            else{
                                campo.setBackground(Color.WHITE);
                            }
                        }
                    }
                }
            }   
        }
        modificarColores();
    }
    /**
     * Cambia el Borde de la casilla seleccionada
     */
    private void cambiarBorde(){
        Border personalizado; 
        LineBorder linear = new LineBorder(SystemColor.textHighlight);
        Border basico = new LineBorder(SystemColor.black);
        personalizado = new LineBorder(SystemColor.blue);
        for (Component c: this.getContentPane().getComponents()){
            JTextField Aux;
            if (c instanceof JTextField){
                Aux = (JTextField)c;
                if (Aux.getName()!=null){
                    if(((Aux.getName().charAt(0))+"").equals("t")){
                        if(Aux.getName().equals(seleccionado)){
                            Aux.setBorder(personalizado);
                        }
                        else{
                            Aux.setBorder(basico);
                        }
                    }
                }
                
            }
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
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(levelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(labH)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(labM)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                            .addComponent(labS)
                                            .addGap(114, 114, 114))
                                        .addComponent(lCronometer, javax.swing.GroupLayout.Alignment.LEADING)))))
                        .addGap(338, 338, 338))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(btnIniciarJ, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(187, 187, 187)
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
                        .addComponent(btnTop10, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(43, 138, Short.MAX_VALUE)
                .addComponent(jLabel1))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 486, Short.MAX_VALUE)
                        .addComponent(btnTop10, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 447, Short.MAX_VALUE)
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
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(btnBorrarJ, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnCargarJ, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed

    private void btnTop10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTop10ActionPerformed
        if(jugar){
             JOptionPane.showMessageDialog(this,"Está jugando una partida","Error",JOptionPane.ERROR_MESSAGE);
        }
        else{
            int contador=1;
            String resultado="Top10\n";
            for(info p : Mejores){
               resultado+="Lugar: "+contador+"\n";
               resultado+=p.toString();
               contador++;
            }
             JOptionPane.showMessageDialog(this,resultado);
        }
        
         
         
    }//GEN-LAST:event_btnTop10ActionPerformed

    private void btnIniciarJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarJActionPerformed
        if(txtNombre.getText().equals("")){
            JOptionPane.showMessageDialog(this,"No se ha insertado un nombre","Error",JOptionPane.ERROR_MESSAGE);
        }
        else{
            if(ganado){
                ganado=false;
                posicion++;
                 Casilla [][] Aux=futoshiki.getArray();
                for (int i=0;i<aXa;i++){
                    for (int j=0;j<aXa;j++){
                        if(!Aux[i][j].confirmarConstante()){
                            Aux[i][j].setValor(0);
                        }
                    }
                }
                for (Component c: this.getContentPane().getComponents()){
                    JTextField campo;
                    if (c instanceof JTextField){
                        campo = (JTextField)c;

                        String nombre = c.getName();
                        if (nombre!=null){
                            int fila = Integer.parseInt(nombre.charAt(1)+"");
                            int columna = Integer.parseInt(nombre.charAt(2)+"");
                            Casilla Aux2 = futoshiki.getArray()[fila][columna];
                            if((nombre.charAt(0)+"").equals("t")){
                                campo.setBackground(Color.white);
                                if (Aux2.getValor()==0){
                                    campo.setText("");
                                }
                                else{
                                    campo.setText(Aux2.getValor()+"");
                                }
                            }
                        }
                    }
                }
                if(posicion==futoshikis.size()){
                    posicion=0;
                }
                
                futoshiki=futoshikis.get(orden.get(posicion));
                llenarPlantilla();
                modificarColores();
                
            }
            jugar=true;
            if(aparece==true){
                  s=0;
                  cs=0;
                  m=0;
                  h=0;
                  t.start();
           }
        }

    }//GEN-LAST:event_btnIniciarJActionPerformed

    private void btnGuargarJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuargarJActionPerformed
       guardar();
    }//GEN-LAST:event_btnGuargarJActionPerformed

    private void btnCargarJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarJActionPerformed
        cargar();
    }//GEN-LAST:event_btnCargarJActionPerformed

    private void btnBorrarJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarJActionPerformed

        retrocede=true;

        if(pila.peek()!=null){
            String accion = pila.pop().dato.toString();
            for (Component c: this.getContentPane().getComponents()){
                JTextField Aux;
                if (c instanceof JTextField){
                    Aux = (JTextField)c;
                    if (Aux.getName()!=null){
                        if(Aux.getName().equals(""+accion.charAt(0)+accion.charAt(1)+accion.charAt(2))){

                            seleccionado=""+accion.charAt(0)+accion.charAt(1)+accion.charAt(2);
                            System.out.println("seleccionado");
                            cambiarBorde();
                            int fila=Integer.parseInt(""+accion.charAt(1));
                            int columna=Integer.parseInt(""+accion.charAt(2));

                            pila2.push(""+accion.charAt(0)+accion.charAt(1)+accion.charAt(2)+futoshiki.getArray()[fila][columna].getValor());
                            if (("n").equals(""+accion.charAt(3))){
                                futoshiki.getArray()[fila][columna].setValor(0);
                                Aux.setText("");
                                Aux.setBackground(Color.white);
                            }
                            else{
                                modificarCasilla(Integer.parseInt(""+accion.charAt(3)));
                            }

                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_btnBorrarJActionPerformed

    private void btnRehacerJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRehacerJActionPerformed
        avanza=true;

        if(pila2.peek()!=null){
            String accion = pila2.pop().dato.toString();
            for (Component c: this.getContentPane().getComponents()){
                JTextField Aux;
                if (c instanceof JTextField){
                    Aux = (JTextField)c;
                    if (Aux.getName()!=null){
                        if(Aux.getName().equals(""+accion.charAt(0)+accion.charAt(1)+accion.charAt(2))){
                            seleccionado=""+accion.charAt(0)+accion.charAt(1)+accion.charAt(2);
                            System.out.println("seleccionado");
                            cambiarBorde();
                            int fila=Integer.parseInt(""+accion.charAt(1));
                            int columna=Integer.parseInt(""+accion.charAt(2));
                            if (("n").equals(""+accion.charAt(3))){
                                futoshiki.getArray()[fila][columna].setValor(0);
                                Aux.setText("");
                                Aux.setBackground(Color.white);
                            }
                            else{
                                modificarCasilla(Integer.parseInt(""+accion.charAt(3)));
                            }

                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_btnRehacerJActionPerformed

    private void btnBorrarJdaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarJdaActionPerformed
        Casilla [][] Aux=futoshiki.getArray();
        for (int i=0;i<aXa;i++){
            for (int j=0;j<aXa;j++){
                if(!Aux[i][j].confirmarConstante()){
                    Aux[i][j].setValor(0);
                }
            }
        }
        for (Component c: this.getContentPane().getComponents()){
            JTextField campo;
            if (c instanceof JTextField){
                campo = (JTextField)c;

                String nombre = c.getName();
                if (nombre!=null){
                    int fila = Integer.parseInt(nombre.charAt(1)+"");
                    int columna = Integer.parseInt(nombre.charAt(2)+"");
                    Casilla Aux2 = futoshiki.getArray()[fila][columna];
                    if((nombre.charAt(0)+"").equals("t")){
                        campo.setBackground(Color.white);
                        if (Aux2.getValor()==0){
                            campo.setText("");
                        }
                        else{
                            campo.setText(Aux2.getValor()+"");
                        }
                    }
                }
            }
        }
    }//GEN-LAST:event_btnBorrarJdaActionPerformed

    private void btnTerminarJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTerminarJActionPerformed
        if (jugar){
            if(t.isRunning()){
                t.stop();
            }
            int opcion =JOptionPane.showConfirmDialog(this,"¿Está seguro?","¿Salir?",JOptionPane.YES_NO_OPTION);
            if (opcion==0){
                Casilla [][] Aux=futoshiki.getArray();
                for (int i=0;i<aXa;i++){
                    for (int j=0;j<aXa;j++){
                        if(!Aux[i][j].confirmarConstante()){
                            Aux[i][j].setValor(0);
                        }
                    }
                }
                for (Component c: this.getContentPane().getComponents()){
                    JTextField campo;
                    if (c instanceof JTextField){
                        campo = (JTextField)c;

                        String nombre = c.getName();
                        if (nombre!=null){
                            int fila = Integer.parseInt(nombre.charAt(1)+"");
                            int columna = Integer.parseInt(nombre.charAt(2)+"");
                            Casilla Aux2 = futoshiki.getArray()[fila][columna];
                            if((nombre.charAt(0)+"").equals("t")){
                                campo.setBackground(Color.white);
                                if (Aux2.getValor()==0){
                                    campo.setText("");
                                }
                                else{
                                    campo.setText(Aux2.getValor()+"");
                                }
                            }
                        }
                    }
                }
                InitialScreen interfaz = new InitialScreen();
                interfaz.setVisible(true);
                interfaz.setGame(futoshikis,aXa);
                dispose();
            }
            else{
                if(aparece==true){
                    t.start();
                }
            }
        }
        else{
            JOptionPane.showMessageDialog(this,"No se ha iniciado una partida","Error",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnTerminarJActionPerformed

    
    public void setReloj(boolean r){
        aparece=r;
        lCronometer.setVisible(aparece);
        labH.setVisible(aparece);
        labM.setVisible(aparece);
        labS.setVisible(aparece);
        
    }
    
    private Timer t;
    private int h, m, s, cs;
    private ActionListener acciones = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ++cs;
            if(cs==100){
                cs=0;
                ++s;
            }
            if(s==60){
                s=0;
                ++m;
            }
            if(m==60){
                m=0;
                ++h;
            }
            actualizarLabel();
        }
    };
    
    private void actualizarLabel(){
        String tiempo = (h<=9?"0":"")+ h + ":" + (m<=9?"0":"") + m + ":" + (s<=9?"0":"")+ s + ":" + (cs<=9?"0":"")+cs;
        lCronometer.setText(tiempo);
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
    private javax.swing.JButton btnBorrarJ;
    private javax.swing.JButton btnBorrarJda;
    private javax.swing.JButton btnCargarJ;
    private javax.swing.JButton btnGuargarJ;
    private javax.swing.JButton btnIniciarJ;
    private javax.swing.JButton btnRehacerJ;
    private javax.swing.JButton btnTerminarJ;
    private javax.swing.JButton btnTop10;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel lCronometer;
    private javax.swing.JLabel labH;
    private javax.swing.JLabel labM;
    private javax.swing.JLabel labS;
    private javax.swing.JLabel levelLabel;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
