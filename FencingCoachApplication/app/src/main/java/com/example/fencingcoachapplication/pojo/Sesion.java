package com.example.fencingcoachapplication.pojo;

import java.io.File;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class Sesion implements Serializable {
    private long idSesion;
    private Date fecha;
    private String usuarioResponsable;
    private List<Usuario> usuarios;
    private List<Asalto> asaltos;


    public Sesion() {
    }

    public Sesion(long idSesion, Date fecha, String usuarioResponsable, List<Usuario> usuarios) {
        this.idSesion = idSesion;
        this.fecha = fecha;
        this.usuarioResponsable = usuarioResponsable;
        this.usuarios = usuarios;
    }

    public List<Asalto> getAsaltos() {
        return asaltos;
    }

    public void setAsaltos(List<Asalto> asaltos) {
        this.asaltos = asaltos;
    }

    public long getIdSesion() {
        return idSesion;
    }

    public void setIdSesion(long idSesion) {
        this.idSesion = idSesion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getUsuarioResponsable() {
        return usuarioResponsable;
    }

    public void setUsuarioResponsable(String usuarioResponsable) {
        this.usuarioResponsable = usuarioResponsable;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public void setId(long l) {
        this.idSesion = l;
    }
}