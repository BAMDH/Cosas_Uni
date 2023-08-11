

def eliminar_elemento_de_lista(elemento, lista):
    """
    Elimina la primer aparicion de el elemento en la lista
    elemento: se espera sea entero
    lista: lista de elementos enteros
    return: nueva lista sin la primer aparicion (de izquierda a derecha) en 
    la lista recibida
    """
    if(isinstance(lista, list) and isinstance(elemento, int)):
        #se_encontro indica si el elemento a buscarse ya se encontro 1 vez
        se_encontro = False
        #se hace el llamado de la funcion auxiliar
        return eliminar_elemento_de_lista_aux_2(elemento, lista)
    else:
        raise ValueError("Tipo de datos de los parametros incorrecto")
        
def eliminar_elemento_de_lista_aux(elemento_a_buscar, lista, se_encontro):
    """
    Funcion recursiva de pila que elimina la primer aparicion de elemento en lista
    elemento: se espera sea entero
    lista: lista de elementos enteros
    se_encontro: parametro auxiliar que indica si el elemento a buscarse ya se encontro
    return: nueva lista sin la primer aparicion (de izquierda a derecha) en 
    la lista recibida
    """
    #Condicion de parada, la lista es vacia
    if(lista == []):
        #neutro de la concatenacion de listas
        return []
    else:
        #el primer elemento de la lista, es distinto al elemento a buscar
        if(lista[0] != elemento_a_buscar or se_encontro):
            #se concatena el primer elemento de la lista para agregarlo a lista resultante
            return [lista[0]] + eliminar_elemento_de_lista_aux(elemento_a_buscar, lista[1:], se_encontro)
        # se encuentra el elemento a buscar y ademas, no se ha encontrado previamente
        elif(lista[0] == elemento_a_buscar and not se_encontro):
            #se marca en la bandera de si se encontro ya el elemento, que se encontro
            se_encontro = True
            #llamado recursivo que no concatena el elemento en la lista
            return eliminar_elemento_de_lista_aux(elemento_a_buscar, lista[1:], se_encontro)
        
def eliminar_elemento_de_lista_aux_2(elemento_a_buscar, lista):
    """
    Funcion recursiva de pila que elimina la primer aparicion de elemento en lista
    elemento: se espera sea entero
    lista: lista de elementos enteros
    se_encontro: parametro auxiliar que indica si el elemento a buscarse ya se encontro
    return: nueva lista sin la primer aparicion (de izquierda a derecha) en 
    la lista recibida
    """
    #Condicion de parada, la lista es vacia
    if(lista == []):
        #neutro de la concatenacion de listas
        return []
    else:
        #el primer elemento de la lista, es distinto al elemento a buscar
        if(lista[0] != elemento_a_buscar):
            #se concatena el primer elemento de la lista para agregarlo a lista resultante
            return [lista[0]] + eliminar_elemento_de_lista_aux_2(elemento_a_buscar, lista[1:])
        # se encuentra el elemento a buscar y ademas, no se ha encontrado previamente
        elif(lista[0] == elemento_a_buscar):
            #se retorna el resto de la lista, sin el elemento a buscar
            return lista[1:]
        

        
lista_nueva = eliminar_elemento_de_lista(5, [1, 38, 7, 2, 5, 6, 89, 9, 2, 5, 1, 2])
print("lista_nueva ", lista_nueva)

        
            
            
        