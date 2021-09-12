/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dominio;

/**
 *
 * @author carlo
 */
public class MdVendedor {
    
    private String nombre;
    private Double enero;
    private Double febrero;
    private Double marzo;
    private final Double total;
    private final Double promedio;
    
    public MdVendedor(String nombre, Double enero, Double febrero, Double marzo){
        this.nombre = nombre;
        this.enero = enero;
        this.febrero = febrero;
        this.marzo = marzo;
        this.total = enero + febrero + marzo;
        this.promedio = (enero + febrero + marzo)/3;
    }
    
     @Override
    public String toString(){
        return  this.getNombre() + "|" + this.getEnero() + "|" + this.getFebrero() + "|" + this.getMarzo() + "|" 
                + this.getTotal() + "|" + this.getPromedio();
    }

    /**
     * @return the nombre
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * @return the enero
     */
    public Double getEnero() {
        return enero;
    }

    /**
     * @return the febrero
     */
    public Double getFebrero() {
        return febrero;
    }

    /**
     * @return the marzo
     */
    public Double getMarzo() {
        return marzo;
    }

    /**
     * @return the promedio
     */
    public Double getPromedio() {
        return promedio;
    }
    
    public Double getTotal(){
        return total;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    /**
     * @param enero the enero to set
     */
    public void setEnero(Double enero) {
        this.enero = enero;
    }

    /**
     * @param febrero the febrero to set
     */
    public void setFebrero(Double febrero) {
        this.febrero = febrero;
    }

    /**
     * @param marzo the marzo to set
     */
    public void setMarzo(Double marzo) {
        this.marzo = marzo;
    }
   
}
