package mx.uatx.siia.serviciosUniversitarios.dto;

public class CitasTO {

    private Integer intIdCita;
    private Integer intIdAlumno;
    private Integer intIdArea;
    private Integer intTramite;
    private String strDescripcionCita;
    private String strEstatus;
    private String strFechaReservada;
    private String strHoraReservada;
    private String strRetroalimentacion;


    /* EXTRA PARAMS */

    private Integer longHistorialAcademico;
    private String strNameArea;
    private String strNameTramite;
    private String strNombreUser;
    private String strPrograma;
    private String strFacultad;
    private Integer strGrado;
    private String strGrupo;
    private String strCorreo;

    public CitasTO(){

    }

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

    public String getStrDescripcionTramite() {
        return strDescripcionCita;
    }

    public void setStrDescripcionTramite(String strDescripcionTramite) {
        this.strDescripcionCita = strDescripcionTramite;
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

    public String getStrFechaReservada() {
        return strFechaReservada;
    }

    public void setStrFechaReservada(String strFechaReservada) {
        this.strFechaReservada = strFechaReservada;
    }

    public String getStrEstatus() {
        return strEstatus;
    }

    public void setStrEstatus(String strEstatus) {
        this.strEstatus = strEstatus;
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


    public String getStrNombreUser() {
        return strNombreUser;
    }

    public void setStrNombreUser(String strNombreUser) {
        this.strNombreUser = strNombreUser;
    }

    public Integer getLongHistorialAcademico() {
        return longHistorialAcademico;
    }

    public void setLongHistorialAcademico(Integer longHistorialAcademico) {
        this.longHistorialAcademico = longHistorialAcademico;
    }

    public String getStrPrograma() {
        return strPrograma;
    }

    public void setStrPrograma(String strPrograma) {
        this.strPrograma = strPrograma;
    }

    public String getStrFacultad() {
        return strFacultad;
    }

    public void setStrFacultad(String strFacultad) {
        this.strFacultad = strFacultad;
    }

    public Integer getStrGrado() {
        return strGrado;
    }

    public void setStrGrado(Integer strGrado) {
        this.strGrado = strGrado;
    }

    public String getStrGrupo() {
        return strGrupo;
    }

    public void setStrGrupo(String strGrupo) {
        this.strGrupo = strGrupo;
    }

    public String getStrCorreo() {
        return strCorreo;
    }

    public String getStrNameArea() {
        return strNameArea;
    }

    public void setStrNameArea(String strNameArea) {
        this.strNameArea = strNameArea;
    }

    public String getStrNameTramite() {
        return strNameTramite;
    }

    public void setStrNameTramite(String strNameTramite) {
        this.strNameTramite = strNameTramite;
    }

    public void setStrCorreo(String strCorreo) {
        this.strCorreo = strCorreo;
    }

    @Override
    public String toString() {
        return "CitasTO{" +
                "intIdCita=" + intIdCita +
                ", intIdAlumno=" + intIdAlumno +
                ", intIdArea=" + intIdArea +
                ", intIdTramite=" + intTramite +
                ", strDescripcionTramite='" + strDescripcionCita + '\'' +
                ", strEstatus='" + strEstatus + '\'' +
                ", strFechaReserva='" + strFechaReservada + '\'' +
                ", strRetroalimentacion='" + strRetroalimentacion + '\'' +
                ", strHoraReservada='" + strHoraReservada + '\'' +
                ", NBAREA='" + strNameArea + '\'' +
                ", NOMBRETRAMITE='" + strNameTramite + '\'' +
                '}';
    }
}
