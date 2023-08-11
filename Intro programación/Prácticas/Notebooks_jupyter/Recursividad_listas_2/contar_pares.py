# Función: contar pares de una lista
# Entradas: lista
# Salidas: cantidad de elementos pares de la lista
# Restricciones: no hay
def contar_pares(lista):
    if lista == []:    # condicion de parada
        return 0    # valor de retorno
    elif lista[0] % 2 == 0:  # revision si elemento es par
        return 1 + contar_pares(lista[1:])
    else:
        return contar_pares(lista[1:])

def contar_pares_old_school(lista):
    if(isinstance(lista, list)):
        indice = 0
        return contar_pares_old_school_aux(lista, indice)
    else:
        raise ValueError("Tipo de datos incorrecto")
    
def contar_pares_old_school_aux(lista, indice):
    if(indice == len(lista)):
        return 0
    else:
        #lista[1:]
        if(lista[indice] % 2 == 0):
            return 1 + contar_pares_old_school_aux(lista, indice + 1)
        else:
            return contar_pares_old_school_aux(lista, indice + 1)
    
    

#numero de pares
print("Numero de pares: " + str(contar_pares_old_school([2, 4, 5, 6])));
