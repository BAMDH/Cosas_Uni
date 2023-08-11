// Lectura-Escritura-Archivos-C-xBytes.cpp: define el punto de entrada de la aplicación de consola.
//
#include <stdio.h>
#include <iostream>
#include <string.h>
bool BitEncendido(char Simbolo, int cual){
	int Mascara = 1;
	Mascara = Mascara << cual;
	if ((Simbolo & Mascara) == 0)
		return false; // Da 0 lo que significa que la posicion cual estaba en 0 
	else return true; // Da <>0 lo que significa que la posicion cual estaba en 1 
}
void EncenderBit(char& Simbolo, int cual)
{
	int Mascara = 1;
	Mascara = Mascara << cual;
	Simbolo = Simbolo | Mascara;
}
void ApagarBit(char& Simbolo, int cual)
{
	int Mascara = 1;
	Mascara = Mascara << cual;
	Mascara = ~Mascara;
	Simbolo = Simbolo & Mascara;
}
void InvertirBit(char &Simbolo, int cual)
{
	int Mascara = 1;
	Mascara = Mascara << cual;
	Simbolo = Simbolo ^ Mascara;
}
/*intercambia la primera y última posición*/
void InvertirXPosicion(char &Simbolo) {
	int Mascara = Simbolo;
	if (BitEncendido(Mascara, 7)) {
		EncenderBit(Simbolo, 0);
	}
	else {
		ApagarBit(Simbolo, 0);
	}
	if (BitEncendido(Mascara,0)) {
		EncenderBit(Simbolo,7);
	}
	else {
		ApagarBit(Simbolo, 7);
	}
}
/*Corre una posicion a la derecha todos los bits*/
void correrDerecha(char& Simbolo) {
	int Mascara = Simbolo;//es necesaria usarla como copia de seguridad porque un bit se pierde
	for (int i = 0; i < 7; i++) {
		if (BitEncendido(Mascara, i + 1)) {
			EncenderBit(Simbolo, i);
		}
		else {
			ApagarBit(Simbolo, i);
		}
	}
	if (BitEncendido(Mascara, 0)) {
		EncenderBit(Simbolo, 7);
	}
	else {
		ApagarBit(Simbolo, 7);
	}
}
/*Corre una posicion a la izquierda todos los bits*/
void correrIzquierda(char& Simbolo) {
	int Mascara = Simbolo;//es necesaria usarla como copia de seguridad porque un bit se pierde
	for (int i = 7; i > 0; i--) {
		if (BitEncendido(Mascara, i - 1)) {
			EncenderBit(Simbolo, i);
		}
		else {
			ApagarBit(Simbolo, i);
		}
	}
	if (BitEncendido(Mascara, 7)) {
		EncenderBit(Simbolo, 0);
	}
	else {
		ApagarBit(Simbolo, 0);
	}
}
void correrDerechaUltra(char& Simbolo) {
	for (int i = 0; i < 10; i++) {//corre en diez posiciones a la derecha todos los bits
		correrDerecha(Simbolo);
	}
	InvertirBit(Simbolo, 5);//invierte el bit en la posición indicada
	for (int i = 0; i < 7; i++) {//corre en siete posiciones a la izquierda todos los bits
		correrIzquierda(Simbolo);
	}
}
void correrIzquierdaUltra(char& Simbolo) {
	for (int i = 0; i < 7; i++) {//corre en siete posiciones a la derecha todos los bits
		correrDerecha(Simbolo);
	}
	InvertirBit(Simbolo, 5);//invierte el bit en la posición indicada
	for (int i = 0; i < 10; i++) {//corre en diez posiciones a la izquierda todos los bits
		correrIzquierda(Simbolo);
	}
}
// A continuaci'on se definen los metodos Veneno_X y Antidoto_X que serviran para Encriptar y Desencriptar el archivo procesado 
// Ojo X puede ser 1,2,3,4...  y siempre el antidoto debera corresponder al veneno.  En este ejemplo solo se usa el 1
void Veneno_1(char Bloque[1000], int limite){
	int i;
	int posicion_a_invertir = 0;

	for (i = 0; i <= limite; i++){
		InvertirBit(Bloque[i], posicion_a_invertir);
	}
}

