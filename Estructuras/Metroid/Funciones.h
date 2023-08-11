#include <stdio.h>
#include <iostream>
#include <math.h>
#include <windows.h>
#include <stdlib.h>
#include <cstdlib>
#include <time.h>

#include <allegro5/allegro5.h>
#include <allegro5/allegro_ttf.h>
#include <allegro5/allegro_font.h>
#include <allegro5/allegro_native_dialog.h>
#include <allegro5/allegro_primitives.h>
#include <allegro5/allegro_image.h>
#include <allegro5/allegro_audio.h>
#include <allegro5/allegro_acodec.h>
#include <string>



ALLEGRO_SAMPLE* sonidos[12];//sonidos
int escenario = 0;//define el escenario que se carga
using namespace std;
/*
contadores generales
*/
float contador=0;
int balasPerdidas = 0;
int balasAcertadas = 0;
#pragma warning(disable:4996);
#define FPS 60.0
#define FPS2 330.0
//sprites
ALLEGRO_BITMAP* sBalas[8];
ALLEGRO_BITMAP* sEnemigo[13];
ALLEGRO_BITMAP* barrera;
ALLEGRO_BITMAP* pared;
ALLEGRO_BITMAP* sItem;
ALLEGRO_BITMAP* sTrofeo;
ALLEGRO_BITMAP* Personaje[11];
ALLEGRO_FONT* Fuente2;
ALLEGRO_FONT* Fuente;
ALLEGRO_BITMAP* Fondo;
bool salto = false;//define si se puede saltar
bool dib = true;//define si se dibuja en pantalla
int ressalto = 0;//cantidad de saltos restantes continuos
int resiz = 0;//cantidad desplazada a la izquierda por golpe contra enemigo
int resde = 0;//cantidad desplazada a la izquierda por golpe contra enemigo
enum dir { arriba, abajo, derecha, izquierda};//se enumeran las direcciones

typedef struct bala {
	int x;
	int y;
	int dir;//dirección en la que se mueve
	int tipo;//Define el daño y sprite
	int atk;//define el atk de la bala
	int explosionTime=0;//Si es bomba, explota al superar cierta cantidad de tiempo
	bool estado = true;//define si interactúa con el entorno
	bala* Siguiente;
}*PtrBala;
typedef struct piso {
	int x;
	int y;
	bool posicion;//Define el sprite
	bool abierto = false;//Si está abierta no interactua con el personaje
	bool puerta;//define si se conmporta como puerta
	piso* Siguiente;
}*PtrPiso;
typedef struct Jugador{
	int x;
	int y;
	int hp = 100;//vida del personaje
	int dir;//dirección en la que se mueve
	int nivel = 0;//define las habilidades que puede usar
	int atk = 50;
	int ani; //define el sprite a utilizar
	int misil=0;//misiles que posee
	bool bolita=false;//define si está en el modo bolita

}*PtrJugador;
typedef struct item{
	int x;
	int y;
	int activo = true;//define si interactúa con el entorno
	bool trofeo = false;//define si es el objeto que permite ganar
}*PtrItem;
typedef struct enemigo {
	int x;
	int y;
	int dir;//dirección en la que se mueve
	int ani=0;
	int tipo;//define el enemigo que se genera, 0 = slime, 1 = cañon, 2 = Cuadrado enojado 
	int hp=400;
	int atk = 10;
	bool congelado = false;//define si está congelado
	bool estado=true;//define si interactúa con el entorno
	enemigo* Siguiente;
}*PtrEnemigo;
PtrItem item1;
PtrItem item2;
PtrItem item3;
PtrItem trofeo;//objeto que se recoge para ganar
PtrItem itemActual;//item que se encuentra en el mapa
/*Guarda los datos de la partida que fue ganada*/
void guardarDatos(char* nombre) {

	FILE* archivo;
	fopen_s(&archivo, "bitacora.txt", "a");
	if (NULL == archivo) {
	}
	else {
		fprintf(archivo, "%s%c", nombre, '\n');//nombre del ganador
		fprintf(archivo, "%f\n", contador);//cantidad de segundos que duró
		fprintf(archivo, "%i\n", balasAcertadas);//cantidad de balas que golpean al enemigo y hacen daño
		fprintf(archivo, "%i\n", balasPerdidas);//cantidad de balas que no hicieron daño o golpearon una pared
		
		
	}
	fclose(archivo);
}
/*
Elimina los elementos de la lista de tipo enemigo
*/
void EliminarLista(PtrEnemigo& Lista){
	PtrEnemigo Aux;
	Aux = Lista;
	while (Aux != NULL){
		Lista = Lista->Siguiente;
		delete(Aux);
		Aux = Lista;
	}
}
/*
Elimina los elementos de la lista de tipo bala
*/
void EliminarLista(PtrBala& Lista){
	PtrBala Aux;
	Aux = Lista;
	while (Aux != NULL){
		Lista = Lista->Siguiente;
		delete(Aux);
		Aux = Lista;
	}
}
/*
Elimina los elementos de la lista de tipo piso
*/
void EliminarLista(PtrPiso& Lista){
	PtrPiso Aux;
	Aux = Lista;
	while (Aux != NULL){
		Lista = Lista->Siguiente;
		delete(Aux);
		Aux = Lista;
	}
}
/*
Borra los elementos del escenario actual
*/
void borrarElementos(PtrBala& listaBalas,PtrEnemigo& enemigos, PtrPiso& suelo) {
	EliminarLista(enemigos);
	EliminarLista(listaBalas);
	EliminarLista(suelo);
}
/*
Crea una nueva bala enemiga
*/
void newBalaE(PtrBala& listaBalas, PtrEnemigo enemigo, int ancho, int tipo) {
	PtrBala Aux = listaBalas;
	int b =25;
	if (listaBalas == NULL) {
		listaBalas = new bala();
		Aux = listaBalas;
		Aux->dir = enemigo->dir;
		if (Aux->dir == derecha) {//define la posicion en que aparece
			Aux->x = enemigo->x + ancho;
		}
		else {
			Aux->x = enemigo->x - ancho;
		}
		Aux->y = enemigo->y - b;
		Aux->tipo = tipo;//define el tipo de la bala
		Aux->atk = enemigo->atk;
	}
	else {
		while (Aux->Siguiente != NULL) {
			Aux = Aux->Siguiente;
		}
		Aux->Siguiente = new bala();
		Aux = Aux->Siguiente;
		Aux->dir = enemigo->dir;
		if (Aux->dir == derecha) {//define la posicion en que aparece
			Aux->x = enemigo->x + ancho;
		}
		else {
			Aux->x = enemigo->x - ancho;
		}
		Aux->y = enemigo->y - b;
		Aux->tipo = tipo;//define el tipo de la bala
		Aux->atk = enemigo->atk;
	}
	al_play_sample(sonidos[1], 0.5, 0, 1, ALLEGRO_PLAYMODE_ONCE, NULL);//se reproduce el sonido
}
/*
Crea las nuevas balas del jugador
*/
void newBala(PtrBala& listaBalas, PtrJugador jugador, int ancho, int tipo) {
	PtrBala Aux = listaBalas;
	int b = -20;
	if (tipo == 4) {
		b = 15;
	}
	if (listaBalas == NULL) {
		listaBalas = new bala();
		Aux = listaBalas;
		Aux->dir = jugador->dir;
		if (Aux->dir == derecha) {//define la posicion en que aparece
			Aux->x = jugador->x + ancho;
		}
		else {
			Aux->x = jugador->x - ancho;
		}
		Aux->y = jugador->y + b;
		Aux->tipo = tipo;//define el tipo de la bala
		Aux->atk = jugador->atk;
	}
	else {
		while (Aux->Siguiente != NULL) {
			Aux = Aux->Siguiente;
		}
		Aux->Siguiente = new bala();
		Aux = Aux->Siguiente;
		Aux->dir = jugador->dir;
		if (Aux->dir == derecha) {//define la posicion en que aparece
			Aux->x = jugador->x + ancho;
		}
		else {
			Aux->x = jugador->x - ancho;
		}
		Aux->y = jugador->y + b;
		Aux->tipo = tipo;//define el tipo de la bala
		Aux->atk = jugador->atk;
	}
	al_play_sample(sonidos[0], 0.13, 0, 1, ALLEGRO_PLAYMODE_ONCE, NULL);//se reproduce el sonido
}

