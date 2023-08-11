def contarAparicionesDigito(numero, digitoABuscar):
    if(isinstance(numero, int) and isinstance(digitoABuscar, int) and digitoABuscar >= 0 and digitoABuscar < 10):
        return contarAparicionesDigitoAux(numero, digitoABuscar);
    else:
        raise ValueError("Los argumentos deben ser enteros, y el digito a buscar, de un digito");

def contarAparicionesDigitoAux(numero, digitoABuscar):
    divEntera = numero // 10;
    modulo = numero % 10;
    #si no se ha llegado al llamado de un numero con cero
    if(numero != 0):
        if(modulo == digitoABuscar): #si el modulo de 10 es igual al numero a buscar, entonces lo encontramos
            return 1 + contarAparicionesDigitoAux(divEntera, digitoABuscar);
        else:
            return contarAparicionesDigitoAux(divEntera, digitoABuscar);
    else: # se llega al llamado con un numero con cero, no debe tomarse en cuenta
        return 0;


cantApariciones = contarAparicionesDigito(256475, 5);
print("Cantidad de apariciones: " + str(cantApariciones));
        
    
    
