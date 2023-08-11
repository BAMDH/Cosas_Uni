# Función: multiplicar los elementos de una lista
# Entradas: lista
# Salidas: producto de los elementos de la lista
# Restricciones: lista no nula
def multiplicar_elementos_lista_pila(lista):
    if lista != []:
        return multiplicar_elementos_lista_pila_aux(lista)
    else:
        return "Error: lista no debe ser nula"
def multiplicar_elementos_lista_pila_aux(lista):
    if lista == []: # condicion de terminacion
        return 1 # valor de retorno
    else:
        return lista[0] * multiplicar_elementos_lista_pila_aux(lista[1:])
    
    
def multiplicar_elementos_lista_cola(lista):
    resultado = 1
    if lista != []:
        return multiplicar_elementos_lista_cola_aux(lista, resultado)
    

def multiplicar_elementos_lista_cola_aux(lista, resultado):
    if lista == []: # condicion de terminacion
        return resultado # valor de retorno
    else:
        resultado *= lista[0]
        return multiplicar_elementos_lista_cola_aux(lista[1:], resultado)

print("Multiplicar lista: " + str(multiplicar_elementos_lista_pila([2, 3, 5])));
