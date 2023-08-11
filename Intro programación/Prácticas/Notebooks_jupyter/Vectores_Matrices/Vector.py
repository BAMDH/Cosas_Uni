from Matriz import Matriz
     #se especifica que Vector hereda de Matriz
class Vector(Matriz):
    """
    Constructor de la clase vector
    """
    def __init__(self, lista, numElementos = 0):
        
        if(lista == []):
            #se llama el constructor de la clase padre matriz
            Matriz.__init__(self, [], 1, numElementos);
        else:
            Matriz.__init__(self, [lista]);
        print(self.hilera);
    """
    Redefinicion del operador de *
    """
    def __mul__(self, vector2):
        suma = 0;
        if(vector2.getNumFilas() == self.getNumFilas() and vector2.getNumColumnas() == self.getNumColumnas()):
            for i in range(0, vector2.getNumColumnas()):
                suma += self.getValor(0, i) * vector2.getValor(0, i);
        else:
            raise ValueError("Dimensiones de vector incorrectas");
        return suma;

    """
    Redefinicion del metodo len, para que retorne el numero de columnas en el vector fila
    """
    def __len__(self):
        return self.getNumColumnas();

    #def setValor(self, numFila, numColumna, valor):
    #    print("Halluuuu");
        


a  = Vector([3, 3]);
b  = Vector([3, 3]);
A = Matriz([[1, 2], [3, 4]]);
prodPunto = a * b;
b.setValor(0, 0, 3);

lista = [];
lista += [a];
lista += [b];
lista += [A];
for elemento in lista:
    print(len(elemento));

