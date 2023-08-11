def calcularRaizNewtonRasph(x, error):
    if(type(x) == int and type(error) == float and x >= 0):       
        #la primer aproximacion de la raiz
        return calcularRaizNewtonRasphAux(x, error, 1);
    else:
        return "Error: y debe ser un real positivo"
    
def calcularRaizNewtonRasphAux(x, error, yApprox):
    errorActual = abs(x - yApprox ** 2);
    if( errorActual <= error):
        return yApprox;    
    else:
        #si la aproximacion es menor a x (numero original), buscamos en el intervalo superior
        yApproxNuevo = 0.5 * (yApprox + (x / yApprox ));
        return calcularRaizNewtonRasphAux(x, error, yApproxNuevo);


print("Calculo por Newton Rasph de: " + str(calcularRaizNewtonRasph(2, 0.0000000001)) );
