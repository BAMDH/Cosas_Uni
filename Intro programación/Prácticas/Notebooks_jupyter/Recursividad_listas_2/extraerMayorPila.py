def escogerMayor(numero1, numero2):
    mayor = numero1;
    if(numero2 > numero1):
        mayor = numero2;
    return mayor;


def extraerMayorAux(lista):
    if (len(lista) == 1):
        return lista[0];
    else:
        return escogerMayor(lista[0], extraerMayorAux(lista[1:]));

def principal():
    mayor = extraerMayorAux([1, 25, 35, 2, -1, 29, 520]);
    print(mayor);

principal();

    
