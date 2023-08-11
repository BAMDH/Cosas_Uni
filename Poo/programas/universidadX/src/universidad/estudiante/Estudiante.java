/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package universidad.estudiante;

/**
 *
 * @author Usuario
 */
public class Estudiante {
    private String nombre;
    private int identificacion;
    private String carrera;
    private String genero;
    private int telefono;
    
    public Estudiante(){}
    public Estudiante(String nombre,int identificacion, String carrera, String genero,int telefono){
        this.nombre=nombre;
        this.identificacion=identificacion;
        this.carrera=carrera;
        this.genero=genero;
        this.telefono=telefono;
    }
    public int id(){
        return this.identificacion;
    }
    public String toString(){
        return "\nNombre: "+nombre+"\nIdentificacion: "+identificacion+"\nCarrera: "+carrera+"\nGenero: "+genero+"\nTelefono: "+telefono+"\n";
    }
}
