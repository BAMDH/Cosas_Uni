class OrdenadorQuickSort2: 

    def ordenarQuickSort(self, lista):
        listaMenores = [];
        listaMayores = [];
        if lista == []:
            return [];
        else:
            elementoPivote = lista[0]
            for elemento in lista[1:]:
                if elemento > elementoPivote:
                    listaMayores += [elemento]
                else:
                    listaMenores += [elemento]
            return self.ordenarQuickSort(listaMenores) + [elementoPivote] + self.ordenarQuickSort(listaMayores);


    def probarQS1(self):
            lista = [3, 1, 4, 1, 5, 9, 2, 6, 5, 3];
            #lista = [5, 6]
            listaOrdenada = self.ordenarQuickSort(lista);
            print("lista ordenada: " + str(listaOrdenada));

ordenador = OrdenadorQuickSort2();
ordenador.probarQS1();

