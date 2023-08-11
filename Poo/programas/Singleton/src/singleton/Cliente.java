/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package singleton;

/**
 *
 * @author Usuario
 */
public class Cliente {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ListaDisciplinas lista= ListaDisciplinas.getInstance();
        for(Disciplina d: lista.crearDisciplinas()){
            System.out.println(d.getNombre());
        }
        lista.probarDisciplina();
        lista.probarDisciplina();
    }
    
}
