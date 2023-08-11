/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programa1;

/**
 *Clase utilizada para manejar las marcas
 * @author Usuario
 */
public class Marca {
    private String atleta;
    private int marca;
    private String paisOrigen;
    private int posicion;
    private String competencia;
    private String prueba;
    private int dorsal;
    private String medida;
    /**
    * Asigna los valores necesarios para registrar la marca
    * @param pAtleta Nombre del atleta que será asignado
    * @param pMarca Marca que será asignado
    * @param pPaisOrigen Pais de origen que será asignado
    * @param pPosicion Posicion que será asignado
    * @param pCompetencia Competencia que será asignada
    * @param pPrueba Prueba que será asignada
    * @param pDorsal Dorsal que será asignado
    * @param pMedida Medida que será asignada
    */
    public Marca(String pAtleta,int pMarca,String pPaisOrigen,int pPosicion,String pCompetencia,String pPrueba, int pDorsal,String pMedida){
        setAtletla(pAtleta);
        setMarca(pMarca);
        setPaisOrigen(pPaisOrigen);
        setPosicion(pPosicion);
        setCompetencia(pCompetencia);
        setPrueba(pPrueba);
        setDorsal(pDorsal);
        setMedida(pMedida);
        
    }
    /**
    * Asigna el nombre del atleta que realizo la marca
    * @param pAtleta Nombre del atleta que será asignado
    */
    public void setAtletla(String pAtleta){
         atleta=pAtleta;
    }
    /**
    * Asigna la marca realizada
    * @param pMarca Marca que será asignado
    */
    public void setMarca(int pMarca){
         marca=pMarca;
    }
    /**
    * Asigna el pais de origen del atleta que realizo la marca
    * @param pPaisOrigen Pais de origen que será asignado
    */
    public void setPaisOrigen(String pPaisOrigen){
        paisOrigen=pPaisOrigen;
    }
    /**
    * Asigna la posicion que obtuvo el atleta que realizó la marca
    * @param pPosicion Posicion que será asignado
    */
    public void setPosicion(int pPosicion){
         posicion=pPosicion;
    }
    /**
    * Asigna la competencia en la que se realizó la marca
    * @param pCompetencia Competencia que será asignada
    */
    public void setCompetencia(String pCompetencia){
         competencia=pCompetencia;
    }
    /**
    * Asigna la prueba en la que se realizó la marca
    * @param pPrueba Prueba que será asignada
    */
    public void setPrueba(String pPrueba){
         prueba=pPrueba;
    }
    /**
    * Asigna el dorsal del atleta que realizo la marca
    * @param pDorsal Dorsal que será asignado
    */
    public void setDorsal(int pDorsal){
         dorsal=pDorsal;
    }
    /**
    * Asigna la medida
    * @param pMedida Medida que será asignada
    */
    public void setMedida(String pMedida){
         medida=pMedida;
    }
    /**
    * Aumenta en 1 la posicion del atleta en la prueba
    *
    */
    public void aumentarPosicion(){
        posicion++;
    }
    
    /**
    * Retorna la marca realizada
    * @return
    */
    public int getMarca(){
        return marca;
    }
    /**
    * Retorna la medida utilizada para medir la prueba
    * @return
    */
    public String getMedida(){
        return medida;
    }
    /**
    * Retorna la prueba en la que se realizó la marca
    * @return
    */
    public String getPrueba(){
        return prueba;
    }
    /**
    * Retorna el string necesario para utilizarlo en la muestra de marcas 
    * por competencia
    *@return
    */
    public String porCompetencia(){
        return "\n"+atleta+"  /  "+dorsal+"  /  "+marca+"  /  "+posicion+"\n";
    }
    /**
    * Retorna el string necesario para utilizarlo en la muestra de marcas 
    * por atleta
    *@return
    */
    public String porAtleta(){
        return "\n"+competencia+"  /  "+marca+"  /  "+posicion+"\n";
    }
    /**
    * Retorna el string necesario para utilizarlo en la muestra de marcas 
    * por Mejores marcas por prueba
    *@return
    */
    public String porMejorMarca(){
        return "\n"+prueba+"  /  "+marca+"  /  "+posicion+"  /  "+competencia+"\n";
    }
    /**
    * Retorna el string necesario para utilizarlo en la muestra de marcas 
    * por prueba
    *@return
    */
    public String porPrueba(){
        return "\n"+atleta+"  / "+paisOrigen+"  /  "+marca+"  /  "+posicion+"  /  "+competencia+"\n";
    }
    
}
