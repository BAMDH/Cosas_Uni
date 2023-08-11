# -*- coding: utf-8 -*-
"""
Created on Wed May 18 16:13:25 2022

@author: Usuario
"""

def Restar_digito(restador,restado,contador):
        digito_menos_significativo= restado%10
        resta=digito_menos_significativo-restador
        if restado== 0:
            return 0
        if resta >= 0:
            potencia= 10**contador
            return resta*potencia+Restar_digito(restador, restado//10, contador+1)
        else:
            return Restar_digito(restador, restado//10, contador+1)
        
        
Prueba1= Restar_digito(7, 9978,0)
Prueba2= Restar_digito(7, 1084,0)
Prueba3= Restar_digito(4, 13,0)
print("Prueba1",Prueba1)
print("Prueba2",Prueba2)
print("Prueba3",Prueba3)