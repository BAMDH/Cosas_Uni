# -*- coding: utf-8 -*-
"""
Created on Wed May 18 15:53:51 2022

@author: Usuario
"""

def contar(lista):
    if lista==[]:
        return 0
    if type(lista[0])== list:
        contador=contar(lista[0])
        return contador+contar(lista[1:])
    else: 
        return 1+contar(lista[1:])
    

Prueba1= contar([[1, 2, 4], 1, [1, 2, [3, 4]]])
Prueba2= contar([[1, [2, [3, [4]]]]])
Prueba3= contar([[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[[3]]]]]]]]]]]]]],1]]]]]]]]]]]]]]]]]]]]]]]]]]])
print("Prueba1",Prueba1)
print("Prueba2",Prueba2)
print("Prueba3",Prueba3)