/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package abstracto;

/**
 *
 * @author Usuario
 */
abstract class Proyecto {
    private String codigo;
    private String nombre;
    private double costoInicial;
    private double ingresosAcumulados;
    public Proyecto(String pCodigo, String pNombre, double pCostoInicial, double pIngresosAcumulados){
        codigo= pCodigo;
        nombre= pNombre;
        costoInicial= pCostoInicial;
        ingresosAcumulados= pIngresosAcumulados;
    } 
    public abstract double calcularCostoActual();
    public double calcularBalance(){
        return 0.0;
    };
}
