def verificar_si_hay_cero(numero):
    if(isinstance(numero, int)):
        numero = abs(numero)
        if(numero == 0):
            return True
        else:
            return verificar_si_hay_cero_aux(numero)
    else:
        return "Error con el tipo de datos de la entrada"

def verificar_si_hay_cero(numero):
    """
    Funcion que verifica si existe al menos un cero en el numero
    recibido
    numero: numero a examinar, debe ser entero
    return: true si tiene al menos un cero, false de lo contrario
    """
    #si recibe un cero, llego al final del recorrido
    if(numero == 0):
        digito_ms = numero % 10
        if(digito_ms == 0):
            return True
        else:
            numero //= 10
            return verificar_si_hay_cero(numero)
    else:
        return False

a = 9213
tiene_cero = verificar_si_hay_cero(a)
print("El numero ", a, " tiene cero? ", tiene_cero)


    
