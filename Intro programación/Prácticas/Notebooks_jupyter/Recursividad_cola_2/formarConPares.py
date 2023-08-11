# Función: pares
# Entradas: número
# Salidas: número formado por dígitos pares
# Restricciones: número debe ser entero
def formarNumeroConPares(num):
    if isinstance(num, int):
        return formarNumeroConParesAux(num, 0, 0)
    else:
        return "Error: numero debe ser entero"
    
"""
Funcion que ejecuta la recursividad
"""
def formarNumeroConParesAux(num, numDigito, numConPares):
    if num == 0:
        #condición de terminación
        #se retorna el resultado construido en el argumetno
        return numConPares
    elif (num % 10) % 2 == 0:
        #si el numero es par, se modifica el argumento
        return formarNumeroConParesAux(num // 10, numDigito + 1, numConPares + (num % 10 * 10 ** numDigito))
    else:
        return formarNumeroConParesAux(num // 10, numDigito, numConPares);


print("Numero con digitos pares: " + str(formarNumeroConPares(5552554)));
