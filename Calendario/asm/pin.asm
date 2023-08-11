SSeg Segment para Stack 'Stack'
     db 64 Dup ('SegStack ')
SSeg EndS

datos Segment
	msg db 'La semana correspondiente al día $'
	msg2 db 10,'Estamos en el dia: $'
	msg3 db 10,'Los dias para terminar el anio son: $'
	ubi dw 2		;guarda la ubicacion del caracter que digito el usuario
	cant dw 0		;variable que almacena el resultado de la semana del dia
	n dw 365	;dias del anio
	b dw 0			;indicador si un anio es bisiesto o no
	r dw 0			;contador
	diasM db 'DoLuMaMiJuViSa#S$';imprime el día en miniatura para el calendario de todo el año
	limite dw 550;limites especiales al pintar puntos
	limite2 dw 283;limite auxiliar al pintar puntos
	plineas dw 22;posición lineas
	nlineasVerticales db 0;numero de lineas verticales a imprimir
	columna db 0;posicion en columna
	dias db 'Domingo  Lunes  Martes  Miercoles  Jueves  Viernes  Sabado  Semana$'
	ndias dw 31;numero de dias por mes
	ndias2 dw ?;numero de dias por mes
	q dw ?;dia
	q2 dw ?
	m dw ?;mes
	m2 dw ?;respaldo del mes
	m3 dw ?
	y dw ?;año
	y2 dw ?;respaldo del año
	y3 dw ?
	d dw ?;resultado de la congruencia de zeller, sirve para saber qué día 
	d2 dw ?;respaldo de la variable d 
	semana dw 0
	modo db 0;define el modo del calendario
	cantidadMeses db 0;cantidad de meses a imprimir, 12 por defecto
	valor1 db 0;valor a sumar cuando se modifica la posicion en fila
	valor2 db 0;valor a sumar cuando se modifica la posicion en columna
	valor3 dw 0;valor a sumar cuando se modifican los respectivos limites en la posicion x
	valor4 dw 0;valor a sumar cuando se modifican los respectivos limites en la posicion y
	valor5 db 0;valor a sumar cuando se modifican los respectivos limites de la filas
	posx dw 0;posicion en x
	posy dw	 0;posicion en y
	yPos db 37;posicion especial para columnas
	fila db 06;posicion para filas
	true dw 0;usada para determinar si se realiza una acción
	instrucciones db 'Para ver un año completo, agregue al llamado del programa lo indicado entre parentesis "/a x", siendo x el año deseado.$'
	instrucciones2 db 10, 'En caso de querer consultar un mes en específico, debe agregar además de lo anterior mencionado "/m x" siendo x el mes deseado.$'
	instrucciones3 db  10, 'Si desea consultar una fecha en específíco, agregue "/d x" siendo x el el día específico del mes.$'
	enero db 'Enero$';variable para imprimir el mes indicado
	febrero db 'Febrero$';variable para imprimir el mes indicado
	marzo db 'Marzo$';variable para imprimir el mes indicado
	abril db 'Abril$';variable para imprimir el mes indicado
	mayo db 'Mayo$';variable para imprimir el mes indicado
	junio db 'Junio$';variable para imprimir el mes indicado
	julio db 'Julio$';variable para imprimir el mes indicado
	agosto db 'Agosto$';variable para imprimir el mes indicado
	septiembre db 'Septiembre$';variable para imprimir el mes indicado
	octubre db 'Octubre$';variable para imprimir el mes indicado
	noviembre db 'Noviembre$';variable para imprimir el mes indicado
	diciembre db 'Diciembre$';variable para imprimir el mes indicado
	mensajeError db 'No existe un calendario para esta fecha$'
	direccion dw ?;guarda la direccion en segmento
	direccion2 dw ?;variable auxiliar guarda la direccion en segmento
	direccion3 dw ?;variable auxiliar guarda la direccion en segmento
	x db 1 ;sirve para manejar el numero de filas que se usa
	t dw 0 ;sirve para indicar los días de diferencias debido al desajuste del calendario juliano
	temp dw 1582 ;sirve de marcador para el año en el cual se debe de cambiar de calendario
	hecho db 0;indica si una acción ha sido realizada
	negativo db 0;indica si es negativo
datos EndS

	Include macroC.asm ;incluye macros

codigo Segment

inicio:
	assume cs:codigo, ds:datos, ss:SSeg
	inicializarDatos datos	
	modoGrafico
	xor cx, cx;limpia el registro cx
	xor bx, bx;limpia el registro bx
	xor dx, dx;limpia el registro dx
	
