# -*- coding: utf-8 -*-
"""
Created on Sun Feb 21 15:37:46 2016

@author: Saul
"""

# Función principal: contarDigitos (versión 1.2)
# Entradas: número
# Salidas: cantidad de dígitos del número
# Restricciones: número debe ser entero
def contarDigitos(num):
    try:
        num = int(num)
    except:
        return 'Error'
    if num != 0:
        return contarDigitosAuxiliar(abs(num))
    else:
        return 1

# Función: contarDigitosAuxiliar (versión 1.2)
# ejecuta la recursividad
def contarDigitosAuxiliar(num):
    if num == 0:      # condición de terminacióm
        return 0       # valor de retorno
    else:
        return 1 + contarDigitosAuxiliar(num // 10)
		
		

        
digitos = contarDigitos(5247);
print("Numero de digitos: "  + str(digitos));
