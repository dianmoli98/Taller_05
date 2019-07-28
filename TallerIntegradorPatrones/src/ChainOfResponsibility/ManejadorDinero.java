package ChainOfResponsibility;


public class ManejadorDinero implements Manejador
{
    private Manejador next;
    protected int cantidad;
    protected double denominacion;

    public ManejadorDinero(int cantidad, double denominacion) {
        this.cantidad = cantidad; // Total de billetes
        this.denominacion = denominacion; // Valor de cada billete
    }

    @Override
    public int getCantidad() {
        return this.cantidad;
    }

    @Override
    public double getDenominacion() {
        return this.denominacion;
    }

    @Override
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public boolean retirar(double monto) {
       return false;
    }

    @Override
    public boolean depositar(int cantidad, int denominacion) {
        return false;
    }

    @Override
    public void setNext(ChainOfResponsibility.Manejador m) {
        this.next = m;
    }

}