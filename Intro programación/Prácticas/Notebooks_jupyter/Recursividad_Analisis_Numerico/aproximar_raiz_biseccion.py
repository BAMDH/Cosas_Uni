def calcular_raiz_biseccion(x, error):
    if (x >= 0 and type(x) == int and type(error) == float):
        li = 0;
        ls = x;
        #la primer aproximacion de la raiz
        return calcular_raiz_biseccion_aux(x, error, li, ls, x / 2);
    else:
        return "Error: y debe ser un real positivo"
    
def calcular_raiz_biseccion_aux(x, error, li, ls, y_approx):
    if(abs(x - y_approx ** 2) <= error):
        return y_approx
    if y_approx ** 2 > x:
        #si la aproximacion "se paso", nos fijamos en el intervalo inferior
        return calcular_raiz_biseccion_aux(x, error, li, y_approx, (li + ls) / 2);
    else:
        #si la aproximacion es menor a x (numero original), buscamos en el intervalo superior
        return calcular_raiz_biseccion_aux(x, error, y_approx, ls, (li + ls) / 2);


print("Calculo por biseccion de: " + str(calcular_raiz_biseccion(2, 0.00001)) );
