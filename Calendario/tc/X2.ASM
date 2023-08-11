
DGROUP 		GROUP _DATA, _BSS

_DATA 		SEGMENT WORD PUBLIC 'DATA'
			
	
y 			DW ?
y2 			DW ?
y3 			DW ?
m 			DW ?
m2 			DW ?
m3 			DW ?
q 			DW ?
t 			DW ? 
true 			DW ? 
cant 			DW ? 
n 			DW ? 
ctrlWord DW ?
ndias 			DW ? 
temp dw 1582
dir 			DW ? 
valor1 DW	 ?
valor2 DW	 ?
valor3 DW	 ?



_DATA 		ENDS

_BSS SEGMENT WORD PUBLIC 'BSS'
			PUBLIC _info
			EXTRN _variable:WORD ; variable externa
_info 		DW ? ; sin valor inicial
_BSS 			ENDS

_TEXT 		SEGMENT BYTE PUBLIC 'CODE'
 			ASSUME CS:_TEXT,DS:DGROUP,SS:DGROUP
 	
			PUBLIC _calcularDias ; _calcularDias será accesible desde el programa C
			PUBLIC _calcularDiasJulianos ; _calcularDiasJulianos será accesible desde el programa C
			PUBLIC _comprobarNumeroDias
			PUBLIC _cantidadDias
			PUBLIC _conversorAJuliano
_calcularDias 		PROC NEAR ; funcion() del C
			mov dir, BP
 			MOV BP,SP
 			MOV BX,[BP+2] ; recuperar variable 'a'
 			MOV CX,[BP+4] ; recuperar variable 'm'
			
			mov y, BX
			mov y2, BX
			mov y3, BX
			mov m, CX

			MOV BX,[BP+6] ; recuperar variable 'd'
 			MOV CX,[BP+8] ; recuperar variable 't'			
			MOV q, BX
			MOV t, CX
			
 			MOV AX,[BP+10] ; AX = 5678h
 			MOV DX,[BP+12] ; DX = 1234h -> DX:AX = 12345678h
			finit
			;13(m+1)/5
			fstcw ctrlWord ; store control word
			or ctrlWord,110000000000b ; set RC = truncate
			fldcw ctrlWord ; load control word
			mov t,5
			fild t
			mov t,13
			fild t
			mov t,1
			fild t
			fild m
			fadd st,st(1)
			fmul st,st(2)
			fdiv st,st(3)
			fist t
			
			;y/4
			finit
			fstcw ctrlWord ; store control word
			or ctrlWord,110000000000b ; set RC = truncate
			fldcw ctrlWord ; load control word
			mov y2, 4
			fild y2
			fild y
			fdiv st, st(1)
			fist y2
			
			;y/100
			finit
			fstcw ctrlWord ; store control word
			or ctrlWord,110000000000b ; set RC = truncate
			fldcw ctrlWord ; load control word
			mov y3, 100
			fild y3
			fild y
			fdiv st, st(1)
			fist y3
			
			;y/400
			finit
			fstcw ctrlWord ; store control word
			or ctrlWord,110000000000b ; set RC = truncate
			fldcw ctrlWord ; load control word
			mov m2, 400
			fild m2
			fild y
			fdiv st, st(1)
			fist m2
			
			
			fild m2
			fild y3
			fild y2
			fild y
			fild t
			fild q
			fadd st,st(1)
			fadd st,st(2)
			fadd st,st(3)
			fsub st,st(4)
			fadd st,st(5)
			fist y
			
			mov ax, y
			mov bx, 7
			xor dx, dx
			div bx
			
			mov ax, dx
			mov BP, dir
			ret
_calcularDias		ENDP
_calcularDiasJulianos 		PROC NEAR ; funcion() del C
			mov dir, BP
 			MOV BP,SP
 			MOV BX,[BP+2] ; recuperar variable 'a'
 			MOV CX,[BP+4] ; recuperar variable 'm'
			
			mov y, BX
			mov y2, BX
			mov y3, BX
			mov m, CX

			MOV BX,[BP+6] ; recuperar variable 'd'
 			MOV CX,[BP+8] ; recuperar variable 't'			
			MOV q, BX
			MOV t, CX
			
 			MOV AX,[BP+10] ; AX = 5678h
 			MOV DX,[BP+12] ; DX = 1234h -> DX:AX = 12345678h
			finit
			;13(m+1)/5
			fstcw ctrlWord ; store control word
			or ctrlWord,110000000000b ; set RC = truncate
			fldcw ctrlWord ; load control word
			mov t,5
			fild t
			mov t,13
			fild t
			mov t,1
			fild t
			fild m
			fadd st,st(1)
			fmul st,st(2)
			fdiv st,st(3)
			fist t
			
			;y/4
			finit
			fstcw ctrlWord ; store control word
			or ctrlWord,110000000000b ; set RC = truncate
			fldcw ctrlWord ; load control word
			mov y2, 4
			fild y2
			fild y
			fdiv st, st(1)
			fist y2
			
			
			
			mov m2,5
			fild m2
			fild y2
			fild y
			fild t
			fild q
			fadd st,st(1)
			fadd st,st(2)
			fadd st,st(3)
			fadd st,st(4)
			
			fist y
			
			
			mov ax, y
			mov bx, 7
			xor dx, dx
			div bx
			
			mov ax, dx
			mov BP, dir
			ret
