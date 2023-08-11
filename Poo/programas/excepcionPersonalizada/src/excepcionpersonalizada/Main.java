
package excepcionpersonalizada;

/**
 *
 *@author 
 */
public class Main {
    static void confirmarEdad(int edad)throws EdadInsuficiente{
        if(edad<18){
            throw new EdadInsuficiente();
        }
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int edad;
        String respuesta="";
        try{
            edad=10;
            confirmarEdad(edad);
            respuesta="Bienvenido al sitio";
        }
        catch(EdadInsuficiente e){
            respuesta=e.getMessage();
            
        }
        System.out.println(respuesta);
    }
    
}
