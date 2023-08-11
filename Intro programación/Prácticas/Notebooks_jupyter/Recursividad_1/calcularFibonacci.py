"""
Funcion que calcula el fibonacci de un numero
param numero, numero a calcular el fibonacci
return, entero correspondiente al fibonacci del parametro
Restricciones, el argumento debe ser mayor o igual a cero
"""
def calcularFibonacci(numero):
    if(isinstance(numero, int) and numero >= 0):        
        return calcularFibonacciAux(numero);
    else:
        #retorna una excepcion del tipo ValueError
        raise ValueError('El argumento debe ser un entero mayor o igual a cero');

"""
Funcion recursiva que realiza el calculo del fibonacci
Fibo(n) = Fibo(n - 1) + Fibo(n + 2)
Condicion de parada: numero == 1 o numero == 0, se retorna 1
param numero, argumento recursivo
return, entero 
"""
def calcularFibonacciAux(numero):
    if(numero > 1):
        #llamado recursivo
        return calcularFibonacciAux(numero - 1) + calcularFibonacciAux(numero - 2);
    elif(numero == 1 or numero == 0):
        return 1;

#prueba del numero de fibonacci
fibo = calcularFibonacci(5);
print("fibonacci: " + str(fibo));
