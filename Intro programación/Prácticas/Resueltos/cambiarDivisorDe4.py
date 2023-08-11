# -*- coding: utf-8 -*-
"""
Created on Wed Jul  6 12:14:48 2022

@author: Usuario
"""

def cambiarDivisorDe4(numero):
    nuevo_numero=0
    potencia=0
    while(numero!=0):
        digito=numero%10
        numero=numero//10
        if digito%4==0:
            if numero==0:
                nuevo_numero=nuevo_numero
            else:
                nuevo_numero=nuevo_numero*(10**potencia)
        else:
            nuevo_numero=nuevo_numero+digito*(10**potencia)
        potencia=potencia+1
        
    return nuevo_numero


prueba1=cambiarDivisorDe4(1488)
prueba2=cambiarDivisorDe4(72571)
prueba3=cambiarDivisorDe4(4275)
print("Prueba 1:",prueba1)
print("Prueba 2:",prueba2)
print("Prueba 3:",prueba3)