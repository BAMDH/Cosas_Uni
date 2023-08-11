# -*- coding: utf-8 -*-
"""
Created on Wed Apr 13 15:31:11 2022

@author: Usuario

"""

"""
Se trae la funcion que confirma el nombre de usuario es valido, para ver el historial de transacciones, y para archivar las transacciones 

"""
from verificar_usuario import nombre, ver_cuenta, cargar_historial
"""
se trae la funcion para que el usuario pueda retirar y depositar dinero en la cuenta 
"""
from añadir import añadir_dinero_usuario
from retirar import retirar_dinero_usuario
"""
Se trae la funcion para crear usuarios y cajeros
"""
from crear_usuario import crear_nuevo_usuario
from crear_cajero import crear_cajero
"""
Setrae la funcion para el banquero pueda añadir billetes a un cajero existente 
"""
from agregar_billetes_cajero_existente import agregar_dinero_cajero_existente

#Diccionario de la lista de usuarios

#comprobar si existe lista de cajeros, si no existe se crea


lista_usuarios= {}
lista_cajeros= []
"""
Funciones para traer todos los usuarios y cajeros del sistema
"""
def cargar_datos_cajeros():
    global lista_cajeros                 
    try:
         cajero= open("lista_cajeros.txt","r")
         cajero.close
    except:
        crear_Lista_cajeros=open("lista_cajeros.txt","w")
        crear_Lista_cajeros.close()
    try:
        lista_cajeros= cajero.readlines()
        lista_cajeros= lista_cajeros[0]
        lista_cajeros= lista_cajeros.replace(" ","")
        lista_cajeros= lista_cajeros.replace("'", "")
        lista_cajeros= lista_cajeros.split(",")
        return lista_cajeros
    except:
        return lista_cajeros
    
def cargar_datos_usuarios(): 
    global lista_usuarios    
    #Si existe el documento lista_usuarios.txt se abre, si no existe la crea
    try:
     usuario= open("lista_usuarios.txt","r")
     lista= usuario.readlines()
     usuario.close
    except:
     crear_Lista_usuarios=open("lista_usuarios.txt","w")
     crear_Lista_usuarios.close()
     #se crea una lista a partir de la información existente en lista_usuarios.txt
    try:
         lista= lista[0]
         lista= lista.replace(" ","")
         lista= lista.replace("'", "")
         lista= lista.split(",")
         cargar_lista_usuarios(lista, lista_usuarios, 0)
         return lista_usuarios
    except:
       lista= []
       cargar_lista_usuarios(lista, lista_usuarios, 0)       
       return lista_usuarios
   
    
def cargar_lista_usuarios(lista_hileras_usuario, diccionario, elemento_actual):

    if(elemento_actual >= len(lista_hileras_usuario)):
        return diccionario
    else:
        #se recupera la llave/valor de la lista
        llave = lista_hileras_usuario[elemento_actual]
        valor = lista_hileras_usuario[elemento_actual + 1]
        #se crea la nueva entrada en el diccionario
        llave = llave.replace(" ", "")
        valor= valor.replace(" ", "")
        valor= valor.replace("'","")
        diccionario[llave] = valor
        elemento_actual += 2
        return cargar_lista_usuarios(lista_hileras_usuario, diccionario, elemento_actual)
        
#Crea un diccionario con los valores de la lista de usuarios
cargar_datos_cajeros()
cargar_datos_usuarios()

    
#Permite elejir la qué operación desea realizar
def elegir_interfaz():
    global lista_cajeros 
    global lista_usuarios
    #Pregunta el tipo de usuario que esta operando el sistema
    eleccion= input("Inserte 1 si es usuario, 2 si es banquero o 3 para salir: ")
    #si el usuario es un usuario corriente 
    if eleccion == "1":
        if (len(lista_usuarios) == 0):
            print("Aún no existen usuarios")
            volver_menu()
        else:
            print("Por favor inicie sesión")
            #se pregunta por el nombre de usuario y su pin y se ejecuta la funcion para confirmar que el usuario existe 
            confirmar_usuario(input("Inserte usuario: "), input("Pin: "))        
    
    #si el usuario es un banquero
    elif eleccion == "2":
        #se despliegan las opciones que tiene el banquero para hacer
        eleccion2=input("Bienvenido banquero\nPor favor ingrese:\n1 si desea crear un usuario \n2 si desea crear un cajero\n3 si desea añadir billetes a un cajero existente\n")
