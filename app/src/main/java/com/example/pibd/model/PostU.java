package com.example.pibd.model;

public class PostU {
String Nombre, Ubicacion, Descripcion, Numero_baños, Numero_Habitaciones, Precio, Tipo;

    public PostU(String nombre, String ubicacion, String descripcion, String numero_baños, String numero_Habitaciones, String precio, String tipo) {
        Nombre = nombre;
        Ubicacion = ubicacion;
        Descripcion = descripcion;
        Numero_baños = numero_baños;
        Numero_Habitaciones = numero_Habitaciones;
        Precio = precio;
        Tipo = tipo;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getUbicacion() {
        return Ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        Ubicacion = ubicacion;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }

    public String getNumero_baños() {
        return Numero_baños;
    }

    public void setNumero_baños(String numero_baños) {
        Numero_baños = numero_baños;
    }

    public String getNumero_Habitaciones() {
        return Numero_Habitaciones;
    }

    public void setNumero_Habitaciones(String numero_Habitaciones) {
        Numero_Habitaciones = numero_Habitaciones;
    }

    public String getPrecio() {
        return Precio;
    }

    public void setPrecio(String precio) {
        Precio = precio;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        Tipo = tipo;
    }

    public PostU(){



}
}

