# -*- coding: utf-8 -*-
"""
Created on Tue Jun  7 14:54:19 2022

@author: Usuario
"""
def calcular_maximos_por_fila_aux(fila):
    numero=0
    while(fila!=[]):
      if numero<fila[0]:
         numero=fila[0]
      fila=fila[1:]
    return numero    
          
def calcular_maximos_por_fila(matriz):
    vector=[]
    while(matriz !=[]):
        fila=matriz[0]
        valores=calcular_maximos_por_fila_aux(fila)
        vector+=[valores]
        matriz=matriz[1:]
    return vector
            
        


Prueba1=calcular_maximos_por_fila([[1, 2, 3], [8, 10, 1], [5, 6, 7]])
Prueba2=calcular_maximos_por_fila([[5, 7, 9], [18, 10, 10], [3, 2, 1]])
print("Prueba1:", Prueba1)
print("Prueba2:", Prueba2)
            