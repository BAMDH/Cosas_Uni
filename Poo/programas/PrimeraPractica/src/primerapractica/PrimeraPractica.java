
package primerapractica;

import java.util.Scanner;
public class PrimeraPractica {

    public static int fibonacci(int numero){
        int viejoNumero=0;
        int nuevoNumero=1;
        int copia=0;
        
        while(numero!=2){
          copia=viejoNumero+nuevoNumero;
          viejoNumero=nuevoNumero;
          nuevoNumero=copia;
          numero=numero-1;
         }
    return nuevoNumero;
    }
    
    public static int factorial(int numero){
    int factorial=1;
    while (numero!=0){
        factorial=factorial*numero;
        numero=numero-1;
    }
    return factorial;
    }
    
    public static boolean esPrimo(int numero){
        boolean esPrimo=true;
        int i=2;
        while(esPrimo&&(i>numero)){
            if (numero%i ==0){
                esPrimo=false;
                    }
            
        }
            
    return esPrimo;
    }
    
    public static boolean esPalindromo(int numero){
        int copia=numero;
        int palindromo=0;
        while(numero!=0){
            palindromo=palindromo*10+(numero%10);
            numero= numero/10;
        }
        System.out.println(palindromo);
    return copia==palindromo;
    }
   
    public static void formatearFecha(int fecha){
      //El año se obtiene al conseguir los últimos cuatro digitos de fecha
      //El mes se obtiene al divivir la fecha/10000 y obtenerlos últimos dos digitos
      //El día se obtiene al fividr fecha/1000000
      System.out.println("el "+fecha/1000000+" del "+(fecha/10000)%100+" del año "+fecha%10000);
      int nuevaFecha=((fecha/10000)%100)*10000+(fecha/1000000)*100+fecha%100;
      System.out.println("La fecha en el nuevo formato es: "+nuevaFecha);
    }    

    public static void convertirMetros(short metros){
        System.out.println("");
        System.out.println("Corresponde a:");
        System.out.println(metros*0.001+" kilometros");
        System.out.println(metros*0.00062137+" millas");
        System.out.println(metros*3.2808+" pies");
        System.out.println(metros*1.0936+" yardas");
        System.out.println(metros*100+" centimetros");
        System.out.println(metros*39.37+" pulgadas");
        }
    
    public static void pausar(){
       Scanner leerPausa = new java.util.Scanner(System.in);     
       System.out.println("Presione enter para continuar...");
       String parar=leerPausa.findInLine("");
       System.out.println("");    
       leerPausa.reset();
    }
    
    public static void main(String[] args){ 
        byte opcion=0;
        Scanner leerDato = new java.util.Scanner(System.in);        
        do{
            System.out.println("0.Salir\n1.Convertir metros\n2.Darle formato a una fecha\n3.¿Es palindromo?\n4.Calcular factorial\n5.¿Es primo?\n6.Fibonacci");
            System.out.print("Dijite su elección: ");
            System.out.println("");
            opcion= leerDato.nextByte();
                switch (opcion){
                    case 1:
                        
                        System.out.print("Inserte la cantidad de metros a convertir: "); 
                        short metros= leerDato.nextShort();

                        convertirMetros(metros);
                        pausar();
                        break;
                    case 2:
                        System.out.print("Ingrese la fecha: "); 
                        int fecha= leerDato.nextInt();
                        formatearFecha(fecha);
                        pausar();
                        break;
                    case 3:
                        System.out.print("Ingrese el numero: "); 
                        int numero= leerDato.nextInt();

                        boolean resultado=esPalindromo(numero);
                        System.out.println("¿Es palindromo? \n"+resultado);
                        pausar();
                        break;

                    case 4:
                        System.out.print("Ingrese el numero: "); 
                        numero= leerDato.nextInt();
                        int resultadoint;
                        
                        if (numero<1){
                            System.out.println("Inserte un numero mayor o igual a 1");
                        }
                        else{
                            resultadoint=factorial(numero);
                            System.out.println("El factorial de "+numero+" es "+resultadoint);
                        }
                        
                        pausar();
                        break;
                    case 5:
                        System.out.print("Ingrese el numero: "); 
                        numero= leerDato.nextInt();
                        
                        if (numero<2){
                        System.out.println("Inserte un numero mayor o igual a 2");
                        }
                        else{
                            resultado=esPrimo(numero);
                            System.out.println("Es primo "+resultado);
                        }
                        pausar();
                        break;
                     case 6:
                        System.out.print("Ingrese el termino: "); 
                        numero= leerDato.nextInt();
                        resultadoint=0;
                        if (numero>1){
                            resultadoint=fibonacci(numero);
                        }
                        System.out.println("El fibonacci es "+resultadoint);
                        pausar();
                        break;       
                }
            }while(opcion!=0);
        }

}
