/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Patrones;

import Adapter.CuentaAdapter;
import ChainOfResponsibility.Manejador;
import Singleton.AtmEC;
import java.util.Currency;
import java.util.Locale;

public class Main
{;
    public static void main(String[] args)
    {
        Manejador manejador20=new Manejador(100,20);
        Manejador manejador10=new Manejador(100,10);
        Manejador moneda050=new Manejador(10,0.50);
        Manejador moneda025=new Manejador(10,0.25);
        Manejador moneda005= new Manejador(1000,0.05);
        AtmEC atm=AtmEC.getInstancia();
        atm.addManejador(moneda005);
        atm.addManejador(moneda025);
        atm.addManejador(moneda050);
        atm.addManejador(manejador20);
        atm.addManejador(manejador10);
        CuentaAdapter ca = new CuentaAdapter(2, 100);
        /*
        
        System.out.println(ca.Balance());
        ca.Retirar(10);
        System.out.println(ca.Balance());
        System.out.println(ca.Depositar(2, 100));
        System.out.println(ca.Balance());
        // Crear un único cajero Automático de dólares con 100 billetes de 20, 100 de 10, 
        // 10 monedas de 0.50, 10 de 0.25 y 1000 de 0.05

        // Crear 10 cuentas nuevas en dólares locale.US con un saldo inicial entre 100.00 y 1000.00 USD cada una.
        
        // Menú principal para seleccionar una de las 10 cuentas solo con el id
        
        // Mostrar el menú para realizar transacciones en el cajero automático */
        AtmEC.transaction(ca);
        System.out.println();
    }

    
}
