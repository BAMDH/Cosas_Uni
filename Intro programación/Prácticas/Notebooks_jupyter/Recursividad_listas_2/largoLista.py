# Función: largo de lista
# Entradas: lista
# Salidas: largo de la lista (cantidad de elementos)
# Restricciones: no hay
def obtenerLargo(lista):
    if lista == []: # condición de terminación
        return 0 # valor de retorno
    else:
        return 1 + obtenerLargo(lista[1:]) # llamada recursiva

#funcion principal
lista = [1, 2, 4, 4];
print("Largo de la lista: " + str(obtenerLargo(lista)));
