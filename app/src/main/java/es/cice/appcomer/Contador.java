package es.cice.appcomer;

/**
 * Created by usuario on 06/05/2017.
 * NO SE USA
 */

public class Contador {
    private String identificador;
    private Integer valor;
    private int longitud;
    private int incremento;

    public Contador(String identificador, Integer valor, int longitud, int incremento) {
        this.identificador = identificador;
        this.valor = valor;
        this.longitud = longitud;
        this.incremento = incremento;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public Integer getValor() {
        return valor;
    }

    public void setValor(Integer valor) {
        this.valor = valor;
    }

    public int getLongitud() {
        return longitud;
    }

    public void setLongitud(int longitud) {
        this.longitud = longitud;
    }

    public int getIncremento() {
        return incremento;
    }

    public void setIncremento(int incremento) {
        this.incremento = incremento;
    }

    public String obtenerContador() {
        Integer valorActual = getValor();
        setValor(getValor() + incremento);
        String repeated = new String(new char[getLongitud()]).replace("\0", "0") + valorActual.toString();
        return getIdentificador() + repeated.substring(repeated.length() - getLongitud());
    }
}
