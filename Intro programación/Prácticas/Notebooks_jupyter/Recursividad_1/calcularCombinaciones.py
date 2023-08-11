"""
Funcion principal que verifica las restricciones
"""
def calcularNumeroCombinaciones(n, m):
    if(isinstance(n, int) and isinstance(m, int) and n >= 0 and m >= 0):
        return calcularNumeroCombinacionesAux(n, m);
    else:
        raise ValueError("Los argumentos deben ser enteros y mayores que cero");
    
"""
Funcion recursiva que calcula el numero de combinaciones
"""
def calcularNumeroCombinacionesAux(n, m):
    if(m == 0 or m == n):
        return 1;
    else:
        return calcularNumeroCombinacionesAux(n - 1, m) + calcularNumeroCombinacionesAux(n - 1, m - 1);




"""
Funcion que verifica las restricciones
"""
def calcularFactorial(n):
    if(isinstance(n, int) and n >= 0):
        return factorialAux(n);
    else:
        raise ValueError("El argumento debe ser entero mayor que cero");
    


"""
Funcion auxiliar recursiva
"""
def calcularFactorialAux(n):
    if(n > 0):
        return n * factorialAux(n - 1);
    else:
        return 1;

def principal():
    #Prueba
    numCombinaciones1 = calcularNumeroCombinaciones(6, 2);
    print("Numero de combinaciones: " + str(numCombinaciones1));
    #Prueba 2
    numCombinaciones2 = calcularNumeroCombinaciones2(6, 2);
    print("Numero de combinaciones: " + str(numCombinaciones1));


"""
Funcion principal que verifica las restricciones
"""
def calcularNumeroCombinaciones2(n, m):
    if(isinstance(n, int) and isinstance(m, int) and n >= 0 and m >= 0):
        calcularNumeroCombinacionesAux(n, m);
    else:
        raise ValueError("Los argumentos deben ser enteros y mayores que cero");
    
"""
Funcion recursiva que calcula el numero de combinaciones
"""
def calcularNumeroCombinacionesAux2(n, m):
    n_factorial = calcularFactorial(n);
    m_factorial = calcularFactorial(m);
    n_menos_m_factorial = calcularFactorial(n - m);
    return n_factorial / (n_factorial * n_menos_m_factorial);

principal();



 
