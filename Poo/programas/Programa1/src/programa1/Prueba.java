
package programa1;

import java.util.ArrayList;

/**
 *Clase utilizada para manejar la información de las pruebas
 * 
 *
 * @author Usuario
 */
public class Prueba {
    private String nombre;
    private String categoria;
    private ArrayList<Marca> marcas= new ArrayList<Marca>();
    /**
    *Crea la prueba
    * @param pNombre Nombre
    * @param pCategoria Categoria
    */
    public Prueba(String pNombre, String pCategoria){
        setNombre(pNombre);
        setCategoria(pCategoria);
    }
     /**
    *Asigna el nombre
    * @param pNombre Nombre
    */
    public void setNombre(String pNombre){
        nombre=pNombre;
    }
     /**
    *Asigna la categoria
    * @param pCategoria Categoria
    */
    public void setCategoria(String pCategoria){
        categoria=pCategoria;
    }
    /**
    *Retorna el nombre
    */
    public String getNombre(){
        return nombre;
    }
    /**
    *Retorna la categoria
    */
    public String getCategoria(){
        return categoria;
    }
    /**
    *Retorna la lista de marcas
    */
    public ArrayList<Marca> getMarcas(){
        return marcas;
    }
    /**
    *Retorna la información de la clase
    */
    public String toString(){
        return nombre+" "+categoria;
    }
}
