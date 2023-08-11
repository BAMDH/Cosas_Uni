def verificarEsNumeroPrimo(numero):
    if(isinstance(numero, int)):
        #un numero es primo o no primo independientemente de su signo
        numero = abs(numero);
        if(numero != 2 and numero != 1):
            return verificarEsNumeroPrimoAux(numero, 2);
        else:
            return True;
    else:
        raise ValueError("El numero a verificar debe ser entero");

def verificarEsNumeroPrimoAux(numero, divisor):
    if(divisor != numero):
        residuo = numero % divisor;
        if(residuo == 0):
            return False;
        else:
            return verificarEsNumeroPrimoAux(numero, divisor + 1);
    else:#no se encontro ningun divisor con residuo cero
        return True;

#Prueba
print("Es el numero primo: " + str(verificarEsNumeroPrimo(983)));

    
