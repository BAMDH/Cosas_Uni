#include <iostream>
#include <ctime>

using namespace std;

/**
@brief Calcula el término n-ésimo de la sucesión de Fibonacci.

@param n: número de orden del término buscado. n >= 1.

@return: término n-ésimo de la sucesión de Fibonacci.
*/

//Se inicia la función que calcula el fibonacci recibiendo un numero n de tipo entero
int fibo(int n)
{
	//Se verifica que el número sea menor a 2, si es así se retorna un 1
	if (n < 2)
		return 1;
	else
		cout << n - 1;
		cout << "\n";
		cout << n - 2;
		cout << "\n";
		//Si n es mayor a 2, se procede a calcular el fibonacci 
		//Este calculo se realiza usando la fórmula de fibonacci: n = n-1 + n-2
		//Se retorna dos veces la misma función, por lo que esta terminará hasta que ambas lleguen a valer 0
		return fibo(n - 1) + fibo(n - 2);
}


int main()
{
	//Se inicializa n para recibir el número ingresado por el usuario
	int n;
	//Se inicializa f para que almacene el resultado de la  función fibo()
	int f;
	//Se imprime un mensaje
	cout << "Numero del termino: ";
	//El usuario introduce una respuesta
	cin >> n;
	//Se llama a la función 
	f = fibo(n);
	//Se muestra la respuesta en pantalla
	cout << "El termino " << n << "-esimo es: " << f << endl;
	//se pausa la pantalla
	system("pause");

	return 0;
}
