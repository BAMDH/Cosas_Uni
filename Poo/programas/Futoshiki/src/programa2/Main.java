/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programa2;

import futoshiki.Casilla;
import futoshiki.Futoshiki;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Usuario
 */
public class Main {
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SAXException, ParserConfigurationException, IOException {
        ArrayList<Futoshiki> partidas = new ArrayList<Futoshiki>();
        Futoshiki futo;
        try{
            File archivoXml = new File("futoshiki2022partidas.xml");
            DocumentBuilderFactory dbFactory =DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = dbFactory.newDocumentBuilder();
            Document documentoXml = builder.parse (archivoXml);
            documentoXml.getDocumentElement().normalize();
           
            NodeList listaPartidas = documentoXml.getElementsByTagName ("partida");
        
            for (int i=0;i<listaPartidas.getLength();i++){
                futo= new Futoshiki();
                for (int y=0;y<5;y++){
                   for (int j=0;j<5;j++){
                   
                    futo.getArray()[y][j].setValor(0);
                    }
                }
                
                
                String provisional;
                Casilla [][]Aux=futo.getArray();
                Node programa = listaPartidas.item(i);
                
                if (programa.getNodeType()==Node.ELEMENT_NODE){
                    Element element = (Element)programa;
                    NodeList constantes = element.getElementsByTagName("constantes");
                    for (int j=0;j<constantes.getLength();j++){
                        provisional= element.getElementsByTagName("constantes").item(j).getTextContent();
                        Aux[Integer.parseInt(provisional.charAt(1)+"")][Integer.parseInt(provisional.charAt(2)+"")].setConstante(Integer.parseInt(provisional.charAt(0)+""));
                    }
                    NodeList desigualdades = element.getElementsByTagName("desigualdades");
                    for (int j=0;j<desigualdades.getLength();j++){
                       provisional= element.getElementsByTagName("desigualdades").item(j).getTextContent();
                        Aux[Integer.parseInt(provisional.charAt(1)+"")][Integer.parseInt(provisional.charAt(2)+"")].setCondicion(provisional.charAt(0)+"");
                    }
                }
            partidas.add(futo);
            }
        }
        catch (IOException | ParserConfigurationException | SAXException e){
            System.err.println("Error: "+e.getMessage());
        }
        Interfaces interfaz = new Interfaces();
        interfaz.setVisible(true);
        interfaz.setGame(partidas);
        
    }
}