void Antidoto_1(char Bloque[1000], int limite)
{
	int i;
	int posicion_a_invertir = 0;

	for (i = 0; i <= limite; i++){
		InvertirBit(Bloque[i], posicion_a_invertir);
	}
}
void Veneno_2(char Bloque[1000], int limite)
{
	int i;
	for (i = 0; i <= limite; i++) {
		InvertirXPosicion(Bloque[i]);
	}
}
void Antidoto_2(char Bloque[1000], int limite)
{
	int i;
	for (i = 0; i <= limite; i++) {
		InvertirXPosicion(Bloque[i]);
	}
}
void Veneno_3(char Bloque[1000], int limite)
{
	int i;
	for (i = 0; i <= limite; i++) {
		InvertirXPosicion(Bloque[i]);
		InvertirBit(Bloque[i], 0);
	}
}
void Antidoto_3(char Bloque[1000], int limite)
{
	int i;
	for (i = 0; i <= limite; i++) {
		InvertirBit(Bloque[i], 0);
		InvertirXPosicion(Bloque[i]);
	}
}
void Veneno_4(char Bloque[1000], int limite)
{
	int i;
	for (i = 0; i <= limite; i++) {
		correrDerecha(Bloque[i]);
	}
}
void Antidoto_4(char Bloque[1000], int limite)
{
	int i;
	for (i = 0; i <= limite; i++) {
		correrIzquierda(Bloque[i]);
	}
}
void Veneno_5(char Bloque[1000], int limite)
{
	int i;
	for (i = 0; i <= limite; i++) {
		correrDerechaUltra(Bloque[i]);
	}
}
void Antidoto_5(char Bloque[1000], int limite)
{
	int i;
	for (i = 0; i <= limite; i++) {
		correrIzquierdaUltra(Bloque[i]);
	}
}

