
#include <stdio.h>
#include <iostream>
#include <math.h>
#include <windows.h>
#include <stdlib.h>
#include <cstdlib>
#include <time.h>
#include <string>
#include <string.h>

#include <allegro5/allegro.h>
#include <allegro5/allegro_ttf.h>
#include <allegro5/allegro_font.h>
#include <allegro5/allegro_native_dialog.h>
#include <allegro5/allegro_primitives.h>
#include <allegro5/allegro_image.h>
#include <allegro5/allegro_audio.h>
#include <allegro5/allegro_acodec.h>
#pragma warning(disable:4996);
using namespace std;

typedef struct Hilera {
	char caracter[1];//guarda el caracter generado aleatoreamente
	int X;//define su posición horizontal
	int Y;//define su posición verticalmente
	bool speed;//define con que timer se ejecuta
	Hilera* Siguiente;//conecta las hileras que han sido generados
}*PtrHilera;

//Globales
//**********************************************************

#define FPS 30.0
int contador;//cuenta los caracteres
float contador2 = 0;//segundos
int contador3 = 0;//hileras destruidas
int contador4 = 0;//hileras creadas
//Elementos de allegro que se utilizarán para el juego
//**********************************************************
ALLEGRO_DISPLAY *pantalla;
ALLEGRO_FONT *fuente;
ALLEGRO_FONT *fuente2;

//LAS SIGUIENTES DOS FUNCIONES SE USAN PARA GENERAR NUMEROS RANDOM "VERDADEROS"
//EN PERIODOS DE TIEMPO MUY CORTOS
long g_seed = 1;
inline int fastrand() {
	g_seed = (214013 * g_seed + 2531011);
	return (g_seed >> 16) & 0x7FFF;
}
/*Genera caracteres ASCII de manera random basandose en la velocidad de las hileras
  Recorre todas las hileras de la lista
*/
void GenerarRandom(PtrHilera& hilera, bool cierto) {
	PtrHilera Aux = hilera;
	while (Aux != NULL) {//recorre la lista
		if (Aux->speed == cierto) {//genera un nuevo caracter si la velocidad de la hilera es la deseada
			Aux->caracter[0] = 'A' + fastrand() % 24;
			contador++;//aumenta el contador de caracteres
		}
		Aux = Aux->Siguiente;
	}
}

/*Borra el elemento especificado, en este caso es una hilera que tocó el fondo de la pantalla.
  Puede que esté en cualquier parte de la lista, por lo que se encarga de volver a vincular la lista 
  para poder eliminar el elemento sin generar problemas
*/
void borrarElemento(PtrHilera& hilera, PtrHilera& elemento) {
	
	PtrHilera Aux = hilera;//guarda el elemento que se va a comparar con el que deseamos eliminar
	PtrHilera Aux2 = hilera;//guarda el elemento anterior en la lista
	while (Aux != elemento) {//Se busca el elemento en la lista
		Aux2 = Aux;
		Aux = Aux->Siguiente;
	}
	Aux2->Siguiente = Aux->Siguiente;//vincula la lista para que no sea afectada por el elemento
	delete(elemento);//libera el elemento
	elemento = NULL;//el puntero procede a apuntar a NULL
	contador3++;//incrementa el contador que cuenta las hileras generadas
}

/*Recorre toda la lista de hileras bajando las que coinciden con la velocidad designada
  Detecta si la hilera ha llegado al fondo y la elimina. Reproduce un sonido al hacerlo
*/
void bajarHilera(PtrHilera& hilera, bool cierto, ALLEGRO_SAMPLE* sonido) {
	PtrHilera Aux = hilera;
	PtrHilera Aux2;
	while (Aux != NULL) {
		if (Aux->speed == cierto) {//si coincide con la velocidad, procede a bajar la hilera
			Aux->Y = Aux->Y + 20;//baja la hilera al aumentar su posicion en y
			Aux2 = Aux->Siguiente;//respalda el siguiente elemento en caso de que se elimine la actual
			if (Aux->Y >= 500) {// si la hilera toca el borde es eliminada
				borrarElemento(hilera, Aux);//se elimina la hilera
				al_play_sample(sonido, 0.13, 0, 1, ALLEGRO_PLAYMODE_ONCE, NULL);//se reproduce el sonido
			}
			Aux = Aux2;
		}
		else {
			Aux = Aux->Siguiente;
		}
	}	
}


