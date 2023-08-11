# -*- coding: utf-8 -*-
"""
Created on Fri Apr 15 22:32:39 2022

@author: Usuario
"""

"""
sacar billetes de cajero
"""

#Traemos las funciones con necesarias para traer los datos del cajero solicitado y rescribirlos 
from agregar_billetes_cajero_existente import cargar_datos_cajero, reescribir_cajero



#para esta funcion va a tomar dos parametros tendra que tomar el monto que el ususario desea retirar, y el nombre de cajero
def sacar_billetes_cajero(entrada, monto):
  
  #se abre el archivo del cajero y se lee su contenido 
  #se guarda la cantidad de billetes que tenga el cajero
  los_billetes = cargar_datos_cajero(entrada)
  
 #se conrifma que el cajero tiene dinero
  if int(los_billetes["Monto"])== 0:
      return "Cajero sin fondos"
  #Se confirma que el usuario no esta pidiendo un monto mayor al del cajero 
  elif int(los_billetes["Monto"])< monto:
    return "El cajero no tiene suficientes fondos"
  else:
      #Se crea variable que almacene los datos del archivo del cajero 
      nuevos_los_billetes= {"Cien":int(los_billetes["Cien"]),"Cincuenta":int(los_billetes["Cincuenta"]),"Veinte":int(los_billetes["Veinte"]),"Diez":int(los_billetes["Diez"]),"Cinco":int(los_billetes["Cinco"]),"Dos":int(los_billetes["Dos"]),"Uno":int(los_billetes["Uno"]),"Monto":int(los_billetes["Monto"])}
      #Se crea variable que ejecute la funcion para saber cuantos billetes va a retirar el usuario 
      retirar= sacar_billetes_aux(nuevos_los_billetes, monto, 0, 0,0 ,0, 0, 0, 0)
      if retirar == 'El cajero no tiene fondos suficientes':
          return "El cajero no tiene suficientes fondos"
      else:   
          #Se modifica la variable del cajero restando el monto que se acaba de retirar por el monto que se encontraba en el cajero
          nuevos_los_billetes["Monto"]-=monto
          #se archivan los datos en el cajero 
          reescribir_cajero(nuevos_los_billetes, entrada)
"""
+Los_billetes = los billetes que existen en el cajero por denominacion
+Monto_sacar = el monto que el usuario desea sacar 
+Lo demas son las variables que almacenan cuantos billetes se estan retirando por denominacion 
"""
def sacar_billetes_aux(los_billetes, monto_sacar,billete100,billete50,billete20, billete10,billete5,billete2,billete1):
    #los if representan si el monto deseado por el usuario es : con que denominacion se puede sacar y si esta se encuentra disponible en el cajero
    #se utiliza recursividad de cola almacenando los billetes que se pueden retirar del cajero en variables definidas dentro de la funcion
    #tambien se confirma que la cantidad de billetes para dar es la minima, utilizando un orden, comnfirmando cual es la denominacion mas grande que alcanza en el monto deseado por el usuario 
    #luego confirmando que esa denomincaion esta disponible en el cajero
    
    if monto_sacar - 100 >= 0 and los_billetes['Cien'] > 0:
      los_billetes['Cien']-=1
      return sacar_billetes_aux(los_billetes, monto_sacar-100, billete100+1, billete50, billete20, billete10, billete5, billete2, billete1)
    if monto_sacar - 50 >= 0 and los_billetes['Cincuenta'] > 0 :
      los_billetes['Cincuenta']-=1
      return sacar_billetes_aux(los_billetes, monto_sacar-50, billete100, billete50+1, billete20, billete10, billete5, billete2, billete1)
    if monto_sacar - 20 >= 0 and los_billetes['Veinte'] > 0:
      los_billetes['Veinte']-=1 
      return sacar_billetes_aux(los_billetes, monto_sacar-20, billete100, billete50, billete20+1, billete10, billete5, billete2, billete1)
    if monto_sacar - 10 >= 0 and los_billetes['Diez']>0:
      los_billetes['Diez']-=1
      return sacar_billetes_aux(los_billetes, monto_sacar-10, billete100, billete50, billete20, billete10+1, billete5, billete2, billete1)
    if monto_sacar - 5 >= 0 and los_billetes['Cinco']>0 :
      los_billetes['Cinco']-=1
      return sacar_billetes_aux(los_billetes, monto_sacar-5, billete100, billete50, billete20, billete10, billete5+1, billete2, billete1)
    if monto_sacar -2 >= 0 and los_billetes['Dos']>0:
      los_billetes['Dos']-=1
      return sacar_billetes_aux(los_billetes, monto_sacar-2, billete100, billete50, billete20, billete10, billete5, billete2+1, billete1)
    if monto_sacar -1 >=0 and los_billetes['Uno']>0 :
      los_billetes['Uno']-=1
      return sacar_billetes_aux(los_billetes, monto_sacar-1, billete100, billete50, billete20, billete10, billete5, billete2, billete1+1)
    #si el monto ya paso por todos los if y aun no llega a 0 se indica que no hay billetes suficientes
    elif monto_sacar > 0:
      return 'El cajero no tiene fondos suficientes'
    #el else retorna los billetes para el usuario
    else :
        print('Billetes a retirar')
        if billete100 > 0 :
            print('Billetes de 100: ', billete100)
        if billete50 > 0 :
            print('Billetes de 50: ', billete50)
        if billete20 > 0 :
            print('Billetes de 20: ', billete20)
        if billete10 > 0 :
            print('Billetes de 10: ', billete10)
        if billete5 > 0 :
            print('Billetes de 5: ', billete5)
        if billete2 > 0 :
            print('Billetes de 2: ', billete2)
        if billete1 > 0 :
            print('Billetes de 1: ', billete1)
            
        #finalmente retorn cuantos billetes quedaron en el cajero
        return los_billetes