using namespace std;
int main(int argc, char* argv[]) {

	//----------------------------------------------------------------------
	// Pintando a pantalla los argumentos que recibe el programa :  Se esperan en esta prueba 3: 
	// Ejecutable e/d FileOrigen FileDestino
	// e/d = e=encritar   d=desencriptar


	for (int i = 0; i < argc; ++i) {
		cout << "argumento " << i << ": " << argv[i] << "\n";
	}
	//----------------------------------------------------------------------

	// Archivos logicos : Buffers tipo FILE
	FILE* ArchivoOrigen, * ArchivoDestino;

	//----------------------------------------------------------------------
	// APERTURA DE ARCHIVO FUENTE Y DESTINO
	/* Apertura del archivo original, para lectura en binario*/
	fopen_s(&ArchivoOrigen, argv[2], "rb");
	if (ArchivoOrigen == NULL){
		perror("No se puede abrir archivo origen ");//, argv[2]
		return -1;
	}

	/* Apertura del archivo destino, para escritura en binario*/
	fopen_s(&ArchivoDestino, argv[3], "wb");
	if (ArchivoDestino == NULL){
		perror("No se puede abrir archivo destino"); // , argv[2]
		return -1;
	}
	//----------------------------------------------------------------------
	// PROCESAMIENTO DE ARCHIVOS FUENTE Y DESTINO
	/* Bloque de 1000 bytes, para meter lo que vamos leyendo del archivo */
	char buffer[10000];
	/* Para guardar el número de items leidos o si ha habido error */
	int leidos;
	char opcion = argv[1][0]; // (strcmp(opcion, 'e')
	//strcat_s(Pieza->Nombre, buffer);
	if (opcion == 'e') {
		leidos = fread(buffer, 1, 1000, ArchivoOrigen);

		/* Mientras se haya leido algo ... */
		while (leidos > 0)
		{

			Veneno_1(buffer, leidos); // Ojo esta usando el mismo metodo de desencriptar ya que solo INVIERTE el Bit 0

			/* ... meterlo en el fichero destino */
			fwrite(buffer, 1, leidos, ArchivoDestino);

			/* y leer siguiente bloque */
			leidos = fread(buffer, 1, 1000, ArchivoOrigen);
		}
	}
	else if (opcion == 'd') {
		leidos = fread(buffer, 1, 1000, ArchivoOrigen);

		/* Mientras se haya leido algo ... */
		while (leidos > 0)
		{

			Antidoto_1(buffer, leidos); // Ojo esta usando el mismo metodo de encriptar ya que solo INVIERTE el Bit 0

			/* ... meterlo en el fichero destino */
			fwrite(buffer, 1, leidos, ArchivoDestino);

			/* y leer siguiente bloque */
			leidos = fread(buffer, 1, 1000, ArchivoOrigen);
		}
	}
	else if (opcion == 'a') {
		leidos = fread(buffer, 1, 1000, ArchivoOrigen);

		/* Mientras se haya leido algo ... */
		while (leidos > 0)
		{

			Veneno_2(buffer, leidos); // Ojo esta usando el mismo metodo de desencriptar ya que solo INVIERTE el Bit 0

			/* ... meterlo en el fichero destino */
			fwrite(buffer, 1, leidos, ArchivoDestino);

			/* y leer siguiente bloque */
			leidos = fread(buffer, 1, 1000, ArchivoOrigen);
		}
	}
	else if (opcion == 'b') {
		leidos = fread(buffer, 1, 1000, ArchivoOrigen);

		/* Mientras se haya leido algo ... */
		while (leidos > 0)
		{

			Antidoto_2(buffer, leidos); // Ojo esta usando el mismo metodo de encriptar ya que solo INVIERTE el Bit 0

			/* ... meterlo en el fichero destino */
			fwrite(buffer, 1, leidos, ArchivoDestino);

			/* y leer siguiente bloque */
			leidos = fread(buffer, 1, 1000, ArchivoOrigen);
		}
	}
	else if (opcion == 'c') {
		leidos = fread(buffer, 1, 1000, ArchivoOrigen);

		/* Mientras se haya leido algo ... */
		while (leidos > 0)
		{

			Veneno_3(buffer, leidos); // Ojo esta usando el mismo metodo de desencriptar ya que solo INVIERTE el Bit 0

			/* ... meterlo en el fichero destino */
			fwrite(buffer, 1, leidos, ArchivoDestino);

			/* y leer siguiente bloque */
			leidos = fread(buffer, 1, 1000, ArchivoOrigen);
		}
	}
	else if (opcion == 'f') {
		leidos = fread(buffer, 1, 1000, ArchivoOrigen);

		/* Mientras se haya leido algo ... */
		while (leidos > 0)
		{

			Antidoto_3(buffer, leidos); // Ojo esta usando el mismo metodo de encriptar ya que solo INVIERTE el Bit 0

			/* ... meterlo en el fichero destino */
			fwrite(buffer, 1, leidos, ArchivoDestino);

			/* y leer siguiente bloque */
			leidos = fread(buffer, 1, 1000, ArchivoOrigen);
		}
	}
	else if (opcion == 'g') {
		leidos = fread(buffer, 1, 1000, ArchivoOrigen);

		/* Mientras se haya leido algo ... */
		while (leidos > 0)
		{

			Veneno_4(buffer, leidos); // Ojo esta usando el mismo metodo de desencriptar ya que solo INVIERTE el Bit 0

			/* ... meterlo en el fichero destino */
			fwrite(buffer, 1, leidos, ArchivoDestino);

			/* y leer siguiente bloque */
			leidos = fread(buffer, 1, 1000, ArchivoOrigen);
		}
	}
	else if (opcion == 'h') {
		leidos = fread(buffer, 1, 1000, ArchivoOrigen);

		/* Mientras se haya leido algo ... */
		while (leidos > 0)
		{

			Antidoto_4(buffer, leidos); // Ojo esta usando el mismo metodo de encriptar ya que solo INVIERTE el Bit 0

			/* ... meterlo en el fichero destino */
			fwrite(buffer, 1, leidos, ArchivoDestino);

			/* y leer siguiente bloque */
			leidos = fread(buffer, 1, 1000, ArchivoOrigen);
		}
	}
	else if (opcion == 'i') {
		leidos = fread(buffer, 1, 1000, ArchivoOrigen);

		/* Mientras se haya leido algo ... */
		while (leidos > 0)
		{

			Veneno_5(buffer, leidos); // Ojo esta usando el mismo metodo de desencriptar ya que solo INVIERTE el Bit 0

			/* ... meterlo en el fichero destino */
			fwrite(buffer, 1, leidos, ArchivoDestino);

			/* y leer siguiente bloque */
			leidos = fread(buffer, 1, 1000, ArchivoOrigen);
		}
	}
	else if (opcion == 'j') {
		leidos = fread(buffer, 1, 1000, ArchivoOrigen);

		/* Mientras se haya leido algo ... */
		while (leidos > 0)
		{

			Antidoto_5(buffer, leidos); // Ojo esta usando el mismo metodo de encriptar ya que solo INVIERTE el Bit 0

			/* ... meterlo en el fichero destino */
			fwrite(buffer, 1, leidos, ArchivoDestino);

			/* y leer siguiente bloque */
			leidos = fread(buffer, 1, 1000, ArchivoOrigen);
		}
	}
	fclose(ArchivoOrigen);
	fclose(ArchivoDestino);
	// Terminamos .. vaya a ver como quedo el archivo Encriptado o Desencriptado.. no olvide manejar estos nombre 
	// en Proyecto->Propiedades->Depuracion->Argumentos 
	// Ejemplo de parametros ... ojo el parametro "0" es el nombre del ejecutable, el parametro 1 es "e" o "d" 
	// el parametro 2 es el archivoOrigen y el parmetro 3 es el archivo destino  
	// Lectura-Escritura-Archivos-C-xBytes.exe d MiFotoEncriptada.enc MiFotoOriginal-restaurada.jpg
}