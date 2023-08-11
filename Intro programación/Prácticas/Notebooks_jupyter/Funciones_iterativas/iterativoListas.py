def multiplicarElementos(lista):
    resultado = 1;
    for elemento in lista:
        resultado *= elemento;
    return resultado;

def contarPares(lista):
    numPares = 0;
    for elemento in lista:
        if(elemento % 2 == 0):
            numPares += 1;
    return numPares;

def obtenerParesOImpares(lista, pares):
    listaNueva = [];
    for elemento in lista:
        if(pares and elemento % 2 == 0):
            listaNueva += [elemento];
        elif(not pares and elemento % 2 != 0):
            listaNueva += [elemento];
    return listaNueva;


def obtenerPares(lista):    
    return obtenerParesOImpares(lista, True);


def obtenerParesEImpares(lista):
    listaPares = obtenerParesOImpares(lista, True);
    listaImpares = obtenerParesOImpares(lista, False);
    return [listaPares, listaImpares];

def eliminarElemento(lista, elementoEliminar):
    listaNueva = [];
    for elemento in lista:
        if(elemento != elementoEliminar):
            listaNueva += [elemento];
    return listaNueva;

def eliminarElementoViejaEscuela(lista, elementoEliminar):
    listaNueva = [];
    for contador in range(0, len(lista)):
        if(lista[contador] != elementoEliminar):
            listaNueva.append(lista[contador]);
    return listaNueva;
        

print("Multiplicacion de elementos en lista: " + str(multiplicarElementos([2, 4, 6])));
print("Contar elementos pares: " + str(contarPares([2, 4, 6, 7])));
print("Obtener elementos pares: " + str(obtenerPares([2, 4, 6, 7])));
print("Obtener elementos pares e impares: " + str(obtenerParesEImpares([2, 4, 6, 7])));
print("Eliminar elemento: " + str(eliminarElementoViejaEscuela([2, 4, 6, 7], 7)));
