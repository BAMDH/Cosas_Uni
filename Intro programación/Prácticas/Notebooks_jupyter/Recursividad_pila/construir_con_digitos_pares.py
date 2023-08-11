def construir_con_digitos_pares(numero):
    """
    Construye un numero nuevo con los digitos pares de la entrada
    numero: numero a usar para tomar sus digitos pares
    return: numero nuevo con los digitos pares de la entrada
    """
    if(isinstance(numero, int)):
        #se toma el valor absoluto
        numero = abs(numero)
        #potencia en cero para el primer llamado
        potencia = 0
        #se invoca la funcion auxiliar
        return construir_con_digitos_pares_aux(numero, potencia)
    else:
        #raise genera una excepcion, de tipo en este caso ValueError
        raise ValueError("El numero de entrada debe ser entero")

def construir_con_digitos_pares_aux(numero, potencia):
    """
    Funcion auxiliar que implementa la recursividad para resolver el problema
    de construir un numero con los digitos pares de la entrada
    """
    if(numero > 0):
        #elimina el digito menos significativo
        div_entera = numero // 10
        #toma el digito menos significativo
        digito_actual = numero % 10
        #se verifica si el digito actual es par
        if(digito_actual % 2 == 0):
            #computa factor para hacer concatenacion numerica
            factor = 10 ** potencia
            #en la proxima llamada recursiva, debe continuar con la siguiente posicion
            potencia += 1
            #llamado recursivo
            return factor * digito_actual + construir_con_digitos_pares_aux(div_entera, potencia)
        else:
            #caso en el que el digito actual no es par
            return construir_con_digitos_pares_aux(div_entera, potencia)
    else:
        #caso trivial
        return 0

numero = 122356
resultado = construir_con_digitos_pares(numero)
print("Numero con digitos pares: ", resultado, " del numero original: ", numero)

    
    
            
