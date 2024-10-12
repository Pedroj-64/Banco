package co.edu.uniquindio.poo;

public class Titular extends Persona {
    private CuentaBancaria cuenta;

    public Titular(String nombre,String apellido,long telefono,int edad,long cedula){
        super(nombre,apellido,telefono,edad,cedula);
    }

    public CuentaBancaria getCuentaBancaria(){
        return cuenta;
    }

    public void setCuentaBancaria(CuentaBancaria cuenta){
        this.cuenta=cuenta;
    }


}
