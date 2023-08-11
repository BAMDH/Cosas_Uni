// FACTORIAL RECURUSIVO

// fact;.cpp : Defines the entry point for the console application.
//

#include <stdio.h>
#include <iostream>
#include <conio.h> 

//Se crea la función factorial que recibe un número n
int factorial(int n)
{
	//Se verifica que n no sea igual a 0 o a 1
	if (n == 0 || n == 1)
		//Se retorna un uno si se cumple la condición, esta es la condición donde finaliza el ciclo
		return 1;  /* condición básica */
	else
		//Se multiplica el número por su n anterior
		return(n * factorial(n - 1));  /* llamada recursiva  el valor de n tiende a la condición básica*/
}

//Parte adicional al programa sobre sumatoria de números impares
int Sumatorial_impares(int N) {
	//Se verifica que n no sea igual a 0 o a 1
	if (N == 0 || N == 1)
		//Se retorna un uno si se cumple la condición, esta es la condición donde finaliza el ciclo
		return 1;
	else {
		//Se verifica si el número sea par
		if (N % 2 == 0) {
			//Se le realiza la resta al número par para obtener el número impar anterior
			return(Sumatorial_impares(N - 1));
		}
		else {
			//Se realiza la suma de impares
			return(N + Sumatorial_impares(N - 1));
		}
	}
}

int main(int argc, char* argv[])
{
	//El número a usar
	int N = 6;
	//Se imprime en pantalla el factorial
	printf("Factorial: %i %i\n", N, factorial(N));
	//Se pausa la pantalla
	system("pause");
	//Se realiza la suma de impares
	printf("Sumatoria: %i \n", Sumatorial_impares(N));
	//Se pausa la pantalla
	system("pause");
}
/*
Prueba de ejecución:
Factorial: 6 720
Presione una tecla para continuar . . .
Sumatoria: 9
Presione una tecla para continuar . . .

*/