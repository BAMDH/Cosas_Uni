from OrdenadorSeleccion import OrdenadorSeleccion;

class OrdenadorInsercion:
    def ordenar(self, lista):
        listaOrdenada = [lista[0]];
        for i in range(1, len(lista)):
            elemento = lista[i];
            posInsertar = len(listaOrdenada);
            for j in range(0, len(listaOrdenada)):
                if(listaOrdenada[j] >= elemento and posInsertar == len(listaOrdenada)):
                    posInsertar = j;
            listaOrdenada.insert(posInsertar, elemento);
        return listaOrdenada;


def principal():
    ordenador1 = OrdenadorSeleccion()
    lista = [5, 2, 3, 45, 3, 1, 9, 8];
    listaOrdenada = ordenador1.ordenar(lista);
    print(listaOrdenada);
    listaOrdenada = ordenador1.ordenar(lista);
  

principal();
    
            
        
