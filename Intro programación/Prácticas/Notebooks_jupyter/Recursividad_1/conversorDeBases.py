def convertirBinarioADecimal(hileraNumero):
    primerPosicion = len(hileraNumero) - 1;
    diccionarioBinario = { "0": 0, "1" : 1};
    base = 2;
    posicionActual = 0;
    numDigitos = len(hileraNumero);
    valorDecimal = conversorAux(hileraNumero, diccionarioBinario, base, numDigitos - 1);
    return valorDecimal;

def conversorAux(hileraNumero, diccionarioBase, base, posicionActual):
    numDigitos = len(hileraNumero) - 1;
    exponente = numDigitos - posicionActual;
    if(0 <= posicionActual):
        digitoActual = diccionarioBase[hileraNumero[posicionActual]];
        return digitoActual * base ** exponente + conversorAux(hileraNumero, diccionarioBase, base, posicionActual - 1);
    else:
        return 0;

#principal
valorDecimal = convertirBinarioADecimal("111");
print("Valor decimal: " + str(valorDecimal));
    
