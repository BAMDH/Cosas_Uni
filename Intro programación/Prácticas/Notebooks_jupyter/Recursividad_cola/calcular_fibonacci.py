import time

def calcular_fibonacci_pila(numero):
    """
    Calcula el fibonacci de numero
    numero: el numero a calcular su fibonacci
    return: el fibonacci del numero (entero)
    """
    if(isinstance(numero, int) and numero >= 0):
        return calcular_fibonacci_pila_aux(numero)
    else:
        raise ValueError("tipo de datos incorrecto o numero negativo")

def calcular_fibonacci_pila_aux(numero):
    """
    Funcion auxiliar que calcula el fibonacci de un numero usando rec. de pila
    numero: numero a usar para calcular su fibonacci
    return: fibonacci del numero recibido
    """
    #caso trivial o base (condicion de parada)
    if(numero == 0 or numero == 1):
        return 1
    else:
        #caso general
        return calcular_fibonacci_pila_aux(numero - 1) + calcular_fibonacci_pila_aux(numero - 2)
        




def calcular_fibonacci_cola(n):
    if isinstance(n, int):
        if (n >= 0): #inicializacion del contador, f1 y f2
            return calcular_fibonacci_cola_aux(n, 0, 0, 1)
        else:
            raise ValueError( "n debe ser mayor o igual a 0")
    else:
        raise ValueError( "El valor recibido debe ser entero")

def calcular_fibonacci_cola_aux(n, contador, f1, f2):
    if (n == contador):# condición de term
        return f2 # valor de retorno
    else: 
        return calcular_fibonacci_cola_aux(n, contador + 1, f2, f1 + f2)
    

numero = 8
fibonacci_numero = calcular_fibonacci_cola(numero)
print("Fibonacci numero cola ", fibonacci_numero)