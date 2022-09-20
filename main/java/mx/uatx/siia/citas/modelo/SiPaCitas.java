package mx.uatx.siia.citas.modelo;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
public class SiPaCitas implements Serializable {
    /**
     * Serial VersionUID
     */
    private static final long serialVersionUID = -4748540113039819136L;
    @NotNull
    @Id
    @Column(name = "IDCITA", nullable = false, unique = true)
    private Integer intIdCita;

    @Column(name="IDHISTORIALACADEMICO")
    private Integer intIdAlumno;

    @Column(name="IDAREACAMPUS")
    private Integer intIdTramite;

    @Column(name="IDTRAMITE")
    private Integer intTramite;

    @Column(name = "DESCRIPCIONCITA")
    private String strDescripcionCita;

    @Column(name="ESTATUSCITAS")
    private String strEstatus;

    @Column(name="FECHARESERVADACITA")
    private String strFechaReservada;

    @Column(name = "RETROALIMENTACIONCITA")
    private String strRetroalimentacion;

    @Column(name = "FECHARESERVADACITA_1")
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
}