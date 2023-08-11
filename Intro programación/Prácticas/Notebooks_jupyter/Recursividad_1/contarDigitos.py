import trace;

"""
Funcion recursiva que cuenta la cantidad de digitos en un numero
@param numero, numero al que se le contará la cantidad de digitos
@return numDigitos, cantidad de digitos

"""
def contarDigitos(numero):
    #se divide de manera entera el numero, si es 0, ya no hay que 
    #hacer llamados recursivos
    divEntera = numero // 10;
    
    if(divEntera != 0): 
        #llamado recursivo si la div. entera es mayor a cero
        return  1 + contarDigitos(numero // 10);
    else:
        #condicion de parada
        return 1;
    
    
"""
Pruebas
"""
numero = 2564;
numDigitos = contarDigitos(-151);
print("Numero de digitos en: " + str(numero) + " es: " + str(numDigitos) );
#tracer = trace.Trace(count=0, trace=1, countfuncs=0, countcallers=0)
#tracer.runfunc(contarDigitos,-1155);

    
