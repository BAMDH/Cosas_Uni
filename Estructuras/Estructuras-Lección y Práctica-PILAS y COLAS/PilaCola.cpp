// Pila_Cola.cpp: define el punto de entrada de la aplicación de consola.
//
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <iostream>


using namespace std;
//******************************************
typedef struct TArticulo
/*
Se crea un nuevo tipo de dato TArticulo que tiene forma de estructura
Tiene 5 campos, los primeros 4 almacenan los datos respectivos a sus nombres
El quinto elemento es un puntero al tipo TArticulo, que solo puede apuntar a elementos de tipo TArticulo
*/
{
	int Codigo;
	char Nombre[20];
	int Disponible;
	float Precio;
	TArticulo* Siguiente;
}*PtrTArticulo; // Se crea el puntero PtrTArticulo que permite apuntar hacia cosas de tipo TArticulo


//******************************************

int i;  //parametro global


void InicializarInventario(PtrTArticulo& Lista)
/*
Se recibe una lista que es un parámetro por referencia que tiene la forma de tipo PtrTArticulo
*/
{
	//Se inicializa Lista en NULL, por lo que abarrotes estará apuntando a NULL ya que Abarrotes = Lista
	Lista = NULL;
}
void DestruirInventario(PtrTArticulo& Lista)
{
	//Se declara una variable local, llamadqa Aux de tipo PtrTArticulo
	PtrTArticulo Aux;
	//Aux va a ponerse al inicio a apuntar a donde apunta la lista
	Aux = Lista;
	//Se crea un ciclo while que termine cuando el Aux sea igual al NULL
	while (Aux != NULL)
	{
		//Se empiezan a eliminar los elementos empezando desde el primer elemento
		//Se le asigna a lista el siguientre valor de la lista
		Lista = Lista->Siguiente;
		//Se elimina el Aux que apunta al primer elemento de la lista
		delete(Aux);
		//Se le asigna a Aux el valor de la Lista
		Aux = Lista;
	}
}
PtrTArticulo CrearArticulo(int NCodigo, int NDisponible, float NPrecio)
//PtrTArticulo CrearArticulo recibe tres punteros numéricos
{
	//Se crea una variable anónima la cual contenga la información de Pieza
	PtrTArticulo Pieza = new(TArticulo);
	//se crea un arreglo de tipo char con una capacidad de 5 caracteres
	char buffer[5];

	//Se llenan las variables del TArticulo, recibiendo el codigo(identificado con NCodigo), su 
	//disponibilidad (identificada con NDiponible) y su precio (identificado con NPrecio).
	Pieza->Codigo = NCodigo;
	Pieza->Disponible = NDisponible;
	Pieza->Precio = NPrecio;

	strcpy_s(Pieza->Nombre, "Pieza");
	_itoa_s(NCodigo, buffer, 10);
	strcat_s(Pieza->Nombre, buffer);
	//strcpy_s, _itoa_s y strcat_s se encargan de concatenar los datos del nombre de la pieza junto con el codigo

	Pieza->Siguiente = NULL;
	//Por cada elemento nuevo creado, el puntero Siguiente se especifica como NULL porque siempre el último elemento de la
	//lista debe apuntar a un NULL para que en caso de un recorrido, se tenga conocimiento de cuando se llegó al final
	return Pieza;
	//Se retorna la pieza
}
void AgregarInicioInventario(PtrTArticulo& Lista, PtrTArticulo& Nuevo)
{
	//El elemento siguiente del nuevo va hacer igual a la lista
	Nuevo->Siguiente = Lista;
	//Se le asigna a lista (que estaba vacía) el valor de la variable Nuevo
	Lista = Nuevo;
}
void AgregarFinalInventario(PtrTArticulo& Lista, PtrTArticulo& Nuevo)
{
	//Se declara una variable local, llamadqa Aux de tipo PtrTArticulo
	PtrTArticulo Aux;
	//Aux va a ponerse al inicio a apuntar a donde apunta la lista
	Aux = Lista;
	/*
	Se crea un ciclo for que termine cuando Aux sea igual a NULL
	Se crea un ciclo while que termine cuando el siguiente Aux sea igual a NULL
	A diferencia de ListarInventario, este para un paso antes para poder agregar un elemento al final de la lista
	*/
	if (Aux != NULL)
	{
		//Se produce un ciclo siempre que el elemento siguiente del Aux es diferente a nulo
		while (Aux->Siguiente != NULL)
		{
			//Se le da el valor del elemento siguiente al Aux
			Aux = Aux->Siguiente;
		}
		//Se iguala el Aux siguiente a Nuevo
		Aux->Siguiente = Nuevo;
	}
	else
	{
		//Se iguala Lista a Nuevo
		Lista = Nuevo;
	}


}
void ListarInventario(PtrTArticulo& Lista)
//Subrutina que se comporta como un procedimiento que no devuelve nada, además recibe a Lista como parámetro por referencia
{
	//Se inicializa un contador en 1
	int Contador = 1;
	//Se declara una variable local, llamadqa Aux de tipo PtrTArticulo
	PtrTArticulo Aux;
	//Aux va a ponerse al inicio a apuntar a donde apunta la lista
	Aux = Lista;
	//Se crea un ciclo while que no parará hasta que Aux no se salga de rango, no sea igual a NULL
	while (Aux != NULL)
	{
		//Se imprime el contador
		printf(" %d ", Contador);
		//Se imprime el codigo
		printf("%d ", Aux->Codigo);
		//Se imprime el nombre
		printf("%s ", Aux->Nombre);
		//Se imprime la disponibilidad
		printf(" %d ", Aux->Disponible);
		//Se imprime el precio
		printf(" %f\n ", Aux->Precio);
		//El Aux se ubica en el siguiente elemento, puede darse la opción de que apunte a NULL
		Aux = Aux->Siguiente;
		//Se incrementa el contador
		Contador++;
	}
}

