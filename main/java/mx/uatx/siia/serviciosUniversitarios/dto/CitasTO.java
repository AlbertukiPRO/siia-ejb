package mx.uatx.siia.serviciosUniversitarios.dto;

public class CitasTO {

    private Integer intIdCita;
    private Integer intIdAlumno;
    private Integer intIdArea;
    private Integer intIdTramite;
    private String strDescripcionTramite;
    private String strEstatus;
    private String strFechaReserva;

    public Integer getIntIdCita() {
        return intIdCita;
    }

    public void setIntIdCita(Integer intIdCita) {
        this.intIdCita = intIdCita;
    }

    public Integer getIntIdAlumno() {
        return intIdAlumno;
    }

    public void setIntIdAlumno(Integer intIdAlumno) {
        this.intIdAlumno = intIdAlumno;
    }

    public Integer getIntIdArea() {
        return intIdArea;
    }

    public void setIntIdArea(Integer intIdArea) {
        this.intIdArea = intIdArea;
    }

    public Integer getIntIdTramite() {
        return intIdTramite;
    }

    public void setIntIdTramite(Integer intIdTramite) {
        this.intIdTramite = intIdTramite;
    }

    public String getStrDescripcionTramite() {
        return strDescripcionTramite;
    }

    public void setStrDescripcionTramite(String strDescripcionTramite) {
        this.strDescripcionTramite = strDescripcionTramite;
    }

    public String getStrEstatus() {
        return strEstatus;
    }

    public void setStrEstatus(String strEstatus) {
        this.strEstatus = strEstatus;
    }

    public String getStrFechaReserva() {
        return strFechaReserva;
    }

    public void setStrFechaReserva(String strFechaReserva) {
        this.strFechaReserva = strFechaReserva;
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

    private String strRetroalimentacion;
    private String strHoraReservada;

    public CitasTO(Integer intIdAlumno, Integer intIdArea, Integer intIdTramite, String strDescripcionTramite, String strEstatus, String strFechaReserva, String strHoraReservada) {
        this.intIdAlumno = intIdAlumno;
        this.intIdArea = intIdArea;
        this.intIdTramite = intIdTramite;
        this.strDescripcionTramite = strDescripcionTramite;
        this.strEstatus = strEstatus;
        this.strFechaReserva = strFechaReserva;
        this.strHoraReservada = strHoraReservada;
    }

    @Override
    public String toString() {
        return "CitasTO{" +
                "intIdCita=" + intIdCita +
                ", intIdAlumno=" + intIdAlumno +
                ", intIdArea=" + intIdArea +
                ", intIdTramite=" + intIdTramite +
                ", strDescripcionTramite='" + strDescripcionTramite + '\'' +
                ", strEstatus='" + strEstatus + '\'' +
                ", strFechaReserva='" + strFechaReserva + '\'' +
                ", strRetroalimentacion='" + strRetroalimentacion + '\'' +
                ", strHoraReservada='" + strHoraReservada + '\'' +
                '}';
    }
}
