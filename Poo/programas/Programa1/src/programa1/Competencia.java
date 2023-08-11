
package programa1;

import java.time.LocalDate;
import java.util.ArrayList;

/**
 *Clase utilizada para manejar las competencias
 * @author Usuario
 */
public class Competencia {
    private String nombre;
    private int idCompetencia;
    private String pais;
    private String lugar;
    private LocalDate fechaInicio;
    private LocalDate fechaFinal;
    private ArrayList<Disciplina> disciplinas;
    private ArrayList<Atleta> atletas;
 /**
    * Crea la competencia e inicia sus listas correspondientes
    * @param pNombre Nombre
    * @param pIdCompetencia Identificación Competencia
    * @param pPais Pais
    * @param pLugar Lugar
    * @param pFechaInicio Fecha de inicio
    * @param pFechaFinal Fecha de finalizacion
    */    
    public Competencia(String pNombre,int pIdCompetencia,String pPais,String pLugar,LocalDate pFechaInicio,LocalDate pFechaFinal){
        setNombre(pNombre);
        setPais(pPais);
        setLugar(pLugar);
        setFechaInicio(pFechaInicio);
        setFechaFinal(pFechaFinal);
        setIdCompetencia(pIdCompetencia);
        disciplinas = new ArrayList<Disciplina>();
        atletas= new ArrayList<Atleta>();
    }
    /**
    *Asigna el nombre
    * @param pNombre Nombre
    */
    public void setNombre(String pNombre){
        if(pNombre.length()>=5&& pNombre.length()<=60){
            nombre = pNombre;
        }
        else{
            System.out.println("No se ha podido agregar el nombre de la competencia");
        }
    }
    /**
    *Asigna la identificacion
    *@param pIdCompetencia Identificación Competencia
    */
    public void setIdCompetencia(int pIdCompetencia){
        idCompetencia=pIdCompetencia;
    }
    /**
    *Asigna el Pais
    * @param pPais Pais
    */
    public void setPais(String pPais){
        pais=pPais;
    }
     /**
    *Asigna el lugar
    * @param pLugar Lugar
    */
    public void setLugar(String pLugar){
        if(pLugar.length()>=5&& pLugar.length()<=60){
            lugar = pLugar;
        }
        else{
            System.out.println("No se ha podido agregar el lugar de la competencia");
        }
    }
     /**
    *Asigna la fecha de inicio
    * @param pFechaInicio Fecha de inicio
    */
    public void setFechaInicio(LocalDate pFechaInicio){
        fechaInicio=pFechaInicio;
    }
    /**
    *Asigna la fecha de finalizacion
    * @param pFechaFinal Fecha de finalizacion
    */
    public void setFechaFinal(LocalDate pFechaFinal){
        fechaFinal=pFechaFinal;
    }
    
    
    /**
    *Retorna el nombre
    * @return
    */
    public String getNombre(){
        return nombre;
    }
    /**
    *Retorna la identificacion de la Competencia
    * @return
    */
    public int getIdCompetencia(){
        return idCompetencia;
    }
    /**
    *Retorna el pais
    * @return
    */
    public String getPais(){
        return pais;
    }
    /**
    *Retorna el lugar
    * @return
    */
    public String getLugar(){
        return lugar;
    }
    /**
    *Retorna la fecha de inicio
    * @return
    */
    public LocalDate getFechaInicio(){
        return fechaInicio;
    }
    /**
    *Retorna la fecha de finalizacion
    * @return
    */
    public LocalDate getFechaFinal(){
        return fechaFinal;
    }
    /**
    *Retorna la lista de disciplinas
    * @return
    */
    public ArrayList<Disciplina> getDisciplinas(){
        return disciplinas;
    }
    /**
    *Retorna la lista de atletas
    * @return
    */
    public ArrayList<Atleta> getAtletlas(){
        return atletas;
    }
    /**
    *Retorna un String con la información de todas las listas
    * @return
    */
    public String listarDisciplinas(){
       String devolver="\nLista de disciplinas\n";
       if(disciplinas.size()==0){
           devolver+="Inexistenten\n";
       }
       else{
            for (int i =0; i< disciplinas.size();i++){
                devolver+=disciplinas.get(i).toString()+"\n";
             }
       }
       return devolver;
    }
    /**
    *Retorna un String con la información de los atletas registrados
    * @return
    */
    public String listarAtletas(){
       String devolver="\nLista de atletas\n";
       if(atletas.size()==0){
           devolver+="Inexistenten\n";
       }
       else{
            for (int i =0; i< atletas.size();i++){
                devolver+=atletas.get(i).toString()+"\n";
             }
       }
       return devolver;
    }
    /**
    *Retorna la información de la clase
    * @return
    */
    public String toString(){
        return "\nNombre: "+getNombre()+"\nPais: "+getPais()+"\nIdentificación competencia: "+getIdCompetencia()+"\nLugar: "+getLugar()+"\nFecha de inicio: "+getFechaInicio()+"\nFecha de finalización: "+getFechaFinal()+listarDisciplinas()+listarAtletas();
    }
}
