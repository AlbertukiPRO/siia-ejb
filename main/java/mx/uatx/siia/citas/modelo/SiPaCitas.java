package mx.uatx.siia.citas.modelo;


import javax.persistence.*;

@Entity
public class SiPaCitas {
    @Id
    @Column(name = "idcita", unique = true, nullable = false)
    private Integer intIdCita;

    @Column(name="idalumno")
    private Integer intIdAlumno;

    @Column(name="idarea")
    private Integer intIdTramite;

    @Column(name="idtramite")
    private Integer intTramite;

    @Column(name = "descripcioncita")
    private String strDescripcionCita;

    @Column(name="estatus")
    private String strEstatus;

    @Column(name="fechareservada")
    private String strFechaReservada;

    @Column(name = "retroalimentación")
    private String strRetroalimentación;

    @Column(name = "horareservada")
    private String strHoraReservada;

    /* GETTERS AND SETTERS */

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

    public Integer getIntIdTramite() {
        return intIdTramite;
    }

    public void setIntIdTramite(Integer intIdTramite) {
        this.intIdTramite = intIdTramite;
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

    public String getStrRetroalimentación() {
        return strRetroalimentación;
    }

    public void setStrRetroalimentación(String strRetroalimentación) {
        this.strRetroalimentación = strRetroalimentación;
    }

    public String getStrHoraReservada() {
        return strHoraReservada;
    }

    public void setStrHoraReservada(String strHoraReservada) {
        this.strHoraReservada = strHoraReservada;
    }
}