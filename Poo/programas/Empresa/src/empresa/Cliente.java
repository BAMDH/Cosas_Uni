
package empresa;


public class Cliente extends Entidad{
    private int creditoLimite;
    private String fechaRegistro;
    private int telefonoEmpresa;
    private String nombreEmpresa;
    public Cliente(String pNombre,int pIdentificacion,int pTelefono,String pDireccion,String pEmail,String pNombreEmpresa,int pTelefonoEmpresa, int pCreditoLimite, String pFechaRegistro){
        super(pNombre,pIdentificacion,pTelefono,pDireccion,pEmail);
        setNombreEmpresa(pNombreEmpresa);
        setTelefonoEmpresa(pTelefonoEmpresa);
        setCreditoLimite(pCreditoLimite);
        setFechaRegistro(pFechaRegistro);
    }
    public void setCreditoLimite(int pCreditoLimite){
        creditoLimite = pCreditoLimite;
    }
    public void setFechaRegistro(String pFechaRegistro){
        fechaRegistro = pFechaRegistro; 
    }
    public void setNombreEmpresa(String pNombreEmpresa){
        nombreEmpresa = pNombreEmpresa;
    }
    public void setTelefonoEmpresa(int pTelefonoEmpresa){
        telefonoEmpresa = pTelefonoEmpresa;
    }
    public int getCreditoLimite(){
        return creditoLimite;
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
        info="\nLos datos del cliente son:";
        if(getNombre()!=""){
            info=info+ " \nNombre: "+getNombre()+"\nTelefono: "+getTelefono();
        }
        if(getNombreEmpresa()!=""){
            info=info+ " \nNombre empresa: "+getNombreEmpresa()+"\nTelefono empresa: "+getTelefonoEmpresa();
        }
        info=info+"\nIdentificacion: "+getIdentificacion()+"\nDireccion: "+getDireccion()+"\nEmail: "+getEmail() +"\nCredito limite: "+getCreditoLimite()+"\nFecha de ingreso: "+getFechaRegistro()+"\n";
        return info;
    }
}
