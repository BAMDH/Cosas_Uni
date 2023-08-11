/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package aeropuerto;

/**
 *
 * @author Usuario
 */
public class Avion {
    private String idAvion;
    private Asiento [] [] matrizAsientos;
    public static int cantidadAviones;
    
    Avion(String id){
        matrizAsientos= new Asiento[25][];
        for(short i=0;i<4;i++){
            matrizAsientos[i]= new Asiento[4];
            String letra="";
            for(short g=0;i<4;g++){
                switch(g){
                    case 0:
                        letra = "A";
                        break;
                    case 1:
                        letra = "B";
                        break;
                    case 2:
                        letra = "C";
                        break;
                    case 3:
                        letra = "D";
                        break;
                }
                matrizAsientos[i][g] = new Asiento("J", g, letra);
            }
        }
        for(short i=4;i<25;i++){
            matrizAsientos[i]= new Asiento[6];
            String letra="";
            for(short g=0;i<4;g++){
                switch(g){
                    case 0:
                        letra = "A";
                        break;
                    case 1:
                        letra = "B";
                        break;
                    case 2:
                        letra = "C";
                        break;
                    case 3:
                        letra = "D";
                        break;
                    case 4:
                        letra = "E";
                        break;
                    case 5:
                        letra = "F";
                        break;
                }
                matrizAsientos[i][g] = new Asiento("E", g, letra);
            }
        }
        idAvion = id;
        cantidadAviones ++;
    }
}
