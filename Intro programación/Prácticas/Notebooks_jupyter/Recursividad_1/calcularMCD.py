"""
Funcion principal para el calculo del MCD, que ademas revisa las restricciones
Restricciones: los argumentos deben ser enteros positivos
@param numero1, primer entero a usar para el calculo del MCD
@param numero2, segundo entero a usar para el calculo del MCD
@return mcd, el maximo comun divisor entre los numeros
"""
def calcularMCD(numero1, numero2):
    if(isinstance(numero1, int) and isinstance(numero1, int)):
        #toma el absoluto de los numeros
        numero1 = abs(numero1);
        numero2 = abs(numero2);
         #si numero2 es mayor que numero1, se intercambian
        if(numero2 > numero1):            
            numero2, numero1 = numero1, numero2;
        #se invoca la funcion auxiliar
        return calcularMCD_aux(numero1, numero2);
    else:
        raise ValueError("Todos los argumentos deben ser enteros");


"""
Calcula el MCD de dos numeros, de manera recursiva
param numero1, primer entero a usar para el calculo del MCD
param numero2, segundo entero a usar para el calculo del MCD
"""
def calcularMCD_aux(numero1, numero2):
    residuo = numero1 % numero2;
    if(residuo > 0):
        return calcularMCD_aux(numero2, residuo);
    else:
        return numero2;

#calculo del MCD de prueba
mcd = calcularMCD(12, 18);
print("MCD: " + str(mcd));
