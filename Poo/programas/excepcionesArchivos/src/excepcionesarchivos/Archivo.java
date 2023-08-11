/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package excepcionesarchivos;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Usuario
 */
public class Archivo {
    private String nombre;
    public Archivo(String pNombre){
       nombre=pNombre;
    }
    /*
        Guarda los datos
        param@ pEstudiante lista de estudiantes
    */
    public void escribirDatos(ArrayList<Estudiante> pEstudiantes) throws IOException{
        FileWriter escribir = null;
        try{
            escribir = new FileWriter(nombre);
            BufferedWriter bfescribir = new BufferedWriter(escribir);
            for (Estudiante estudiante : pEstudiantes) {
                 bfescribir.write(estudiante.getNombre() + "," + estudiante.getCarnet()+ "\n");
            }
            bfescribir.close();
            escribir.close();
            System.out.println("Se han guardado los datos exitosamente");
        }
        catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }    
    } 
    public ArrayList<Estudiante> leerDatos() throws IOException{
        ArrayList<Estudiante> estudiantes = new ArrayList<Estudiante>(); 
        File archivo = null;
         try{
             archivo = new File(nombre);
             Scanner leerArchivo = new Scanner(archivo);
             while (leerArchivo.hasNextLine()){
                String linea = leerArchivo.nextLine();
                Scanner delimitar = new Scanner(linea);
                delimitar.useDelimiter("\\s*,\\s*");
                Estudiante estudiante= new Estudiante(delimitar.next(),Integer.parseInt(delimitar.next()));
                estudiantes.add(estudiante);
             }
         }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
	}
         return estudiantes;
    }
    
}
