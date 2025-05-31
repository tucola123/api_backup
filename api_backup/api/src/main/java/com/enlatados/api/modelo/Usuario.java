package  com.enlatados.api.modelo;

import lombok.Getter;

public class Usuario {
    @Getter
    private String id;
    private String nombre;
    private String apellido;
    private String contraseña;

    public Usuario (String id, String nombre, String apellido, String contraseña){

        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.contraseña = contraseña;

    }

    @Override
    public String toString() {
        return "Usuario{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", contraseña='" + contraseña + '\'' +
                '}';
    }
}
