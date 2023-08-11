
package programa1;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 *Clase utilizada para manejar la información del atleta
 * 
 *
 * @author Usuario
 */
public class Atleta {
    private String nombre;
    private String apellido1;
    private String apellido2;
    private String idAtleta;
    private String paisOrigen;
    private String email;
    private String telefono;
    private LocalDate fechaNacimiento;
    private ArrayList<Marca> marcas= new ArrayList<Marca>();
    /**
    * Crea el atleta
    * @param pNombre Nombre
    * @param pApellido1 Primer Apellido
    * @param pApellido2 Segundo Apellido

    * @param pPaisOrigen Pais de origen
    * @param pEmail Email
    * @param pTelefono Telefono
    * @param pFechaNacimiento Fecha de nacimiento
    */
    public Atleta(String pNombre,String pApellido1,String pApellido2,String pPaisOrigen,String pEmail,String pTelefono,LocalDate pFechaNacimiento){
        setNombre(pNombre);
        setApellido1(pApellido1);
        setApellido2(pApellido2);   
        setPaisOrigen(pPaisOrigen);
        setEmail(pEmail);
        setTelefono(pTelefono);
        setFechaNacimiento(pFechaNacimiento);
    }
    /**
    *Asigna el nombre
    * @param pNombre Nombre
    */
    public void setNombre(String pNombre){
        if(pNombre.length()>=2&& pNombre.length()<=20){
            nombre=pNombre;
        }
    }
    /**
    *Asigna el primer apellido
    * @param pApellido1 Primer Apellido
    */
    public void setApellido1(String pApellido1){
        if(pApellido1.length()>=2&& pApellido1.length()<=20){
            apellido1=pApellido1;
        }
    }
    /**
    *Asigna el segundo apellido
    * @param pApellido2 Segundo Apellido
    */
    public void setApellido2(String pApellido2){
        if(pApellido2.length()>=2&& pApellido2.length()<=20){
            apellido2=pApellido2;
        }
    }
    /**
    *Asigna el Identificacion atleta
    * @param pIdAtleta Identificacion atleta
    */
    public void setIdAtleta(String pIdAtleta){
       
       idAtleta=pIdAtleta;
       
    }  
    /**
    *Asigna el Pais de origen
    * @param pPaisOrigen Pais de origen
    */
    public void setPaisOrigen(String pPaisOrigen){
        if(pPaisOrigen.length()==3){
            paisOrigen=pPaisOrigen;
        }
    }
    /**
    *Asigna el email y confirma que sea valido
    * @param pEmail Email
    */

    public void setEmail(String pEmail){
        String emailREGEX = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";                               
        Pattern pattern = Pattern.compile(emailREGEX ); 
        pattern .matcher(pEmail).matches(); 
        if(pattern .matcher(pEmail).matches()){
            email=pEmail;
        }
    }
    /**
    *Asigna el telefono
    * @param pTelefono Telefono
    */

    public void setTelefono(String pTelefono){
        if(pTelefono.length()<=20){
            telefono=pTelefono;
        }
    }
    /**
    *Asigna la fecha de nacimiento
    * @param pFechaNacimiento Fecha de nacimiento
    */
    public void setFechaNacimiento(LocalDate pFechaNacimiento){
        fechaNacimiento=pFechaNacimiento;
    }
    /**
    *Retorna la lista de marcas
    * @return
    */
    public ArrayList<Marca> getMarcas(){
        return marcas;
    }
    /**
    *Retorna el nombre
    * @return
    */
    public String getNombre(){
        return nombre;
    }
    
    /**
    *Retorna el primer apellido
    * @return
    */
    public String getApellido1(){
        return apellido1;
    }
    /**
    *Retorna el segundo apellido
    * @return
    */
    public String getApellido2(){
        return apellido2;
    }
    /**
    *Retorna la identificacion del atleta
    * @return
    */
    public String getIdAtleta(){
        return idAtleta;
    }
    
    /**
    *Retorna el pais de origen
    * @return
    */
    public String getPaisOrigen(){
        return paisOrigen;
    }  
    /**
    *Retorna el email
    * @return
    */
    public String getEmail(){
        return email;
    }
    
    /**
    *Retorna Telefono
    * @return
    */
    public String getTelefono(){
        return telefono;
    }
    
    /**
    *Retorna la fecha de nacimiento
    * @return
    */
    public LocalDate getFechaNacimiento(){
        return fechaNacimiento;
    }
    
    /**
    *Retorna el nombre más ambos apellidos
    * @return
    */
    public String getNombreCompleto(){
        return getNombre()+" "+getApellido1()+" "+getApellido2();
    }
    
    /**
    *Retorna la información de la clase
    * @return
    */
    public String toString(){
        return "\n"+getNombre()+" "+getApellido1()+" "+getApellido2()+"\nPais: "+getPaisOrigen()+"\nEmail: "+getEmail()+"\nTelefono: "+getTelefono()+"\nFecha de nacimiento: "+getFechaNacimiento();
    }
    
}
