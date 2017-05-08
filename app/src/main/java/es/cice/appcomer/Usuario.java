package es.cice.appcomer;

import java.util.List;

/**
 * Created by cice on 5/5/17.
 */

public class Usuario {
    String nombre;
    int hambre;

    public Usuario() {
    }

    public Usuario(String nombre) {
        this.nombre = nombre;
        this.hambre = 5;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getHambre() {
        return hambre;
    }

    public void setHambre(int hambre) {
        this.hambre = hambre;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "nombre='" + nombre + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;

        return nombre != null ? nombre.equals(usuario.nombre) : usuario.nombre == null;

    }

    @Override
    public int hashCode() {
        return nombre != null ? nombre.hashCode() : 0;
    }


    public static List<Usuario> getData() {
        return ConsultasFirebase.getListaUsuarios();
    }
}
