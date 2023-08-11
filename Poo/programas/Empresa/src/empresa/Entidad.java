
package empresa;


public class Entidad {
    private String nombre;
    private int identificacion;
    private int telefono;
    private String direccion;
    private String email;
    
    public Entidad(String pNombre,int pIdentificacion,int pTelefono,String pDireccion,String pEmail){
        setNombre(pNombre);
        setIdentificacion(pIdentificacion);
        setTelefono(pTelefono);
        setDireccion(pDireccion);
        setEmail(pEmail);
    }
    public void setNombre(String pNombre){
        nombre = pNombre;
    }
    public void setIdentificacion(int pIdentificacion){
        identificacion = pIdentificacion;
    }
    public void setTelefono(int pTelefono){
        telefono = pTelefono;
    }
    public void setDireccion(String pDireccion){
        direccion = pDireccion;
    }
    public void setEmail(String pEmail){
        email = pEmail;
    }
   public String getNombre(){
        return nombre;
    }
    public int getIdentificacion(){
        return identificacion;
    }
    public int getTelefono(){
        return telefono;
    }
    public String getDireccion(){
        return direccion;
    }
    public String getEmail(){
        return email;
    }
    public String toString(){
        String info;
        info="Los datos de la entidad son: \n Nombre: "+getNombre()+"\nIdentificacion: "+getIdentificacion()+"\nTelefono: "+getTelefono()+"\nDireccion: "+getDireccion()+"\nEmail: "+getEmail();
        return info;
    }
}
