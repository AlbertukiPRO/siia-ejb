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

    /** attributes que seran agregados después en el sql join **/

    @Column(name = "NOMBRE")
    private String strNombreUser;
    @Column(name = "PROGRAMA")
    private String strPrograma;
    @Column(name = "FACULTAD")
    private String strFacultad;
    @Column(name = "GRADO")
    private Integer strGrado;
    @Column(name = "GRUPO")
    private String strGrupo;
    @Column(name = "CORREO")
    private String strCorreo;

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


    public String getStrNombreUser() {
        return strNombreUser;
    }

    public void setStrNombreUser(String strNombreUser) {
        this.strNombreUser = strNombreUser;
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

    public void setStrCorreo(String strCorreo) {
        this.strCorreo = strCorreo;
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