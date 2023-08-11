def intercambiarPivote(lista, posPivote, posMenor, posMayor):
    if(posMenor >= posMayor):
        if(lista[posPivote] >= lista[posMayor]):
            intercambiar(lista, posPivote, posMayor);

    elif(lista[posMenor] >= lista[posPivote] and lista[posMayor] <= lista[posPivote]):
        intercambiar(lista, posMenor, posMayor);
        intercambiarPivote(lista, posPivote, posMenor + 1, posMayor - 1)
    elif(lista[posMenor] <= lista[posPivote] and lista[posMayor] >= lista[posPivote]):        
        intercambiarPivote(lista, posPivote, posMenor + 1, posMayor - 1)
    elif(lista[posMenor] <= lista[posPivote]);
        intercambiarPivote(lista, posPivote, posMenor + 1, posMayor);
    elif(lista[posMenor] >= lista[posPivote]);
        intercambiarPivote(lista, posPivote, posMenor, posMayor - 1);



def intercambiar(lista, pos1, pos2):
    temp = lista[pos1];
    lista[pos1] = lista[pos2];
    lista[pos2] = temp;
    return temp;