leercomandos:
	; Leer parámetros de línea de comandos
	mov si, 80h ; Cargar la dirección de memoria 80h en SI
	add si, ubi ; Saltar los primeros 3 caracteres ("/m ")
    ; Leer el valor del parámetro en formato ASCII y convertirlo a binario
    xor ax, ax ; Limpiar el registro AX
    mov al, byte ptr es:[si] ; Leer el primer carácter del valor del parámetro

verifcomando:
	cmp ax, 6Dh			;se verifica si se ingresa una "m" indicativa del mes
	je valoresingresados
	
	cmp ax, 64h			;se verifica si se ingresa una "d" indicativa del dia
	je valoresingresados
	
	cmp ax, 61h			;se verifica si se ingresa una "a" indicativa del anio
	je valoresingresados
	
	cmp ax, 20h ; Comparar el carácter leído con un espacio
	je espaciovacio
	
	cmp ax, 2Dh			;detecta una "-" indicativa del negativo 
	je esnegativo
	
	cmp ax, 2Fh  		;detecta una "/"
	je barrainclinada
	
	cmp ax, 0Dh
	je verifinal
	
	cmp ax, 0
	je fincomandos
	
	jmp armarvalor			;Se van armando los valores correspondientes a la fecha
valoresingresados:
	add dx, ax
	inc ubi
	jmp leercomandos
barrainclinada:
	inc ubi
	jmp leercomandos
esnegativo:
	mov negativo, 1;se indica que la cantidad es negativa
	inc ubi
	jmp leercomandos
verificarfinal:
	inc si
	xor ax, ax ; Limpiar el registro AX
    mov al, byte ptr es:[si] ; Leer el primer carácter del valor del parámetro
	cmp ax, 0
	je fincomandos
	inc ubi
	jmp leercomandos
espaciovacio:
	inc ubi
	cmp cx, 1
	je verifinal
	inc cx
	jmp leercomandos
verifinal:
	cmp dx, 6Dh
	je agregarmes
	
	cmp dx, 64h
	je agregardia
	
	cmp dx, 61h
	je agregaranio
	
	jmp leercomandos
fincomandos:
	cmp negativo, 1;confirma si es negativo
	je manipularA
	jmp confirmarModo
armarvalor:
	mov true,1
	sub ax, 30h
	push dx
	push cx
	xor dx, dx
	
	mov cx, ax
	mov ax, bx
	mov bx, 10
	mul bx
	
	add ax, cx
	mov bx, ax
	
	pop cx
	pop dx
	
	inc ubi
	jmp leercomandos
agregarmes:
	mov [m], bx
	xor dx, dx
	xor bx, bx
	xor cx, cx
	xor ax, ax
	inc ubi
	jmp leercomandos
agregardia: 	
	mov [q], bx
	xor dx, dx
	xor bx, bx
	xor cx, cx
	xor ax, ax
	inc ubi
	jmp leercomandos
agregaranio:
	mov y, bx
	xor dx, dx
	xor bx, bx
	xor cx, cx
	xor ax, ax
	inc ubi
	jmp leercomandos

ManipularA:
	mov ax,y;mueve al ax el año
	mov bx,-1;se mueve un -1 a b
	xor dx,dx
	mul bx;al multiplicar se, se consigue que el año se convierta en su versión negativa
	mov y,ax;se guarda el año negativo en la variable y
	mov negativo, 0;se apaga el indicador negativo
	jmp confirmarModo
	
mostrarInstrucciones:
	mov dx, offset instrucciones
	imprimirString dx
	mov dx, offset instrucciones2
	imprimirString dx
	mov dx, offset instrucciones3
	imprimirString dx
	terminar;finaliza el programa
	
confirmarModo:
	modoGrafico
	cmp true,0;confirma si se han insertado valores
	je mostrarInstrucciones;en caso de que no se hayan insertado, muestra las instrucciones
	cmp y,0;confirma que el año sea distinto de 0
	je noPermitido;en caso de ser 0 imprime un mensaje de error y finaliza el programa
	mov true,0;se apaga el indicador true
	mov negativo,0;se apaga el indicador negativo
	mov modo,2;indica que se desplegará la información sobre el día específico solicitado por el usuario
	cmp q,0;confirma que se haya insertado un día
	jne saltoContinuar2a;en caso de que se haya insertado un día, es decir, que sea distinto de 0, procederá a calcular la información solicitada
	cmp m,0;confirma que se haya insertado un mes
	mov modo,1;indica que se desplegará el calendario de un mes específico
	jne calendario
	mov modo,0;en caso de que no se hayan cumplido las anteriores condiciones, se desplegará el calendario del año completo
	jmp calendario
noPermitido:
	mov dx,offset mensajeError
	imprimirString dx
	terminar
