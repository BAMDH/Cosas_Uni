// t1.cpp : Defines the entry point for the console application.
//

#include <iostream>
#include <ctime>

/**
@file Resolución del problema de las Torres de Hanoi
*/


using namespace std;


/**
@brief Resuelve el problema de las Torres de Hanoi
@param M: número de discos. M > 1.
@param i: número de columna en que están los discos.
i es un valor de {1, 2, 3}. i != j.
@param j: número de columna a que se llevan los discos.
j es un valor de {1, 2, 3}. j != i.

Esta función imprime en la salida estándar la secuencia de
movimientos necesarios para desplazar los M discos de la
columna i a la j, observando la restricción de que ningún
disco se puede situar sobre otro de tamaño menor. Utiliza
una única columna auxiliar.
*/

//Se inicializa la función para la torre hanoi, recibiendo el número de discos M, la columna donde están los discos i y el número de 
//columna donde se llevan los discos j
void hanoi(int M, int i, int j)
{
	//Se ejecuta el ciclo siempre que M sea mayor a 0
	if (M > 0)
	{
		hanoi(M - 1, i, 6 - i - j);
		cout << i << " -> " << j << endl;
		hanoi(M - 1, 6 - i - j, j);
	}
}

int main()
{
	//Se inicializa la variable M que almacena el número de discos
	int M;
	//El do hace que el ciclo while se ejecuta siempre
	do
	{
		//Se imprime un mensaje
		cout << "Número de discos: ";
		//Se guarda la entrada deigitada por el usuario en la variable M
		cin >> M;
		//Se ejecuta el ciclo while siempre que M sea menor o igual a 0
	} while (M <= 0);
	//Se llama a la función hanoi dándole los valores de la cantidad de discos y sus columnas
	hanoi(M, 1, 2);
	//Se pausa la pantalla
	system("pause");

	return 0;
}
