def contar_digitos(numero):
    if(isinstance(numero, int)):
        return contar_digitos_auxiliar(numero)
    else:
        return "Error en el tipo de datos recibido"
        
        
        
# Función: contarDigitosAuxiliar (versión 1.2) # ejecuta la recursividad 
def contar_digitos_auxiliar(numero): 
    div_entera = numero // 10
    if(div_entera != 0): 
        #llamado recursivo si la div. entera es mayor a cero 
        return 1 + contar_digitos_auxiliar(div_entera)
    else: #condicion de parada 
        return 1
numero = 9289
num_digitos = contar_digitos_auxiliar(numero)
print("Numero de digitos", num_digitos)

