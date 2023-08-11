
package practicamatrices;

import java.util.Scanner;
public class PracticaMatrices {
     public static void main(String[] args){ 
        int array[][];
        array= new int[4][];
        for(short i=0;i<4;i++){
          array[i]= new int[i];  
        }
        
        for(short x=0;x<4;x++){
          System.out.println("Longitud de la fila "+x+" "+array[x].length);  
        } 
        
    }
    
}
