# -*- coding: utf-8 -*-
"""
Created on Wed May 18 15:42:54 2022

@author: Usuario
"""
def separar_aux(lista,par,impar):
    if lista==[]:
        return par,impar
    else:
        if lista[0]%2==0:
            return separar_aux(lista[1:], par+[lista[0]], impar)
        else:
            return separar_aux(lista[1:], par, impar+[lista[0]])

def separar(lista):
    if type(lista)!= list:
        raise ValueError("Por favor inserte una lista")
    else:
        
        return separar_aux(lista,[],[])



Prueba1= separar([12,14,16,20,30])
Prueba2= separar([12,1,20,3])
Prueba3= separar([1,2,6,7,8,9])
print("Prueba1",Prueba1)
print("Prueba2",Prueba2)
print("Prueba3",Prueba3)