package mx.uatx.siia.serviciosUniversitarios.dto;

public class TramitesTO {

    private String intIdTramite;
    private Integer intIdArea;
    private String strNombreTramite;
    private String strDescripcionTramite;
    private String strRequisitos;

    public String getIntIdTramite() {
        return intIdTramite;
    }

    public void setIntIdTramite(String intIdTramite) {
        this.intIdTramite = intIdTramite;
    }

    public Integer getIntIdArea() {
        return intIdArea;
    }

    public void setIntIdArea(Integer intIdArea) {
        this.intIdArea = intIdArea;
    }

    public String getStrNombreTramite() {
        return strNombreTramite;
    }

    public void setStrNombreTramite(String strNombreTramite) {
        this.strNombreTramite = strNombreTramite;
    }

    public String getStrDescripcionTramite() {
        return strDescripcionTramite;
    }

    public void setStrDescripcionTramite(String strDescripcionTramite) {
        this.strDescripcionTramite = strDescripcionTramite;
    }

    public String getStrRequisitos() {
        return strRequisitos;
    }

    public void setStrRequisitos(String strRequisitos) {
        this.strRequisitos = strRequisitos;
    }

    public TramitesTO(String intIdTramite, Integer intIdArea, String strNombreTramite, String strDescripcionTramite, String strRequisitos) {
        this.intIdTramite = intIdTramite;
        this.intIdArea = intIdArea;
        this.strNombreTramite = strNombreTramite;
        this.strDescripcionTramite = strDescripcionTramite;
        this.strRequisitos = strRequisitos;
    }

    public TramitesTO(String intIdTramite, String strNombreTramite){
        this.intIdTramite = intIdTramite;
        this.strNombreTramite = strNombreTramite;
    }

    public TramitesTO(){

    }
}
