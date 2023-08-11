"""
Calculo de la sumatoria de un numero
param n, entero hasta donde se calcula la sumatoria
return, sumatoria
Restricciones: n debe ser un numero entero y mayor que cero
"""
def calcularSumatoria(n):
    if(isinstance(n, int) and n > 0):
        return calcularSumatoriaAux(n);
    else:
        raise ValueError("El argumento debe ser entero y positivo");
        

"""
Calculo de la sumatoria de un numero
param n, entero hasta donde se calcula la sumatoria
return, sumatoria
"""
def calcularSumatoriaAux(i):
    if(i == 1):
        return 1;
    else:
        return i + calcularSumatoriaAux(i - 1);

#funcion principal
sumatoria = calcularSumatoria(993);
print("Resultado de la sumatoria: " + str(sumatoria));




def calcularSumatoriaIntervalo(a, b):
    if(isinstance(a, int) and isinstance(b, int) and  a > 0 and b > 0):
        return calcularSumatoriaIntervaloAux(a, b);
    else:
        raise ValueError("El argumento debe ser entero y positivo");
        

"""
Calculo de la sumatoria de un numero
param n, entero hasta donde se calcula la sumatoria
return, sumatoria
"""
def calcularSumatoriaIntervaloAux(a, b):
    if(a == b):
        return a;
    else:
        return a + calcularSumatoriaIntervaloAux(a + 1, b);

#funcion principal
sumatoria = calcularSumatoriaIntervalo(10, 15);
print("Resultado de la sumatoria: " + str(sumatoria));





