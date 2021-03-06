package es.cice.appcomer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cice on 5/5/17.
 */

public class Comida {
    private String nombre;
    private String restaurante;
    private String ubicacion;
    private String fechaHora;
    private String id;
    private List<Usuario> listaUsuarios;

    public Comida() {
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public Comida(String nombre, String restaurante, String ubicacion, String fechaHora, String id) {
        this.nombre = nombre;
        this.restaurante = restaurante;
        this.ubicacion = ubicacion;
        this.fechaHora = fechaHora;
        this.id = id;
        this.listaUsuarios = new ArrayList<Usuario>();
    }

    public Comida(String nombre, String restaurante, String ubicacion, String fechaHora, String id, List<Usuario> listaUsuarios) {
        this.nombre = nombre;
        this.restaurante = restaurante;
        this.ubicacion = ubicacion;
        this.fechaHora = fechaHora;
        this.id = id;
        this.listaUsuarios = listaUsuarios;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getRestaurante() {
        return restaurante;
    }

    public void setRestaurante(String restaurante) {
        this.restaurante = restaurante;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
    }

    @Override
    public String toString() {
        return "Comida{" +
                "nombre='" + nombre + '\'' +
                ", restaurante='" + restaurante + '\'' +
                ", fechaHora='" + fechaHora + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comida comida = (Comida) o;

        if (nombre != null ? !nombre.equals(comida.nombre) : comida.nombre != null) return false;
        if (restaurante != null ? !restaurante.equals(comida.restaurante) : comida.restaurante != null)
            return false;
        return fechaHora != null ? fechaHora.equals(comida.fechaHora) : comida.fechaHora == null;

    }

    @Override
    public int hashCode() {
        int result = nombre != null ? nombre.hashCode() : 0;
        result = 31 * result + (restaurante != null ? restaurante.hashCode() : 0);
        result = 31 * result + (fechaHora != null ? fechaHora.hashCode() : 0);
        return result;
    }
}