PtrTArticulo BuscarArticulo(PtrTArticulo& Lista, int cual)
{
	//Se retorna la lista
	return Lista;
}


void GuardarInventario(PtrTArticulo Lista) {
	/*
	Esta función recibe el/los elementos de la lista y los escribe o coloca en un archivo de texto llamado ARCHIVO.txt
	*/
	FILE* archivo;
	//Se abre el archivo con el nombre indicado
	fopen_s(&archivo, "ARCHIVO.txt", "w+");
	//Se ejecuta si el NULL es igual al archivo
	if (NULL == archivo) {
		//Se le reporta el error al usuario
		printf("No se pudo abrir el archivo.");
	}
	else {
		PtrTArticulo AUX = Lista;
		//Durante el corrido del while, se anotará la información que se desea en el archivo de texto usando el fprintf
		while (AUX != NULL) {
			//Se escribe en el archivo el codigo del Aux
			fprintf(archivo, "%i\n", AUX->Codigo);
			//Se escribe en el archivo el nombre del Aux
			fprintf(archivo, "%s\n", AUX->Nombre);
			//Se escribe en el archivo el disponible del Aux
			fprintf(archivo, "%i\n", AUX->Disponible);
			//Se escribe en el archivo el precio del Aux
			fprintf(archivo, "%f\n", AUX->Precio);
			//Se iguala el Aux a su siguiente valor
			AUX = AUX->Siguiente;
		}


	}
	//Se cierra el archivo
	fclose(archivo);
}
void CargarInventario(PtrTArticulo& Lista) {
	/*
	Esta función se encarga de cargar a la pantalla los datos ubicados en el archivo de texto y mostrarselo al usuarion
	*/
	PtrTArticulo Nuevo;
	FILE* archivo;
	//Se abre el archivo en modo lectura
	fopen_s(&archivo, "ARCHIVO.txt", "r");
	//Se produce el if si el archivo es igual a NULL
	if (NULL == archivo) {
		//Se le indica el error al usuario
		printf("No se pudo abrir el archivo");
	}
	else {
		while (!feof(archivo)) {
			//Se le asigna un nuevo elemento de tipo TArticulo al Nuevo
			Nuevo = new(TArticulo);
			//Se establece un arreglo de 20 caracteres llamado Cadena
			char Cadena[20];
			//Se busca el codigo
			fscanf_s(archivo, "%i\n", &Nuevo->Codigo);
			//Se busca la cadena
			fscanf_s(archivo, "%s\n", Cadena, 20);
			//Se busca la disponibilidad
			fscanf_s(archivo, "%i\n", &Nuevo->Disponible);
			//Se busca precio
			fscanf_s(archivo, "%f\n", &Nuevo->Precio);
			strcpy_s(Nuevo->Nombre, Cadena);
			//Se le asigna al siguiente valor del Nuevo el valor de NULL
			Nuevo->Siguiente = NULL;
			//Se colocar el elemento al final de la lista
			AgregarFinalInventario(Lista, Nuevo);

		}
	}
	//Se cierra el archivo
	fclose(archivo);
}

