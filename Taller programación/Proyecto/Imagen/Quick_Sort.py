# -*- coding: utf-8 -*-
"""
Created on Sun May 15 15:27:13 2022

@author: Administrador
"""
from PIL import Image, ImageOps
import numpy as np
import sys
import time
sys.setrecursionlimit(1000000000)

def calcular_quick_sort():
    """
    Se define una función que le pregunte al usuario la opción a elegir
    """
    print("Por favor siga las instrucciones")
    print("Ingrese 1 se desea utilizar una imagen \nIngrese un 2 si desea usar una matriz \nIngrese 3 si desea volver al menú principal")
    entrada = input("Ingrese su respuesta aquí    ")
    if(entrada == "1"):
        return guardar_imagen()
    elif(entrada == "2"):
        from Quick_Sort_Matriz import filtrar_matriz
        return filtrar_matriz()
    else:
        if(entrada == "3"):
            from Menu import menu
            return menu()
        else:
            print("Por favor ingrese una opción válida")
            return calcular_quick_sort()
    
def filtrar_matriz():
    matriz = input("Por favor ingrese la matriz")
    ventana = input("Por favor ingrese el ancho de la ventana a utilizar")
    if(isinstance(ventana, int) and isinstance(matriz, list)):
        return filtrar_matriz(matriz, ventana)
    else:
        print("La ventana debe ser un número entero")
        return filtrar_imagen()
    
def filtrar_imagen(nombre):
    """
    Se crea una función que se encargue de procesar la imagen en escala de grises
    en Gray
    """
    imagen = nombre
    print("Test 1 running")
    #Se abre la imagen
    imagen = Image.open(imagen)
    #Se abre la imagen en escala de grises
    gray_image = ImageOps.grayscale(imagen)
    #Se muestra la imagen original
    gray_image.show()
    gray_image_np = np.asarray(gray_image)
    gray_image_lists_py = gray_image_np.tolist()
    #Se muestra el número de filas
    num_filas = len(gray_image_lists_py)
    print("numero de filas ", num_filas)
    #Se muestra el número de columnas
    num_columnas = len(gray_image_lists_py[0])
    print("numero de columnas ", num_columnas)
    return gray_image_lists_py

class OrdenadorQuickSort2: 
    def ordenarQuickSort(self, lista):
        listaMenores = [];
        listaMayores = [];
        if(lista == []):
            return [];
        else:
            elementoPivote = lista[0]
            for elemento in lista[1:]:
                if(elemento > elementoPivote):
                    listaMayores += [elemento]
                else:
                    listaMenores += [elemento]
            return self.ordenarQuickSort(listaMenores) + [elementoPivote] + self.ordenarQuickSort(listaMayores)
    def probarQS1(self,lista):
            listaOrdenada = self.ordenarQuickSort(lista)
            return listaOrdenada
           
def valor_media(lista_ordenada, posicion_media):
  if(lista_ordenada == []):
      return posicion_media
  posicion_media = len(lista_ordenada) //2
  if(len(lista_ordenada) % 2 != 0):
    return lista_ordenada[posicion_media-1]
  else:
      if(len(lista_ordenada) == 1):
         return lista_ordenada[posicion_media] 
      else:
        return lista_ordenada[posicion_media]

def calculo_media_QS(lista):
      ordenador = OrdenadorQuickSort2();
      lista_ordenada =  ordenador.probarQS1(lista);
      posicion_media = len(lista_ordenada) //2
      return valor_media(lista_ordenada, posicion_media)

class Ordenador_Burbuja_Recursivo:
    def intercambiar(self, lista, posicion_1, posicion_2):
        """
        Se crea una variable temporal que contenga los elementos de la primera
        y segunda posición
        """
        var_temporal = lista[posicion_1]
        lista[posicion_1] = lista[posicion_2]
        lista[posicion_2] = var_temporal
        
    def flotar_burbuja(self, lista, pivote_burbuja, posicion_actual):
        """
        Se crea una función que vaya inspeccionando la variable que contiene el
        valor actual, respecto al pivote_burbuja
        """
        if(posicion_actual < pivote_burbuja):
            if(lista[posicion_actual] > lista[posicion_actual + 1]):
                self.intercambiar(lista, posicion_actual, posicion_actual + 1)
            return self.flotar_burbuja(lista, pivote_burbuja, posicion_actual + 1)    

    def ordenar(self, lista):
        """
        Se crea una copia de la lista ordenada
        """
        lista_copia = lista.copy()
        if(isinstance(lista, list)):
            pivote_burbuja = len(lista_copia) - 1 
            return self.ordenar_burbuja_aux(lista_copia, pivote_burbuja)
        else:
            raise ValueError("Tipo de datos incorrecto")

    def ordenar_burbuja_aux(self, lista, pivote_burbuja):
        """
        Se crea una función que se encargue de reducir el valor del pivote
        """
        if(pivote_burbuja > 0):
            posicion_actual = 0
            self.flotar_burbuja(lista, pivote_burbuja, posicion_actual)
            pivote_burbuja -= 1
            return self.ordenar_burbuja_aux(lista, pivote_burbuja)
        else:
            return lista

