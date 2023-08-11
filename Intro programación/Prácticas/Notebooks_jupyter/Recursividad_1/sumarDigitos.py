def sumarDigitosParteEnteraAux(numero):
    if(numero > 0):
        residuo = numero % 10;
        return residuo + sumarDigitosParteEnteraAux(numero // 10);
    else:
        return 0;

def sumarDigitosParteNoEntera(numero):
    #0.2354 * 10 = 2.354
    epsilon = 0.00000001;
    if(numero > epsilon):
        numeroMultiplicado10 = numero * 10;
        digitoMasSignificativo = int(numeroMultiplicado10);
        nuevoNumero = numeroMultiplicado10 - digitoMasSignificativo;
        return digitoMasSignificativo + sumarDigitosParteNoEntera(nuevoNumero);
    else:
        return 0;

print(sumarDigitosParteNoEntera(0.235))
    


        
    
