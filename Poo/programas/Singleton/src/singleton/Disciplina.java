
package singleton;

import java.util.ArrayList;

/**
*Clase que maneja las disciplinas
* @author Usuario
*/
public class Disciplina {
    private String nombre;
    private ArrayList<Prueba> pruebas=null;
    
    private static Disciplina instance = null;
    private Disciplina(String pNombre){
        setNombre(pNombre);
    }
    public static Disciplina getInstance(String pNombre){
        if (instance==null){
            instance = new Disciplina(pNombre);
        }
        return instance;
    }
    public ArrayList<Prueba> crearPruebasMarcha(){
        if(pruebas==null){
            pruebas=new ArrayList<Prueba>();
            System.out.println("Agregando pruebas");
            pruebas.add(new Prueba("20 km"));
            pruebas.add(new Prueba("50 km"));
            System.out.println("Pruebas agregadas");
        }
        return pruebas;
    }
    public ArrayList<Prueba> crearPruebasSalto(){
        if(pruebas==null){
              pruebas=new ArrayList<Prueba>();
            System.out.println("Agregando pruebas");
            pruebas.add(new Prueba("Longitud"));
            pruebas.add(new Prueba("Triple Salto"));
            pruebas.add(new Prueba("Altura"));
            pruebas.add(new Prueba("Pertiga"));
            System.out.println("Pruebas agregadas");
        }
        return pruebas;
    }
    public ArrayList<Prueba> crearPruebasLanzamientos(){
        if(pruebas==null){
              pruebas=new ArrayList<Prueba>();
            System.out.println("Agregando pruebas");
            pruebas.add(new Prueba("Peso"));
            pruebas.add(new Prueba("Disco"));
            pruebas.add(new Prueba("Jabalina"));
            pruebas.add(new Prueba("Martillo"));
            System.out.println("Pruebas agregadas");
        }
        return pruebas;
    }
    public ArrayList<Prueba> crearPruebasSaltos(){
        if(pruebas==null){
              pruebas=new ArrayList<Prueba>();
            System.out.println("Agregando pruebas");
            pruebas.add(new Prueba("100m vallas"));
            pruebas.add(new Prueba("110m vallas"));
            pruebas.add(new Prueba("400m vallas"));
            pruebas.add(new Prueba("3000m obstaculos"));
            System.out.println("Pruebas agregadas");
        }
        return pruebas;
    }
    public ArrayList<Prueba> crearPruebasFondo(){
        if(pruebas==null){
              pruebas=new ArrayList<Prueba>();
            System.out.println("Agregando pruebas");
            pruebas.add(new Prueba("5000m"));
            pruebas.add(new Prueba("10 000m"));
            pruebas.add(new Prueba("Campo a traves"));
            pruebas.add(new Prueba("Media maraton"));
            pruebas.add(new Prueba("Maraton"));
            System.out.println("Pruebas agregadas");
        }
        return pruebas;
    }
    public ArrayList<Prueba> crearPruebasMedioFondo(){
        if(pruebas==null){
              pruebas=new ArrayList<Prueba>();
            System.out.println("Agregando pruebas");
            pruebas.add(new Prueba("800m"));
            pruebas.add(new Prueba("1500m"));
            pruebas.add(new Prueba("3000 m^30"));
            System.out.println("Pruebas agregadas");
        }
        return pruebas;
    }
    public ArrayList<Prueba> crearPruebasMedioCarreras(){
        if(pruebas==null){
            System.out.println("Agregando pruebas");
            pruebas=new ArrayList<Prueba>();
            pruebas.add(new Prueba("100m"));
            pruebas.add(new Prueba("200m"));
            pruebas.add(new Prueba("400 m"));
            System.out.println("Pruebas agregadas");
        }
        return pruebas;
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
    * @return
    */
    public String getNombre(){
        return nombre;
    }
    /**
    *Retorna la medida
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
        return "\nDisciplina "+nombre+"\n"+listarPruebas();
    }
}
