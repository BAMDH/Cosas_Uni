def formarConDigitosPares(numero):
    
    if(isinstance(numero, int) and numero != 0):
        numero = abs(numero);
        factor = 1;
        return formarConDigitosParesAux(numero, factor);
    else:
        raise ValueError("El numero debe ser entero distinto de cero");



		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
def formarConDigitosParesAux(numero, factor):
    #condicion de parada 
    if(numero > 0):
        #si no se cumple la condicion de parada, revisamos el digito menos significativo
        divEntera = numero // 10;
        #extraemos el digito menos significativo con el modulo 10
        digitoActual = numero % 10;
        if(digitoActual % 2 == 0):
            #si el digito MS es par entonces definimos el sumando como digito acutal por el factor
            sumando = digitoActual * factor;
            return sumando + formarConDigitosParesAux(divEntera, factor * 10);
        else:
            return formarConDigitosParesAux(divEntera, factor);
    else:
        return 0;

#funcion principal
numero = formarConDigitosPares(21102);
print("Este es el numero formado con los digitos pares: " + str(numero));
