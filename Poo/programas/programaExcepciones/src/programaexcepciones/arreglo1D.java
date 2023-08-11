/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programaexcepciones;

/**
 *
 * @author Usuario
 */
public class arreglo1D {
    int [] arreglo1D;
    public arreglo1D(){
        arreglo1D = new int[10];
        for (byte i= 0; i<10; i++){
            arreglo1D[i]=i*5;
        }
    }
    public void imprimirArreglo()throws ArrayIndexOutOfBoundsException{
       for (int i = 0;i <= arreglo1D.length;i++){
            System.out.println("indice "+i+ " tiene el valor "+arreglo1D[i]);
       }
    }
}