#si el baqnuero digita el numero 1 se ejcuta para crear un usuario
        if eleccion2 == "1":
            Entrada= input("Ingrese el usuario que desea crear: ")
            #se confirma que el nombre de usuaario sea valido
            confirmar= nombre(Entrada,0,0, 0)
            if confirmar == 'Usuario valido':
                #Se crea el archivo del usuario y se escribe su nombre en la lista de usuarios 
                crear_nuevo_usuario(Entrada)
                #Funcion que pregunta si el banquero desea cerrar el sistema o si desea volver a la  pantalla principal 
                volver_menu()
            else:
                print("Nombre de usuario inválido\nAsegúrese de que el nombre de usuario posee letras seguidas de cuatro números y un caracter")
                volver_menu()
        #Si el banquero digita el 2 sera para crear un cajero 
        elif eleccion2 == "2":
            #El cajero digita el nombre del nuevo cajero y se confirma que no exista
            cajero= input("Ingrese el nombre del cajero que desea crear: ")
            if cajero in lista_cajeros:
                print("Cajero existente")
                volver_menu()
                #Que su nombre cumpla el estandar
            else:
                crear_cajero(cajero)
                volver_menu()
        #Si el banquero digita el numero 3 es para depositar dinero en cajeros existentes 
        elif eleccion2 =="3":
            #se confirma de que existan cajeros
            if (len(lista_cajeros)== 0):
                print("No existen cajeros")
                volver_menu()
            else:
                #se despliegan los cajeros dispoibles
                print(lista_cajeros)    
                cajero= input("Por favor elija un cajero:",)
                #Confirmamos que el cajero digitado por el banquero este entre los cajeros disponibles
                if cajero in lista_cajeros:
                    #Se invoca la funcion para que el banquero deposite los billetes 
                    agregar_dinero_cajero_existente(cajero)
                    volver_menu()
                else:
                    print("No existe ese cajero")
                    volver_menu()
        else:
            print("Digite una opción válida")
            volver_menu()
    
    elif eleccion == "3":
        print("Gracias por su tiempo")
    else:
        print("Seleccione una opción válida")
        volver_menu()
"""
+Funcion para volver menu principal 
+Se invoca despues de cada accion falllida o exitosa
"""        
def volver_menu():
    cargar_datos_cajeros()
    cargar_datos_usuarios()
    eleccion= input("Digite 1 si desea volver al menú, digite cualquier otro valor para salir: ")
    if eleccion =="1":
        elegir_interfaz()
    
    else: 
        print("Gracias por su tiempo")
        
"""
Funcion que confirma que el nombre de usuario y pin introducidos coinciden con un usuario existente
"""        
def confirmar_usuario(usuario,pin):
    
    global lista_usuarios
    global lista_cajeros
   #Se verificaque el nombre de usuario cumple el estandar
    nombre_veri= nombre(usuario, 0, 0, 0)    
    if(nombre_veri== 'Usuario valido'):
       #inicia sesión
        if usuario in lista_usuarios:
         if pin == lista_usuarios[usuario]:
                print("Bienvenido", usuario)
                ver_cuenta(usuario)
                
                #Elige la operación
                hola= input("Elija\n1 si desea hacer un deposito\n2 si desea retirar dinero\n3 si desea consultar su historial\n")
                #opcion 1 es para depositar dinero
                if hola== "1":
                        #se confirma que existan cajeros para realizar las operacion
                        if (len(lista_cajeros)== 0):
                            print("No existen cajeros")
                            volver_menu()
                        else:
                            #se despliegan los cajeros disponibles
                            print("Cajeros disponibles\n",lista_cajeros)    
                            cajero= input("Por favor elija un cajero:",)
                            #se confirma que el cajero digitado por el usuario este dentro de las opciones 
                            if cajero in lista_cajeros:
                                #se ejecuta la funcion para deposita dinero 
                                añadir_dinero_usuario(usuario,cajero)
                                volver_menu()
                            else:
                                print("No existe ese cajero")
                                volver_menu()
                #La opcion 2 es para depositar dinero
                elif hola== "2":
                       #se confirma que existan cajeros para realizar la operacion
                    if (len(lista_cajeros)== 0):
                        print("No existen cajeros")
                        volver_menu()
                    else:
                        #se despliegan los cajeros disponibles
                        print("Cajeros disponibles\n",lista_cajeros)    
                        cajero= input("Por favor elija un cajero:",)
                        #se confirma que el cajero digitado por el usuario este dentro de las opciones
                        if cajero in lista_cajeros:
                            #se ejecuta la funcion para retira dinero 
                            retirar_dinero_usuario(usuario,cajero)
                            volver_menu()
                        else:
                            print("No existe ese cajero")
                            volver_menu()
                #La opcion 3 es para el historial de transacciones del usuario 
                elif hola == "3" :
                    print("Historial")
                    #el try/except es por si el usuario no tiene transacciones 
                    try:
                        cargar_historial(usuario)
                    except: print("Aún no se han realizado transacciones")
                    volver_menu()
                
                else: 
                        print("Inserte una opción válida")
                        volver_menu()
         else:
            print("Pin incorrecto")
            volver_menu()
        else:
            print("Usuario inexistente")
            volver_menu()
    else: 
        print("Nombre de usuario incorrecto")
        volver_menu()

#se invoca la funcion para que el sistema funcione
elegir_interfaz()
