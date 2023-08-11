def combinarElementoIterativo(self, lista, elemento):
		inserto = False;
		for i in range(0, len(lista)):
			if(lista[i] > elemento):
				lista.insert(i, elemento);
				inserto = True;
		if(not inserto):
			lista.insert(len(lista), elemento);
			

		