/*Busca si existe una hilera con la misma posicion que se le va a otorgar a la nueva hilera*/
bool buscarExistente(int x, PtrHilera hilera) {
	bool encontrado = false;
	PtrHilera Aux = hilera;
	while(Aux!=NULL){
		if (abs(Aux->X-x)<10) {//se deja un margen de 10 para que no haya colisión entre hileras
			encontrado = true;
		}
		Aux = Aux->Siguiente;
	}
	return encontrado;
}
/*Crea una nueva hilera e inicializa su cordenada en X y Y de manera aleatoria
  Confirma que la cordenada x no esté ocupada por otra hilera 
*/
void crearHilera(PtrHilera& lista, PtrHilera& hilera) {
	bool existente = true;
	while (existente==true) {//confirma que no exista otra hilera con la misma X
		hilera->X = rand() % 780;
		existente = buscarExistente(hilera->X, lista);
	}
	hilera->Y = rand() % 250;
	hilera->caracter[0] = ' ';
	hilera->speed = rand() % 2;//se le asigna aleatoriamente la velocidad a la que cae
	hilera->Siguiente = lista;
	lista = hilera;

	contador4++;//aumenta el contador que guarda la cantidad de hileras creadas
}

void inicializarstack(char stack[6]) {
	for (int i = 0; i > 6; i++) {
		stack[i] = ' ';
	}
}
/*
CADA LINEA DE CARACTERES QUE VA CAYENDO SE GUARDA EN UN STACK, QUE ES UN ARREGLO DE CHAR
COMO SE VAN GENERANDO CARACTERES NUEVOS ENTONCES LOS CARACTERES EN EL STACK SE VAN DESPLAZANDO
A MODO DE UNA PILA FIFO, PERO COMO ES UN ARREGLO ENTONCES SE IMPLEMENTA MAS FACIL DE LA SIGUIENTE MANERA
*/
void desplazar(char stack[6]) {
	stack[5] = stack[4];
	stack[4] = stack[3];
	stack[3] = stack[2];
	stack[2] = stack[1];
	stack[1] = stack[0];
}
/*
DIBUJAR LO QUE HACE ES EN BASE A UN STACK DEFINIDO Y UN CONJUNTO DE COORDENADAS IMPRIME EN EL DISPLAY
LA SECUENCIA DE CARACTERES ASCII, Y A SU VEZ LLAMA A LA FUNCION DESPLAZAR PARA SACAR DEL STACK EL CARACTER MAS VIEJO Y
AÑADIR AL INICIO EL MAS NUEVO
DIBUJA TODOS LOS ELEMENTOS DE LA LISTA DE HILERAS
*/
char actual[1];
void dibujar(PtrHilera lista, char stack[6]) {
	PtrHilera Aux = lista;
	while (Aux != NULL) {//dibuja en pantalla la hilera, la cual posee hasta 6 caracteres
						 //Se basa en su posicion para dibujarla
		int x = Aux->X;
		int y = Aux->Y;
		actual[0] = stack[0];
		al_draw_text(fuente, al_map_rgb(0, 200, 0), x, y - 20, ALLEGRO_ALIGN_CENTRE, actual);
		actual[0] = stack[1];
		al_draw_text(fuente, al_map_rgb(0, 150, 0), x, y - 40, ALLEGRO_ALIGN_CENTRE, actual);
		actual[0] = stack[2];
		al_draw_text(fuente, al_map_rgb(0, 100, 0), x, y - 60, ALLEGRO_ALIGN_CENTRE, actual);
		actual[0] = stack[3];
		al_draw_text(fuente, al_map_rgb(0, 50, 0), x, y - 80, ALLEGRO_ALIGN_CENTRE, actual);
		actual[0] = stack[4];
		al_draw_text(fuente, al_map_rgb(0, 25, 0), x, y - 100, ALLEGRO_ALIGN_CENTRE, actual);
		actual[0] = stack[5];
		al_draw_text(fuente, al_map_rgb(0, 10, 0), x, y - 120, ALLEGRO_ALIGN_CENTRE, actual);
		al_draw_text(fuente, al_map_rgb(255, 255, 255), x, y, ALLEGRO_ALIGN_CENTRE, actual);
		al_flip_display();
		desplazar(stack);
		stack[0] = Aux->caracter[0];
		Aux = Aux->Siguiente;
	}

}
/*
Esta función guarda los contadores que registran los caracteres e hileras creadas,
las eliminadas y el tiempo de ejecucion en un archivo de texto llamado ARCHIVO.txt
*/
void GuardarDatos() {

	FILE* archivo;
	fopen_s(&archivo, "ARCHIVO.txt", "w+");
	if (NULL == archivo) {
	}
	else {
			fprintf(archivo, "%i\n", contador);//cantidad caracteres creados
			fprintf(archivo, "%f\n", contador2);//cantidad segundos ejecutados
			fprintf(archivo, "%i\n", contador3);//cantidad hileras destruidas
			fprintf(archivo, "%i\n", contador4);//cantidad hileras creadas
	}
	fclose(archivo);
}
/*
Esta función carga en las variables contadores que registran los caracteres e hileras creadas,
las eliminadas y el tiempo de ejecucion, para retomar el conteo de estas
Se ejecuta al inicio del  programa
Se cargan los datos que se encuentran en ARCHIVO.txt
*/
void CargarDatos() {
	FILE* archivo;
	fopen_s(&archivo, "ARCHIVO.txt", "r");
	if (NULL == archivo) {
	}
	else {
		while (!feof(archivo)) {
			fscanf_s(archivo, "%i\n", &contador);//cantidad caracteres creados
			fscanf_s(archivo, "%f\n", &contador2);//cantidad segundos ejecutados
			fscanf_s(archivo, "%i\n", &contador3);//cantidad hileras destruidas
			fscanf_s(archivo, "%i\n", &contador4);//cantidad hileras creadas
		}
	}
	//Se cierra el archivo
	fclose(archivo);
}

