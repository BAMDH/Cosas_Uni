def encontrarMayor(lista):
    if(isinstance(lista, list)):
        return encontrarMayorAux(lista, len(lista) - 1, lista[len(lista) - 1]);
    else:
        raise ValueError("No se recibió una lista como parametro");

#supone recorrido inverso de la lista
def encontrarMayorAux(lista, i, mayor):
    if(i == -1):
        #condicion de parada, primera posicion invalida
        return mayor;
    else:
        #si el elemento actual es mayor, se actualiza
        if(lista[i] > mayor):
            mayor = lista[i];
        return encontrarMayorAux(lista, i - 1, mayor);


print("Elemento mayor: " + str(encontrarMayor([1, 5, 0, 2])));
