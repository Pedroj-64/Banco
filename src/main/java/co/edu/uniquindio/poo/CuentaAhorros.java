package co.edu.uniquindio.poo;

public class CuentaAhorros extends CuentaBancaria {
    private double interes;

    public CuentaAhorros(String nombreTitular,String ApellidoTitular,long numeroCuenta,double saldo,double interes){
        super(nombreTitular,ApellidoTitular,numeroCuenta,saldo);
        this.interes=interes;
    }

    public void setInteres(double interes){
        this.interes=interes;
    }

    public double getInteres(){
        return interes;
    }

    public void calcularInteres(){
        double ganancia=saldo*interes;
        setSaldo(getSaldo()+ganancia);
    }
}
