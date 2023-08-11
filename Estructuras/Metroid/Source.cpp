#include "Funciones.h"
#include <stdio.h>
#include <iostream>
#include <math.h>
#include <windows.h>
#include <stdlib.h>
#include <cstdlib>
#include <time.h>
#include <string>
#include <string.h>
typedef struct datos {
	char nombre[60];
	int balasPerdidas;
	int balasAcertadas;
	float segundos;
	datos* Siguiente;
	datos* Anterior;
}*PtrDatos;

/*Agrega datos a lista enlazada*/
void cargarDatos(PtrDatos& dato) {
	FILE* archivo;
	dato = NULL;
	PtrDatos Aux=dato;
	fopen_s(&archivo, "bitacora.txt", "r");
	if (NULL == archivo) {
	}
	else {
		while (!feof(archivo)) {
			if (Aux == NULL) {
				dato = new datos();
				Aux = dato;
				Aux->Anterior = NULL;
			}
			else {
				Aux->Siguiente = new datos();
				Aux->Siguiente->Anterior = Aux;
				Aux = Aux->Siguiente;
			}
			fscanf_s(archivo, "%s", Aux->nombre, _countof(Aux->nombre));
			fscanf_s(archivo, "%f\n", &Aux->segundos);
			fscanf_s(archivo, "%i\n", &Aux->balasAcertadas);
			fscanf_s(archivo, "%i\n", &Aux->balasPerdidas);
			cout << Aux->nombre;
			Aux->Siguiente = NULL;
		}
	}
	fclose(archivo);
	//Se cierra el archivo


}

