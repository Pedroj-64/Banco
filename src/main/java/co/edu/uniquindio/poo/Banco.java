package co.edu.uniquindio.poo;

import java.util.Collection;
import java.util.LinkedList;

public class Banco {
    private String nombre;
    private Collection<CuentaBancaria> cuentasBancarias;
    private Collection<CuentaAhorros> cuentasAhorros;
    private Collection<CuentaCorriente> cuentasCorrientes;

    public Banco(String nombre) {
        this.nombre = nombre;
        cuentasBancarias = new LinkedList<>();
        cuentasAhorros = new LinkedList<>();
        cuentasCorrientes = new LinkedList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Collection<CuentaBancaria> getCuentasBancarias() {
        return cuentasBancarias;
    }

    public void setCuentasBancarias(Collection<CuentaBancaria> cuentasBancarias) {
        this.cuentasBancarias = cuentasBancarias;
    }

    public Collection<CuentaAhorros> getCuentasAhorros() {
        return cuentasAhorros;
    }

    public void setCuentasAhorros(Collection<CuentaAhorros> cuentasAhorros) {
        this.cuentasAhorros = cuentasAhorros;
    }

    public Collection<CuentaCorriente> getCuentasCorrientes() {
        return cuentasCorrientes;
    }

    public void setCuentasCorrientes(Collection<CuentaCorriente> cuentasCorrientes) {
        this.cuentasCorrientes = cuentasCorrientes;
    }

    public boolean existeCuenta(long numeroCuenta) {
        boolean banderilla = false;
        for (CuentaBancaria cuenta : cuentasBancarias) {
            if (cuenta.getNumeroCuenta() == numeroCuenta) {
                banderilla = true;
                break;
            }
        }
        return banderilla;
    }

    public void crearCuentaAhorros(Titular titular, int numeroCuenta, double saldoInicial, double interes) {
        CuentaAhorros cuenta = new CuentaAhorros(titular.getNombre(), titular.getApellido(), numeroCuenta, saldoInicial,
                interes);
        if (!existeCuenta(cuenta.getNumeroCuenta())) {
            cuentasBancarias.add(cuenta);
            cuentasAhorros.add(cuenta);

        } else if (existeCuenta(numeroCuenta) == true) {
            System.out.println("La cuenta ya existe en la base de datos");
        }
    }

    public void crearCuentaCorriente(Titular titular, long numeroCuenta, long saldoInicial, int sobregiro) {
        CuentaCorriente cuenta = new CuentaCorriente(titular.getNombre(), titular.getApellido(), numeroCuenta,
                saldoInicial, sobregiro);
        if (!existeCuenta(cuenta.getNumeroCuenta())) {
            cuentasBancarias.add(cuenta);
            cuentasCorrientes.add(cuenta);

        } else if (existeCuenta(numeroCuenta) == true) {
            System.out.println("La cuenta ya existe en la base de datos");
        }
    }

    public void eliminarCuenta(long numeroCuenta) {
        for (CuentaBancaria cuenta : cuentasBancarias) {
            if (cuenta.getNumeroCuenta() == numeroCuenta) {
                cuentasBancarias.remove(cuenta);
                System.out.println("La cuenta fue eliminada exitosamente");
                break;
            }
        }
    }

    public void depositarMonto(long numeroCuenta, int monto) {
        if (monto > 0) {
            for (CuentaBancaria cuenta : cuentasBancarias) {

                if (cuenta.getNumeroCuenta() == numeroCuenta) {
                    double saldoActual = cuenta.getSaldo();
                    saldoActual += monto;
                    cuenta.setSaldo(saldoActual);
                }

            }
        }else{
            System.exit(1);
        }

    }

    public CuentaBancaria buscarCuenta(int numeroCuenta){
        CuentaBancaria cuenta=null;
        for(CuentaBancaria cuentaBancaria : cuentasBancarias){
            if(numeroCuenta==cuentaBancaria.getNumeroCuenta()){
                cuenta=cuentaBancaria;
            }
        }
        return cuenta;
    }

    public void retirarMontoComplejo(long numeroCuenta, int monto) throws Exception {
        for (CuentaBancaria cuenta : cuentasBancarias) {
            for (CuentaAhorros cuentaAhorros : cuentasAhorros) {
                for (CuentaCorriente cuentaCorriente : cuentasCorrientes) {
                    if (cuenta.getNumeroCuenta() == numeroCuenta && cuentaAhorros.equals(cuenta)) {
                        if (monto <= cuentaAhorros.getSaldo()) {
                            double saldoActual = cuentaAhorros.getSaldo();
                            saldoActual -= monto;
                            cuentaAhorros.setSaldo(saldoActual); // Actualizar el saldo
                            break;
                        } else {
                            throw new Exception("Saldo insuficiente en la cuenta de ahorros");
                        }
                    } else if (cuenta.getNumeroCuenta() == numeroCuenta && cuenta.equals(cuentaCorriente)) {
                        if (monto <= cuentaCorriente.getSaldo()) {
                            double saldoActual = cuentaCorriente.getSaldo();
                            saldoActual -= monto;
                            cuentaCorriente.setSaldo(saldoActual); // Actualizar el saldo
                            break;
                        } else if (monto > cuentaCorriente.getSaldo()) {
                            double saldoAntiguo = cuentaCorriente.getSaldo();
                            double saldoNuevo = cuentaCorriente.getSaldo() + cuentaCorriente.getSobregiro();
                            cuentaCorriente.setSaldo(saldoNuevo);
                            double saldoActual = cuentaCorriente.getSaldo();
                            double retiro = saldoActual - monto;
                            if (retiro < 0) {
                                cuentaCorriente.setSaldo(saldoAntiguo);
                                throw new Exception("Saldo insuficiente");
                            }
                            if (retiro >= 0) { // Asegurarse de que no sea negativo
                                cuentaCorriente.setSaldo(saldoActual - monto); // Actualizar el saldo
                            }
                            break;
                        }
                    }
                }
            }
        }
    }
    
    public void retirarMontoAhorros(Long numeroCuenta, int monto) throws Exception{
        for(CuentaAhorros cuentaAhorros: cuentasAhorros){
            if(cuentaAhorros.getNumeroCuenta()==numeroCuenta){
                if(monto<cuentaAhorros.getSaldo()){
                    cuentaAhorros.setSaldo(cuentaAhorros.getSaldo()-monto);
                }else{
                    throw  new Exception("Saldo insuficiente en la cuenta");
                }
                    
                
            }
        }
    }
    public void retirarMontoCorriente(long numeroCuenta, int monto) throws  Exception{
        for(CuentaCorriente cuentaCorriente : cuentasCorrientes){
            if(cuentaCorriente.getNumeroCuenta()==numeroCuenta){
                if(monto<cuentaCorriente.getSaldo()){
                    cuentaCorriente.setSaldo(cuentaCorriente.getSaldo()-monto);
                }else if(monto>cuentaCorriente.getSaldo()){
                    double saldoProvisional=cuentaCorriente.getSaldo()+cuentaCorriente.getSobregiro();
                    double retiro= saldoProvisional-monto;
                    if(retiro<0){
                        throw new Exception("El saldo con sobregiro aun es insuficiente");
                    }else if (retiro>0){
                        cuentaCorriente.setSaldo(cuentaCorriente.getSaldo()-monto);
                    }
                }

            }
        }
    }

    public void ajustesCuentas(){
        for(CuentaBancaria cuenta: cuentasBancarias){
            cuenta.verificarActividad();
        }
        for(CuentaAhorros cuentaAhorros: cuentasAhorros){
            cuentaAhorros.calcularInteres();
        }
    }

}
