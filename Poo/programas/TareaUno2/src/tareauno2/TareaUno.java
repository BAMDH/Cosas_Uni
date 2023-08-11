
package tareauno2;
import java.util.Scanner;

public class TareaUno {

    
    ///Creado por Brandon Andrés Mora Díaz 2022164409
        
    public static void pausar(){
       Scanner leerPausa = new java.util.Scanner(System.in);     
       System.out.println("Presione enter para continuar...");
       String parar=leerPausa.findInLine("");
       System.out.println("");    
       leerPausa.reset();
    }
    
    public static String [][] registrarPasajeros(String arreglo[][],String id,int ultimo, Scanner leerDatos, int len){
        String nombre="";
        String apellidos="";
        len=16;
        leerDatos.nextLine();
        while(len>15){
            System.out.println("Inserte un nombre menor a 15 caracteres");
            nombre= leerDatos.nextLine();
            len=nombre.length();
        }
        len=31;
        while(len>30){
            System.out.println("Inserte appellidos menores a 30 caracteres");
            apellidos= leerDatos.nextLine();
            len=apellidos.length();
        }
        
        arreglo[ultimo][0]= id;
        arreglo[ultimo][1]=nombre;
        arreglo[ultimo][2]=apellidos;
        
        return arreglo;        
    }

    public static String [][][][] crearAviones(String aviones[][][][], int ultimo){
        for(short i=0;i<4;i++){
           aviones[ultimo][i]= new String[4][3];
           for(byte g=0;g<4;g++){
               aviones[ultimo][i][g][0]="J";
                aviones[ultimo][i][g][1]="A";
           }
        }
        for(short i=4;i<25;i++){
           aviones[ultimo][i]= new String[6][3];
            for(byte g=0;g<6;g++){
                aviones[ultimo][i][g][0]="J";
                aviones[ultimo][i][g][1]="A";
           }
        }            
        return aviones;
}
    
    public static String [][][][] excluirAvion(String aviones[][][][], int avion){
        for(short i=0;i<4;i++){
           for(byte g=0;g<4;g++){
               aviones[avion][i][g][0]=null;
                aviones[avion][i][g][1]=null;
                aviones[avion][i][g][2]=null;
           }
        }
        for(short i=4;i<25;i++){
            for(byte g=0;g<6;g++){
                aviones[avion][i][g][0]=null;
                aviones[avion][i][g][1]=null;
                aviones[avion][i][g][2]=null;
           }
        }            
        return aviones;
}
  
    public static String [][] eliminarPasajerosAvion(String pasajeros[][],String aviones[][][][],int avion){
        int posicion;
        for(short i=0;i<4;i++){
           for(byte g=0;g<4;g++){
               if("A".equals(aviones[avion][i][g][1])){
                   posicion=posicionPasajero(pasajeros,aviones[avion][i][g][2]);
                   pasajeros[posicion][3]=null;
                   pasajeros[posicion][4]=null;
               }
           }
        }
        for(short i=4;i<25;i++){
            for(byte g=0;g<6;g++){
                if("A".equals(aviones[avion][i][g][1])){
                   posicion=posicionPasajero(pasajeros,aviones[avion][i][g][2]);
                   pasajeros[posicion][3]=null;
                   pasajeros[posicion][4]=null;
               }
            }
        }
        return pasajeros;
}
       
    public static String [][][][] vaciarAvion(String aviones[][][][], int avion){
        for(short i=0;i<4;i++){
           for(byte g=0;g<4;g++){
               if("A".equals(aviones[avion][i][g][1])){
                    aviones[avion][i][g][2]=null;
               }
           }
        }
        for(short i=4;i<25;i++){
            for(byte g=0;g<6;g++){
                if("A".equals(aviones[avion][i][g][1])){
                    aviones[avion][i][g][2]=null;
               }
            }
        }            
        return aviones;
     }
    