_calcularDiasJulianos		ENDP

_conversorAJuliano PROC NEAR
			mov dir, BP
 			MOV BP,SP
 			MOV BX,[BP+2] ; recuperar variable 'y'
 			MOV CX,[BP+4] ; recuperar variable 'm'	
			mov y, BX
			mov m, CX
			MOV BX,[BP+6] ; recuperar variable 'dia'		
			MOV q, BX
			MOV BX,[BP+8] ; recuperar variable 'true'
				
			mov true,BX
 			MOV AX,[BP+10] ; AX = 5678h
 			MOV DX,[BP+12] ; DX = 1234h -> DX:AX = 12345678h
			

			;a=Y/100
			finit
			fstcw ctrlWord ; store control word
			or ctrlWord,110000000000b ; set RC = truncate
			fldcw ctrlWord ; load control word
			mov y2,100
			fild y2
			fild y
			fdiv st,st(1)
			fist y2
			
			;A/4
			
			finit
			fstcw ctrlWord ; store control word
			or ctrlWord,110000000000b ; set RC = truncate
			fldcw ctrlWord ; load control word
			mov y3,4
			fild y3
			fild y2
			fdiv st,st(1)
			fist y3
			
			;b=2-A+(a/4)
			finit
			fild y3
			fild y2;a
			mov m2,2
			fild m2
			fsub st,st(1)
			fadd st,st(2)
			fist m2
			
			
			;INT(365,25(Y+4716) )//primeros cuatro valores
			mov  ax, y
			add ax,4716
			mov y, ax
			finit
			fstcw ctrlWord ; store control word
			or ctrlWord,110000000000b ; set RC = truncate
			fldcw ctrlWord ; load control word
			mov valor1, 10000 
			fild valor1
			fild y
			mov valor1, 1461 
			mov valor2, 4
			fild valor2
			fild valor1
			fdiv st,st(1)
			fmul st,st(2)
			fdiv st,st(3)
			fist valor3
			
			;INT(365,25(Y+4716) )/ultimos cuatro valores
			finit
			fstcw ctrlWord ; store control word
			or ctrlWord,110000000000b ; set RC = truncate
			fldcw ctrlWord ; load control word
			fild valor3
			mov valor1, 10000 
			fild valor1
			fild y
			mov valor1, 1461 
			mov valor2, 4
			fild valor2
			fild valor1
			fdiv st,st(1)
			fmul st,st(2)
			fdiv st,st(3)
			fsub st,st(4)
			fmul st,st(3)
			fist valor2
			
			
			;INT(30,6001(M+1) )
			inc m
			finit
			fstcw ctrlWord ; store control word
			or ctrlWord,110000000000b ; set RC = truncate
			fldcw ctrlWord ; load control word
			fild m
			mov t, 30
			fild t
			mov valor1, 6001
			mov t, 10000
			fild t
			fild valor1
			fdiv st,st(1)
			fadd st,st(2)
			fmul st,st(3)
			fist y3
			
			;DJ=INT(365,25(Y+4716) )+INT(30,6001(M+1) )+D+B-1524,5
			fild y3
			fild q
			fild m2
			fild valor2
			mov t,2
			fild t
			mov t, -3049
			fild t
			
			fdiv st,st(1);-3049/2 = -1524.5
			
			fadd st,st(2);+primeros cuatro valores
			fadd st,st(3);+b
			fadd st,st(4);+d
			fadd st,st(5);+y3
			
			fist y
			mov ax,y
			cmp true,0
			je xfin
			cmp true,1
			mov ax, valor3
			cmp true,2
			je decimal
			xfin:
		
			mov BP, dir
			ret
			decimal:
				finit
				mov t, 10
				fild t
				fild y
				fild y3
				fild q
				fild m2
				fild valor2
				mov t,2
				fild t
				mov t, -3049
				fild t
				fdiv st,st(1);-3049/2 = -1524.5
				fadd st,st(2);+primeros cuatro valores
				fadd st,st(3);+b
				fadd st,st(4);+d
				fadd st,st(5);+y3
				fsub st,st(6);
				fmul st ,st(7)
				fist y
				mov ax,y
				jmp xfin
			
_conversorAJuliano ENDP

