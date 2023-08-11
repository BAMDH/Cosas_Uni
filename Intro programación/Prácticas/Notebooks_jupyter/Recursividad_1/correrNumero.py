def correrNumero(numero):
    if(isinstance(numero, int)):
        digitoMenosSignificativo = numero % 10;
        nuevoNumero = numero // 10;
        return correrNumeroAux(nuevoNumero, 0, digitoMenosSignificativo); 
    else:
        raise ValueError("Tipo de datos a la entrada incorrecto");

def correrNumeroAux(numero, potencia, digitoMenosSignificativo):
    if(numero > 0):
        divEntera = numero // 10;
        residuo = numero % 10;
        return residuo * 10 ** potencia + correrNumeroAux(divEntera, potencia + 1, digitoMenosSignificativo);
    else: 
        return digitoMenosSignificativo * 10 ** potencia;

def principal():
    print(correrNumero(25614));

principal();
