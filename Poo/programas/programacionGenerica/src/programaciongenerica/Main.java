/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package programaciongenerica;

/**
 *
 * @author Usuario
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Nodo Aux;
        ListaDoblementeEnlazada<Integer> listaInt = new ListaDoblementeEnlazada<Integer>();
        Integer i, j,k;
        i=100; j=125;k=150;
        listaInt.push(i);
        listaInt.push(j);
        listaInt.push(k);
        System.out.println("Lista de enteros");
        listaInt.imprimirLista();
        ListaDoblementeEnlazada<Aula>listaAulas= new ListaDoblementeEnlazada<Aula>();
        Aula aula1,aula2,aula3;
        aula1 = new Aula("B3","8",32);
        listaAulas.push(aula1);
        aula2 = new Aula("B6","3",27);
        listaAulas.push(aula2);
        aula3 = new Aula("B3","Rojo",20);listaAulas.push(aula3);
        System.out.println("Lista de Aulas");
        listaAulas.imprimirLista();
        Aux=listaInt.pop();
        System.out.println("Lista de enteros despues de eliminar un elemento");
        listaInt.imprimirLista();
        listaAulas.popLeft();
        System.out.println("Lista de aulas despues de eliminar un elemento");
        listaAulas.imprimirLista();
        listaAulas.clear();
        System.out.println("Lista de Aulas despues de ser vaciada");
        listaAulas.imprimirLista();
        System.out.println("La lista de aulas está vacia "+listaAulas.empty());
        System.out.println("La lista de enteros está vacia "+listaInt.empty());
        System.out.println("metodo peak aplicado en la lista Enteros "+listaInt.peek().dato.toString());
        System.out.println("metodo peakleft aplicado en la lista Enteros "+listaInt.peekleft().dato.toString());
    }
    
}
