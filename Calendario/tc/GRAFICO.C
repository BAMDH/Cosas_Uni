#include <graphics.h>
#include <conio.h>
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

extern valor3;
extern calcularDias();
extern calcularDiasJulianos();
extern comprobarNumeroDias();
extern cantidadDias();
extern conversorAJuliano();


int y, y2, x, n, d, dia, mes, a, b,year,c,semana;
char string[20];
int option = 0;
float resultado;
float resultado2;

void pintarLineasVerticales() {
	setcolor(5);
	//lineas verticales
	setlinestyle(0, 3, 2);
	y = 170;
	y2 = 65;
	x = 0;
	a=3;
	line(0, 43, 0, y);//inicial
	line(163, 43 + x, 163 , y);//inicial2
	line(323, 43 + x, 323, y);//inicial3
	line(483, 43 + x, 483, y);//inicial3
	line(140, 43 + x, 140, y);//final
	line(300, 43 + x, 300, y);//final2
	line(460, 43 + x, 460, y);//final3
	line(620, 43 + x, 620, y);//final3
	setlinestyle(2, 8, 2);
	line(15 + a, y2, 15 + a, y);//domingo-lunes
	line(35 + a, y2, 35 + a, y);//lunes-martes
	line(55 + a, y2, 55 + a, y);//martes-miercoles
	line(75 + a, y2, 75 + a, y);//miercoles-jueves
	line(95 + a, y2, 95 + a, y);//jueves-viernes
	line(115 + a, y2, 115 + a, y);//viernes-sabado
	y += 140;
	y2 += 140;
	x = 140;
	setlinestyle(0, 3, 2);
	line(0, 43, 0, y);//inicial
	line(163, 43 + x, 163, y);//inicial2
	line(323, 43 + x, 323, y);//inicial3
	line(483, 43 + x, 483, y);//inicial3
	line(140, 43 + x, 140, y);//final
	line(300, 43 + x, 300, y);//final2
	line(460, 43 + x, 460, y);//final3
	line(620, 43 + x, 620, y);//final3
	setlinestyle(2, 8, 2);
	line(15 + a, y2, 15 + a, y);//domingo-lunes
	line(35 + a, y2, 35 + a, y);//lunes-martes
	line(55 + a, y2, 55 + a, y);//martes-miercoles
	line(75 + a, y2, 75 + a, y);//miercoles-jueves
	line(95 + a, y2, 95 + a, y);//jueves-viernes
	line(115 + a, y2, 115 + a, y);//viernes-sabado
	y += 140;
	y2 += 140;
	x = 280;
	setlinestyle(0, 3, 2);
	line(0, 43, 0, y);//inicial
	line(163, 43 + x, 163, y);//inicial2
	line(323, 43 + x, 323, y);//inicial3
	line(483, 43 + x, 483, y);//inicial3
	line(140, 43 + x, 140, y);//final
	line(300, 43 + x, 300, y);//final2
	line(460, 43 + x, 460, y);//final3
	line(620, 43 + x, 620, y);//final3
	setlinestyle(2, 8, 2);
	line(15 + a, y2, 15 + a, y);//domingo-lunes
	line(35 + a, y2, 35 + a, y);//lunes-martes
	line(55 + a, y2, 55 + a, y);//martes-miercoles
	line(75 + a, y2, 75 + a, y);//miercoles-jueves
	line(95 + a, y2, 95 + a, y);//jueves-viernes
	line(115 + a, y2, 115 + a, y);//viernes-sabado

	y = 170;
	y2 = 65;
	a = 166;
	setlinestyle(2, 8, 2);
	line(15 + a, y2, 15 + a, y);//domingo-lunes
	line(35 + a, y2, 35 + a, y);//lunes-martes
	line(55 + a, y2, 55 + a, y);//martes-miercoles
	line(75 + a, y2, 75 + a, y);//miercoles-jueves
	line(95 + a, y2, 95 + a, y);//jueves-viernes
	line(115 + a, y2, 115 + a, y);//viernes-sabado
	y += 140;
	y2 += 140;
	line(15 + a, y2, 15 + a, y);//domingo-lunes
	line(35 + a, y2, 35 + a, y);//lunes-martes
	line(55 + a, y2, 55 + a, y);//martes-miercoles
	line(75 + a, y2, 75 + a, y);//miercoles-jueves
	line(95 + a, y2, 95 + a, y);//jueves-viernes
	line(115 + a, y2, 115 + a, y);//viernes-sabado
	y += 140;
	y2 += 140;
	line(15 + a, y2, 15 + a, y);//domingo-lunes
	line(35 + a, y2, 35 + a, y);//lunes-martes
	line(55 + a, y2, 55 + a, y);//martes-miercoles
	line(75 + a, y2, 75 + a, y);//miercoles-jueves
	line(95 + a, y2, 95 + a, y);//jueves-viernes
	line(115 + a, y2, 115 + a, y);//viernes-sabado
	y = 170;
	y2 = 65;
	a = 486;
	setlinestyle(2, 8, 2);
	line(15 + a, y2, 15 + a, y);//domingo-lunes
	line(35 + a, y2, 35 + a, y);//lunes-martes
	line(55 + a, y2, 55 + a, y);//martes-miercoles
	line(75 + a, y2, 75 + a, y);//miercoles-jueves
	line(95 + a, y2, 95 + a, y);//jueves-viernes
	line(115 + a, y2, 115 + a, y);//viernes-sabado
	y += 140;
	y2 += 140;
	line(15 + a, y2, 15 + a, y);//domingo-lunes
	line(35 + a, y2, 35 + a, y);//lunes-martes
	line(55 + a, y2, 55 + a, y);//martes-miercoles
	line(75 + a, y2, 75 + a, y);//miercoles-jueves
	line(95 + a, y2, 95 + a, y);//jueves-viernes
	line(115 + a, y2, 115 + a, y);//viernes-sabado
	y += 140;
	y2 += 140;
	line(15 + a, y2, 15 + a, y);//domingo-lunes
	line(35 + a, y2, 35 + a, y);//lunes-martes
	line(55 + a, y2, 55 + a, y);//martes-miercoles
	line(75 + a, y2, 75 + a, y);//miercoles-jueves
	line(95 + a, y2, 95 + a, y);//jueves-viernes
	line(115 + a, y2, 115 + a, y);//viernes-sabado
	y = 170;
	y2 = 65;
	a = 326;
	setlinestyle(2, 8, 2);
	line(15 + a, y2, 15 + a, y);//domingo-lunes
	line(35 + a, y2, 35 + a, y);//lunes-martes
	line(55 + a, y2, 55 + a, y);//martes-miercoles
	line(75 + a, y2, 75 + a, y);//miercoles-jueves
	line(95 + a, y2, 95 + a, y);//jueves-viernes
	line(115 + a, y2, 115 + a, y);//viernes-sabado
	y += 140;
	y2 += 140;
	line(15 + a, y2, 15 + a, y);//domingo-lunes
	line(35 + a, y2, 35 + a, y);//lunes-martes
	line(55 + a, y2, 55 + a, y);//martes-miercoles
	line(75 + a, y2, 75 + a, y);//miercoles-jueves
	line(95 + a, y2, 95 + a, y);//jueves-viernes
	line(115 + a, y2, 115 + a, y);//viernes-sabado
	y += 140;
	y2 += 140;
	line(15 + a, y2, 15 + a, y);//domingo-lunes
	line(35 + a, y2, 35 + a, y);//lunes-martes
	line(55 + a, y2, 55 + a, y);//martes-miercoles
	line(75 + a, y2, 75 + a, y);//miercoles-jueves
	line(95 + a, y2, 95 + a, y);//jueves-viernes
	line(115 + a, y2, 115 + a, y);//viernes-sabado
	y = 170;
	y2 = 65;
	a = 166;
	setlinestyle(2, 8, 2);
	line(15 + a, y2, 15 + a, y);//domingo-lunes
	line(35 + a, y2, 35 + a, y);//lunes-martes
	line(55 + a, y2, 55 + a, y);//martes-miercoles
	line(75 + a, y2, 75 + a, y);//miercoles-jueves
	line(95 + a, y2, 95 + a, y);//jueves-viernes
	line(115 + a, y2, 115 + a, y);//viernes-sabado
	y += 140;
	y2 += 140;
	line(15 + a, y2, 15 + a, y);//domingo-lunes
	line(35 + a, y2, 35 + a, y);//lunes-martes
	line(55 + a, y2, 55 + a, y);//martes-miercoles
	line(75 + a, y2, 75 + a, y);//miercoles-jueves
	line(95 + a, y2, 95 + a, y);//jueves-viernes
	line(115 + a, y2, 115 + a, y);//viernes-sabado
	y += 140;
	y2 += 140;
	line(15 + a, y2, 15 + a, y);//domingo-lunes
	line(35 + a, y2, 35 + a, y);//lunes-martes
	line(55 + a, y2, 55 + a, y);//martes-miercoles
	line(75 + a, y2, 75 + a, y);//miercoles-jueves
	line(95 + a, y2, 95 + a, y);//jueves-viernes
	line(115 + a, y2, 115 + a, y);//viernes-sabado
}
void pintarLineasVerticales2() {
	setcolor(5);
	//lineas verticales
	setlinestyle(0, 3, 2);
	y = 245;
	line(10, 10, 10, y);//inicial
	line(610, 10, 610, y);//final
	setlinestyle(2, 8, 2);
	line(110, 43, 110, y);//domingo-lunes
	line(180, 43, 180, y);//lunes-martes
	line(265, 43, 265, y);//martes-miercoles
	line(365, 43, 365, y);//miercoles-jueves
	line(445, 43, 445, y);//jueves-viernes
	line(530, 43, 530, y);//viernes-sabado
}
void pintarLineasHorizontales() {
	setcolor(5);
	setlinestyle(0, 3, 2);
	//lineas horizontales
	x = 0;
	a = 140;
	b = 0;
	y = 0;
	line(b + x, 43 + y, a + x, 43 + y);
	line(b + x, 65 + y, a + x, 65 + y);
	line(b + x, 82 + y, a + x, 82 + y);
	line(b + x, 170 + y, a + x, 170 + y);

	setcolor(10);
	setlinestyle(2, 8, 2);
	line(b + x, 95 + y, a + x, 95 + y);
	line(b + x, 110 + y, a + x, 110 + y);
	line(b + x, 127 + y, a + x, 127 + y);
	line(b + x, 142 + y, a + x, 142 + y);
	line(b + x, 157 + y, a + x, 157 + y);
	y = 140;
	setcolor(5);
	setlinestyle(0, 3, 2);
	
	line(b + x, 43 + y, a +x, 43 + y);
	line(b + x, 65 + y, a + x, 65 + y);
	line(b + x, 82 + y, a + x, 82 + y);
	line(b + x, 170 + y, a + x, 170 + y);

	setcolor(10);
	setlinestyle(2, 8, 2);
	line(b + x, 95 + y, a + x, 95 + y);
	line(b + x, 110 + y, a + x, 110 + y);
	line(b + x, 127 + y, a + x, 127 + y);
	line(b + x, 142 + y, a + x, 142 + y);
	line(b + x, 157 + y, a + x, 157 + y);
	y = 280;
	setcolor(5);
	setlinestyle(0, 3, 2);
	line(b + x, 43 + y, a + x, 43 + y);
	line(b + x, 65 + y, a + x, 65 + y);
	line(b + x, 82 + y, a + x, 82 + y);
	line(b + x, 170 + y, a + x, 170 + y);
	setcolor(10);
	setlinestyle(2, 8, 2);
	line(b + x, 95 + y, a + x, 95 + y);
	line(b + x, 110 + y, a + x, 110 + y);
	line(b + x, 127 + y, a + x, 127 + y);
	line(b + x, 142 + y, a + x, 142 + y);
	line(b + x, 157 + y, a + x, 157 + y);
	

	x = 160;
	b = 3;
	y = 0;
	setcolor(5);
	setlinestyle(0, 3, 2);

	line(b + x, 43 + y, a + x, 43 + y);
	line(b + x, 65 + y, a + x, 65 + y);
	line(b + x, 82 + y, a + x, 82 + y);
	line(b + x, 170 + y, a + x, 170 + y);

	setcolor(10);
	setlinestyle(2, 8, 2);
	line(b + x, 95 + y, a + x, 95 + y);
	line(b + x, 110 + y, a + x, 110 + y);
	line(b + x, 127 + y, a + x, 127 + y);
	line(b + x, 142 + y, a + x, 142 + y);
	line(b + x, 157 + y, a + x, 157 + y);
	y = 140;
	setcolor(5);
	setlinestyle(0, 3, 2);

	line(b + x, 43 + y, a + x, 43 + y);
	line(b + x, 65 + y, a + x, 65 + y);
	line(b + x, 82 + y, a + x, 82 + y);
	line(b + x, 170 + y, a + x, 170 + y);

	setcolor(10);
	setlinestyle(2, 8, 2);
	line(b + x, 95 + y, a + x, 95 + y);
	line(b + x, 110 + y, a + x, 110 + y);
	line(b + x, 127 + y, a + x, 127 + y);
	line(b + x, 142 + y, a + x, 142 + y);
	line(b + x, 157 + y, a + x, 157 + y);
	y = 280;
	setcolor(5);
	setlinestyle(0, 3, 2);
	line(b + x, 43 + y, a + x, 43 + y);
	line(b + x, 65 + y, a + x, 65 + y);
	line(b + x, 82 + y, a + x, 82 + y);
	line(b + x, 170 + y, a + x, 170 + y);
	setcolor(10);
	setlinestyle(2, 8, 2);
	line(b + x, 95 + y, a + x, 95 + y);
	line(b + x, 110 + y, a + x, 110 + y);
	line(b + x, 127 + y, a + x, 127 + y);
	line(b + x, 142 + y, a + x, 142 + y);
	line(b + x, 157 + y, a + x, 157 + y);

	x = 320;
	b = 3;
	y = 0;
	setcolor(5);
	setlinestyle(0, 3, 2);

	line(b + x, 43 + y, a + x, 43 + y);
	line(b + x, 65 + y, a + x, 65 + y);
	line(b + x, 82 + y, a + x, 82 + y);
	line(b + x, 170 + y, a + x, 170 + y);

	setcolor(10);
	setlinestyle(2, 8, 2);
	line(b + x, 95 + y, a + x, 95 + y);
	line(b + x, 110 + y, a + x, 110 + y);
	line(b + x, 127 + y, a + x, 127 + y);
	line(b + x, 142 + y, a + x, 142 + y);
	line(b + x, 157 + y, a + x, 157 + y);
	y = 140;
	setcolor(5);
	setlinestyle(0, 3, 2);

	line(b + x, 43 + y, a + x, 43 + y);
	line(b + x, 65 + y, a + x, 65 + y);
	line(b + x, 82 + y, a + x, 82 + y);
	line(b + x, 170 + y, a + x, 170 + y);

	setcolor(10);
	setlinestyle(2, 8, 2);
	line(b + x, 95 + y, a + x, 95 + y);
	line(b + x, 110 + y, a + x, 110 + y);
	line(b + x, 127 + y, a + x, 127 + y);
	line(b + x, 142 + y, a + x, 142 + y);
	line(b + x, 157 + y, a + x, 157 + y);
	y = 280;
	setcolor(5);
	setlinestyle(0, 3, 2);
	line(b + x, 43 + y, a + x, 43 + y);
	line(b + x, 65 + y, a + x, 65 + y);
	line(b + x, 82 + y, a + x, 82 + y);
	line(b + x, 170 + y, a + x, 170 + y);
	setcolor(10);
	setlinestyle(2, 8, 2);
	line(b + x, 95 + y, a + x, 95 + y);
	line(b + x, 110 + y, a + x, 110 + y);
	line(b + x, 127 + y, a + x, 127 + y);
	line(b + x, 142 + y, a + x, 142 + y);
	line(b + x, 157 + y, a + x, 157 + y);
	x = 480;
	b = 3;
	y = 0;
	setcolor(5);
	setlinestyle(0, 3, 2);

	line(b + x, 43 + y, a + x, 43 + y);
	line(b + x, 65 + y, a + x, 65 + y);
	line(b + x, 82 + y, a + x, 82 + y);
	line(b + x, 170 + y, a + x, 170 + y);

	setcolor(10);
	setlinestyle(2, 8, 2);
	line(b + x, 95 + y, a + x, 95 + y);
	line(b + x, 110 + y, a + x, 110 + y);
	line(b + x, 127 + y, a + x, 127 + y);
	line(b + x, 142 + y, a + x, 142 + y);
	line(b + x, 157 + y, a + x, 157 + y);
	y = 140;
	setcolor(5);
	setlinestyle(0, 3, 2);

	line(b + x, 43 + y, a + x, 43 + y);
	line(b + x, 65 + y, a + x, 65 + y);
	line(b + x, 82 + y, a + x, 82 + y);
	line(b + x, 170 + y, a + x, 170 + y);

	setcolor(10);
	setlinestyle(2, 8, 2);
	line(b + x, 95 + y, a + x, 95 + y);
	line(b + x, 110 + y, a + x, 110 + y);
	line(b + x, 127 + y, a + x, 127 + y);
	line(b + x, 142 + y, a + x, 142 + y);
	line(b + x, 157 + y, a + x, 157 + y);
	y = 280;
	setcolor(5);
	setlinestyle(0, 3, 2);
	line(b + x, 43 + y, a + x, 43 + y);
	line(b + x, 65 + y, a + x, 65 + y);
	line(b + x, 82 + y, a + x, 82 + y);
	line(b + x, 170 + y, a + x, 170 + y);
	setcolor(10);
	setlinestyle(2, 8, 2);
	line(b + x, 95 + y, a + x, 95 + y);
	line(b + x, 110 + y, a + x, 110 + y);
	line(b + x, 127 + y, a + x, 127 + y);
	line(b + x, 142 + y, a + x, 142 + y);
	line(b + x, 157 + y, a + x, 157 + y);
}
void pintarLineasHorizontales2() {
	setcolor(5);
	setlinestyle(0, 3, 2);
	//lineas horizontales
	line(10, 10, 610, 10);
	line(10, 43, 610, 43);
	line(10, 65, 610, 65);
	line(10, 245, 610, 245);
	setcolor(10);
	setlinestyle(2, 8, 2);
	line(10, 95, 610, 95);
	line(10, 125, 610, 125);
	line(10, 155, 610, 155);
	line(10, 185, 610, 185);
	line(10, 215, 610, 215);
}

