/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Singleton;

import Adapter.Cuenta;
import Adapter.CuentaAdapter;
import ChainOfResponsibility.Manejador;
import Patrones.Account;
import java.util.ArrayList;
import java.util.Currency;
import java.util.Locale;
import java.util.Scanner;

public class AtmEC {
    private static AtmEC instance=null;
    private static Currency moneda=null;
    private static double dinero = 3057.5;
    private Manejador manejador;
    private Cuenta cuenta;
    static Scanner in;
    protected static ArrayList <Manejador> manejadores;
    
    // -----------------
    private AtmEC() {
        
    }
    
    public static AtmEC getInstancia()
    {
         if (instance == null) {
             instance = new AtmEC();
             moneda= Currency.getInstance(Locale.US);
             manejadores=new ArrayList<>();
            System.out.println("Su cajero ha sido creado");
        }
        else {
            System.out.println("Ya existe el cajero");
        }
        return instance;
    }

    // -----------------
    public boolean sacarDinero(double dinero) { 
        AtmEC.dinero -= dinero;
        //ver esto.
        return manejador.retirar(dinero);
        
    }
    
    
    // -----------------
    public double getTotal() {
        return AtmEC.dinero;
    }

    

    // -----------------
    public void ingresarDinero(double dinero, int denominacion) {
        AtmEC.dinero += dinero;
        //ver esto
        manejador.depositar(denominacion, (int) dinero);
        // Todo: Sólo se puede depositar billetes de una sola denominación y agregarse al manejador correspondiente
    
    }

    public void addManejador(Manejador m){
        manejadores.add(m);
    }
    public Manejador removeManejador(int i){
        return manejadores.remove(i);
    }
    
    

    //Dentro de las transacciones se debe llamar al ATM para hacer el retiro o deposito de la cuenta correspondiente
    public static void transaction(CuentaAdapter cuenta){
        // here is where most of the work is
        in=new Scanner(System.in);
        int choice; 
        System.out.println("Please select an option"); 
        System.out.println("1. Withdraw");
        System.out.println("2. Deposit");
        System.out.println("3. Balance");
        System.out.println("4. Balance ATM");
        choice= in.nextInt();
        switch(choice){
            case 1:
                float amount; 
                System.out.println("Please enter amount to withdraw: "); 
                amount = in.nextFloat();
                if(amount > cuenta.getCuenta().getAmount() || amount == 0){
                    System.out.println("You have insufficient funds\n\n"); 
                    anotherTransaction(cuenta); // ask if they want another transaction
                } else {
                    if(dinero>=amount){
                        cuenta.getCuenta().withdraw(dinero);
                        instance.sacarDinero(amount);
                        anotherTransaction(cuenta); 
                    }
                    // Todo: verificar que se puede realizar el retiro del atm
                    // Todo: actualizar tanto la cuenta como el atm y de los manejadores
                    // Todo: Mostrar resumen de transacción o error
                    System.out.println("You have withdrawn "+amount+" and your new balance is "+cuenta.Balance());
                }
            break; 
            case 2:
                    // option number 2 is depositing 
                    float deposit; 
                    System.out.println("Please enter amount you would wish to deposit: "); 
                    deposit = in.nextFloat();
                    System.out.println("Please enter the denomintion dollars you'd wish deposit it: ");
                    int denomination;
                    denomination=in.nextInt();
                    for(Manejador manejador: manejadores){
                        if(manejador.getDenominacion()==denomination){
                           instance.ingresarDinero(deposit, denomination);
                            cuenta.getCuenta().deposit(deposit);
                            System.out.println("You have withdrawn "+deposit+" and your new balance is "+cuenta.Balance());
                            anotherTransaction(cuenta); 
                        }   
                    }
                    // Todo: Mostrar resumen de transacción o error
                    // Todo: actualizar tanto la cuenta como el atm
            break; 
            case 3:
                    // Todo: mostrar el balance de la cuenta
                    System.out.println("Your balance is "+cuenta.Balance());
                    anotherTransaction(cuenta); 
            break;
            case 4:
                    // Todo: mostrar el balance del ATM con los billetes en cada manejador
                    System.out.println("The ATM's balance according with our system is :" +instance.getTotal());
                    anotherTransaction(cuenta); 
            break;
            default:
                    System.out.println("Invalid option:\n\n"); 
                    anotherTransaction(cuenta);
            break;
        }
    }
    public static void anotherTransaction(CuentaAdapter cuenta){
        System.out.println("Do you want another transaction?\n\n Press 1 for another transaction\n2 To exit");
        int anotherTransaction = in.nextInt();
        switch (anotherTransaction) {
            case 1:
                transaction(cuenta); // call transaction method
                break;
            case 2:
                System.out.println("Thanks for choosing us. Good Bye!");
                break;
            default:
                System.out.println("Invalid choice\n\n");
                anotherTransaction(cuenta);
                break;
        }
    }

    
}