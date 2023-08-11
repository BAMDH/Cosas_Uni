#include <iostream>
#include <stdio.h>
using namespace std;
bool BitEncendido(int Simbolo, int cual)
{
	int Mascara = 1;
	Mascara = Mascara << cual;
	if ((Simbolo & Mascara) == 0)
		return false; // Da 0 lo que significa que la posicion cual estaba en 0
	else return true; // Da <>0 lo que significa que la posicion cual estaba en 1
}
bool BitApagado(int Simbolo, int cual)
{
	int Mascara = 1;
	Mascara = Mascara << cual;
	if ((Simbolo & Mascara) == 0)
		return true; // Da 0 lo que significa que la posicion cual estaba en 0
	else return false; // Da <>0 lo que significa que la posicion cual estaba en 1
}
void EncenderBit(int& Simbolo, int cual)
{
	int Mascara = 1;
	Mascara = Mascara << cual;
	Simbolo = Simbolo | Mascara;
}
void ApagarBit(int& Simbolo, int cual)
{
	int Mascara = 1;
	Mascara = Mascara << cual;
	Mascara = ~Mascara;
	Simbolo = Simbolo & Mascara;
}
void PinteBits(int Simbolo)
{
	int Uno = 1;
	int Cero = 0;
	for (int i = 7; i >= 0; --i)
	{
		if (BitEncendido(Simbolo, i))
			printf("%d ", Uno);
		else
			printf("%d ", Cero);
	}
	printf("\n");
}
void BinarioDecimal(int Simbolo)
{
	int resultado = 0;
	int exponente = 0;
	printf("%i", Simbolo);
	printf("\n");
	for (int i = 0; i <= 7; i++)
	{
		if (BitEncendido(Simbolo, i))
			resultado = resultado + 1 * pow(2, exponente);
		exponente++;
	}
	printf("Numero binario con su valor decimal: %i \n", resultado);
	printf("\n");
}
void InvertirBit(int& Simbolo, int cual)
//Se le realizó una modificación al código original ya que no nos funcionaba
{
	if (BitEncendido(Simbolo, cual)) {
		ApagarBit(Simbolo, cual);
	}
	else{
		EncenderBit(Simbolo, cual);
	}
}

void InvertirXPosicion(int& Simbolo) {
	int Mascara = Simbolo;
	for (int i = 0; 7 >= i; i++) {
		if (BitEncendido(Mascara, 7 - i)) {
			EncenderBit(Simbolo, i);
		}
		else {
			ApagarBit(Simbolo, i);
		}
	}
}

int main(int argc, char* argv[])
{
	int Prueba = 0;
	int j;
	//Corrida de prueba del código
	for (j = 0; j <= 7; j++)
	{
		printf("%s \n", "------------------");
		EncenderBit(Prueba, j); // Enciende el Bit en la posicion "j"
		printf("%d :", Prueba); PinteBits(Prueba);
		printf("%s \n", "------------------");
		ApagarBit(Prueba, j); // Apaga el Bit en la posicion "j"
		printf("%d :", Prueba); PinteBits(Prueba);
	}
	system("pause");
	system("CLS");
	//Ejercicio 2.2.b Invertir x valor
	printf("Valor que vamos a invertir x valor: ");
	for (j = 0; j <= 7; j++) {
		if (j % 2 == 0) {
			EncenderBit(Prueba, j);
		}
	}
	PinteBits(Prueba);
	system("pause");
	printf("Valor inverso x valor: ");
	for (j = 0; j <= 7; j++) {
		InvertirBit(Prueba, j);
	}
	PinteBits(Prueba);
	//Ejercicio 2.2.c Conversión de binario a decimal
	printf("Valor decimal del numero binario es:");
	BinarioDecimal(Prueba);
	printf("Valor que vamos a invertir por posicion:");
	PinteBits(Prueba);
	//Ejercicio 2.2.a Invertir por posición
	InvertirXPosicion(Prueba);
	printf("Valor inverso x posicion: ");
	PinteBits(Prueba);
	return 0;
}