void imprimirDias() {
	settextstyle(0, 0, 1);
	setcolor(9);//me gusta
	y = 85;
	while (dia <= n) {

		itoa(dia, string, 10);
		if (d == 1) {
			outtextxy(0+x, y+y2, string);//domingo
		}
		if (d == 2) {
			outtextxy(20+x, y + y2, string);//lunes
		}
		if (d == 3) {
			outtextxy(40+x, y + y2, string);//martes
		}
		if (d == 4) {

			outtextxy(60+x, y + y2, string);//miercoles
		}
		if (d == 5) {
			outtextxy(80+x, y + y2, string);//jueves
		}if (d == 6) {
			outtextxy(100+x, y + y2, string);//viernes
		}
		if (d == 0) {
			outtextxy(120+x, y + y2, string);//sabado
			
			itoa(semana, string, 10);
			outtextxy(140 + x, y + y2, string);//semana
			semana ++;
			y += 15;
		}
		dia++;
		d++;
		if (d > 6) {
			d = 0;
		}
		if (d != 1) {
			itoa(semana, string, 10);
			outtextxy(140 + x, y + y2, string);
		}
	}
}
void imprimirDias2() {
	
	while (dia <= n) {
		itoa(dia, string, 10);
		if (d == 1) {
			outtextxy(45, y, string);//domingo
		}
		if (d == 2) {
			outtextxy(135, y, string);//lunes
		}
		if (d == 3) {
			outtextxy(215, y, string);//martes
		}
		if (d == 4) {
			outtextxy(300, y, string);//miercoles
		}
		if (d == 5) {

			outtextxy(395, y, string);//jueves

		}
		if (d == 6) {
			outtextxy(475, y, string);//viernes
		}
		if (d == 0) {
			outtextxy(565, y, string);//sabado
		
			itoa(semana, string, 10);
			outtextxy(615, y, string);//semana
			semana++;
			y += 30;
		}
		
		dia++;
		d++;
		if (d > 6) {
			d = 0;	
		}

	}
	itoa(semana, string, 10);
	outtextxy(615, y, string);//semana
}
void imprimirMes() {
	if (mes == 1) {
		outtextxy(20, 0, "Enero");
	}
	if (mes == 2) {
		outtextxy(20, 0, "Febrero");
	}
	if (mes == 3) {
		outtextxy(20, 0, "Marzo");
	}
	if (mes == 4) {
		outtextxy(20, 0, "Abril");
	}
	if (mes == 5) {
		outtextxy(20, 0, "Mayo");
	}
	if (mes == 6) {
		outtextxy(20, 0, "Junio");
	}
	if (mes == 7) {
		outtextxy(20, 0, "Julio");
	}
	if (mes == 8) {
		outtextxy(20, 0, "Agosto");
	}
	if (mes == 9) {
		outtextxy(20, 0, "Septiembre");
	}
	if (mes == 10) {
		outtextxy(20, 0, "Octubre");
	}
	if (mes == 11) {
		outtextxy(20, 0, "Noviembre");
	}
	if (mes == 12) {
		outtextxy(20, 0, "Diciembre");
	}
}
void imprimirDatos() {
	itoa(year, string, 10);




	setcolor(9);
	settextstyle(10, 0, 1);
	outtextxy(270, 0, string);


	settextstyle(3, 0, 1);
	setcolor(2);
	outtextxy(50, 40, "Enero");
	outtextxy(210, 40, "Febrero");
	outtextxy(370, 40, "Marzo");
	outtextxy(530, 40, "Abril");
	outtextxy(50, 180, "Mayo");
	outtextxy(210, 180, "Junio");
	outtextxy(370, 180, "Julio");
	outtextxy(530, 180, "Agosto");
	outtextxy(20, 320, "Septiembre");
	outtextxy(200, 320, "Octubre");
	outtextxy(350, 320, "Noviembre");
	outtextxy(510, 320, "Diciembre");

	y = 60;
	outtextxy(3, y, "D L K M J V S #");
	outtextxy(166, y, "D L K M J V S #");
	outtextxy(326, y, "D L K M J V S #");
	outtextxy(486, y, "D L K M J V S #");
	y = 200;
	outtextxy(3, y, "D L K M J V S #");
	outtextxy(166, y, "D L K M J V S #");
	outtextxy(326, y, "D L K M J V S #");
	outtextxy(486, y, "D L K M J V S #");
	y = 340;
	outtextxy(3, y, "D L K M J V S #");
	outtextxy(166, y, "D L K M J V S #");
	outtextxy(326, y, "D L K M J V S #");
	outtextxy(486, y, "D L K M J V S #");
}
void menu() {
	fflush(stdin);
	printf("Digite una de las siguientes opciones\n0.Salir\n1.Calendario de un año\n2.Calendario de un mes especifico\n3.Calcular días restantes\n4.Convertir fecha a Juliano\n");
	scanf("%d", &option);
	if (option > 0) {
		printf("Inserte el año deseado: ");
		fflush(stdin);
		scanf("%d", &year);
		if (option > 1) {
			printf("Inserte el mes deseado: ");
			fflush(stdin);
			scanf("%d", &mes);
			if (option > 2) {
				printf("Inserte el dia deseado: ");
				fflush(stdin);
				scanf("%d", &dia);
			}
		}
	}
	fflush(stdin);
}

