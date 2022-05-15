package mx.uatx.siia.serviciosUniversitarios.dto;

public class CitasTO {

    private Integer intIdCita;
    private Integer intIdAlumno;
    private Integer intIdArea;
    private Integer intIdTramite;
    private String strDescripcionTramite;
    private String strEstatus;
    private String strFechaReserva;
    private String strRetroalimentacion;
    private String strHoraReservada;

    public CitasTO(Integer intIdCita, Integer intIdAlumno, Integer intIdArea, Integer intIdTramite, String strDescripcionTramite, String strEstatus, String strFechaReserva, String strRetroalimentacion, String strHoraReservada) {
        this.intIdCita = intIdCita;
        this.intIdAlumno = intIdAlumno;
        this.intIdArea = intIdArea;
        this.intIdTramite = intIdTramite;
        this.strDescripcionTramite = strDescripcionTramite;
        this.strEstatus = strEstatus;
        this.strFechaReserva = strFechaReserva;
        this.strRetroalimentacion = strRetroalimentacion;
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
