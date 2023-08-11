# Función: separar elementos pares e impares de una lista
# Entradas: lista
# Salidas: lista de elementos pares y lista de elementos 
#          impares de la lista de entrada
# Restricciones: no hay
def construirListasParesImpares(lista):
    if lista == []:    # caso especial de lista vacía
        return [[], []]
    else:
        return construirListasParesImparesAux(lista, 0, [], [])


def construirListasParesImparesAux(lista, i, listaPares, listaImpares):
    if i == len(lista):    # condición de terminación
        return [listaPares, listaImpares]    # valor de retorno
    elif lista[i] % 2 == 0:  # revisión si elemento es par
        listaPares += [lista[i]];        
    else:
        listaImpares += [lista[i]];
    #llamado recursivo
    return construirListasParesImparesAux(lista, i + 1, listaPares, listaImpares);


print("Lista de pares e impares: " + str(construirListasParesImpares([1, 2, 4, 5, 7, 8])));
