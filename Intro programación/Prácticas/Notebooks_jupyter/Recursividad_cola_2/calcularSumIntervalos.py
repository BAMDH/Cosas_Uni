# Función: sumatoria de un intervalo
# Entradas: a, b (límites de sumatoria)
# Salidas: sumatoria según fórmula dada
# Restricciones: n >= 0, a <= b
def calcularSumatoriaIntervalo(a, b):
    if a < 0:    # verificación de restricción
        return 'Error: limite debe ser mayor o igual a 0'
    elif a > b:
        return 'Error: A debe ser menor o igual a B'
    else:
        return calcularSumatoriaIntervaloAux(a + 1, b, a);

"""
Funcion auxiliar con rec. de cola
"""
def calcularSumatoriaIntervaloAux(a, b, resultado):
    if a > b:    # condición de terminación
        return resultado    # valor de retorno
    else:
        return calcularSumatoriaIntervaloAux(a + 1, b, resultado + a);


print("Sumatoria en intervalo: " + str(calcularSumatoriaIntervalo(5, 10)));



