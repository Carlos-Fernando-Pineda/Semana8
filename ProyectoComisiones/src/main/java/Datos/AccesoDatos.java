/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Dominio.MdVendedor;
import Excepciones.*;
import java.util.*;

/**
 *
 * @author carlo
 */
public interface AccesoDatos {
    boolean existe(String nombreArchivo) throws AccesoDatosEx;
    public List<MdVendedor> listar(String nombreArchivo) throws LecturaDatosEx;
    void escribir(MdVendedor vendedor, String nombreArchivo, boolean anexar) throws EscrituraDatosEx;
    public String buscar(String nombreArchivo, Double buscar) throws LecturaDatosEx;
    public void crear (String nombreArchivo) throws AccesoDatosEx;
    public void borrar (String nombreArchivo) throws AccesoDatosEx;
}
