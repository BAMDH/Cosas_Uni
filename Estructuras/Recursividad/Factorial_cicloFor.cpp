// FACTORIAL CON CICLOS

#include <iostream>
#include <ctime>
#include <conio.h> // clrscr y getch
using namespace std;
//Se realiza el factorial de numero
double factorial(int numero)
{
	//Se establece la respuesta como un double inicializado en 0, esto ya que el factorial mínimo a dar es 1.
	double respuesta = 1;
	//Se inicializa el contador para el for
	int i;

	if (numero == 0)
		//Se retorna un 1 si el número es igual a 0
		return 1;
	else
	{
		//Se inicializa el ciclo que va realizando el factorial 
		for (i = 1; i <= numero; i++)
			//Se va incrementando la respuesta multiplicandose por el contador
			respuesta = respuesta * i;
	}
	//Se devuelve el resultado
	return respuesta;
}

int main()
{
	//Se inicializa el número como un float
	float n;
	//Se imprimen algunas instrucciones
	cout << "\t\t\t\t\t\t\t\t\tE.S.C";
	cout << "\nINGRESE UN NUMERO PARA SACAR SU FACTORIAL...";
	//Se le pide la respuesta al usuario
	cin >> n;
	//Se verifica si el número es negativo o positivo
	if (n < 0 && n == int(n))
		//Si es negativo se devuelve una respuesta con un menos delante del número obtenido con el factorial
		cout << "\t\tEL FACTORIAL DE " << n << " ES:   " << "-" << factorial(n * (-1));
	else
		//Se verifica si es posible sacarle factorial al número
		if (n >= 0 && n == int(n))
			//Si es posible, se imprime la respuesta en pantalla
			cout << "\t\tEL FACTORIAL DE " << n << " ES:   " << factorial(n) << endl;
		else
			//Se le indica el error al usuario ya que el tipo de dato ingresado es incorrecto
			cout << "\a\t\tNO SE PUEDE SACAR EL FACTORIAL DE " << n << endl;
	//Se pausa la pantalla
	system("pause");
	return 0;
}
