 

def calcularFactorial(n):
    factorial = 1; 
    for i in range(1, n + 1):
        factorial *= i;
    return factorial;

def calcularSumatoriaIntervalo(a, b):
    sumatoria = 0;
    for i in range(a, b + 1):
        sumatoria += i;
    return sumatoria;


def construirConPares(numero):
    numDigito = 0;
    numConPares = 0;
    while(numero != 0):
        digito = numero % 10;
        if(digito % 2 == 0):
            numConPares += digito * (10 ** numDigito);
            numDigito += 1;
        numero //= 10;
    return numConPares;


def convertirBinarioDecimal(numeroBinario):
    numDigito = 0;
    numDecimal = 0;
    while(numeroBinario != 0):
        digito = numeroBinario % 10;        
        numDecimal += digito * (2 ** numDigito);
        numDigito += 1;
        numeroBinario //= 10;
    return numDecimal;

def calcularFibonacci(n):
    fibonacci = 1;
    anterior = 1;
    trasAnterior = 1;
    for i in range(2, n + 1):
        fibonacci = anterior + trasAnterior;
        trasAnterior = anterior;
        anterior = fibonacci;
    return fibonacci;
        

print("Factorial iterativo: " + str(calcularFactorial(5)));
print("Fibonacci iterativo: " + str(calcularFibonacci(5)));
print("Construir con pares: " + str(construirConPares(72234)));
print("Calcular sumatoria: " + str(calcularSumatoriaIntervalo(3, 7)));
print("Convertir binario a decimal: " + str(convertirBinarioDecimal(110)));