//La función coloca los elementos dejando el primer elemento de la lista como último y el último como primero
void Push(PtrTArticulo& Lista, PtrTArticulo Nuevo) {
	//Se hace llamado a la función AgregarInicioInventario para colocar los elementos al principo de la lista
	AgregarInicioInventario(Lista, Nuevo);
}
PtrTArticulo Top(PtrTArticulo& Lista) {
	//Se busca el valor del primer elemento de la lista, pero solo su referencia
	//Se retorna la lista
	return Lista;
}
PtrTArticulo Pop(PtrTArticulo& Lista) {
	//Se establece una variable Aux que sea igual a la Lista
	PtrTArticulo Aux = Lista;
	//Se le asigna a la lista el siguiente elemento de la lista
	Lista = Lista->Siguiente;
	//Se le asigna al siguiente valor del Aux un valor NULL
	Aux->Siguiente = NULL;
	//Se retorna el Aux
	return Aux;
}
void Encolar(PtrTArticulo& Lista, PtrTArticulo Nuevo) {
	//Se colocan los elementos al final de la lista, por lo que el primer elemento queda como primero y el último como último
	AgregarFinalInventario(Lista, Nuevo);
}

PtrTArticulo Primero(PtrTArticulo& Lista) {
	//Se hace un llamado al primer elemento de la lista mediante un return
	return Lista;
}
PtrTArticulo Descolar(PtrTArticulo& Lista) {
	//Se establece una variable Aux que sea igual a la Lista
	PtrTArticulo Aux = Lista;
	//Se le asigna a la lista el siguiente elemento de la lista
	Lista = Lista->Siguiente;
	//Se le asigna al siguiente valor del Aux un valor NULL
	Aux->Siguiente = NULL;
	//Se retorna el Aux
	return Aux;
}







