package com.example.ejecicios;

/**
 * Clase simple para realizar cálculos acumulados.
 *
 * @author Javier
 */
public class Calculadora {

    /**
     * Lista de las operaciones soportadas
     */
    public static final String OPERACIONES_SOPORTADAS = "+-*/=%";
    private double acumulado = 0;
    private char operacion = '+';

    /**
     * Constructor por defecto
     */
    public Calculadora() {
        inicializa();
    }

    /**
     * Restaura el valor inicial para calcular la siguiente secuencia
     */
    public void inicializa() {
        acumulado = 0;
        operacion = '+';
    }

    /**
     * Obtiene el total acumulado hasta el momento.
     * @return Valor acumulado
     */
    public double getAcumulado() {
        return acumulado;
    }

    /**
     * Comprueba que sea una de las operaciones soportadas por la calculadora.
     * @param operacion Símbolo de la operación
     * @return true si la soporta, false en el resto de los casos.
     */
    public boolean isOperacion(char operacion) {
        return OPERACIONES_SOPORTADAS.indexOf(operacion) != -1;
    }
    /**
     * Realiza la operación pendiente una vez obtenido el segundo operador y 
     * guarda la nueva operación pendiente
     * @param operando2 segundo operador
     * @param nuevaOperacion la nueva operación pendiente
     * @return el total acumulado hasta el momento
     * @throws Exception Cuando el símbolo de operación no esta soportado
     */
    public double calcula(double operando2, char nuevaOperacion) throws CalculadoraException{
        if (!isOperacion(nuevaOperacion)) {
            throw new CalculadoraException("Operación no soportada.");
        }
        switch (operacion) {
            case '+':
                acumulado += operando2;
                break;
            case '-':
                acumulado -= operando2;
                break;
            case '*':
                acumulado *= operando2;
                break;
            case '/':
                acumulado /= operando2;
                break;
            case '%':
                acumulado %= operando2;
                break;
            case '=':
                acumulado += operando2;
                break;
            default:
                throw new CalculadoraException("Operación no soportada.");
        }
        this.operacion = nuevaOperacion;
        return acumulado;
    }

    /**
     * Sobrecarga
     * @see Calculadora#calcula(double, char) 
     * @param operando2 segundo operador
     * @param nuevaOperacion la nueva operación pendiente
     * @return el total acumulado hasta el momento
     * @throws Exception Cuando el símbolo de operación no esta soportado
     */
    public double calcula(String operando2, char nuevaOperacion) throws CalculadoraException {
        if (operando2.endsWith(",")) {
            operando2 += "0";
        }
        try {
            return calcula(
                    Double.parseDouble(operando2.replace(',', '.')),
                    nuevaOperacion);
        } catch (NumberFormatException ex) {
            throw new CalculadoraException(
                    "El operando no tienen un formato númerico valido.", 
                    ex);
        }
    }
}

