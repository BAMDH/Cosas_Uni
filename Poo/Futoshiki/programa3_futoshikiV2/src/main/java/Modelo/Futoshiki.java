/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package Modelo;

import java.io.Serializable;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class Futoshiki implements Serializable{

    private static final long serialVersionUID = 3939279435346767392L;
    private Casilla [][] futoshiki;
    private int aXa;
    private String nivel;
    public Futoshiki(int paXa,String pNivel){
        aXa=paXa;
        nivel=pNivel;
        futoshiki=new Casilla[aXa][aXa];
        for(int i=0;i<aXa;i++){
            for(int j=0;j<aXa;j++){
                futoshiki[i][j]=new Casilla();
            }
        }
    }
    /**Función para confirmar la existencia de un numero en la misma fila
     * 
     * @param int y fila
     * @param int x Columna
     * @return cierto Significa que existe o no existe en dicha fila
     */
    public boolean confirmarExistenciaX(int y,int x){
        int Aux = futoshiki[y][x].getValor();
        boolean cierto=false;
        for (int i=0;i<aXa;i++){
            if(i!=x){
               
                if(futoshiki[y][i].getValor()==Aux&&Aux!=0){
                    
                    cierto=true;
                }
            } 
        }
        return cierto;
    }
    /**Función para confirmar la existencia de un numero en la misma fila
     * 
     * @param int y fila
     * @param int x Columna
     * @return cierto Significa que existe o no existe en dicha columna
     */
    public boolean confirmarExistenciaY(int y,int x){
        int Aux = futoshiki[y][x].getValor();
        boolean cierto=false;
        for (int i=0;i<aXa;i++){
            if(i!=y){
                if(futoshiki[i][x].getValor()==Aux&&Aux!=0){
                    
                    cierto=true;
                }
            }
        }
        return cierto;
    }
    /**Función para confirmar que se cumple la condición
     * 
     * @param int y fila
     * @param int x Columna
     * @return cierto Significa que existe se cumple la condición
     */
    public boolean confirmarCondicion(int y,int x){
        boolean cierto=false;
        String condicion=futoshiki[y][x].getCondicion();
        
        if ((condicion!=null)){
            
                if (futoshiki[y][x].getValor()!=0){
                    if(condicion.equals("b")||condicion.equals("a")){
                            int Aux1=futoshiki[y][x+1].getValor();
                            if(Aux1!=0){
                                cierto=futoshiki[y][x].confirmarCondicion(Aux1);
                            }
                            else{
                                cierto=true;
                            }

                    }
                    else{
                            int Aux2=futoshiki[y+1][x].getValor();
                            if(Aux2!=0){
                                cierto=futoshiki[y][x].confirmarCondicion(Aux2);
                            }
                            else{
                                cierto=true;
                            }

                    }
                }
                else{
                cierto=true;
                }
            }
        
        else{
            cierto=true;
        }
        return cierto;
    }
    /**
     * Devuelve el ancho por el alto
     * @return Dimensión de la matriz
     */
    public int getaXa(){
        return aXa;
    }      
    /**
     * Devuelve la matriz
     * @return Matriz
     */
    public Casilla[][] getArray(){
        return futoshiki;
    }
    /**
     * Devuelve el nivel
     * @return nivel
     */
    public String getNivel(){
        return nivel;
    }
}

