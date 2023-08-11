"""
Funcion principal de elevarPotencia
param numero, numero que se elevara por la potencia
param potencia, veces que se multiplicara el numero
"""
def elevarAPotencia(numero, potencia):
    if(isinstance(numero, int) and isinstance(potencia, int) and potencia >= 0):
        return elevarAPotenciaAux(numero, potencia);
    else:
        raise ValueError("El argumento es incorrecto");
        


"""
Funcion auxiliar
"""
def elevarAPotenciaAux(numero, potencia):
    if(potencia > 0):
        return numero * elevarAPotenciaAux(numero, potencia - 1);
    elif(potencia == 0):
        return 1;

#prueba elevar potencia
numElevado = elevarAPotencia(16, 3);
print("Numero elevado: " + str(numElevado));
        
