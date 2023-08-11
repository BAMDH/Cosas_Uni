
"""
Ejecuta la cuenta de los digitos
"""
def contarDigitos(numero):
    if(numero != 0):
        return 1 + contarDigitos(numero // 10);
    else:
        return 0;

def esPalindromo(numero):
    if(isinstance(numero, int)):
        #de cero a n - 1
        numDigitos = contarDigitos(numero) - 1;
        return esPalindromoAux(numero, numDigitos);
    else:
        raise ValueError("La entrada debe ser un numero entero");

def esPalindromoAux(numero, numDigitoMS):
        if(numDigitoMS > 0):
            decenaMax = 10 ** numDigitoMS;
            #extrae el digito mas signiticativo segun la decena maxima
            digitoMS = (numero // decenaMax) % 10;
            digitoLS = (numero % 10);
            if(digitoMS == digitoLS):
                #sigue siendo un posible palindromo
                divEntera = numero // 10;
                return esPalindromoAux(divEntera, numDigitoMS - 2);
            else:
                return False;
        else:# se recorrio todo el numero
                return True;
        

print( "Es palindromo: " + str(esPalindromo(52325)));