saltoContinuar2a:
	cmp m,0;confirma que se haya insertado el mes correspondiente al día
	je noPermitido;en caso de que no haya sido insertado, se mostrará un mensaje de error
	guardarValores;crea un respaldo de los valores almacenados en las variables 'y' y 'm'
	jmp continuar1b
	
calendario:	
	guardarValores;crea un respaldo de los valores almacenados en las variables 'y' y 'm'
	mov q,1;se mueve a q un uno que indica el primer día del mes
	mov direccion2, offset setLimite3;se guarda la dirección de la etiqueta
	mov direccion3, offset unicoCalendario;se guarda la dirección de la etiqueta
	cmp modo,1;confirma el modo
	je saltoUnicoCalendario;si es 1, imprime el calendario de un mes en específico
	mov m,1;se inicializa el mes en 1, para calcular el calendario del año completo
	mov ax,m
	mov m2,ax;se hace un respaldo del nuevo mes
	mov direccion2, offset setLimite;se guarda la dirección de la etiqueta
	mov direccion3, offset inicio3;se guarda la dirección de la etiqueta
	mov fila,00;se indica la fila deseada
	mov columna,37;se indica la columna deseada
	mov direccion, offset inicio2;meter la direccion del segmento en el cual se desea salir
	push y;meter en pila el valor a imprimir
	jmp imprimirDecimal


	
saltoUnicoCalendario:
	jmp UnicoCalendario
	
	
imprimirDecimal:
	setPosicion fila, columna;coloca el puntero en la fila y columna indicada
	pop ax;saca el valor pasado por pila
	cmp ax,0;confirma si es negativo
	jl casoNegativo;en caso de serlo, manipula el número para que sea imprimible
	xor dx, dx
	mov bx,10;así se consigue el último digito de la cantidad
	div bx
	push ax;metemos el residuo en la pila
	imprimirCaracter dx;imprimir el ultimo digito
	dec columna;de esta manera se imprimirá de derecha a izquierda
	pop ax
	cmp ax,0;comparamos que el residuo no sea cero
	push ax
	jne imprimirDecimal;en caso de que sigan habiendo digitos por imprimir, se repite el proceso
	jmp confirmarSigno

casoNegativo:
	mov negativo,1;se indica que es un negativo
	mov bx,-1;lo vuelve positivo
	xor dx,dx
	mul bx
	push ax;lo pasa por pila
	jmp imprimirDecimal

confirmarSigno:
	cmp negativo,1;en caso de que la cantidad sea negativa, se procederá a imprimir el signo '-'
	je imprimirNegativo
	jmp [direccion];salta a la direccion de la label que posee esta variable
imprimirNegativo:
	setPosicion fila, columna;coloca el puntero en la fila y columna indicada
	mov negativo,0;se apaga el indicador de negativo
	mov ah,02h;imprime un caracter
	mov dx,2Dh;simbolo '-'
	int 21h
	jmp [direccion]

inicio2:
	guardarValores
	mov direccion, offset inicio3
	mov fila,01;indica la fila
	mov columna,9;indica la columna

confirmarMes:;confirma e imprime el mes
	mov dx, offset enero
	cmp m,1
	je imprimirMes
	mov dx, offset febrero
	cmp m,2
	je imprimirMes
	mov dx, offset marzo
	cmp m,3
	je imprimirMes
	mov dx, offset abril
	cmp m,4
	je imprimirMes
	mov dx, offset mayo
	cmp m,5
	je imprimirMes
	mov dx, offset junio
	cmp m,6
	je imprimirMes
	mov dx, offset julio
	cmp m,7
	je imprimirMes
	mov dx, offset agosto
	cmp m,8
	je imprimirMes
	mov dx, offset septiembre
	cmp m,9
	je imprimirMes
	mov dx, offset octubre
	cmp m,10
	je imprimirMes
	mov dx, offset noviembre
	cmp m,11
	je imprimirMes
	mov dx, offset diciembre
	cmp m,12
	je imprimirMes
imprimirMes:
	push dx
	call agregar;agrega los valores que modifican la posicion por cada corrida del programa.
	setPosicion fila,columna;coloca el cursor en posicion
	pop dx
	imprimirString dx
	jmp comprobarNumeroDias
	
comprobarNumeroDias:
	cmp m2, 2
	je salto0
	cmp m2, 7
	jng salto1
	jmp salto2
	
salto0:
	cmp y2,1582
	jl salto01
	
	xor dx, dx
	mov ax, [y2]
	mov bx, 4
	div bx
	cmp dx, 0
	je verbis
	mov dx, 28
	jmp fin
salto01:
	mov ax,y2
	mov bx,4
	xor dx,dx
	div bx
	cmp dx,0
	mov dx,29
	je fin
	mov dx,28
	jmp fin
