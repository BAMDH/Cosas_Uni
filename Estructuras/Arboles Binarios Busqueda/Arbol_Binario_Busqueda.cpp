// Raizbb.cpp : Defines the entry point for the console application.
//

#include "iostream"
#include <stdio.h>

using namespace std;

typedef struct NodoBB
{
	int llave;
	NodoBB* HijoIzquierdo;
	NodoBB* HijoDerecho;
}X;

int contador = 0;

bool verdadero = true;
bool Insertar(NodoBB*& Raiz, int cualllave)
{
	if (Raiz == NULL)
		//Se verifica que la raiz no sea NULL
	{
		//Se crea una nueva raiz de tipo NodoBB
		Raiz = new(NodoBB);
		//Se le asigna a Raiz->llave el valor de cualllave
		Raiz->llave = cualllave;
		//Se inicializan los hijos
		Raiz->HijoIzquierdo = NULL;
		Raiz->HijoDerecho = NULL;
		//Se retorna un true
		return true;
	}
	else
	{
		//Si cualllave es igual a Raiz->llave se retorna un false
		if (cualllave == Raiz->llave) return false;
		else
			//Si cualllave es menor a Raiz->llave, se para como parámetro el Raiz->HijoIzquierdo, retornando la función de manera recursiva
			if (cualllave < Raiz->llave) return Insertar(Raiz->HijoIzquierdo, cualllave);
			//Si cualllave es mayor a Raiz->llave, se para como parámetro el Raiz->HijoDerecho, retornando la función de manera recursiva
			else return Insertar(Raiz->HijoDerecho, cualllave);
	}
}

bool InsertarCh(NodoBB*& Raiz, char cualllave)
{
	if (Raiz == NULL)
	{
		Raiz = new(NodoBB);
		Raiz->llave = cualllave;
		Raiz->HijoIzquierdo = NULL;
		Raiz->HijoDerecho = NULL;
		return true;
	}
	else
	{
		if (cualllave == Raiz->llave) return false;
		else
			if (cualllave < Raiz->llave) return InsertarCh(Raiz->HijoIzquierdo, cualllave);
			else return InsertarCh(Raiz->HijoDerecho, cualllave);
	}
}

void PodarHojas(NodoBB*& Raiz)
//Esta función se encarga de eliminar el arbol, iniciando desde las hojas
{
	if (Raiz != NULL)
	{
		//Se llama la función de manera recursiva, retornando Raiz->HijoIzquierdo y Raiz->HijoDerecho como parámetros
		PodarHojas(Raiz->HijoIzquierdo);
		PodarHojas(Raiz->HijoDerecho);
		//Se indica el elemento borrado
		printf("Borro : %i \n", Raiz->llave);
		//Se elimina la Raiz
		//Se inicializa la Raiz como NULL
		delete(Raiz); Raiz = NULL;
	}
}

void PodarRamas(NodoBB*& Raiz)
{
	if (Raiz != NULL)
	{
		PodarRamas(Raiz->HijoIzquierdo);
		PodarRamas(Raiz->HijoDerecho);
		delete(Raiz); Raiz = NULL;
	}
}
NodoBB* Buscar(NodoBB* Raiz, int cualllave)
//Esta función recibe un valor, el cual debe de buscar en la Raiz
{
	if (Raiz == NULL)
		//Si la Raiz es NULL se retorna NULL
	{
		return NULL;
	}
	else
	{
		//Se verifica cualllave con todas las Raiz->llave, retornando Raiz si esta es igual
		if (cualllave == Raiz->llave) return Raiz;
		else
			//Sino es así, continúa buscando pasando como parámetro Raiz->HijoIzquierdo y Raiz->HijoDerecho, de manera recursiva
			if (cualllave < Raiz->llave) return Buscar(Raiz->HijoIzquierdo, cualllave);
			else return Buscar(Raiz->HijoDerecho, cualllave);
	}
}

