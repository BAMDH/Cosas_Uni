from OrdenadorBurbuja import OrdenadorBurbuja;
from OrdenadorQuickSort import OrdenadorQuickSort;
from OrdenadorSeleccion import OrdenadorSeleccion;
#from OrdenadorCombinacion import OrdenadorCombinacion;
from OrdenadorInsercion import OrdenadorInsercion;

class ProbadorOrdenadores:
    def __init__(self):
        self.ordenadorBurbuja = OrdenadorBurbuja();
        self.ordenadorQuickSort = OrdenadorQuickSort();
        self.ordenadorSeleccion = OrdenadorSeleccion();
        #self.ordenadorCombinacion = OrdenadorCombinacion();
        self.ordenadorInsercion = OrdenadorInsercion();

    def probarOrdenadores(self):
        lista = [80, 50, 25, 89, 23, 1, -5, 400];
        listaBurbuja = self.ordenadorBurbuja.ordenarBurbuja(lista);
        #listaInsercion = self.ordenadorInsercion.ordenarInsercion(lista);
        #listaQuickSort = self.ordenadorQuickSort.ordenarQuickSort(lista);
        #listaSeleccion = self.ordenadorSeleccion.ordenarSeleccion(lista);
        #listaCombinacion = self.ordenadorCombinacion.ordenarPorCombinacion(lista);
        print("Lista ordenada: " + str(listaBurbuja));



probador = ProbadorOrdenadores();
probador.probarOrdenadores();
        
        