verbis:
	mov ax, [y2]
	xor dx, dx
	mov bx, 100
	mov cx, 400
	div bx
	mov ax, dx
	mov dx, 29
	mov n,366
	xor bx, bx
	cmp ax, bx
	jne fin			;se verifica que sea bisiesto cumpliendo que si es múltiplo de 100 y 400 es bisiesto, pero si no lo es de 400 entonces no
	xor dx, dx
	mov ax, [y2]
	div cx
	mov ax, dx
	mov dx, 29
	mov n,366
	xor bx, bx
	cmp ax, bx
	je fin
	mov dx, 28
	jmp fin
salto1:
	mov ax, [m2]
	mov bx, 2
	xor dx, dx
	div bx
	cmp dx, 0
	jng salto4
	jmp short salto3
salto2:
	mov ax, [m2]
	mov bx, 2
	xor dx, dx
	div bx
	cmp dx, 0
	jng salto3
	jmp short salto4
salto3:
	mov dx, 31
	jmp short fin
salto4:
	mov dx, 30
fin:
	mov ndias,dx
	mov ndias2,dx
	cmp y2,1582
	je modificar
	jmp direccion
	
modificar:
	cmp m2,10
	je casoEspecial
	jmp direccion
casoEspecial:
	mov true,1
	mov ndias,4
	cmp q,15
	jne saltoDireccion
	mov true,0
	mov ndias,31
	jmp direccion
saltoDireccion:
	jmp direccion

inicio3:
	mov fila,2
	mov columna,5
	call agregar
	setPosicion fila,columna
	
	mov dx, offset diasm
	imprimirString dx

	mov fila,3
	
	mov ah,valor1
	add fila,ah
	mov direccion, offset paint
	jmp zeller

	
agregar proc near
	mov ah,valor1
	mov al,valor2
	add fila,ah;agrega a la fila el incremento indicado en cada ciclo del programa
	add columna,al;agrega a la columna el incremento indicado en cada ciclo del programa
	ret
agregar endP
	
zeller:
	mov bx, 3
	mov dx, [y]
	mov cx, [m]
	cmp cx, bx
	jge short fin1
	
	add cx, 12
	mov [m], cx
	sub dx, 1
	mov [y], dx
	
fin1:	
	xor dx, dx
	mov bx, 5
	mov cx, 13
	
	mov ax, [m]
	add ax, 1
	mul cx
	div bx
	
	add ax, [q]
	add ax, [y]
	push ax
	
	xor dx, dx
	mov cx, 4
	mov ax, [y]
	div cx
	
	mov cx, ax
	pop ax
	add ax, cx
	push ax
	
	xor dx, dx
	mov cx, 100
	mov ax, [y]
	div cx
	
	mov cx, ax
	pop ax
	sub ax, cx
	push ax
	
	xor dx, dx
	mov cx, 400
	mov ax, [y]
	div cx
	
	mov cx, ax
	pop ax
	add ax, cx
	mov bx, 7
	xor dx, dx
	div bx
	mov cx,m2
	mov m3,cx
	mov cx,y2
	mov y3,cx
	
	cmp dx, 0
	je fin2
	sub dx, 1
	jmp short continuo
fin2:
	mov dx, 6
continuo:	
	cmp [y2], 1582
	je verif1
	cmp [y2], 1582
	jl antes			;Se utiliza calendario juliano
	jmp agregardiasemana
	
verif1:
	cmp [m3], 10
	je verif2
	cmp [m3], 10
	jl antes			;Se utiliza calendario juliano
	jmp agregardiasemana
	
verif2:
	cmp [q], 15
	jge verif3
	jmp antes			;Se utiliza calendario juliano
verif3:
	jmp agregardiasemana
antes:					;Se utiliza calendario juliano
	cmp dx, 3
	jg verif20
	add dx, 3
	jmp previo
verif20:
	cmp dx, 4
	je verif21
	cmp dx, 5
	je verif22
	cmp dx, 6
	je verif23
verif21:
	mov dx, 0
	jmp previo
verif22:
	mov dx, 1
	jmp previo
verif23:
	mov dx, 2
	jmp previo
previo:
	push dx
ciclodiferencia:			;se calculan los días de error correspondientes al calendario juliano para así realizar su debido desplazamiento
	
	cmp [temp], cx
	je intervalo1
	inc cx
	
	xor dx, dx
	mov ax, [y3]
	mov bx, 4
	div bx
	
	cmp dx, 0
	jne modifanio
	
	xor dx, dx
	mov ax, [y3]
	mov bx, 100
	div bx
	
	cmp dx, 0
	jne modifanio
	
	xor dx, dx
	mov ax, [y3]
	mov bx, 400
	div bx
	
	cmp dx, 0
	je modifanio
	
	cmp y2,1501
	jge intervalo20
	cmp y2,1501
	jl diferencia
	jmp modifanio

