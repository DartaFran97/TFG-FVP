package com.example.fencingcoachapplication.pojo;

public class Asalto {

    private String idAsalto;
    private static String usuarioLocal;
    private static String usuarioVisitante;
    private long sesionId;
    private String vencedor;
    private static int tocadosLocal;
    private static int tocadosVisitante;

    public Asalto() {
    }

    public Asalto(String idAsalto, String usuarioLocal, String usuarioVisitante, long sesionId, String vencedor, int tocadosLocal, int tocadosVisitante) {
        this.idAsalto = idAsalto;
        this.usuarioLocal = usuarioLocal;
        this.usuarioVisitante = usuarioVisitante;
        this.sesionId = sesionId;
        this.vencedor = vencedor;
        this.tocadosLocal = tocadosLocal;
        this.tocadosVisitante = tocadosVisitante;
    }

    public Asalto(String nombreTirador1, String nombreTirador2, int tocadosTirador1, int tocadosTirador2) {
        this.usuarioLocal = usuarioLocal;
        this.usuarioVisitante = usuarioVisitante;
        this.tocadosLocal = tocadosLocal;
        this.tocadosVisitante = tocadosVisitante;
    }



    public String getIdAsalto() {
        return idAsalto;
    }

    public void setIdAsalto(String idAsalto) {
        this.idAsalto = idAsalto;
    }

    public String getUsuarioLocal() {
        return usuarioLocal;
    }

    public void setUsuarioLocal(String usuarioLocal) {
        this.usuarioLocal = usuarioLocal;
    }

    public String getUsuarioVisitante() {
        return usuarioVisitante;
    }

    public void setUsuarioVisitante(String usuarioVisitante) {
        this.usuarioVisitante = usuarioVisitante;
    }

    public long getSesionId() {
        return sesionId;
    }

    public void setSesionId(long sesionId) {
        this.sesionId = sesionId;
    }


    public void setVencedor(String vencedor) {
        this.vencedor = vencedor;
    }

    public int getTocadosLocal() {
        return tocadosLocal;
    }

    public void setTocadosLocal(int tocadosLocal) {
        this.tocadosLocal = tocadosLocal;
    }

    public int getTocadosVisitante() {
        return tocadosVisitante;
    }

    public void setTocadosVisitante(int tocadosVisitante) {
        this.tocadosVisitante = tocadosVisitante;
    }
    // Método para determinar el vencedor basado en los puntos
    public static String getVencedor() {
        if (tocadosLocal > tocadosVisitante) {
            return usuarioLocal;
        } else if (tocadosVisitante > tocadosLocal) {
            return usuarioVisitante;
        }
        return null;
    }

    // Método para obtener el perdedor basado en los puntos
    public String getPerdedor () {
        if (tocadosLocal < tocadosVisitante) {
            return usuarioLocal;
        } else if (tocadosVisitante < tocadosLocal) {
            return usuarioVisitante;
        }

        return null;
    }
}
