# -*- coding: utf-8 -*-
"""
Created on Fri Apr 15 11:52:55 2022

@author: Usuario
"""
from verificar_usuario import cargar_info

#Tiene la misma funcionalidad que la funcion que se encuentra en añadir.py, pero si la importo aquí se crea un bucle al querer usar esta librería en el menu 
def agregar_billetes_a_cajero(cien,cincuenta,veinte,diez,cinco,dos,uno):
  #se deifine las variables que almacenaran el numero de billetes que se van a añadir 
  billetes100 = 0 
  billetes50 = 0 
  billetes20 = 0
  billetes10 = 0
  billetes5 = 0 
  billetes2 = 0 
  billetes1 = 0
  #Este if confirma que las entradas sean valores positivos
  if(cien>= 0) and (cincuenta>= 0) and (veinte>= 0) and (diez>= 0) and (cinco>= 0) and (dos>= 0) and (uno>= 0):
    return agregar_billete_aux(cien,cincuenta,veinte,diez,cinco,dos,uno,billetes100,billetes50,billetes20,billetes10,billetes5,billetes2,billetes1)
  else: 
      return False
"""
+Definimos la funcion auxiliar la cual va añadir los billetes a las variables de los archivos
+Las variables de que tienen nombre los numeros de las denominacion de los billetes son las entradas
"""
def agregar_billete_aux(cien,cincuenta,veinte,diez,cinco,dos,uno,billetes100,billetes50,billetes20,billetes10,billetes5,billetes2,billetes1):
    #Entonces si en la entrada en los numeros de denominacion(cien, cincuenta, Veinte, etc...) lee un numero que no sea 0 se ejecuta el if de esa denominacion para añadir los billetes a las variables del archivo  
    if cien !=0:
      billetes100 = billetes100+cien
      cien= cien-cien
      return agregar_billete_aux(cien,cincuenta,veinte,diez,cinco,dos,uno,billetes100,billetes50,billetes20,billetes10,billetes5,billetes2,billetes1)
    
    if cincuenta != 0:
      billetes50 = billetes50+cincuenta
      cincuenta= cincuenta-cincuenta
      return agregar_billete_aux(cien,cincuenta,veinte,diez,cinco,dos,uno,billetes100,billetes50,billetes20,billetes10,billetes5,billetes2,billetes1)

    if veinte !=0:
      billetes20 = billetes20+veinte
      veinte= veinte-veinte
      return agregar_billete_aux(cien,cincuenta,veinte,diez,cinco,dos,uno,billetes100,billetes50,billetes20,billetes10,billetes5,billetes2,billetes1)

    if diez !=0:
      billetes10 = billetes10+diez
      diez=diez-diez
      return agregar_billete_aux(cien,cincuenta,veinte,diez,cinco,dos,uno,billetes100,billetes50,billetes20,billetes10,billetes5,billetes2,billetes1)
   
    if cinco !=0:
      billetes5 = billetes5+cinco
      cinco=cinco-cinco
      return agregar_billete_aux(cien,cincuenta,veinte,diez,cinco,dos,uno,billetes100,billetes50,billetes20,billetes10,billetes5,billetes2,billetes1)

    if dos !=0:
      billetes2 = billetes2+dos
      dos= dos-dos
      return agregar_billete_aux(cien,cincuenta,veinte,diez,cinco,dos,uno,billetes100,billetes50,billetes20,billetes10,billetes5,billetes2,billetes1)
    else:
      billetes1= billetes1+uno
      monto= (billetes100*100)+(billetes50*50)+(billetes20*20)+(billetes10*10)+(billetes5*5)+(billetes2*2)+billetes1
      #El la funcion retorna el resultado el cual sera un dicccionario con las denominacio de los billetes y los billetes que se añadira
      resultado={"Cien":billetes100,"Cincuenta":billetes50,"Veinte":billetes20,"Diez":billetes10,"Cinco":billetes5,"Dos":billetes2,"Uno":billetes1, "Monto":monto }

      return resultado










