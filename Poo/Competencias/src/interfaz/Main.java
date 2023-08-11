/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package interfaz;

import java.io.IOException;
import java.util.ArrayList;
import programa1.Atleta;
import programa1.Competencia;
import programa1.Configuracion;

/**
 * inicializa el programa
 *@author Usuario
 */

public class Main {

    /**
     * @param args the command line arguments
     */

    public static void main(String[] args) throws IOException {
    ArrayList<Atleta> atletas= new ArrayList<Atleta>();
    ArrayList<Competencia> competencias= new ArrayList<Competencia>();
    Configuracion config= new Configuracion();
    MenuPrincipal menu = new  MenuPrincipal();
    menu.setVisible(true);
    menu.setAtletas(atletas);
    menu.setCompetencias(competencias);
    menu.setConfig(config);
    }
}