"""
Función: elimina primera aparición de elemento en lista
Entradas: elemento, lista
Salidas: lista sin primera aparición del elemento dado
Restricciones: no hay
"""
def eliminarElementoDeLista(elemento, lista):
    if lista == []:    # condición de terminación
        return []
    elif lista[0] == elemento:  # condición de terminación
        return lista[1:]
    else:#construccion en la pila de la lista resultante
        return [lista[0]] + eliminarElementoDeLista(elemento, lista[1:])


def eliminarElementoDeListaTodasApariciones(elemento, lista):
    if lista == []:    # condición de terminación
        return []
    elif lista[0] == elemento:  # condición de terminación
        return eliminarElementoDeListaTodasApariciones(elemento, lista[1:]);
    else:#construccion en la pila de la lista resultante
        return [lista[0]] + eliminarElementoDeListaTodasApariciones(elemento, lista[1:]);

#principal
listaSinElemento = eliminarElementoDeListaTodasApariciones(50, [1, 50, 6, 9, 50]);
print("Lista con el elemento eliminado: " + str(listaSinElemento));