diferencia:
	add [t], 1
	jmp modifanio
modifanio:
	mov [y3], cx
	jmp ciclodiferencia
verifmes:
	cmp [y3], 1500
	jge modifanio
	jmp	diferencia
intervalo1:
	mov ax, [t]
	cmp	ax, 7
	jl intervalo2
	
	xor dx, dx
	inc ax
	mov bx, 7
	div bx
	mov [t], dx
	jmp intervalo2
intervalo20:
	pop dx
	jmp continuo2
	
intervalo2:
	pop dx
intervalo3:
	mov ax, [t]
	cmp ax, 0
	je continuo2
	
ciclodiferencia2:
	dec ax
	mov [t], ax
	
	cmp dx, 0
	je intervalo4
intervalo5:
	dec dx
	jmp	intervalo3
intervalo4:
	mov dx, 7
	jmp intervalo5
	
continuo2:
	push dx
	mov ax, y2
	mov bx, 4
	xor dx, dx
	div bx
	cmp dx, 0
	je verificardesplazamiento
	pop dx
agregardiasemana:
	mov [d], dx;guarda el resultado en 'd'
	jmp direccion
verificardesplazamiento:			;Se verifica si el año que se consulta es bisiesto para el juliano y no para el gregoriano, si 
									;es así se realiza un desplazamiento a los meses despues del 29 de febrero para arreglarlos
	mov ax, y2
	mov bx, 100
	xor dx, dx
	div bx
	
	cmp dx, 0
	jne sacarsemana
	
	mov ax, y2
	mov bx, 400
	xor dx, dx
	div bx
	
	cmp dx, 0
	je sacarsemana
	
	cmp m2, 3
	jl sacarsemana
	
	pop dx

	cmp dx, 6
	je desplazardias
	
	inc dx
	jmp agregardiasemana
sacarsemana:
	pop dx
	jmp	agregardiasemana
desplazardias:
	mov dx, 0
	jmp agregardiasemana
	
saltoSetLimite:
	jmp setLimite
	
imprimirEnPosicionParaSabado2:
	push dx
	dec columna;decrementa la columna para poder imprimir las decenas
	setPosicion fila,columna
	pop ax
	imprimirCaracter ax
	
	mov ax, ndias
	inc ax
	cmp q, ax;confirma si llegó al último día
	je saltarDireccion
	mov ah,x;incrementa la fila según lo requerido por el programa
	add fila,ah
	jmp direccion	
	
saltarDireccion:
	jmp direccion2

imprimirEnPosicionParaSabado:
	mov al,valor2;modifica la columna según lo requerido
	add columna,al
	mov hecho,0;apaga el indicador 
	setPosicion fila,columna; para jueves
	pop ax
	imprimirCaracter ax;imprime las unidades
	inc q;incrementa el día
	mov d, 0;el siguiente día es domingo
	pop dx
	cmp dx,0;confirma si hay decenas
	jne imprimirEnPosicionParaSabado2;imprime decenas
	mov ah,x;incrementa la fila según lo requerido por el programa
	add fila,ah
	jmp direccion
	
paint2:
	cmp d,3;miercoles
	mov columna,12
	je imprimirEnPosicion
	cmp d,4;jueves
	mov columna,14
	je imprimirEnPosicion
	cmp d,5;viernes
	mov columna,16
	je imprimirEnPosicion
	
	cmp d,6;sabado
	mov columna,18
	je imprimirEnPosicionParaSabado

imprimirEnPosicion2:
	push dx
	dec columna;decrementa la columna para poder imprimir las decenas
	setPosicion fila,columna; para sabado
	pop ax
	imprimirCaracter ax
	jmp direccion	

imprimirEnPosicion:
	mov al,valor2;modifica la columna según lo requerido
	add columna,al
	setPosicion fila,columna
	pop ax
	imprimirCaracter ax
	inc q;aumenta el dia
	inc d;aumenta la correspondencia al día de la semana
	pop dx
	cmp dx,0;confirma que haya decenas
	jne imprimirEnPosicion2
	jmp direccion


