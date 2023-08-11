# -*- coding: utf-8 -*-
"""
Created on Tue Jun  7 14:20:07 2022

@author: Usuario
"""
def ordenar_pacientes_por_edad_aux(elemento, nueva_lista,contador):
    if contador==len(nueva_lista):
        nueva_lista+=[elemento]
        return nueva_lista
        
    if nueva_lista[contador]>elemento:
        lista2=nueva_lista[contador:]
        lista1=nueva_lista[:contador]
        nueva_lista=lista1+[elemento]+lista2
        return nueva_lista
    else:
        contador= contador+1
        return ordenar_pacientes_por_edad_aux(elemento, nueva_lista,contador)
def ordenar_pacientes_por_edad(lista,nueva_lista):
        if lista==[]:
            
            return nueva_lista
        else:
                nueva_lista=ordenar_pacientes_por_edad_aux(lista[0], nueva_lista,0)
                return ordenar_pacientes_por_edad(lista[1:], nueva_lista)
                


hola=[3,4,5,0,6,1,2]
print(ordenar_pacientes_por_edad(hola, []))