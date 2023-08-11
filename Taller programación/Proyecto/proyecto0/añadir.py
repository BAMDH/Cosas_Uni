# -*- coding: utf-8 -*-
"""
Created on Wed Apr 13 20:05:47 2022

@author: Usuario
"""


from verificar_usuario import cargar_info
from agregar_billetes_cajero_existente import agregar_dinero_cajero_depositado_por_usuario
from crear_usuario import en_caso_de_signo_pregunta

#Funcion que toma los billetes que se desean depositar como entradas
def agregar_billetes(cien,cincuenta,veinte,diez,cinco,dos,uno):
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


def reescribir_usuario(diccionario1, archivo,transaccion):
    #convertir el nuevo usuario al formato que es aceptado por el txt
    diccionario1= str(diccionario1)
    diccionario_usable=diccionario1.replace("{","")
    diccionario_usable=diccionario_usable.replace("}","")
    diccionario_usable=diccionario_usable.replace(":",",")
    diccionario_usable=diccionario_usable.replace(" ","")
    diccionario_usable= diccionario_usable+"\n"+transaccion
    usuario=open(archivo, "w")
    usuario.writelines(diccionario_usable)
    usuario.close
    print("Operación exitosa")

#definí billetes= función para facilitar el uso del diccionario que retorna

def añadir_dinero_usuario(Entrada,nombre_cajero):
   #leer los datos del archivo y crear un diccionario
   #try/except en el caso de que no existan transacciones hechas  ya que genera error en line 97 
   #Porque todas las transacciones se guardan en una segunda linea entonces al invocar esa linea para archivar una transaccion da un error porque no existe
    try:
        Entrada_fiable= en_caso_de_signo_pregunta(Entrada)
        archivo= Entrada_fiable+".txt"
        info_usuario= open(archivo, "r")
        info_us= info_usuario.readlines()
        info= info_us[0]
        info2= info_us[1]
        info= info.replace(" ","")
        info= info.replace("'", "")
        info= info.split(",")
        info_usuario.close()
        diccionario= {}
        cargar_info(info, diccionario, 0)
        #Añadir el nuevo monto
        print("ingrese el número de billetes por nominación que desea depositar")
        #try/except para confirmar que los valores introducidos son numeros enteros positivos 
        try:
            monto= agregar_billetes(int(input("Billetes de 100: ",)),int(input("Billetes de 50: ",)),int(input("Billetes de 20: ",)),int(input("Billetes de 10: ",)),int(input("Billetes de 5: ",)),int(input("Billetes de 2: ",)),int(input("Billetes de 1: ",)))
            #si el monto a retirar es 0, no se realiza la operación
            if int(monto["Monto"]) <= 0:
                print("No se ha podido realizar la operación, la cantidad debe ser mayor a 0")
            else:
                nuevo_monto= int(diccionario["Monto"])+int(monto["Monto"])
                
               
                nuevo_diccionario= {"Usuario":diccionario["Usuario"],"Pin":diccionario["Pin"],"Monto":nuevo_monto}
                
                #fecha de la transacción
                from datetime import datetime
                import pytz
                Costa_Rica= pytz.timezone('America/Costa_Rica')
                now= datetime.now(Costa_Rica)
                fecha= now.strftime("%d/%m/%Y %H:%M:%S")
                transaccion= "+"+str(monto["Monto"])
                transaccion= transaccion+ " " + fecha+ " " +nombre_cajero+ ","
                
                #añadir fecha de transacción al historial, si aún no existe, se crea
                
                transaccion= transaccion+info2
                
                
                print("Monto depositado: ",monto["Monto"],"Monto total: ",nuevo_monto)
                
                #se llama la función para añadir los cambios al archivo del usuario
                reescribir_usuario(nuevo_diccionario,archivo,transaccion)
                print("A continuación se cactualizarán los datos del cajero selecionado")
                agregar_dinero_cajero_depositado_por_usuario(nombre_cajero, monto)
                
        except:
               print("Inserte números enteros")
   #misma funcionalidad que lo de arriba solo que escribe directamerte en la segunda linea del archivo            
    except:
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
          #Añadir el nuevo monto
          print("Ingrese el número de billetes por nominación que desea depositar")
          try:
              monto= agregar_billetes(int(input("Billetes de 100: ",)),int(input("Billetes de 50: ",)),int(input("Billetes de 20: ",)),int(input("Billetes de 10: ",)),int(input("Billetes de 5: ",)),int(input("Billetes de 2: ",)),int(input("Billetes de 1: ",)))
              #si el monto a retirar es 0, no se realiza la operación
              if int(monto["Monto"]) <= 0:
                  print("No se ha podido realizar la operación, la cantidad debe ser mayor a 0")
              else:
                  
                  nuevo_monto= int(diccionario["Monto"])+int(monto["Monto"])
                  
                 
                  nuevo_diccionario= {"Usuario":diccionario["Usuario"],"Pin":diccionario["Pin"],"Monto":nuevo_monto}
                  
                  #fecha de la transacción
                  from datetime import datetime
                  import pytz
                  Costa_Rica= pytz.timezone('America/Costa_Rica')
                  now= datetime.now(Costa_Rica)
                  fecha= now.strftime("%d/%m/%Y %H:%M:%S")
                  transaccion= "+"+str(monto["Monto"])
                  transaccion= transaccion+ " " + fecha + " " + nombre_cajero
                  
                  #añadir fecha de transacción al historial, si aún no existe, se crea
                  
                  transaccion= transaccion
                  
                  
                  print("Monto depositado: ",monto["Monto"],"Monto total: ",nuevo_monto)
                  
                  #se llama la función para añadir los cambios al archivo del usuario
                  reescribir_usuario(nuevo_diccionario,archivo,transaccion)
                  print("A continuación se actualizarán los datos del cajero selecionado")
                  agregar_dinero_cajero_depositado_por_usuario(nombre_cajero, monto)
          except:
              print("Inserte números enteros")


    