paint:
	mov direccion, offset paint
	mov columna,20
	cmp hecho, 0;confirma si se imprimió el número de semana
	je imprimirSemana;en caso de que no, se imprime
	
	mov ax,q
	mov bx,10;para obtener decenas y unidades por separado
	xor dx,dx
	div bx
	push ax;decenas
	push dx;unidades
	
	mov ax, ndias
	inc ax
	cmp q, ax;confirma que se hayan imprimido todos los días del mes
	je setLimite
	
	cmp d,0;domingo
	mov columna,6
	je imprimirEnPosicion
	
	cmp d,1;lunes
	mov columna,8
	je imprimirEnPosicion
	
	cmp d,2;martes
	mov columna,10
	je imprimirEnPosicion

	jmp paint2
	
imprimirSemana:
	inc semana;aumenta la semana en la que se encuentra
	mov al,valor2;modifica la columna según lo requerido
	add columna,al
	setPosicion fila,columna
	push semana
	mov hecho,1;indica que ya se imprimió la semana
	jmp imprimirDecimal	

setLimite:
	mov plineas,15
	mov ax, valor4
	add plineas,ax
	mov limite2,143
	mov ax, valor4
	add limite2,ax
	mov ah,7
	add ah,valor5
	cmp fila,ah
	je crearLineasHorizontales
	
	
	mov limite2,159
	mov ax, valor4
	add limite2,ax
	jmp crearLineasHorizontales
	
crearLineasHorizontales:
	mov direccion, offset crearLineasHorizontales
	mov limite,152
	mov ax, valor3
	add limite,ax
	
	mov ax, limite2
	cmp plineas, ax
	je setLimite2
	
	mov posx,40
	mov ax,valor3
	add posx,ax
	
	
	setInicioLinea posx, plineas;
	add plineas,16
	jmp pintarHaciaDerecha
	
pintarHaciaDerecha:
	inc cx
	int 10h
	cmp cx,limite
	jne pintarHaciaDerecha
	jmp direccion

setLimite2:

	
	mov limite,127
	mov ax, valor4
	add limite,ax
	mov ah,7
	add ah,valor5
	cmp fila,ah
	je crearLineasVerticales
	mov limite,142
	mov ax, valor4
	add limite,ax
	jmp crearLineasVerticales

agregarXY proc near
	xor ax,ax
	mov ax, valor3
	add posx,ax
	xor ax,ax
	mov ax, valor4
	add posy,ax
	ret
agregarXY endP

saltoPintarHaciaAbajo:
	jmp pintarHaciaAbajo

crearLineasVerticales:

	mov direccion, offset crearLineasVerticales
	;primera linea
	mov posx, 40
	mov posy, 15
	call agregarXY
	setInicioLinea posx, posy
	cmp nLineasVerticales, 0
	je saltoPintarHaciaAbajo
	
	;domingo/lunes
	mov posx, 55
	mov posy, 32
	call agregarXY
	setInicioLinea posx, posy
	cmp nLineasVerticales, 1
	je saltoPintarHaciaAbajo
	
	;lunes/martes
	mov posx, 72
	mov posy, 32
	call agregarXY
	setInicioLinea posx, posy
	cmp nLineasVerticales, 2
	je pintarHaciaAbajo
	
	;martes/miercoles
	mov posx, 87
	mov posy, 32
	call agregarXY
	setInicioLinea posx, posy
	cmp nLineasVerticales, 3
	je pintarHaciaAbajo
	
	;miercoles/jueves
	mov posx, 103
	mov posy, 32
	call agregarXY
	setInicioLinea posx, posy
	cmp nLineasVerticales, 4
	je pintarHaciaAbajo	

	
	;jueves/viernes
	mov posx, 119
	mov posy, 32
	call agregarXY
	setInicioLinea posx, posy
	cmp nLineasVerticales, 5
	je pintarHaciaAbajo
	
	jmp crearLineasVerticales2

	
pintarHaciaAbajo:
	inc dx
	int 10h
	cmp dx,limite
	jne pintarHaciaAbajo 	
	inc nLineasVerticales
	jmp direccion
	
crearLineasVerticales2:
	
	;viernes/sabado
	mov posx, 135
	mov posy, 32
	call agregarXY
	setInicioLinea posx,posy
	cmp nLineasVerticales, 6
	je pintarHaciaAbajo
	
	;final
	mov posx, 152
	mov posy, 15
	call agregarXY
	setInicioLinea posx,posy
	cmp nLineasVerticales, 7
	je pintarHaciaAbajo	
	jmp finalizar
	

saltoDeLinea:
	mov valor2, 0
	add valor1, 9
	add valor5, 9
	add valor4, 143
	mov valor3, 0
	cmp d,0
	jne casoEspecialSemana
	jmp inicio2

continuarCasoEspecial:
	mov ax, m2
	mov m, ax
	mov ax, y2
	mov y, ax
	mov q, 15
	mov nLineasVerticales, 0
	jmp direccion3
