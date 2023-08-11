# -*- coding: utf-8 -*-
"""
Created on Thu Mar 24 14:13:31 2022

@author: saul1
"""


def calcular_factorial_rec_pila(numero):
    """
    Calcula el factorial de numero
    numero: numero a usar para calcular el factorial
    return: factorial del numero
    """
    if(isinstance(numero, int) and numero >= 0):
        return calcular_factorial_aux(numero)
        
    else:
        raise ValueError("Tipo de datos incorrecto o negativo")
        
def calcular_factorial_rec_pila_aux(numero):
    """
    Funcion auxiliar recursiva de pila que calcula el factorial de un numero
    numero: numero a usar para calcular el factorial
    return: factorial del numero construido en la pila de llamados
    """
    if(numero == 0):
        #caso trivial 0!
        return 1
    if(numero == 1):
        #caso trivial 1!
        return 1
    else:
        return numero * calcular_factorial_aux(numero - 1)
    
 

def calcular_factorial_cola(numero):
    if(isinstance(numero, int) and numero >= 0):
        factorial = numero
        return calcular_factorial_cola_aux(numero, factorial)
    else:
        raise ValueError("Error en la entrada con el tipo de datos")

def calcular_factorial_cola_aux(numero, factorial):
        #casos base
        if(numero == 0):
            return 1
        if(numero == 1):
            return factorial
        else:
            #n - 1
            numero -= 1
            #resultado acumulado
            factorial *= numero
            #se hace el llamado recursivo
            return calcular_factorial_cola_aux(numero, factorial)
        
factorial = calcular_factorial_cola(0)
print("factorial con cola ", factorial)
    