void PreOrdenRID(NodoBB* Raiz)
{
	if (Raiz != NULL)
	{
		//Se muestra en pantalla primero la Raiz, luego sus hijos izquierdos y después hijos derechos
		//Se pasan como parámetro Raiz->HijoIzquierdo y Raiz->HijoDerecho, de manera recursiva
		printf("%i  ", Raiz->llave);
		PreOrdenRID(Raiz->HijoIzquierdo);
		PreOrdenRID(Raiz->HijoDerecho);
	}
}
void PreOrdenRIDCh(NodoBB* Raiz)
{
	if (Raiz != NULL)
	{
		printf("%c  ", Raiz->llave);
		PreOrdenRIDCh(Raiz->HijoIzquierdo);
		PreOrdenRIDCh(Raiz->HijoDerecho);
	}
}

void EnOrdenIRD(NodoBB* Raiz)
{
	if (Raiz != NULL)
	{
		// Se muestra en pantalla primero sus hijos izquierdos, luego la Raiz después sus hijos derechos
		//Se pasan como parámetro Raiz->HijoIzquierdo y Raiz->HijoDerecho, de manera recursiva
		EnOrdenIRD(Raiz->HijoIzquierdo);
		printf("%i  ", Raiz->llave);
		EnOrdenIRD(Raiz->HijoDerecho);
	}
}

void EnOrdenIRDCh(NodoBB* Raiz)
{
	if (Raiz != NULL)
	{
		EnOrdenIRDCh(Raiz->HijoIzquierdo);
		printf("%c  ", Raiz->llave);
		EnOrdenIRDCh(Raiz->HijoDerecho);
	}
}

void PosOrdenIDR(NodoBB* Raiz)
{
	if (Raiz != NULL)
	{
		// Se muestra en pantalla primero sus hijos izquierdos, luego sus hijos derechos y por último su Raiz
		//Se pasan como parámetro Raiz->HijoIzquierdo y Raiz->HijoDerecho, de manera recursiva
		PosOrdenIDR(Raiz->HijoIzquierdo);
		PosOrdenIDR(Raiz->HijoDerecho);
		printf("%i  ", Raiz->llave);
	}
}

void PosOrdenIDRCh(NodoBB* Raiz)
{
	if (Raiz != NULL)
	{
		PosOrdenIDRCh(Raiz->HijoIzquierdo);
		PosOrdenIDRCh(Raiz->HijoDerecho);
		printf("%c   ", Raiz->llave);
	}
}

void ContarElementos(NodoBB* Raiz) {
	if (Raiz != NULL) {
		contador++;
		ContarElementos(Raiz->HijoDerecho);
		ContarElementos(Raiz->HijoIzquierdo);
	}
}

bool CompararArboles(NodoBB* Raiz1, NodoBB* Raiz2) {
	if (Raiz1 != NULL && Raiz2 != NULL){
		CompararArboles(Raiz1->HijoIzquierdo, Raiz2->HijoIzquierdo);
		CompararArboles(Raiz1->HijoDerecho, Raiz2->HijoDerecho);
		if (Raiz1->llave != Raiz2->llave) {
			verdadero = false;
			return verdadero;
		}
	}
	if (Raiz1 == NULL && Raiz2 != NULL || Raiz1 != NULL && Raiz2 == NULL) {
		verdadero = false;
		return verdadero;
	}
}

void Clonar(NodoBB*& Raiz1, NodoBB* Raiz2) {
	if (Raiz2 != NULL && Raiz1 != NULL) {
		Raiz1->llave = Raiz2->llave;
		Clonar(Raiz1->HijoIzquierdo, Raiz2->HijoIzquierdo);
		Clonar(Raiz1->HijoDerecho, Raiz2->HijoDerecho);
	}
	if(Raiz2 != NULL && Raiz1 == NULL) {
		Raiz1 = new(NodoBB);
		Raiz1->HijoIzquierdo = NULL;
		Raiz1->HijoDerecho = NULL;
		Raiz1->llave = Raiz2->llave;
		Clonar(Raiz1->HijoIzquierdo, Raiz2->HijoIzquierdo);
		Clonar(Raiz1->HijoDerecho, Raiz2->HijoDerecho);
	}
	if (Raiz2 == NULL && Raiz1 != NULL) {
		PodarRamas(Raiz1);
	}
}