repetir:
	mov hecho,0
	add valor2, 18
	add valor3, 144
	cmp valor2, 72
	je saltoDeLinea
	cmp d,0
	jne casoEspecialSemana
	jmp inicio2

casoEspecialSemana:
	dec semana
	jmp inicio2
	
	
finalizar:
	mov direccion3, offset inicio2
	cmp true, 1
	je continuarCasoEspecial
	inc cantidadMeses
	mov ax, m2
	mov m, ax
	inc m
	mov nLineasVerticales, 0
	mov ax, y2
	mov y, ax
	
	mov q, 1
	cmp cantidadMeses, 12
	jne repetir
	
	setPosicion 28, 48
	terminar

saltoFinal3:
	jmp final3
	
unicoCalendario:
	setPosicion 04,10;se coloca el cursor en la linea 04 columna 10
	mov dx, offset dias
	imprimirString dx
	mov direccion, offset continuar
	mov fila,02
	mov columna,36
	jmp confirmarMes
continuar:
	mov direccion, offset continuar1b
	mov fila,6
	restaurarValores
	jmp zeller
	
;encuentra la semana
continuar1b:
	mov ax,d	
	mov d2,ax
	mov direccion, offset continuar1c
	restaurarValores
	mov m,1
	mov ax,m2
	mov ubi,ax
	mov m2,1
	mov ax,q
	mov q2,ax
	mov q,1
	jmp zeller
continuar1c:
	mov ax, ubi
	mov m2,ax
	mov ax,d2
	mov ubi,ax
	mov bx,d
	mov d2,bx
	mov ax,ubi
	mov d,ax
	mov ax,q2
	mov q,ax
	restaurarValores
	jmp continuar2a
	
VerificarBisiesto Proc
	xor dx, dx
	mov ax, [y]
	mov bx, 4
	div bx
	cmp dx, 0
	je verbisiprevio
	mov n,365
	jmp FinVerificacion
verbisiprevio:
	cmp y, 1582
	jge verbisi
	mov n,366
	jmp FinVerificacion
verbisi:
	mov ax, [y]
	xor dx, dx
	mov bx, 100
	mov cx, 400
	div bx
	mov ax, dx
	xor bx, bx
	cmp ax, bx
	jne modificaranio			;se verifica que sea bisiesto cumpliendo que si es múltiplo de 100 y 400 es bisiesto, pero si no lo es de 400 entonces no
	jmp continuarverificacion
modificaranio:
	mov n,366
	jmp FinVerificacion
continuarverificacion:
	xor dx, dx
	mov ax, [y]
	div cx
	mov ax, dx
	xor bx, bx
	cmp ax, bx
	je modificaranio
FinVerificacion:
	ret
VerificarBisiesto EndP

continuar2a:
	inc [r]
	mov ax, [m]
	mov bx, [r]
	cmp ax, bx
	je condic
	jmp short caldia
	
condic:
	cmp bx, 1
	je	salto0a
	jmp fin10a
caldia:
	cmp r, 2
	je salto0a
	cmp r, 7
	jng salto1a
	jmp salto2a
salto0a:
	call VerificarBisiesto
	cmp r, 1
	je fin10a
	jmp	continuar2a
salto1a:
	mov ax, [r]
	mov bx, 2
	xor dx, dx
	div bx
	cmp dx, 0
	jng salto4a
	jmp short salto3a
salto2a:
	mov ax, [r]
	mov bx, 2
	xor dx, dx
	div bx
	cmp dx, 0
	jng salto3a
	jmp short salto4a
salto3a:
	add [cant], 31
	jmp continuar2a
salto4a:
	add [cant], 30
	jmp continuar2a

fin10a:
	mov ax, [cant];mueve al ax la cantidad de días obtenidos
	mov dx, [m];mueve al dx el mes
	mov cx, [q];mueve al cx el dia insertado
	add ax, cx;se juntan los días obtenidos y el insertado
	mov [cant], ax;se guarda en cant
	
	cmp dx, 1
	je fin11a
	cmp dx, 2
	je fin11a
	
	add ax, 28
	mov [cant], ax
	jmp fin11a
esbisiesto:
	add ax, 29
	mov [cant], ax
	jmp fin11a
fin11a:
	add ax,d2
	dec ax
	add ax,dx
	xor dx, dx
	mov bx, 7
	div bx
	inc ax

fin12a:
	mov semana,ax
	cmp modo,2
	je imprimir
	dec semana
	jmp paint3
imprimir:
	mov [b], ax     ; Guardar el resultado en la variable d
	xor ax,ax
	; Mostrar el resultado en la salida
	mov dx, offset msg ; Dirección del mensaje a imprimir
	imprimirString dx
	getposicion
	push b
	mov direccion, offset imprimirNumeroDia
	jmp imprimirDecimal

