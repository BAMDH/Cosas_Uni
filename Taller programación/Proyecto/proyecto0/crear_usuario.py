# -*- coding: utf-8 -*-
"""
+Se define una funcion para almacenar los archivos txt del usuario que terminen con un signo de pregunta
+Los archivos no se pueden guardar con un signo de pregunta al final 
+Solo cambia el nombre del archivo, el nombre del usuario se mantiene 
"""
def en_caso_de_signo_pregunta(Entrada):
    if "?" in Entrada:
        Entrada= Entrada.replace("?","")
        return en_caso_de_signo_pregunta(Entrada)
    else:
        return Entrada

"""
Esta funcion añade el nombre de usuario a la lista de usuarios
"""
def añadir_a_lista_usuarios(lista_hileras_usuario, diccionario, elemento_actual):

    if(elemento_actual >= len(lista_hileras_usuario)):
        diccionario_usable= str(diccionario)
        diccionario_usable=diccionario_usable.replace("{","")
        diccionario_usable=diccionario_usable.replace("}","")
        diccionario_usable=diccionario_usable.replace(":",",")
        diccionario_usable=diccionario_usable.replace(" ","")
        diccionario_usable=diccionario_usable.replace("\"","")
        archivo= open("lista_usuarios.txt", "w")
        archivo.write(diccionario_usable)
        archivo.close()
   
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
        
        return añadir_a_lista_usuarios(lista_hileras_usuario, diccionario, elemento_actual)
    
    
"""
Funcion principal
"""
    
def crear_nuevo_usuario(Entrada):
    #se escrbe un try en el caso de que sea el primer usuario en ser creado  
    try:
        lista_usuarios = open("lista_usuarios.txt","r")
        lista= lista_usuarios.readlines()
        
        lista= lista[0]
        lista= lista.replace(" ","")
        lista= lista.replace("'", "")
    
        lista_usuarios.close
        
        #Se verifica si existe el usuario
        if Entrada in lista:
          print("Usuario existente, no es posible crear un nuevo usuario")
        #Se crea el nuevo usuario
        else:
            
          import random
          Entrada_fiable= en_caso_de_signo_pregunta(Entrada)
          #se geenera el pin aleatorio
          pin= random.randint(1000,9999)
          #se generan los datos que tendra el archivo del usuario 
          diccionario= {"Usuario":Entrada,"Pin":pin,"Monto":0}
          diccionario_usable= str(diccionario)
          diccionario_usable=diccionario_usable.replace("{","")
          diccionario_usable=diccionario_usable.replace("}","")
          diccionario_usable=diccionario_usable.replace(":",",")
          diccionario_usable=diccionario_usable.replace(" ","")
          nombre_archivo = Entrada_fiable+".txt"
          #Se abre el archivo del usuario y se escriben los datos 
          archivo = open(nombre_archivo, "w")
          archivo.write(diccionario_usable)
          archivo.close()
          print("Se ha creado exitosamente el nuevo usuario",Entrada)
          print("Pin:",pin)
          
          #Se añade a lista de usuarios de manera que el nombre de usuario sea la llave y el pin la clave
          pin_usuario= {Entrada:pin}
          lista1= lista.split(",")
          añadir_a_lista_usuarios(lista1,pin_usuario,0)       
          
          
          #Se ejecuta el except en caso de que el archivo de la lista de usuarios este vacia
    except:
          import random
          Entrada_fiable= en_caso_de_signo_pregunta(Entrada)
          pin= random.randint(1000,9999)
          diccionario= {"Usuario":Entrada,"Pin":pin,"Monto":0}
          diccionario_usable= str(diccionario)+"\n"
          diccionario_usable=diccionario_usable.replace("{","")
          diccionario_usable=diccionario_usable.replace("}","")
          diccionario_usable=diccionario_usable.replace(":",",")

          nombre_archivo = Entrada_fiable+".txt"
          archivo = open(nombre_archivo, "w")
          archivo.write(diccionario_usable)
          archivo.close()
          print("Se ha creado exitosamente el nuevo usuario")
          print("Pin:",pin)
          listax= []
          pin_usuario= {Entrada:pin}
          añadir_a_lista_usuarios(listax,pin_usuario,0)
      



