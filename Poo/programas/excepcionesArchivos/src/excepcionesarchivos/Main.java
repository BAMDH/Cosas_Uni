/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package excepcionesarchivos;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class Main {
    public static void pausar(){
       Scanner leerPausa = new java.util.Scanner(System.in);     
       System.out.println("Presione enter para continuar...");
       String parar=leerPausa.findInLine("");
       System.out.println("");    
       leerPausa.reset();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Archivo archivo1= new Archivo("hola.txt");
        Archivo archivo2= new Archivo("hola4");
        ArrayList<Estudiante> estudiantes= new ArrayList<Estudiante>();
        estudiantes.add(new Estudiante("Brandon",2002));
        estudiantes.add(new Estudiante("Azucena",1023));
        System.out.println("Guardando los siguientes estudiantes en hola.txt");
        System.out.println(estudiantes.get(0).toString());
        System.out.println(estudiantes.get(1).toString());
        archivo1.escribirDatos(estudiantes);
        pausar();
        estudiantes.clear();
        System.out.println("Tratando de abrir un archivo inexistente para que sea leido");
        archivo2.leerDatos();
        pausar();
        System.out.println("Se han eliminado los estudiantes de la lista de estudiantes, ahora posee "+estudiantes.size()+" estudiantes");
        System.out.println("Cardando los estudiantes en hola.txt");
        estudiantes=archivo1.leerDatos();
        System.out.println("Estudiantes cargados");
        System.out.println(estudiantes.get(0).toString());
        System.out.println(estudiantes.get(1).toString());
        pausar();
      
        
        
        
    }
    
}
