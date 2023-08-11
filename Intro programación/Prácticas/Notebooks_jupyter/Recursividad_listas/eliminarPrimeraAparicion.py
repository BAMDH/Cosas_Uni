def eliminarElementoPrimeraAparicion(elementoBuscar, lista):
    if(lista == []):
        return [];
    else:
        elementoActual = lista[0];
        if(elementoBuscar == elementoActual):
           return lista[1 : ];
        else:
           return [elementoActual] + eliminarElementoPrimeraAparicion(elementoBuscar, lista[1:]);


def principal():
    nuevaLista = eliminarElementoPrimeraAparicion(80, [5, 8, 3, 8]);
    print(nuevaLista);

principal();
