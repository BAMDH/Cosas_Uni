# Función: factorial
# Entradas: número
# Salidas: cantidad de dígitos del número
# Restricciones: número debe ser entero mayor o igual a cero
def calcularFactorial(n):    # función principal
    if isinstance(n, int):  # verificación argumento
        if n >= 0:
            return calcularFactorialAux(n, 1)  # llamada a auxiliar
        else:
            raise ValueError("Error en n");
    else:
        raise ValueError("Factorial solo está definido para enteros.");

"""
Funcion auxiliar que implementa la recursividad
"""
def calcularFactorialAux(n, resultado):    # función auxiliar recursiva
    if n == 0:
        return resultado
    return calcularFactorialAux(n - 1, resultado * n)  # llamada recursiva


print("Calcular factorial: " + str(calcularFactorial(5)));



