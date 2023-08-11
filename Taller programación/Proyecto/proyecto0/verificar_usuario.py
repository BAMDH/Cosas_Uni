# -*- coding: utf-8 -*-
"""
Created on Wed Apr 13 16:11:43 2022

@author: Usuario
"""
#Verifica si el nombre de usuario es válido
from crear_usuario import en_caso_de_signo_pregunta
"""
Esta funcion verifica que el nombre introducido es valido utilizando recursividad de cola, codificacion unicode y listas
variable usuario es el nombre de usuario que se desea crear 
Las demas tienen com finalidad almacenar cuantas letras, numeros, carcteres tiene el nombre de usuario 
"""

def nombre(usuario,letra,numero,caracter):
#Se convierte el nombre de usuario en una lista 
  lista_nombre = list(usuario)
  #condicion de parada 
  if lista_nombre != [] :
    #se convierte el primer valor de la lista a codificacion unicode 
    unIcode = ord(lista_nombre[0])
    #Se confirma que los primeros valores sean letras mayusculas o minusculas 
    if  65 <= unIcode <= 90 and numero == 0 or 97 <= unIcode <= 122 and numero == 0:
      #Se eliminia el valor leido
        del(lista_nombre[0])
        #Se retorna la funcion para hacer un llamado recursivo sumando 1 a la variable de letra indicando que se leyo una letra 
        return nombre(lista_nombre,letra+1,numero,caracter)
    #Este if es en caso de que lea un numero por lo que debe haber leido una letra primero y no debe leer mas de 4 
    if 48 <= unIcode <= 57 and letra != 0 and numero < 4  :
      del(lista_nombre[0])
      #Se retorna la funcion para hacer un llamado recursivo sumando 1 a la variable de numero indicando que se leyo un numero
      return nombre(lista_nombre,letra,numero+1,caracter)
  #este if es para el caso en el que lea un caracter especifico en estee caso son los caracteres:!,$,#,&,?
  #Deben de estar al final 
    if unIcode == 35 and numero == 4 or unIcode ==  63 and numero == 4 or unIcode == 36 and numero == 4 or unIcode == 38 and numero == 4 or unIcode == 33 and numero == 4:
      del(lista_nombre[0])
      #Se retorna la funcion para hacer un llamado recursivo sumando 1 a la variable de caracter indicando que se leyo un caracter 
      return nombre(lista_nombre,letra,numero,caracter+1)
    else :
      return ('Usuario invalido')  
  #Finalmete se retorna que el nombre de usuario es valido solo en caso de que haya leido 4 numeros y un caracter especifico
  #No se necesita confirmar la letra ya que de otra forma el sistema no habria leido el numero 
  elif numero == 4 and   caracter == 1:
    return 'Usuario valido' 
  else :
    return 'Usuario invalido'

"""
Funcion que carga la infirmacion en lo
"""
def cargar_info(lista_hileras, diccionario, elemento_actual):

    if(elemento_actual >= len(lista_hileras)):
        return diccionario
    else:
        #se recupera la llave/valor de la lista
        llave = lista_hileras[elemento_actual]
        valor = lista_hileras[elemento_actual + 1]
        #se crea la nueva entrada en el diccionario
        llave = llave.replace(" ", "")
        valor= valor.replace(" ", "")
        valor= valor.replace("'","")
        diccionario[llave] = valor
        elemento_actual += 2
        
        
        return cargar_info(lista_hileras, diccionario, elemento_actual)




#Muestra los datos de la cuenta
def ver_cuenta(Entrada):
    Entrada= en_caso_de_signo_pregunta(Entrada)
    archivo= Entrada+".txt"
    info_usuario= open(archivo, "r")
    info= info_usuario.readlines()
    info= info[0]
    info= info.replace(" ","")
    info= info.replace("'", "")
    info= info.split(",")
    info_usuario.close()
    diccionario= {}
    elemento_actual= 0
    cargar_info(info, diccionario, elemento_actual)
    print("Monto: ",diccionario["Monto"])

"""
Funcion para ver el historial de transacciones 
"""
def cargar_historial(usuario):
    Entrada= en_caso_de_signo_pregunta(usuario)
    entrada= Entrada+".txt"
    archivo= open(entrada, "r")
    historial= archivo.readlines()
    archivo.close()
    historial= historial[1]
    historial= historial.replace(",", "\n")
    historial= historial.replace("'", "\n")
    print(historial)