    public static void consultarAvion(String aviones[][][][], int avion,String listaPasajeros[][]){
        System.out.println("Clase Ejecutiva");
        String letra="";
        int posicion=0;
        for(short i=0;i<4;i++){
           for(byte g=0;g<4;g++){
               switch(g){
                   case 0:
                       letra="A";
                       break;
                   case 1:
                       letra="B";
                       break;
                   case 2:
                       letra="C";
                       break;
                   case 3:
                       letra="D";
                       break;
               }

               if(aviones[avion][i][g][2]==null){
                    if("I".equals(aviones[avion][i][g][1])){
                        System.out.print((i+1)+"-"+letra+" inactivo"+" ");  
                    }
                    else{
                          System.out.print((i+1)+"-"+letra+" vacio"+" ");
                    }
                }
               else{
                   posicion=posicionPasajero(listaPasajeros,aviones[avion][i][g][2]);
                   System.out.print((i+1)+"-"+letra+" "+listaPasajeros[posicion][1]+" "+listaPasajeros[posicion][2]+" ");
               }

           }
        System.out.print("\n");
        }
        System.out.println("Clase Ejecutiva");
        for(short i=4;i<25;i++){
            for(byte g=0;g<6;g++){
               switch(g){
                   case 0:
                       letra="A";
                       break;
                   case 1:
                       letra="B";
                       break;
                   case 2:
                       letra="C";
                       break;
                   case 3:
                       letra="D";
                       break;
                   case 4:
                       letra="E";
                       break;
                   case 5:
                       letra="F";
                       break;
               }
               
               if(aviones[avion][i][g][2]==null){
                    if("I".equals(aviones[avion][i][g][1])){
                        System.out.print((i+1)+"-"+letra+" inactivo"+" ");  
                    }
                    else{
                          System.out.print((i+1)+"-"+letra+" vacio"+" ");
                    }
               }
               else{
                   posicion=posicionPasajero(listaPasajeros,aviones[avion][i][g][2]);
                   System.out.print((i+1)+"-"+letra+" "+listaPasajeros[posicion][1]+" "+listaPasajeros[posicion][2]+" ");
               }
           }
           System.out.print("\n"); 
        }            
    }
    
    public static void consultarAsientos(String aviones[][][][], String listaAviones[]){
        int avion=0;
        while (avion<listaAviones.length){
            if(listaAviones[avion]!=null){
                System.out.println("En el avion "+listaAviones[avion]);
                int disponible=0;
                for(short i=0;i<4;i++){
                   for(byte g=0;g<4;g++){
                       if (aviones[avion][i][g][2]==null){
                            if("A".equals(aviones[avion][i][g][1])){
                                disponible=disponible+1;
                            }    
                       }
                   }
                }
                System.out.println("Hay "+disponible+" asientos disponibles en clase ejecutiva");
                disponible=0;
                for(short i=4;i<25;i++){
                    for(byte g=0;g<6;g++){
                       if (aviones[avion][i][g][2]==null){
                            if("A".equals(aviones[avion][i][g][1])){
                                disponible=disponible+1;

                            }
                       }
                    }
                }            
                System.out.println("Hay "+disponible+" asientos disponibles en clase economica");
            }
            avion=avion+1;
        }    
    }
    
    public static boolean pasajeroEnAvion(String arreglo [][][][],int avion){
        int i=0;
        int g=0;
        boolean encontro= false;
        while (((i < 4)&&(!encontro))){
               g=0;
               while (((g < 4)&&(!encontro))){
               if(arreglo[avion][i][g][2]!=null){
                   encontro=true;
               }
               g=g+1;
               } 
               i=i+1;
        }
        i=4;
        while (((i < 25)&&(!encontro))){
               g=0;
               while (((g < 6)&&(!encontro))){
                    if(arreglo[avion][i][g][2]!=null){
                        encontro=true;
               }
               g=g+1;
               } 
               i=i+1;
        }
        return encontro;
    }
    
    public static boolean buscarAvion(String arreglo[], String avion){
        boolean encontro= false;
        for(int i=0;i < arreglo.length;){
            if(arreglo[i]!=null){
                if((arreglo[i]).equals(avion)){
                    encontro=true;
                }
            }
            i=i+1;
        }
        return encontro;
    }
   
    public static boolean buscarPasajero(String arreglo[][], String id){
        int i=0;
        boolean encontro= false;
        while ((((i < arreglo.length)&&(!encontro)))){
            if(arreglo[i][0]!=null){
                if((arreglo[i][0]).equals(id)){
                    encontro=true;
                }
            }
            i=i+1;
        }
        return encontro;
    }
    
    public static int posicionAvion(String arreglo[], String avion){
        int i=0;
        boolean encontro= false;
        while ((((i < arreglo.length)&&(!encontro))&&(arreglo[i]!=null))){
            if(arreglo[i]!=null){
                if((arreglo[i]).equals(avion)){
                    encontro=true;
                }
            }
            else{
                i=(i+1);
            }
        }
        return i;
    }
    
