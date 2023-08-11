/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package aeropuerto;

public class Asiento {
    private String claseAsiento;
    private int filaAsiento;
    private String letraAsiento;
    private boolean estadoAsiento;
    private String idPasajero;
    
    Asiento(String clase, int fila, String letra){
        claseAsiento = clase;
        filaAsiento = fila;
        letraAsiento = letra;
        estadoAsiento = true;
    }
    
    public String toString(){
        String info = "";
        if (!estadoAsiento){
            info = "Este asiento está inactivo";
        }
        else{
            info = "Este asiento pertenece a la ";
            if (claseAsiento == "E"){
                info = info + "clase economica\n";
            }
            else{
                info = info + "clase ejecutiva\n";
            }
            info = "Ubicado en la fila " + filaAsiento + " letra" + letraAsiento;
        }    
        return info;
    }
}
