# Función: obtener los elementos pares de una lista
# Entradas: lista
# Salidas: lista de elementos pares de la lista de entrada
# Restricciones: no hay
def construirListaConPares(lista):
    return construirListaConParesAux(lista, 0, []);


"""
Construye una lista con los numeros pares de la entrada recursivamente
"""
def construirListaConParesAux(lista, i, resultado):
    if i == len(lista):    # condición de terminación
        return resultado    # valor de retorno
    elif lista[i] % 2 == 0:  # revisión si elemento es par
        resultado += [lista[i]];
        return  construirListaConParesAux(lista,  i  +  1, resultado);
    else:
        return construirListaConParesAux(lista, i + 1, resultado);


print("Lista con elementos pares: " + str(construirListaConPares([2, 4, 5, 9, 7])));



