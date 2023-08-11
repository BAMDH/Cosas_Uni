
"""
Funcion principal de prueba
"""
def probarOrdenamiento1():
    listaA = [8, 7, 4, 2, 1, -2, -300];

    lista1 = [40, 60, 80];
    lista2 = [20, 30, 90];
    lista = combinar(lista1, lista2);
    print("La lista combinada es: " + str(lista));
    print("Lista ordenada: " + str(ordenarPorCombinacion(listaA)));

"""
Implementacion del algoritmo mergesort
"""
def ordenarPorCombinacion(lista):
    N = len(lista);
    #divide y venceras: divide la lista en dos
    posMedio = N // 2 ;
    if( N == 1):
        #caso trivial de lista ordenada
        return lista;
    else:
        #divide la lista en dos para ser ordenadas
        #despues de haber sido ordenadas, se combinan
        listaInferior = ordenarPorCombinacion(lista[0: posMedio]);
        listaSuperior = ordenarPorCombinacion(lista[posMedio :]);
        return combinar(listaInferior, listaSuperior);
        
        
"""
Combina dos listas
@param lista1, lista a combinar
@param lista2, lista a combinar
"""
def combinar(lista1, lista2):
    if(lista2 != []):
        combinarElemento(lista1, lista2[0], 0);
        return combinar(lista1, lista2[1:]);
    else:
        return lista1;
    
"""
Combina un elemento en una lista, de manera ordenada
@param lista, lista donde se combinara
@param elemento, elemento que se combinara
@param pos, posicion de combinacion, inicializado en 0
"""
def combinarElemento(lista, elemento, pos):           
    #si el elemento a insertar es menor a la posicion actual en la lista, lo inserta antes
    #o si llego al final
    if(pos == len(lista) or elemento < lista[pos]):
        lista.insert(pos, elemento);
    else:
        combinarElemento(lista, elemento, pos + 1);

probarOrdenamiento1();
