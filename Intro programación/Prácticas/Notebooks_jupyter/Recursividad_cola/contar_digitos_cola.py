# -*- coding: utf-8 -*-
"""
Created on Thu Mar 24 13:35:32 2022

@author: saul1
"""

def contar_digitos(numero):
    """
    Cuenta la cantidad de digitos de un numero implementando recursividad de cola
    numero: numero de entrada, se espera que sea un entero
    return: la cantidad de digitos total
    """
    if(isinstance(numero, int)):
        if(numero != 0):
            #se inicializa en 0 la variable que almacena el resultado final
            cantidad_digitos = 0
            #llamado de la funcion auxiliar recursiva
            return contar_digitos_cola_aux(numero, cantidad_digitos)
        else:
            #si es 0 lo que recibe, retorna un 1 (1 digito)
            return 1
    else:
        #la variable de entrada es de un tipo de datos diferente
        raise ValueError("Tipo de datos incorrecto")

def contar_digitos_cola_aux(numero_entero, cantidad_digitos):
    """
    Implementa el conteo de digitos usando recursividad de cola
    numero_entero: numero a procesar, se le hacen divisiones enteras por 10 
    sucesivas en los llamados recursivos
    cantidad_digitos: guarda la cantidad total de digitos contabilizados hasta 
    ahora
    return: cantidad_digitos, cuando se llega a la condicion de parada
    """
    if(numero_entero > 0):
        #descarta el digito menos significativo
        div_entera = numero_entero // 10
        #se aumenta la cantidad de digitos
        cantidad_digitos += 1
        #se hace el llamado recursivo
        return contar_digitos_cola_aux(div_entera, cantidad_digitos)
    else:
        #se llega a la condicion de parada cuando numero_entero es 0
        return cantidad_digitos
        
    
numero_entero = 267
cantidad_digitos = contar_digitos(numero_entero)
print("Cantidad total de digitos ", cantidad_digitos)
    