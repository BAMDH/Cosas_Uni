
# Función convertir de binario a decimal
# Entradas: número
# Salidas: equivalente del número en decimal
# Restricciones: número sea entero binario
def esBinario(num):
    if num == 0:    # condición de terminación
        return True    # valor de retorno
    elif num % 10 != 0 and num % 10 != 1:
        return False
    else:
        return esBinario(num // 10)

"""
Funcion principal
"""
def convertirBinarioDecimal(num):
    if (isinstance(num, int) and esBinario(num)):
        return convertirBinarioDecimalAux(num, 0, 0)
    else:
        return "Error: numero debe ser entero binario"

"""
Funcion auxiliar que ejecuta la recursividad
"""
def convertirBinarioDecimalAux(num, exponente, resultado):
    if (num == 0):    # condición de terminación
        return resultado    # valor de retorno
    else:
        resultado += num % 10 * 2 ** exponente;
        return  convertirBinarioDecimalAux(num  //  10,  exponente  +  1,  resultado);


print("Conversion del numero a binario: " + str(convertirBinarioDecimal(110)));
