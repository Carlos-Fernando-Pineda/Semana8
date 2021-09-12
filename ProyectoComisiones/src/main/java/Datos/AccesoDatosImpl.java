/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Datos;

import Dominio.MdVendedor;
import Excepciones.*;
import java.io.*;
import java.util.*;

/**
 *
 * @author carlo
 */
public class AccesoDatosImpl implements AccesoDatos{
    @Override
    public boolean existe(String nombreArchivo) throws AccesoDatosEx {
        File archivo = new File(nombreArchivo);
        return archivo.exists();
    }
    
    @Override
    public List<MdVendedor> listar(String nombreArchivo) throws LecturaDatosEx {
          List<MdVendedor> vendedores = new ArrayList();
        try {
           BufferedReader entrada = null; 
            File archivo = new File(nombreArchivo);
           
            entrada = new BufferedReader(new FileReader(archivo));
            String linea = null;
            linea = entrada.readLine();
            while (linea != null){
                String[] DatosVendedores = linea.split("\\|");
                MdVendedor vendedor = new MdVendedor(DatosVendedores[0], Double.valueOf(DatosVendedores[1]), Double.valueOf(DatosVendedores[2]), Double.valueOf(DatosVendedores[3]));
                vendedores.add(vendedor);
                linea = entrada.readLine();
            }   
            entrada.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) { 
            ex.printStackTrace(System.out);
        }
        return vendedores;
    }
    
    
    @Override
    public void escribir(MdVendedor vendedor, String nombreArchivo,boolean anexar) throws EscrituraDatosEx{
        PrintWriter salida = null;
        try {
            File archivo = new File(nombreArchivo);
            salida = new PrintWriter(new FileWriter(archivo, anexar));
            salida.println(vendedor.toString());
            salida.close();
            System.out.println("Se ha escrito el registro en el archivo");
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        } finally {
            salida.close();
        }
    }
    
    
    @Override
    public String buscar(String nombreArchivo, Double buscar){
       BufferedReader entrada = null;
        String r = null;
        try {
            File archivo = new File(nombreArchivo);
            entrada = new BufferedReader(new FileReader(archivo));
            String linea = null;
            int x = 1;
            linea = entrada.readLine();
            while (linea != null){
                String DatosVendedores[] = linea.split("\\|");
                for(String Datos : DatosVendedores){
                    if(buscar != null && String.valueOf(buscar).equals(Datos)){
                        r = linea + "indice" + x;
                        break;
                    }
                }
                linea = entrada.readLine();
                x++;
            }
            entrada.close();
        } catch(FileNotFoundException ex){
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        } finally {
            try {
                entrada.close();
            } catch (IOException ex){
                ex.printStackTrace(System.out);
            }
        }
        return r;
    }

public void crear(String nombreArchivo){
    PrintWriter salida = null;
        try {
            File archivo = new File(nombreArchivo);
            salida = new PrintWriter(new FileWriter(archivo));
            salida.close();
          } catch (IOException ex) {
           ex.printStackTrace(System.out);
        } finally {
            salida.close();
        }
}
   
@Override
public void borrar(String nombreArchivo) throws AccesoDatosEx{
    File archivo = new File(nombreArchivo);
    archivo.delete();
    System.out.println("Se ha borrado el archivo");
}
    
    
}
