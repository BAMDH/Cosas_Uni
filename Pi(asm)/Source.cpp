


#include <stdio.h>
#include <stdlib.h>
#include <time.h>


//ns=no se mofifica na= no afecta el proceso
//
extern "C" int __fastcall PrimerCalculo(int, int);
extern "C" int __fastcall ModD();
extern "C" int __fastcall GetD();
extern "C" int __fastcall ModD2();     
extern "C" int __fastcall Reiniciar();                   
long b = 0;                       
long c ;                 
long d;                        
long e = 0;           
long f = 10000;                                                                                   
long h = 0;
long a;
int diferencia=0;


long x;
bool t = false;
int o;
int y = 0;
time_t begin, end;
typedef struct Index {
    int i;
    long cantidad=0;
    Index* Siguiente;
}*PtrIndex;
void DestruirIndex(PtrIndex& Lista)
{
    PtrIndex Aux;
    Aux = Lista;
    while (Aux != NULL)
    {
        Lista = Lista->Siguiente;
        delete(Aux);
        Aux = Lista;
    }
}
void AgregarFinal(PtrIndex& Lista)
{
    PtrIndex Nuevo;
    Nuevo = new(Index);
    Nuevo->i = o;
    Nuevo->Siguiente = Lista;
    Lista = Nuevo;
}

void rellenarIndex(PtrIndex& Lista) {
    o = c;
    while (o >= 0) {
        AgregarFinal(Lista);
        o--;
    }
}

PtrIndex getIndex(PtrIndex& Lista) {
    PtrIndex Aux;
    Aux = Lista;
    while (Aux->i != b && Aux->Siguiente!=NULL)
    {
        Aux = Aux->Siguiente;
    }
    return Aux;
}
void setColor() {

    switch (y) {
            case 1: {
                system("color 89");
            }
            case 2: {
                system("color 8E");
            }
            case 3: {
                system("color 8D");
            }
            case 4: {
                system("color 87");
            }
            case 5: {
                system("color 8A");
            }
            case 6: {
                system("color 8F");
            }
            case 7: {

                system("color 89");
            }
    }
    y++;
    if (y > 7) {
        y = 1;
    }
}
//color 0 y E es rojo está bueno 4 color papaya F moradito lindo
int main(void) {
    while (true) {
        system("color ED");
        system("cls");
        printf("****************************************************************************");
        printf("\n*                       Bienvenido al calculo de pi                        *");
        printf("\n*               Ingrese la cantidadad de decimales a calcular              *");
        printf("\n****************************************************************************\n");
        fflush(stdin);
        scanf_s("\n%d", &o);
        fflush(stdin);
        begin = end = 0;
        
        time(&begin);
        o++;
        x = o;
        if (o % 4 != 0) {
            diferencia = 4 - o % 4;
            o += diferencia;
            t = true;
        }

        c = (o / 4 + 1) * 14;
        PtrIndex lista = NULL;
        PtrIndex Aux;
        rellenarIndex(lista);

        for (; (b = c -= 14) > 0;) {    //outer loop: 4 digits per loop


            for (; --b > 0;) {   //14 hasta 0   //inner loop: radix conv 


                Aux = getIndex(lista);
                Aux->cantidad = PrimerCalculo(b, Aux->cantidad);
                d = ModD();

            }
            setColor();
            if (c < 15) {
                if (x > 0 && t) {
                    a = 10;
                    diferencia--;
                    while (diferencia > 0) {
                        a *= 10;
                        diferencia--;
                    }

                    printf("%d", (e + (d / f)) / a);//print prev 4 digits
                }
                else {
                    printf("%.4d", e + d / f);//print prev 4 digits
                }
            }

            else {
                printf("%.4d", e + d / f);//print prev 4 digits
            }
            e = ModD2();


        }
        time(&end);
        time_t elapsed = end - begin;   
        f = 10000;
        printf("\nTiempo de ejecucion algoritmo pi: %ld segundos.\n", elapsed);

        scanf_s("\n%d", &o);
        getchar();
        diferencia = a = h = e = d = c = b = 0;
        Reiniciar();
        DestruirIndex(lista);
    }
    return 0;
}