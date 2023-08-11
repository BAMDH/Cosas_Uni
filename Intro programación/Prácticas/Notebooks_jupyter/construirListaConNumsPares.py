# -*- coding: utf-8 -*-
"""
Created on Thu Feb 25 19:00:58 2016

@author: Saul
"""

def construirListaConNumsPares(lista):
    if(lista == []):
        print("La lista esta vacia");
        return lista;
    else:
        return construirListaConNumsPares_aux(lista);  
        
def construirListaConNumsPares_aux(lista):
    if(lista != []):
        elemento = lista[0];
        #es par 
        if(elemento % 2 == 0):
            return [elemento] + construirListaConNumsPares_aux(lista[1:]);
        else:
            return [] + construirListaConNumsPares_aux(lista[1:]);
    else:
        return [];
        
lista = [1, 5, 7, 9, 6, 4];
listaN = construirListaConNumsPares(lista);
    