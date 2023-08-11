def contarDigitos(numero):
	if isinstance(numero,int):
		if numero != 0: #inicializacion del argumento a construir en la cola
			return contarDigitosAux(abs(numero), 0)
		else:
			return 1;	
	return "Error: número debe ser entero"
	
def contarDigitosAux(numero, numDigitos):
	if numero == 0: # condición de terminación
		return numDigitos;  # valor de retorno
	else:
		return contarDigitosAux(numero // 10, 1 + numDigitos)

print("Contar digitos: " + str(contarDigitos(5555)));