int main() {
	CargarDatos();
	PtrHilera lista= NULL;
	PtrHilera elemento;
	if (!al_init()) {
		fprintf(stderr, "No se puede iniciar allegro!\n");
		return -1;
	}
	al_init_primitives_addon();
	al_install_keyboard();
	al_install_audio();
	al_init_acodec_addon();
	al_reserve_samples(7);
	//Esta línea de código permite que la ventana tenga la capacidad de cambiar de tamaño
	al_set_new_display_flags(ALLEGRO_WINDOWED | ALLEGRO_RESIZABLE);
	pantalla = al_create_display(720, 510);
	al_set_window_position(pantalla, 200, 200);
	al_set_window_title(pantalla, "MATRIX");
	if (!pantalla) {
		fprintf(stderr, "No se puede crear la pantalla!\n");
		return -1;
	}
	//Líneas para obtener las funcionalidades del uso de las fuentes
	//*******************
	al_init_font_addon();
	al_init_ttf_addon();
	//*******************
	//Línea para obtener las funcionalidades de las imágenes
	//*******************
	al_init_image_addon();
	//*******************
	fuente = al_load_font("pixel.ttf", 16, NULL);
	fuente2 = al_load_font("pixel.ttf", 26, NULL);
	//Timers que se necesitarán para el juego
	//**********************************************************
	ALLEGRO_TIMER *primerTimer = al_create_timer(5 / FPS);
	ALLEGRO_TIMER *segundoTimer = al_create_timer(3.8 / FPS);
	ALLEGRO_TIMER *tercerTimer = al_create_timer(1 / FPS);
	ALLEGRO_TIMER *cuartoTimer = al_create_timer(8 / FPS);
	ALLEGRO_TIMER *cTimer = al_create_timer(2/FPS);

	//**********************************************************

	//Se crea una cola de eventos
	ALLEGRO_EVENT_QUEUE *colaEventos = al_create_event_queue();

	//Registro de los eventos
	//**********************************************************
	al_register_event_source(colaEventos, al_get_timer_event_source(primerTimer));
	al_register_event_source(colaEventos, al_get_timer_event_source(segundoTimer));
	al_register_event_source(colaEventos, al_get_timer_event_source(tercerTimer));
	al_register_event_source(colaEventos, al_get_timer_event_source(cuartoTimer));
	al_register_event_source(colaEventos, al_get_timer_event_source(cTimer));
	al_register_event_source(colaEventos, al_get_keyboard_event_source());
	al_register_event_source(colaEventos, al_get_display_event_source(pantalla));
	//al_register_event_source(colaEventos, al_get_keyboard_event_source());
	//**********************************************************
	//registra la musica
	ALLEGRO_SAMPLE* musica = al_load_sample("Matrix.mp3");
	ALLEGRO_SAMPLE* sonido = al_load_sample("mario.mp3");
	//Inicialización de los timer
	//**********************************************************
	al_start_timer(primerTimer);
	al_start_timer(segundoTimer);
	al_start_timer(tercerTimer);
	al_start_timer(cuartoTimer);
	al_start_timer(cTimer);
	//**********************************************************
	bool hecho = true;
	ALLEGRO_KEYBOARD_STATE estadoTeclado;

	/*Se crean treinta hileras al iniciar para que la pantalla no se vea tan vacía*/
	for (int i = 0; i < 30; i++) {
		elemento = new(Hilera);
		crearHilera(lista, elemento);
	}
	char stack[6];
	al_play_sample(musica, 1.3, 0, 1, ALLEGRO_PLAYMODE_LOOP, NULL);//se ejecuta infinitamente el tema de Matrix
	while (hecho) {
		ALLEGRO_EVENT eventos;		
		al_wait_for_event(colaEventos, &eventos);		
		/*A continuación se encuentran los timers que definen las acciones tomadas
		  según pasa cierta cantidad de tiempo*/
		if (eventos.type == ALLEGRO_EVENT_TIMER) {
			if (eventos.timer.source == primerTimer) {
				GenerarRandom(lista, true);//se generan caracteres segun la velocidad asignada en este timer
				bajarHilera(lista, true,sonido);//se bajan las hileras según la velocidad que indica el timer	
			}
			if (eventos.timer.source == cuartoTimer) {
				GenerarRandom(lista, false);
				bajarHilera(lista, false,sonido);
			}

			if (eventos.timer.source == segundoTimer) {
				elemento = new(Hilera);//genera un nuevo elemento de tipo hilera
				crearHilera(lista, elemento);//lo añade a la lista de hileras
			}
			if (eventos.timer.source == tercerTimer) {
				contador2= contador2+0.0166666667;//Aumenta el contador de segundos
			}
			if (eventos.timer.source == cTimer) {
				al_clear_to_color(al_map_rgb(0, 0, 0));//Limpia la pantalla
				dibujar(lista, stack);//dibuja las hileras en pantalla
			}
		}
		/*Cuando se le da al botón rojo x se guardan los datos y cierra el programa*/
		if (eventos.type == ALLEGRO_EVENT_DISPLAY_CLOSE){
			al_destroy_sample(musica);
			GuardarDatos();
			hecho = false;
		}
			
		if (eventos.type == ALLEGRO_EVENT_KEY_DOWN)
		{
			/*Si se presiona la tecla escape se sale parcialmente del juego
			  Muestra los datos guardados en los contadores
			  Detiene la música de fondo
			  Es necesario volver a presionar escape para volver a salir
			*/
			switch (eventos.keyboard.keycode) {
			case ALLEGRO_KEY_ESCAPE:
				al_clear_to_color(al_map_rgb(0, 0, 0));
				al_destroy_sample(musica);//destruye la música
				GuardarDatos();//guarda los datos
				/*espera a que se presione la tecla escape para salir, mientras muestra las estadisticas en pantalla*/
				while (hecho) {
					al_wait_for_event(colaEventos, &eventos);
					string caracteres = "Cantidad caracteres generados: " + to_string(contador);
					const char* b = caracteres.c_str();
					string hileraGenerada = "Cantidad hileras generadas: " + to_string(contador4);
					const char* c = hileraGenerada.c_str();
					string hileras = "Hileras que han llegado al fondo: " + to_string(contador3);
					const char* d = hileras.c_str();
					string tiempo = "Tiempo de ejecucion en segundos: " + to_string(contador2);
					const char* e = tiempo.c_str();
					al_draw_text(fuente2, al_map_rgb(0, 100, 0), 720 / 2, (510 * (250.0 / 768.0)-50), ALLEGRO_ALIGN_CENTRE, b);
					al_draw_text(fuente2, al_map_rgb(0, 100, 0), 720 / 2, (510 * (250.0 / 768.0)), ALLEGRO_ALIGN_CENTRE, c);
					al_draw_text(fuente2, al_map_rgb(0, 150, 0), 720 / 2, (510 * (250.0 / 768.0) + 50), ALLEGRO_ALIGN_CENTRE, d);
					al_draw_text(fuente2, al_map_rgb(0, 150, 0), 720 / 2, (510 * (250.0 / 768.0) + 100), ALLEGRO_ALIGN_CENTRE, e);
					al_draw_text(fuente2, al_map_rgb(0, 150, 0), 720 / 2, (510 * (250.0 / 768.0) + 200), ALLEGRO_ALIGN_CENTRE, "Presione escape para salir...");
					if (eventos.type == ALLEGRO_EVENT_DISPLAY_CLOSE) {//sale al presionar la x roja
						hecho = false;
					}
					if (eventos.type == ALLEGRO_EVENT_KEY_DOWN)
					{
						//Si se presiona la tecla escape se sale del juego
						switch (eventos.keyboard.keycode) {
						case ALLEGRO_KEY_ESCAPE:
							
						hecho = false;
						}
					}
					al_flip_display();
				}
			}
		}
			
	}
	/*Destruye todo lo usado por Allegro*/
	al_destroy_event_queue(colaEventos);
	al_destroy_sample(sonido);
	al_destroy_display(pantalla);
	al_destroy_font(fuente);
	al_destroy_font(fuente2);
	al_destroy_timer(primerTimer);
	al_destroy_timer(segundoTimer);
	al_destroy_timer(tercerTimer);
	al_destroy_timer(cuartoTimer);
	al_destroy_timer(cTimer);
}
