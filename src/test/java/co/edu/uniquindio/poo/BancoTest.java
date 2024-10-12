package co.edu.uniquindio.poo;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.logging.*;

import org.junit.jupiter.api.Test;;

public class BancoTest {
    
    private Logger LOG=Logger.getLogger(BancoTest.class.getName());


    @Test
    public void depositarMontoNegativoTest(){
        LOG.info("Inicializando prueba del deposito de mondo en cuentas de ahorros y corrientes");
        Titular titular= new Titular("Pepito", "Alcachofa", 43232, 78, 1938403L);
        Banco banco=new Banco("Banco Bogota");
        banco.crearCuentaAhorros(titular, 492023, 0, 0.20);
        assertThrows(Throwable.class,() -> banco.depositarMonto(492023, -40832));


    }
    @Test
    public void depositarMontoTest(){
        LOG.info("Inicializando prueba del deposito de mondo en cuentas de ahorros y corrientes");
        Titular titular= new Titular("Pepito", "Alcachofa", 43232, 78, 1938403L);
        Banco banco=new Banco("Banco Bogota");
        banco.crearCuentaAhorros(titular, 492023, 0, 0.20);
        banco.depositarMonto(492023, 2039202);
        CuentaBancaria cuenta=banco.buscarCuenta(492023);
        boolean verificar= 2039202==cuenta.getSaldo();
        assertTrue(verificar,"mala mia");


    }

    @Test
    public void retirarDinero(){
        LOG.info("Inicilizando prueba para la verificacion de retirar dinero en cuenta ahorros y corriente");
        Titular titular= new Titular("Pepito", "Alcachofa", 43232, 78, 1938403L);
        Banco banco=new Banco("Banco Bogota");
        banco.crearCuentaCorriente(titular, 4920, 0, 5000);
        banco.depositarMonto(4920, 49000);
        assertThrows(Exception.class,() -> banco.retirarMontoCorriente(4920, 55000));
        
    }
}