int main() {

	int variable=0;
		
	int monitor = DETECT, modo;
	option = 6;

	while (option > 0) {
		year = 2023;
		dia = 1;
		mes = 1;
		
		system("cls");
		menu();

		if (option != 0)
		{
			initgraph(&monitor, &modo, "c:\\bgi");






			a = 0;
			b = 0;

			if (mes < 3) {
				a = 1;
				b = 12;
			}
			variable = calcularDias(year - a, mes + b, dia, 0, 0x12345678);
			if (year <= 1582) {
				if (mes > 10) {
					variable = calcularDias(year - a, mes + b, dia, 0, 0x12345678);
				}
				else {
					variable = calcularDiasJulianos(year - a, mes + b, dia, 0, 0x12345678);
				}
			}
			d = variable;
			n = comprobarNumeroDias(year, mes, dia, 0, 0x12345678);
			if (option == 1) {
				pintarLineasHorizontales();

				pintarLineasVerticales();
				imprimirDatos();
				semana = 1;
				mes = 1;
				c = 0;
				x = 3;
				y2 = 0;
				while (mes < 13) {

					n = comprobarNumeroDias(year, mes, dia, 0, 0x12345678);
					dia = 1;
					imprimirDias();
					if (n == 4) {
						dia = 15;
						d = calcularDias(year, mes, dia, 0, 0x12345678);
						n = 31;
						imprimirDias();

					}
					mes++;
					x += 161;
					c++;
					if (c == 3) {
						x -= 2;
					}
					if (c == 4) {
						x = 3;
						c = 0;
						y2 += 140;
					}

				}






				//sumar de 200 en 200

				settextstyle(6, 0, 1);
				setcolor(7);
				outtextxy(10, 450, "Presione enter para salir");
				scanf("i%", option);
			


			}

			if (option == 2) {
				semana = cantidadDias(year, mes, dia, 0, 0x12345678);
				if (year == 1582 && mes > 10) {
					semana -= 2;
				}
				setcolor(9);
				settextstyle(10, 0, 2);
				itoa(year, string, 10);
				outtextxy(260, 0, string);
				imprimirMes();

				settextstyle(7, 0, 2);
				setcolor(2);
				outtextxy(10, 40, "Domingo Lunes Martes Miercoles Jueves Viernes Sabado");




				pintarLineasHorizontales2();
				pintarLineasVerticales2();


				//dias
				settextstyle(1, 0, 2);
				setcolor(13);//me gusta
				semana = cantidadDias(year, mes, dia, 0, 0x12345678);
				if (year == 1582 && mes > 10) {
					semana -= 2;
				}
				y = 65;
				imprimirDias2();
				if (year == 1582 && mes == 10) {
					dia = 15;
					d = calcularDias(year, mes, dia, 0, 0x12345678);
					n = 31;
				}
				imprimirDias2();

				

				settextstyle(6, 0, 1);
				setcolor(7);
				outtextxy(10, 450, "Presione enter para salir");
				scanf("i%", option);
				
				option = 6;


			}
			if (option == 3) {
				semana = cantidadDias(year, mes, dia, 0, 0x12345678);
				if (year == 1582 && mes > 10) {
					semana -= 2;
				}
				printf("El día %i del mes %i del año %i cae en la semana %i", dia, mes, year, semana);
				n=cantidadDias(year, mes, dia, 2, 0x12345678);
				d = cantidadDias(year, mes, dia, 1, 0x12345678);

				
				
				
				printf("\n\nDicho día es el #%i del año", d);
				d = n - d;
				printf("\n\nFaltan %i dias para que termine del año", d);

				settextstyle(6, 0, 3);
				setcolor(7);
				outtextxy(0, 120, "Presione enter para salir");
				scanf("i%", option);
				option = 6;
			}
			if (option == 4) {
				resultado = conversorAJuliano(year - a, mes + b, dia,0, 0x12345678);
				resultado2 = conversorAJuliano(year - a, mes + b, dia, 1, 0x12345678);
				resultado2 = resultado2 * 10000;
				resultado = resultado + resultado2;
				resultado2 = conversorAJuliano(year - a, mes + b, dia, 2, 0x12345678);
				resultado2 = resultado2 / 10;
				resultado = resultado + resultado2;
				settextstyle(6, 0, 3);
				setcolor(7);

				printf("Datos Insertados");
				printf("\n\nAño: %i",year);
				
				printf("\nMes:%i ",mes);
				

				printf("\nDia: %i",dia);
				
				printf("\n\nEquivalente Juliano: %.1f", resultado);
				
				fflush(stdin);
				settextstyle(6, 0, 3);
				setcolor(7);
				outtextxy(0, 120, "Presione enter para salir");
				scanf("i%", option);
				option = 6;
			}
			cleardevice();

			
		}
			
			
			

		
	}
	closegraph();
 return 0;
}