    public static int posicionPasajero(String arreglo[][], String pasajero){
            int i=0;
        boolean encontro= false;
        while ((((i < arreglo.length)&&(!encontro))&&(arreglo[i][0]!=null))){
            if((arreglo[i][0]).equals(pasajero)){
                encontro=true;
                }
            else{
                i=(i+1);
            }
        }
        return i;
    }
    
    public static int[] asientoPosicion(int poAsiento[],String asiento){
    int fila=Integer.parseInt(asiento.charAt(0)+"")*10;
    fila=fila+Integer.parseInt(asiento.charAt(1)+"");
    if(("J".equals(asiento.charAt(3)+""))&&(fila<5)){
        if("A".equals(asiento.charAt(2)+"")){
            poAsiento[1]=0;
        }
        if("B".equals(asiento.charAt(2)+"")){
            poAsiento[1]=1;
        }       
        if("C".equals(asiento.charAt(2)+"")){
            poAsiento[1]=2;
        }
        if("D".equals(asiento.charAt(2)+"")){
            poAsiento[1]=3;
        }
    }
    else if(("E".equals(asiento.charAt(3)+""))&&(fila>4)){
        if("A".equals(asiento.charAt(2)+"")){
            poAsiento[1]=0;
        }
        if("B".equals(asiento.charAt(2)+"")){
            poAsiento[1]=1;
        }       
        if("C".equals(asiento.charAt(2)+"")){
            poAsiento[1]=2;
        }
        if("D".equals(asiento.charAt(2)+"")){
            poAsiento[1]=3;
        }
        if("E".equals(asiento.charAt(2)+"")){
            poAsiento[1]=4;
        }
        if("F".equals(asiento.charAt(2)+"")){
            poAsiento[1]=5;
        }
    }
   else{
          poAsiento[1]=999;
    }
    if ((0<fila)&&(fila<26)){
        poAsiento[0]=fila-1;
    }
    else{
            poAsiento[0]=999;
        }
    return poAsiento;

    }
   
    public static String [][][][] modificarEstadoAsiento(String aviones[][][][],int avion,int fila,int asiento){
        if("A".equals(aviones[avion][fila][asiento][1])){
            aviones[avion][fila][asiento][1]="I";
            System.out.println("Se ha modificado el estado del asiento, ahora está inactivo");
        }
        else{
            aviones[avion][fila][asiento][1]="A";
            System.out.println("Se ha modificado el estado del asiento, ahora está activo");
        }
        return aviones;
    }
   
    public static boolean comprobarIgual(String n,int len, short limite, Scanner leerDatos){
         len=n.length();
        boolean encontro=false;
        if ((len!=limite)){
          encontro=false;
        }
        else{
          encontro=true;
        }
         return encontro;
    }
    
    
    public static boolean comprobarRango(String n,int len, short limite,short minimo, Scanner leerDatos){
         len=n.length();
        boolean encontro=false;
        if ((len>limite)||(len<minimo)){
          encontro=false;
        }
        else{
          encontro=true;
        }
         return encontro;
    }
    
    public static boolean comprobarLen(String n,int len, short limite, Scanner leerDatos){
         len=n.length();
        boolean encontro=false;
        if ((len>limite)){
          encontro=false;
        }
        else{
          encontro=true;
        }
         return encontro;
   }
    
