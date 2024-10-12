package co.edu.uniquindio.poo;

public class CuentaCorriente extends CuentaBancaria {
    
    private int sobregiro;

    public CuentaCorriente(String nombreTitular,String apellidoTitular,long numeroCuenta,double saldo,int sobregiro){
        super(nombreTitular,apellidoTitular,numeroCuenta,saldo);
        this.sobregiro=sobregiro;
    }

    public int getSobregiro() {
        return sobregiro;
    }

    public void setSobregiro(int sobregiro) {
        this.sobregiro = sobregiro;
    }

}
