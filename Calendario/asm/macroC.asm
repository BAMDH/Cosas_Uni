;********************************
;inicializa el segmento de datos*
;********************************
inicializarDatos Macro datos
	xor ax,ax
	mov ax,datos
	mov ds,ax
EndM

;***************************
;inicializa el modo gráfico*
;***************************
modoGrafico Macro
	mov al,12h
	xor ah,ah
	int 10h
endM 

;***********************************
;Restaura los valores del mes y año*
;***********************************
restaurarValores Macro
	mov ax,y2
	mov y,ax
	mov ax,m2
	mov m,ax
endM

;***********************************
;Respalda los valores del mes y año*
;***********************************
guardarValores Macro
	mov ax,y
	mov y2,ax
	mov ax,m
	mov m2,ax
endM

;***************************************
;pone el cursor en la posicion indicada*
;***************************************
setPosicion Macro fila, columna
	mov ah, 02h
	mov bh, 00
	mov dh,fila
	mov dl,columna
	int 10h
EndM

;*********************************
;consigue la posicion del cursosr*
;*********************************
getPosicion Macro 
	xor dx,dx
    mov	 ah,03
	mov	 bh,00
    int	 10h
	mov fila, dh
	mov columna,dl
EndM

;******************************************
;Imprime un caracter en su version decimal*
;******************************************
imprimirCaracter Macro Caracter
	mov ah,02h
	mov dx,Caracter
	add dx,30h
	int 21h
EndM

;******************
;Imprime un string*
;******************
imprimirString Macro String
	mov ah,09h
	mov dx,String
	int 21h
EndM

;*********************************
;pone el primer punto de la linea*
;*********************************
setInicioLinea Macro x,y
	mov ah, 0ch
	mov al, 0eh
	xor bh,bh
	mov cx,x
	mov dx,y
	int 10h
endM

;********************
;termina el programa*
;********************
terminar Macro
	mov ax, 4c00h
	int 21h
endM