bool EsCompleto(NodoBB* Raiz) {
	if (Raiz != NULL) {
		if ((Raiz->HijoIzquierdo == NULL && Raiz->HijoDerecho != NULL) || (Raiz->HijoIzquierdo != NULL && Raiz->HijoDerecho == NULL)) {
			verdadero = false;
			return verdadero;
		}
		else {
		EsCompleto(Raiz->HijoIzquierdo);
		EsCompleto(Raiz->HijoDerecho);
		}
	}
}

void VisualizarNodos(NodoBB* Raiz) {
	if (Raiz != NULL) {
		VisualizarNodos(Raiz->HijoIzquierdo);
		VisualizarNodos(Raiz->HijoDerecho);
		if (Raiz->HijoIzquierdo == NULL && Raiz->HijoDerecho == NULL) {
			printf("\nUna hoja es: %i", Raiz->llave);
		}
	}
}

void main(int argc, char* argv[])
{
	NodoBB* ArbolEnteros = NULL;
	NodoBB* ArbolEnteros2 = NULL;
	NodoBB* ArbolCaracteres = NULL;

	printf("\nINSERTANDO la secuencia de Elementos : 9, 6, 16, 3, 13, 8, 18 ");
	Insertar(ArbolEnteros, 9);
	Insertar(ArbolEnteros, 6);
	Insertar(ArbolEnteros, 16);
	Insertar(ArbolEnteros, 3);
	Insertar(ArbolEnteros, 13);
	Insertar(ArbolEnteros, 8);
	Insertar(ArbolEnteros, 18);
	printf("\n\nRECORIENDO: ");
	printf("\nElementos en recorrido PreOrden : ");
	PreOrdenRID(ArbolEnteros);

	printf("\nElementos en recorrido EnOrden : ");
	EnOrdenIRD(ArbolEnteros);

	printf("\nElementos en recorrido PosOrden : ");
	PosOrdenIDR(ArbolEnteros);

	printf("\n\nBUSCANDO 1 x 1 : ");
	NodoBB* Aux;
	if ((Aux = Buscar(ArbolEnteros, 18)) != NULL) printf("\nBusco %i y encontro %i ", 18, Aux->llave);
	if ((Aux = Buscar(ArbolEnteros, 8)) != NULL) printf("\nBusco %i y encontro %i ", 8, Aux->llave);
	if ((Aux = Buscar(ArbolEnteros, 13)) != NULL) printf("\nBusco %i y encontro %i ", 13, Aux->llave);
	if ((Aux = Buscar(ArbolEnteros, 3)) != NULL) printf("\nBusco %i y encontro %i ", 3, Aux->llave);
	if ((Aux = Buscar(ArbolEnteros, 16)) != NULL) printf("\nBusco %i y encontro %i ", 16, Aux->llave);
	if ((Aux = Buscar(ArbolEnteros, 6)) != NULL) printf("\nBusco %i y encontro %i ", 6, Aux->llave);
	if ((Aux = Buscar(ArbolEnteros, 9)) != NULL) printf("\nBusco %i y encontro %i ", 9, Aux->llave);

	printf("\n\nBORRANDO TODO ... de las hojas a la Raiz:\n");
	PodarHojas(ArbolEnteros);
	system("pause");
	system("CLS");

	printf("Ejercicio número 3 de la practica de árboles binarios búsqueda\n");
	Insertar(ArbolEnteros, 3);
	Insertar(ArbolEnteros, 7);
	Insertar(ArbolEnteros, 5);
	Insertar(ArbolEnteros, 1);
	Insertar(ArbolEnteros, 6);
	Insertar(ArbolEnteros, 8);
	Insertar(ArbolEnteros, 4);
	Insertar(ArbolEnteros, 2);
	Insertar(ArbolEnteros, 9);
	printf("\nPreOrden:\n");
	PreOrdenRID(ArbolEnteros);
	printf("\nPosOrden:\n");
	PosOrdenIDR(ArbolEnteros);
	printf("\nEnOrden:\n");
	EnOrdenIRD(ArbolEnteros);
	printf("\nPodando arbol:\n");
	PodarHojas(ArbolEnteros);
	system("pause");

	printf("\nEjercicio número 4 de la practica de árboles binarios búsqueda\n");
	system("CLS");
	Insertar(ArbolEnteros, 9);
	Insertar(ArbolEnteros, 5);
	Insertar(ArbolEnteros, 1);
	Insertar(ArbolEnteros, 4);
	Insertar(ArbolEnteros, 3);
	Insertar(ArbolEnteros, 15);
	Insertar(ArbolEnteros, 11);
	Insertar(ArbolEnteros, 14);
	Insertar(ArbolEnteros, 13);
	printf("\nEjercicio a.\n");
	printf("\nPreOrden:\n");
	PreOrdenRID(ArbolEnteros);
	printf("\nEjercicio b.\n");
	printf("\nPosOrden:\n");
	PosOrdenIDR(ArbolEnteros);
	printf("\nPodando arbol:\n");
	PodarHojas(ArbolEnteros);
	system("pause");
	system("CLS");

	printf("Ejercicio número 5 de la practica de árboles binarios búsqueda\n");
	printf("Parte a.");
	Insertar(ArbolEnteros, 9);
	Insertar(ArbolEnteros, 6);
	Insertar(ArbolEnteros, 16);
	Insertar(ArbolEnteros, 3);
	Insertar(ArbolEnteros, 13);
	Insertar(ArbolEnteros, 8);
	Insertar(ArbolEnteros, 18);
	contador = 0;
	ContarElementos(ArbolEnteros);
	printf("La cantidad de elementos son: %i", contador);
	printf("\nPodando arbol:\n");
	PodarHojas(ArbolEnteros);
	system("pause");
	system("CLS");

	printf("Parte b. \n");
	Insertar(ArbolEnteros2, 9);
	Insertar(ArbolEnteros2, 6);
	Insertar(ArbolEnteros2, 16);
	Insertar(ArbolEnteros2, 3);
	Insertar(ArbolEnteros2, 13);
	Insertar(ArbolEnteros2, 8);
	Insertar(ArbolEnteros2, 18);

	Insertar(ArbolEnteros, 9);
	Insertar(ArbolEnteros, 6);
	Insertar(ArbolEnteros, 16);
	Insertar(ArbolEnteros, 3);
	Insertar(ArbolEnteros, 13);
	Insertar(ArbolEnteros, 8);
	Insertar(ArbolEnteros, 18);
	verdadero = true;
	CompararArboles(ArbolEnteros, ArbolEnteros2);
	if (verdadero)
		printf("\nLos arboles son iguales\n");
	else
		printf("\nLos arboles no son iguales\n");
	printf("\nPodando arbol:\n");
	PodarHojas(ArbolEnteros);
	PodarHojas(ArbolEnteros2);
	system("pause");
	system("CLS");

	printf("Parte c.");
	Insertar(ArbolEnteros2, 1);
	Insertar(ArbolEnteros2, 7);
	Insertar(ArbolEnteros2, 10);
	Insertar(ArbolEnteros2, 5);
	Insertar(ArbolEnteros2, 15);
	Insertar(ArbolEnteros2, 2);
	Insertar(ArbolEnteros2, 20);

	Insertar(ArbolEnteros, 9);
	Insertar(ArbolEnteros, 6);
	Insertar(ArbolEnteros, 16);
	Insertar(ArbolEnteros, 3);
	Insertar(ArbolEnteros, 13);
	Insertar(ArbolEnteros, 8);
	Insertar(ArbolEnteros, 18);
	printf("\nArbol al que le realizaremos un clon:\n");
	EnOrdenIRD(ArbolEnteros2);
	printf("\nArbol que sera el clon\n");
	EnOrdenIRD(ArbolEnteros);
	printf("\n");
	Clonar(ArbolEnteros, ArbolEnteros2);
	printf("\nArbol clon:\n");
	EnOrdenIRD(ArbolEnteros);
	printf("\nPodando arboles:\n");
	PodarHojas(ArbolEnteros);
	PodarHojas(ArbolEnteros2);
	system("pause");
	system("CLS");

	printf("Parte d.");
	Insertar(ArbolEnteros, 9);
	Insertar(ArbolEnteros, 6);
	Insertar(ArbolEnteros, 16);
	Insertar(ArbolEnteros, 3);
	Insertar(ArbolEnteros, 13);
	Insertar(ArbolEnteros, 8);
	Insertar(ArbolEnteros, 18);
	verdadero = true;
	EsCompleto(ArbolEnteros);
	if (verdadero)
		printf("El arbol esta completo");
	else
		printf("El arbol no esta completo");
	PodarHojas(ArbolEnteros);
	system("Pause");
	system("CLS");

	printf("Parte e.");
	Insertar(ArbolEnteros, 9);
	Insertar(ArbolEnteros, 6);
	Insertar(ArbolEnteros, 16);
	Insertar(ArbolEnteros, 3);
	Insertar(ArbolEnteros, 13);
	Insertar(ArbolEnteros, 8);
	Insertar(ArbolEnteros, 18);
	VisualizarNodos(ArbolEnteros);
	PodarHojas(ArbolEnteros);
	system("Pause");

	printf("Ejercicio número 6 de la practica de árboles binarios búsqueda\n");
	printf("Insertando secuencias de elementos:\n");
	printf("Numero 1:\n");
	Insertar(ArbolEnteros, 65);
	Insertar(ArbolEnteros, 50);
	Insertar(ArbolEnteros, 23);
	Insertar(ArbolEnteros, 70);
	Insertar(ArbolEnteros, 82);
	Insertar(ArbolEnteros, 68);
	Insertar(ArbolEnteros, 39);
	printf("\nPreOrden:\n");
	PreOrdenRID(ArbolEnteros);
	printf("\nPosOrden:\n");
	PosOrdenIDR(ArbolEnteros);
	printf("\nPodando arbol:\n");
	PodarHojas(ArbolEnteros);
	system("pause");
	system("CLS");

	printf("Numero 2:");
	Insertar(ArbolEnteros, 43);
	Insertar(ArbolEnteros, 58);
	Insertar(ArbolEnteros, 75);
	Insertar(ArbolEnteros, 86);
	Insertar(ArbolEnteros, 65);
	Insertar(ArbolEnteros, 70);
	Insertar(ArbolEnteros, 67);
	Insertar(ArbolEnteros, 73);
	Insertar(ArbolEnteros, 93);
	Insertar(ArbolEnteros, 69);
	Insertar(ArbolEnteros, 25);
	Insertar(ArbolEnteros, 66);
	Insertar(ArbolEnteros, 68);
	Insertar(ArbolEnteros, 47);
	Insertar(ArbolEnteros, 62);
	Insertar(ArbolEnteros, 10);
	Insertar(ArbolEnteros, 60);
	printf("\nPreOrden:\n");
	PreOrdenRID(ArbolEnteros);
	printf("\nPosOrden:\n");
	PosOrdenIDR(ArbolEnteros);
	printf("\nPodando arbol:\n");
	PodarHojas(ArbolEnteros);
	system("pause");
	system("CLS");

	printf("Numero 3:");
	Insertar(ArbolEnteros, 10);
	Insertar(ArbolEnteros, 27);
	Insertar(ArbolEnteros, 29);
	Insertar(ArbolEnteros, 17);
	Insertar(ArbolEnteros, 25);
	Insertar(ArbolEnteros, 21);
	Insertar(ArbolEnteros, 15);
	Insertar(ArbolEnteros, 31);
	Insertar(ArbolEnteros, 13);
	Insertar(ArbolEnteros, 51);
	Insertar(ArbolEnteros, 20);
	Insertar(ArbolEnteros, 24);
	Insertar(ArbolEnteros, 48);
	Insertar(ArbolEnteros, 19);
	Insertar(ArbolEnteros, 60);
	Insertar(ArbolEnteros, 35);
	Insertar(ArbolEnteros, 66);
	printf("\nPreOrden:\n");
	PreOrdenRID(ArbolEnteros);
	printf("\nPosOrden:\n");
	PosOrdenIDR(ArbolEnteros);
	printf("\nPodando arbol:\n");
	PodarHojas(ArbolEnteros);
	system("pause");
	system("CLS");

	printf("Numero 4:");
	Insertar(ArbolEnteros, 10);
	Insertar(ArbolEnteros, 68);
	Insertar(ArbolEnteros, 80);
	Insertar(ArbolEnteros, 27);
	Insertar(ArbolEnteros, 53);
	Insertar(ArbolEnteros, 43);
	Insertar(ArbolEnteros, 21);
	Insertar(ArbolEnteros, 77);
	Insertar(ArbolEnteros, 58);
	Insertar(ArbolEnteros, 63);
	Insertar(ArbolEnteros, 15);
	Insertar(ArbolEnteros, 37);
	Insertar(ArbolEnteros, 41);
	Insertar(ArbolEnteros, 72);
	Insertar(ArbolEnteros, 39);
	Insertar(ArbolEnteros, 95);
	Insertar(ArbolEnteros, 70);
	printf("\nPreOrden:\n");
	PreOrdenRID(ArbolEnteros);
	printf("\nPosOrden:\n");
	PosOrdenIDR(ArbolEnteros);
	printf("\nPodando arbol:\n");
	PodarHojas(ArbolEnteros);
	system("pause");
	system("CLS");

	printf("Ejercicio número 7 de la practica de árboles binarios búsqueda\n");
	printf("a.BinarioNormal(No ordenado)\n");
	InsertarCh(ArbolCaracteres, 'A');
	InsertarCh(ArbolCaracteres, 'B');
	InsertarCh(ArbolCaracteres, 'C');
	InsertarCh(ArbolCaracteres, 'D');
	InsertarCh(ArbolCaracteres, 'E');
	InsertarCh(ArbolCaracteres, 'F');
	InsertarCh(ArbolCaracteres, 'G');
	//En el caso de PreOrden, el resultado es el mismo
	printf("\nPreOrden:");
	PreOrdenRIDCh(ArbolCaracteres);

	//En el caso de EnOrden, el resultado es diferente al mostrado en el ejemplo, pero igual cumole con su función de ordenar
	printf("\nInOrden:");
	EnOrdenIRDCh(ArbolCaracteres);

	//En el caso de PosOrden, el resultado es diferente al mostrado en el ejemplo, pero igual cumole con su función de ordenar
	printf("\nPosOrden:");
	PosOrdenIDRCh(ArbolCaracteres);
	PodarHojas(ArbolCaracteres);


	system("pause");
	system("CLS");
	printf("b. Binario de Busqueda ");
	Insertar(ArbolEnteros, 120);
	Insertar(ArbolEnteros, 87);
	Insertar(ArbolEnteros, 43);
	Insertar(ArbolEnteros, 22);
	Insertar(ArbolEnteros, 65);
	Insertar(ArbolEnteros, 56);
	Insertar(ArbolEnteros, 99);
	Insertar(ArbolEnteros, 93);
	Insertar(ArbolEnteros, 140);
	Insertar(ArbolEnteros, 130);
	Insertar(ArbolEnteros, 135);
	printf("b.Binario de Búsqueda:\n");
	printf("Numero 1:\n");
	printf("PreOrden\n");
	PreOrdenRID(ArbolEnteros);

	printf("\nNumero 2:\n");
	printf("InOrden\n");
	EnOrdenIRD(ArbolEnteros);

	printf("\nNumero 3:\n");
	printf("PostOrden\n");
	PosOrdenIDR(ArbolEnteros);
	printf("\n");
	PodarHojas(ArbolEnteros);
	system("pause");
	system("CLS");

	printf("c. Binario de Busqueda ");
	Insertar(ArbolEnteros, 95);
	Insertar(ArbolEnteros, 80);
	Insertar(ArbolEnteros, 72);
	Insertar(ArbolEnteros, 60);
	Insertar(ArbolEnteros, 82);
	Insertar(ArbolEnteros, 81);
	Insertar(ArbolEnteros, 84);
	Insertar(ArbolEnteros, 100);
	Insertar(ArbolEnteros, 110);
	Insertar(ArbolEnteros, 105);
	printf("c.Binario de Búsqueda:\n");
	printf("Numero 1:\n");
	printf("PreOrden\n");
	PreOrdenRID(ArbolEnteros);

	printf("\nNumero 2:\n");
	printf("InOrden\n");
	EnOrdenIRD(ArbolEnteros);

	printf("\nNumero 3:\n");
	printf("PostOrden\n");
	PosOrdenIDR(ArbolEnteros);
	printf("\n");
	PodarHojas(ArbolEnteros);

	Insertar(ArbolEnteros, 9);
	Insertar(ArbolEnteros, 6);
	Insertar(ArbolEnteros, 16);
	Insertar(ArbolEnteros, 3);
	Insertar(ArbolEnteros, 13);
	Insertar(ArbolEnteros, 8);
	Insertar(ArbolEnteros, 18);
	ContarElementos(ArbolEnteros);
	printf("\nPodando arbol:\n");
	PodarHojas(ArbolEnteros);
	system("pause");
	system("CLS");
}
/*
Documentación

INSERTANDO la secuencia de Elementos : 9, 6, 16, 3, 13, 8, 18

RECORIENDO:
Elementos en recorrido PreOrden : 9  6  3  8  16  13  18
Elementos en recorrido EnOrden : 3  6  8  9  13  16  18
Elementos en recorrido PosOrden : 3  8  6  13  18  16  9

BUSCANDO 1 x 1 :
Busco 18 y encontro 18
Busco 8 y encontro 8
Busco 13 y encontro 13
Busco 3 y encontro 3
Busco 16 y encontro 16
Busco 6 y encontro 6
Busco 9 y encontro 9

BORRANDO TODO ... de las hojas a la Raiz:
Borro : 3
Borro : 8
Borro : 6
Borro : 13
Borro : 18
Borro : 16
Borro : 9
Presione una tecla para continuar . . .

Ejercicio n├║mero 3 de la practica de ├írboles binarios b├║squeda

PreOrden:
3  1  2  7  5  4  6  8  9
PosOrden:
2  1  4  6  5  9  8  7  3
EnOrden:
1  2  3  4  5  6  7  8  9
Podando arbol:
Borro : 2
Borro : 1
Borro : 4
Borro : 6
Borro : 5
Borro : 9
Borro : 8
Borro : 7
Borro : 3
Presione una tecla para continuar . . .


Ejercicio a.

PreOrden:
9  5  1  4  3  15  11  14  13
Ejercicio b.

PosOrden:
3  4  1  5  13  14  11  15  9
Podando arbol:
Borro : 3
Borro : 4
Borro : 1
Borro : 5
Borro : 13
Borro : 14
Borro : 11
Borro : 15
Borro : 9
Presione una tecla para continuar . . .

Ejercicio n├║mero 5 de la practica de ├írboles binarios b├║squeda
Parte a.La cantidad de elementos son: 7
Podando arbol:
Borro : 3
Borro : 8
Borro : 6
Borro : 13
Borro : 18
Borro : 16
Borro : 9
Presione una tecla para continuar . . .

Parte b.

Los arboles son iguales

Podando arbol:
Borro : 3
Borro : 8
Borro : 6
Borro : 13
Borro : 18
Borro : 16
Borro : 9
Borro : 3
Borro : 8
Borro : 6
Borro : 13
Borro : 18
Borro : 16
Borro : 9
Presione una tecla para continuar . . .

Parte c.
Arbol al que le realizaremos un clon:
1  2  5  7  10  15  20
Arbol que sera el clon
3  6  8  9  13  16  18

Arbol clon:
1  2  5  7  10  15  20
Podando arboles:
Borro : 2
Borro : 5
Borro : 20
Borro : 15
Borro : 10
Borro : 7
Borro : 1
Borro : 2
Borro : 5
Borro : 20
Borro : 15
Borro : 10
Borro : 7
Borro : 1
Presione una tecla para continuar . . .

Parte d.El arbol esta completoBorro : 3
Borro : 8
Borro : 6
Borro : 13
Borro : 18
Borro : 16
Borro : 9
Presione una tecla para continuar . . .

Parte e.
Una hoja es: 3
Una hoja es: 8
Una hoja es: 13
Una hoja es: 18Borro : 3
Borro : 8
Borro : 6
Borro : 13
Borro : 18
Borro : 16
Borro : 9
Presione una tecla para continuar . . .

Numero 1:

PreOrden:
65  50  23  39  70  68  82
PosOrden:
39  23  50  68  82  70  65
Podando arbol:
Borro : 39
Borro : 23
Borro : 50
Borro : 68
Borro : 82
Borro : 70
Borro : 65
Presione una tecla para continuar . . .

Numero 2:
PreOrden:
43  25  10  58  47  75  65  62  60  70  67  66  69  68  73  86  93
PosOrden:
10  25  47  60  62  66  68  69  67  73  70  65  93  86  75  58  43
Podando arbol:
Borro : 10
Borro : 25
Borro : 47
Borro : 60
Borro : 62
Borro : 66
Borro : 68
Borro : 69
Borro : 67
Borro : 73
Borro : 70
Borro : 65
Borro : 93
Borro : 86
Borro : 75
Borro : 58
Borro : 43
Presione una tecla para continuar . . .

Numero 3:
PreOrden:
10  27  17  15  13  25  21  20  19  24  29  31  51  48  35  60  66
PosOrden:
13  15  19  20  24  21  25  17  35  48  66  60  51  31  29  27  10
Podando arbol:
Borro : 13
Borro : 15
Borro : 19
Borro : 20
Borro : 24
Borro : 21
Borro : 25
Borro : 17
Borro : 35
Borro : 48
Borro : 66
Borro : 60
Borro : 51
Borro : 31
Borro : 29
Borro : 27
Borro : 10
Presione una tecla para continuar . . .

Numero 4:
PreOrden:
10  68  27  21  15  53  43  37  41  39  58  63  80  77  72  70  95
PosOrden:
15  21  39  41  37  43  63  58  53  27  70  72  77  95  80  68  10
Podando arbol:
Borro : 15
Borro : 21
Borro : 39
Borro : 41
Borro : 37
Borro : 43
Borro : 63
Borro : 58
Borro : 53
Borro : 27
Borro : 70
Borro : 72
Borro : 77
Borro : 95
Borro : 80
Borro : 68
Borro : 10
Presione una tecla para continuar . . .

Ejercicio n├║mero 7 de la practica de ├írboles binarios b├║squeda
a.BinarioNormal(No ordenado)

PreOrden:A  B  C  D  E  F  G
InOrden:A  B  C  D  E  F  G
PosOrden:G   F   E   D   C   B   A   Borro : 71
Borro : 70
Borro : 69
Borro : 68
Borro : 67
Borro : 66
Borro : 65
Presione una tecla para continuar . . .

b. Binario de Busqueda b.Binario de B├║squeda:
Numero 1:
PreOrden
120  87  43  22  65  56  99  93  140  130  135
Numero 2:
InOrden
22  43  56  65  87  93  99  120  130  135  140
Numero 3:
PostOrden
22  56  65  43  93  99  87  135  130  140  120
Borro : 22
Borro : 56
Borro : 65
Borro : 43
Borro : 93
Borro : 99
Borro : 87
Borro : 135
Borro : 130
Borro : 140
Borro : 120
Presione una tecla para continuar . . .

c. Binario de Busqueda c.Binario de B├║squeda:
Numero 1:
PreOrden
95  80  72  60  82  81  84  100  110  105
Numero 2:
InOrden
60  72  80  81  82  84  95  100  105  110
Numero 3:
PostOrden
60  72  81  84  82  80  105  110  100  95
Borro : 60
Borro : 72
Borro : 81
Borro : 84
Borro : 82
Borro : 80
Borro : 105
Borro : 110
Borro : 100
Borro : 95

Podando arbol:
Borro : 3
Borro : 8
Borro : 6
Borro : 13
Borro : 18
Borro : 16
Borro : 9
Presione una tecla para continuar . . .
*/