def concatenarNumerosAux(numero1, numero2, potencia):
    #numero2 es el numero menos significativo en el resultado
    if(numero2 > 0):
        divEntera2 = numero2 // 10;
        residuo2 = numero2 % 10;
        
        return (residuo2 * 10 ** potencia) + concatenarNumerosAux(numero1, divEntera2, potencia + 1);
    elif(numero1 > 0):
        divEntera1 = numero1 // 10;
        residuo1 = numero1 % 10;       
        return (residuo1 * 10 ** potencia) + concatenarNumerosAux(divEntera1, numero2, potencia + 1);
    else:
        return 0;

def concatenarNumeros(numero1, numero2):
    if(isinstance(numero1, int) and isinstance(numero2, int) ):
        return concatenarNumerosAux(numero1, numero2, 0);
    else:
        raise ValueError("Error en las entradas del algoritmo");


def principal():
    numero1 = 2567;
    numero2 = 457;
    resultado = concatenarNumeros(numero1, numero2);
    print(resultado);


principal();
