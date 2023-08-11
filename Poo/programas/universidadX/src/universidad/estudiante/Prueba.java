/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package universidad.estudiante;

import java.util.Scanner;


public class Prueba {
    public static void pausar(){
       Scanner leerPausa = new java.util.Scanner(System.in);     
       System.out.println("Presione enter para continuar...");
       String parar=leerPausa.findInLine("");
       System.out.println("");    
       leerPausa.reset();
    }
    public static void main(String[] args){
    Estudiante estudiante;
    ListaEstudiante lista;
    boolean realizado;
    lista= new ListaEstudiante();
    lista.agregarALista(estudiante= new Estudiante("Martha Solano Q.", 10018, "IC", "Femenino",11114444));
    lista.agregarALista(estudiante= new Estudiante("William Mata R.", 10025, "IC", "Masculino", 22223333));
    lista.agregarALista(estudiante= new Estudiante("Brandon Mora D.", 20221, "IC", "Masculino",11114444));
    lista.agregarALista(estudiante= new Estudiante("Azucena di Yeatz S.", 99980, "EL", "Femenino",77776666));
    
    System.out.println("A continuacion se mostraran los datos de los estudiantes agregados.\n");
    pausar();
    System.out.println(lista.toString());
    

    System.out.println("A continuacion se mostraran los datos de los estudiantes al haber eliminado a quien posee la identificacion 10025.\n");
       pausar();
    realizado = lista.eliminarDeLista(10025);
    if (!realizado){
           System.out.println("No se ha encontrado coincidencias con los datos insertados, eliminacion fallida");
        }
    else{
           System.out.println("Se ha eliminado satisfactoriamente");
    }
    System.out.println("Nueva lista:\n");
    System.out.println(lista.toString());
    }
}

