
package empresa;

import java.util.Scanner;

public class Main {
    public static void pausar(){
       Scanner leerPausa = new java.util.Scanner(System.in);     
       System.out.println("Presione enter para continuar...");
       String parar=leerPausa.findInLine("");
       System.out.println("");    
       leerPausa.reset();
    }
    public static void main(String[] args){
    Cliente cliente1 = new Cliente("Brandon",1234,63136724,"Cortezal Costa Rica","brandon04e@gmail.com","",0,200000,"27/12/2022");
    Cliente cliente2 = new Cliente("Victor",3412,67246313,"San Jose Costa Rica","victor@gmail.com","Mercedez",999020,40000,"07/12/2022");
    Cliente cliente3 = new Cliente("Azucena",0000,00001111,"Cortezal Costa Rica","Azu@gmail.com","Floristeria Lirio",111111,4990000,"01/12/2020");
    Cliente cliente4 = new Cliente("Antonio",9999,999999,"Guanacaste Costa Rica","Antoooo@gmail.com","Logistica Antonio",22334,222223100,"07/12/2022");
    Proveedor proveedor1 = new Proveedor("Negri",0012,9999898,"Barbacoas Costa Rica","millonario@gmail.com","",0,99999999,"27/12/2002");
    Proveedor proveedor3 = new Proveedor("Maria",8461,777767,"Osaka Japon","bishop@gmail.com","Golden time",1112312,899989989,"07/23/1998");
    Proveedor proveedor2 = new Proveedor("Silvie",0010,00001111,"Sortilege","princess@gmail.com","",0,1000000000,"12/02/2003");
    Proveedor proveedor4 = new Proveedor("Isabel",8887,888888,"Gran Bretaña","Isabelle@gmail.com","",0,222222233,"07/12/1880");
    Empleado empleado1 = new Empleado("Rolando",0102,9898,"Piedades Costa Rica","necesitodinero@gmail.com",999,"2/8/2002");
    Empleado empleado2 = new Empleado("genesis",9102,9008,"Grifo Alto Costa Rica","Geen@gmail.com",22999,"2/8/2000");
    Empleado empleado3 = new Empleado("Mario",4402,8223,"Italia","cryptomonedas@gmail.com",92299,"2/8/2002");
    Empleado empleado4 = new Empleado("Martin",9232,94456,"San Pablo","necesitodinero@gmail.com",999111,"2/8/2002");
    
    Empresa empresa = new Empresa("BAMD");
    empresa.agregarCliente(cliente1);
    empresa.agregarCliente(cliente2);
    empresa.agregarCliente(cliente3);
    empresa.agregarCliente(cliente4);
    empresa.agregarCliente(cliente4);
    empresa.agregarProveedor(proveedor1);
    empresa.agregarProveedor(proveedor2);
    empresa.agregarProveedor(proveedor3);
    empresa.agregarProveedor(proveedor4);
     empresa.agregarEmpleado(empleado1);
    empresa.agregarEmpleado(empleado2);
    empresa.agregarEmpleado(empleado3);
    empresa.agregarEmpleado(empleado4);
    System.out.println(empresa.toString());
    pausar();
    System.out.println(empresa.CantidadClientesEmpresa());
    System.out.println(empresa.CantidadProveedoresEmpresa());
    pausar();
    System.out.println("Se cambia el nombre del Cliente 2 por Javier en vez de Victor");
    cliente2.setNombre("Javier");
    System.out.println(cliente2.toString());
    }
}
