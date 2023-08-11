Include macroC.asm ;incluye macros
datos1 Segment
	fila1 db ?
	columna1 db ?
datos1 endS
Procedimientos Segment 
	Public pintarHaciaDerecha,pintarHaciaAbajo,imprimirCantidad,imprimirPos
	Assume CS:Procedimientos, ds:datos1

imprimirCantidad proc far
	mov cx,0
	mov bp, sp
	mov ax, bp[+4]
	mov fila1, ah
	mov ax, bp[+6]
	mov columna1, ah
	mov ax, bp[+8]
	cmp ax,0;confirma si es negativo
	jl casoNegativo;en caso de serlo, manipula el número para que sea imprimible
	push ax
	
impCantidad:
	setPosicion fila1, columna1;coloca el puntero en la fila y columna indicada
	pop ax;saca el valor pasado por pila
	
	xor dx, dx
	mov bx,10;así se consigue el último digito de la cantidad
	div bx
	push ax;metemos el residuo en la pila
	imprimirCaracter dx;imprimir el ultimo digito
	dec columna1;de esta manera se imprimirá de derecha a izquierda
	pop ax
	cmp ax,0;comparamos que el residuo no sea cero
	push ax
	jne impCantidad;en caso de que sigan habiendo digitos por imprimir, se repite el proceso
	jmp confirmarSigno

casoNegativo:
	mov cx,1;se indica que es un negativo
	mov bx,-1;lo vuelve positivo
	xor dx,dx
	mul bx
	push ax;lo pasa por pila
	jmp impCantidad

confirmarSigno:
	cmp cx,1;en caso de que la cantidad sea negativa, se procederá a imprimir el signo '-'
	je imprimirNegativo
	jmp final;salta a la direccion de la label que posee esta variable
imprimirNegativo:
	setPosicion fila1, columna1;coloca el puntero en la fila y columna indicada
	mov ah,02h;imprime un caracter
	mov dx,2Dh;simbolo '-'
	int 21h
	jmp final
final:
	pop ax
	ret
imprimirCantidad endP	
	

imprimirPos proc far
	mov bp, sp
	mov ax, bp[+4]
	mov fila1, ah
	mov columna1, al
	mov ax, bp[+8]
	push ax
	mov ax, bp[+6]
	push ax
	setPosicion fila1,columna1
	pop ax
	imprimirCaracter ax
	pop dx
	cmp dx,0;confirma que haya decenas
	jne imprimirEnPosicion2
	jmp final1
imprimirEnPosicion2:
	push dx
	dec columna1;decrementa la columna para poder imprimir las decenas
	setPosicion fila1,columna1; para sabado
	pop ax
	imprimirCaracter ax
	jmp final1
final1:
	ret
	
imprimirPos endP
	
	
;********************************
;pinta la linea hacia la derecha*
;********************************
pintarHaciaDerecha proc far
	mov bp, sp
	mov bx, bp[+4]
repetir:

	push bx
	xor bx,bx
	inc cx;incrementa la posición en x de la linea
	int 10h;imprime
	pop bx
	cmp cx,bx;confirma que no haya llegado al limite
	jne repetir;repite el ciclo hasta llegar al limite
	ret
pintarHaciaDerecha endP
;***************************
;pinta la linea hacia abajo*
;***************************	
pintarHaciaAbajo proc far
	mov bp, sp
	mov bx, bp[+4]
repetir1:
	push bx
	xor bx,bx
	inc dx;incrementa la posición en x de la linea
	int 10h;imprime
	pop bx
	cmp dx,bx;confirma que no haya llegado al limite
	jne repetir1;repite el ciclo hasta llegar al limite
	ret
pintarHaciaAbajo endP
Procedimientos EndS
END