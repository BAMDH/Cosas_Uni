# -*- coding: utf-8 -*-
"""
Created on Wed Jul  6 10:50:35 2022

@author: Usuario
"""
def apariciones_aux(numero,cantidad,n_apariciones):
        if cantidad==0:
            return n_apariciones
        else:
            digito=cantidad%10
            
            if digito==numero:
                n_apariciones= n_apariciones+1
            return apariciones_aux(numero, cantidad//10, n_apariciones)
def apariciones(numero,cantidad):
    if numero==0 and cantidad==0:
        return 1
    else:
        return apariciones_aux(numero,cantidad,0)


prueba1=apariciones(4,34214)  
prueba2=apariciones(7,140981)
prueba3=apariciones(0,0)
print("Prueba 1:",prueba1)
print("Prueba 2:",prueba2)
print("Prueba 3:",prueba3)