void main()
{
	if (!al_init()) {
		al_show_native_message_box(NULL, "Ventana Emergente", "Error", "No se puede inicializar Allegro", NULL, NULL);
		return;
	}
	PtrDatos dato;
	al_init_font_addon();
	al_init_ttf_addon();
	al_init_image_addon();
	al_init_primitives_addon();
	al_install_keyboard();
	al_install_mouse();

	int ResX = 1200;
	int ResY = 600;
	int mousex = 0;
	int mousey = 0;

	ALLEGRO_MONITOR_INFO monitor;
	al_get_monitor_info(0, &monitor);
	const int RX = monitor.x2 - monitor.x1;
	const int RY = monitor.y2 - monitor.y1;

	ALLEGRO_DISPLAY* pantalla = al_create_display(ResX, ResY);
	al_set_window_position(pantalla, 100, 50);
	al_set_window_title(pantalla, "Juego");

	if (!pantalla)
	{
		al_show_native_message_box(NULL, "Ventana Emergente", "Error", "No se puede crear la pantalla", NULL, ALLEGRO_MESSAGEBOX_ERROR);
		return;
	}

	ALLEGRO_EVENT_QUEUE* cola_eventos = al_create_event_queue();

	ALLEGRO_TIMER* timer = al_create_timer(1.0 / FPS);
	ALLEGRO_TIMER* timer2 = al_create_timer(5.0 / FPS);
	ALLEGRO_FONT* Fuente = al_load_font("pixel.ttf", 40, NULL);
	ALLEGRO_FONT* Fuente2 = al_load_font("pixel.ttf", 20, NULL);
	al_register_event_source(cola_eventos, al_get_timer_event_source(timer));
	al_register_event_source(cola_eventos, al_get_timer_event_source(timer2));
	al_register_event_source(cola_eventos, al_get_display_event_source(pantalla));
	al_register_event_source(cola_eventos, al_get_keyboard_event_source());
	al_register_event_source(cola_eventos, al_get_mouse_event_source());
	bool presionado = false;
	bool hecho = true;
	bool instr = true;
	ALLEGRO_KEYBOARD_STATE estadoTeclado;
	al_start_timer(timer);
	al_start_timer(timer2);

	while (hecho) {
		ALLEGRO_EVENT eventos;
		al_wait_for_event(cola_eventos, &eventos);
		if (eventos.type == ALLEGRO_EVENT_MOUSE_AXES)
		{
			mousex = eventos.mouse.x;
			mousey = eventos.mouse.y;

		}
		if (eventos.type == ALLEGRO_EVENT_TIMER)
		{
			if (eventos.timer.source == timer)
			{
				al_clear_to_color(al_map_rgb(0, 0, 0));
				al_draw_text(Fuente, al_map_rgb(250, 220, 110), ResX / 2, 100, ALLEGRO_ALIGN_CENTRE, "Metroid");
				//al_draw_filled_rectangle(370, 210, 430, 230, al_map_rgb(200, 50, 50));
				al_draw_text(Fuente, al_map_rgb(250, 250, 250), ResX / 2, 200, ALLEGRO_ALIGN_CENTRE, "Jugar");
				al_draw_text(Fuente, al_map_rgb(250, 250, 250), ResX / 2, 250, ALLEGRO_ALIGN_CENTRE, "Instrucciones");
				al_draw_text(Fuente, al_map_rgb(250, 250, 250), ResX / 2, 300, ALLEGRO_ALIGN_CENTRE, "Registro");
				al_draw_text(Fuente, al_map_rgb(250, 250, 250), ResX / 2, 350, ALLEGRO_ALIGN_CENTRE, "Salir");
			}
		}
		if ((mousex >= ResX / 2 - 30 && mousex <= ResX / 2 + 30) && (mousey >= 210) && (mousey <= 230)) {
			al_draw_text(Fuente, al_map_rgb(250, 220, 110), ResX / 2, 200, ALLEGRO_ALIGN_CENTRE, "Jugar");
			if (eventos.type == ALLEGRO_EVENT_MOUSE_BUTTON_UP)
			{
				if (eventos.mouse.button & 1) {
					juego();
				}
			}
		}
		if ((mousex >= ResX / 2 - 30 && mousex <= ResX / 2 + 30) && (mousey >= 260) && (mousey <= 280)) {
			al_draw_text(Fuente, al_map_rgb(250, 220, 110), ResX / 2, 250, ALLEGRO_ALIGN_CENTRE, "Instrucciones");
			if (eventos.type == ALLEGRO_EVENT_MOUSE_BUTTON_UP)
			{
				if (eventos.mouse.button & 1) {
					while (instr) {
						ALLEGRO_EVENT eventos2;
						al_wait_for_event(cola_eventos, &eventos2);
						if (eventos2.type == ALLEGRO_EVENT_MOUSE_AXES)
						{
							mousex = eventos2.mouse.x;
							mousey = eventos2.mouse.y;

						}
						if (eventos2.type == ALLEGRO_EVENT_TIMER)
						{
							//al_wait_for_event(cola_eventos, &eventos);
							if (eventos2.timer.source == timer)
							{
								al_clear_to_color(al_map_rgb(0, 0, 0));
								al_draw_text(Fuente, al_map_rgb(250, 220, 110), ResX / 2, 100, ALLEGRO_ALIGN_CENTRE, "INSTRUCCIONES");
								//al_draw_filled_rectangle(370, 210, 430, 230, al_map_rgb(200, 50, 50));
								al_draw_text(Fuente, al_map_rgb(250, 250, 250), ResX / 2, 150, ALLEGRO_ALIGN_CENTRE, "Movimiento: AD/Flechas izquierda y derecha");
								al_draw_text(Fuente, al_map_rgb(250, 250, 250), ResX / 2, 200, ALLEGRO_ALIGN_CENTRE, "Disparo: ESPACIO");
								al_draw_text(Fuente, al_map_rgb(250, 250, 250), ResX / 2, 250, ALLEGRO_ALIGN_CENTRE, "Salto: W/Flecha hacia arriba");
								al_draw_text(Fuente, al_map_rgb(250, 250, 250), ResX / 2, 300, ALLEGRO_ALIGN_CENTRE, "Modo bolita: S/Flecha hacia abajo");
								al_draw_text(Fuente, al_map_rgb(250, 250, 250), ResX / 2, 350, ALLEGRO_ALIGN_CENTRE, "Salir modo bolita: Q");
								al_draw_text(Fuente, al_map_rgb(250, 250, 250), ResX / 2, 400, ALLEGRO_ALIGN_CENTRE, "Misil: Mantener LCTRL y disparar");
								al_draw_text(Fuente, al_map_rgb(250, 250, 250), ResX / 2, 450, ALLEGRO_ALIGN_CENTRE, "Rayo gelido: Mantener LSHIFT y disparar");
								al_draw_text(Fuente, al_map_rgb(250, 250, 250), ResX / 4-100, 400, ALLEGRO_ALIGN_CENTRE, "Salir");
							}
						}
						if ((mousex >= ResX / 4 - 30-100 && mousex <= ResX / 4 + 30-100) && (mousey >= 400) && (mousey <= 420)) {
							al_draw_text(Fuente, al_map_rgb(250, 220, 110), ResX / 4-100, 400, ALLEGRO_ALIGN_CENTRE, "Salir");
							{
								if (eventos2.type == ALLEGRO_EVENT_MOUSE_BUTTON_UP)
								{
									if (eventos2.mouse.button & 1) {
										instr = false;
										break;
									}
								}
							}
						}
						al_flip_display();
					}
					instr = true;
				}
			}
		}
		if ((mousex >= ResX / 2 - 55 && mousex <= ResX / 2 + 50) && (mousey >= 310) && (mousey <= 330)) {
			al_draw_text(Fuente, al_map_rgb(250, 220, 110), ResX / 2, 300, ALLEGRO_ALIGN_CENTRE, "Registro");
			if (eventos.type == ALLEGRO_EVENT_MOUSE_BUTTON_DOWN)
			{

				if (eventos.mouse.button & 1) {
					bool salir = false;
					cargarDatos(dato);
					PtrDatos Aux = dato;
					while (!salir) {
						al_clear_to_color(al_map_rgb(0, 0, 0));
						al_wait_for_event(cola_eventos, &eventos);
						al_draw_text(Fuente, al_map_rgb(0, 100, 0), 600, (510 * (250.0 / 768.0) - 50), ALLEGRO_ALIGN_CENTRE, "Partidas");
						al_draw_text(Fuente, al_map_rgb(0, 100, 0), 600, (510 * (250.0 / 768.0)) + 250, ALLEGRO_ALIGN_CENTRE, "Presione Enter para finalizar");
						al_get_keyboard_state(&estadoTeclado);
						if ((int)Aux->segundos!=0) {
							al_get_keyboard_state(&estadoTeclado);
							al_wait_for_event(cola_eventos, &eventos);
							
							string nombre =  Aux->nombre;
							nombre = "Nombre: " + nombre;
							string segundos = "Segundos sobrevividos: "+to_string(Aux->segundos);
							const char* b = segundos.c_str();
							string balasAcertadas = "Balas acertadas: "+to_string(Aux->balasAcertadas);
							const char* c = balasAcertadas.c_str();
							string balasPerdidas = "Balas perdidas: "+to_string(Aux->balasPerdidas);
							const char* d = balasPerdidas.c_str();
							string indi = "Utilize las teclas W y S para recorrer la lista";
							const char* a = nombre.c_str();
							const char* e = indi.c_str();

							al_draw_text(Fuente, al_map_rgb(250, 250, 250), 600, (510 * (250.0 / 768.0)) + 50, ALLEGRO_ALIGN_CENTRE, b);
							al_draw_text(Fuente, al_map_rgb(250, 250, 250), 600, (510 * (250.0 / 768.0)) + 100, ALLEGRO_ALIGN_CENTRE, c);
							al_draw_text(Fuente, al_map_rgb(250, 250, 250), 600, (510 * (250.0 / 768.0)) + 150, ALLEGRO_ALIGN_CENTRE, d);
							al_draw_text(Fuente, al_map_rgb(250, 250, 250), 600, (510 * (250.0 / 768.0)), ALLEGRO_ALIGN_CENTRE, a);
							al_draw_text(Fuente, al_map_rgb(0, 100, 0), 600, (510 * (250.0 / 768.0))+200, ALLEGRO_ALIGN_CENTRE, e);
							
								
									if (al_key_down(&estadoTeclado, ALLEGRO_KEY_S) && Aux->Siguiente != NULL && !presionado) {
										Aux = Aux->Siguiente;
										presionado = true;
									}
									if (al_key_down(&estadoTeclado, ALLEGRO_KEY_W) && Aux->Anterior != NULL && !presionado) {
										Aux = Aux->Anterior;
										presionado = true;
									}
									if (!al_key_down(&estadoTeclado, ALLEGRO_KEY_S) && !al_key_down(&estadoTeclado, ALLEGRO_KEY_W)) {
										presionado = false;
									
									}
									
							
							
						}
						if (al_key_down(&estadoTeclado, ALLEGRO_KEY_ENTER)) {
							salir = true;
						}
						

						
					al_flip_display();
					}
				}
			}
		}
		if ((mousex >= ResX / 2 - 30 && mousex <= ResX / 2 + 30) && (mousey >= 350) && (mousey <= 380)) {
			al_draw_text(Fuente, al_map_rgb(250, 220, 110), ResX / 2, 350, ALLEGRO_ALIGN_CENTRE, "Salir");
			{
				if (eventos.type == ALLEGRO_EVENT_MOUSE_BUTTON_UP)
				{
					if (eventos.mouse.button & 1) {
						hecho = false;
						break;
					}
				}
			}
		}
		al_flip_display();
	}
	al_destroy_event_queue(cola_eventos);
	al_destroy_font(Fuente);
	al_destroy_font(Fuente2);
	al_destroy_timer(timer);
	al_destroy_display(pantalla);
}
