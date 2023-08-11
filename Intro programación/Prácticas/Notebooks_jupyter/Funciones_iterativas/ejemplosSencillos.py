


def principal():
    listaNueva = insertarElemento(1, 20, [5, 7, 4, 8, 10]);
    print(listaNueva);

def insertarElemento(posicion, elemento, lista):
    listaNueva = [];
    contador = 0;
    for iterador in lista:
        if(contador == posicion):
            listaNueva += [elemento];
            listaNueva += [iterador];
        else:
            listaNueva += [iterador];
        contador += 1;
        

    return listaNueva;
    
principal();
