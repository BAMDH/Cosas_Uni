package programaexcepciones;


public class Main {
    public static void main(String[] args) {
        System.out.println("---Iniciando programa---");
        CalculadoraBasica objl = new CalculadoraBasica(30,5,"/");
        objl.calcular();
        System.out.println(objl);
        objl = new CalculadoraBasica(30,0,"/");
        objl.calcular();
        System.out.println(objl);
        arreglo1D ar = new arreglo1D();
        try{
            ar.imprimirArreglo();
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println ("EXCEPCION:INDICE FUERA DE RANGO");
            e.printStackTrace();
        }   
        System.out.println("---Finalizando programa---");
        
    }  
}
