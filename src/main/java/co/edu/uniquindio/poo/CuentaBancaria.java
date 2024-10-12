package co.edu.uniquindio.poo;

public abstract class CuentaBancaria {
    private String nombreTitular;
    private String apellidoTitular;
    private long numeroCuenta;
    public double saldo;
    private boolean estaActiva;

    public CuentaBancaria(String nombreTitular, String apellidoTitular, long numeroCuenta,double saldo) {
        this.nombreTitular = nombreTitular;
        this.apellidoTitular = apellidoTitular;
        this.numeroCuenta = numeroCuenta;
        this.saldo=saldo;
        saldo = 0;
        estaActiva = false;
    }

    public String getNombreTitular() {
        return nombreTitular;
    }

    public void setNombreTitular(String nombreTitular) {
        this.nombreTitular = nombreTitular;
    }

    public String getApellidoTitular() {
        return apellidoTitular;
    }

    public void setApellidoTitular(String apellidoTitular) {
        this.apellidoTitular = apellidoTitular;
    }

    public long getNumeroCuenta() {
        return numeroCuenta;
    }

    public void setNumeroCuenta(long numeroCuenta) {
        this.numeroCuenta = numeroCuenta;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public boolean getEstaActiva() {
        return estaActiva;
    }

    public void setEstaActiva(boolean estaActiva) {
        this.estaActiva = estaActiva;
    }

    public void verificarActividad() {
        if (saldo == 0) {
            estaActiva = false;
        } else if (saldo > 0) {
            estaActiva = true;
        }
    }
}
