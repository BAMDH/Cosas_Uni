
package singleton;

import java.util.ArrayList;

/**
 *Clase utilizada para manejar la información de las pruebas
 * 
 *
 * @author Usuario
 */
public class Prueba {
    private String nombre;
    /**
    *Crea la prueba
    * @param pNombre Nombre
    * @param pCategoria Categoria
    */
    public Prueba(String pNombre){
        setNombre(pNombre);
    }
     /**
    *Asigna el nombre
    * @param pNombre Nombre
    */
    public void setNombre(String pNombre){
        nombre=pNombre;
    }
    /**
    *Retorna el nombre
    */
    public String getNombre(){
        return nombre;
    }
    public String toString(){
        return nombre;
    }
}