/*
Dibuja todos los enemigos activos
*/
void dibujarEnemigos(PtrEnemigo enemigos) {
	PtrEnemigo Aux = enemigos;
	while (Aux != NULL) {
		if (Aux->estado) {//confirma que pueda interactuar con el entorno
			/*
			Seleciona el sprite adecuado
			*/
			if (Aux->tipo == 0) {
				if (Aux->dir == derecha) {
					al_draw_bitmap(sEnemigo[Aux->ani + 5], Aux->x - 60, Aux->y - 28, NULL);
				}
				else {
					al_draw_bitmap(sEnemigo[Aux->ani + 2], Aux->x - 60, Aux->y - 28, NULL);
				}
			}
			else if (Aux->tipo == 2) {
				al_draw_bitmap(sEnemigo[Aux->ani], Aux->x - 60, Aux->y - 28, NULL);
			}
			else {
				if (Aux->dir == derecha) { 
					al_draw_bitmap(sEnemigo[Aux->ani+2], Aux->x - 60, Aux->y - 28, NULL); }
				else {
					al_draw_bitmap(sEnemigo[Aux->ani], Aux->x - 60, Aux->y - 28, NULL);
				}
				
			}
		}
		Aux = Aux->Siguiente;
	}
}
/*
Dibuja todas las balas activas e utiliza el sprite correspondiente a su tipo
*/
void dibujarBalas(PtrBala listaBalas) {
	PtrBala Aux = listaBalas;
	while (Aux != NULL) {
		if (Aux->estado) {
			al_draw_bitmap(sBalas[Aux->tipo], Aux->x, Aux->y, NULL);//Dibuja la bala que esté activa, automaticamente utiliza el sprite adecuado
		}
		Aux = Aux->Siguiente;
	}
}
/*
Se encarga de dibujar todos los pisos
*/
void dibujarPiso(PtrPiso suelo) {
	PtrPiso Aux = suelo;
	while (Aux != NULL) {
		if (!Aux->abierto) {
			if (Aux->posicion) {
				al_draw_bitmap(barrera, Aux->x, Aux->y, NULL);//sprite horizontal
			}
			else {
				al_draw_bitmap(pared, Aux->x, Aux->y, NULL);//sprite vertical
			}
		}
		Aux = Aux->Siguiente;
	}
}
/*
Se encarga de dibujar todos los elementos activos y estadisticas que son importantes de tener en cuenta
*/
void dibujar(PtrJugador jugador, int anchoP, int altoP, PtrBala listaBalas, PtrPiso suelo, PtrEnemigo enemigos) {
	if (dib) {
		al_clear_to_color(al_map_rgb(0, 0, 0));
		al_draw_scaled_bitmap(Fondo, 400 / 20 + 100, 0, 200, 125, 0, 0, 1200, 600, NULL);
		al_draw_filled_rounded_rectangle(635, 20, 795, 40, 10, 10, al_map_rgba(0, 0, 0, 100));
		al_draw_filled_rounded_rectangle(28, 20, 155, 90, 10, 10, al_map_rgba(0, 0, 0, 100));
		al_draw_text(Fuente2, al_map_rgb(250, 200, 50), 790, 20, ALLEGRO_ALIGN_RIGHT, "Presione ESC para salir");
		string vida = "Vida: " + to_string(jugador->hp);
		string misiles = "Misiles: " + to_string(jugador->misil);
		string tiempo = "Segundos: " + to_string(contador);
		const char* e = tiempo.c_str();
		const char* v = vida.c_str();
		const char* m = misiles.c_str();
		
		
		
		
		dibujarPiso(suelo);
		if (jugador->bolita) {
			al_draw_bitmap(Personaje[jugador->ani], jugador->x - anchoP / 3 + 1, jugador->y - altoP / 2 + 14, NULL);//dibuja el sprite del jugador en modo bolita
		}
		else {
			al_draw_bitmap(Personaje[jugador->ani], jugador->x - anchoP / 2, jugador->y - altoP / 2 + 14, NULL);//dibuja el sprite normal
		}
		dibujarBalas(listaBalas);
		dibujarEnemigos(enemigos);

		al_draw_filled_rounded_rectangle(945, 25, 1185, 65, 10, 10, al_map_rgba(0, 0, 0, 100));
		al_draw_text(Fuente, al_map_rgb(250, 200, 50), 35, 25, ALLEGRO_ALIGN_LEFT, v);
		al_draw_text(Fuente, al_map_rgb(250, 200, 50), 35, 50, ALLEGRO_ALIGN_LEFT, m);
		al_draw_text(Fuente, al_map_rgb(250, 200, 50), 950, 25, ALLEGRO_ALIGN_LEFT, e);
		if (itemActual != NULL) {
			if (itemActual->activo) {
				if (itemActual->trofeo) {//si es el objeto que termina el juego dibuja un trofeo, en caso contrario un item
					al_draw_bitmap(sTrofeo, itemActual->x, itemActual->y - 40, NULL);
				}
				else {
					al_draw_bitmap(sItem, itemActual->x, itemActual->y - 40, NULL);
				}

			}
		}
		al_flip_display();
	}
	else {
		dib = true;
	}
}
/*
Detecta la colisión entre una bala y una pared. Eliminando la bala
*/
void choqueDerechaB(PtrBala Bala, PtrPiso suelo, int alto, int altoBarrera, int ancho, int anchoBarrera, bool posicion) {
	bool condicion = false;
	PtrPiso Aux = suelo;
	while (Aux != NULL && !condicion) {
		/*Confirma la posicion del piso y procede a realizar los calculos de manera personalidada*/
		if ((Aux->posicion == posicion && posicion)) {
			if ((Bala->y - altoBarrera / 2 <= Aux->y) && (Bala->y + altoBarrera / 2 >= Aux->y)) {
				if (abs((Bala->x + ancho / 2) - (Aux->x + anchoBarrera / 2)) < (anchoBarrera / 2)) {
					condicion = true;
				}
			}
		}

		if ((Aux->posicion == posicion && !posicion)) {
			if ((Bala->y <= Aux->y + altoBarrera + alto / 3) && (Bala->y - 80 >= abs(Aux->y - altoBarrera / 2))) {
				if (abs((Bala->x - ancho / 3) - (Aux->x - anchoBarrera)) < (ancho / 2)) {
					condicion = true;
				}
			}
		}

		Aux = Aux->Siguiente;
	}
	if (condicion) {// elimina la bala si detectó colisión
		Bala->estado = false;
	}

}
/*
Detecta la colisión entre una bala y una pared. Eliminando la bala
*/
void choqueIzquierdaB(PtrBala Bala, PtrPiso suelo, int alto, int altoBarrera, int ancho, int anchoBarrera, bool posicion) {
	bool condicion = false;
	PtrPiso Aux = suelo;
	while (Aux != NULL && !condicion) {
		/*Confirma la posicion del piso y procede a realizar los calculos de manera personalidada*/
		if (Aux->posicion == posicion && posicion) {
			if ((Bala->y - altoBarrera / 2 <= Aux->y) && (Bala->y + altoBarrera / 2 >= Aux->y)) {
				if (abs((Bala->x - ancho / 2) - (Aux->x + anchoBarrera / 2)) < (anchoBarrera / 2)) {
					condicion = true;
				}
			}
		}
		
		if ((Aux->posicion == posicion && !posicion)) {
			if ((Bala->y <= Aux->y + altoBarrera + alto / 3) && (Bala->y - 80 >= abs(Aux->y - altoBarrera / 2))) {
				if (abs((Bala->x) - (Aux->x + anchoBarrera + anchoBarrera / 2)) < (ancho / 2)) {
					condicion = true;
				}
			}
		}
		
		Aux = Aux->Siguiente;
	}
	if (condicion) {//elimina la bala si hay choque
		Bala->estado = false;
	}
}
/*
Detecta si el enemigo puede avanzar a la derecha
*/
bool choqueDerechaE(PtrEnemigo enemigo, PtrPiso suelo, int alto, int altoBarrera, int ancho, int anchoBarrera, bool posicion) {
	bool condicion = false;
	PtrPiso Aux = suelo;
	while (Aux != NULL && !condicion) {
		/*Confirma la posicion del piso y procede a realizar los calculos de manera personalidada*/
		if ((Aux->posicion == posicion && posicion)) {
			if ((enemigo->y - ancho / 2 <= Aux->y) && (enemigo->y + alto / 4 >= Aux->y)) {
				if (abs((enemigo->x - ancho / 2) - (Aux->x)) < (anchoBarrera / 2)) {
					condicion = true;
				}
			}
		}
		if ((Aux->posicion == posicion && !posicion)) {
			if ((enemigo->y <= Aux->y + altoBarrera + alto / 3) && (enemigo->y >= Aux->y - altoBarrera / 2 + alto / 2 + 10)) {
				if (abs((enemigo->x - ancho / 3) - (Aux->x - anchoBarrera)) < (ancho / 2)) {
					condicion = true;
				}
			}
		}
		Aux = Aux->Siguiente;
	}
	return condicion;
}
/*
Detecta si el enemigo puede avanzar a la izquierda
*/
bool choqueIzquierdaE(PtrEnemigo enemigo, PtrPiso suelo, int alto, int altoBarrera, int ancho, int anchoBarrera, bool posicion) {
	bool condicion = false;
	PtrPiso Aux = suelo;
	while (Aux != NULL && !condicion) {
		/*Confirma la posicion del piso y procede a realizar los calculos de manera personalidada*/
		if (Aux->posicion == posicion && posicion) {
			if (((enemigo->y - ancho / 2 <= Aux->y) && (enemigo->y + alto / 4 >= Aux->y))) {
				if (abs((enemigo->x + ancho / 2) - (Aux->x + anchoBarrera / 2)) < (ancho / 2)) {
					condicion = true;
				}
			}
		}
		if ((Aux->posicion == posicion && !posicion)) {
			if ((enemigo->y <= Aux->y + altoBarrera + alto / 3) && (enemigo->y >= Aux->y - altoBarrera / 2 + alto / 2 + 10)) {
				if (abs((enemigo->x + ancho / 8) - (Aux->x + anchoBarrera + anchoBarrera / 2)) < (ancho / 2)) {
					condicion = true;
				}
			}
		}
		Aux = Aux->Siguiente;
	}
	return condicion;
}
/*
Detecta si el jugador puede avanzar a la derecha
*/
bool choqueDerecha(PtrJugador jugador, PtrPiso suelo, int alto, int altoBarrera, int ancho, int anchoBarrera, bool posicion) {
	bool condicion = false;
	PtrPiso Aux = suelo;
	int b = 20;
	int a = 20;
	if (jugador->bolita) {
		b = 60;
		a = 20;
	}
	/*Confirma la posicion del piso y procede a realizar los calculos de manera personalidada*/
	while (Aux != NULL && !condicion) {
		if (!Aux->abierto) {
			if ((Aux->posicion == posicion && posicion)) {
				if ((jugador->y - alto / 2 <= Aux->y) && (jugador->y + alto / 2 >= Aux->y)) {
					if (abs((jugador->x + ancho / 2) - (Aux->x + anchoBarrera / 2)) < (anchoBarrera / 2)) {
						condicion = true;
					}
				}
			}
			if (Aux->posicion == posicion && !posicion) {
				if((jugador->y <= Aux->y + altoBarrera + alto / 3) && (jugador->y - b >= (Aux->y - altoBarrera / 2 + a))) {
					if (abs((jugador->x - ancho / 8) - (Aux->x - anchoBarrera)) < (anchoBarrera / 2)) {
						condicion = true;
					}
				}
			}
		}
		Aux = Aux->Siguiente;
	}
	return condicion;
}
/*
Detecta si el jugador puede avanzar a la izquierda
*/
bool choqueIzquierda(PtrJugador jugador, PtrPiso suelo, int alto, int altoBarrera, int ancho, int anchoBarrera, bool posicion) {
	bool condicion = false;
	PtrPiso Aux = suelo;
	int b = 20;
	int a = 20;
	if (jugador->bolita) {
		b = 60;
		a = 20;
	}
	/*Confirma la posicion del piso y procede a realizar los calculos de manera personalidada*/
	while (Aux != NULL && !condicion) {
		if (!Aux->abierto) {
			if (Aux->posicion == posicion && posicion) {
				if ((jugador->y - alto / 2 <= Aux->y) && (jugador->y + alto / 2 >= Aux->y)) {
					if (abs((jugador->x - ancho / 2) - (Aux->x + anchoBarrera / 2)) < (anchoBarrera / 2)) {
						condicion = true;
					}
				}
			}
			if ((Aux->posicion == posicion && !posicion)) {
				if ((jugador->y  <= Aux->y + altoBarrera + alto / 3) && (jugador->y - b >= (Aux->y - altoBarrera / 2+a))) {
					if (abs((jugador->x - ancho / 2 + 16) - (Aux->x + anchoBarrera)) < (anchoBarrera / 2)) {
						condicion = true;
					}
				}
			}
		}
		Aux = Aux->Siguiente;
	}
	return condicion;
}
/*
Detecta si el jugador colisiona con una plataforma superior a su posición
*/
bool tocaTecho(PtrJugador jugador, PtrPiso suelo, int alto, int altoBarrera, int ancho, int anchoBarrera, bool posicion) {
	bool condicion = false;
	int a = 0;
	if (jugador->bolita) {//personaliza la hitbox
		a = 5;
	}
	PtrPiso Aux = suelo;
	while (Aux != NULL && !condicion) {
		if (!Aux->abierto) {
			if (Aux->posicion == posicion) {
				if ((jugador->x - ancho / 4 <= Aux->x + anchoBarrera) && (jugador->x + ancho / 4 >= Aux->x)) {
					if ((abs((jugador->y - alto / 2) - (Aux->y)) < (altoBarrera / 2+a)) && ressalto > 12) {
						condicion = true;
					}
				}
			}
		}
		Aux = Aux->Siguiente;
	}
	return condicion;
}
/*
Detecta si el jugador colisiona con una plataforma a la hora de transformarse de vuelta
*/
bool puedePararse(PtrJugador jugador, PtrPiso suelo, int alto, int altoBarrera, int ancho, int anchoBarrera, bool posicion) {
	bool condicion = true;
	int a = 5;//modifica la hitbox
	PtrPiso Aux = suelo;
	int contador = 0;
	while (contador < 35 && condicion) {
		while (Aux != NULL && condicion) {
			if (!Aux->abierto) {//en caso de ser una puerta, detecta que no esté abierta
				if (Aux->posicion == posicion) {
					int y = jugador->y - contador;
					if ((jugador->x - ancho / 4 <= Aux->x + anchoBarrera) && (jugador->x + ancho / 2 >= Aux->x)) {//detecta el rango en x
						if ((abs((y - alto / 2) - (Aux->y)) < (altoBarrera + a))) {//detecta la altura
							condicion = false;
							
						}
					}
				}
			}
			Aux = Aux->Siguiente;
		}
		contador++;
	}
	return condicion;
}
/*
Detecta si el jugador está apoyado en una plataforma y habilita el salto de ser así
*/
bool seEncuentraApoyado(PtrJugador jugador, PtrPiso suelo, int alto, int altoBarrera, int ancho, int anchoBarrera, bool posicion) {
	bool condicion = false;
	PtrPiso Aux = suelo;
	while (Aux != NULL && !condicion) {
		if (!Aux->abierto) {
			if (Aux->posicion == posicion && posicion) {
				if ((jugador->x - ancho / 4 <= Aux->x + anchoBarrera) && (jugador->x + ancho / 4 >= Aux->x)) {
					if (abs((jugador->y + alto / 2) - (Aux->y)) < (altoBarrera / 2)) {
						salto = false;//habilita el salto cuando se toca el piso
						condicion = true;
					}
				}
			}
		}
		Aux = Aux->Siguiente;
	}
	return condicion;
}
/*
Detecta si un enemigo está apoyado en una plataforma
*/
bool seEncuentraApoyadoE(PtrEnemigo enemigo, PtrPiso suelo, int alto, int altoBarrera, int ancho, int anchoBarrera, bool posicion) {
	bool condicion = false;
	PtrPiso Aux = suelo;
	while (Aux != NULL && !condicion) {
		if (Aux->posicion == posicion && posicion) {
			if ((enemigo->x - ancho / 4 <= Aux->x + anchoBarrera) && (enemigo->x + ancho / 4 >= Aux->x)) {
				if (abs((enemigo->y + alto / 2) - (Aux->y)) < (altoBarrera / 2) - 4) {
					condicion = true;
				}
			}
		}
		if (Aux->posicion == posicion && !posicion) {
			if ((enemigo->x - ancho / 4 + 15 <= Aux->x + anchoBarrera / 2) && (enemigo->x + ancho / 9 >= Aux->x)) {
				if (abs((enemigo->y) - alto / 2 - (Aux->y)) < (altoBarrera / 2)) {
					condicion = true;
				}
			}
		}
		Aux = Aux->Siguiente;
	}
	return condicion;
}
/*
Detecta la colisión con el jugador y se encarga de disminuir la vida del personaje
Si el enemigo está congelado el jugador lo puede usar como trampolín, de lo contrario recibe daño aplicando un retroceso
Si el jugador y el enemigo van en direcciones opuestas, cambia la dirección del enemigo
*/
void colisionEnemigoJugador(PtrEnemigo enemigos, PtrJugador& jugador, PtrPiso suelo) {
	int x2, x1, y2, y1, a, b, ancho;
	PtrEnemigo Aux = enemigos;
	bool hecho = false;
	while (Aux != NULL && !hecho) {
		
		if (Aux->estado) {//Detecta que pueda interactuar
			
			if (Aux->x > jugador->x) {
				x2 = Aux->x;
				y2 = Aux->y;
				x1 = jugador->x;
				y1 = jugador->y;
			}
			else {
				x1 = Aux->x;
				y1 = Aux->y;
				x2 = jugador->x;
				y2 = jugador->y;
			}
			a = pow((x2 - x1), 2);
			b = pow((y2 - y1), 2);
			if (jugador->bolita) {//personaliza la hitbox
				ancho = al_get_bitmap_width(sEnemigo[Aux->ani]) - 42;
			}
			else {
				ancho = al_get_bitmap_width(sEnemigo[Aux->ani]) - 22;
			}
			if (sqrt(a + b) < ancho) {//detecta la colisión
				if (!Aux->congelado) {//detecta que no esté congelado
					jugador->hp -= Aux->atk;
					if (Aux->dir == izquierda) {//deteca la colisón del impacto
						if (jugador->dir == izquierda && jugador->x > Aux->x) {//detecta la colisón del jugador, decidiendo hacia donde aplicar el retroceso
							resde = 35;//aplica retorceso
						}
						else {
							resiz = 35;//aplica retorceso
							if (Aux->tipo != 1) {//cambia la dirección del enemigo una vez impacta
								Aux->dir = derecha;
							}
						}

					}
					else {
						if (jugador->dir == derecha && jugador->x < Aux->x) {
							resiz = 55;//aplica retorceso
						}
						else {
							resde = 55;//aplica retorceso
							if (Aux->tipo != 1) {
								Aux->dir = izquierda;
							}
						}
					}
					if (ressalto <= 0) {
						ressalto = 15;//aplica retorceso
						salto = true;
					}
					al_play_sample(sonidos[6], 0.5, 0, 1, ALLEGRO_PLAYMODE_ONCE, NULL);//se reproduce el sonido
				}
				else {
					ressalto = 80;//funciona como trampolin si está congelado el enemigo
					salto = true;//deshabilita el salto
				}
				
			}
		}
		
		Aux = Aux->Siguiente;
	}
}
/*
Función encargada de dañar a los enemigos según el impacto que reciben
*/
void takeDamageEnemigo(PtrEnemigo enemigos, PtrEnemigo& enemigo, PtrBala balas) {
	int damage = balas->tipo + 1;//define el daño que hará la bala según su tipo
	balasAcertadas++;//aumenta el contador de balas acertadas
	if (balas->tipo == 4 || balas->tipo == 5) {//si es una bomba el daño se multiplica por diez
		damage = 10;
	}
	enemigo->hp -= balas->atk * (damage);
	if (enemigo->tipo == 1) {//si el enemigo es un cañon y este muere al instante, pero no todo tipo de balas puede matarlo
		al_play_sample(sonidos[3], 0.5, 0, 1, ALLEGRO_PLAYMODE_ONCE, NULL);//se reproduce el sonido
	}
	if (enemigo->hp <= 0) {//deshabilita al enemigo una vez muere
		enemigo->estado = false;
		if (enemigo->tipo == 0) {//sonido slime muere
			al_play_sample(sonidos[5], 4, 0, 1, ALLEGRO_PLAYMODE_ONCE, NULL);//se reproduce el sonido
		}
	}
	else {//de lo contrario simplemente reproduce el sonido que hace al ser golpeado
		if (enemigo->tipo == 0) {
			al_play_sample(sonidos[4], 0.5, 0, 1, ALLEGRO_PLAYMODE_ONCE, NULL);//se reproduce el sonido
		}
	}
}
/*
Detecta que los enemigos estén en el area de explosión y los mata
*/
void explosionGolpeaEnemigo(PtrEnemigo& enemigos, PtrBala Bala) {
	PtrEnemigo Aux = enemigos;
	while (Aux != NULL) {
		if (Aux->tipo != 2) {
			if ((abs(Bala->y - 15 - Aux->y) <= 100) && Aux->estado) {//define el área de explosión
				if (abs((Bala->x) - (Aux->x)) < (100)) {
					takeDamageEnemigo(enemigos, Aux, Bala);//daña al enemigo
				}
			}
		}
		Aux = Aux->Siguiente;
	}
}








