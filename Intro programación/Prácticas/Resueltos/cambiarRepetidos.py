# -*- coding: utf-8 -*-
"""
Created on Wed Jul  6 12:31:00 2022

@author: Usuario
"""

def  cambiarRepetidos(lista,posicion=0):
    nueva_lista=[]
    while(posicion!=len(lista)):
        if lista[posicion] in nueva_lista:
            nueva_lista=nueva_lista+[-1]
        else:
            nueva_lista=nueva_lista+[lista[posicion]]
        posicion=posicion+1
    return nueva_lista

prueba1=cambiarRepetidos ( [ 1 , 2 , 5 , 3 , 1 , 5 , 1 ] )
prueba2=cambiarRepetidos ( [ 1 , 2 , 5 , 3 , 1 , 6 , 1 ] )
prueba3=cambiarRepetidos ( [ 1 , 2 , 57 ,5,5, 3 , 1 , 57 , 81 ] )
print("Prueba 1:",prueba1)
print("Prueba 2:",prueba2)
print("Prueba 3:",prueba3)
                
