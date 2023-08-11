/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

/**
 *
 * @author Usuario
 */
public class Model {

    private String seleccionado = "";
    private Futoshiki futoshiki;
    private ListaDoblementeEnlazada<String> pila;
    private ListaDoblementeEnlazada<String> pila2;
    private boolean avanza = false;
    private boolean retrocede = false;
    private boolean jugar = false;
    private ArrayList<Futoshiki> futoshikis;
    private ArrayList<Integer> orden;
    private int posicion = 0;
    private boolean ganado = false;
    private int aXa = 8;
    private Container panel;
    private JTextField txtNombre;
    private JLabel labH, labM, labS, lCronometer, levelLabel;
    private ArrayList<Tops> top;
    private ArrayList<Info> Mejores;
    private boolean aparece;
    private String nivel;
    private Configuracion confi;
    boolean multinivel = false;
    private Timer t;
    private int h, m, s, cs;
    private int hT = 0, mT = 0, sT = 0, csT = 0;
    private boolean izquierda = false;

    public Model(Container pPanel, JTextField field, JLabel label) {
        panel = pPanel;
        txtNombre = field;
        levelLabel = label;
        t = new Timer(10, acciones);
        pila = new ListaDoblementeEnlazada();
        pila2 = new ListaDoblementeEnlazada();
        orden = new ArrayList<Integer>();
        cargarT();
    }

    private void setTops() {
        for (int i = 0; i < top.size(); i++) {
            Tops c = top.get(i);
            if (c.getaXa() == aXa && (c.getNivel()).equals(nivel)) {
                top.get(i).setTop(Mejores);
                System.out.println("y");
            }
        }
    }

    private void buscarTop() {
        for (int i = 0; i < top.size(); i++) {
            Tops c = top.get(i);
            System.out.println(c.getTop().size());
            if (c.getaXa() == aXa && (c.getNivel()).equals(nivel)) {
                Mejores = top.get(i).getTop();
            }
        }
    }

    /**
     * Carga todos los parametros del juego
     *
     * @param pFutoshikis Partidas
     * @param pAXa tamaño de las partidas a jugar
     * @param pNivel Nivel del juego
     */
    public void setGame(ArrayList<Futoshiki> pFutoshikis, Configuracion pConfi) {
        futoshikis = pFutoshikis;
        confi = pConfi;
        izquierda = confi.getIzquierda();
        aXa = confi.getAXa();
        nivel = confi.getNivel();
        multinivel = confi.getMultiNivel();
        int max = futoshikis.size();
        buscarTop();
        ArrayList<Integer> Aux = new ArrayList<Integer>();
        if (multinivel) {
            boolean hecho = false;
            while (!hecho) {
                Random rand = new Random();
                int rand1 = rand.nextInt(max);
                if (!Aux.contains(rand1)) {
                    Aux.add(rand1);
                    if (orden.size() == 0) {
                        nivel = "facil";
                    }
                    if (orden.size() == 1) {
                        nivel = "intermedio";
                    }
                    if (orden.size() == 2) {
                        nivel = "dificil";
                    }
                    if (futoshikis.get(rand1).getaXa() == aXa) {
                        if (nivel.equals(futoshikis.get(rand1).getNivel())) {
                            orden.add(rand1);
                            System.out.println(nivel);
                            if (nivel.equals("dificil")) {
                                hecho = true;
                            }
                            Aux.clear();
                        }
                    }
                }
            }
            nivel = "facil";
        } else {
            while (Aux.size() < futoshikis.size()) {
                Random rand = new Random();
                int rand1 = rand.nextInt(max);
                if (!Aux.contains(rand1)) {
                    Aux.add(rand1);
                    if (futoshikis.get(rand1).getaXa() == aXa && nivel.equals(futoshikis.get(rand1).getNivel())) {
                        orden.add(rand1);
                    }
                }
            }
        }
        futoshiki = futoshikis.get(orden.get(0));
        llenarPlantilla();

    }

    public void setLabH(JLabel labH) {
        this.labH = labH;
    }

    public void setLabM(JLabel labM) {
        this.labM = labM;
    }

    public void setLabS(JLabel labS) {
        this.labS = labS;
    }