/*
Confirma que el jugador haya sido golpeado por una bala enemiga
Al recibir daño desaparece la bala y el jugador obtiene un retroceso
*/
void balaGolpeaJugador(PtrJugador& jugador, PtrBala Bala,int alto, int ancho) {
	bool condicion = false;
	int x1, x2, y1, y2, a, b;
	PtrBala Aux = Bala;
	if (Aux->tipo == 6) {//confirma que sea una bala enemiga
		if (Aux->x > jugador->x) {
			x2 = Aux->x;
			y2 = Aux->y;
			x1 = jugador->x;
			y1 = jugador->y;
		}
		else {
			x1 = Aux->x;
			y1 = Aux->y;
			x2 = jugador->x;
			y2 = jugador->y;
		}
		a = pow((x2 - x1), 2);
		b = pow((y2 - y1), 2);

		ancho = ancho - 22;
		alto = alto - 22;

		if (sqrt(a + b) < ancho) {
			if (sqrt(a + b) < alto) {//confirma el impacto
				Aux->estado = false;
				jugador->hp -= Aux->atk;
				/*Otorga el retroceso dependiendo de la dirección*/
				if (Aux->dir == izquierda) {
					if (jugador->dir == izquierda && jugador->x > Aux->x) {
						resde = 35;
					}
					else {
						resiz = 35;
					}

				}
				else {
					if (jugador->dir == derecha && jugador->x < Aux->x) {
						resiz = 55;
					}
					else {
						resde = 55;
					}
				}
				if (ressalto <= 0) {
					ressalto = 15;
					salto = true;
				}
				al_play_sample(sonidos[6], 0.5, 0, 1, ALLEGRO_PLAYMODE_ONCE, NULL);//se reproduce el sonido
			}
		}
	}
}
/*
Confirma que la bala haya golpeado al enemigo y procede a restar vida si es posible dicha interacción
*/
void balaGolpeaEnemigo(PtrEnemigo enemigos, PtrBala Bala) {
	bool condicion = false;
	int x1, x2, y1, y2,a,b;
	PtrEnemigo Aux = enemigos;
	int alto, ancho;
	while (Aux != NULL && !condicion) {
		if (Aux->estado) {
			if (Aux->x > Bala->x) {
				x2 = Aux->x;
				y2 = Aux->y;
				x1 = Bala->x;
				y1 = Bala->y;
			}
			else {
				x1 = Aux->x;
				y1 = Aux->y;
				x2 = Bala->x;
				y2 = Bala->y;
			}
			a = pow((x2 - x1), 2);
			b = pow((y2 - y1), 2);
			/*Define la hitbox*/
			ancho = al_get_bitmap_width(sEnemigo[Aux->ani]) - 22;
			alto = al_get_bitmap_height(sEnemigo[Aux->ani]) - 22;

			if (sqrt(a + b) < ancho){
				if (sqrt(a + b) < alto) {//detecta colisión
					condicion = true;
				}
			}

			if (condicion) {//Si colisionó, realiza la acción
				Bala->estado = false;
				if (Bala->tipo != 7) {//comprueba que no sea una bala con el atributo congelar
					if (Aux->tipo != 2) {//detecta que el enemigo pueda recibir daño, todos menos el cuadrado enojado
						if (Aux->tipo != 1 || (Aux->tipo == 1 && Bala->tipo == 3)) {//detecta que el cañon solo pueda ser dañado por misil, el slime por todos
							takeDamageEnemigo(enemigos, Aux, Bala);
						}
						else {//reproduce el sonido de cañon ileso y aumenta las balas perdidas
							al_play_sample(sonidos[2], 0.13, 0, 1, ALLEGRO_PLAYMODE_ONCE, NULL);//se reproduce el sonido
							balasPerdidas++;
						}
					}
					else {//reproduce el sonido del cuadrado enojado ileso y aumenta las balas perdidas
						al_play_sample(sonidos[11], 0.13, 0, 1, ALLEGRO_PLAYMODE_ONCE, NULL);//se reproduce el sonido
						balasPerdidas++;
					}
				}
				else {//congela o descongela al enemigo
					if (Aux->congelado) {
						Aux->congelado = false;
					}
					else {
						Aux->congelado = true;
					}
					balasAcertadas++;
					al_play_sample(sonidos[10], 0.13, 0, 1, ALLEGRO_PLAYMODE_ONCE, NULL);//se reproduce el sonido
				}

			}
		}
		Aux = Aux->Siguiente;
	}
	
}
/*
Confirma que la bala haya impactado con un enemigo o con una pared
*/
void impactoBala(PtrEnemigo enemigos, PtrBala listaBalas, PtrPiso suelo, PtrJugador jugador, int alto, int ancho) {
	PtrBala Aux = listaBalas;
	while (Aux != NULL) {
		if (Aux->tipo != 4 && Aux->tipo != 5 && Aux->tipo != 6) {//impacto con enemigo
			if (Aux->estado) {
				balaGolpeaEnemigo(enemigos, Aux);
			}
		}
		if (Aux->tipo != 4 && Aux->tipo != 5) {//impacto con pared
			if (Aux->estado) {
				choqueDerechaB(Aux, suelo, al_get_bitmap_height(sBalas[Aux->tipo]), al_get_bitmap_height(barrera), al_get_bitmap_width(sBalas[Aux->tipo]), al_get_bitmap_width(barrera), true);
				choqueDerechaB(Aux, suelo, al_get_bitmap_height(sBalas[Aux->tipo]), al_get_bitmap_height(pared), al_get_bitmap_width(sBalas[Aux->tipo]), al_get_bitmap_width(pared), false);
				choqueIzquierdaB(Aux, suelo, al_get_bitmap_height(sBalas[Aux->tipo]), al_get_bitmap_height(barrera), al_get_bitmap_width(sBalas[Aux->tipo]), al_get_bitmap_width(barrera), true);
				choqueIzquierdaB(Aux, suelo, al_get_bitmap_height(sBalas[Aux->tipo]), al_get_bitmap_height(barrera), al_get_bitmap_width(sBalas[Aux->tipo]), al_get_bitmap_width(barrera), false);
				if (!Aux->estado) {
					balasPerdidas++;
				}
			}
		}
		if (Aux->tipo == 6) {//impacto con jugador
			if (Aux->estado) {
				balaGolpeaJugador(jugador, Aux,alto,ancho);
			}
		}
		Aux = Aux->Siguiente;
	}
}
/*
Contea el tiempo necesario para que explote la bomba
*/
void aumentarExplosion(PtrEnemigo& enemigos, PtrBala& listaBalas) {
	PtrBala Aux = listaBalas;
	PtrBala Aux2 = listaBalas;
	while (Aux != NULL) {
		Aux2 = Aux->Siguiente;
		if (Aux->tipo == 4 || Aux->tipo == 5) {
			if (Aux->estado) {//si la bomba está activa, aumenta su tiempo
				if (Aux->explosionTime > 500) {//si el tiempo es mayor a 500, explota y hace daño en área
					explosionGolpeaEnemigo(enemigos, Aux);
					Aux->estado = false;
					al_play_sample(sonidos[7], 0.5, 0, 1, ALLEGRO_PLAYMODE_ONCE, NULL);//se reproduce el sonido	
				}
				else {
					Aux->explosionTime ++;//Aumenta el tiempo
				}
			}
		}
		Aux = Aux2;
	}
}
/*
Mueve todas las balas que están activas, exceptuando las bombas
*/
void moverBalas(PtrBala& listaBalas) {
	PtrBala Aux = listaBalas;
	while (Aux != NULL) {
		if (Aux->tipo != 5 && Aux->tipo != 4) {//confirma que no es una bomba y procede a moverla
			if (Aux->estado) {
				if (Aux->dir == derecha) {
					Aux->x++;
				}
				else {
					Aux->x--;
				}
			}
		}
		
		Aux = Aux->Siguiente;
	}
}
/*
Aumenta la animación de las balas que poseen animación
*/
void aumentarBalasAni(PtrBala& balas) {
	bool hecho = false;
	PtrBala Aux = balas;
	while (Aux != NULL) {
		if (Aux->tipo == 4 && !hecho) {//itercambia el cuarto sprite por el quinto
			Aux->tipo = 5;
			hecho = true;
		}
		if (Aux->tipo == 5 && !hecho) {//intercambia el quinto por el cuarto SPRITE
			Aux->tipo = 4;
			hecho = true;
		}
		hecho = false;
		Aux = Aux->Siguiente;
	}
}
/*
Se encarga de que exista una única bomba a la vez
*/
bool confirmarBombaExiste(PtrBala& balas) {
	bool cierto = false;
	PtrBala Aux = balas;
	while (Aux != NULL && !cierto) {
		if (Aux->tipo == 4 && Aux->estado|| Aux->tipo == 5 && Aux->estado) {//detecta la existencia de una bomba activa 
			Aux->tipo = 5;
			cierto = true;
		}
		Aux = Aux->Siguiente;
	}
	return cierto;
}
/*
Se encarga de confirmar la existencia de enemigos vivos
*/
bool confirmarEnemigoExiste(PtrEnemigo& enemigos) {
	bool cierto = false;
	PtrEnemigo Aux = enemigos;
	while (Aux != NULL && !cierto) {
		if (Aux->estado &&Aux->tipo!=2){//detecta que el enemigo siga vivo, exceptuando los cuadrados enojados
			cierto = true;
		}
		Aux = Aux->Siguiente;
	}
	return cierto;
}
/*
Se encarga de mover a los enemigos que estén activos
*/
void moverEnemigos(PtrEnemigo& enemigos) {
	PtrEnemigo Aux = enemigos;
	while (Aux != NULL) {
		if (Aux->estado) {
			if (!Aux->congelado) {
				if (Aux->tipo != 1) {//detecta que no sea un cañon y procede a moverlo en su respectiva dirección
					if (Aux->dir == derecha) {
						Aux->x++;
					}
					else {
						Aux->x--;
					}
				}
			}
		}
		Aux = Aux->Siguiente;
	}
}
/*
Aplica la gravedad a los enemigos, de esta manera caen
*/
void caerEnemigo(PtrEnemigo& enemigos, PtrPiso suelo) {
	bool soporte;
	PtrEnemigo Aux = enemigos;
	while (Aux != NULL) {
		if (Aux->estado) {
			if (Aux->tipo != 2) {//detecta que no sea un cuadrado enojado, ya que estos vuelan
				soporte = seEncuentraApoyadoE(Aux, suelo, al_get_bitmap_height(sEnemigo[0]), al_get_bitmap_height(barrera), al_get_bitmap_width(sEnemigo[0]), al_get_bitmap_width(barrera), true)
					|| seEncuentraApoyadoE(Aux, suelo, al_get_bitmap_height(sEnemigo[0]), al_get_bitmap_height(barrera), al_get_bitmap_width(sEnemigo[0]), al_get_bitmap_width(barrera), false);
				if (!soporte) {//si el enemigo no está apoyado en una plataforma, se desplaza hacia abajo
					Aux->y++;
				}
			}
		}
		Aux = Aux->Siguiente;
	}
}
/*
Confirma que el enemigo cambie de dirección una vez ha tocado una pared
*/
void confirmaraDireccion(PtrEnemigo& enemigos,PtrPiso suelo, ALLEGRO_BITMAP* pared, ALLEGRO_BITMAP* barrera) {
	PtrEnemigo Aux = enemigos;
	bool choque;
	while (Aux != NULL) {
		if (Aux->tipo != 1) {
			if (!Aux->congelado) {
				choque = choqueDerechaE(Aux, suelo, al_get_bitmap_height(sEnemigo[0]), al_get_bitmap_height(barrera), al_get_bitmap_width(sEnemigo[0]), al_get_bitmap_width(barrera), true)
					|| choqueDerechaE(Aux, suelo, al_get_bitmap_height(sEnemigo[0]), al_get_bitmap_height(pared), al_get_bitmap_width(sEnemigo[0]), al_get_bitmap_width(pared), false)
					|| choqueIzquierdaE(Aux, suelo, al_get_bitmap_height(sEnemigo[0]), al_get_bitmap_height(barrera), al_get_bitmap_width(sEnemigo[0]), al_get_bitmap_width(barrera), true)
					|| choqueIzquierdaE(Aux, suelo, al_get_bitmap_height(sEnemigo[0]), al_get_bitmap_height(pared), al_get_bitmap_width(sEnemigo[0]), al_get_bitmap_width(pared), false);
				if (choque) {//si existe un choque con una pared, cambia de dirección
					if (Aux->dir == derecha) {
						Aux->dir = izquierda;
					}
					else {
						Aux->dir = derecha;
					}
				}
			}
		}
		Aux = Aux->Siguiente;
	}
}
/*
Aumenta la animación de los enemigos, asegurandose de que corresponda a su movimiento
*/
void aumentarEnemigosAni(PtrEnemigo& enemigos) {
	PtrEnemigo Aux = enemigos;
	while (Aux != NULL) {
		if (Aux->estado) {
			if (!Aux->congelado) {
				if (Aux->tipo == 0) {//confirma que sea slime
					Aux->ani++;
					if (Aux->ani > 2) {//confirma que la animación no se salga del rango
						Aux->ani = 0;
					}
				}
			}
		}
		Aux = Aux->Siguiente;
	}
}
/*
Confirma que el jugador está dentro del rango adecuado y crea una bala
*/
void crearBalaEnemiga(PtrEnemigo& enemigos, PtrJugador jugador, PtrBala& listaBalas, int anchoP, int altoP, PtrPiso suelo) {
	bool condicion = false;
	int x1, x2, y1, y2, a, b;
	PtrEnemigo Aux = enemigos;
	int alto, ancho;
	while (Aux != NULL && !condicion) {
		if (Aux->estado && Aux->tipo == 1) {
			alto = al_get_bitmap_height(sEnemigo[Aux->ani]);
			if (jugador->y <= Aux->y + alto / 2 && jugador->y >= Aux->y - alto / 2) {//confirma que el personaje está dentro del rango del cañon
				newBalaE(listaBalas, Aux, al_get_bitmap_width(sEnemigo[Aux->ani]), 6);//crea bala enemiga
				Aux->ani = 9;
				dibujar(jugador, anchoP, altoP, listaBalas, suelo, enemigos);
				dib = false;
			}
			Aux->ani = 8;
		}
		Aux = Aux->Siguiente;
	}
}
/*
Recoge el item que permite aumentar el nivel del traje
*/
void recogerItem(PtrJugador& jugador) {
	int x2, x1, y2, y1, a, b, ancho;
	PtrItem Aux = itemActual;
	if (Aux != NULL) {
		if (Aux->activo) {
			if (Aux->x > jugador->x) {
				x2 = Aux->x;
				y2 = Aux->y;
				x1 = jugador->x;
				y1 = jugador->y;
			}
			else {
				x1 = Aux->x;
				y1 = Aux->y;
				x2 = jugador->x;
				y2 = jugador->y;
			}
			a = pow((x2 - x1), 2);
			b = pow((y2 - y1), 2);

			if (sqrt(a + b) < 15) {//detecta rango de recolección
			
				Aux->activo = false;//deshabilita el item
				jugador->nivel++;//aumenta el nivel del jugador
				jugador->misil = 10;//Resetea el número de misiles
				al_play_sample(sonidos[8], 0.5, 0, 1, ALLEGRO_PLAYMODE_ONCE, NULL);//se reproduce el sonido
				
			}
		}
	}
}
/*
Abre elementos piso que han sido asignados como puertas
*/
void abrirPuerta(PtrPiso& suelo) {
	PtrPiso Aux = suelo;
	while (Aux != NULL) {
		if (Aux->puerta == true) {//Si es una puerta la abre
			Aux->abierto = true;
		}
		Aux = Aux->Siguiente;
	}
	al_play_sample(sonidos[9], 0.5, 0, 1, ALLEGRO_PLAYMODE_ONCE, NULL);//se reproduce el sonido
}
/*
Genera el primer escenario
*/
void generarEscenario0(PtrPiso& suelo) {
	suelo = new piso();
	int y = 575;
	int x = 1175;
	/*No estandar*/
	PtrPiso Aux = suelo;
	Aux->x = 925;
	Aux->y = 400;
	Aux->posicion = true;

	/*piso intermedio*/
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 200;
	Aux->y = y - 180;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 450;
	Aux->y = y - 180;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 700;
	Aux->y = y - 180;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 950;
	Aux->y = y - 180;
	Aux->posicion = true;

	/*lado izquierdo*/
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 0;
	Aux->y = 200;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 0;
	Aux->y = 330;
	Aux->posicion = false;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 0;
	Aux->y = 105;
	Aux->posicion = false;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 0;
	Aux->y = 0;
	Aux->posicion = false;

	/*lado derecho*/
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = x;
	Aux->y = 330;
	Aux->puerta = true;//puerta
	Aux->posicion = false;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = x;
	Aux->y = 205;
	Aux->posicion = false;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = x;
	Aux->y = 0;
	Aux->posicion = false;

	/*
	piso
	*/
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 0;
	Aux->y = y;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 250;
	Aux->y = y;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 500;
	Aux->y = y;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 750;
	Aux->y = y;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 1000;
	Aux->y = y;
	Aux->posicion = true;


	/*Techo*/
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 0;
	Aux->y = 0;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 250;
	Aux->y = 0;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 500;
	Aux->y = 0;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 750;
	Aux->y = 0;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 1000;
	Aux->y = 0;
	Aux->posicion = true;


	/*piso superior*/
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 200;
	Aux->y =200;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 450;
	Aux->y = 200;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 700;
	Aux->y = 200;
	Aux->posicion = true;

	

}
/*
Genera el segundo escenario
*/
void generarEscenario1(PtrPiso& suelo) {
	suelo = new piso();
	int y = 575;
	int x = 1175;
	PtrPiso Aux = suelo;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 400;
	Aux->y = y - 350;
	Aux->posicion = false;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = -50;
	Aux->y = y - 280;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 1000;
	Aux->y = y - 280;
	Aux->posicion = true;
	/*Intermedio*/
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 200;
	Aux->y = y - 120;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 450;
	Aux->y = y - 120;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 700;
	Aux->y = y - 120;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 950;
	Aux->y = y - 120;
	Aux->posicion = true;


	/*lado izquierdo*/
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 0;
	Aux->y = 330;
	Aux->puerta = true;//puerta
	Aux->posicion = false;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 0;
	Aux->y = 205;
	Aux->posicion = false;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 0;
	Aux->y = 0;
	Aux->posicion = false;


	/*lado derecho*/
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = x;
	Aux->y = 330;
	Aux->puerta = true;//puerta
	Aux->posicion = false;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = x;
	Aux->y = 205;
	Aux->posicion = false;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = x;
	Aux->y = 0;
	Aux->posicion = false;


	/*piso*/
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 0;
	Aux->y = y;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 250;
	Aux->y = y;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 500;
	Aux->y = y;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 750;
	Aux->y = y;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 1000;
	Aux->y = y;
	Aux->posicion = true;

	/*techo*/
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 0;
	Aux->y = 0;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 250;
	Aux->y = 0;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 500;
	Aux->y = 0;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 750;
	Aux->y = 0;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 1000;
	Aux->y = 0;
	Aux->posicion = true;

	/*piso superior*/
	
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 450;
	Aux->y = 200;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 300;
	Aux->y = 200;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 700;
	Aux->y = 200;
	Aux->posicion = true;

	

}
/*
Genera el tercer escenario
*/
void generarEscenario2(PtrPiso& suelo) {
	suelo = new piso();
	int y = 575;
	int x = 1175;
	PtrPiso Aux = suelo;

	/*Primer pilar*/
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 400;
	Aux->y = 195;
	Aux->posicion = false;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 400;
	Aux->y = 330;
	Aux->posicion = false;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 300;
	Aux->y = 170;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 300;
	Aux->y = 430;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = -50;
	Aux->y = 300;
	Aux->posicion = true;

	/*Segundo pilar*/
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 950;
	Aux->y = 195;
	Aux->posicion = false;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 950;
	Aux->y = 0;
	Aux->posicion = false;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 700;
	Aux->y = 100;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 700;
	Aux->y = 300;
	Aux->posicion = true; 
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 500;
	Aux->y = 430;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 500;
	Aux->y = 300;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 950;
	Aux->y = 430;
	Aux->posicion = true;

	/*lado izquierdo*/
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 0;
	Aux->y = 330;
	Aux->puerta = true;//puerta
	Aux->posicion = false;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 0;
	Aux->y = 205;
	Aux->posicion = false;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 0;
	Aux->y = 0;
	Aux->posicion = false;


	/*lado derecho*/
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = x;
	Aux->y = 330;
	Aux->puerta = true;//puerta
	Aux->posicion = false;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = x;
	Aux->y = 205;
	Aux->posicion = false;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = x;
	Aux->y = 0;
	Aux->posicion = false;


	/*piso*/
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 0;
	Aux->y = y;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 250;
	Aux->y = y;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 500;
	Aux->y = y;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 750;
	Aux->y = y;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 1000;
	Aux->y = y;
	Aux->posicion = true;

	/*techo*/
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 0;
	Aux->y = 0;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 250;
	Aux->y = 0;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 500;
	Aux->y = 0;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 750;
	Aux->y = 0;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 1000;
	Aux->y = 0;
	Aux->posicion = true;
}
/*
Genera el cuarto escenario
*/
void generarEscenario3(PtrPiso& suelo) {
	suelo = new piso();
	int y = 575;
	int x = 1175;
	PtrPiso Aux = suelo;

	/*Primer pilar*/
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 140;
	Aux->y = 185;
	Aux->posicion = false;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 240;
	Aux->y = 185;
	Aux->posicion = false;
	Aux->puerta = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 75;
	Aux->y = 160;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 300;
	Aux->y = 160;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 525;
	Aux->y = 160;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 750;
	Aux->y = 160;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 75;
	Aux->y = 430;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = -154;
	Aux->y = 370;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 76;
	Aux->y = 430;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 76;
	Aux->y = 300;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = -154;
	Aux->y = 230;
	Aux->posicion = true;

	/*segundo pilar*/
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 875;
	Aux->y = 325;
	Aux->posicion = false;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 940;
	Aux->y = 325;
	Aux->posicion = false;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 875;
	Aux->y = 185;
	Aux->posicion = false;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 940;
	Aux->y = 185;
	Aux->posicion = false;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 825;
	Aux->y = 430;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 1070;
	Aux->y = 370;
	Aux->posicion = true;
	
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 876;
	Aux->y = 300;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 1070;
	Aux->y = 230;
	Aux->posicion = true;


	/*Piso intermedio inferior*/
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 300;
	Aux->y = 430;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 500;
	Aux->y = 405;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 500;
	Aux->y = 430;
	Aux->posicion = true;
	/*Piso intermedio superior*/
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 300;
	Aux->y = 300;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 500;
	Aux->y = 300;
	Aux->posicion = true;

	/*lado izquierdo*/
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 0;
	Aux->y = 330;
	Aux->puerta = true;//puerta
	Aux->posicion = false;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 0;
	Aux->y = 205;
	Aux->posicion = false;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 0;
	Aux->y = 0;
	Aux->posicion = false;


	/*lado derecho*/
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = x;
	Aux->y = 330;
	Aux->puerta = true;//puerta
	Aux->posicion = false;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = x;
	Aux->y = 205;
	Aux->posicion = false;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = x;
	Aux->y = 0;
	Aux->posicion = false;


	/*piso*/
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 0;
	Aux->y = y;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 250;
	Aux->y = y;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 500;
	Aux->y = y;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 750;
	Aux->y = y;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 1000;
	Aux->y = y;
	Aux->posicion = true;

	/*techo*/
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 0;
	Aux->y = 0;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 250;
	Aux->y = 0;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 500;
	Aux->y = 0;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 750;
	Aux->y = 0;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 1000;
	Aux->y = 0;
	Aux->posicion = true;
}
/*
Genera el quinto escenario
*/
void generarEscenario4(PtrPiso& suelo) {
	suelo = new piso();
	int y = 575;
	int x = 1175;
	int y2 = 505;
	PtrPiso Aux = suelo;

	/*Piso intermedio inferior*/
	
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 150;
	Aux->y = 0;
	Aux->posicion = false;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 150;
	Aux->y = 275;
	Aux->posicion = false;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 375;
	Aux->y = 0;
	Aux->posicion = false;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 375;
	Aux->y = 175;
	Aux->posicion = false;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 150;
	Aux->y = 275;
	Aux->posicion = false;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 525;
	Aux->y = 275;
	Aux->posicion = false;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 150;
	Aux->y = 225;
	Aux->posicion = false;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = -75;
	Aux->y = y2-100;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 150;
	Aux->y = y2;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 300;
	Aux->y = y2;
	Aux->posicion = true;
	
	/*Piso intermedio superior*/
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 375;
	Aux->y = 400;
	Aux->posicion = true;
	
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 700;
	Aux->y = 400;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 750;
	Aux->y = 400;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 725;
	Aux->y = 375;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 525;
	Aux->y = 275;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 750;
	Aux->y = 275;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 1000;
	Aux->y = 275;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 750;
	Aux->y = 400;
	Aux->posicion = false;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 975;
	Aux->y = 400;
	Aux->posicion = false;
	

	/*lado izquierdo*/
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 0;
	Aux->y = 330;
	Aux->puerta = true;//puerta
	Aux->posicion = false;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 0;
	Aux->y = 205;
	Aux->posicion = false;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 0;
	Aux->y = 0;
	Aux->posicion = false;


	/*lado derecho*/
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = x;
	Aux->y = 330;
	Aux->puerta = true;//puerta
	Aux->posicion = false;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = x;
	Aux->y = 205;
	Aux->posicion = false;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = x;
	Aux->y = 0;
	Aux->posicion = false;


	/*piso*/
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 0;
	Aux->y = y;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 250;
	Aux->y = y;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 500;
	Aux->y = y;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 750;
	Aux->y = y;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 1000;
	Aux->y = y;
	Aux->posicion = true;

	/*techo*/
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 0;
	Aux->y = 0;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 250;
	Aux->y = 0;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 500;
	Aux->y = 0;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 750;
	Aux->y = 0;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 1000;
	Aux->y = 0;
	Aux->posicion = true;
}
/*
Genera el sexto escenario
*/
void generarEscenario5(PtrPiso& suelo) {
	suelo = new piso();
	int y = 575;
	int x = 1175;
	int y2 = 505;
	PtrPiso Aux = suelo;


	/*Primer pilar*/
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 140;
	Aux->y = 185;
	Aux->posicion = false;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 240;
	Aux->y = 185;
	Aux->posicion = false;
	Aux->puerta = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 75;
	Aux->y = 160;
	Aux->posicion = true;
	Aux->Siguiente = new piso();

	Aux = Aux->Siguiente;
	Aux->x = 525;
	Aux->y = 425;
	Aux->posicion = true;
	Aux->Siguiente = new piso();

	Aux = Aux->Siguiente;
	Aux->x = 525;
	Aux->y = 225;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 525;
	Aux->y = 185;
	Aux->posicion = false;
	
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 750;
	Aux->y = 185;
	Aux->posicion = false;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 300;
	Aux->y = 160;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 750;
	Aux->y = 160;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 75;
	Aux->y = 430;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = -154;
	Aux->y = 370;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 76;
	Aux->y = 430;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 76;
	Aux->y = 300;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = -154;
	Aux->y = 230;
	Aux->posicion = true;
	/*lado izquierdo*/
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 0;
	Aux->y = 330;
	Aux->puerta = true;//puerta
	Aux->posicion = false;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 0;
	Aux->y = 205;
	Aux->posicion = false;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 0;
	Aux->y = 0;
	Aux->posicion = false;

	/*lado derecho izquierdo*/
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = x-200;
	Aux->y = 330;
	Aux->posicion = false;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = x-200;
	Aux->y = 205;
	Aux->posicion = false;
	Aux->posicion = false;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = x - 350;
	Aux->y = 205;
	Aux->posicion = true;

	/*lado derecho*/
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = x;
	Aux->y = 330;
	Aux->puerta = true;//puerta
	Aux->abierto = true;
	Aux->posicion = false;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = x;
	Aux->y = 205;
	Aux->posicion = false;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = x;
	Aux->y = 0;
	Aux->posicion = false;




	/*piso*/
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 0;
	Aux->y = y;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 250;
	Aux->y = y;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 500;
	Aux->y = y;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 750;
	Aux->y = y;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 1000;
	Aux->y = y;
	Aux->posicion = true;

	/*techo*/
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 0;
	Aux->y = 0;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 250;
	Aux->y = 0;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 500;
	Aux->y = 0;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 750;
	Aux->y = 0;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 1000;
	Aux->y = 0;
	Aux->posicion = true;
}
/*
Genera el septimo escenario
*/
void generarEscenario6(PtrPiso& suelo) {
	suelo = new piso();
	int y = 575;
	int x = 1175;
	int y2 = 505;
	PtrPiso Aux = suelo;

	/*lado izquierdo*/
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 0;
	Aux->y = 330;
	Aux->posicion = false;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 0;
	Aux->y = 205;
	Aux->posicion = false;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 0;
	Aux->y = 0;
	Aux->posicion = false;


	/*lado derecho*/
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = x;
	Aux->y = 330;
	Aux->posicion = false;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = x;
	Aux->y = 205;
	Aux->posicion = false;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = x;
	Aux->y = 0;
	Aux->posicion = false;




	/*piso*/
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 0;
	Aux->y = y;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 250;
	Aux->y = y;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 500;
	Aux->y = y;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 750;
	Aux->y = y;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 1000;
	Aux->y = y;
	Aux->posicion = true;

	/*techo*/
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 0;
	Aux->y = 0;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 250;
	Aux->y = 0;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 500;
	Aux->y = 0;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 750;
	Aux->y = 0;
	Aux->posicion = true;
	Aux->Siguiente = new piso();
	Aux = Aux->Siguiente;
	Aux->x = 1000;
	Aux->y = 0;
	Aux->posicion = true;
}
/*
Genera los enemigos pertenecientes al primer escenario
*/
void generarEnemigo0(PtrEnemigo& enemigos) {
	PtrEnemigo Aux;
	enemigos = new enemigo();
	enemigos->dir = derecha;
	enemigos->x = 280;
	enemigos->y = 300;
	enemigos->tipo = 0;
	enemigos->Siguiente = new enemigo();
	Aux = enemigos->Siguiente;
	Aux->dir = derecha;
	Aux->x = 100;
	Aux->y = 400;
	Aux->tipo = 0;
	Aux->Siguiente = new enemigo();
	Aux = Aux->Siguiente;
	Aux->dir = izquierda;
	Aux->x = 750;
	Aux->y = 150;
	Aux->tipo = 0;
}
/*
Genera los enemigos pertenecientes al segundo escenario
*/
void generarEnemigo1(PtrEnemigo& enemigos) {
	PtrEnemigo Aux;
	enemigos = new enemigo();
	enemigos->dir = derecha;
	enemigos->x = 900;
	enemigos->y = 400;
	enemigos->tipo = 0;
	enemigos->Siguiente = new enemigo();
	Aux = enemigos->Siguiente;
	Aux->dir = derecha;
	Aux->x = 500;
	Aux->y = 400;
	Aux->tipo = 0;
	Aux->Siguiente = new enemigo();
	Aux = Aux->Siguiente;
	Aux->dir = izquierda;
	Aux->x = 900;
	Aux->y = 500;
	Aux->hp = 1;
	Aux->tipo = 1;
	Aux->ani = 8;
	Aux->atk = 20;
}
/*
Genera los enemigos pertenecientes al tercer escenario
*/
void generarEnemigo2(PtrEnemigo& enemigos) {
	PtrEnemigo Aux;
	enemigos = new enemigo();
	Aux = enemigos;
	Aux->dir = izquierda;
	Aux->x = 1100;
	Aux->y = 500;
	Aux->hp = 1;
	Aux->tipo = 1;
	Aux->ani = 8;
	Aux->atk = 20;
	Aux->Siguiente = new enemigo();
	Aux = Aux->Siguiente;
	Aux->dir = derecha;
	Aux->x = 145;
	Aux->y = 100;
	Aux->hp = 1;
	Aux->tipo = 1;
	Aux->ani = 8;
	Aux->atk = 20;
	Aux->Siguiente = new enemigo();
	Aux = Aux->Siguiente;
	Aux->dir = izquierda;
	Aux->x = 350;
	Aux->y = 300;
	Aux->hp = 1;
	Aux->tipo = 1;
	Aux->ani = 8;
	Aux->atk = 20;
	Aux->Siguiente = new enemigo();
	Aux = Aux->Siguiente;
	Aux->dir = izquierda;
	Aux->x = 500;
	Aux->y = 100;
	Aux->hp = 1;
	Aux->tipo = 1;
	Aux->ani = 8;
	Aux->atk = 20;
	Aux->Siguiente = new enemigo();
	Aux = Aux->Siguiente;
	Aux->dir = izquierda;
	Aux->x = 900;
	Aux->y = 50;
	Aux->hp = 1;
	Aux->tipo = 1;
	Aux->ani = 8;
	Aux->atk = 20;
	Aux->Siguiente = new enemigo();
	Aux = Aux->Siguiente;
	Aux->dir = izquierda;
	Aux->x = 900;
	Aux->y = 200;
	Aux->hp = 1;
	Aux->tipo = 1;
	Aux->ani = 8;
	Aux->atk = 20;
	Aux->Siguiente = new enemigo();
	Aux = Aux->Siguiente;
	Aux->dir = izquierda;
	Aux->x = 700;
	Aux->y = 380;
	Aux->hp = 1;
	Aux->tipo = 1;
	Aux->ani = 8;
	Aux->atk = 20;
}
/*
Genera los enemigos pertenecientes al cuarto escenario
*/
void generarEnemigo3(PtrEnemigo& enemigos) {
	PtrEnemigo Aux;
	enemigos = new enemigo();
	Aux = enemigos;
	Aux->dir = derecha;
	Aux->x = 325;
	Aux->y = 350;
	Aux->hp = 1;
	Aux->tipo = 1;
	Aux->ani = 8;
	Aux->atk = 20;

	Aux->Siguiente = new enemigo();
	Aux = Aux->Siguiente;
	Aux->dir = derecha;
	Aux->x = 325;
	Aux->y = 240;
	Aux->hp = 1;
	Aux->tipo = 1;
	Aux->ani = 8;
	Aux->atk = 20;
	Aux->Siguiente = new enemigo();
	Aux = Aux->Siguiente;
	Aux->dir = derecha;
	Aux->x = 355;
	Aux->y = 350;
	Aux->tipo = 0;
	Aux->Siguiente = new enemigo();
	Aux = Aux->Siguiente;
	Aux->dir = derecha;
	Aux->x = 325;
	Aux->y = 350;
	Aux->tipo = 0;
	
}
/*
Genera los enemigos pertenecientes al quinto escenario
*/
void generarEnemigo4(PtrEnemigo& enemigos) {
	PtrEnemigo Aux;
	
	enemigos = new enemigo();
	Aux = enemigos;
	Aux->dir = derecha;
	Aux->x = 625;
	Aux->y = 350;
	Aux->tipo = 0;

	Aux->Siguiente = new enemigo();
	Aux = Aux->Siguiente;
	Aux->dir = izquierda;
	Aux->x = 975;
	Aux->y = 360;
	Aux->hp = 1;
	Aux->tipo = 1;
	Aux->ani = 8;
	Aux->atk = 20;
	

}
/*
Genera los enemigos pertenecientes al sexto escenario
*/
void generarEnemigo5(PtrEnemigo& enemigos) {
	PtrEnemigo Aux;
	enemigos = new enemigo();
	Aux = enemigos;
	Aux->dir = derecha;
	Aux->x = 625;
	Aux->y = 150;
	Aux->tipo = 2;
	Aux->ani = 12;
	Aux->atk = 5;
	Aux->Siguiente = new enemigo();
	Aux = Aux->Siguiente;
	Aux->dir = derecha;
	Aux->x = 325;
	Aux->y = 350;
	Aux->hp = 1;
	Aux->tipo = 1;
	Aux->ani = 8;
	Aux->atk = 20;
	Aux->Siguiente = new enemigo();
	Aux = Aux->Siguiente;
	Aux->dir = derecha;
	Aux->x = 625;
	Aux->y = 400;
	Aux->tipo = 0;
	Aux->Siguiente = new enemigo();
	Aux = Aux->Siguiente;
	Aux->dir = derecha;
	Aux->x = 300;
	Aux->y = 480;
	Aux->tipo = 0;
}
/*
Genera los enemigos pertenecientes al septimo escenario
*/
void generarEnemigo6(PtrEnemigo& enemigos) {
	PtrEnemigo Aux;
	enemigos = new enemigo();
	Aux = enemigos;
	Aux->dir = derecha;
	Aux->x = 625;
	Aux->y = 480;
	Aux->tipo = 2;
	Aux->ani = 12;
	Aux->atk = 5;
	Aux->Siguiente = new enemigo();
	Aux = Aux->Siguiente;
	Aux->dir = izquierda;
	Aux->x = 300;
	Aux->y = 300;
	Aux->tipo = 2;
	Aux->ani = 12;
	Aux->atk = 5;
	Aux->Siguiente = new enemigo();
	Aux = Aux->Siguiente;
	Aux->dir = derecha;
	Aux->x = 900;
	Aux->y = 200;
	Aux->tipo = 2;
	Aux->ani = 12;
	Aux->atk = 5;
}
/*
Carga los escenarios
*/
void cargarEscenario(PtrPiso& suelo, PtrEnemigo& enemigos) {
	switch (escenario) {
	case 0:
		generarEnemigo0(enemigos);
		generarEscenario0(suelo);
		break;
	case 1:
		generarEnemigo1(enemigos);
		generarEscenario1(suelo);
		itemActual = item1;//carga el item del escenario
		break;
	case 2:
		generarEnemigo2(enemigos);
		generarEscenario2(suelo);
		break;
	case 3:
		generarEnemigo3(enemigos);
		generarEscenario3(suelo);
		itemActual = item2;
		break;
	case 4:
		generarEnemigo4(enemigos);
		generarEscenario4(suelo);
		break;

	case 5:
		generarEnemigo5(enemigos);
		generarEscenario5(suelo);
		itemActual = item3;
		break;
	case 6:
		generarEnemigo6(enemigos);
		generarEscenario6(suelo);
		itemActual = trofeo;
		break;
	}

}


