# -*- coding: utf-8 -*-
"""
Created on Tue Jun  7 16:18:26 2022

@author: Usuario
"""

class Paciente():
    def __init__(self,nombre,cedula, edadCronologica):
        self.__nombre=nombre
        self.__cedula=cedula
        self.__edadCronologica=edadCronologica
    def obtener_datos_pacientes(self):
      
        return [self.__nombre,self.__cedula,self.__edadCronologica]
      
class Hospital():
    def __init__(self,nombre):
        
        self.__nombre=nombre
        self.__lista_pacientes=[]

    def agregar_paciente(self, paciente):
        self.__lista_pacientes+=[paciente.obtener_datos_pacientes()]
     
        
    def mostrar_pacientes(self):
        paciente= 0
        while(len(self.__lista_pacientes)!=paciente):
            print(self.__lista_pacientes[paciente][0],"edad cronologica:",self.__lista_pacientes[paciente][2])
            paciente= paciente+1

hospital = Hospital("Max Peralta")
hospital.agregar_paciente(Paciente("saul", 3236211,edadCronologica = 21))
hospital.agregar_paciente(Paciente("paul", 3236115,edadCronologica = 2))
hospital.agregar_paciente(Paciente("raul", 3236113,edadCronologica = 5))
hospital.agregar_paciente(Paciente("carlo", 3236114,edadCronologica =51))
hospital.agregar_paciente(Paciente("pablo", 3236117,edadCronologica = 3))
hospital.mostrar_pacientes()