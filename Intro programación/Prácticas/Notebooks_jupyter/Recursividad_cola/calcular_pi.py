import numpy as np

def calcular_pi(n):
    """
    n: la cantidad de terminos a emplear en la sumatoria
    return: la estimacion de pi/8
    """
    if(isinstance(n, int)):
        #se inicializan los parametros auxiliares
        #resultado
        pi_octavos = 0
        #parametro auxiliar
        i = 1
        #llamado de la funcion recursiva
        return calcular_pi_aux(n, i, pi_octavos)
    else:
        raise ValueError("Tipo de datos incorrecto")
    
def calcular_pi_aux(n, i, pi_octavos):
    """
    n: cantidad de terminos en la sumatoria para aproximar pi/8
    i: variable que se incrementa en cada llamado recursivo
    pi_octavos: resultado a retornar
    """
    if(n == 0):
        #condicion de parada: se retorna la variable con el resultado acumulado
        return pi_octavos
    else:      
        #termino de la sumatoria de leibniz
        pi_octavos += 1 / (i * (i + 2))
        i += 4
        n -= 1
        #se hace el llamado recursivo
        return calcular_pi_aux(n, i, pi_octavos)
    
    
def test_1(n):    
    pi_estimado = 8 * calcular_pi(n)    
    error = np.pi - pi_estimado
    print("Estimacion de pi: ", pi_estimado )
    print("Error ", error)
        
n = 1000
test_1(n)