void juego() {
	
	if (!al_init()) {
		al_show_native_message_box(NULL, "Ventana Emergente", "Error", "No se puede inicializar Allegro", NULL, NULL);
		return;
	}

	al_init_font_addon();
	al_init_ttf_addon();
	al_init_image_addon();
	al_init_primitives_addon();
	al_install_keyboard();
	al_install_audio();
	al_init_acodec_addon();
	al_reserve_samples(20);
	
	int tipoB=0;
	int ResX = 800;
	int ResY = 50;

	ALLEGRO_EVENT_QUEUE* colaEventos = al_create_event_queue();

	ALLEGRO_TIMER* timer = al_create_timer(1 / FPS);
	ALLEGRO_TIMER* segundotimer = al_create_timer(1 / FPS2);
	ALLEGRO_TIMER* timer2 = al_create_timer(25 / FPS);
	ALLEGRO_TIMER* timerMovimientoEnemigo = al_create_timer(1.5 / FPS2);
	ALLEGRO_TIMER* timerAniPer = al_create_timer(8 / FPS);
	Fondo = al_load_bitmap("Fondo.jpg");
	barrera = al_load_bitmap("suelo.png");
	pared = al_load_bitmap("VBarrera.png");
	Fuente = al_load_font("pixel.ttf", 40, NULL);
	Fuente2 = al_load_font("pixel.ttf", 20, NULL);
	sItem = al_load_bitmap("imagenes/i.png");
	sTrofeo = al_load_bitmap("imagenes/t.png");
	ALLEGRO_SAMPLE* musica = al_load_sample("musica/Arsenal.mp3");
	Personaje[0] = al_load_bitmap("imagenes/Samus/s0.png");
	Personaje[1] = al_load_bitmap("imagenes/Samus/s1.png");
	Personaje[2] = al_load_bitmap("imagenes/Samus/s2.png");
	Personaje[3] = al_load_bitmap("imagenes/Samus/s3.png");
	Personaje[4] = al_load_bitmap("imagenes/Samus/s4.png");
	Personaje[5] = al_load_bitmap("imagenes/Samus/s5.png");
	Personaje[6] = al_load_bitmap("imagenes/Samus/s6.png");
	Personaje[7] = al_load_bitmap("imagenes/Samus/s7.png");
	Personaje[8] = al_load_bitmap("imagenes/Samus/s8.png");
	Personaje[9] = al_load_bitmap("imagenes/Samus/s9.png");
	Personaje[10] = al_load_bitmap("imagenes/Samus/s10.png");
	sEnemigo[0]= al_load_bitmap("imagenes/slime/s0.png");
	sEnemigo[1] = al_load_bitmap("imagenes/slime/s1.png");
	sEnemigo[2] = al_load_bitmap("imagenes/slime/s2.png");
	sEnemigo[3] = al_load_bitmap("imagenes/slime/s3.png");
	sEnemigo[4] = al_load_bitmap("imagenes/slime/s4.png");
	sEnemigo[5] = al_load_bitmap("imagenes/slime/s5.png");
	sEnemigo[6] = al_load_bitmap("imagenes/slime/s6.png");
	sEnemigo[7] = al_load_bitmap("imagenes/slime/s7.png");
	sEnemigo[8] = al_load_bitmap("imagenes/slime/c1.png");
	sEnemigo[9] = al_load_bitmap("imagenes/slime/c2.png");
	sEnemigo[10] = al_load_bitmap("imagenes/slime/c3.png");
	sEnemigo[11] = al_load_bitmap("imagenes/slime/c4.png");
	sEnemigo[12] = al_load_bitmap("imagenes/slime/v.png");
	sBalas[0]= al_load_bitmap("imagenes/balas/bala.png");
	sBalas[1] = al_load_bitmap("imagenes/balas/bala2.png");
	sBalas[2] = al_load_bitmap("imagenes/balas/bala3.png");
	sBalas[3] = al_load_bitmap("imagenes/balas/bala4.png");
	sBalas[4] = al_load_bitmap("imagenes/balas/bala5.png");
	sBalas[5] = al_load_bitmap("imagenes/balas/bala6.png");
	sBalas[6] = al_load_bitmap("imagenes/balas/b7.png");
	sBalas[7] = al_load_bitmap("imagenes/balas/b8.png");
	sonidos[0] = al_load_sample("musica/disparo.mp3");
	sonidos[1] = al_load_sample("musica/cDispara.mp3");
	sonidos[2] = al_load_sample("musica/cIleso.mp3");
	sonidos[3] = al_load_sample("musica/cMuerte.mp3");
	sonidos[4] = al_load_sample("musica/sAtacado.mp3");
	sonidos[5] = al_load_sample("musica/sMuere.wav");
	sonidos[6] = al_load_sample("musica/pAtacado.mp3");
	sonidos[7] = al_load_sample("musica/explosion.mp3");
	sonidos[8] = al_load_sample("musica/levelUp.mp3");
	sonidos[9] = al_load_sample("musica/pAbierta.mp3");
	sonidos[10] = al_load_sample("musica/congelar.mp3");
	sonidos[11] = al_load_sample("musica/ghast.mp3");
	al_register_event_source(colaEventos, al_get_timer_event_source(timer));
	al_register_event_source(colaEventos, al_get_timer_event_source(timer2));
	al_register_event_source(colaEventos, al_get_timer_event_source(timerMovimientoEnemigo));
	al_register_event_source(colaEventos, al_get_timer_event_source(segundotimer));
	al_register_event_source(colaEventos, al_get_timer_event_source(timerAniPer));
	al_register_event_source(colaEventos, al_get_keyboard_event_source());
	PtrPiso suelo = NULL;
	PtrEnemigo enemigos = NULL;
	generarEnemigo0(enemigos);
	generarEscenario0(suelo);
	PtrBala listaBalas = NULL;
	PtrJugador jugador = new Jugador();
	jugador->x = 51;
	jugador->y = 100;
	int cont = 1;
	bool soporte = false;
	bool hecho = true;
	bool techo = false;
	bool choque = false;
	bool disparo = false;
	bool mover = false;
	bool standby = false;
	bool misil = false;
	bool recargado = false;
	bool puertasAbiertas = false;
	bool existeEnemigo;
	int anchoP;
	int altoP;
	bool rayoGelido = false;
	float seg = 0;
	item1 = new item();
	item1->x = 500;
	item1->y = 400;
	item2 = new item();
	item2->x = 190;
	item2->y = 250;
	item3 = new item();
	item3->x = 190;
	item3->y = 250;
	trofeo = new item();
	trofeo->x = 250;
	trofeo->y = 100;
	trofeo->trofeo = true;//define a trofeo como item que gana la partida
	al_start_timer(segundotimer);
	al_start_timer(timer);
	al_start_timer(timer2);
	al_start_timer(timerMovimientoEnemigo);
	al_start_timer(timerAniPer);
	anchoP = al_get_bitmap_width(Personaje[0]);
	altoP = al_get_bitmap_height(Personaje[0]);
	jugador->dir = derecha;
	al_play_sample(musica, 0.2, 0, 1, ALLEGRO_PLAYMODE_LOOP, NULL);
	ALLEGRO_KEYBOARD_STATE estadoTeclado;
	
	while (hecho) {
		ALLEGRO_EVENT eventos;
		
		al_get_keyboard_state(&estadoTeclado);
		al_wait_for_event(colaEventos, &eventos);
		if (eventos.type == ALLEGRO_EVENT_TIMER) {
			//soporte = seEncuentraApoyado(PosJugX,PosJugY,ResY);
			/*
			Define si el jugador puede ser afectado por la gravedad
			*/
			soporte = seEncuentraApoyado(jugador, suelo, altoP,al_get_bitmap_height(barrera), anchoP, al_get_bitmap_width(barrera),true)
				|| seEncuentraApoyado(jugador, suelo, altoP, al_get_bitmap_height(pared), anchoP, al_get_bitmap_width(pared), false);
			/*
			Define si puede seguir saltando
			*/
			techo = tocaTecho(jugador, suelo, altoP, al_get_bitmap_height(barrera),anchoP, al_get_bitmap_width(barrera), true);
				
			if (eventos.timer.source == timer) {
				al_get_keyboard_state(&estadoTeclado);
				/*Se convierte en bolita si tiene el nivel adecuado*/
				if ((al_key_down(&estadoTeclado, ALLEGRO_KEY_DOWN) && !salto) || (al_key_down(&estadoTeclado, ALLEGRO_KEY_S) && !salto) && jugador->nivel > 1) {
					jugador->bolita = true;
					jugador->ani = 10;
					anchoP = al_get_bitmap_width(Personaje[10]);//define el ancho correspondiente al sprite
					altoP = al_get_bitmap_height(Personaje[10]);//define el alto correspondiente al sprite
				}
				/*
				Define si puede salir del modo bolita
				Si está en movimiento, no se puede salir
				*/
				if (al_key_down(&estadoTeclado, ALLEGRO_KEY_Q) &&jugador->bolita&&!salto && standby&&!(al_key_down(&estadoTeclado, ALLEGRO_KEY_DOWN) && !salto) && !(al_key_down(&estadoTeclado, ALLEGRO_KEY_S))) {
					/*Confirma que no haya una plataforma arriba, si la hay no puede salir del modo bolita*/
					if (puedePararse(jugador, suelo, altoP, al_get_bitmap_height(barrera), anchoP, al_get_bitmap_width(barrera), true)) {
						jugador->y -= 35;//reajusta la posición del jugador
						jugador->bolita = false;
					}		
				}
				/*Habilita los misiles si tiene el nivel suficiente*/
				if (al_key_down(&estadoTeclado, ALLEGRO_KEY_LCTRL)&& jugador->nivel>0) {
					misil = true;
				}
				/*Cuando se deja de presionar LCTRL se deshabilitan los misiles*/
				if (!al_key_down(&estadoTeclado, ALLEGRO_KEY_LCTRL)) {
					misil = false;
				}
				/*Habilita el rayo gelido si tiene el nivel suficiente*/
				if (al_key_down(&estadoTeclado, ALLEGRO_KEY_LSHIFT) && jugador->nivel > 2) {
					rayoGelido = true;
				}
				/*Cuando se deja de presionar LSHIFT se deshabilita el rayo gelido*/
				if (!al_key_down(&estadoTeclado, ALLEGRO_KEY_LSHIFT)) {
					rayoGelido = false;
				}
				/*Crea una bala si está en modo bolita o carga el ataque si no está en dicho modo*/
				if (al_key_down(&estadoTeclado, ALLEGRO_KEY_SPACE)) {
					if (jugador->bolita) {//confirma modo bolita
						if (!confirmarBombaExiste(listaBalas)) {
							tipoB = 4;//define la bala para que sea bomba
							newBala(listaBalas, jugador, 0, tipoB);	
						}
					}
					else {
						disparo = true;
						seg += 1;//Aumenta el disparo cargado
					}	
				}
				/*Genera el disparo*/
				if (!al_key_down(&estadoTeclado, ALLEGRO_KEY_SPACE)&&disparo && !jugador->bolita) {
					/*Define el disparo según su tiempo de carga*/
					if (seg < 20) {
						tipoB = 0;//azul
					}
					else if (seg > 35) {
						tipoB = 2;//Creeper
					}
					else {
						tipoB = 1;//verde
					}
					if (misil&&jugador->misil!=0) {//dispara misil
						tipoB = 3;
						jugador->misil--;
					}
					if (rayoGelido) {//dispara rayo gelido
						tipoB = 7;
					}
					newBala(listaBalas, jugador, anchoP/2, tipoB);
					disparo = false;
					seg = 0;
					
				}
				contador = contador + 0.0166666667;//Aumenta el contador de segundos
			}
			if (eventos.timer.source == segundotimer) {
				al_get_keyboard_state(&estadoTeclado);
				/*El personaje salta al precionar espacio*/
				if ((al_key_down(&estadoTeclado, ALLEGRO_KEY_UP) && !salto) || (al_key_down(&estadoTeclado, ALLEGRO_KEY_W) && !salto) || ressalto > 0) {
					if (ressalto > 0) {//impulsa en el aire  de manera continúa
						ressalto--;//disminuye el tiempo de impulso
						salto = true;//deshabilita el salto
					}
					if (techo) {
						ressalto = 0;//anula el salto si toca techo
						jugador->y -= 2;//reacomoda el jugador
					}
					if (!salto && !techo) {
						ressalto = 90;//define el tiempo de salto
					}
					jugador->y -= 2;//asciende aljugador
				}
				if (al_key_down(&estadoTeclado, ALLEGRO_KEY_RIGHT) || al_key_down(&estadoTeclado, ALLEGRO_KEY_D) || resde > 0) {
					choque = choqueDerecha(jugador, suelo, altoP, al_get_bitmap_height(barrera), anchoP, al_get_bitmap_width(barrera), true)
						|| choqueDerecha(jugador, suelo, altoP, al_get_bitmap_height(pared), anchoP, al_get_bitmap_width(pared), false);
					if (!choque) {
						//si no impacta con una pared, puede desplazarse
						if (resde > 0) {//fuerza de retroceso cuando es golpeado por un enemigo
							resde--;
							jugador->x += 2;
						}
						else {//desplazamiento normal
							jugador->x += 1;
							jugador->dir = derecha;
							standby = false;//detecta que se mueve
						}
						
					}
					else {//elimina la fuerza de retroceso si golpea con una pared
						resde = 0;
					}


				}
				if (al_key_down(&estadoTeclado, ALLEGRO_KEY_LEFT) || al_key_down(&estadoTeclado, ALLEGRO_KEY_A) || resiz > 0) {
					choque = choqueIzquierda(jugador, suelo, altoP, al_get_bitmap_height(barrera), anchoP, al_get_bitmap_width(barrera), true)
						|| choqueIzquierda(jugador, suelo, altoP, al_get_bitmap_height(pared), anchoP, al_get_bitmap_width(pared), false);
					if (!choque) {
						//si no impacta con una pared, puede desplazarse
						if (resiz > 0) {//fuerza de retroceso cuando es golpeado por un enemigo
							resiz--;
							jugador->x -= 2;
						}
						else {//desplazamiento normal
							jugador->x -= 1;
							jugador->dir = izquierda;
							standby = false;//detecta que se mueve
						}
					}
					else {//elimina la fuerza de retroceso si golpea con una pared
						resde = 0;
					}
				}
				if (!(al_key_down(&estadoTeclado, ALLEGRO_KEY_RIGHT) || al_key_down(&estadoTeclado, ALLEGRO_KEY_D))&&!(al_key_down(&estadoTeclado, ALLEGRO_KEY_LEFT) || al_key_down(&estadoTeclado, ALLEGRO_KEY_A))) {
					//detecta si no se está moviendo
					mover = false;
					/*Define el sprite según la dirección y si está en bolita*/
					if (jugador->dir == derecha&&jugador->bolita) {
						jugador->ani = 10;
						anchoP = al_get_bitmap_width(Personaje[10]);
						altoP = al_get_bitmap_height(Personaje[10]);
					}
					if (jugador->dir == derecha && !jugador->bolita) {
						jugador->ani = 0;
						anchoP = al_get_bitmap_width(Personaje[0]);
						altoP = al_get_bitmap_height(Personaje[0]);
					}
					if (jugador->dir == izquierda && !jugador->bolita) {
						jugador->ani = 9;
						anchoP = al_get_bitmap_width(Personaje[9]);
						altoP = al_get_bitmap_height(Personaje[9]);
					}
					standby=true;//define que está parado
				}
				aumentarExplosion(enemigos, listaBalas);
				colisionEnemigoJugador(enemigos, jugador,suelo);
			}
			if ((eventos.timer.source == timerMovimientoEnemigo)) {
				moverEnemigos(enemigos);
				confirmaraDireccion(enemigos, suelo, pared, barrera);	
			}
			if ((eventos.timer.source == timerAniPer)) {
				/*Aumenta la animación del personaje y evita que se salga de rango*/
				if (jugador->dir == izquierda&&!standby && ! jugador->bolita) {
					if (mover) {//confirma que el jugador ya se movió una vez
						jugador->ani++;
						if (jugador->ani > 8) {
							jugador->ani = 5;
						}
					}
					else {//de lo contrario asigna el sprite correspondiente a la dirección izquierda
						jugador->ani = 5;
						mover = true;
					}
				}
				if (jugador->dir == derecha&&!standby && !jugador->bolita) {

					if (mover) {//confirma que el jugador ya se movió una vez
						jugador->ani++;
						if (jugador->ani > 4) {
							jugador->ani = 1;
						}
					}
					else {//de lo contrario asigna el sprite correspondiente a la dirección derecha
						jugador->ani = 1;
						mover = true;
					}
				}
				aumentarBalasAni(listaBalas);
			}
			if ((eventos.timer.source == timer2)) {
				crearBalaEnemiga(enemigos, jugador, listaBalas, anchoP, altoP, suelo);
				aumentarEnemigosAni(enemigos);
			}
			if ((eventos.timer.source == segundotimer)) {
				//¡Gravedad!
				caerEnemigo(enemigos, suelo);
				if (!soporte && ressalto<=0) {//confirma que el jugador está en el aire y no está saltando
					if (ressalto >= -30) {
						ressalto--;
					}
					else {
						jugador->y += 2;
					}
				}
				moverBalas(listaBalas);
				impactoBala(enemigos, listaBalas, suelo, jugador, altoP, anchoP);
			}
		}
		/*Recarga la vida y misiles del jugador cada 5 segundos*/
		if ((int) contador % 5 == 0&&!recargado) {
			if (jugador->nivel > 0 && jugador->misil < 10) {//Confirma que tenga el nivel adecuado y no exceda los diez misiles
				jugador->misil++;
			}
			if (jugador->hp < 100){
				jugador->hp += 5;
				if (jugador->hp > 100) {//Confirma que no exceda los 100 de vida
					jugador->hp = 100;
				}
			}
			recargado = true;//deshabilita el recargado
		}
		if ((int)contador % 5 == 1) {//activa la opción de recargar energía
			recargado = false;
		}

		existeEnemigo = confirmarEnemigoExiste(enemigos);
		if (!existeEnemigo && !puertasAbiertas) {//si no existen enemigos y no se han abierto las puertas, las abre
			abrirPuerta(suelo);
			puertasAbiertas = true;
		}
		if (jugador->x > 1250) {//define si el jugador avanza un escenario 
			itemActual = NULL;//desparece el item actual
			borrarElementos(listaBalas,enemigos, suelo);
			escenario++;//Aumenta el escenario para cargar el siguiente
			cargarEscenario(suelo,enemigos);
			jugador->x = 51;
			puertasAbiertas = false;//cierra las puertas
		}
		if (jugador->x < 0) {//define si el jugadorretrocede un escenario 
			itemActual = NULL;
			borrarElementos(listaBalas, enemigos, suelo);
			escenario--;//Disminuye el escenario para cargar el anterior
			cargarEscenario(suelo, enemigos);
			jugador->x = 1180;
			puertasAbiertas = false;
		}
		recogerItem(jugador);
		if (itemActual != NULL) {
			/*Detecta si se agarró el trofeo y termina el juego, le pide el nombre al usuario y guarda las estadísticas*/
			if (!itemActual->activo && itemActual->trofeo) {
				al_get_keyboard_state(&estadoTeclado);
				char nombre[40];
				nombre[0] = char(95);
				char letra;
				int pos = 0;
				while (hecho) {
					al_wait_for_event(colaEventos,&eventos);
					al_clear_to_color(al_map_rgb(0, 0, 0));
					string caracteres = "Segundos sobrevividos: " + to_string(contador);
					const char* b = caracteres.c_str();
					string hileraGenerada = "Cantidad de balas acertadas: " + to_string(balasAcertadas);
					const char* c = hileraGenerada.c_str();
					string hileras = "Cantidad de balas perdidas: " + to_string(balasPerdidas);
					const char* d = hileras.c_str();
					if (eventos.type == ALLEGRO_EVENT_KEY_CHAR) {
						if (pos != 40) {//limite del nombre
							//Si se preciona la tecla BACKSPACE borra el caracter y disminuye en 1 la posicion
							if (eventos.keyboard.keycode == ALLEGRO_KEY_BACKSPACE) {
								if (pos != 0) {
									nombre[pos] = NULL;
									pos = pos - 1;

								}
							}
							//Se preciona ENTER para terminar
							else if (eventos.keyboard.keycode == ALLEGRO_KEY_ENTER) {
								hecho = false;
							}
							//Se inhabilitan las teclas LEFT, RIGHT, TAB, ESCAPE
							else if (eventos.keyboard.keycode == ALLEGRO_KEY_LEFT) {
							}
							else if (eventos.keyboard.keycode == ALLEGRO_KEY_RIGHT) {
							}
							else if (eventos.keyboard.keycode == ALLEGRO_KEY_TAB) {
							}
							else if (eventos.keyboard.keycode == ALLEGRO_KEY_ESCAPE) {
							}
							//Si se preciona espacio se procede a imprimer "_"
							else if (eventos.keyboard.keycode == ALLEGRO_KEY_SPACE) {
								nombre[pos] = char(95);
								pos = pos + 1;
							}
							else {
								//Si no se preciona alguna de las teclas anteriores (se preciona un caracter unicode) se procede a escribirlo en el nombre
								if (eventos.keyboard.unichar != ALLEGRO_KEY_BACKSPACE) {
									nombre[pos] = eventos.keyboard.unichar;
									pos = pos + 1;
								}
							}
						}
					}
					//Para señalar la ubicacion de escritura se coloca un caracter al final del nombre
					nombre[pos] = '<';
					nombre[pos + 1] = NULL;
					//Muestra en pantalla las estadisticas
					al_draw_text(Fuente, al_map_rgb(0, 100, 0), 600, (510 * (250.0 / 768.0) - 50), ALLEGRO_ALIGN_CENTRE, "Felicitaciones, has ganado");
					al_draw_text(Fuente, al_map_rgb(0, 100, 0), 600, (510 * (250.0 / 768.0)), ALLEGRO_ALIGN_CENTRE, b);
					al_draw_text(Fuente, al_map_rgb(0, 100, 0), 600, (510 * (250.0 / 768.0)) + 30, ALLEGRO_ALIGN_CENTRE, c);
					al_draw_text(Fuente, al_map_rgb(0, 100, 0), 600, (510 * (250.0 / 768.0)) + 55, ALLEGRO_ALIGN_CENTRE, d);
					al_draw_text(Fuente, al_map_rgb(0, 100, 0), 600, (510 * (250.0 / 768.0)) + 150, ALLEGRO_ALIGN_CENTRE, nombre);
					al_draw_text(Fuente, al_map_rgb(0, 100, 0), 600, (510 * (250.0 / 768.0))+200, ALLEGRO_ALIGN_CENTRE, "Presione Enter para finalizar");
					al_draw_text(Fuente, al_map_rgb(255, 255, 255),  600, (510 * (250.0 / 768.0)) + 115, ALLEGRO_ALIGN_CENTRE, "Ingrese su nombre");
					al_flip_display();
				}
				nombre[pos] = NULL;//elimina el <
				guardarDatos(nombre);
			}
		}
		dibujar(jugador, anchoP, altoP, listaBalas, suelo, enemigos);
		/*Termina el juego si el jugador muere o se preciona escape, no guarda datos*/
		if (jugador->hp <= 0 || al_key_down(&estadoTeclado, ALLEGRO_KEY_ESCAPE)){
			while (hecho) {
				/*Muestra las estadísticas*/
				al_get_keyboard_state(&estadoTeclado);
				al_clear_to_color(al_map_rgb(0, 0, 0));
				al_wait_for_event(colaEventos, &eventos);
				string caracteres = "Segundos sobrevividos: " + to_string(contador);
				const char* b = caracteres.c_str();
				string hileraGenerada = "Cantidad de balas acertadas: " + to_string(balasAcertadas);
				const char* c = hileraGenerada.c_str();
				string hileras = "Cantidad de balas perdidas: " + to_string(balasPerdidas);
				const char* d = hileras.c_str();
				al_draw_text(Fuente, al_map_rgb(0, 100, 0), 600, (510 * (250.0 / 768.0) - 50), ALLEGRO_ALIGN_CENTRE, b);
				al_draw_text(Fuente, al_map_rgb(0, 100, 0), 600, (510 * (250.0 / 768.0)), ALLEGRO_ALIGN_CENTRE, c);
				al_draw_text(Fuente, al_map_rgb(0, 150, 0), 600, (510 * (250.0 / 768.0) + 50), ALLEGRO_ALIGN_CENTRE, d);
				al_draw_text(Fuente2, al_map_rgb(0, 150, 0), 600, (510 * (250.0 / 768.0) + 200), ALLEGRO_ALIGN_CENTRE, "Presione enter para salir...");
				if (al_key_down(&estadoTeclado, ALLEGRO_KEY_ENTER)) {
					hecho = false;
				}
				al_flip_display();
			}
		}
		
	}
	
	al_destroy_event_queue(colaEventos);
	al_destroy_font(Fuente);
	al_destroy_font(Fuente2);
	al_destroy_timer(timer);
	al_destroy_timer(segundotimer);
	al_destroy_sample(musica);
	//al_destroy_display(pantalla);
}