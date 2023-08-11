/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programaexcepciones;

/**
 *
 * @author Usuario
 */
public class CalculadoraBasica {
    private int operando1;
    private int operando2;
    private String operacion;
    private int resultado;
    public CalculadoraBasica(int pOperando1,int pOperando2,String pOperacion){
        operando1 = pOperando1;
        operando2 = pOperando2;
        operacion = pOperacion;
    }
    public void calcular(){
        System.out.println ("Iniciando metodo...");
        try{
            if (operacion =="/"){
                if (operando2==0){
                    throw new ArithmeticException ("ERROR:DIVISION POR CERO");
                }
                resultado =operando1 /operando2;
            }
        }
        catch (ArithmeticException e){
            System.out.println ("HAY UNA EXCEPCION ARITMETICA:");
            e.printStackTrace();
            resultado = 0;
        }
        catch (Exception e){
            System.out.println ("OTRAS EXCEPCIONES");
            e.printStackTrace();
            resultado = 0;
        }
        finally{
            System.out.println ("...Terminando metodo");
        }
    }
    public String toString(){
        return operando1 +" "+operacion +" "+operando2 +" = "+resultado;
    }
}
