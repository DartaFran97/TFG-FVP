package com.example.fencingcoachapplication.pojo;

public class Resultado {

    private String idResultado;
    private Usuario usuario;
    private long asaltoId;
    private int tocadosDados;
    private int tocadosRecibidos;
    private int victorias;
    private int derrotas;
    private float coeficienteTocados;


    public Resultado() {}

    public Resultado(Usuario usuario, String idResultado, long asaltoId, int tocadosDados, int tocadosRecibidos, int victorias, int derrotas, float coeficienteTocados) {
        this.idResultado = idResultado;
        this.usuario = usuario;
        this.tocadosDados = tocadosDados;
        this.tocadosRecibidos = tocadosRecibidos;
        this.victorias = victorias;
        this.derrotas = derrotas;
        this.coeficienteTocados = coeficienteTocados;
    }



    public String getIdResultado() {
        return idResultado;
    }

    public void setIdResultado(String idResultado) {
        this.idResultado = idResultado;
    }

    public String getUsuario() {
        return usuario.getNombreUsuario();
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public long getAsaltoId() {
        return asaltoId;
    }

    public void setAsaltoId(long asaltoId) {
        this.asaltoId = asaltoId;
    }

    public int getTocadosDados() {
        return tocadosDados;
    }

    public void setTocadosDados(int tocadosDados) {
        this.tocadosDados = tocadosDados;
    }

    public int getTocadosRecibidos() {
        return tocadosRecibidos;
    }

    public void setTocadosRecibidos(int tocadosRecibidos) {
        this.tocadosRecibidos = tocadosRecibidos;
    }

    public int getVictorias() {
        return victorias;
    }

    public void setVictorias(int victorias) {
        this.victorias = victorias;
    }

    public int getDerrotas() {
        return derrotas;
    }

    public void setDerrotas(int derrotas) {
        this.derrotas = derrotas;
    }

    public float getCoeficienteTocados() {
        return coeficienteTocados;
    }

    public void setCoeficienteTocados(float coeficienteTocados) {
        this.coeficienteTocados = coeficienteTocados;
    }


}
