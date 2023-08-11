class OrdenadorCombinacion:
    
    """
    Implementacion del algoritmo mergesort
    """
    def ordenarPorCombinacion(self, lista):
        N = len(lista);
        #divide y venceras: divide la lista en dos
        posMedio = N // 2 ;
        if( N == 1):
            #caso trivial de lista ordenada
            return lista;
        else:
            #divide la lista en dos para ser ordenadas
            #despues de haber sido ordenadas, se combinan
			LI = lista[0: posMedio];
			LS = lista[posMedio :];
            LIO = self.ordenarPorCombinacion(LI);
            LSO = self.ordenarPorCombinacion(LS);
            return self.combinar(LIO, LSO);
            
            
    """
    Combina dos listas
    @param lista1, lista a combinar
    @param lista2, lista a combinar
    """
    def combinar(self, lista1, lista2):
        if(lista2 != []):
            self.combinarElemento(lista1, lista2[0], 0);
            return self.combinar(lista1, lista2[1:]);
        else:
            return lista1;
        
    """
    Combina un elemento en una lista, de manera ordenada
    @param lista, lista donde se combinara
    @param elemento, elemento que se combinara
    @param pos, posicion de combinacion, inicializado en 0
    """
    def combinarElemento(self, lista, elemento, pos):           
        #si el elemento a insertar es menor a la posicion actual en la lista, lo inserta antes
        #o si llego al final
        if(pos == len(lista) or elemento < lista[pos]):
            lista.insert(pos, elemento);
        else:
            self.combinarElemento(lista, elemento, pos + 1);


