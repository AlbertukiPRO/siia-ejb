package mx.uatx.siia.serviciosUniversitarios.dto;

public class AreasTO {

    private String intIdAreas;
    private String strNombreAreas;
    private Integer strIdCalendario;
    private Integer intIdTramites;
    private Integer intIdHorarios;

    public AreasTO(String intIdAreas, String strNombreAreas, Integer strIdCalendario, Integer intIdTramites, Integer intIdHorarios) {
        this.intIdAreas = intIdAreas;
        this.strNombreAreas = strNombreAreas;
        this.strIdCalendario = strIdCalendario;
        this.intIdTramites = intIdTramites;
        this.intIdHorarios = intIdHorarios;
    }

    public AreasTO(String intIdAreas, String strNombreAreas) {
        this.intIdAreas = intIdAreas;
        this.strNombreAreas = strNombreAreas;
    }
    public AreasTO(){

    }

    public String getIntIdAreas() {
        return intIdAreas;
    }

    public void setIntIdAreas(String intIdAreas) {
        this.intIdAreas = intIdAreas;
    }

    public String getStrNombreAreas() {
        return strNombreAreas;
    }

    public void setStrNombreAreas(String strNombreAreas) {
        this.strNombreAreas = strNombreAreas;
    }

    public Integer getStrIdCalendario() {
        return strIdCalendario;
    }

    public void setStrIdCalendario(Integer strIdCalendario) {
        this.strIdCalendario = strIdCalendario;
    }

    public Integer getIntIdTramites() {
        return intIdTramites;
    }

    public void setIntIdTramites(Integer intIdTramites) {
        this.intIdTramites = intIdTramites;
    }

    public Integer getIntIdHorarios() {
        return intIdHorarios;
    }

    public void setIntIdHorarios(Integer intIdHorarios) {
        this.intIdHorarios = intIdHorarios;
    }
}
