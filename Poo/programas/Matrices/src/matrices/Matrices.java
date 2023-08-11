
package matrices;
import java.util.Scanner;
/**
 *
 * @author Brandon
 */
public class Matrices {
    public static int[][] leerMatriz(short filas, short columnas,Scanner leerDatos){
        int [][]matriz;
        matriz=new int[filas][columnas];
        for(short i=0;i<filas;i++){
            for(short j=0;j<columnas;j++){
                System.out.println("Inserte el elemento "+j+" de la fila "+i+" de la Matriz");
                short elemento=leerDatos.nextShort();
                matriz[i][j]=elemento;
            }
        }
        return matriz;    
    }
    
    public static void main(String[] args) {
        Scanner leerDatos=new Scanner(System.in);
        System.out.println("Inserte las filas que tendrá la matriz");
        short filas=leerDatos.nextShort();
        System.out.println("Inserte las columnas que tendrá la matriz");
        short columnas=leerDatos.nextShort();
        int [][]matriz= leerMatriz(filas,columnas,leerDatos);
        System.out.println(matriz);
    }
    
}
