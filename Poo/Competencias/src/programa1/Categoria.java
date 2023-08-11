
package programa1;

/**
 *Clase utilizada para manejar Categorias
 * @author Usuario
 */
public class Categoria {
    private String nombre;
    private String genero;
    /**
    *Crea la categoria
    * @param pNombre Nombre
    * @param pGenero Genero
    */
    public Categoria(String pNombre,String pGenero){
        setNombre(pNombre);
        setGenero(pGenero);
    }
    /**
    *Asigna el nombre
    * @param pNombre Nombre
    */
    public void setNombre(String pNombre){
        nombre=pNombre;
    }
    /**
    *Asigna el genero
    * @param pGenero Genero
    */
    public void setGenero(String pGenero){
        genero=pGenero;
    }
    /**
    *Retorna el nombre
    * @return
    */
    public String getNombre(){
        return nombre;
    }
    /**
    *Retorna el genero
    * @return
    */
    public String getGenero(){
        return genero;
    }
    /**
    *Retorna la información de la clase
    * @return
    */
    public String toString(){
        return "Categoria "+ getNombre()+" "+getGenero();
    }
}
