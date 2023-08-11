def buscarElemento(lista, elementoBuscar):
   
    posicion = - 1;
    for contador in range(0, len(lista)):
        print("Elemento a analizar: " + str(lista[contador]));
        if(lista[contador] == elementoBuscar):
           posicion = contador;
           print("Elemento encontrado!");
           break;
           print("Despues del break")
    return posicion;

buscarElemento([1,2, 4, 5, 6, 7], 5);


