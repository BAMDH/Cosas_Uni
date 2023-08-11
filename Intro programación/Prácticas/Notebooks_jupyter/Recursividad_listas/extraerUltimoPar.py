def extraerUltimoElementoPar(lista):
    return extraerUltimoElementoParAux(lista, False);

"""
@param lista, Lista de elementos numericos, debe ser verificado
@param ultimoPar, tipo de datos entero, es la variable que guarda el
ultimo elemento par.
"""
def extraerUltimoElementoParAux(lista, ultimoPar):
    #la condicion de parada es que la lista este vacia
    if(lista == []):
        #rec. de cola, se retorna la variable que acumula el resultado
        return ultimoPar;
    else:
        #se extrae el primer elemento
        elementoActual = lista[0];
        # se corrobora que el elemento sea par
        if(elementoActual % 2 == 0):
            #si es par, se actualiza el acumulador
            ultimoPar = elementoActual;
        # se realiza un nuevo llamado recursivo
        #con el resto de la lista
        return extraerUltimoElementoParAux(lista[1:], ultimoPar);


def principal():
    lista = [2, 4, 6, 15, 20, 93];
    #lista = [5, 5, 5]
    ultimoPar = extraerUltimoElementoPar(lista);
    print(ultimoPar);


principal();
