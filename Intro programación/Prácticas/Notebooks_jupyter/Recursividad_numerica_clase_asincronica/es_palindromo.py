
        

def contar_digitos(numero): 
    """
    Implementa la recursividad de el conteo de digitos
    numero: numero a procesar
    return: cantidad de digitos
    """
    div_entera = numero // 10
    if(div_entera != 0): 
        #llamado recursivo si la div. entera es mayor a cero 
        return 1 + contar_digitos(div_entera)
    else: #condicion de parada 
        return 1

def invertir_numero(numero, potencia_actual, numero_invertido):
    """
    Invierte un numero entero
    numero: numero a invertir
    potencia_actual: potencia actual que se va decrementando con cada llamado recursivo
    numero_invertido: acumula el resultado
    return: numero_invertido
    """
    if(numero == 0):
        return numero_invertido
    else:
        #se extrae el digito menos significativo
        digito_ms = numero % 10
        #se elimina el digito menos significativo
        div_entera = numero // 10
        #se acumula el  numero invertido
        numero_invertido += digito_ms * 10 ** potencia_actual
        #se decrementa la potencia actual
        potencia_actual -= 1
        #llamado recursivo
        return invertir_numero(div_entera, potencia_actual, numero_invertido)
    
    


def es_palindromo(numero):
    """
    Recibe un numero entero que puede ser negativo y verifica si es palindromo
    return: True si es palindromo, false de lo contrario
    """
    if(isinstance(numero, int)):
        numero = abs(numero)
        #se calcula la cantidad de digitos
        cantidad_digitos = contar_digitos(numero)
        
        #se calcula el numero invertido
        resultado = 0
        numero_invertido = invertir_numero(numero, cantidad_digitos - 1, resultado)
        #si es un palindromo, entonces el numero invertido es igual a el mismo
        return numero_invertido == numero
    else:
        raise ValueError("El tipo de datos es incorrecto")
        
def test_invertir_numero():
    numero = 123414
    cantidad_digitos = contar_digitos(numero)
    numero_invertido = 0
    numero_invertido =  invertir_numero(numero, cantidad_digitos - 1, numero_invertido)
    print("numero original ", numero, " numero invertido ", numero_invertido)
    
def test_palindromo():
    numero = 123431
    print("Es palindromo el numero ", numero, " ? ", es_palindromo(numero))

test_palindromo()
    
        