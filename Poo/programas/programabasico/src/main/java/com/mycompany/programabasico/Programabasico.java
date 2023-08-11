package com.mycompany.programabasico;
import java.util.Scanner;

public class Programabasico {

    public static void main(String[] args) {
        Scanner leerDato = new Scanner(System.in);
        System.out.print("Nombre: ");
        String nombre = leerDato.nextLine();
        System.out.println("Hola "+nombre);
        System.out.println("Fin del programa");         
    }
}
