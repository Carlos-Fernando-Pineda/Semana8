/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Principal;

import Datos.ClsVendedoresJDBC;
import Dominio.ClsVendedor;
import Dominio.MdVendedor;
import Negocio.*;
import java.util.*;

/**
 *
 * @author carlo
 */
public class ClsPrincipal {
    boolean exit;
    private static final Scanner scanner = new Scanner(System.in);
    private static final String nombreArchivo = "C:\\tmp\\prograII\\vendedores.txt";
    private static final NominaVendedores vendedores = new NominaVendedoresImpl();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ClsPrincipal menu = new ClsPrincipal();
        menu.correrMenu();
    }
    
        public void correrMenu(){
        while(!exit){
        System.out.println(" Haga una seleccion: ");
        System.out.println("1) Iniciar archivo.");
        System.out.println("2) Agregar empleado. ");
        System.out.println("3) Mayor y Menor Vendedor por Mes. ");
        System.out.println("4) Mayor Vendedor. ");
        System.out.println("5) Editar. ");
        System.out.println("6) Buscar por Cantidad. ");
        System.out.println("7) Listar informacion. ");
        System.out.println("8) Editar en base de datos.");
        System.out.println("9) Borrar en base de datos.");
        System.out.println("0) Salir. ");
            int op = getInput();
            realizarAccion(op);
        }
    }
    private int getInput(){
        Scanner kb = new Scanner(System.in);
        int op = -1;
        while(op < 0 || op > 9){
            try {
                op = Integer.parseInt(kb.nextLine());
            }
            catch(NumberFormatException e){
                System.out.println("Seleccion Invalida. Intente de nuevo.");
            }
        }
        return op;
    }
    
    private void realizarAccion(int op){
        switch(op){
            case 0:
                exit = true;
                System.out.println("Gracias! tenga un buen dia.");
                break;
            case 1:
                vendedores.iniciarArchivo(nombreArchivo);
                break;
            case 2:
                Ingreso_Informacion();
                break;
            case 3:
                MayorYMenor();
                break;
            case 4:
                vendedores.MayorVendedor(nombreArchivo);
                break;
            case 5:
                Editar();
                break;
            case 6:
                BuscarPorCantidad();
                break;
            case 7:
                vendedores.listarVendedores(nombreArchivo);
                break;
            case 8:
                Editarbd();
                break;
            case 9:
                borrarbd();
                break;
            default:
                System.out.println("Un error ha ocurrido. ");
                break;
        }
    }
    
    private void Ingreso_Informacion(){
      String nombre;
      Double enero, febrero, marzo;
      ClsVendedoresJDBC VendeJDBC = new ClsVendedoresJDBC();
      ClsVendedor nuevo = new ClsVendedor();
      System.out.println("Ingrese el nombre: ");
      nombre = scanner.nextLine();
      nuevo.setNombre(nombre);
      System.out.println("Ingrese enero: ");
      enero = scanner.nextDouble();
      nuevo.setEnero(enero);
      System.out.println("Ingrese febrero: ");
      febrero = scanner.nextDouble();
      nuevo.setFebrero(febrero);
      System.out.println("Ingrese Marzo: ");
      marzo = scanner.nextDouble();
      nuevo.setMarzo(marzo);
      VendeJDBC.insert(nuevo);
      vendedores.AgregarVendedor(nombreArchivo, nombre, enero, febrero, marzo);
      scanner.nextLine();
    }
    private void MayorYMenor(){
        int mes;
        System.out.println("Ingrese el mes: ");
        System.out.println("\n1) Enero. \n2) Febrero. \n3) Marzo.");
        mes = Integer.parseInt(scanner.nextLine());
        vendedores.MayorYMenorPorMes(nombreArchivo, mes);
    }
    private void Editar(){
        String nuevo;
        int registro, columna;
        System.out.println("Ingrese el no. de registro: ");
        registro = Integer.parseInt(scanner.nextLine());
        System.out.println("¿Que dato desea editar?");
        System.out.println("\n1) Nombre. \n2)Enero. \n3)Febrero. \n4)Marzo.");
        columna = Integer.parseInt(scanner.nextLine());
        System.out.println("Ingrese su nuevo dato: ");
        nuevo = scanner.nextLine();
        vendedores.Editar(nombreArchivo, nuevo, registro, columna);
    }
    private void Editarbd(){
        String nombre;
        Double enero, febrero, marzo;
        ClsVendedoresJDBC vendeJDBC = new ClsVendedoresJDBC();
        ClsVendedor nuevo = new ClsVendedor();
        System.out.println("Ingrese el Codigo id del registro a modificar: ");
        nuevo.setCodigo(scanner.nextInt());
        System.out.println("Ingrese el nuevo nombre: ");
        nombre = scanner.nextLine();
        nuevo.setNombre(nombre);
        System.out.println("Ingrese la nueva cantidad de Enero: ");
        enero = scanner.nextDouble();
        nuevo.setEnero(enero);
        System.out.println("Ingrese la nueva cantidad de Febrero: ");
        febrero = scanner.nextDouble();
        nuevo.setFebrero(febrero);
        System.out.println("Ingrese la nueva cantidad de Marzo: ");
        marzo = scanner.nextDouble();
        nuevo.setMarzo(marzo);
        vendeJDBC.actualizar(nuevo);
        System.out.println("Actualizacion exitosa.\n");
    }
    private void borrarbd(){
        ClsVendedoresJDBC vendeJDBC = new ClsVendedoresJDBC();
        ClsVendedor nuevo = new ClsVendedor();
        System.out.println("Ingrese el codigo del registro a borrar: ");
        nuevo.setCodigo(scanner.nextInt());
        vendeJDBC.borrar(nuevo);
        System.out.println("El registro ha sido borrado.\n");
    }
    private void BuscarPorCantidad(){
        Double numero;
        System.out.println("Ingrese la cantidad: ");
        numero = scanner.nextDouble();
        scanner.nextLine();
        vendedores.PorCantidad(nombreArchivo, numero);
    }
}
