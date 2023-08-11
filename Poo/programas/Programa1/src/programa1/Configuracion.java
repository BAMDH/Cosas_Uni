/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programa1;

/**
 *Clase utilizada para manejar la configuracion
 * @author Usuario
 */
public class Configuracion {
    private int numCompetencia;
    private int numAtletas;
    /**
    *Inicializa la configuracion preterminada
    */
    public Configuracion(){
        setNumCompetencia(1);
        setNumAtletas(0);
    }
    /**
    *Asigna el Numero de competencia
    */
    public void setNumCompetencia(int pNum){
        numCompetencia=pNum;
    }
    /**
    *Asigna el Numero de competencia
    */
    public void setNumAtletas(int pNum){
        numAtletas=pNum;
    }
    
    /**
    *Returna el numero de competencia
    * @return
    */
    public int getNumCompetencia(){
        return numCompetencia;
    }
    /**
    *Returna el numero de competencia
    * @return
    */
    public int getNumAtletas(){
        return numAtletas;
    }
    /**
    *Aumenta en uno el numero de competencia
    */
    public void sumar(){
        numCompetencia++;
    }
    
}