imprimirNumeroDia:
	mov dx, offset msg2 
	imprimirString dx
	getposicion
	inc columna
	push cant
	mov direccion, offset imprimirRestantes	;meter la direccion del segmento en el cual se desea salir
	jmp imprimirDecimal
imprimirRestantes:
	mov dx, offset msg3
	imprimirString dx
	getposicion
	inc columna
	mov ax, [n]
	mov bx, [cant]
	sub ax, bx
	push ax
	mov direccion, offset finalizar2	;meter la direccion del segmento en el cual se desea salir
	jmp imprimirDecimal
finalizar2:
	terminar
paint4:
	cmp d,5
	mov columna,56
	je saltoImprimirEnPosicion
	
	cmp d,6
	mov columna,65
	je saltoImprimirEnPosicionParaSabado
	
	
saltoImprimirEnPosicion:
	mov x,2
	mov direccion, offset paint3
	jmp imprimirEnPosicion
	
saltoImprimirEnPosicionParaSabado:
	mov x,2
	mov direccion, offset paint3
	jmp imprimirEnPosicionParaSabado

paint3:
	mov direccion, offset paint3
	mov columna,73
	cmp hecho, 0
	je saltoImprimirSemana
	mov ax,q
	mov bx,10
	xor dx,dx
	div bx
	push ax;decenas
	push dx;unidades
	
	mov ax, ndias
	inc ax
	cmp q, ax
	je setLimite3
	
	cmp d,0
	mov columna,13
	je saltoImprimirEnPosicion
	
	cmp d,1
	mov columna,22
	je saltoImprimirEnPosicion
	
	cmp d,2
	mov columna,29
	je saltoImprimirEnPosicion
	
	cmp d,3
	mov columna,38
	je saltoImprimirEnPosicion
	
	cmp d,4
	mov columna,48
	je saltoImprimirEnPosicion
	
	jmp paint4	

saltoImprimirSemana:
	jmp imprimirSemana
	
	
setLimite3:
	cmp fila,14
	mov limite2,278
	je crearLineasHorizontales2
	mov limite2,310
	jmp crearLineasHorizontales2
crearLineasHorizontales2:
	mov ax, limite2
	cmp plineas, ax
	je setLimite4
	
	
	setInicioLinea 70, plineas;
	add plineas,32
	jmp pintarHaciaDerecha
	
saltoPintarHaciaDerecha:
	mov direccion, offset crearLineasHorizontales2
	jmp pintarHaciaDerecha

setLimite4:
	cmp fila,14
	mov limite,246
	je crearLineasVerticales3
	mov limite,278
	jmp crearLineasVerticales3

crearLineasVerticales3:

	;primera linea
	setInicioLinea 70,22
	cmp nLineasVerticales, 0
	je saltoPintarHaciaAbajo2
	
	;domingo/lunes
	setInicioLinea 143,54
	cmp nLineasVerticales, 1
	je saltoPintarHaciaAbajo2
	
	;lunes/martes
	setInicioLinea 200,54
	cmp nLineasVerticales, 2
	je saltoPintarHaciaAbajo2
	
	;martes/miercoles
	setInicioLinea 261,54
	cmp nLineasVerticales, 3
	je saltoPintarHaciaAbajo2
	
	;miercoles/jueves
	setInicioLinea 352,54
	cmp nLineasVerticales, 4
	je saltoPintarHaciaAbajo2	
	jmp crearLineasVerticales4

	
saltoPintarHaciaAbajo2:
	mov direccion, offset crearLineasVerticales3
	jmp pintarHaciaAbajo
	
crearLineasVerticales4:
	;jueves/viernes
	setInicioLinea 413,54
	cmp nLineasVerticales, 5
	je saltoPintarHaciaAbajo2
	
	;viernes/sabado
	setInicioLinea 487,54
	cmp nLineasVerticales, 6
	je saltoPintarHaciaAbajo2
	
	;final
	setInicioLinea 550,22
	cmp nLineasVerticales, 7
	je saltoPintarHaciaAbajo2
	jmp final2	
	
final2:
	mov direccion, offset final3
	mov fila,02
	mov columna,34
	push y2
	jmp imprimirDecimal

saltoContinuarCasoEspecial:
	jmp continuarCasoEspecial

continuar2:
	mov fila,06
	mov direccion, offset paint3
	restaurarValores
	jmp zeller	
final3:
	mov direccion, offset continuar2
	mov direccion3, offset casoEspecial
	cmp true, 1
	je saltoContinuarCasoEspecial
	setPosicion 28, 48
	terminar
	
codigo Ends
end inicio	