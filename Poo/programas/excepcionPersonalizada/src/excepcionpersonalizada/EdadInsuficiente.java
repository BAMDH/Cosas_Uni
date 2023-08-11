/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepcionpersonalizada;

/**
 *
 * @author Usuario
 */
public class EdadInsuficiente extends Exception{
    private String mensaje;
    public EdadInsuficiente(){
       super ("No puede entrar a este sitio, no posee la edad para comprar");
    }
}
