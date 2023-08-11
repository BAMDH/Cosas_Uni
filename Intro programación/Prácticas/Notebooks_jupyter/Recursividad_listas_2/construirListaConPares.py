"""
 Función: obtener los elementos pares de una lista
 Entradas: lista
 Salidas: lista de elementos pares de la lista de entrada
 Restricciones: no hay
 """
def construirListaConPares(lista):
    if lista == []:    # condición de terminación
        return []    # valor de retorno
    elif lista[0] % 2 == 0:  # revisión si elemento es par
        return [lista[0]] + construirListaConPares(lista[1:])
    else:
        return construirListaConPares(lista[1:])


#Prueba
print("Lista con numeros pares: " + str(construirListaConPares([2, 4, 6, 9, 15, 31])));
