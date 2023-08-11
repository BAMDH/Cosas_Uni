# -*- coding: utf-8 -*-
"""
Created on Wed Jul  6 09:22:16 2022

@author: Usuario
"""

def ceros(cantidad):
    if cantidad==0:
        return True
    else:
        digito=cantidad%10
        if digito==0:
            return True
        else:
            nueva_cantidad= cantidad//10
            if nueva_cantidad==0:
                return False
            else:
                return ceros(nueva_cantidad)



prueba1=ceros(3214)  
prueba2=ceros(14098)
prueba3=ceros(0)
print("Prueba 1:",prueba1)
print("Prueba 2:",prueba2)
print("Prueba 3:",prueba3)
