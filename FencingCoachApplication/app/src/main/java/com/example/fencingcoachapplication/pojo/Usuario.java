package com.example.fencingcoachapplication.pojo;

import java.io.Serializable;
import java.util.List;

public class Usuario implements Serializable {
    private long id;
    private String nombreUsuario;
    private String contrasena;
    private String categoria;
    private String correo;
    private String arma;

    private boolean alta;
    private boolean rol;

    public Usuario() {
    }

    public Usuario(String name, String contrasena, String categoria, String correo, String arma) {
        this.nombreUsuario = name;
        this.contrasena = contrasena;
        this.categoria = categoria;
        this.correo = correo;
        this.arma = arma;
        this.rol=false;
        this.alta=true;

    }


    public Usuario(String username, String contrasena) {
        this.nombreUsuario = username;
        this.contrasena = contrasena;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getArma() {
        return arma;
    }

    public void setArma(String arma) {
        this.arma = arma;
    }

    public boolean isAlta() {
        return alta;
    }

    public void setAlta(boolean alta) {
        this.alta = alta;
    }

    public boolean isRol() {
        return rol;
    }

    public void setRol(boolean rol) {
        this.rol = rol;
    }
}
