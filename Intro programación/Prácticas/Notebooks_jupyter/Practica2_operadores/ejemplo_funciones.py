a = 13
print(a)
#la definicion de la funcion, junto con el encabezado, con dos parametros
def funcion_1(parametro_1, parametro_2):
    """
    Descripcion general de lo que hace la funcion
    parametro_1: primer operando de la suma a ejecutar
    parametro_2: segundo operando de la suma a ejecutar
    return: retorna el resultado de la suma de los parametros
    """
    print(a)
    suma = parametro_1 + parametro_2
    exponenciacion = parametro_1 ** parametro_2
    return suma

def funcion_2(parametro_1):
    """"
    Negativo del parametro recibido
    parametro_1: numero a hacer negativo
    return: el negativo del parametro recibido
    """
    return -parametro_1

def funcion_3(parametro_1, parametro_2):
    """
    Realiza la resta de los parametros
    parametro_1: minuendo
    parametro_2: sustraendo
    return: resultado de la resta
    """
    resultado = parametro_1 - parametro_2
    return resultado
parametro_1 = 5 * funcion_2(3)
parametro_2 = 7 * funcion_3(4, 2)
resultado = funcion_1(parametro_1, parametro_2) 
print("resultado ", resultado)

