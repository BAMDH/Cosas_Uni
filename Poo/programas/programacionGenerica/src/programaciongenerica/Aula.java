/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programaciongenerica;

/**
 *
 * @author Usuario
 */
public class Aula {
    private String idEdificio;
    private String idAula;
    private int cantidadEstudiantes;
    public Aula (String pIdEdificio,String pIdAula,int pCantidadEstudiantes){
        idEdificio = pIdEdificio;
        idAula = pIdAula;
        cantidadEstudiantes = pCantidadEstudiantes;
    }
    public String toString(){
        return "Edificio: "+idEdificio+ " Aula :"+idAula+" Estudiantes: "+cantidadEstudiantes;
    }
}
