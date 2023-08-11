

def convertir_de_base_x_a_base_10(numero, base_origen):
        """
        Funcion principal para hacer la conversion, verifica los tipos y restricciones
        numero: numero a convertir, puede ser negativo
        base_origen: base origen del numero
        """
        if(isinstance(numero, int) and isinstance(base_origen, int) and base_origen > 1):
            #se inicializa la variable auxiliar potencia en 0, para luego aumentarla de 1 en 1
            potencia = 0
            factor = 1
            if(numero < 0):
                #en este caso factor pasa a ser negativo
                factor = -1
            numero = abs(numero)
            return factor * convertir_de_base_x_a_base_10_aux(numero, base_origen, potencia)
        else:
            return "Error: el tipo de datos de las entradas no es correcto, o las restricciones no se satisfacen"
            
def convertir_de_base_x_a_base_10_aux(numero, base_origen, potencia):
    """
    Convierte un numero de base x a base 10
    numero: numero entero en la base x, a convertir a base 10
    base_origen: base del numero recibido
    potencia: potencia actual en la sumatoria
    return: el numero en base 10
    """
    if(numero > 0):
        #se extrae el digito menos significativo
        digito_menos_significativo = numero % 10              
        #se elimina el digito menos significativo
        numero = numero // 10
        #se construye el resultado en la pila de llamados, y se hacer el llamado recursivo
        return digito_menos_significativo * (base_origen ** potencia) + convertir_de_base_x_a_base_10_aux(numero, base_origen, potencia + 1)
    else:
        #caso base o trivial
        return 0

numero = 1001
base_origen = 2
resultado = convertir_de_base_x_a_base_10(numero, base_origen)
print("Numero ", numero, " en base ", base_origen, " es ", resultado, " en base 10")
    
