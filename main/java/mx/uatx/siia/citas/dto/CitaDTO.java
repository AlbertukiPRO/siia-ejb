package mx.uatx.siia.citas.dto;

public class CitaDTO {

    private Integer intIdCita;

    private Long intIdAlumno;

    private Integer intIdArea;

    private Integer intTramite;

    private String strDescripcionCita;

    private String strEstatus;

    private String strFechaReservada;

    private String strRetroalimentacion;

    private String strHoraReservada;

    private String strUSERAUDIT;

    // TODO DELETE

    public Integer getIntIdCita() {
        return intIdCita;
    }

    public void setIntIdCita(Integer intIdCita) {
        this.intIdCita = intIdCita;
    }

    public Long getIntIdAlumno() {
        return intIdAlumno;
    }

    public void setIntIdAlumno(Long intIdAlumno) {
        this.intIdAlumno = intIdAlumno;
    }

    public Integer getIntIdArea() {
        return intIdArea;
    }

    public void setIntIdArea(Integer intIdArea) {
        this.intIdArea = intIdArea;
    }

    public Integer getIntTramite() {
        return intTramite;
    }

    public void setIntTramite(Integer intTramite) {
        this.intTramite = intTramite;
    }

    public String getStrDescripcionCita() {
        return strDescripcionCita;
    }

    public void setStrDescripcionCita(String strDescripcionCita) {
        this.strDescripcionCita = strDescripcionCita;
    }

    public String getStrEstatus() {
        return strEstatus;
    }

    public void setStrEstatus(String strEstatus) {
        this.strEstatus = strEstatus;
    }

    public String getStrFechaReservada() {
        return strFechaReservada;
    }

    public void setStrFechaReservada(String strFechaReservada) {
        this.strFechaReservada = strFechaReservada;
    }

    public String getStrRetroalimentacion() {
        return strRetroalimentacion;
    }

    public void setStrRetroalimentacion(String strRetroalimentacion) {
        this.strRetroalimentacion = strRetroalimentacion;
    }

    public String getStrHoraReservada() {
        return strHoraReservada;
    }

    public void setStrHoraReservada(String strHoraReservada) {
        this.strHoraReservada = strHoraReservada;
    }

    public String getStrUSERAUDIT() {
        return strUSERAUDIT;
    }

    public void setStrUSERAUDIT(String strUSERAUDIT) {
        this.strUSERAUDIT = strUSERAUDIT;
    }
}
