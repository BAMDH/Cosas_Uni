class Matriz:
    """"
    Constructor de la matriz
    @param numFilas, numero de filas de la matriz
    @param numColumnas, numero de columnas de la matriz
    @return instancia de la matriz  """        

    def __init__(self, listaDeListas, numFilas = 0, numColumnas = 0):
        self.hilera = "hola";
        if(listaDeListas != []):
            self.__matriz = listaDeListas;
            self.__filas = len(listaDeListas);
            self.__columnas = len(listaDeListas[0]);
        elif(numFilas > 0 and  numColumnas > 0):
             self.__filas = numFilas;
             self.__columnas = numColumnas;
             self.__matriz = self.__inicializarMatriz(numFilas, numColumnas);
        else:
            raise ValueError("Parametros de inicializacion invalidos");
    """"
    Destructor de la matriz
    """
    def __del__(self):
        print("me destruyeron");

    """
    Metodo privado de la clase matriz
    @param numFilas, numero de filas para inicializar la matriz
    @param numColumnas, numero de columnas para inicializar la matriz
    @return matriz, matriz inicializada
    """
    def __inicializarMatriz(self, numFilas : int, numColumnas : int):
        matriz = [[0] * numColumnas];
        for i in range(1, numFilas):
            matriz += [[0] * numColumnas];
        return matriz;
        
    """
    Operador str() sobrecargado, que representa la instancia de una Matriz, a una hilera
    """
    def __str__(self):
        return str(self.__matriz);

    """"
    Operador +, que suma la matriz misma (self) y otra matriz recibida por parametro
    @param B, segundo operando de la suma
    @return C, suma de matrices
    """
    def __add__(self, B):        
        C = Matriz([], B.__filas, B.__columnas);
        if(self.__filas == B.__filas and self.__columnas == B.__columnas):            
            for fila in range(0, self.__filas):
                for columna in range(0, self.__columnas):
                    C.__matriz[fila][columna] = self.__matriz[fila][columna] + B.__matriz[fila][columna];
        else:
            raise ValueError("Dimensiones invalidas");
        return C;


    """"
    Operador -, que resta la matriz misma (self) y otra matriz recibida por parametro
    @param B, segundo operando de la suma
    @return C, resta de matrices
    """
    def __sub__(self, B):        
        C = Matriz([], B.__filas, B.__columnas);
        if(self.__filas == B.__filas and self.__columnas == B.__columnas):            
            for fila in range(0, self.__filas):
                for columna in range(0, self.__columnas):
                    C.__matriz[fila][columna] = self.__matriz[fila][columna] - B.__matriz[fila][columna];
        else:
            raise ValueError("Dimensiones invalidas");
        return C;

    """
    Retorna el numero de filas de la Matriz 
    """
    def getNumFilas(self):
        return self.__filas;
    """
    Retorna el numero de columnas de la Matriz 
    """
    def getNumColumnas(self):
        return self.__columnas;

    """
    Retorna el valor especifico en la matriz
    """
    def getValor(self, numFila, numColumna):
        return self.__matriz[numFila][numColumna];
    """
    Asigna el valor especifico en la matriz
    """
    def setValor(self, numFila, numColumna, valor):
        self.__matriz[numFila][numColumna] = valor;

    """
    Redefinicion del metodo len, para que retorne el numero de columnas en el vector fila
    """
    def __len__(self):
        
        return self.getNumColumnas() * self.getNumFilas();
    
                    

def principal():
    A = Matriz([[1, 2], [3, 4]]);
    print(A);
    
    B = Matriz([[1, 2], [3, 4]]);
    print(B);
    A += B;
    C = A + B + B;
    print(C);
    print(C);
                
      
