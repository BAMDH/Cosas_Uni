def buscarBinarioAuxBinario(lista, posicion, elementoABuscar):
    elementoActual = lista[posicion];
    nuevaPosicion = posicion // 2;
    if(elementoActual == elementoABuscar):
        return True;
    #si el elemento actual es mayor al elemento a buscar, se busca en la porcion inferior
    elif(posicion > 0 and elementoActual > elementoABuscar):        
        return buscarBinarioAux(lista[0: posicion],  nuevaPosicion, elementoABuscar );
    #si el elemento actual es menor al elemento a buscar, se busca en la porcion superior
    elif(posicion > 0 and elementoActual < elementoABuscar):
        return buscarBinarioAux(lista[posicion + 1:],  nuevaPosicion, elementoABuscar );
    else:
        return False;

def buscarBinarioAux(lista, numElementosDescartados, elementoABuscar):
    medio = len(lista) // 2;
    elementoActual = lista[medio];
    
    if(elementoActual == elementoABuscar):
        #se suman los elementos descartados cuando se hace busqueda en el intervalo superior
        return numElementosDescartados + medio;
    #si el elemento actual es mayor al elemento a buscar, se busca en la porcion inferior
    elif(medio > 0 and elementoActual > elementoABuscar):        
        return buscarBinarioAux(lista[0: medio],  numElementosDescartados, elementoABuscar );
    #si el elemento actual es menor al elemento a buscar, se busca en la porcion superior
    elif(medio > 0 and elementoActual < elementoABuscar):
        #se suman los elementos descartados
        numElementosDescartados += medio + 1;
        return buscarBinarioAux(lista[medio + 1:],  numElementosDescartados, elementoABuscar );
    else:
        return False;

def principal():
    lista = [2, 3, 4, 5, 6, 7, 8];
    elemento = 7;
    print(buscarBinarioAux(lista, 0, elemento));

principal();

