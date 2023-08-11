/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package universidad.estudiante;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */
public class ListaEstudiante {
   private ArrayList<Estudiante> lista;
   boolean realizado;
   int contador;//contador para toda la clase, no es atributo
   
   public ListaEstudiante(){
        //crea una lista con valor por defecto
        lista=new ArrayList<Estudiante>();
   }

   public void agregarALista(Estudiante estudiante){
        this.lista.add(estudiante);
   }
      public boolean eliminarDeLista(int id){
       realizado = false;
       contador= 0;
       while((contador<this.lista.size())&&(!realizado)){
           if(this.lista.get(contador).id()==id){
                this.lista.remove(contador);
                realizado=true;
           }
           else{
           contador++;
           }
       }
      return realizado;
    }
   
   public String toString(){
      String info="";
      for(int i=0;i<this.lista.size();i++){
            info=info+"Estudiante "+(i+1)+lista.get(i).toString()+"\n\n";
      }
      if (info.length()==0){
          info="No hay elementos en la lista";
      }
          
      return info;
   }
   
}
