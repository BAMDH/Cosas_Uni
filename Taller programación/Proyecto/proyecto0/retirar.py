# -*- coding: utf-8 -*-
"""
Created on Thu Apr 14 15:04:31 2022

@author: Usuario
"""
#Funciones para archivar los datos del usuario y para leer los datos del usuario aun teniendo signo de pregunta 
from añadir import reescribir_usuario
from crear_usuario import en_caso_de_signo_pregunta
from verificar_usuario import cargar_info
#funcion para retirar los billetes 
from sacar_billetes import sacar_billetes_cajero

"""
Esta funcion principal la cual une las funcionalidades 
"""
def retirar_dinero_usuario(Entrada,nombre_cajero):
    
    #carga los datos en forma de diccionario
    Entrada_fiable= en_caso_de_signo_pregunta(Entrada)
    archivo= Entrada_fiable+".txt"
    info_usuario= open(archivo, "r")
    info_us= info_usuario.readlines()
    info= info_us[0]

    info= info.replace(" ","")
    info= info.replace("'", "")
    info= info.split(",")
    info_usuario.close()
    diccionario= {}
    cargar_info(info, diccionario, 0)
    #Confirmamos que el usuario tiene dinero en la cuenta 
    if int(diccionario["Monto"]) == 0:
        print("No posee saldo para retirar")
    else :
        try:
          #Si el monto a retirar es cero o menor, no se realiza la operación
            monto= int(input("inserte la cantidad a retirar: "))
            if monto >0:
                
                #Se ejecuta el if en caso de que el monto del cajero sea mayor al monto que el usuario desea retirar
                    if monto <= int(diccionario["Monto"]):
                        #se ejecuta la funcion para que el cajero pueda dar los billetes y la menor cantidad de ellos 
                        sacar= sacar_billetes_cajero(nombre_cajero, monto)
                        if (sacar == "El cajero no tiene suficientes fondos") or (sacar == "Cajero sin fondos"):
                            print("No se ha podido realizar la operación\n",sacar)
                        else:
                            nuevo_monto= int(diccionario["Monto"]) - monto
                            nuevo_diccionario= {"Usuario":diccionario["Usuario"],"Pin":diccionario["Pin"],"Monto":nuevo_monto}
                            #Se genera la fecha de la transacción y la hora
                            
                            info2= info_us[1]
                            from datetime import datetime
                            import pytz
                            Costa_Rica= pytz.timezone('America/Costa_Rica')
                            now= datetime.now(Costa_Rica)
                            fecha= now.strftime("%d/%m/%Y %H:%M:%S")
                            transaccion= "-"+str(monto)
                            transaccion= transaccion+ " " + fecha+ " " + nombre_cajero +","
                            #se genera la variable con la info de la transaccion
                            transaccion= transaccion+info2
                            
                            print("Saldo actual: ",nuevo_monto)
                            #Se registra la transaccion el archivo del usuario 
                            reescribir_usuario(nuevo_diccionario,archivo,transaccion)
                            
                            
                    else: print("No se puede realizar el retiro, no se posee la cantidad solicitada")
            else: print("La cantidad insertada debe ser positiva y mayor a 0")
        except:
            print("Inserte números enteros")
    
    
        
    
