class Ordenador_Burbuja:
    def intercambiar(self, pos1, pos2, lista):
        #intercambia los elementos en las posiciones pos1 y pos2
        temporal = lista[pos1]
        lista[pos1] = lista[pos2]
        lista[pos2] = temporal
        return lista

    def flotar_burbuja(self, pivote_burbuja, lista):
        #pone a flotar la burbuja hasta pivote_burbuja
        for pivoteJ in range(0, pivote_burbuja):
            if(lista[pivoteJ] > lista[pivoteJ + 1]):
                lista = self.intercambiar(pivoteJ, pivoteJ + 1, lista)
        return lista

    
    def ordenar(self, lista):
        for pivote_burbuja in range(len(lista) - 1, 0, -1):
            lista = self.flotar_burbuja(pivote_burbuja, lista)
        return lista
    


def principal():
    ordenador = Ordenador_Burbuja();
    lista = [-3, -5, -9, 2, 5, 7, 8];    
    listaNueva = ordenador.ordenar(lista);
    print(listaNueva);

principal();
    



