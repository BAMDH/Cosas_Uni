/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package empresa;


public class Proveedor extends Entidad{
    private int montoOtorgado;
    private String fechaRegistro;
    private int telefonoEmpresa;
    private String nombreEmpresa;
    public Proveedor(String pNombre,int pIdentificacion,int pTelefono,String pDireccion,String pEmail,String pNombreEmpresa,int pTelefonoEmpresa, int pMontoOtorgado, String pFechaRegistro){
        super(pNombre,pIdentificacion,pTelefono,pDireccion,pEmail);
        montoOtorgado = pMontoOtorgado;
        fechaRegistro = pFechaRegistro; 
        nombreEmpresa = pNombreEmpresa;
        telefonoEmpresa = pTelefonoEmpresa;
    }
    public int getMontoOtorgado(){
        return montoOtorgado;
    }
    public String getFechaRegistro(){
        return fechaRegistro;
    }
    public String getNombreEmpresa(){
        return nombreEmpresa;
    }
    public int getTelefonoEmpresa(){
        return telefonoEmpresa;
    }
    public String toString(){
        String info;
        info="\nLos datos del proveedor son:";
        if(getNombre()!=""){
            info=info+ " \nNombre: "+getNombre()+"\nTelefono: "+getTelefono();
        }
       if(getNombreEmpresa()!=""){
            info=info+ " \nNombre empresa: "+getNombreEmpresa()+"\nTelefono empresa: "+getTelefonoEmpresa();
        }
        info=info+"\nIdentificacion: "+getIdentificacion()+"\nDireccion: "+getDireccion()+"\nEmail: "+getEmail() +"\nMonto otorgado: "+getMontoOtorgado()+"\nFecha de ingreso: "+getFechaRegistro()+"\n";
        return info;
    }
    
}
