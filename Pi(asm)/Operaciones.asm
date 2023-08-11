.386
.model flat, C

.data
b dd ?
d dd 0
h dd 0
f dd 10000
g dd 0
a dd 0
temp dd 0
ctrlWord dw 0

.code


OPTION LANGUAGE: SYSCALL
@PrimerCalculo@8 proc
	mov	EAX, ECX;recuperar b
	mov EBX, EDX;recuperar a
	mov b,EAX
	mov a,EBX
	mov f,10000
	;d *= b;
	finit
	fild d
	fild b
	fmul
	fist d
	
	
	cmp h,0
	je primeraCondi
	jmp segundaCondi
	
	primeraCondi:
	;d += 2000 * f
	finit
	fild d
	mov temp, 2000
	fild temp
	fild f
	fmul st,st(1)
	fadd st,st(2)
	fist d
	jmp fin
	
	segundaCondi:
	;d += a * f;
	finit
	fild d
	fild a
	fild f
	fmul st,st(1)
	fadd st,st(2)
	fist d
	
	jmp fin
    

	
    fin:
	;g = b + b - 1
	fild b
	mov temp,1
	fild temp
	fild b
	fsub st,st(1)
	fadd st,st(2)
	fist g
	
	;a = d % g;
	mov eax, d
	mov ebx,g
	xor edx,edx
	div ebx
	mov a, edx
	
	
	
	mov eax, a
	
	ret
        

@PrimerCalculo@8 ENDP



@ModD@0 proc
	;d=d/g
	finit
	fstcw ctrlWord ; store control word
	or ctrlWord,110000000000b ; set RC = truncate
	fldcw ctrlWord ; load control word
	fild g
	fild d
	fdiv st,st(1)
	fist d
	
	mov eax, d
	ret

@ModD@0 ENDP
@ModD2@0 proc
	;d=d%f
	mov eax, d
	mov ebx,f
	xor edx,edx
	div ebx
	mov d, edx
	mov eax, d
	mov h,4
	ret

@ModD2@0 ENDP
@Reiniciar@0 proc
	mov d,0
	mov g,0
	mov f,10000
	mov h,0
	mov a,0
	mov temp,0
	ret

@Reiniciar@0 ENDP

OPTION LANGUAGE: C
END
