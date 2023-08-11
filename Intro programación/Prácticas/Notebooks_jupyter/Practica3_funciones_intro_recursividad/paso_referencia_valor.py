
def funcion_1(numero, lista):
    """
    Funcion 1 prueba el concepto de paso por referencia y valor
    """
    numero += 1
    lista[1] = 200
    print("Se finalizo la funcion 1")


a = 5
r = [7, 6, 1]
funcion_1(a, r)
print("Contenido de a ", a)
print("Contenido de r ", r)


    
