
package empresa;


public class Empleado extends Entidad{
    private int salarioMensual;
    private String fechaIngreso;
    public Empleado(String pNombre,int pIdentificacion,int pTelefono,String pDireccion,String pEmail, int pSalarioMensual, String pFechaIngreso){
        super(pNombre,pIdentificacion,pTelefono,pDireccion,pEmail);
        salarioMensual = pSalarioMensual;
        fechaIngreso = pFechaIngreso; 
    }
    public int getSalarioMensual(){
        return salarioMensual;
    }
    public String getFechaIngreso(){
        return fechaIngreso;
    }
    public String toString(){
        String info;
        info="\nLos datos del empleado son: \nNombre: "+getNombre()+"\nIdentificacion: "+getIdentificacion()+"\nTelefono: "+getTelefono()+"\nDireccion: "+getDireccion()+"\nEmail: "+getEmail()+"\nSalario mensual: "+getSalarioMensual()+"\nFecha de ingreso: "+getFechaIngreso()+"\n";
        return info;
    }
}
