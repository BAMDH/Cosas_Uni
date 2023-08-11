# -*- coding: utf-8 -*-
"""
Created on Wed Jul  6 09:43:00 2022

@author: Usuario
"""

def pares(numero,potencia=0):
    if numero==0:
        return 0
    else:
        digito=numero%10
        if digito%2==0:

            potencia1=10**potencia
      
            return digito*potencia1+pares(numero//10,potencia+1)
        else:
            return pares(numero//10,potencia)




prueba1=pares(3214)      
prueba2=pares(14098)
prueba3=pares(0)
print("Prueba 1:",prueba1)
print("Prueba 2:",prueba2)
print("Prueba 3:",prueba3)