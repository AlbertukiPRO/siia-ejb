package mx.uatx.siia.citas.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MisCitas {

    @Id
    @Column(name = "IDCITA")
    private String strIdCita;
    @Column(name = "DESCRIPCIONCITA")
    private String strDescripcionCita;
    @Column(name = "RETROALIMENTACIONCITA")
    private String strRepuesta;
    @Column(name = "FECHARESERVADACITA")
    private String strFechaReservada;
    @Column(name = "FECHARESERVADACITA_1")
    private String strHoraReservada;
    @Column(name = "ESTATUSCITAS")
    private String strEstatus;
    @Column(name = "IDHISTORIALACADEMICO")
    private String strIdHistorial;
    @Column(name = "NBAREA")
    private String area;
    @Column(name = "NOMBRETRAMITE")
    private String strNameTramite;
    @Column(name = "NOMBREUSER")
    private String strNombre;
    @Column(name = "MATRICULA")
    private String strMatricula;
    @Column(name = "PROGRAMA")
    private String strPrograma;

    private String facultad;
    private String semestre;
    private String grupo;
    private String nombre;

    public MisCitas() {

    }

    public MisCitas(String strNombre){
        this.strNombre = strNombre;
    }

    public String getStrIdCita() {
        return strIdCita;
    }

    public void setStrIdCita(String strIdCita) {
        this.strIdCita = strIdCita;
    }

    public String getStrDescripcionCita() {
        return strDescripcionCita;
    }

    public void setStrDescripcionCita(String strDescripcionCita) {
        this.strDescripcionCita = strDescripcionCita;
    }

    public String getStrRepuesta() {
        return strRepuesta;
    }

    public void setStrRepuesta(String strRepuesta) {
        this.strRepuesta = strRepuesta;
    }

    public String getStrFechaReservada() {
        return strFechaReservada;
    }

    public void setStrFechaReservada(String strFechaReservada) {
        this.strFechaReservada = strFechaReservada;
    }

    public String getStrHoraReservada() {
        return strHoraReservada;
    }

    public void setStrHoraReservada(String strHoraReservada) {
        this.strHoraReservada = strHoraReservada;
    }

    public String getStrEstatus() {
        return strEstatus;
    }

    public void setStrEstatus(String strEstatus) {
        this.strEstatus = strEstatus;
    }

    public String getStrIdHistorial() {
        return strIdHistorial;
    }

    public void setStrIdHistorial(String strIdHistorial) {
        this.strIdHistorial = strIdHistorial;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getStrNameTramite() {
        return strNameTramite;
    }

    public void setStrNameTramite(String strNameTramite) {
        this.strNameTramite = strNameTramite;
    }

    public String getStrNombre() {
        return strNombre;
    }

    public void setStrNombre(String strNombre) {
        this.strNombre = strNombre;
    }

    public String getStrMatricula() {
        return strMatricula;
    }

    public void setStrMatricula(String strMatricula) {
        this.strMatricula = strMatricula;
    }

    public String getStrPrograma() {
        return strPrograma;
    }

    public void setStrPrograma(String strPrograma) {
        this.strPrograma = strPrograma;
    }

    @Override
    public String toString() {
        return "MisCitas{" +
                "strIdCita='" + strIdCita + '\'' +
                ", strDescripcionCita='" + strDescripcionCita + '\'' +
                ", strRepuesta='" + strRepuesta + '\'' +
                ", strFechaHoraReservada='" + strFechaReservada + '\'' +
                ", strEstatus='" + strEstatus + '\'' +
                ", strUser='" + strIdHistorial + '\'' +
                ", area='" + area + '\'' +
                ", strNameTramite='" + strNameTramite + '\'' +
                ", strNombre='" + strNombre + '\'' +
                ", strPrograma='" + strPrograma + '\'' +
                ", strHora='" + strHoraReservada + '\'' +
                '}';
    }
}