void main(int argc, char* argv[])
{
	//************************************************************************
	PtrTArticulo Pila_Llantas;
	PtrTArticulo Cola_Pasajeros; // de Bus
	PtrTArticulo Nuevo;
	int i;

	//************************************************************************	

	//Se inicializa InicializarInventario de Pila_Llantas y Cola_Pasajeros
	InicializarInventario(Pila_Llantas);
	InicializarInventario(Cola_Pasajeros);
	//Se crea una variable opc que almacena la respuesta del usuario
	int opc;
	//Se crea una variable menu de tipo bool inicializada en true
	bool menu = true;
	//Se crea un ciclo que termina si menu el false
	while (menu) {
		//Se limpia la pantalla
		system("CLS");
		//Se imprime el menú junto con las opciones para el usuario
		cout << " ********* Menu de pila *********" << endl << endl;
		cout << "  1. Usar Pila" << endl;
		cout << "  2. Usar cola" << endl;
		cout << "  3. Salir" << endl;
		cout << endl << "	Digite la opcion: ";
		//Se le pide la respuesta al usuario mediante el cin
		//Se le asigna el valor del cin a opc
		cin >> opc;
		switch (opc) {
			//Se crea un switch que revisa los valores del opc
		case 1: {
			//Se crea una variable opc que almacena la respuesta del usuario
			int opc;
			//Se crea una variable menu de tipo bool inicializada en true
			bool menu = true;
			//Se crea un ciclo que termina si menu el false
			while (menu) {
				//Se limpia la pantalla
				system("CLS");
				cout << " ********* Submenu de pila *********" << endl << endl;
				cout << "  1. Push de 100 elementos" << endl;
				cout << "  2. Top" << endl;
				cout << "  3. Pop" << endl;
				cout << "  4. Salir" << endl;
				cout << endl << "	Digite la opcion: ";
				//Se le pide la respuesta al usuario mediante el cin
				//Se le asigna el valor del cin a opc
				cin >> opc;
				switch (opc) {
					//Se crea un switch que revisa los valores del opc
				case 1: {// INSERTANDO CON PUSH
					//Se limpia la pantalla
					system("CLS");
					//Se imprime un mensaje en la pantalla
					cout << "Haciendo Push de 100 elementos" << endl;

					for (i = 1; i <= 100; i++)
						//Se crea un ciclo que cree 100 nuevos valores del 0 al 100
					{
						Nuevo = CrearArticulo(i, i, i);
						//Se colocan los elementos en Pila mediante la función Push
						Push(Pila_Llantas, Nuevo);
						cout << "Se acaba de hacer PUSH, el Top de la Pila ahora es: " << endl << Top(Pila_Llantas)->Codigo << endl;
					}
					//Se pausa la pantalla
					system("pause");
					//Se "rompe" el ciclo
					break;
				}
				case 2: {// PREGUNTANDO POR EL TOP
					system("CLS");
					if (Top(Pila_Llantas) != NULL)
						cout << "El Top de la Pila es: " << endl << Top(Pila_Llantas)->Codigo << endl;
					else
						cout << "El Top de la Pila es: NULO " << endl;

					system("pause");
					//Se "rompe" el ciclo
					break;
				}
				case 3: { //SACANDO CON POP
					//Se limpia la pantalla
					system("CLS");
					PtrTArticulo Aux = Pop(Pila_Llantas);
					if (Top(Pila_Llantas) != NULL)
						cout << "Se acaba de hacer POP, el Top de la Pila ahora es: " << endl << Top(Pila_Llantas)->Codigo << endl;
					else
						cout << "Se acaba de hacer POP, el Top de la Pila ahora es:  NULO " << endl;
					//Se pausa la pantalla
					system("pause");
					//Se "rompe" el ciclo
					break;
				}
				case 4: {// SALIENDO Y DEVOLVIENDO MEMORIA DINAMICA
					//Se limpia la pantalla
					system("CLS");
					//Se le asigna al menu al menu un valor false
					menu = false;
					//Se limpia la pantalla
					system("CLS");
					cout << "Liberando memoria ... " << endl << endl << endl; 
					//Se destruye el inventario
					DestruirInventario(Pila_Llantas);
					//Se pausa la pantalla
					system("Pause");
					//Se "rompe" el ciclo
					break;
				}
				}
			}
			//Se pausa la pantalla
			system("pause");
			//Se "rompe" el ciclo
			break;
		}
		case 2: {
			int opc;
			//Se llama la variable opc de tipo int
			bool menu = true;
			//Se le asigna al menu de tipo bool un valor true
			while (menu) {
				//Se limpia la pantalla
				system("CLS");
				//Se imprime el submenu al usuario
				cout << " ********* Submenu de cola *********" << endl << endl;
				cout << "  1. Encolar" << endl;
				cout << "  2. Primero" << endl;
				cout << "  3. Descolar" << endl;
				cout << "  4. Salir" << endl;
				cout << endl << "	Digite la opcion: ";
				//Se guarda la respuesta del usuario
				cin >> opc;
				//Se verifica el contenido del opc
				switch (opc) {
				case 1: {// 
					//Se limpia la pantalla
					system("CLS");
					//Se imprime un mensaje en pantalla
					cout << "Haciendo Encolar de 50 elementos" << endl;

					for (i = 1; i <= 50; i++)
						// Se crea un ciclo que cree 100 nuevos valores del 0 al 50
					{
						//Se le asigna a Nuevo el valor del nuevo articulo
						Nuevo = CrearArticulo(i, i, i);
						//Se colocan los elementos en Cola mediante la función Encolar
						Encolar(Cola_Pasajeros, Nuevo);
						//Se limpia la pantalla
						system("CLS");
						//Se imprime un mensaje en pantalla
						cout << "Se acaba de hacer ENCOLAR al final de la Cola de : " << Nuevo->Codigo << endl;
					}
					//Se pausa el programa
					system("pause");
					//Se "rompe" el ciclo
					break;
				}
				case 2: {// PRIMERO
					system("CLS");
					cout << "El primero que va a ser atendido de la cola es : " << Primero(Cola_Pasajeros)->Codigo << endl;
					//Se pausa el programa
					system("pause");
					//Se "rompe" el ciclo
					break;
				}
				case 3: {// DESCOLAR
					//Se limpia la pantalla
					system("CLS");

					PtrTArticulo Aux = Descolar(Cola_Pasajeros);

					//Se verifica que Primero sea diferente o igual a NULL
					if (Primero(Cola_Pasajeros) != NULL)
						//Se imprime in mensaje indicando el primer valor de la lista
						cout << "Se acaba de hacer DESCOLAR, el nuevo primero  de la Cola ahora es: " << endl << Primero(Cola_Pasajeros)->Codigo << endl;
					else
						// Se imprime in mensaje indicando el primer valor de la lista
						cout << "Se acaba de hacer DESCOLAR, el primero de la Cola ahora es:  NULO " << endl;
					//Se pausa el programa
					system("pause");
					//Se "rompe" el ciclo
					break;
				}
				case 4: {// SALIENDO Y DEVOLVIENDO MEMORIA DINAMICA
					//Se le asigna a la pantalla un valor de false
					menu = false;
					//Se limpia la pantalla
					system("CLS");
					//Se imprime un mensaje
					cout << "Liberando memoria ... " << endl << endl << endl;
					//Se destruye el inventario para liberar la memoria
					DestruirInventario(Cola_Pasajeros);
					//Se pausa el programa
					system("Pause");
					//Se "rompe" el ciclo
					break;
				}
				}
			}
			//Se pausa el programa
			system("pause");
			//Se "rompe" el ciclo
			break;
		}
		case 3: {
			//Se le asigna al menu un valor de false
			menu = false;
			//Se limpia la pantalla
			system("CLS");
			//Se imprimen mensajes para el usuario
			cout << "Opcion 5: saliendo . . ." << endl << endl << endl;
			cout << "ADIOS!!!" << endl; 
			//Se pausa el programa
			system("Pause");
			//Se "rompe" el ciclo
			break;
		}
		}
	}
}
¨/*
 Prueba 1: Push de 100 elementos
 Haciendo Push de 100 elementos
Se acaba de hacer PUSH, el Top de la Pila ahora es:
1
Se acaba de hacer PUSH, el Top de la Pila ahora es:
2
Se acaba de hacer PUSH, el Top de la Pila ahora es:
3
Se acaba de hacer PUSH, el Top de la Pila ahora es:
4
Se acaba de hacer PUSH, el Top de la Pila ahora es:
5
Se acaba de hacer PUSH, el Top de la Pila ahora es:
6
Se acaba de hacer PUSH, el Top de la Pila ahora es:
7
Se acaba de hacer PUSH, el Top de la Pila ahora es:
8
Se acaba de hacer PUSH, el Top de la Pila ahora es:
9
Se acaba de hacer PUSH, el Top de la Pila ahora es:
10
Se acaba de hacer PUSH, el Top de la Pila ahora es:
11
Se acaba de hacer PUSH, el Top de la Pila ahora es:
12
Se acaba de hacer PUSH, el Top de la Pila ahora es:
13
Se acaba de hacer PUSH, el Top de la Pila ahora es:
14
Se acaba de hacer PUSH, el Top de la Pila ahora es:
15
Se acaba de hacer PUSH, el Top de la Pila ahora es:
16
Se acaba de hacer PUSH, el Top de la Pila ahora es:
17
Se acaba de hacer PUSH, el Top de la Pila ahora es:
18
Se acaba de hacer PUSH, el Top de la Pila ahora es:
19
Se acaba de hacer PUSH, el Top de la Pila ahora es:
20
Se acaba de hacer PUSH, el Top de la Pila ahora es:
21
Se acaba de hacer PUSH, el Top de la Pila ahora es:
22
Se acaba de hacer PUSH, el Top de la Pila ahora es:
23
Se acaba de hacer PUSH, el Top de la Pila ahora es:
24
Se acaba de hacer PUSH, el Top de la Pila ahora es:
25
Se acaba de hacer PUSH, el Top de la Pila ahora es:
26
Se acaba de hacer PUSH, el Top de la Pila ahora es:
27
Se acaba de hacer PUSH, el Top de la Pila ahora es:
28
Se acaba de hacer PUSH, el Top de la Pila ahora es:
29
Se acaba de hacer PUSH, el Top de la Pila ahora es:
30
Se acaba de hacer PUSH, el Top de la Pila ahora es:
31
Se acaba de hacer PUSH, el Top de la Pila ahora es:
32
Se acaba de hacer PUSH, el Top de la Pila ahora es:
33
Se acaba de hacer PUSH, el Top de la Pila ahora es:
34
Se acaba de hacer PUSH, el Top de la Pila ahora es:
35
Se acaba de hacer PUSH, el Top de la Pila ahora es:
36
Se acaba de hacer PUSH, el Top de la Pila ahora es:
37
Se acaba de hacer PUSH, el Top de la Pila ahora es:
38
Se acaba de hacer PUSH, el Top de la Pila ahora es:
39
Se acaba de hacer PUSH, el Top de la Pila ahora es:
40
Se acaba de hacer PUSH, el Top de la Pila ahora es:
41
Se acaba de hacer PUSH, el Top de la Pila ahora es:
42
Se acaba de hacer PUSH, el Top de la Pila ahora es:
43
Se acaba de hacer PUSH, el Top de la Pila ahora es:
44
Se acaba de hacer PUSH, el Top de la Pila ahora es:
45
Se acaba de hacer PUSH, el Top de la Pila ahora es:
46
Se acaba de hacer PUSH, el Top de la Pila ahora es:
47
Se acaba de hacer PUSH, el Top de la Pila ahora es:
48
Se acaba de hacer PUSH, el Top de la Pila ahora es:
49
Se acaba de hacer PUSH, el Top de la Pila ahora es:
50
Se acaba de hacer PUSH, el Top de la Pila ahora es:
51
Se acaba de hacer PUSH, el Top de la Pila ahora es:
52
Se acaba de hacer PUSH, el Top de la Pila ahora es:
53
Se acaba de hacer PUSH, el Top de la Pila ahora es:
54
Se acaba de hacer PUSH, el Top de la Pila ahora es:
55
Se acaba de hacer PUSH, el Top de la Pila ahora es:
56
Se acaba de hacer PUSH, el Top de la Pila ahora es:
57
Se acaba de hacer PUSH, el Top de la Pila ahora es:
58
Se acaba de hacer PUSH, el Top de la Pila ahora es:
59
Se acaba de hacer PUSH, el Top de la Pila ahora es:
60
Se acaba de hacer PUSH, el Top de la Pila ahora es:
61
Se acaba de hacer PUSH, el Top de la Pila ahora es:
62
Se acaba de hacer PUSH, el Top de la Pila ahora es:
63
Se acaba de hacer PUSH, el Top de la Pila ahora es:
64
Se acaba de hacer PUSH, el Top de la Pila ahora es:
65
Se acaba de hacer PUSH, el Top de la Pila ahora es:
66
Se acaba de hacer PUSH, el Top de la Pila ahora es:
67
Se acaba de hacer PUSH, el Top de la Pila ahora es:
68
Se acaba de hacer PUSH, el Top de la Pila ahora es:
69
Se acaba de hacer PUSH, el Top de la Pila ahora es:
70
Se acaba de hacer PUSH, el Top de la Pila ahora es:
71
Se acaba de hacer PUSH, el Top de la Pila ahora es:
72
Se acaba de hacer PUSH, el Top de la Pila ahora es:
73
Se acaba de hacer PUSH, el Top de la Pila ahora es:
74
Se acaba de hacer PUSH, el Top de la Pila ahora es:
75
Se acaba de hacer PUSH, el Top de la Pila ahora es:
76
Se acaba de hacer PUSH, el Top de la Pila ahora es:
77
Se acaba de hacer PUSH, el Top de la Pila ahora es:
78
Se acaba de hacer PUSH, el Top de la Pila ahora es:
79
Se acaba de hacer PUSH, el Top de la Pila ahora es:
80
Se acaba de hacer PUSH, el Top de la Pila ahora es:
81
Se acaba de hacer PUSH, el Top de la Pila ahora es:
82
Se acaba de hacer PUSH, el Top de la Pila ahora es:
83
Se acaba de hacer PUSH, el Top de la Pila ahora es:
84
Se acaba de hacer PUSH, el Top de la Pila ahora es:
85
Se acaba de hacer PUSH, el Top de la Pila ahora es:
86
Se acaba de hacer PUSH, el Top de la Pila ahora es:
87
Se acaba de hacer PUSH, el Top de la Pila ahora es:
88
Se acaba de hacer PUSH, el Top de la Pila ahora es:
89
Se acaba de hacer PUSH, el Top de la Pila ahora es:
90
Se acaba de hacer PUSH, el Top de la Pila ahora es:
91
Se acaba de hacer PUSH, el Top de la Pila ahora es:
92
Se acaba de hacer PUSH, el Top de la Pila ahora es:
93
Se acaba de hacer PUSH, el Top de la Pila ahora es:
94
Se acaba de hacer PUSH, el Top de la Pila ahora es:
95
Se acaba de hacer PUSH, el Top de la Pila ahora es:
96
Se acaba de hacer PUSH, el Top de la Pila ahora es:
97
Se acaba de hacer PUSH, el Top de la Pila ahora es:
98
Se acaba de hacer PUSH, el Top de la Pila ahora es:
99
Se acaba de hacer PUSH, el Top de la Pila ahora es:
100
Presione una tecla para continuar . . .


 Prueba 2: Top
El Top de la Pila es:
100
Presione una tecla para continuar . . .

 Prueba 3: Pop
Se acaba de hacer POP, el Top de la Pila ahora es:
99
Presione una tecla para continuar . . .

 Prueba 4: Encolar
Haciendo Encolar de 50 elementos
Se acaba de hacer ENCOLAR al final de la Cola de : 1
Se acaba de hacer ENCOLAR al final de la Cola de : 2
Se acaba de hacer ENCOLAR al final de la Cola de : 3
Se acaba de hacer ENCOLAR al final de la Cola de : 4
Se acaba de hacer ENCOLAR al final de la Cola de : 5
Se acaba de hacer ENCOLAR al final de la Cola de : 6
Se acaba de hacer ENCOLAR al final de la Cola de : 7
Se acaba de hacer ENCOLAR al final de la Cola de : 8
Se acaba de hacer ENCOLAR al final de la Cola de : 9
Se acaba de hacer ENCOLAR al final de la Cola de : 10
Se acaba de hacer ENCOLAR al final de la Cola de : 11
Se acaba de hacer ENCOLAR al final de la Cola de : 12
Se acaba de hacer ENCOLAR al final de la Cola de : 13
Se acaba de hacer ENCOLAR al final de la Cola de : 14
Se acaba de hacer ENCOLAR al final de la Cola de : 15
Se acaba de hacer ENCOLAR al final de la Cola de : 16
Se acaba de hacer ENCOLAR al final de la Cola de : 17
Se acaba de hacer ENCOLAR al final de la Cola de : 18
Se acaba de hacer ENCOLAR al final de la Cola de : 19
Se acaba de hacer ENCOLAR al final de la Cola de : 20
Se acaba de hacer ENCOLAR al final de la Cola de : 21
Se acaba de hacer ENCOLAR al final de la Cola de : 22
Se acaba de hacer ENCOLAR al final de la Cola de : 23
Se acaba de hacer ENCOLAR al final de la Cola de : 24
Se acaba de hacer ENCOLAR al final de la Cola de : 25
Se acaba de hacer ENCOLAR al final de la Cola de : 26
Se acaba de hacer ENCOLAR al final de la Cola de : 27
Se acaba de hacer ENCOLAR al final de la Cola de : 28
Se acaba de hacer ENCOLAR al final de la Cola de : 29
Se acaba de hacer ENCOLAR al final de la Cola de : 30
Se acaba de hacer ENCOLAR al final de la Cola de : 31
Se acaba de hacer ENCOLAR al final de la Cola de : 32
Se acaba de hacer ENCOLAR al final de la Cola de : 33
Se acaba de hacer ENCOLAR al final de la Cola de : 34
Se acaba de hacer ENCOLAR al final de la Cola de : 35
Se acaba de hacer ENCOLAR al final de la Cola de : 36
Se acaba de hacer ENCOLAR al final de la Cola de : 37
Se acaba de hacer ENCOLAR al final de la Cola de : 38
Se acaba de hacer ENCOLAR al final de la Cola de : 39
Se acaba de hacer ENCOLAR al final de la Cola de : 40
Se acaba de hacer ENCOLAR al final de la Cola de : 41
Se acaba de hacer ENCOLAR al final de la Cola de : 42
Se acaba de hacer ENCOLAR al final de la Cola de : 43
Se acaba de hacer ENCOLAR al final de la Cola de : 44
Se acaba de hacer ENCOLAR al final de la Cola de : 45
Se acaba de hacer ENCOLAR al final de la Cola de : 46
Se acaba de hacer ENCOLAR al final de la Cola de : 47
Se acaba de hacer ENCOLAR al final de la Cola de : 48
Se acaba de hacer ENCOLAR al final de la Cola de : 49
Se acaba de hacer ENCOLAR al final de la Cola de : 50
Presione una tecla para continuar . . .

 Prueba 5:
El primero que va a ser atendido de la cola es : 1
Presione una tecla para continuar . . .

 Prueba 6:
Se acaba de hacer DESCOLAR, el nuevo primero  de la Cola ahora es:
2
Presione una tecla para continuar . . .
 */