# -*- coding: utf-8 -*-
"""
Created on Sat May 28 15:34:05 2022

@author: Usuario
"""
frecuencia_CPU = 2400
megas_RAM = 1024
class Burbuja:
    def intercambiar(self, pos1, pos2, lista):
        #intercambia los elementos en las posiciones pos1 y pos2
        temporal = lista[pos1]
        lista[pos1] = lista[pos2]
        lista[pos2] = temporal
        return lista

    def flotar_burbuja(self, pivote_burbuja, lista):
        #pone a flotar la burbuja hasta pivote_burbuja
        for pivoteJ in range(0, pivote_burbuja):
       
            if(lista[pivoteJ][2] > lista[pivoteJ + 1][2]):
                lista = self.intercambiar(pivoteJ, pivoteJ + 1, lista)
        return lista

    
    def ordenar(self, lista):
        for pivote_burbuja in range(len(lista) - 1, 0, -1):
                        lista = self.flotar_burbuja(pivote_burbuja, lista)
        return lista


class tienda:
    #Crea la clase tienda   
    def __init__(self, nombre):
        self.nombre= nombre
        self.lista_computadoras= []
        
    def agregar_computadora(self,computadora):
       
        #Se toman los datos de la computadora
        computadora=computadora.obtener_datos()
        
        #Agrega la computadora en forma de lista
        self.lista_computadoras+=[computadora]
        
        
    def mostrar_computadores(self):
        #Imprime todas las computadoras que hay en la lista de computadoras
        contador=0
        
        while (contador!= len(self.lista_computadoras)):
            
            print("CPU:",self.lista_computadoras[contador][0],"Mhz, RAM:",self.lista_computadoras[contador][1],", precio ",self.lista_computadoras[contador][2] )
            contador=contador+1
    def ordenar_computadores_por_precio(self):
        
        self.lista_computadoras=Burbuja().ordenar(self.lista_computadoras)  
              
              
class Computadora:     
    #crea la nueva computadora
    def __init__(self,frecuencia_CPU, megas_RAM ,precio):
        
        self.__precio=precio
        self.__megas_RAM= megas_RAM
        self.__frecuencia_CPU = frecuencia_CPU
        
    # sirve para conseguir los datos de la computadora creada    
    def obtener_datos(self):
        #guarda los datos en el formato de lista [frecuencia_cpu, megas_RAM, precio]
        return [self.__frecuencia_CPU,self.__megas_RAM,self.__precio]
   
        

tienda= tienda("Tienda")
tienda . agregar_computadora ( Computadora ( frecuencia_CPU , megas_RAM, 1250))
tienda . agregar_computadora ( Computadora ( frecuencia_CPU - 400 , megas_RAM, 800)) 
tienda . agregar_computadora ( Computadora ( frecuencia_CPU + 700 , megas_RAM, 1500))
tienda . agregar_computadora ( Computadora ( frecuencia_CPU - 1000 , megas_RAM, 450))
tienda . agregar_computadora ( Computadora ( frecuencia_CPU - 1500 , megas_RAM, 350))
tienda . agregar_computadora ( Computadora ( frecuencia_CPU + 2500 , megas_RAM, 1900))
tienda . agregar_computadora ( Computadora ( frecuencia_CPU + 2500 , megas_RAM, 1900))

tienda . ordenar_computadores_por_precio ( ) 
tienda.mostrar_computadores()