"""
Esta funcion rescribe los datos del archivo del cajero 
"""
def reescribir_cajero(diccionario1, archivo):
    #convertir el nuevo usuario al formato que es aceptado por el txt
    archivo= archivo+".txt"
    diccionario1= str(diccionario1)
    diccionario_usable=diccionario1.replace("{","")
    diccionario_usable=diccionario_usable.replace("}","")
    diccionario_usable=diccionario_usable.replace(":",",")
    diccionario_usable=diccionario_usable.replace(" ","")
    usuario=open(archivo, "w")
    usuario.writelines(diccionario_usable)
    usuario.close
    
    print("Los datos han sido actualizados")

"""
Esta funcion carga los datos a los archivos del cajero hacia una variable dentro del codigo 
"""
def cargar_datos_cajero(nombre_cajero):
    #se abre el archivo del cajero y se leen los datos de los billetes 
    entrada= nombre_cajero+".txt"
    cajero_abrir= open(entrada, "r")
    linea=cajero_abrir.readlines()
    linea= linea[0]
    linea= linea.replace("'","")
    cajero_abrir.close()
    linea= linea.split(",")
    diccionario_cajero= {}
    #definimos la funcion para agregar los billetes al archivo del cajero
    cargar_info(linea, diccionario_cajero,0)
    return diccionario_cajero
    
"""
Esta funcion reune y ejecuta todas las funcionalidades para archivar los datos al cajero 
"""   

def agregar_dinero_cajero_existente(nombre_cajero):
    #se ejecuta la funcion la cual trae los datos del archivo del cajero 
    diccionario_cajero= cargar_datos_cajero(nombre_cajero)
    #se usa un try para el caso en el que se introduzcan valores que no son numeros enteros 
    try:
        print("Inserte los billetes por nominación que desea depositar")
        #Se define la variable que almacena las la funcion con las entradas o los billetes que se van a depositar 
        billetes= agregar_billetes_a_cajero(int(input("Billetes de 100: ",)),int(input("Billetes de 50: ",)),int(input("Billetes de 20: ",)),int(input("Billetes de 10: ",)),int(input("Billetes de 5: ",)),int(input("Billetes de 2: ",)),int(input("Billetes de 1: ",)))
        #se confirma que el monto no sea negativo 
        if billetes["Monto"] > 0:
            #definimos la variable para sumar los billetes a los billetes que estan el cajero 
            nuevo_diccionario= {"Cien": int(diccionario_cajero["Cien"])+billetes["Cien"],"Cincuenta":int(diccionario_cajero["Cincuenta"])+billetes["Cincuenta"],"Veinte":int(diccionario_cajero["Veinte"])+billetes["Veinte"],"Diez":int(diccionario_cajero["Diez"])+billetes["Diez"],"Cinco":int(diccionario_cajero["Cinco"])+billetes["Cinco"],"Dos":int(diccionario_cajero["Dos"])+billetes["Dos"],"Uno":int(diccionario_cajero["Uno"])+billetes["Uno"],"Monto":int(diccionario_cajero["Monto"])+billetes["Monto"]}
            #se define la funcion para cambiar los datos del cajero 
            reescribir_cajero(nuevo_diccionario, nombre_cajero)
            
        else: print("No se ha podido realizar la transacción, el monto debe ser mayor a 0")
    
    except: print("Inserte números enteros")
   





"""
La funcion esta para 
"""
def agregar_dinero_cajero_depositado_por_usuario(nombre_cajero,billetes):
    diccionario_cajero= cargar_datos_cajero(nombre_cajero)
    try:
        
        if billetes["Monto"] > 0:
    
            nuevo_diccionario= {"Cien": int(diccionario_cajero["Cien"])+billetes["Cien"],"Cincuenta":int(diccionario_cajero["Cincuenta"])+billetes["Cincuenta"],"Veinte":int(diccionario_cajero["Veinte"])+billetes["Veinte"],"Diez":int(diccionario_cajero["Diez"])+billetes["Diez"],"Cinco":int(diccionario_cajero["Cinco"])+billetes["Cinco"],"Dos":int(diccionario_cajero["Dos"])+billetes["Dos"],"Uno":int(diccionario_cajero["Uno"])+billetes["Uno"],"Monto":int(diccionario_cajero["Monto"])+billetes["Monto"]}
            reescribir_cajero(nuevo_diccionario, nombre_cajero)
            
        else: print("No se ha podido realizar la transacción, el monto debe ser mayor a 0")
    
    except: print("Inserte números enteros")
