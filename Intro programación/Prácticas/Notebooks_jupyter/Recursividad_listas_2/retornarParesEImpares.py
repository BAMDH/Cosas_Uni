"""
Funcion principal
"""
def retornarParesE_Impares(lista):
    if lista == []:    # caso especial de lista vacía
        return [[], []]
    else:
        #funcion lambda par, que evalua el residuo de 2
        par = lambda x: x % 2 == 0
        impar = lambda x: x % 2 != 0
    return [escogerDeLista(lista, par)] + [escogerDeLista(lista, impar)]

"""
Escoge los elementos en la lista segun la funcion definida por parametro
"""
def escogerDeLista(lista, funcionLambda):
    if lista == []:    # condición de terminación
        return []    # valor de retorno
    elif funcionLambda(lista[0]):  # revisión si elemento es par o impar,
        #dependiendo de la funcion recibida
        
        return [lista[0]] + escogerDeLista(lista[1:], funcionLambda)
    else:
        return escogerDeLista(lista[1:], funcionLambda)



#Prueba principal
paresEImpares = retornarParesE_Impares([0, 1, 2, 5, 89, 8, 10]);
print("Lista de numeros pares e impares: " + str(paresEImpares));






