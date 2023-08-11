def buscarMinimo(lista, posMinimo, pos):    
    if(pos < len(lista)):
        elemento = lista[pos];
        if(elemento < lista[posMinimo]):
            posMinimo = pos;
            
        return buscarMinimo(lista, posMinimo, pos + 1);
    else:
        return posMinimo;

def intercambiar(lista, pos1, pos2):
    temp = lista[pos1];
    lista[pos1] = lista[pos2];
    lista[pos2] = temp;
    
    

def ordenarSeleccionAux(lista, pivote):
    if(pivote < len(lista)):
        posMinimo = buscarMinimo(lista, pivote, pivote);
        intercambiar(lista, posMinimo, pivote);
        return ordenarSeleccionAux(lista, pivote + 1);
    else:
        return lista;


def ordenarSeleccion(lista):
    return ordenarSeleccionAux(lista, 0);


def buscarBinarioAux(lista, posMin, posMax, elementoBuscar):
    if(posMin >= 0 and posMax < len(lista)):
        posMed = int((posMin + posMax) / 2);
        if(lista[posMed] == elementoBuscar):
            return posMed;
        elif(lista[posMed] < elementoBuscar): #interv. sup. 
            return buscarBinarioAux(lista, posMed + 1, posMax, elementoBuscar);
        elif(lista[posMed] > elementoBuscar): #interv. inf.
            return buscarBinarioAux(lista, posMin, posMed - 1, elementoBuscar);
            
def buscarBinario(lista, elementoBuscar):
    return buscarBinarioAux(lista, 0, len(lista) - 1, elementoBuscar);


def sumarMatrices(A, B):
    numFilas = len(A);
    numColumnas = len(A[0]);    
    C = inicializarMatriz(0, numFilas, numColumnas, []);
    return sumarMatricesAux(A, B, C, 0, 0);

def inicializarMatriz(numFila, numFilas, numColumnas, matriz):
    if(numFila < numFilas):
        matriz += [[0] * numColumnas];
        return inicializarMatriz(numFila + 1, numFilas, numColumnas, matriz)
    else:
        return matriz;

def sumarMatricesAux(A, B, C, fila, columna):
    numFilas = len(A);
    numColumnas = len(A[0]);
    if(fila < numFilas and columna < numColumnas):
        C[fila][columna] = A[fila][columna] + B[fila][columna];        
        return sumarMatricesAux(A, B, C, fila, columna + 1);
    elif(fila < numFilas):
        columna = 0;
        return sumarMatricesAux(A, B, C, fila + 1, columna);
    else:
        return C;

def principal():
    lista = [100, -5, 4, 3, 2, 5];
    listaOrdenada = ordenarSeleccion(lista);
    print("Lista ordenada: " + str(listaOrdenada));
    print("Pos elemento: " + str(buscarBinario(listaOrdenada, 2)));
    A = [[1, 2, 3], [1, 2, 3]];
    B = [[1, 2, 3], [5, 5, 6]];
    print("C: " + str(sumarMatrices(A, B)));

principal();
        
    
    
