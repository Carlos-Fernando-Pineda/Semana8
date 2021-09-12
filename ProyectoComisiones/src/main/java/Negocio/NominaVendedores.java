/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

/**
 *
 * @author carlo
 */
public interface NominaVendedores {
    public void AgregarVendedor(String nombreArchivo, String nombreVendedor, Double enero, Double febrero, Double marzo);
    public void MayorYMenorPorMes(String nombreArchivo, int mes);
    public void MayorVendedor(String nombreArchivo);
    public void listarVendedores(String nombreArchivo);
    public void Editar(String nombreArchivo, String DatoNuevo, int registro, int columna);
    public void PorCantidad(String nombreArchivo, Double Cantidad);
    public void iniciarArchivo(String nombreArchivo);
}
