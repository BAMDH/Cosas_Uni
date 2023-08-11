# -*- coding: utf-8 -*-
"""
Created on Tue Mar 29 14:14:32 2022

@author: saul1
"""

def calcular_potencia(x, n):
    """
    Calcula la potencia de un numero usando recursividad de cola
    x: base
    n: exponente
    return: potencia x ** n
    """
    if(isinstance(n, int) and isinstance(x, int)):
        resultado = 1
        return calcular_potencia_aux(x, n, resultado)
    else:
        raise ValueError("entradas incorrectas")
        
def calcular_potencia_aux(x, n, resultado):
    """
    Implementa la recursividad
    """
    if(n == 0):
        return resultado
    else:
        resultado *= x
        return calcular_potencia_aux(x, n - 1, resultado)

x = 2
n = 4.0
print("potencia ", calcular_potencia(x, n))

