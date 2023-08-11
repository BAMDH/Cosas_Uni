def reproducirTepezcuintles(numParejasInicial, numMes):
    if(isinstance(numParejasInicial, int) and isinstance(numMes, int) and numMes >= 0 and numParejasInicial > 0):
        return reproducirTepezcuintlesAux(numParejasInicial, numMes);
    else:
        raise ValueError("Los parametros deben ser enteros y positivos");


def reproducirTepezcuintlesAux(numParejasInicial, numMes):
    if(numMes == 0):
        #caso trivial, al final del primer mes habran 3 tepezcuintles por cada pareja
        return 3 * (numParejasInicial);
    else:
        return 3 * ( reproducirTepezcuintlesAux(numParejasInicial, numMes - 1) // 2);

print ( "Numero de tepezcuintles: " + str(reproducirTepezcuintles(5, 2)));    



        
    
