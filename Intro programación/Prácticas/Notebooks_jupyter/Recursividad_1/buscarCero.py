# -*- coding: utf-8 -*-
"""
Created on Fri Feb 26 15:41:18 2016

@author: saul1917
"""
"""
Funcion principal buscar cero, que define las restricciones al usuario
param numero, entero donde se buscara el cero
return True, si encuentra el numero, False de lo contrario
"""
def buscarCero(numero):    
    if(isinstance(numero, int)):
        if(numero != 0):
            numero = abs(numero);
            return buscarCeroAuxiliar(numero);
        else:
            return True;
    else:
        raise ValueError("El argumento debe ser entero");
        
"""
Funcion auxiliar recursiva
param numero, argumento variable en los llamados recursivos
condicion de parada: cuando el argumento es cero
retorn True, si encuentra el numero
"""
def buscarCeroAuxiliar(numero):
    if(numero > 0):
        residuo10 = numero % 10;
        divEntera10 = numero // 10;
        if(residuo10 == 0):
            return True;
        else:
            return buscarCeroAuxiliar(divEntera10);
    else:
        return False;

def principal():
    #ejecucion de la prueba
    try:
        resultado = buscarCero("hola");
    except:
        print("atajamos la papa!");

principal();

    
