# Función: contar pares de una lista
# Entradas: lista
# Salidas: cantidad de elementos pares de la lista
# Restricciones: no hay
def contarPares(lista):
    return contarParesAux(lista, 0, 0);

"""
Contar elementos pares 
"""
def contarParesAux(lista, i, result):
    if i == len(lista):    # condición de terminación
        return result    # valor de retorno
    elif lista[i] % 2 == 0: # revisión si elemento es par
        return contarParesAux(lista, i + 1, result + 1);
    else:
        return contarParesAux(lista, i + 1, result);


print("Contar elementos pares: " + str(contarPares([3, 4, 5, 6])));