    public void setlCronometer(JLabel lCronometer) {
        this.lCronometer = lCronometer;
    }

    public ArrayList<Futoshiki> getFutoshikis() {
        return futoshikis;
    }

    public Configuracion getConfi() {
        return confi;
    }

    /**
     * Rehace la última jugada borrada
     */
    public void rehacerJugada() {
        avanza = true;
        if (pila2.peek() != null) {
            String accion = pila2.pop().dato.toString();
            for (Component c : panel.getComponents()) {
                JTextField Aux;
                if (c instanceof JTextField) {
                    Aux = (JTextField) c;
                    if (Aux.getName() != null) {
                        if (Aux.getName().equals("" + accion.charAt(0) + accion.charAt(1) + accion.charAt(2))) {
                            seleccionado = "" + accion.charAt(0) + accion.charAt(1) + accion.charAt(2);
                            System.out.println("seleccionado");
                            cambiarBorde();
                            int fila = Integer.parseInt("" + accion.charAt(1));
                            int columna = Integer.parseInt("" + accion.charAt(2));
                            if (("n").equals("" + accion.charAt(3))) {
                                futoshiki.getArray()[fila][columna].setValor(0);
                                Aux.setText("");
                                Aux.setBackground(Color.white);
                            } else {
                                modificarCasilla(Integer.parseInt("" + accion.charAt(3)), true);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Acciona los botones
     *
     * @param c JButton a accionar
     */
    public void modificar(JButton c) {
        if (jugar) {
            retrocede = false;
            avanza = false;
            modificarCasilla(Integer.parseInt(c.getText()), false);
        } else {
            JOptionPane.showMessageDialog(panel, "No ha iniciado una partida", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Muestra el reloj si lo considera necesario
     *
     * @param r Define si aparece el reloj
     */
    public void setReloj(boolean r) {
        aparece = r;
        lCronometer.setVisible(aparece);
        labH.setVisible(aparece);
        labM.setVisible(aparece);
        labS.setVisible(aparece);
    }

    /**
     * Selecciona el JTextField
     *
     * @param c JTextField a seleccionar
     */
    public void seleccionar(JTextField c) {
        seleccionado = c.getName();
        cambiarBorde();
    }

    public void sugerir() {
        if (jugar) {
            sugerencias();
        }
    }

    public void verTop() {
        if (jugar) {
            JOptionPane.showMessageDialog(panel, "Está jugando una partida", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            int contador = 1;
            String resultado = "Top10\n";
            for (Info p : Mejores) {
                resultado += "Lugar: " + contador + "\n";
                resultado += p.toString();
                contador++;
            }
            JOptionPane.showMessageDialog(panel, resultado);
        }
    }
    private ActionListener acciones = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ++cs;
            if (cs == 100) {
                cs = 0;
                ++s;
            }
            if (s == 60) {
                s = 0;
                ++m;
            }
            if (m == 60) {
                m = 0;
                ++h;
            }
            actualizarLabel();
        }
    };

    private void actualizarLabel() {
        String tiempo = (h <= 9 ? "0" : "") + h + ":" + (m <= 9 ? "0" : "") + m + ":" + (s <= 9 ? "0" : "") + s + ":" + (cs <= 9 ? "0" : "") + cs;
        lCronometer.setText(tiempo);
    }

    public void borrarJugada() {
        Casilla[][] Aux = futoshiki.getArray();
        for (int i = 0; i < aXa; i++) {
            for (int j = 0; j < aXa; j++) {
                if (!Aux[i][j].confirmarConstante()) {
                    Aux[i][j].setValor(0);
                }
            }
        }
        for (Component c : panel.getComponents()) {
            JTextField campo;
            if (c instanceof JTextField) {
                campo = (JTextField) c;

                String nombre = c.getName();
                if (nombre != null) {
                    int fila = Integer.parseInt(nombre.charAt(1) + "");
                    int columna = Integer.parseInt(nombre.charAt(2) + "");
                    Casilla Aux2 = futoshiki.getArray()[fila][columna];
                    if ((nombre.charAt(0) + "").equals("t")) {
                        campo.setBackground(Color.white);
                        if (Aux2.getValor() == 0) {
                            campo.setText("");
                        } else {
                            campo.setText(Aux2.getValor() + "");
                        }
                    }
                }
            }
        }
    }

    /**
     * Termina el juego
     *
     * @return Se retorna true si se desea terminar
     */
    public boolean terminar() {
        if (t.isRunning()) {
            t.stop();
        }
        int opcion = JOptionPane.showConfirmDialog(panel, "¿Está seguro?", "¿Salir?", JOptionPane.YES_NO_OPTION);
        if (opcion == 0) {
            Casilla[][] Aux = futoshiki.getArray();
            for (int i = 0; i < aXa; i++) {
                for (int j = 0; j < aXa; j++) {
                    if (!Aux[i][j].confirmarConstante()) {
                        Aux[i][j].setValor(0);
                    }
                }
            }
            for (Component c : panel.getComponents()) {
                JTextField campo;
                if (c instanceof JTextField) {
                    campo = (JTextField) c;
                    String nombre = c.getName();
                    if (nombre != null) {
                        int fila = Integer.parseInt(nombre.charAt(1) + "");
                        int columna = Integer.parseInt(nombre.charAt(2) + "");
                        Casilla Aux2 = futoshiki.getArray()[fila][columna];
                        if ((nombre.charAt(0) + "").equals("t")) {
                            campo.setBackground(Color.white);
                            if (Aux2.getValor() == 0) {
                                campo.setText("");
                            } else {
                                campo.setText(Aux2.getValor() + "");
                            }
                        }
                    }
                }
            }
            return true;
        } else {
            if (aparece == true) {
                t.start();
            }
            return false;
        }
    }

    /**
     * Recrea la matriz acorde a las propiedades cargadas
     */
    private void recrearJuego() {
        for (Component c : panel.getComponents()) {
            JTextField Aux;
            if (c instanceof JTextField) {
                Aux = (JTextField) c;
                if (Aux.getName() != null) {
                    c.setVisible(false);
                    panel.remove(Aux);
                }
            }
        }
        for (Component c : panel.getComponents()) {
            JButton Aux;
            if (c instanceof JButton) {
                Aux = (JButton) c;
                if (Aux.getName() != null) {
                    c.setVisible(false);
                    panel.remove(Aux);
                }
            }
        }
        if (nivel.equals("facil")) {
            levelLabel.setText("Nivel: Fácil");
        }
        if (nivel.equals("intermedio")) {
            levelLabel.setText("Nivel: Intermedio");
        }
        if (nivel.equals("dificil")) {
            levelLabel.setText("Nivel: Difícil");
        }
        ArrayList<JTextField> tField = new ArrayList<JTextField>();
        Font fuente = new Font("Dialog", Font.BOLD, 16);
        for (int i = 0; i < aXa; i++) {
            for (int j = 0; j < aXa; j++) {
                tField.add(new JTextField());
            }
        }
        String posi = "";
        int columna = 0, fila = 0, X = 185, Y = 98, H = 50, W = 60;
        int a = 90, b = 70;
        if (aXa > 6) {
            H = 30;
            b = 50;
        }
        for (JTextField c : tField) {
            posi = "t" + fila + columna;
            c.setName(posi);
            c.setBounds(X, Y, W, H);
            c.setVisible(true);
            c.setEditable(false);
            c.setFont(fuente);
            c.setHorizontalAlignment(JTextField.CENTER);
            c.setBorder(new LineBorder(SystemColor.black));
            c.setAlignmentX((float) 0.5);
            c.setAlignmentY((float) 0.5);
            c.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent evt) {
                    seleccionar(c);
                }
            });
            panel.add(c);
            if (columna == aXa - 1) {
                fila++;
                Y += b;
                columna = 0;
                X = 185;
            } else {
                columna++;
                X += a;
            }
        }
        tField.clear();
        columna = 0;
        fila = 0;
        Y = 100;
        X = 185;
        for (int i = 0; i < aXa; i++) {
            for (int j = 0; j < aXa - 1; j++) {
                tField.add(new JTextField());
            }
        }
        int h2 = 16;
        for (JTextField c : tField) {
            posi = "c" + fila + columna + "b";
            c.setName(posi);
            c.setBounds(X + (W / 2) - 8, Y + H - 3, h2, h2);
            c.setVisible(true);
            c.setEditable(false);
            c.setText("v");
            c.setFont(fuente);
            c.setHorizontalAlignment(JTextField.CENTER);
            c.setBorder(null);
            c.setAlignmentX((float) 0.5);
            c.setAlignmentY((float) 0.5);
            c.setOpaque(false);
            panel.add(c);
            if (columna == aXa - 1) {
                fila++;
                Y += b;
                columna = 0;
                X = 185;
            } else {
                columna++;
                X += a;
            }
        }
        tField.clear();
        columna = 0;
        fila = 0;
        Y = 115;
        X = 185;
        if (aXa > 6) {
            Y = 105;
        }
        for (int i = 0; i < aXa; i++) {
            for (int j = 0; j < aXa - 1; j++) {
                tField.add(new JTextField());
            }
        }
        for (JTextField c : tField) {
            posi = "c" + fila + columna + "a";
            c.setName(posi);
            c.setBounds(X + W + 10, Y, h2, h2);
            c.setEditable(false);
            c.setFont(fuente);
            c.setHorizontalAlignment(JTextField.CENTER);
            c.setBorder(null);
            c.setAlignmentX((float) 0.5);
            c.setAlignmentY((float) 0.5);
            c.setOpaque(false);
            c.setVisible(true);
            panel.add(c);
            if (columna == aXa - 2) {
                fila++;
                Y += b;
                columna = 0;
                X = 185;
            } else {
                columna++;
                X += a;
            }
        }
        ArrayList<JButton> botones = new ArrayList<JButton>();
        for (short i = 0; i < aXa; i++) {
            botones.add(new JButton("" + (i + 1)));
        }
        int xx = 0;
        a = 0;
        switch (aXa) {
            case 5:
                xx = 555;

                break;
            case 6:
                xx = 643;
                break;
            case 7:
                xx = 733;
                break;
            case 8:
                xx = 823;
                break;
            case 9:
                xx = 913;
                break;
        }
        if (izquierda) {
            xx = 0;
        }
        for (JButton c : botones) {
            c.setName(c.getText());
            c.setBounds(xx + 70, 90 + a, 68, 26);
            c.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent f) {
                    modificar(c);
                }
            });
            panel.add(c);
            c.setVisible(true);
            a += 40;
        }
    }

    /**
     * Carga el top 10
     */
    public void cargarT() {
        try {
            FileInputStream fi = new FileInputStream(new File("top.dat"));
            ObjectInputStream oi = new ObjectInputStream(fi);
            top = (ArrayList<Tops>) oi.readObject();
            oi.close();
            fi.close();
        } catch (FileNotFoundException e) {
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
    public void cargar() {
        Partida partida = null;
        try {
            FileInputStream fi = new FileInputStream(new File("futoshiki2022juegoactual.dat"));
            ObjectInputStream oi = new ObjectInputStream(fi);
            partida = (Partida) oi.readObject();
            oi.close();
            fi.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            System.out.println("Error initializing stream");
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (partida != null) {
            futoshiki = partida.getFutoshiki();
            pila = partida.getPila();
            pila2 = partida.getPila2();
            posicion = partida.getP();
            orden = partida.getOrden();
            izquierda = partida.getConfi().getIzquierda();
            int[] tiempo = partida.getT();
            cs = tiempo[0];
            s = tiempo[1];
            m = tiempo[2];
            h = tiempo[3];
            tiempo = partida.getTiempoT();
            csT = tiempo[0];
            sT = tiempo[1];
            mT = tiempo[2];
            hT = tiempo[3];
            if (cs != 0 || s != 0 || m != 0 || h != 0) {
                aparece = true;
            }
            if (aparece && !(t.isRunning())) {
                t.start();
            }
            nivel = futoshiki.getNivel();
            aXa = futoshiki.getaXa();
            txtNombre.setText(partida.getN());
            multinivel = partida.getConfi().getMultiNivel();
            recrearJuego();
            llenarPlantilla();
            modificarColores();
            jugar = true;
            ganado = false;
        }
        buscarTop();
    }

    /**
     * Guarda el top 10
     */
    private void guardarT() {
        setTops();
        try {
            FileOutputStream f = new FileOutputStream(new File("top.dat"));
            ObjectOutputStream o = new ObjectOutputStream(f);
            o.writeObject(top);
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
    public void guardar() {
        if (jugar) {
            int[] tiempo = new int[4];
            int[] tiempoT = new int[4];
            tiempo[0] = cs;
            tiempo[1] = s;
            tiempo[2] = m;
            tiempo[3] = h;
            tiempoT[0] = csT;
            tiempoT[1] = sT;
            tiempoT[2] = mT;
            tiempoT[3] = hT;
            Partida partida = new Partida(futoshiki, pila, pila2, orden, posicion, tiempo, txtNombre.getText(), confi, tiempoT);
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
        } else {
            JOptionPane.showMessageDialog(panel, "No se ha iniciado una partida", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    /**
     * Llena todos los campos de la interfaz para poder visualizar la partida
     * actual
     */
    public void llenarPlantilla() {
        for (Component c : panel.getComponents()) {
            JTextField campo;
            if (c instanceof JTextField) {
                campo = (JTextField) c;
                String nombre = c.getName();
                if (nombre != null) {
                    int fila = Integer.parseInt(nombre.charAt(1) + "");
                    int columna = Integer.parseInt(nombre.charAt(2) + "");
                    Casilla Aux = futoshiki.getArray()[fila][columna];
                    if ((nombre.charAt(0) + "").equals("t")) {
                        campo.setBackground(Color.white);
                        if (Aux.getValor() != 0) {
                            campo.setText(Aux.getValor() + "");
                        } else {
                            campo.setText("");
                        }
                    }

                    if ((nombre.charAt(0) + "").equals("c")) {
                        String condicion = "";
                        if ((nombre.charAt(3) + "").equals("b")) {
                            condicion = Aux.getCondicion();
                            if (condicion == null) {
                                campo.setText("");
                            } else {
                                if (condicion.equals("z")) {
                                    campo.setText("^");
                                    Font fuente = new Font("Dialog", Font.BOLD, 22);
                                    c.setFont(fuente);
                                } else if (condicion.equals("y")) {
                                    campo.setText("v");
                                    Font fuente = new Font("Dialog", Font.BOLD, 16);
                                    c.setFont(fuente);
                                } else {
                                    campo.setText("");
                                }
                            }

                        }
                        if ((nombre.charAt(3) + "").equals("a")) {
                            condicion = Aux.getCondicion();
                            if (condicion == null) {
                                campo.setText("");
                            } else {
                                if (condicion.equals("a")) {
                                    campo.setText(">");
                                } else if (condicion.equals("b")) {
                                    campo.setText("<");
                                } else {
                                    campo.setText("");
                                }
                                Font fuente = new Font("Dialog", Font.BOLD, 16);
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
    private void colorearVerde() {
        for (Component c : panel.getComponents()) {
            JTextField campo;
            JTextField Aux;
            if (c instanceof JTextField) {
                Aux = (JTextField) c;
                if (Aux.getName() != null) {
                    if ((Aux.getName().charAt(0) + "").equals("t")) {
                        campo = (JTextField) c;
                        campo.setBackground(Color.green);
                    }
                }
            }
        }
    }

    /**
     * Modifica los colores de las casillas acorde su valor y si incumple alguna
     * condición En caso de incumplir una condición, lo pinta de rojo, en caso
     * contrario de blanco Colorea todas las casillas de verde si no se
     * encuentra ninguna incongruencia y todas poseen un valor
     */
    public void modificarColores() {
        boolean ceros = false;
        boolean condiciones = false;
        for (Component c : panel.getComponents()) {
            JTextField campo;
            JTextField Aux;
            if (c instanceof JTextField) {
                Aux = (JTextField) c;
                if (Aux.getName() != null) {
                    if ((Aux.getName().charAt(0) + "").equals("t")) {
                        campo = (JTextField) c;
                        String provisional = campo.getName();
                        int fila = Integer.parseInt(provisional.charAt(1) + "");
                        int columna = Integer.parseInt(provisional.charAt(2) + "");
                        Casilla[][] Aux3 = futoshiki.getArray();
                        Casilla Aux2 = Aux3[fila][columna];
                        if (Aux2.getValor() == 0) {
                            ceros = true;
                        }
                        if (Aux2.confirmarConstante()) {

                        } else {
                            boolean cierto = false;
                            boolean condicion1 = futoshiki.confirmarCondicion(fila, columna);
                            boolean condicion2 = futoshiki.confirmarExistenciaX(fila, columna);
                            boolean condicion3 = futoshiki.confirmarExistenciaY(fila, columna);
                            if (condicion2) {

                                condiciones = true;
                                cierto = true;
                            }
                            if (condicion3) {
                                cierto = true;
                                condiciones = true;
                            }
                            if (!condicion1) {
                                condiciones = true;
                                cierto = true;
                            }
                            if (fila <= (aXa - 1) && fila > 0) {
                                String condi = futoshiki.getArray()[fila - 1][columna].getCondicion();
                                if (condi != null) {
                                    if (condi.equals("z") || condi.equals("y")) {
                                        if (!futoshiki.confirmarCondicion(fila - 1, columna)) {
                                            cierto = true;
                                            condiciones = true;
                                        }
                                    }
                                }
                            }
                            if (columna <= (aXa - 1) && columna > 0) {
                                String condi = futoshiki.getArray()[fila][columna - 1].getCondicion();
                                if (condi != null) {
                                    if (condi.equals("a") || condi.equals("b")) {
                                        if (!futoshiki.confirmarCondicion(fila, columna - 1)) {
                                            condiciones = true;
                                            cierto = true;
                                        }
                                    }
                                }
                            }
                            if (cierto) {
                                campo.setBackground(Color.RED);
                            } else {
                                campo.setBackground(Color.WHITE);
                            }
                        }
                    }
                }
            }
        }
        if (!ceros && !condiciones) {
            if (multinivel && !nivel.equals("dificil")) {
                Info tiempo = new Info(cs + csT, s + sT, m + mT, h + hT, txtNombre.getText());
                csT = (-cs);
                sT = (-s);
                mT = (-m);
                hT = (-h);
                agregarTop(tiempo);
                siguienteJuego();
                nivel = futoshiki.getNivel();
                if (nivel.equals("facil")) {
                    levelLabel.setText("Nivel: Fácil");
                }
                if (nivel.equals("intermedio")) {
                    levelLabel.setText("Nivel: Intermedio");
                }
                if (nivel.equals("dificil")) {
                    levelLabel.setText("Nivel: Difícil");
                }
                System.out.println("hola");
            } else {
                colorearVerde();
                System.out.println("y");
                ganado = true;
                jugar = false;
                t.stop();
                Info tiempo = new Info(cs + csT, s + sT, m + mT, h + hT, txtNombre.getText());
                agregarTop(tiempo);
                guardarT();
                JOptionPane.showMessageDialog(panel, "¡Felicidades, has ganado!", "Victoria", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    /**
     * Agrega un nuevo record al top, si no cumple con los requisitos
     *
     * @param a Valor a analizar para saber si corresponde al top 10
     */
    private void agregarTop(Info a) {
        ArrayList<Info> Aux = new ArrayList<Info>();
        if (Mejores.size() == 0) {
            Aux.add(a);
        } else {
            for (Info t : Mejores) {
                if (Aux.size() < 10) {
                    if (t.getMedicion() > a.getMedicion() && !Aux.contains(a)) {
                        Aux.add(a);
                    }
                    if (!Aux.contains(t)) {
                        Aux.add(t);
                    }
                }
                if (Aux.size() > 10) {
                    Aux.remove(10);
                }

            }
            if (Aux.size() < 10) {
                System.out.println(!Aux.contains(a));
                if (!Aux.contains(a)) {
                    Aux.add(a);
                }
            }
        }
        Mejores = Aux;
    }

    /**
     * Modifica la casilla según el valor que recibe esta función Cambia el
     * color de las casillas si el valor se repite o no cumple con la condición
     *
     * @param numero Numero a insertar en la casilla
     * @return Si retorna true significa que es posible realizr la operacion
     */
    public boolean modificarCasilla(int numero, boolean consideracion) {
        boolean hacer = true;
        for (Component c : panel.getComponents()) {
            JTextField campo;
            JTextField Aux;
            if (c instanceof JTextField) {
                Aux = (JTextField) c;
                if (Aux.getName() != null) {
                    if (Aux.getName().equals(seleccionado) || Aux.getBackground() == Color.RED) {
                        campo = (JTextField) c;
                        String provisional = campo.getName();
                        int fila = Integer.parseInt(provisional.charAt(1) + "");
                        int columna = Integer.parseInt(provisional.charAt(2) + "");
                        Casilla[][] Aux3 = futoshiki.getArray();
                        Casilla Aux2 = Aux3[fila][columna];
                        if (Aux2.confirmarConstante()) {
                            if (!consideracion) {
                                JOptionPane.showMessageDialog(panel, "Este valor es una constante", "Error", JOptionPane.ERROR_MESSAGE);
                            }
                            hacer = false;
                        } else {
                            if (provisional.equals(seleccionado)) {
                                Aux2.setValor(numero);
                                if (!retrocede || avanza) {
                                    String valor = campo.getText();
                                    if (!avanza) {
                                        pila2.clear();
                                    }
                                    if (valor.equals("")) {
                                        pila.push(seleccionado + "n");
                                    } else {
                                        pila.push(seleccionado + valor);
                                    }
                                }
                            }
                            campo.setText(futoshiki.getArray()[fila][columna].getValor() + "");
                            if (campo.getText().equals("0")) {
                                campo.setText("");
                            }
                            boolean cierto = false;
                            boolean condicion1 = futoshiki.confirmarCondicion(fila, columna);
                            boolean condicion2 = futoshiki.confirmarExistenciaX(fila, columna);
                            boolean condicion3 = futoshiki.confirmarExistenciaY(fila, columna);
                            if (condicion2) {
                                if (provisional.equals(seleccionado)) {
                                    if (!consideracion) {
                                        JOptionPane.showMessageDialog(panel, "Ya existe en la fila", "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                    cierto = true;
                                    hacer = false;
                                }
                            }
                            if (condicion3) {
                                if (provisional.equals(seleccionado)) {
                                    if (!consideracion) {
                                        JOptionPane.showMessageDialog(panel, "Ya existe en la columna", "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                    cierto = true;
                                    hacer = false;
                                }
                            }
                            boolean condiciones = false;
                            if (!condicion1) {
                                condiciones = true;
                                cierto = true;
                            }
                            if (fila <= (aXa - 1) && fila > 0) {
                                if (!futoshiki.confirmarCondicion(fila - 1, columna)) {
                                    condiciones = true;
                                    cierto = true;
                                }
                            }
                            if (columna <= (aXa - 1) && columna > 0) {
                                if (!futoshiki.confirmarCondicion(fila, columna - 1)) {
                                    condiciones = true;
                                    cierto = true;
                                }
                            }
                            if (condiciones) {
                                if (provisional.equals(seleccionado)) {
                                    if (!consideracion) {
                                        JOptionPane.showMessageDialog(panel, "No se da la condición", "Error", JOptionPane.ERROR_MESSAGE);
                                    }
                                    hacer = false;
                                }
                            }
                            if (cierto) {
                                campo.setBackground(Color.RED);

                            } else {
                                campo.setBackground(Color.WHITE);
                            }
                        }
                    }
                }
            }
        }
        modificarColores();
        return hacer;
    }

    /**
     * Borra el último movimiento rehalizado
     */
    public void borrarMovimiento() {
        retrocede = true;
        if (pila.peek() != null) {
            String accion = pila.pop().dato.toString();
            for (Component c : panel.getComponents()) {
                JTextField Aux;
                if (c instanceof JTextField) {
                    Aux = (JTextField) c;
                    if (Aux.getName() != null) {
                        if (Aux.getName().equals("" + accion.charAt(0) + accion.charAt(1) + accion.charAt(2))) {

                            seleccionado = "" + accion.charAt(0) + accion.charAt(1) + accion.charAt(2);
                            System.out.println("seleccionado");
                            cambiarBorde();
                            int fila = Integer.parseInt("" + accion.charAt(1));
                            int columna = Integer.parseInt("" + accion.charAt(2));

                            pila2.push("" + accion.charAt(0) + accion.charAt(1) + accion.charAt(2) + futoshiki.getArray()[fila][columna].getValor());
                            if (("n").equals("" + accion.charAt(3))) {
                                futoshiki.getArray()[fila][columna].setValor(0);
                                Aux.setText("");
                                Aux.setBackground(Color.white);
                            } else {
                                modificarCasilla(Integer.parseInt("" + accion.charAt(3)), true);
                            }
                        }
                    }
                }
            }
        }
    }
    
    /**
     * Avanza al siguiente juego
     */
    private void siguienteJuego() {
        if (multinivel && nivel.equals("dificil")) {
            posicion = 2;
        } else {
            posicion++;
        }
        Casilla[][] Aux = futoshiki.getArray();
        for (int i = 0; i < aXa; i++) {
            for (int j = 0; j < aXa; j++) {
                if (!Aux[i][j].confirmarConstante()) {
                    Aux[i][j].setValor(0);
                }
            }
        }
        for (Component c : panel.getComponents()) {
            JTextField campo;
            if (c instanceof JTextField) {
                campo = (JTextField) c;

                String nombre = c.getName();
                if (nombre != null) {
                    int fila = Integer.parseInt(nombre.charAt(1) + "");
                    int columna = Integer.parseInt(nombre.charAt(2) + "");
                    Casilla Aux2 = futoshiki.getArray()[fila][columna];
                    if ((nombre.charAt(0) + "").equals("t")) {
                        campo.setBackground(Color.white);
                        if (Aux2.getValor() == 0) {
                            campo.setText("");
                        } else {
                            campo.setText(Aux2.getValor() + "");
                        }
                    }
                }
            }
        }
        if (posicion == futoshikis.size()) {
            posicion = 0;
        }
        futoshiki = futoshikis.get(orden.get(posicion));
        llenarPlantilla();
        modificarColores();
    }

    /**
     * Inicia la partida
     */
    public void iniciar() {
        if (txtNombre.getText().equals("")) {

            JOptionPane.showMessageDialog(panel, "No se ha insertado un nombre", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            if (ganado) {
                ganado = false;
                siguienteJuego();
            }
            jugar = true;
            if (aparece == true) {
                s = 0;
                cs = 0;
                m = 0;
                h = 0;
                t.start();
            }
        }
    }

    /**
     * Cambia el Borde de la casilla seleccionada
     */
    public void cambiarBorde() {
        Border personalizado;
        LineBorder linear = new LineBorder(SystemColor.textHighlight);
        Border basico = new LineBorder(SystemColor.black);
        personalizado = new LineBorder(SystemColor.blue);
        for (Component c : panel.getComponents()) {
            JTextField Aux;
            if (c instanceof JTextField) {
                Aux = (JTextField) c;
                if (Aux.getName() != null) {
                    if (((Aux.getName().charAt(0)) + "").equals("t")) {
                        if (Aux.getName().equals(seleccionado)) {
                            Aux.setBorder(personalizado);
                        } else {
                            Aux.setBorder(basico);
                        }
                    }
                }
            }
        }
    }

    /**
     * Considera todos los posibles valores que se pueden colocar en la casilla
     * actual sin incumplir una condición
     */
    public void sugerencias() {
        int AuxN = 0;
        for (Component c : panel.getComponents()) {
            JTextField Aux;
            if (c instanceof JTextField) {
                Aux = (JTextField) c;
                if (Aux.getName() != null) {
                    if (Aux.getName().equals(seleccionado)) {
                        String str = Aux.getText();
                        if (!str.equals("")) {
                            AuxN = Integer.parseInt(str);
                        }
                    }
                }
            }
        }
        ArrayList<Integer> suge = new ArrayList<Integer>();
        for (int i = 1; i < aXa + 1; i++) {
            if (AuxN != i) {
                if (modificarCasilla((i), true)) {
                    suge.add(i);
                }
            }
        }
        modificarCasilla(AuxN, true);
        String resu = "Posibles jugadas:\n";
        int contador = 0;
        for (int i : suge) {
            if (contador > 0) {
                resu += (" o ");
            }
            resu += i;
            contador++;
        }
        if (suge.size() == 0) {
            resu += "No hay movimientos válidos";
        }
        JOptionPane.showMessageDialog(panel, resu, "SUGERENCIAS", JOptionPane.INFORMATION_MESSAGE);
    }
}
