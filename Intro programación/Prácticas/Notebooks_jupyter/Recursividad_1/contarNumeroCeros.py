# -*- coding: utf-8 -*-
"""
Created on Sun Feb 21 17:27:59 2016

@author: Saul
"""

def determinarNumeroDeCerosAux(numero):
        modulo = numero % 10;
        divEntera = numero // 10;
        if(numero > 0): # hay mas digitos a revisar
            if(modulo == 0):
                #divisible entre 10, entonces hay un cero
                return 1 + determinarNumeroDeCerosAux(divEntera); 
            else:
                #si no es divisible entre 10, no hay cero
                return determinarNumeroDeCerosAux(divEntera);
        else:#el ultimo digito a revisar no puede ser cero
            return 0;
            
def determinarNumeroDeCeros(numero):
    numeroAbs = abs(numero);
    if(numeroAbs != 0):# si es distinto de cero, calcular
        numDigitos = determinarNumeroDeCerosAux(numeroAbs);
    else:# si es un cero, solo hay 1 
        numDigitos = 1;
    return numDigitos;

numero = 125060;
numCeros = determinarNumeroDeCeros(numero);
print("Numero de ceros " + str(numCeros));