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
public class ClsVendedor {
    private int codigo;
    private String nombre;
    private Double enero;
    private Double febrero;
    private Double marzo;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getEnero() {
        return enero;
    }

    public void setEnero(Double enero) {
        this.enero = enero;
    }

    public Double getFebrero() {
        return febrero;
    }

    public void setFebrero(Double febrero) {
        this.febrero = febrero;
    }

    public Double getMarzo() {
        return marzo;
    }

    public void setMarzo(Double marzo) {
        this.marzo = marzo;
    }

    @Override
    public String toString() {
        return "ClsVendedor{" + "codigo=" + codigo + ", nombre=" + nombre + ", enero=" + enero + ", febrero=" + febrero + ", marzo=" + marzo + '}';
    }
}
