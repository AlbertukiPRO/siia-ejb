package mx.uatx.siia.citas;
import javax.persistence.*;

@Entity
public class SIMSCITAS  {
    /**
     * Serial VersionUID
     */
    @Id
    @SequenceGenerator(name = "IDCITA", sequenceName = "IDCITA", allocationSize = 1)
    @Column(name = "IDCITA")
    private Integer intIdCita;

    @Column(name="IDHISTORIALACADEMICO")
    private Long intIdAlumno;

    @Column(name="IDAREACAMPUS")
    private Integer intIdArea;

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

    @Column(name = "USERAUDIT")
    private String strUSERAUDIT;

    /* GETTERS AND SETTERS */

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

    public String getStrUSERAUDIT() {
        return strUSERAUDIT;
    }

    public void setStrUSERAUDIT(String strUSERAUDIT) {
        this.strUSERAUDIT = strUSERAUDIT;
    }

    public String getStrHoraReservada() {
        return strHoraReservada;
    }

    public void setStrHoraReservada(String strHoraReservada) {
        this.strHoraReservada = strHoraReservada;
    }

    @Override
    public String toString() {
        return "SiPaCitas{" +
                "intIdCita=" + intIdCita +
                ", intIdAlumno=" + intIdAlumno +
                ", intIdArea=" + intIdArea +
                ", intTramite=" + intTramite +
                ", strDescripcionCita='" + strDescripcionCita + '\'' +
                ", strEstatus='" + strEstatus + '\'' +
                ", strFechaReservada='" + strFechaReservada + '\'' +
                ", strRetroalimentacion='" + strRetroalimentacion + '\'' +
                ", strHoraReservada='" + strHoraReservada + '\'' +
                ", strUSERAUDIT='" + strUSERAUDIT + '\'' +
                '}';
    }
}