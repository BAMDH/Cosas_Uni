def aproximar_poblacion_bacteriana_aux(poblacion,n,turnos):
   
    turnos=turnos+2 
    if turnos < n:
        
        nueva_poblacion= poblacion*3+poblacion
        return nueva_poblacion + aproximar_poblacion_bacteriana_aux(poblacion,n,turnos);
       
    else:
        
        return 0
def aproximar_poblacion_bacteriana(poblacion_inicial,n):
    
    
    if isinstance(poblacion_inicial, int) and isinstance(n,int):
        if n== 1:
            return poblacion_inicial
        else:
          turnos= 0
          aproximar= aproximar_poblacion_bacteriana_aux(poblacion_inicial,n,turnos)
          return aproximar
    else:
        return"Valores invalidos"

b= aproximar_poblacion_bacteriana(3,7)
print(b)

