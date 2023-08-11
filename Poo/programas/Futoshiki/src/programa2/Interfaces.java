/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package programa2;

import futoshiki.Casilla;
import futoshiki.Futoshiki;
import java.awt.Color;
import java.awt.Component;
import java.awt.SystemColor;
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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author Natalia S.R.S
 */
public class Interfaces extends javax.swing.JFrame {
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
    /**
     * Creates new form Interface
     */
    public Interfaces() {
        initComponents();
        nombrarTextField();
        setLocationRelativeTo(null);
        this.getContentPane().setBackground(Color.lightGray);
        pila= new  ListaDoblementeEnlazada();
        pila2= new  ListaDoblementeEnlazada();
        orden = new ArrayList<Integer>();
    }   
    public void setGame(ArrayList<Futoshiki> pFutoshikis){
        futoshikis=pFutoshikis;
         int max = futoshikis.size();
        int min = 0;
        int range = max - min;
        while(orden.size() <futoshikis.size()){
            Random rand = new Random();
            int rand1 = rand.nextInt(max);
            if(!orden.contains(rand1)){
                 orden.add(rand1);
            }
        }
        futoshiki=futoshikis.get(orden.get(0));
        llenarPlantilla();
    }
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
            llenarPlantilla();
            modificarColores();
        }
        
    
    }
    public void guardar(){
        Partida partida= new Partida(futoshiki,pila,pila2,orden,posicion);
        
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
                                }
                                else if(condicion.equals("y")){
                                    campo.setText("v");
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
                            }
                        }
                    }
                }
            }
            
        }
    
    }
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
                                if(fila<=4&&fila>0){
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

                                if(columna<=4&&columna>0){
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
            JOptionPane.showMessageDialog(this,"¡Felicidades, has ganado!","Victoria",JOptionPane.INFORMATION_MESSAGE);
        }
    }  
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
                            if(fila<=4&&fila>0){
                                if (!futoshiki.confirmarCondicion(fila-1, columna)){
                                    condiciones = true;
                                    cierto=true;
                                    System.out.println("2");
                                }
                            }
                            
                            if(columna<=4&&columna>0){
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
    private void cambiarBorde(){
        Border personalizado; 
        LineBorder linear = new LineBorder(SystemColor.textHighlight);
        Border basico = UIManager.getBorder("TextField.border");
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
    private void nombrarTextField(){
        t00.setName("t00");
        t01.setName("t01");
        t02.setName("t02");
        t03.setName("t03");
        t04.setName("t04");
        c00a.setName("c00a");
        c01a.setName("c01a");
        c02a.setName("c02a");
        c03a.setName("c03a");
        c00b.setName("c00b");
        c01b.setName("c01b");
        c02b.setName("c02b");
        c03b.setName("c03b");
        c04b.setName("c04b");
        t10.setName("t10");
        t11.setName("t11");
        t12.setName("t12");
        t13.setName("t13");
        t14.setName("t14");
        c10a.setName("c10a");
        c11a.setName("c11a");
        c12a.setName("c12a");
        c13a.setName("c13a");
        c10b.setName("c10b");
        c11b.setName("c11b");
        c12b.setName("c12b");
        c13b.setName("c13b");
        c14b.setName("c14b");
        t20.setName("t20");
        t21.setName("t21");
        t22.setName("t22");
        t23.setName("t23");
        t24.setName("t24");
        c20a.setName("c20a");
        c21a.setName("c21a");
        c22a.setName("c22a");
        c23a.setName("c23a");
        c20b.setName("c20b");
        c21b.setName("c21b");
        c22b.setName("c22b");
        c23b.setName("c23b");
        c24b.setName("c24b");
        t30.setName("t30");
        t31.setName("t31");
        t32.setName("t32");
        t33.setName("t33");
        t34.setName("t34");
        c30a.setName("c30a");
        c31a.setName("c31a");
        c32a.setName("c32a");
        c33a.setName("c33a");
        c30b.setName("c30b");
        c31b.setName("c31b");
        c32b.setName("c32b");
        c33b.setName("c33b");
        c34b.setName("c34b");
        t40.setName("t40");
        t41.setName("t41");
        t42.setName("t42");
        t43.setName("t43");
        t44.setName("t44");
        c40a.setName("c40a");
        c41a.setName("c41a");
        c42a.setName("c42a");
        c43a.setName("c43a");
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
        t00 = new javax.swing.JTextField();
        t02 = new javax.swing.JTextField();
        t01 = new javax.swing.JTextField();
        t03 = new javax.swing.JTextField();
        t04 = new javax.swing.JTextField();
        t14 = new javax.swing.JTextField();
        t12 = new javax.swing.JTextField();
        t10 = new javax.swing.JTextField();
        t11 = new javax.swing.JTextField();
        t13 = new javax.swing.JTextField();
        t24 = new javax.swing.JTextField();
        t22 = new javax.swing.JTextField();
        t20 = new javax.swing.JTextField();
        t21 = new javax.swing.JTextField();
        t23 = new javax.swing.JTextField();
        t34 = new javax.swing.JTextField();
        t32 = new javax.swing.JTextField();
        t30 = new javax.swing.JTextField();
        t31 = new javax.swing.JTextField();
        t33 = new javax.swing.JTextField();
        t44 = new javax.swing.JTextField();
        t42 = new javax.swing.JTextField();
        t40 = new javax.swing.JTextField();
        t41 = new javax.swing.JTextField();
        t43 = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        btn2 = new javax.swing.JButton();
        btn1 = new javax.swing.JButton();
        btn3 = new javax.swing.JButton();
        btn5 = new javax.swing.JButton();
        btn4 = new javax.swing.JButton();
        btnBorrarJda = new javax.swing.JButton();
        btnIniciarJ = new javax.swing.JButton();
        btnRehacerJ = new javax.swing.JButton();
        btnTop10 = new javax.swing.JButton();
        btnTerminarJ = new javax.swing.JButton();
        btnBorrarJ = new javax.swing.JButton();
        btnGuargarJ = new javax.swing.JButton();
        btnCargarJ = new javax.swing.JButton();
        c00a = new javax.swing.JTextField();
        c01a = new javax.swing.JTextField();
        c02a = new javax.swing.JTextField();
        c03a = new javax.swing.JTextField();
        c10a = new javax.swing.JTextField();
        c11a = new javax.swing.JTextField();
        c21a = new javax.swing.JTextField();
        c31a = new javax.swing.JTextField();
        c12a = new javax.swing.JTextField();
        c22a = new javax.swing.JTextField();
        c13a = new javax.swing.JTextField();
        c23a = new javax.swing.JTextField();
        c20a = new javax.swing.JTextField();
        c30a = new javax.swing.JTextField();
        c32a = new javax.swing.JTextField();
        c40a = new javax.swing.JTextField();
        c41a = new javax.swing.JTextField();
        c33a = new javax.swing.JTextField();
        c43a = new javax.swing.JTextField();
        c42a = new javax.swing.JTextField();
        c10b = new javax.swing.JTextField();
        c20b = new javax.swing.JTextField();
        c11b = new javax.swing.JTextField();
        c21b = new javax.swing.JTextField();
        c12b = new javax.swing.JTextField();
        c22b = new javax.swing.JTextField();
        c13b = new javax.swing.JTextField();
        c14b = new javax.swing.JTextField();
        c23b = new javax.swing.JTextField();
        c24b = new javax.swing.JTextField();
        c34b = new javax.swing.JTextField();
        c33b = new javax.swing.JTextField();
        c32b = new javax.swing.JTextField();
        c31b = new javax.swing.JTextField();
        c30b = new javax.swing.JTextField();
        c00b = new javax.swing.JTextField();
        c01b = new javax.swing.JTextField();
        c02b = new javax.swing.JTextField();
        c03b = new javax.swing.JTextField();
        c04b = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(204, 0, 204));

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

        t00.setEditable(false);
        t00.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        t00.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t00.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t00MouseClicked(evt);
            }
        });
        t00.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t00ActionPerformed(evt);
            }
        });

        t02.setEditable(false);
        t02.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        t02.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t02.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t02MouseClicked(evt);
            }
        });
        t02.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t02ActionPerformed(evt);
            }
        });

        t01.setEditable(false);
        t01.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        t01.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t01.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t01MouseClicked(evt);
            }
        });
        t01.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t01ActionPerformed(evt);
            }
        });

        t03.setEditable(false);
        t03.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        t03.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t03.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t03MouseClicked(evt);
            }
        });
        t03.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t03ActionPerformed(evt);
            }
        });

        t04.setEditable(false);
        t04.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        t04.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t04.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t04MouseClicked(evt);
            }
        });
        t04.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t04ActionPerformed(evt);
            }
        });

        t14.setEditable(false);
        t14.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        t14.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t14MouseClicked(evt);
            }
        });
        t14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t14ActionPerformed(evt);
            }
        });

        t12.setEditable(false);
        t12.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        t12.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t12MouseClicked(evt);
            }
        });
        t12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t12ActionPerformed(evt);
            }
        });

        t10.setEditable(false);
        t10.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        t10.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t10MouseClicked(evt);
            }
        });
        t10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t10ActionPerformed(evt);
            }
        });

        t11.setEditable(false);
        t11.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        t11.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t11MouseClicked(evt);
            }
        });
        t11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t11ActionPerformed(evt);
            }
        });

        t13.setEditable(false);
        t13.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        t13.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t13MouseClicked(evt);
            }
        });
        t13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t13ActionPerformed(evt);
            }
        });

        t24.setEditable(false);
        t24.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        t24.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t24.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t24MouseClicked(evt);
            }
        });
        t24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t24ActionPerformed(evt);
            }
        });

        t22.setEditable(false);
        t22.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        t22.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t22.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t22MouseClicked(evt);
            }
        });
        t22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t22ActionPerformed(evt);
            }
        });

        t20.setEditable(false);
        t20.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        t20.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t20MouseClicked(evt);
            }
        });
        t20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t20ActionPerformed(evt);
            }
        });

        t21.setEditable(false);
        t21.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        t21.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t21.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t21MouseClicked(evt);
            }
        });
        t21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t21ActionPerformed(evt);
            }
        });

        t23.setEditable(false);
        t23.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        t23.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t23.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t23MouseClicked(evt);
            }
        });
        t23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t23ActionPerformed(evt);
            }
        });

        t34.setEditable(false);
        t34.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        t34.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t34.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t34MouseClicked(evt);
            }
        });
        t34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t34ActionPerformed(evt);
            }
        });

        t32.setEditable(false);
        t32.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        t32.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t32.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t32MouseClicked(evt);
            }
        });
        t32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t32ActionPerformed(evt);
            }
        });

        t30.setEditable(false);
        t30.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        t30.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t30.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t30MouseClicked(evt);
            }
        });
        t30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t30ActionPerformed(evt);
            }
        });

        t31.setEditable(false);
        t31.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        t31.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t31.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t31MouseClicked(evt);
            }
        });
        t31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t31ActionPerformed(evt);
            }
        });

        t33.setEditable(false);
        t33.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        t33.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t33.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t33MouseClicked(evt);
            }
        });
        t33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t33ActionPerformed(evt);
            }
        });

        t44.setEditable(false);
        t44.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        t44.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t44.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t44MouseClicked(evt);
            }
        });
        t44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t44ActionPerformed(evt);
            }
        });

        t42.setEditable(false);
        t42.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        t42.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t42.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t42MouseClicked(evt);
            }
        });
        t42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t42ActionPerformed(evt);
            }
        });

        t40.setEditable(false);
        t40.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        t40.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t40.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t40MouseClicked(evt);
            }
        });
        t40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t40ActionPerformed(evt);
            }
        });

        t41.setEditable(false);
        t41.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        t41.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t41.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t41MouseClicked(evt);
            }
        });
        t41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t41ActionPerformed(evt);
            }
        });

        t43.setEditable(false);
        t43.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        t43.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        t43.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                t43MouseClicked(evt);
            }
        });
        t43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                t43ActionPerformed(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Rockwell", 3, 18)); // NOI18N
        jLabel4.setText("Nivel Fácil");

        btn2.setBackground(new java.awt.Color(204, 255, 255));
        btn2.setText("2");
        btn2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn2ActionPerformed(evt);
            }
        });

        btn1.setBackground(new java.awt.Color(204, 255, 255));
        btn1.setText("1");
        btn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn1ActionPerformed(evt);
            }
        });

        btn3.setBackground(new java.awt.Color(204, 255, 255));
        btn3.setText("3");
        btn3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn3ActionPerformed(evt);
            }
        });

        btn5.setBackground(new java.awt.Color(204, 255, 255));
        btn5.setText("5");
        btn5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn5ActionPerformed(evt);
            }
        });

        btn4.setBackground(new java.awt.Color(204, 255, 255));
        btn4.setText("4");
        btn4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn4ActionPerformed(evt);
            }
        });

        btnBorrarJda.setText("<html><CENTER>BORRAR JUEGO<html>");
        btnBorrarJda.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        btnBorrarJda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarJdaActionPerformed(evt);
            }
        });

        btnIniciarJ.setText("<html><CENTER>INICIAR JUEGO<html>");
        btnIniciarJ.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        btnIniciarJ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnIniciarJActionPerformed(evt);
            }
        });

        btnRehacerJ.setText("<html><CENTER>REHACER JUGADA<html>");
        btnRehacerJ.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        btnRehacerJ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRehacerJActionPerformed(evt);
            }
        });

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

        btnBorrarJ.setText("<html><CENTER>BORRAR JUGADA<html>");
        btnBorrarJ.setHorizontalTextPosition(javax.swing.SwingConstants.LEADING);
        btnBorrarJ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarJActionPerformed(evt);
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

        c00a.setEditable(false);
        c00a.setBackground(new java.awt.Color(198, 189, 189));
        c00a.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        c00a.setText("<");
        c00a.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        c00a.setCaretColor(new java.awt.Color(242, 242, 242));
        c00a.setDisabledTextColor(new java.awt.Color(242, 242, 242));
        c00a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c00aActionPerformed(evt);
            }
        });

        c01a.setEditable(false);
        c01a.setBackground(new java.awt.Color(198, 189, 189));
        c01a.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        c01a.setText("<");
        c01a.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        c01a.setCaretColor(new java.awt.Color(242, 242, 242));
        c01a.setDisabledTextColor(new java.awt.Color(242, 242, 242));
        c01a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c01aActionPerformed(evt);
            }
        });

        c02a.setEditable(false);
        c02a.setBackground(new java.awt.Color(198, 189, 189));
        c02a.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        c02a.setText("<");
        c02a.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        c02a.setCaretColor(new java.awt.Color(242, 242, 242));
        c02a.setDisabledTextColor(new java.awt.Color(242, 242, 242));
        c02a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c02aActionPerformed(evt);
            }
        });

        c03a.setEditable(false);
        c03a.setBackground(new java.awt.Color(198, 189, 189));
        c03a.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        c03a.setText("<");
        c03a.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        c03a.setCaretColor(new java.awt.Color(242, 242, 242));
        c03a.setDisabledTextColor(new java.awt.Color(242, 242, 242));
        c03a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c03aActionPerformed(evt);
            }
        });

        c10a.setEditable(false);
        c10a.setBackground(new java.awt.Color(198, 189, 189));
        c10a.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        c10a.setText("<");
        c10a.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        c10a.setCaretColor(new java.awt.Color(242, 242, 242));
        c10a.setDisabledTextColor(new java.awt.Color(242, 242, 242));
        c10a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c10aActionPerformed(evt);
            }
        });

        c11a.setEditable(false);
        c11a.setBackground(new java.awt.Color(198, 189, 189));
        c11a.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        c11a.setText("<");
        c11a.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        c11a.setCaretColor(new java.awt.Color(242, 242, 242));
        c11a.setDisabledTextColor(new java.awt.Color(242, 242, 242));
        c11a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c11aActionPerformed(evt);
            }
        });

        c21a.setEditable(false);
        c21a.setBackground(new java.awt.Color(198, 189, 189));
        c21a.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        c21a.setText("<");
        c21a.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        c21a.setCaretColor(new java.awt.Color(242, 242, 242));
        c21a.setDisabledTextColor(new java.awt.Color(242, 242, 242));
        c21a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c21aActionPerformed(evt);
            }
        });

        c31a.setEditable(false);
        c31a.setBackground(new java.awt.Color(198, 189, 189));
        c31a.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        c31a.setText("<");
        c31a.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        c31a.setCaretColor(new java.awt.Color(242, 242, 242));
        c31a.setDisabledTextColor(new java.awt.Color(242, 242, 242));
        c31a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c31aActionPerformed(evt);
            }
        });

        c12a.setEditable(false);
        c12a.setBackground(new java.awt.Color(198, 189, 189));
        c12a.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        c12a.setText("<");
        c12a.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        c12a.setCaretColor(new java.awt.Color(242, 242, 242));
        c12a.setDisabledTextColor(new java.awt.Color(242, 242, 242));
        c12a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c12aActionPerformed(evt);
            }
        });

        c22a.setEditable(false);
        c22a.setBackground(new java.awt.Color(198, 189, 189));
        c22a.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        c22a.setText("<");
        c22a.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        c22a.setCaretColor(new java.awt.Color(242, 242, 242));
        c22a.setDisabledTextColor(new java.awt.Color(242, 242, 242));
        c22a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c22aActionPerformed(evt);
            }
        });

        c13a.setEditable(false);
        c13a.setBackground(new java.awt.Color(198, 189, 189));
        c13a.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        c13a.setText("<");
        c13a.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        c13a.setCaretColor(new java.awt.Color(242, 242, 242));
        c13a.setDisabledTextColor(new java.awt.Color(242, 242, 242));
        c13a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c13aActionPerformed(evt);
            }
        });

        c23a.setEditable(false);
        c23a.setBackground(new java.awt.Color(198, 189, 189));
        c23a.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        c23a.setText("<");
        c23a.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        c23a.setCaretColor(new java.awt.Color(242, 242, 242));
        c23a.setDisabledTextColor(new java.awt.Color(242, 242, 242));
        c23a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c23aActionPerformed(evt);
            }
        });

        c20a.setEditable(false);
        c20a.setBackground(new java.awt.Color(198, 189, 189));
        c20a.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        c20a.setText("<");
        c20a.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        c20a.setCaretColor(new java.awt.Color(242, 242, 242));
        c20a.setDisabledTextColor(new java.awt.Color(242, 242, 242));
        c20a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c20aActionPerformed(evt);
            }
        });

        c30a.setEditable(false);
        c30a.setBackground(new java.awt.Color(198, 189, 189));
        c30a.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        c30a.setText("<");
        c30a.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        c30a.setCaretColor(new java.awt.Color(242, 242, 242));
        c30a.setDisabledTextColor(new java.awt.Color(242, 242, 242));
        c30a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c30aActionPerformed(evt);
            }
        });

        c32a.setEditable(false);
        c32a.setBackground(new java.awt.Color(198, 189, 189));
        c32a.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        c32a.setText("<");
        c32a.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        c32a.setCaretColor(new java.awt.Color(242, 242, 242));
        c32a.setDisabledTextColor(new java.awt.Color(242, 242, 242));
        c32a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c32aActionPerformed(evt);
            }
        });

        c40a.setEditable(false);
        c40a.setBackground(new java.awt.Color(198, 189, 189));
        c40a.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        c40a.setText("<");
        c40a.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        c40a.setCaretColor(new java.awt.Color(242, 242, 242));
        c40a.setDisabledTextColor(new java.awt.Color(242, 242, 242));
        c40a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c40aActionPerformed(evt);
            }
        });

        c41a.setEditable(false);
        c41a.setBackground(new java.awt.Color(198, 189, 189));
        c41a.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        c41a.setText("<");
        c41a.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        c41a.setCaretColor(new java.awt.Color(242, 242, 242));
        c41a.setDisabledTextColor(new java.awt.Color(242, 242, 242));
        c41a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c41aActionPerformed(evt);
            }
        });

        c33a.setEditable(false);
        c33a.setBackground(new java.awt.Color(198, 189, 189));
        c33a.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        c33a.setText("<");
        c33a.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        c33a.setCaretColor(new java.awt.Color(242, 242, 242));
        c33a.setDisabledTextColor(new java.awt.Color(242, 242, 242));
        c33a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c33aActionPerformed(evt);
            }
        });

        c43a.setEditable(false);
        c43a.setBackground(new java.awt.Color(198, 189, 189));
        c43a.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        c43a.setText("<");
        c43a.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        c43a.setCaretColor(new java.awt.Color(242, 242, 242));
        c43a.setDisabledTextColor(new java.awt.Color(242, 242, 242));
        c43a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c43aActionPerformed(evt);
            }
        });

        c42a.setEditable(false);
        c42a.setBackground(new java.awt.Color(198, 189, 189));
        c42a.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        c42a.setText("<");
        c42a.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        c42a.setCaretColor(new java.awt.Color(242, 242, 242));
        c42a.setDisabledTextColor(new java.awt.Color(242, 242, 242));
        c42a.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c42aActionPerformed(evt);
            }
        });

        c10b.setEditable(false);
        c10b.setBackground(new java.awt.Color(198, 189, 189));
        c10b.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        c10b.setText("^");
        c10b.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        c10b.setCaretColor(new java.awt.Color(242, 242, 242));
        c10b.setDisabledTextColor(new java.awt.Color(242, 242, 242));
        c10b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c10bActionPerformed(evt);
            }
        });

        c20b.setEditable(false);
        c20b.setBackground(new java.awt.Color(198, 189, 189));
        c20b.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        c20b.setText("^");
        c20b.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        c20b.setCaretColor(new java.awt.Color(242, 242, 242));
        c20b.setDisabledTextColor(new java.awt.Color(242, 242, 242));
        c20b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c20bActionPerformed(evt);
            }
        });

        c11b.setEditable(false);
        c11b.setBackground(new java.awt.Color(198, 189, 189));
        c11b.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        c11b.setText("^");
        c11b.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        c11b.setCaretColor(new java.awt.Color(242, 242, 242));
        c11b.setDisabledTextColor(new java.awt.Color(242, 242, 242));
        c11b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c11bActionPerformed(evt);
            }
        });

        c21b.setEditable(false);
        c21b.setBackground(new java.awt.Color(198, 189, 189));
        c21b.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        c21b.setText("^");
        c21b.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        c21b.setCaretColor(new java.awt.Color(242, 242, 242));
        c21b.setDisabledTextColor(new java.awt.Color(242, 242, 242));
        c21b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c21bActionPerformed(evt);
            }
        });

        c12b.setEditable(false);
        c12b.setBackground(new java.awt.Color(198, 189, 189));
        c12b.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        c12b.setText("^");
        c12b.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        c12b.setCaretColor(new java.awt.Color(242, 242, 242));
        c12b.setDisabledTextColor(new java.awt.Color(242, 242, 242));
        c12b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c12bActionPerformed(evt);
            }
        });

        c22b.setEditable(false);
        c22b.setBackground(new java.awt.Color(198, 189, 189));
        c22b.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        c22b.setText("^");
        c22b.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        c22b.setCaretColor(new java.awt.Color(242, 242, 242));
        c22b.setDisabledTextColor(new java.awt.Color(242, 242, 242));
        c22b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c22bActionPerformed(evt);
            }
        });

        c13b.setEditable(false);
        c13b.setBackground(new java.awt.Color(198, 189, 189));
        c13b.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        c13b.setText("^");
        c13b.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        c13b.setCaretColor(new java.awt.Color(242, 242, 242));
        c13b.setDisabledTextColor(new java.awt.Color(242, 242, 242));
        c13b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c13bActionPerformed(evt);
            }
        });

        c14b.setEditable(false);
        c14b.setBackground(new java.awt.Color(198, 189, 189));
        c14b.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        c14b.setText("^");
        c14b.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        c14b.setCaretColor(new java.awt.Color(242, 242, 242));
        c14b.setDisabledTextColor(new java.awt.Color(242, 242, 242));
        c14b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c14bActionPerformed(evt);
            }
        });

        c23b.setEditable(false);
        c23b.setBackground(new java.awt.Color(198, 189, 189));
        c23b.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        c23b.setText("^");
        c23b.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        c23b.setCaretColor(new java.awt.Color(242, 242, 242));
        c23b.setDisabledTextColor(new java.awt.Color(242, 242, 242));
        c23b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c23bActionPerformed(evt);
            }
        });

        c24b.setEditable(false);
        c24b.setBackground(new java.awt.Color(198, 189, 189));
        c24b.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        c24b.setText("^");
        c24b.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        c24b.setCaretColor(new java.awt.Color(242, 242, 242));
        c24b.setDisabledTextColor(new java.awt.Color(242, 242, 242));
        c24b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c24bActionPerformed(evt);
            }
        });

        c34b.setEditable(false);
        c34b.setBackground(new java.awt.Color(198, 189, 189));
        c34b.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        c34b.setText("^");
        c34b.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        c34b.setCaretColor(new java.awt.Color(242, 242, 242));
        c34b.setDisabledTextColor(new java.awt.Color(242, 242, 242));
        c34b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c34bActionPerformed(evt);
            }
        });

        c33b.setEditable(false);
        c33b.setBackground(new java.awt.Color(198, 189, 189));
        c33b.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        c33b.setText("^");
        c33b.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        c33b.setCaretColor(new java.awt.Color(242, 242, 242));
        c33b.setDisabledTextColor(new java.awt.Color(242, 242, 242));
        c33b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c33bActionPerformed(evt);
            }
        });

        c32b.setEditable(false);
        c32b.setBackground(new java.awt.Color(198, 189, 189));
        c32b.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        c32b.setText("^");
        c32b.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        c32b.setCaretColor(new java.awt.Color(242, 242, 242));
        c32b.setDisabledTextColor(new java.awt.Color(242, 242, 242));
        c32b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c32bActionPerformed(evt);
            }
        });

        c31b.setEditable(false);
        c31b.setBackground(new java.awt.Color(198, 189, 189));
        c31b.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        c31b.setText("^");
        c31b.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        c31b.setCaretColor(new java.awt.Color(242, 242, 242));
        c31b.setDisabledTextColor(new java.awt.Color(242, 242, 242));
        c31b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c31bActionPerformed(evt);
            }
        });

        c30b.setEditable(false);
        c30b.setBackground(new java.awt.Color(198, 189, 189));
        c30b.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        c30b.setText("^");
        c30b.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        c30b.setCaretColor(new java.awt.Color(242, 242, 242));
        c30b.setDisabledTextColor(new java.awt.Color(242, 242, 242));
        c30b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c30bActionPerformed(evt);
            }
        });

        c00b.setEditable(false);
        c00b.setBackground(new java.awt.Color(198, 189, 189));
        c00b.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        c00b.setText("^");
        c00b.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        c00b.setCaretColor(new java.awt.Color(242, 242, 242));
        c00b.setDisabledTextColor(new java.awt.Color(242, 242, 242));
        c00b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c00bActionPerformed(evt);
            }
        });

        c01b.setEditable(false);
        c01b.setBackground(new java.awt.Color(198, 189, 189));
        c01b.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        c01b.setText("^");
        c01b.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        c01b.setCaretColor(new java.awt.Color(242, 242, 242));
        c01b.setDisabledTextColor(new java.awt.Color(242, 242, 242));
        c01b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c01bActionPerformed(evt);
            }
        });

        c02b.setEditable(false);
        c02b.setBackground(new java.awt.Color(198, 189, 189));
        c02b.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        c02b.setText("^");
        c02b.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        c02b.setCaretColor(new java.awt.Color(242, 242, 242));
        c02b.setDisabledTextColor(new java.awt.Color(242, 242, 242));
        c02b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c02bActionPerformed(evt);
            }
        });

        c03b.setEditable(false);
        c03b.setBackground(new java.awt.Color(198, 189, 189));
        c03b.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        c03b.setText("^");
        c03b.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        c03b.setCaretColor(new java.awt.Color(242, 242, 242));
        c03b.setDisabledTextColor(new java.awt.Color(242, 242, 242));
        c03b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c03bActionPerformed(evt);
            }
        });

        c04b.setEditable(false);
        c04b.setBackground(new java.awt.Color(198, 189, 189));
        c04b.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        c04b.setText("^");
        c04b.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        c04b.setCaretColor(new java.awt.Color(242, 242, 242));
        c04b.setDisabledTextColor(new java.awt.Color(242, 242, 242));
        c04b.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                c04bActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(157, 157, 157)
                        .addComponent(c00b, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(73, 73, 73)
                        .addComponent(c01b, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(76, 76, 76)
                        .addComponent(c02b, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addComponent(c03b, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(74, 74, 74)
                        .addComponent(c04b, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(29, 29, 29)
                                .addComponent(btnIniciarJ, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 389, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(t00, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(c00a, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(t01, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(c01a, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(t02, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(c02a, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(t03, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(c03a, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(5, 5, 5)
                                .addComponent(t04, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(t20, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(4, 4, 4)
                                                .addComponent(c20a, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(t21, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(t10, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(c10a, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(5, 5, 5)
                                                .addComponent(t11, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(5, 5, 5)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(c11a, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(c21a, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(23, 23, 23)
                                        .addComponent(c10b, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(73, 73, 73)
                                        .addComponent(c11b, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(t12, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(5, 5, 5)
                                        .addComponent(c12a, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(5, 5, 5)
                                        .addComponent(t13, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(5, 5, 5)
                                        .addComponent(c13a, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(5, 5, 5)
                                        .addComponent(t14, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(22, 22, 22)
                                                .addComponent(c12b, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(76, 76, 76)
                                                .addComponent(c13b, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addComponent(t22, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(5, 5, 5)
                                                .addComponent(c22a, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(5, 5, 5)
                                                .addComponent(t23, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(5, 5, 5)
                                                .addComponent(c23a, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(5, 5, 5)
                                                .addComponent(t24, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(27, 27, 27)
                                                .addComponent(c14b, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(t30, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(5, 5, 5)
                                        .addComponent(c30a, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(5, 5, 5)
                                        .addComponent(t31, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(25, 25, 25)
                                        .addComponent(c20b, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(72, 72, 72)
                                        .addComponent(c21b, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(52, 52, 52)
                                        .addComponent(c22b, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(78, 78, 78)
                                        .addComponent(c23b, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(70, 70, 70)
                                        .addComponent(c24b, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(5, 5, 5)
                                        .addComponent(c31a, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(5, 5, 5)
                                        .addComponent(t32, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(c32a, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(5, 5, 5)
                                        .addComponent(t33, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(5, 5, 5)
                                        .addComponent(c33a, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(5, 5, 5)
                                        .addComponent(t34, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnBorrarJda)
                                    .addComponent(btnTerminarJ))
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnRehacerJ)
                                        .addGap(158, 158, 158))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btnBorrarJ)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(btnGuargarJ, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(btnCargarJ, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(btnTop10, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createSequentialGroup()
                                    .addComponent(t40, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(5, 5, 5)
                                    .addComponent(c40a, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(5, 5, 5)
                                    .addComponent(t41, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(5, 5, 5)
                                    .addComponent(c41a, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(5, 5, 5)
                                    .addComponent(t42, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(5, 5, 5)
                                    .addComponent(c42a, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(5, 5, 5)
                                    .addComponent(t43, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(5, 5, 5)
                                    .addComponent(c43a, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGap(6, 6, 6)
                                    .addComponent(t44, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(139, 139, 139)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(301, 301, 301)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(c30b, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72)
                        .addComponent(c31b, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72)
                        .addComponent(c32b, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(79, 79, 79)
                        .addComponent(c33b, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(69, 69, 69)
                        .addComponent(c34b, javax.swing.GroupLayout.PREFERRED_SIZE, 16, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(170, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(t00, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t02, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t01, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t03, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(t04, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(c00a, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(c01a, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(c02a, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(c03a, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(5, 5, 5)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(c00b, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(c03b, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(c02b, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(c01b, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(c04b, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(t10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(t11, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(c10a, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(c11a, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(t12, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(t13, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(t14, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(c12a, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(c13a, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, 0)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(c10b, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(c11b, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(c12b, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(c14b, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(t20, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t21, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(c21a, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t22, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(c22a, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t23, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(c20a, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(c23a, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(t24, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(c13b, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(c20b, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(c22b, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(c23b, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(c24b, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(c21b, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(t30, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(t32, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(t31, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(t33, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(c30a, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(c31a, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(c32a, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(t34, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(c33a, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn2, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn3, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn4, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btn5, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnIniciarJ, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnBorrarJda, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnRehacerJ, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnTerminarJ, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(c33b, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(c31b, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(c32b, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(c34b, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(c30b, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, 0)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(t40, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(t42, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(t41, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(t43, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(t44, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(c40a, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(c41a, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(c43a, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(c42a, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(18, 18, 18)
                        .addComponent(btnTop10, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnGuargarJ)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnCargarJ))
                            .addComponent(btnBorrarJ, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(0, 44, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void t00ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t00ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t00ActionPerformed
    private void t02ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t02ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t02ActionPerformed
    private void t01ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t01ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t01ActionPerformed
    private void t03ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t03ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t03ActionPerformed
    private void t04ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t04ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t04ActionPerformed
    private void t14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t14ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t14ActionPerformed
    private void t12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t12ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t12ActionPerformed
    private void t10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t10ActionPerformed
    private void t11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t11ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t11ActionPerformed
    private void t13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t13ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t13ActionPerformed
    private void t24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t24ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t24ActionPerformed
    private void t22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t22ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t22ActionPerformed
    private void t20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t20ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t20ActionPerformed
    private void t21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t21ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t21ActionPerformed
    private void t23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t23ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t23ActionPerformed
    private void t34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t34ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t34ActionPerformed
    private void t32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t32ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t32ActionPerformed
    private void t30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t30ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t30ActionPerformed
    private void t31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t31ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t31ActionPerformed
    private void t33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t33ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t33ActionPerformed
    private void t44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t44ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t44ActionPerformed
    private void t42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t42ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t42ActionPerformed
    private void t40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t40ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t40ActionPerformed
    private void t41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t41ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t41ActionPerformed
    private void t43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_t43ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_t43ActionPerformed
    private void txtNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNombreActionPerformed
    private void btnBorrarJdaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarJdaActionPerformed
         Casilla [][] Aux=futoshiki.getArray();
        for (int i=0;i<5;i++){
            for (int j=0;j<5;j++){
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
    private void btn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn1ActionPerformed
        if(jugar){
            retrocede= false;
            avanza=false;
            modificarCasilla(1);
        }
    }//GEN-LAST:event_btn1ActionPerformed
    private void btnIniciarJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnIniciarJActionPerformed
        if(txtNombre.getText().equals("")){
            JOptionPane.showMessageDialog(this,"No se ha insertado un nombre","Error",JOptionPane.ERROR_MESSAGE);
        }
        else{
            if(ganado){
                ganado=false;
                posicion++;
                 Casilla [][] Aux=futoshiki.getArray();
                for (int i=0;i<5;i++){
                    for (int j=0;j<5;j++){
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
        }
    
    }//GEN-LAST:event_btnIniciarJActionPerformed
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
    private void btnTop10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTop10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTop10ActionPerformed
    private void btnTerminarJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTerminarJActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTerminarJActionPerformed
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
    private void c00aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c00aActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c00aActionPerformed
    private void c01aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c01aActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c01aActionPerformed
    private void c02aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c02aActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c02aActionPerformed
    private void c03aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c03aActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c03aActionPerformed
    private void c10aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c10aActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c10aActionPerformed
    private void c11aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c11aActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c11aActionPerformed
    private void c21aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c21aActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c21aActionPerformed
    private void c31aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c31aActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c31aActionPerformed
    private void c12aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c12aActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c12aActionPerformed
    private void c22aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c22aActionPerformed
       // TODO add your handling code here:
    }//GEN-LAST:event_c22aActionPerformed
    private void c13aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c13aActionPerformed
       // TODO add your handling code here:
    }//GEN-LAST:event_c13aActionPerformed
    private void c23aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c23aActionPerformed
      // TODO add your handling code here:
    }//GEN-LAST:event_c23aActionPerformed
    private void c20aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c20aActionPerformed
       // TODO add your handling code here:
    }//GEN-LAST:event_c20aActionPerformed
    private void c30aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c30aActionPerformed
       // TODO add your handling code here:
    }//GEN-LAST:event_c30aActionPerformed
    private void c32aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c32aActionPerformed
     // TODO add your handling code here:
    }//GEN-LAST:event_c32aActionPerformed
    private void c40aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c40aActionPerformed
       // TODO add your handling code here:
    }//GEN-LAST:event_c40aActionPerformed
    private void c41aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c41aActionPerformed
       // TODO add your handling code here:
    }//GEN-LAST:event_c41aActionPerformed
    private void c33aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c33aActionPerformed
       // TODO add your handling code here:
    }//GEN-LAST:event_c33aActionPerformed
    private void c43aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c43aActionPerformed
    }//GEN-LAST:event_c43aActionPerformed
    private void c42aActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c42aActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c42aActionPerformed
    private void c10bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c10bActionPerformed
       // TODO add your handling code here:
    }//GEN-LAST:event_c10bActionPerformed
    private void c20bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c20bActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c20bActionPerformed
    private void c11bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c11bActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c11bActionPerformed
    private void c21bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c21bActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c21bActionPerformed
    private void c12bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c12bActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c12bActionPerformed
    private void c22bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c22bActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c22bActionPerformed
    private void c13bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c13bActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c13bActionPerformed
    private void c14bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c14bActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c14bActionPerformed
    private void c23bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c23bActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c23bActionPerformed
    private void c24bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c24bActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c24bActionPerformed
    private void c34bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c34bActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c34bActionPerformed
    private void c33bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c33bActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c33bActionPerformed
    private void c32bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c32bActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c32bActionPerformed
    private void c31bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c31bActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c31bActionPerformed
    private void c30bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c30bActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c30bActionPerformed
    private void c00bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c00bActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c00bActionPerformed
    private void c01bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c01bActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c01bActionPerformed
    private void c02bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c02bActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c02bActionPerformed
    private void c03bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c03bActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c03bActionPerformed
    private void c04bActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_c04bActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_c04bActionPerformed
    private void t00MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t00MouseClicked
       seleccionado=t00.getName();
       cambiarBorde();
       
    }//GEN-LAST:event_t00MouseClicked
    private void t01MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t01MouseClicked
       seleccionado=t01.getName();
       cambiarBorde();
    }//GEN-LAST:event_t01MouseClicked
    private void t02MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t02MouseClicked
       seleccionado=t02.getName();
       cambiarBorde();
    }//GEN-LAST:event_t02MouseClicked
    private void t03MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t03MouseClicked
        seleccionado=t03.getName();
        cambiarBorde();
    }//GEN-LAST:event_t03MouseClicked
    private void t04MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t04MouseClicked
        seleccionado=t04.getName();
        cambiarBorde();
    }//GEN-LAST:event_t04MouseClicked
    private void t10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t10MouseClicked
        seleccionado=t10.getName();
        cambiarBorde();
    }//GEN-LAST:event_t10MouseClicked
    private void t11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t11MouseClicked
        seleccionado=t11.getName();
        cambiarBorde();
    }//GEN-LAST:event_t11MouseClicked
    private void t12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t12MouseClicked
       seleccionado=t12.getName();
       cambiarBorde();
    }//GEN-LAST:event_t12MouseClicked
    private void t13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t13MouseClicked
        seleccionado=t13.getName();
        cambiarBorde();
    }//GEN-LAST:event_t13MouseClicked
    private void t14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t14MouseClicked
        seleccionado=t14.getName();
        cambiarBorde();
    }//GEN-LAST:event_t14MouseClicked
    private void t20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t20MouseClicked
        seleccionado=t20.getName();
        cambiarBorde();
    }//GEN-LAST:event_t20MouseClicked
    private void t21MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t21MouseClicked
        seleccionado=t21.getName();
        cambiarBorde();
    }//GEN-LAST:event_t21MouseClicked
    private void t22MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t22MouseClicked
        seleccionado=t22.getName();
        cambiarBorde();
    }//GEN-LAST:event_t22MouseClicked
    private void t23MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t23MouseClicked
        seleccionado=t23.getName();
        cambiarBorde();
    }//GEN-LAST:event_t23MouseClicked
    private void t24MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t24MouseClicked
        seleccionado=t24.getName();
        cambiarBorde();
    }//GEN-LAST:event_t24MouseClicked
    private void t30MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t30MouseClicked
        seleccionado=t30.getName();
        cambiarBorde();
    }//GEN-LAST:event_t30MouseClicked
    private void t31MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t31MouseClicked
        seleccionado=t31.getName();
        cambiarBorde();
    }//GEN-LAST:event_t31MouseClicked
    private void t32MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t32MouseClicked
         seleccionado=t32.getName();
         cambiarBorde();
    }//GEN-LAST:event_t32MouseClicked
    private void t33MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t33MouseClicked
         seleccionado=t33.getName();
         cambiarBorde();
    }//GEN-LAST:event_t33MouseClicked
    private void t34MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t34MouseClicked
         seleccionado=t34.getName();
         cambiarBorde();
    }//GEN-LAST:event_t34MouseClicked
    private void t40MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t40MouseClicked
       seleccionado=t40.getName();
       cambiarBorde();
    }//GEN-LAST:event_t40MouseClicked
    private void t41MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t41MouseClicked
        seleccionado=t41.getName();
        cambiarBorde();
    }//GEN-LAST:event_t41MouseClicked
    private void t42MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t42MouseClicked
        seleccionado=t42.getName();
        cambiarBorde();
    }//GEN-LAST:event_t42MouseClicked
    private void t43MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t43MouseClicked
        seleccionado=t43.getName();
        cambiarBorde();
    }//GEN-LAST:event_t43MouseClicked
    private void t44MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_t44MouseClicked
        seleccionado=t44.getName();
        cambiarBorde();
    }//GEN-LAST:event_t44MouseClicked
    private void btn2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn2ActionPerformed
        if(jugar){
           retrocede= false;
           avanza=false;
           modificarCasilla(2);
        }
    }//GEN-LAST:event_btn2ActionPerformed
    private void btn3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn3ActionPerformed
        if(jugar){
            retrocede= false;
            avanza=false;
            modificarCasilla(3);
        }
    }//GEN-LAST:event_btn3ActionPerformed
    private void btn4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn4ActionPerformed
        if(jugar){
            retrocede= false;
            avanza=false;
            modificarCasilla(4);
        }
    }//GEN-LAST:event_btn4ActionPerformed
    private void btn5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn5ActionPerformed
        if(jugar){
            retrocede= false;
            avanza=false;
            modificarCasilla(5);
        }
    }//GEN-LAST:event_btn5ActionPerformed

    private void btnGuargarJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuargarJActionPerformed
        guardar();
    }//GEN-LAST:event_btnGuargarJActionPerformed

    private void btnCargarJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarJActionPerformed
        cargar();
    }//GEN-LAST:event_btnCargarJActionPerformed
    
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
            java.util.logging.Logger.getLogger(Interfaces.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaces.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaces.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaces.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaces().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn1;
    private javax.swing.JButton btn2;
    private javax.swing.JButton btn3;
    private javax.swing.JButton btn4;
    private javax.swing.JButton btn5;
    private javax.swing.JButton btnBorrarJ;
    private javax.swing.JButton btnBorrarJda;
    private javax.swing.JButton btnCargarJ;
    private javax.swing.JButton btnGuargarJ;
    private javax.swing.JButton btnIniciarJ;
    private javax.swing.JButton btnRehacerJ;
    private javax.swing.JButton btnTerminarJ;
    private javax.swing.JButton btnTop10;
    private javax.swing.JTextField c00a;
    private javax.swing.JTextField c00b;
    private javax.swing.JTextField c01a;
    private javax.swing.JTextField c01b;
    private javax.swing.JTextField c02a;
    private javax.swing.JTextField c02b;
    private javax.swing.JTextField c03a;
    private javax.swing.JTextField c03b;
    private javax.swing.JTextField c04b;
    private javax.swing.JTextField c10a;
    private javax.swing.JTextField c10b;
    private javax.swing.JTextField c11a;
    private javax.swing.JTextField c11b;
    private javax.swing.JTextField c12a;
    private javax.swing.JTextField c12b;
    private javax.swing.JTextField c13a;
    private javax.swing.JTextField c13b;
    private javax.swing.JTextField c14b;
    private javax.swing.JTextField c20a;
    private javax.swing.JTextField c20b;
    private javax.swing.JTextField c21a;
    private javax.swing.JTextField c21b;
    private javax.swing.JTextField c22a;
    private javax.swing.JTextField c22b;
    private javax.swing.JTextField c23a;
    private javax.swing.JTextField c23b;
    private javax.swing.JTextField c24b;
    private javax.swing.JTextField c30a;
    private javax.swing.JTextField c30b;
    private javax.swing.JTextField c31a;
    private javax.swing.JTextField c31b;
    private javax.swing.JTextField c32a;
    private javax.swing.JTextField c32b;
    private javax.swing.JTextField c33a;
    private javax.swing.JTextField c33b;
    private javax.swing.JTextField c34b;
    private javax.swing.JTextField c40a;
    private javax.swing.JTextField c41a;
    private javax.swing.JTextField c42a;
    private javax.swing.JTextField c43a;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JTextField t00;
    private javax.swing.JTextField t01;
    private javax.swing.JTextField t02;
    private javax.swing.JTextField t03;
    private javax.swing.JTextField t04;
    private javax.swing.JTextField t10;
    private javax.swing.JTextField t11;
    private javax.swing.JTextField t12;
    private javax.swing.JTextField t13;
    private javax.swing.JTextField t14;
    private javax.swing.JTextField t20;
    private javax.swing.JTextField t21;
    private javax.swing.JTextField t22;
    private javax.swing.JTextField t23;
    private javax.swing.JTextField t24;
    private javax.swing.JTextField t30;
    private javax.swing.JTextField t31;
    private javax.swing.JTextField t32;
    private javax.swing.JTextField t33;
    private javax.swing.JTextField t34;
    private javax.swing.JTextField t40;
    private javax.swing.JTextField t41;
    private javax.swing.JTextField t42;
    private javax.swing.JTextField t43;
    private javax.swing.JTextField t44;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
