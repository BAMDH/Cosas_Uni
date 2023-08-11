#reproduccion de conejos: cada par de conejos tiene N conejitos cada mes.
#Aproximadamente despues de M,   meses (p.ej 5 meses), cada conejo se muere
#Implementar una funcion que estime la cantidad de conejos en una granja, al inicio de cada mes (m = 0, conejos = N)


def estimarPoblacionConejos(numTotalMeses, poblacionInicial, numHijosPorConejo, mesesVidaConejo):
    if(type(numTotalMeses) == type(poblacionInicial) == type(numHijosPorConejo) == int):
        return estimarPoblacionConejosAux(0, numTotalMeses, numHijosPorConejo, mesesVidaConejo, [poblacionInicial], poblacionInicial);
    else:
        raise ValueError("Tipos de datos incorrectos");


def estimarPoblacionConejosAux(n, numMesFinal, numHijosPorConejo, mesesVidaConejo, colaConejosAMorir, totalConejos):
    if(n == numMesFinal):
        return totalConejos;
    else:        
        conejosNacidos = (totalConejos // 2 ) * numHijosPorConejo;
        
        numConejosAMorir = 0;
        if(len(colaConejosAMorir) == mesesVidaConejo):
            numConejosAMorir = colaConejosAMorir[0];
            #colaConejosAMorir = colaConejosAMorir[1 : ];
            del(colaConejosAMorir[0]);

        colaConejosAMorir += [conejosNacidos];
        totalConejos += conejosNacidos - numConejosAMorir; 
        return estimarPoblacionConejosAux(n + 1, numMesFinal, numHijosPorConejo, mesesVidaConejo, colaConejosAMorir, totalConejos);

numMesFinal = 2;
numHijosPorConejo = 3;
poblacionInicial = 6;
mesesVidaConejo = 2
poblacion = estimarPoblacionConejos(numMesFinal, poblacionInicial, numHijosPorConejo, mesesVidaConejo);
print("Estimacion de la poblacion: " + str(poblacion));
