def fibonacci(n):
    if isinstance(n, int):
        if (n >= 0):
            #inicializacion del contador
            return fibonacciAux(n, 0, 1, 0)
        else:
            raise ValueError( "Error: n debe ser mayor o igual a 0");
    else:
        raise ValueError( "Error: el valor recibido debe ser entero");

def fibonacci2(n):
    if isinstance(n, int):
        if (n >= 0):
            #inicializacion del contador
            return fibonacciAux2(n, 0, 0, 1)
        else:
            raise ValueError( "Error: n debe ser mayor o igual a 0");
    else:
        raise ValueError( "Error: el valor recibido debe ser entero");

def fibonacciAux2(n, contador, resAnterior, resTransAnterior):
    if (n == contador):# condición de term
        return resAnterior;   # valor de retorno
    else:
        resTransanterior = resAnterior;
        resAnterior = resAnterior + resTransAnterior;
        contador += 1;
        return fibonacciAux(n, contador, resAnterior, resTransanterior);

    
def fibonacciAux(n, contador, resAnterior, resTransAnterior):
    if (n == contador):# condición de term
        return resAnterior;   # valor de retorno
    else:
        resActual = resAnterior + resTransAnterior;
        contador += 1;
        return fibonacciAux(n, contador, resActual, resAnterior);


print("fibonacci: "  + str(fibonacci2(3)));
