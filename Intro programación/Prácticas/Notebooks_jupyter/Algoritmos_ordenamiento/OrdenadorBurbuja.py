class OrdenadorBurbuja:

        def ordenar(self, lista):
                pivote = len(lista) - 1;
                posicion = 0;
                return self.ordenarListaBurbujaAux(lista, posicion, pivote);

        def ordenarListaBurbujaAux(self, lista, posicion, pivote):
                if(pivote > 0 and posicion + 1 < pivote):
                        nodo1 = lista[posicion];
                        nodo2 = lista[posicion + 1];
                        if(nodo1 > nodo2):
                                self.intercambiar(posicion, posicion + 1, lista);
                        self.ordenarListaBurbujaAux(lista, posicion + 1, pivote);
                elif(pivote > 0):
                        posicion = 0;
                        self.ordenarListaBurbujaAux(lista, posicion, pivote - 1);
                
                return lista;
                        
                
        def intercambiar(self, posA, posB, lista):
                temp = lista[posA];
                lista[posA] = lista[posB];
                lista[posB] = temp;

        def ordenarIterativo(self, lista):
                m = len(lista) - 1;
                for p in range(m, 0, -1):
                        for k in range(0, p):
                                aK = lista[k];
                                aK1 = lista[k + 1];
                                if(aK > aK1):
                                        self.intercambiar(k, k+1, lista);
                return lista;

def principal():
        ordenador = OrdenadorBurbuja();
        lista = [3, -1, 20, -5, 40, 9, 10];
        listaOrdenada = ordenador.ordenarIterativo(lista);
        print(listaOrdenada);
        
principal();
                

















                
