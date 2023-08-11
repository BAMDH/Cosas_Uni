# -*- coding: utf-8 -*-
"""
Created on Wed May 11 16:10:05 2022

@author: Administrador
"""

def menu():
    """
    Se crea un menú donde se le pregunta al usuario que algoritmo de ordenamiento
    desea utilizar
    """
    print("Bienvenido usuario, por favor ingrese lo que se le solicite")
    print("Ingrese un 1 si desea utilizar el algoritmo de ordenamiento seleccion \nIngrese un 2 si desea utilizar el algoritmo de ordenamiento quick sort \n")
    entrada = input("Ingrese su respuesta aquí   ")
    if(entrada == "1"):
        from Ordenamiento_Seleccion import calcular_ordenamiento_seleccion
        return calcular_ordenamiento_seleccion()
    elif(entrada == "2"):
        from Quick_Sort import calcular_quick_sort
        return calcular_quick_sort()
    else:
        raise ValueError("Tipo de datos incorrectos")
        
menu()