_comprobarNumeroDias PROC NEAR
			mov dir, BP
 			MOV BP,SP
 			MOV BX,[BP+2] ; recuperar variable 'y'
 			MOV CX,[BP+4] ; recuperar variable 'm'	
			
			mov y, BX
			mov y2, BX
			
			mov m, CX
			mov m2, CX
			MOV BX,[BP+6] ; recuperar variable 'dia'
 			MOV CX,[BP+8] ; recuperar variable 'true'			
			MOV q, BX
			MOV true, CX
 
 			MOV AX,[BP+10] ; AX = 5678h
 			MOV DX,[BP+12] ; DX = 1234h -> DX:AX = 12345678h
			cmp m2, 2;comprueba que es enero
			je salto0
			cmp m2, 7;de lo contrario comprueba si es menor a julio
			jng salto1
			jmp salto2
			salto0:
				cmp y2,1582;comprueba que sea parte del juliano
				jl salto01
				
				xor dx, dx
				mov ax, [y2];de lo contrario, calcula que sea bisiesto
				mov bx, 4
				div bx
				cmp dx, 0
				je verbis
				mov dx, 28;si no es bisiesto, la cantidad de días es 28 para febrero
				jmp fin
			salto01:
				mov ax,y2
				mov bx,4
				xor dx,dx
				div bx
				cmp dx,0;comprueba que sea bisiesto
				mov dx,29;en caso de serlo, la cantidad de días es 29
				je fin
				mov dx,28;en caso de no serlo, la cantidad es 28
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
				mov dx, 31;el numero de días es 31
				jmp short fin
			salto4:
				mov dx, 30;el numero de días es 30
			fin:
				mov ndias,dx;guarda la cantidad de días
				
				cmp y2,1582;en caso de ser 1582
				je modificar;se modifica
				jmp direccion
				
			modificar:
				cmp m2,10;confirma que sea octubre
				je casoEspecial; en caso de serlo, salta al caso especial
				jmp direccion
			casoEspecial:
				mov true,1;activa el indicador de que está en fecha juliana
				mov ndias,4;para juliano
				cmp q,15;comprueba si ya es fecha gregoriana
				jne direccion
				mov true,0;desactiva el indicador
				mov ndias,31;para gregoriano
				jmp direccion

			direccion:
				mov AX, ndias
				mov BP, dir
				ret
_comprobarNumeroDias ENDP
_cantidadDias PROC NEAR
	mov dir, BP
 			MOV BP,SP
			mov t,0
			xor dx,dx
			xor bx,bx
			xor dx,dx
			xor ax,ax
 			MOV BX,[BP+2] ; recuperar variable 'y'
 			MOV CX,[BP+4] ; recuperar variable 'm'	
			mov cant,0
			mov n,0
			mov y, BX
			mov m, CX
			MOV CX,[BP+6] ; recuperar variable 'q'	
 			mov q,CX
			MOV CX,[BP+8] ; recuperar variable 'true'	
 			mov true,CX
			MOV AX,[BP+10] ; AX = 5678h
 			MOV DX,[BP+12] ; DX = 1234h -> DX:AX = 12345678h
			jmp continuar2a

			VerificarBisiesto Proc
				xor dx, dx
				mov ax, [y]
				mov bx, 4
				div bx
				cmp dx, 0;confirma si es divisible por cuatro
				je verbisiprevio;de serlo procede a confirmar otras condiciones
				mov n,365;mueve 365 días al no ser un año bisiesto
				jmp FinVerificacion
			verbisiprevio:
				cmp y, 1582;compara que sea parte del calendario juliano
				jge verbisi; en caso de ser mayor, procede a verificar otras condiciones
				mov n,366;al ser bisiesto, añade 366 días
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
				mov n,366;al ser bisiesto, se agrega 366 días
				jmp FinVerificacion
			continuarverificacion:
				xor dx, dx
				mov ax, [y]
				div cx
				mov ax, dx
				xor bx, bx
				cmp ax, bx;compara que sea divisible por 400
				je modificaranio
			FinVerificacion:
				ret
			VerificarBisiesto EndP

			continuar2a:
				inc [t]
				mov ax, [m]
				mov bx, [t]
				cmp ax, bx
				je condic
				jmp short caldia
				
			condic:
				cmp bx, 1
				je	salto0a
				jmp fin10a
			caldia:
				cmp t, 2
				je salto0a
				cmp t, 7
				jng salto1a
				jmp salto2a
			salto0a:
				call VerificarBisiesto
				cmp t, 1
				je fin10a
				jmp	continuar2a
			salto1a:
				mov ax, [t]
				mov bx, 2
				xor dx, dx
				div bx
				cmp dx, 0
				jng salto4a
				jmp short salto3a
			salto2a:
				mov ax, [t]
				mov bx, 2
				xor dx, dx
				div bx
				cmp dx, 0
				jng salto3a
				jmp short salto4a
			salto3a:
				add [cant], 31;añade 31 días
				jmp continuar2a
			salto4a:
				add [cant], 30;añade 30 días
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
				
				add ax, 28;se agrega los días de febrero
				mov [cant], ax;se guarda en cant
				jmp fin11a
			esbisiesto:
				add ax, 29;se agrega los días de febrero bisiesto
				mov [cant], ax
				jmp fin11a
			fin11a:
				cmp true,0
				je calcsemana
				cmp true,1
				mov ax,cant
				je ed
				mov ax,n
				jmp ed
			calcsemana:
				finit
				
				mov t,7
				fild t
				fild cant
				fdiv st,st(1)
				fist t
				mov ax,t
				inc ax
				jmp ed
			ed:
			
				mov BP, dir
				ret
			
_cantidadDias ENDP

_TEXT 		ENDS
 			END