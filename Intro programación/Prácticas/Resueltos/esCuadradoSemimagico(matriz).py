# -*- coding: utf-8 -*-
"""
Created on Wed Jul  6 11:03:06 2022

@author: Usuario
"""
def sumar_elementos(lista,cantidad=0):
    if lista==[]:
        return cantidad
    else:

        return sumar_elementos(lista[1:],cantidad+lista[0])
    
def esCuadradoSemimagico_aux(matriz,suma):
    if matriz==[]:
        return True
    else:
        if sumar_elementos(matriz[0])!=suma:
            return False
        else:
            return esCuadradoSemimagico_aux(matriz[1:],suma)
def esCuadradoSemimagico(matriz):
    suma=sumar_elementos(matriz[0])

    return esCuadradoSemimagico_aux(matriz[1:],suma)


prueba1=esCuadradoSemimagico([[6,2,1],[5,4,0],[4,4,1]])  
prueba2=esCuadradoSemimagico([[6,2,1],[5,4,1],[4,4,1]])
prueba3=esCuadradoSemimagico([[6,0,0],[5,1,0],[4,1,1]])
print("Prueba 1:",prueba1)
print("Prueba 2:",prueba2)
print("Prueba 3:",prueba3)