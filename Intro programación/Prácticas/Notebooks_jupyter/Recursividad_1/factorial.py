"""
Funcion que verifica las restricciones
"""
def factorial(n):
    if(isinstance(n, int) and n >= 0):
        return factorialAux(n);
    else:
        raise ValueError("El argumento debe ser entero mayor que cero");
    


"""
Funcion auxiliar recursiva
"""
def factorialAux(n):
    if(n > 0):
        return n * factorialAux(n - 1);
    else:
        return 1;
    
#Prueba del factorial
print("factorial " + str(factorial(5)));

