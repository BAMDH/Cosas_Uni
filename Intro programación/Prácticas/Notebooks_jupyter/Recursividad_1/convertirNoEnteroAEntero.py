def convertirNoEnteroAEnteroAux(numero, potenciaActual, epsilon):    
    numeroInt = int(numero);
    maxDigitos = 9;
    resta = numero - numeroInt;
    resta = round(resta, maxDigitos);
    if(resta > epsilon):
        numeroPor10 = numero * 10;
        #se redondea el numero para evitar  imprecisiones en la multiplicacion
        numeroPor10 = round(numeroPor10, maxDigitos);
        return int(convertirNoEnteroAEnteroAux(numeroPor10, potenciaActual, epsilon));
    else:
        return numero;



def sumarDigitos(numero):
    numero = abs(numero);
    numeroInt = int(numero);
    convNoEntero = convertirNoEnteroAEnteroAux(numero, 10, 0.01);    
    resultado = sumarDigitosParteEntera(convNoEntero)
    return resultado;

def sumarDigitosParteEntera(numero):
    if(numero > 0):
        divEntera = numero // 10;
        residuo = numero % 10;
        return residuo + sumarDigitosParteEntera(divEntera);
    else:
        return 0;


#0.357 prueba incorrecta

numDigs = sumarDigitos(122.45);
print(numDigs);


    
