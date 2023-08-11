/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package interfaz;

/**
 *
 * @author Usuario
 */
public  class Estudiante implements BecableContratable{
    public Estudiante(){}
    public void becar(){
        System.out.println("Es posible becarlo");
    }
    public void contratar(){
        System.out.println("Es posible contratarlo");
    }
}
