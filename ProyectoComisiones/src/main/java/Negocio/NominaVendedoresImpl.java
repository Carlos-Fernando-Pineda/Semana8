/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import Datos.*;
import Dominio.MdVendedor;
import Excepciones.*;
import java.util.*;

/**
 *
 * @author carlo
 */
public class NominaVendedoresImpl implements NominaVendedores{
    
    private final AccesoDatos datos;
    
    public NominaVendedoresImpl(){
        this.datos = new AccesoDatosImpl(); //poner * en el import datos.*
    }
    
    @Override
    public void AgregarVendedor(String nombreArchivo, String nombreVendedor, Double enero, Double febrero, Double marzo) {
       MdVendedor vendedor = new MdVendedor(nombreVendedor, enero, febrero, marzo);
        boolean anexar = false;
        try {
            anexar = datos.existe(nombreArchivo);//bandera si anexa o no
            datos.escribir(vendedor, nombreArchivo, anexar);
            System.out.println("Registro exitoso!");
        } catch (AccesoDatosEx ex) {
            System.out.println("Error de acceso a datos");
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void listarVendedores(String nombreArchivo) {
        try {
            List<MdVendedor> vendedores = datos.listar(nombreArchivo);
            if(vendedores.size() > 0 ){
                vendedores.forEach(vendedor -> {
                    System.out.println(vendedor);
                });
            }else{
                System.out.println("No se encontraron registros");
            }
        } catch (LecturaDatosEx ex) {
            System.out.println("Error de acceso a datos");
            ex.printStackTrace(System.out);
        }
        
    }

    @Override
    public void MayorYMenorPorMes(String nombreArchivo, int mes){
        Double M = 0.0;
        Double m = 0.0;
        String N = null;
        String n = null;
        try {
            List<MdVendedor> vendedores = datos.listar(nombreArchivo);
            for (MdVendedor vendedor : vendedores){
                String[] DatosVendedores = vendedor.toString().split("\\|");
                if(Double.valueOf(DatosVendedores[mes]) > M){
                    M = Double.valueOf(DatosVendedores[mes]);
                    N = DatosVendedores[0];
                }
                if (Double.valueOf(DatosVendedores[mes]) < m){
                    m = Double.valueOf(DatosVendedores[mes]);
                    n = DatosVendedores[0];
                }
            }
            if (N != null && n != null){
                System.out.println("Mayor: "+ N + " con ventas de: "+ M);
                System.out.println("\nMenor: "+ n + " con ventas de: "+ m);
            }
        } catch (LecturaDatosEx ex){
            System.out.println("Error en la lectura de Datos");
            ex.printStackTrace(System.out);
        }
    }

    @Override
    public void iniciarArchivo(String nombreArchivo) {
        try {
            if(datos.existe(nombreArchivo)){
                datos.borrar(nombreArchivo);
                datos.crear(nombreArchivo);
            } else {
                //crearmos archivo
                datos.crear(nombreArchivo);
            }
        } catch (AccesoDatosEx ex) {
            System.out.println("Error de acceso a datos");
            ex.printStackTrace(System.out);
        }
    }  
    
     @Override
    public void MayorVendedor(String nombreArchivo){
        Double M = 0.0;
        String r = null;
        try {
            List<MdVendedor> vendedores = datos.listar(nombreArchivo);
            for(MdVendedor vendedor : vendedores){
                if(vendedor.getTotal() > M){
                    r = vendedor.getNombre() + " con ventas de: "+ vendedor.getTotal();
                }
            }
            System.out.println("Mayor Vendedor: "+ r);
        }catch(AccesoDatosEx ex){
            System.out.println("Error de acceso a datos");
            ex.printStackTrace(System.out);
        }
    }
    
     @Override
    public void PorCantidad(String nombreArchivo, Double cantidad){
        String r = null;
        try {
            r = datos.buscar(nombreArchivo, cantidad);
        }catch(LecturaDatosEx ex){
            System.out.println("Error de lectura a datos");
            ex.printStackTrace(System.out);
        }
        System.out.println("El resultado es: "+ r);
    }
    
    @Override
    public void Editar(String nombreArchivo, String DatoNuevo, int registro, int columna) {
        registro--;
        try {
            List<MdVendedor> vendedores = datos.listar(nombreArchivo);
            String nombre = vendedores.get(registro).getNombre();
            Double enero = vendedores.get(registro).getEnero();
            Double febrero = vendedores.get(registro).getFebrero();
            Double marzo = vendedores.get(registro).getMarzo();
            switch(columna){
                case 1:
                    nombre = DatoNuevo;
                    break;
                case 2:
                    enero = Double.valueOf(DatoNuevo);
                    break;
                case 3:
                    febrero = Double.valueOf(DatoNuevo);
                    break;
                case 4:
                    marzo = Double.valueOf(DatoNuevo);
                    break;
                default:
                    System.out.println("Esta columna no existe");
                    break;
            }
            MdVendedor vendedor = new MdVendedor(nombre, enero, febrero, marzo);
            vendedores.set(registro, vendedor);
            boolean anexar = false;
            for (MdVendedor linea : vendedores){
                datos.escribir(linea, nombreArchivo, anexar);
                anexar = datos.existe(nombreArchivo);
            }
        } catch(LecturaDatosEx ex){
            System.out.println("Error de Lectura a Datos");
            ex.printStackTrace(System.out);
        } catch(EscrituraDatosEx ex){
            System.out.println("Error de Escritura a Datos");
            ex.printStackTrace(System.out);
        } catch(AccesoDatosEx ex){
            System.out.println("Error de Acceso a Datos");
            ex.printStackTrace(System.out);
        } catch(NumberFormatException ex){
            System.out.println("Ha ocurrido un error, intente otro numero");
            ex.printStackTrace(System.out);
        }
    }
}