def calculo_media_ordenamiento(lista):
    """
    Se define una función que calcule la media de la ventana
    Se toma el valor que fue ordenado en Ordenador_Burbuja_Recursivo()
    para calcularle la media a la ventana
    """
    ordenador_burbuja = Ordenador_Burbuja_Recursivo()
    lista_ordenada = ordenador_burbuja.ordenar(lista)
    posicion_media = len(lista_ordenada) //2
    return valor_media(lista_ordenada, posicion_media)


def conseguir_posicion_ordenamiento(matriz, posicion, ventana, fila, lista1):
    """
    Consigue todos los elementos de la primera fila según su posición
    """
    if posicion==len(matriz[fila]):
        return lista1
    else:
        ventana_elemento=conseguir_ventana(matriz, posicion,fila, ventana, [], 0)
        media=calculo_media_ordenamiento(ventana_elemento) ###
        return conseguir_posicion_ordenamiento(matriz,posicion+1,ventana,fila,lista1+[media])

def conseguir_posicion_QS(matriz, posicion,ventana,fila,lista1):
    """
    Se define una función que consigue la posición de cada uno de los elementos
    de la ventana
    """
    if(posicion == len(matriz[fila])):
        return lista1
    else:
        ventana_elemento=conseguir_ventana(matriz, posicion,fila, ventana, [], 0)
        media = calculo_media_QS(ventana_elemento)
        return conseguir_posicion_QS(matriz, posicion + 1, ventana, fila, lista1 + [media])

def recorrer_matriz_ordenamiento(lista, elemento, ventana, lista1):
    """
    recorre la matriz
    en cada llamado aumenta el elemento que representa la fila en la que se ubica
    utiliza el algoritmo de ordenamiento
    """
    if elemento== len(lista):
            return lista1
    else:
        return recorrer_matriz_ordenamiento(lista, elemento+1, ventana,lista1+[conseguir_posicion_ordenamiento(lista,0,ventana,elemento,[])])
         
def recorrer_matriz_QS(lista,elemento,ventana,lista1):
    """
    recorre la matriz
    en cada llamado aumenta el elemento que representa la fila en la que se ubica
    """
    if elemento== len(lista):
        return lista1
    else:
        return recorrer_matriz_QS(lista, elemento+1, ventana,lista1+[conseguir_posicion_QS(lista,0,ventana,elemento,[])])   
      
def conse_elemento(lista,columna,ventana,contador):
    """
    Se define una función que consigue los elementos de la ventana
    se crea una variable nueva_posicion que contenga los elementos de la nueva
    posición
    """   
    if contador== ventana:
        return []
    else:
        nueva_posicion=columna-ventana+contador+1
        if nueva_posicion<0 or nueva_posicion>=len(lista):  
            return conse_elemento(lista,columna, ventana, contador+1)
        else:
            nuevo_elemento=lista[nueva_posicion]
            return [nuevo_elemento]+conse_elemento(lista,columna, ventana, contador+1)
            
def conseguir_ventana(lista,columna,fila,ventana,nueva_lista,contador):
    """
    Se crea una función que consigue la ventana
    se crea una variable nueva_fila que contenga el valor de la fila actual
    menos la ventana mas el contador incrementado en 1
    """
    if contador == ventana:
        return nueva_lista
    else:
        nueva_fila = fila - ventana + contador + 1
        if (nueva_fila <0 or nueva_fila >= len(lista)):
           return conseguir_ventana(lista,columna,fila,ventana,nueva_lista,contador+1) 
        else:
            resultado = conse_elemento(lista[nueva_fila], columna, ventana, 0)
            return conseguir_ventana(lista, columna, fila, ventana, nueva_lista + resultado, contador + 1)

def guardar_imagen():
    """
    Se define una función que guarde la imagen que fue corregida
    """
    #Se le pregunta al usuario el nombre de la imagen
    nombre = input("Inserte el nombre de la imagen   ")
    #Se le pregunta al usuario la ventana que desea utilizar
    ventana = int(input("Ingrese la ventana   "))
    #Se comienza con el contador
    inicio = time.time()
    lista = filtrar_imagen(nombre)
    resultado = recorrer_matriz_QS(lista, 0, ventana, [])
    #Se define el nuevo nombre de la nueva imagen creada mediante una copia
    nuevo_nombre = nombre.split(".")
    nuevo_nombre = nuevo_nombre[0] + "_copia." + nuevo_nombre[1]
    # se guarda como imagen
    image = np.array(resultado)
    pi2 = Image.fromarray((image).astype(np.uint8))
    pi2.show()
    pi2.save(nuevo_nombre)
    #Se le pone fin al contador
    fin = time.time()
    #Se realiza la resta entre el tiempo en el que termina y luego con el tiempo en el que inicia
    tiempo = fin - inicio
    #Se calculan los milisegundos que tarda en realizarse la operación
    milisegundos= round(tiempo, 3)
    print("Duración:", milisegundos, "ms")