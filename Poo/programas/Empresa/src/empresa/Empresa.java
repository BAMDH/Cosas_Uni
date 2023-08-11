/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package empresa;

import java.util.ArrayList;

public class Empresa {
    private String nombre;
    private ArrayList<Cliente> clientes;
    private ArrayList<Proveedor> proveedores;
    private ArrayList<Empleado> empleados;
    public Empresa(String pNombre){
        nombre = pNombre;
        clientes = new ArrayList<Cliente>();
        empleados = new ArrayList<Empleado>();
        proveedores = new ArrayList<Proveedor>();
    }
    public String CantidadClientesEmpresa(){
        int noEmpresa=0;
        int siEmpresa=0;
        for(int i=0; i<clientes.size();i++){
                if (clientes.get(i).getNombreEmpresa()!=""){
                    siEmpresa++;
                }
                else{
                    noEmpresa++;
                }
        }
        return "Son empresas "+siEmpresa+" clientes, mientras que "+noEmpresa+" no lo son";
    }
    public String CantidadProveedoresEmpresa(){
        int noEmpresa=0;
        int siEmpresa=0;
        for(int i=0; i<proveedores.size();i++){
                if (proveedores.get(i).getNombreEmpresa()!=""){
                    siEmpresa++;
                }
                else{
                    noEmpresa++;
                }
        }
        return "Son empresas "+siEmpresa+" proveedores, mientras que "+noEmpresa+" no lo son";
    }
    public void agregarCliente(Cliente pCliente){
        if(confirmarExistencia(pCliente.getIdentificacion())) {
            System.out.println("La persona con la identificación indicada ya existe");
        }
        else{
            clientes.add(pCliente);
        }
    }
    public void agregarEmpleado(Empleado pEmpleado){
            if(confirmarExistencia(pEmpleado.getIdentificacion())) {
            System.out.println("La persona con la identificación indicada ya existe");
        }
        else{
            empleados.add(pEmpleado);
        }
    }
    public void agregarProveedor(Proveedor pProveedor){
            if(confirmarExistencia(pProveedor.getIdentificacion())) {
            System.out.println("La persona con la identificación indicada ya existe");
        }
        else{
            proveedores.add(pProveedor);
        }
    }
    public String infoClientes(){
       String info="\nLista de clientes";
       if (clientes.size()==0){
           info=info+"\nNo hay clientes";
       }
       else{
            for(int i=0; i<clientes.size();i++){
                info=info+clientes.get(i).toString();
            }
       }
       return info;
   }
    public String infoEmpleados(){
       String info="\nLista de empleados";
       if (empleados.size()==0){
           info=info+"\nNo hay empleados";
       }
       else{
            for(int i=0; i<empleados.size();i++){
                info=info+empleados.get(i).toString();
            }
       }
       return info;
   }
    public String infoProveedores(){
       String info="\nLista de proveedores";
       if (proveedores.size()==0){
           info=info+"\nNo hay proveedores";
       }
       else{
            for(int i=0; i<proveedores.size();i++){
                info=info+proveedores.get(i).toString();
            }
       }
       return info;
   }
    public boolean confirmarExistencia(int pIdentificacion){
        boolean encontrado = false;
        for(int i=0; i<clientes.size();i++){
            if (pIdentificacion == clientes.get(i).getIdentificacion()){
                encontrado = true;
            }
        }
        for(int i=0; i<empleados.size();i++){
            if (pIdentificacion == empleados.get(i).getIdentificacion()){
                encontrado = true;
            }
        }
        for(int i=0; i<proveedores.size();i++){
            if (pIdentificacion == proveedores.get(i).getIdentificacion()){
                encontrado = true;
            }
        }
        return encontrado;
    }  
    public String toString(){
        String info = "\nInformacion sobre la empresa "+nombre;
        info=info+infoClientes()+"\n"+"\n";
        info=info+infoEmpleados()+"\n"+"\n";
        info=info+infoProveedores()+"\n"+"\n";
        return info;
    }
}
