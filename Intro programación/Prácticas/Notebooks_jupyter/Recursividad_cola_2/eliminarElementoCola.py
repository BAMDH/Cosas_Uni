# Función: eliminar todas las apariciones de un elemento en 
#          una lista.
# Entradas: elemento, lista
# Salidas: lista sin primera aparición del elemento dado
# Restricciones:
def eliminarElemento(elemento, lista):
    return eliminarElementoAux(elemento, lista, 0, []);


def eliminarElementoAux(elemento, lista, i, listaNueva):
    if i == len(lista):
        return listaNueva
    #si el elemento es distinto, se agrega 
    if elemento != lista[i]:
        listaNueva += [lista[i]];
    #llamado recursivo    
    return  eliminarElementoAux(elemento, lista, i + 1, listaNueva)


print("Eliminacion de elemento: " + str(eliminarElemento(2, [2, 4, 5, 8])));
