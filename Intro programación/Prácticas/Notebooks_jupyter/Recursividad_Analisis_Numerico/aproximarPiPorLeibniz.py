import sys



def aproximarPiPorLeibniz(n):
    if (n > 0):
        #se multiplica por 8 la aproximacion de leibniz
        return aproximarPiPorLeibnizAux(n, 1, 1, 0) * 8
    else:
        raise ValueError("Error en límite de sumatoria");
    
def aproximarPiPorLeibnizAux(n, termActual, i, resultado):
    if (termActual == n):
        return resultado;
    resultado += 1 / (i * (i + 2)); 
    return aproximarPiPorLeibnizAux(n, termActual + 1, i + 4, resultado);

sys.setrecursionlimit(10**6);
print("Pi por Leibniz: " + str(aproximarPiPorLeibniz(500)));
