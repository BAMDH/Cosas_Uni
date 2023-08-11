
def multiplicarNumeros(numero1, numero2):
    if( isinstance(numero1, int) and isinstance(numero2, int)):
        factor = 1;
        if(numero1 < 0 and numero2 < 0):
            numero1 = abs(numero1);
            numero2 = abs(numero2);
        elif(  numero2 < 0 and numero1 > 0):
            factor = -1;
            numero2 = abs(numero2);
        return factor * multiplicarNumerosAux(numero1, numero2);
    else:
        raise ValueError("Los numeros deben ser enteros");

def multiplicarNumerosAux(numero1, numero2):
    if(numero2 == 0):
        return 0;
    else:
        return numero1 + multiplicarNumerosAux(numero1, numero2 - 1);


def principal():
    resultado = multiplicarNumeros(8, -4);
    print(resultado);

principal();