    public static void mostrarInfoPasajero(String pasajeros[][],int posicion){
        System.out.println("Nombre: "+pasajeros[posicion][1]);
        System.out.println("Apellidos: "+pasajeros[posicion][2]);
        if(pasajeros[posicion][4]!=null){
            System.out.println("Avion: "+pasajeros[posicion][4]);
        }
        else{
            System.out.println("Avion: no asignado");
        }
        if(pasajeros[posicion][3]!=null){
            System.out.println("Asiento: "+pasajeros[posicion][3]);
        }
        else{
            System.out.println("Asiento: no asignado");
        }
        
    }
            
    
    public static void main(String[] args) {
        String idAvion;
        String idPasajero;
        int len;
        boolean cierto;
        int ultimoPasajero=0;
        int ultimoAvion=0;
        int posicion;
        int posicionPasajero;
        short limite;
        short minimo;
        String asiento;
        String [][] pasajeros;
        pasajeros = new String[1000][5];
        String opcion = null;
        String [][][][]aviones;
        String formatoAsiento;
        aviones=new String[20][25][][];
        String []listaAviones=new String[20];
        int posicionAsientos []=new int[2];
        Scanner leerDatos = new Scanner(System.in); 
        
        while(!"0".equals(opcion)){

           System.out.println("0) Salir\n1) Registrar avion\n2) Modificar capacidad avion\n3) Excluir avion\n4) Asignar asiento\n5) Vaciar asiento\n6) Vaciar avion\n7) Consultar estado del avion\n8) Consultar informacion pasajero\n9) Consultar cantidad de asientos\n10) Registrar pasajeros\n");
           opcion = leerDatos.nextLine();
           leerDatos.reset();
           switch(opcion){
                case "1":
                    len=0;
                    limite=5;
                    System.out.println("Inserte la identificacion del avion, 5 caracteres");
                    idAvion=leerDatos.next();
                    leerDatos.nextLine();
                    cierto=comprobarIgual(idAvion,len,limite,leerDatos);
                    if (!cierto){
                            System.out.println("Numero incorrecto de caracteres");
                            pausar();
                            break;
                        }
                    cierto=buscarAvion(listaAviones,idAvion);
                    if(cierto==false){
                        listaAviones[ultimoAvion]=idAvion;
                        aviones=crearAviones(aviones,ultimoAvion);
                        ultimoAvion=ultimoAvion+1;
                        System.out.println("Se ha agregado el Avion");
                    }
                    else{
                        System.out.println("Avion existente");
                    }
                    pausar();
                    break;
                
                
                    
                    
                case "2":
                    len=0;
                    limite=5;
                    System.out.println("Inserte la identificacion del avion, 5 caracteres");
                    idAvion=leerDatos.next();
                    cierto=comprobarIgual(idAvion,len,limite,leerDatos);
                    if (!cierto){
                            System.out.println("Numero incorrecto de caracteres");
                            pausar();
                            break;
                        }
                    if(buscarAvion(listaAviones,idAvion)==false){
                        System.out.println("No existe el avion "+idAvion);
                        pausar();
                        break;
                    }
                    posicion=posicionAvion(listaAviones,idAvion);
                    asiento="";
                    len=0;
                    limite=4;
                    System.out.println("ingrese el asiento siguiendo el formato FFLC\nSiendo FF la fila que es un valor entre 01 a 25, L la letra y C la clase");
                    asiento=leerDatos.next();
                    cierto=comprobarIgual(asiento,len,limite,leerDatos);
                        if (!cierto){
                            System.out.println("Numero incorrecto de caracteres");
                        }
                    posicionAsientos=asientoPosicion(posicionAsientos,asiento);
                    if ((posicionAsientos[0]==999)||(posicionAsientos[1]==999)){
                        System.out.println("Asiento no encontrado");
                    }
                    else{
                        if(aviones[posicion][posicionAsientos[0]][posicionAsientos[1]][2]==null){
                            aviones=modificarEstadoAsiento(aviones,posicion,posicionAsientos[0],posicionAsientos[1]);
                        }
                        else{
                            System.out.println("El asiento ya está en uso");
                        }
                       
                    }
                    pausar();
                    break;

                    
                    
                    
                    case "3":
                    len=0;
                    limite=5;
                    System.out.println("Inserte la identificacion del avion, 5 caracteres");
                    idAvion=leerDatos.next();
                    cierto=comprobarIgual(idAvion,len,limite,leerDatos);
                    if (!cierto){
                            System.out.println("Numero incorrecto de caracteres");
                            pausar();
                            break;
                        }
                    if(buscarAvion(listaAviones,idAvion)==false){
                        System.out.println("No existe el avion "+idAvion);
                        pausar();
                        break;
                    }
                    posicion=posicionAvion(listaAviones,idAvion);
                    cierto=pasajeroEnAvion(aviones,posicion);
                    if(cierto){
                        System.out.println("Este avion aún tiene pasajeros");
                        pausar();
                        break;
                    }
                    aviones=excluirAvion(aviones,posicion);
                    listaAviones[posicion]=null;
                    System.out.println("Se ha eliminado el avion");
                    pausar();
                    break;                   
                    
                    
                    
                case "4":
                    len=0;
                    limite=5;
                    System.out.println("Inserte la identificacion del avion, 5 caracteres");
                    idAvion=leerDatos.next();
                    cierto=comprobarIgual(idAvion,len,limite,leerDatos);
                    if (!cierto){
                            System.out.println("Numero incorrecto de caracteres");
                            pausar();
                            break;
                        }
                    if(buscarAvion(listaAviones,idAvion)==false){
                        System.out.println("No existe el avion "+idAvion);
                        pausar();
                        break;
                    }
                    posicion=posicionAvion(listaAviones,idAvion);
                    formatoAsiento="";
                    len=0;
                    limite=21;
                    minimo=5;
                    System.out.println("Ingrese el asiento siguiendo el formato FFLCP\nSiendo FF la fila que es un valor entre 01 a 25, L la letra, C la clase y P la identificación del pasajero\n");
                    formatoAsiento=leerDatos.next();
                    cierto=comprobarRango(formatoAsiento,len,limite,minimo,leerDatos);
                    if (!cierto){
                            System.out.println("Numero incorrecto de caracteres");
                            pausar();
                            break;
                        } 
                    asiento="";
                    idPasajero="";
                    for(byte i=0;i<4;i++){
                        asiento=asiento+formatoAsiento.charAt(i);
                    }
                    for(byte i=4;i<formatoAsiento.length();i++){
                        idPasajero=idPasajero+formatoAsiento.charAt(i);
                    }
                     posicionAsientos=asientoPosicion(posicionAsientos,asiento);
                     if ((posicionAsientos[0]==999)||(posicionAsientos[1]==999)){
                        System.out.println("Asiento no encontrado");
                        pausar();
                        break;
                    }
                    else{
                    cierto=buscarPasajero(pasajeros,idPasajero);
                        if(cierto){ 
                            if(aviones[posicion][posicionAsientos[0]][posicionAsientos[1]][2]==null){    
                                posicionPasajero=posicionPasajero(pasajeros,idPasajero);
                                if(pasajeros[posicionPasajero][3]==null){
                                    if("A".equals(aviones[posicion][posicionAsientos[0]][posicionAsientos[1]][1])){
                                        pasajeros[posicionPasajero][4]=idAvion;
                                        pasajeros[posicionPasajero][3]=asiento;
                                        aviones[posicion][posicionAsientos[0]][posicionAsientos[1]][2]=idPasajero;
                                        System.out.println("Se ha agregado el pasajero "+idPasajero+" a el asiento "+asiento);
                                    }
                                    else{
                                        System.out.println("No se ha podido acceder al asiento "+asiento+" porque está inactivo");
                                    }
                                }
                                    
                                else{
                                System.out.println("El pasajero ya tiene asignado el asiento "+pasajeros[posicionPasajero][3]);
                                }
                            }
                            else{
                                System.out.println("El asiento ya está en uso");
                            }
                        }
                        else{
                                System.out.println("No existe el pasajero "+idPasajero);
                            }
                     }
                    pausar();
                    break;
                
                    
                case "5":
                    len=0;
                    limite=5;
                    System.out.println("Inserte la identificacion del avion, 5 caracteres");
                    idAvion=leerDatos.next();
                    cierto=comprobarIgual(idAvion,len,limite,leerDatos);
                    if (!cierto){
                            System.out.println("Numero incorrecto de caracteres");
                            pausar();
                            break;
                        }
                    if(buscarAvion(listaAviones,idAvion)==false){
                        System.out.println("No existe el avion "+idAvion);
                        pausar();
                        break;
                    }
                    posicion=posicionAvion(listaAviones,idAvion);
                    formatoAsiento="";
                    len=0;
                    limite=21;
                    minimo=5;
                    System.out.println("Ingrese el asiento siguiendo el formato FFLCP\nSiendo FF la fila que es un valor entre 01 a 25, L la letra, C la clase y P la identificación del pasajero\n");
                    formatoAsiento=leerDatos.next();
                    cierto=comprobarRango(formatoAsiento,len,limite,minimo,leerDatos);
                    if (!cierto){
                            System.out.println("Numero incorrecto de caracteres");
                            pausar();
                            break;
                        }
                    asiento="";
                    idPasajero="";
                    for(byte i=0;i<4;i++){
                        asiento=asiento+formatoAsiento.charAt(i);
                    }
                    for(byte i=4;i<formatoAsiento.length();i++){
                        idPasajero=idPasajero+formatoAsiento.charAt(i);
                    }
                     posicionAsientos=asientoPosicion(posicionAsientos,asiento);
                     if ((posicionAsientos[0]==999)||(posicionAsientos[1]==999)){
                        System.out.println("Asiento no encontrado");
                        pausar();
                        break;
                    }
                    else{
                    cierto=buscarPasajero(pasajeros,idPasajero);
                        if(cierto){ 
                            if(aviones[posicion][posicionAsientos[0]][posicionAsientos[1]][2]!=null){
                                if(aviones[posicion][posicionAsientos[0]][posicionAsientos[1]][2].equals(idPasajero)){ 
                                    posicionPasajero=posicionPasajero(pasajeros,idPasajero);
                                    pasajeros[posicionPasajero][3]=null;
                                    pasajeros[posicionPasajero][4]=null;
                                    aviones[posicion][posicionAsientos[0]][posicionAsientos[1]][2]=null;
                                    System.out.println("Se ha liberado  el asiento "+asiento);
                                } 
                                else{
                                    System.out.println("El pasajero"+idPasajero+" no está en el asiento "+asiento);
                                }
                            }
                            else{
                            System.out.println("El asiento está vació");
                            }
                        }
                        else{
                          System.out.println("No existe el pasajero "+idPasajero);
                        }
                    }
                    pausar();
                    break;
                    
                case "6":
                    len=0;
                    limite=5;
                    System.out.println("Inserte la identificacion del avion, 5 caracteres");
                    idAvion=leerDatos.next();
                    cierto=comprobarIgual(idAvion,len,limite,leerDatos);
                    if (!cierto){
                            System.out.println("Numero incorrecto de caracteres");
                            pausar();
                            break;
                        }
                    if(buscarAvion(listaAviones,idAvion)==false){
                        System.out.println("No existe el avion "+idAvion);
                        pausar();
                        break;
                    }
                    posicion=posicionAvion(listaAviones,idAvion);
                    pasajeros=eliminarPasajerosAvion(pasajeros,aviones,posicion);
                    aviones=vaciarAvion(aviones,posicion);
                    System.out.println("Se han vaciado los asientos del avion");
                    pausar();
                    break;
                
                    
                case "7":
                    len=0;
                    limite=5;
                    System.out.println("Inserte la identificacion del avion, 5 caracteres");
                    idAvion=leerDatos.next();
                    cierto=comprobarIgual(idAvion,len,limite,leerDatos);
                    if (!cierto){
                            System.out.println("Numero incorrecto de caracteres");
                            pausar();
                            break;
                        }
                    if(buscarAvion(listaAviones,idAvion)==false){
                        System.out.println("No existe el avion "+idAvion);
                        pausar();
                        break;
                    }
                    posicion=posicionAvion(listaAviones,idAvion);
                    consultarAvion(aviones,posicion,pasajeros);
                    pausar();
                    break;
                    
                case "8":
                    len=13;
                    limite=12;
                    System.out.println("Inserte la identificacion del pasajero, menor o igual a 12 caracteres");
                    idPasajero=leerDatos.next();
                    cierto=comprobarLen(idPasajero,len,limite,leerDatos);
                    if (!cierto){
                            System.out.println("Numero incorrecto de caracteres");
                            pausar();
                            break;
                        }
                    cierto=buscarPasajero(pasajeros,idPasajero);
                    if(cierto==false){
                        System.out.println("El pasajero no existe");
                        pausar();
                        break;
                    }
                    posicionPasajero=posicionPasajero(pasajeros,idPasajero);
                    mostrarInfoPasajero(pasajeros,posicionPasajero);
                    pausar();
                    break;
                    
                case "9":    
                    cierto=false;
                    for(short i=0;i<listaAviones.length;i++){
                        if(listaAviones[i]!=null){
                            cierto=true;
                        }
                    }
                    if (!cierto){
                        System.out.println("No hay aviones disponibles");
                        pausar();
                        break;
                    }
                    consultarAsientos(aviones,listaAviones);
                    pausar();
                    break;
                
                
                
                
                case "10":    
                    len=13;
                    limite=12;
                    System.out.println("Inserte una identificación menor a 12 caracteres");
                    idPasajero=leerDatos.next();
                    cierto=comprobarLen(idPasajero,len,limite,leerDatos);
                    if (!cierto){
                            System.out.println("Numero incorrecto de caracteres");
                            pausar();
                            break;
                        }
                    cierto=buscarPasajero(pasajeros,idPasajero);
                    if(cierto==false){
                        pasajeros=registrarPasajeros(pasajeros,idPasajero,ultimoPasajero,leerDatos, len);
                        ultimoPasajero=ultimoPasajero+1;
                        System.out.println("Se ha agregado el pasajero\nid: "+pasajeros[ultimoPasajero-1][0]+"\nNombre: "+pasajeros[ultimoPasajero-1][1]+"\nApellidos: "+pasajeros[ultimoPasajero-1][2]);
                    }
                    else{
                        System.out.println("Usuario ya registrado");
                    }
                    pausar();
                    break;
                
            }
        }
    }
}