def extraerDigitoMayor(numero):
    if(numero > 0):
        divEntera =  numero // 10;
        residuo = numero % 10;
        return calcularMayor(residuo, extraerDigitoMayor(divEntera));
    else:
        return 0;
		
def extraerDigitoMayor2(numero):
	

def calcularMayor(numero1, numero2):
    mayor = numero1;
    if(numero2 > numero1):
        mayor = numero2;
    return mayor;

def principal():
    digMayor = extraerDigitoMayor(85657);
    print(digMayor);

principal();
