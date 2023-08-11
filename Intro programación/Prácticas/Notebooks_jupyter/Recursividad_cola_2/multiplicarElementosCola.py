"""
Funcion principal
"""
def multiplicarElementos(lista):
    if lista != []:    # condición de terminación
        return multiplicarElementosAux(lista, 0, 1)
    else:
        return "Error: lista no puede ser nula"

"""
Funcion secundaria
"""
def multiplicarElementosAux(lista, i, resultado):
    if i == len(lista):    # condición de terminación
        return resultado    # valor de retorno
    else:
        return multiplicarElementosAux(lista, i + 1, resultado * lista[i])



print("Multiplicar elementos en la lista: " + str(multiplicarElementos([5, 10, 2])));
