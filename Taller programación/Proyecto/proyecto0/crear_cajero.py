# -*- coding: utf-8 -*-
"""
Created on Thu Apr 14 19:46:54 2022

@author: Usuario


"""

"""
CREACION DE CAJEROS FUNCIONAL CON CONFIRMACION DE NOMBRE DE CAJERO
"""
from añadir import agregar_billetes

"""
Esta funcion verifica que el nombre introducido es valido utilizando recursividad de cola, codificacion unicode y listas
Las demas tienen com finalidad almacenar cuantas letras, numeros tiene el nombre de usuario 
Aplica los mismo que lo de crear usuarios
"""
def cajero(nombre_cajero, letra, numero):
  lista_cajero = list(nombre_cajero)

  if lista_cajero != [] :
    unIcode = ord(lista_cajero[0]) 
    if  65 <= unIcode <= 90:
      letra += 1
      del(lista_cajero[0])
      return cajero(lista_cajero, letra, numero)
    if 48 <= unIcode <= 57 and letra == 3 :
      del(lista_cajero[0])
      numero += 1  
      return cajero(lista_cajero, letra, numero)
    else :
      return ('Nombre de cajero invalido')  
  elif  letra == 3 and numero > 0 :
    return True 
  else :
    return False
"""
Funcion para agregar el nombre del cajero a la lista de cajeros
"""
def agregar_a_lista_de_cajeros(nombre_cajero):
   
    cajeros_lista= open("lista_cajeros.txt", "r")
    lista= cajeros_lista.readlines()
    
    cajeros_lista.close()  
    #si la lista esta vacia 
    if len(lista)== 0:
       lista= nombre_cajero
       cajeros_lista= open("lista_cajeros.txt", "w")
       cajeros_lista.write(str(lista))
       cajeros_lista.close()
       print("Cajero creado exitosamente")
    else:
        lista1= lista[0]
        lista= lista1+","+nombre_cajero
        cajeros_lista= open("lista_cajeros.txt", "w")
        cajeros_lista.write(str(lista))
        cajeros_lista.close()
        print("Cajero creado exitosamente")
"""
Funcion para crear un cajero 
"""        
        
def crear_cajero(nombre_cajero):
    #Se confirma que el nombre del cajero cumpla el estandar
    if cajero(nombre_cajero,0,0) == True:
        #Se confirma que el nombre de cajero no exista 
        #El try/Except es en el caso de que la lista de cajero este vacia 
        try:
              lista_usuarios = open("lista_cajeros.txt","r")
              lista= lista_usuarios.readlines()
              lista_usuarios.close()
              lista= str(lista)
              lista= lista.replace("[","")
              lista= lista.replace("]","")
              lista= lista.replace(" ","")
              lista= lista.replace("'","")
              lista= lista.split(",")
            
              if nombre_cajero in lista:
                  print("Cajero existente")
                  
             #Se crea el archivo del cajero que almacene los billetes por denominacion    
              else:
                  try:
                     print('Ingrese el monto que desea depositar en el cajero')
                     agregar_dinero= agregar_billetes(int(input("Billetes de 100: ",)),int(input("Billetes de 50: ",)),int(input("Billetes de 20: ",)),int(input("Billetes de 10: ",)),int(input("Billetes de 5: ",)),int(input("Billetes de 2: ",)),int(input("Billetes de 1: ",)))
                     #se confirma que los valores introducidos sean numeros enteros positivos    
                     if agregar_dinero == "No puede insertar negativos":
                         print(agregar_dinero)
                     else:
                         #se guardan los datos dentro del archivo |
                        cajeros = agregar_dinero
                        cajeros_usable = str(cajeros)
                        cajero_usable=cajeros_usable.replace("{","")
                        cajero_usable=cajero_usable.replace("}","")
                        cajero_usable=cajero_usable.replace(":",",")
                        cajero_usable=cajero_usable.replace(" ","")
                        nombre_arch = nombre_cajero+".txt"
                        archivo = open(nombre_arch,"w")
                        archivo.write(cajero_usable)
                        archivo.close()
                       
                        agregar_a_lista_de_cajeros(nombre_cajero)
                    
                  except:
                      print("Inserte una cantidad valida")
                      
        #Aqui hace lo mismo que la de arriba solo que in la confirmacion de que el cajero exista
        except:
              try:
                 print('Ingrese el monto que desea depositar en el cajero')
                 agregar_dinero= agregar_billetes(int(input("Billetes de 100: ",)),int(input("Billetes de 50: ",)),int(input("Billetes de 20: ",)),int(input("Billetes de 10: ",)),int(input("Billetes de 5: ",)),int(input("Billetes de 2: ",)),int(input("Billetes de 1: ",)))
                 if agregar_dinero == "No puede insertar negativos":
                     print(agregar_dinero)
                 else:      
                 
                     cajeros = agregar_dinero
                     cajeros_usable = str(cajeros)
                     
                     cajero_usable=cajeros_usable.replace("{","")
                     cajero_usable=cajero_usable.replace("}","")
                     cajero_usable=cajero_usable.replace(":",",")
                     cajero_usable=cajero_usable.replace(" ","")
                     nombre_arch = nombre_cajero+".txt"
                     archivo = open(nombre_arch,"w")
                     archivo.write(cajero_usable)
                     archivo.close()

                     agregar_a_lista_de_cajeros(nombre_cajero)
              except:
                 print("Inserte una cantidad valida")
            
    else :
      print(cajero(nombre_cajero,0,0))
