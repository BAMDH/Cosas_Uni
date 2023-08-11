
package programa1;

import java.util.ArrayList;

/**
*Clase que maneja las disciplinas
* @author Usuario
*/
public class Disciplina {
    private String nombre;
    private String medida;
    private ArrayList<Prueba> pruebas;
    /**
    *Crea la disciplina
    * @param pNombre Nombre
    * @param pMedida Medida
    */
    public Disciplina(String pNombre,String pMedida){
        setNombre(pNombre);
        setMedida(pMedida);
        pruebas= new ArrayList<Prueba>();
    }
    /**
    *Asigna el nombre
    * @param pNombre Nombre
    */
    public void setNombre(String pNombre){
        nombre=pNombre;
    }
    /**
    *Asigna la medida
    * @param pMedida Medida
    */
    public void setMedida(String pMedida){
        medida=pMedida;
    }
    /**
    *Retorna el nombre
    * @return
    */
    public String getNombre(){
        return nombre;
    }
    /**
    *Retorna la medida
    * @return
    */
    public String getMedida(){
        return medida;
    }
    /**
    *Retorna la lista de Pruebas
    * @return
    */
    public ArrayList<Prueba> getPruebas(){
        return pruebas;
    }
    /**
    *Lista todas las pruebas que existen en la disciplina
    * @return
    */
    public String listarPruebas(){
       String devolver="\nLista de pruebas\n";
       if(pruebas.size()==0){
           devolver+="Inexistenten\n";
       }
       else{
            for (int i =0; i< pruebas.size();i++){
                devolver+=pruebas.get(i).toString()+"\n";
             }
       }
       return devolver;
    }
    /**
    *Retorna la información de la clase
    * @return
    */
    public String toString(){
        return "\nDisciplina "+nombre+"\nSistema de medicion: "+medida+listarPruebas();
    }
}
