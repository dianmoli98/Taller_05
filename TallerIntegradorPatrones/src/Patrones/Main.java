/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patrones;

import Adapter.CuentaAdapter;
import ChainOfResponsibility.Manejador;
import ChainOfResponsibility.ManejadorDinero;
import Singleton.AtmEC;
import java.util.Currency;
import java.util.Locale;
import java.util.Scanner;

public class Main
{;
    public static void main(String[] args)
    {
        // Crear un único cajero Automático de dólares con 100 billetes de 20, 100 de 10, 
        // 10 monedas de 0.50, 10 de 0.25 y 1000 de 0.05
        Manejador manejador20=new ManejadorDinero(100,20.0);
        Manejador manejador10=new ManejadorDinero(100,10.0);
        Manejador moneda050=new ManejadorDinero(10,0.50);
        Manejador moneda025=new ManejadorDinero(10,0.25);
        Manejador moneda005= new ManejadorDinero(1000,0.05);
        AtmEC atm=AtmEC.getInstancia();
        atm.addManejador(moneda005);
        atm.addManejador(moneda025);
        atm.addManejador(moneda050);
        atm.addManejador(manejador20);
        atm.addManejador(manejador10);
        CuentaAdapter ca = new CuentaAdapter(2, 100);
        
        // Crear 10 cuentas nuevas en dólares locale.US con un saldo inicial entre 100.00 y 1000.00 USD cada una.
        CuentaAdapter cuenta1 = new CuentaAdapter(1, 100);
        CuentaAdapter cuenta2 =new CuentaAdapter(2, 660);
        CuentaAdapter cuenta3 = new CuentaAdapter(3, 300);
        CuentaAdapter cuenta4 =new CuentaAdapter(4, 250);
        CuentaAdapter cuenta5 = new CuentaAdapter(5, 360);
        CuentaAdapter cuenta6 =new CuentaAdapter(6, 450);
        CuentaAdapter cuenta7 =new CuentaAdapter(7, 560);
        CuentaAdapter cuenta8 = new CuentaAdapter(8, 200);
        CuentaAdapter cuenta9 =new CuentaAdapter(9, 850);
        CuentaAdapter cuenta10 = new CuentaAdapter(10, 320);
        
        // Mostrar el menú para realizar transacciones en el cajero automático */
        AtmEC.transaction(ca);
        System.out.println();
        
        
        // Menú principal para seleccionar una de las 10 cuentas solo con el id
        Scanner sc = new Scanner(System.in);
        System.out.println("Ingrese el id de su cuenta:");
        int ingreso=sc.nextInt();
        System.out.println(ca.Balance());
        ca.Retirar(10);
        System.out.println(ca.Balance());
        System.out.println(ca.Depositar(2, 100));
        System.out.println(ca.Balance());
        
    }

    
}
