def calcular_poblacion_hormigas_cola(n):
    """
    Calcula la poblacion de un hormiguero luego de n periodos con rec. de cola
    n: cantidad de periodos 
    return: numero total de hormigas en los n periodos
    """
    if(isinstance(n, int) and n >= 0 ):
        cantidad_hormigas = 15
        return calcular_poblacion_hormigas_aux_cola(n, cantidad_hormigas)
    else:
        raise ValueError("La variable no es entera o positiva")
        
def calcular_poblacion_hormigas_aux_cola(n, cantidad_hormigas):
    """
    Funcion recursiva de pila que calcula la cantidad de hormigas luego de 
    n periodos, con rec. de cola
    n: numero de periodos
    return: la cantidad total de hormigas
    """
    if(n == 0):
        #cuanto n es 0 se retorna la cantidad de hormigas acumulada
        return cantidad_hormigas
    else:
        # si n no es 0, se reproducen las hormigas nuevas del periodo anterior
        nueva_cantidad_hormigas = (cantidad_hormigas // 2) * 3
        return calcular_poblacion_hormigas_aux_cola(n - 1, nueva_cantidad_hormigas)
    



def calcular_poblacion_hormigas_pila(n):
    """
    Calcula la poblacion de un hormiguero luego de n periodos con rec. de pila
    n: cantidad de periodos 
    return: numero total de hormigas en los n periodos
    """
    if(isinstance(n, int) and n >= 0 ):
        return calcular_poblacion_hormigas_aux_pila(n)
    else:
        raise ValueError("La variable no es entera o positiva")
    
def calcular_poblacion_hormigas_aux_pila(n):
    """
    Funcion recursiva de pila que calcula la cantidad de hormigas luego de 
    n periodos
    n: numero de periodos
    return: la cantidad total de hormigas
    """
    if(n == 0):
        # en el primer periodo siempre hay 15 hormigas
        return 15
    else:
        #por cada pareja se generan 3 hormigas nuevas
        return (calcular_poblacion_hormigas_aux_pila(n - 1) // 2) * 3 

def test_calcular_poblacion_hormigas_pila():
    """Realiza las pruebas de la funcion que calcula la cantidad de hormigas usando rec. de pila"""
    n = 1
    print(" Luego de n = ", n, " periodos hay: ", calcular_poblacion_hormigas_pila(n))
    n = 4
    print(" Luego de n = ", n, " periodos hay: ", calcular_poblacion_hormigas_pila(n))
    
def test_calcular_poblacion_hormigas_cola():
    """Realiza las pruebas de la funcion que calcula la cantidad de hormigas usando rec. de pila"""
    #n = 1
    #print(" Luego de n = ", n, " periodos hay: ", calcular_poblacion_hormigas_cola(n))
    n = 4
    print(" Luego de n = ", n, " periodos hay: ", calcular_poblacion_hormigas_cola(n))
    
        
    
test_calcular_poblacion_hormigas_cola()
    
    
