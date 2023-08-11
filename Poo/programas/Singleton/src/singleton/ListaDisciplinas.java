/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package singleton;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class ListaDisciplinas {
    private ArrayList<Disciplina> disciplinas=null;
    
    private static ListaDisciplinas instance = null;
    private ListaDisciplinas(){
        
    }
    public static ListaDisciplinas getInstance(){
        if (instance==null){
            instance = new ListaDisciplinas();
        }
        return instance;
    }
    public ArrayList<Disciplina> crearDisciplinas(){
        if(disciplinas==null){
            disciplinas= new ArrayList<Disciplina>();
            System.out.println("Agregando Disciplinas");
            Disciplina disciplina;
            disciplina=Disciplina.getInstance("Carreras de velocidad");
            disciplinas.add(disciplina);
            disciplina=Disciplina.getInstance("Medio Fondo");
            disciplinas.add(disciplina);
            disciplina=Disciplina.getInstance("Fondo");
            disciplinas.add(disciplina);
            disciplina = Disciplina.getInstance("Saltos");
            disciplinas.add(disciplina);
            disciplina=Disciplina.getInstance("Marcha");
            disciplinas.add(disciplina);
            disciplina=Disciplina.getInstance("Salto");
            disciplinas.add(disciplina);
            disciplina=Disciplina.getInstance("Lanzamientos");
            disciplinas.add(disciplina);
            System.out.println("Disciplinas agregadas");
        }
        return disciplinas;
    }
    public void probarDisciplina(){
        
        for(Prueba d: disciplinas.get(0).crearPruebasMedioCarreras()){
            System.out.println(d.getNombre());
        }
    }
}
