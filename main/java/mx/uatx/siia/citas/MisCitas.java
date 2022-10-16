package mx.uatx.siia.citas;

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
    private String strFechaHoraReservada;
    @Column(name = "FECHARESERVADACITA_1")
    private String strHora;
    @Column(name = "ESTATUSCITAS")
    private String strEstatus;
    @Column(name = "IDHISTORIALACADEMICO")
    private String strUser;
    @Column(name = "NBAREA")
    private String area;
    @Column(name = "NOMBRETRAMITE")
    private String strtramite;
    @Column(name = "STRUSER")
    private String strNombre;
    private String strPrograma;
    private String facultad;
    private String semestre;
    private String grupo;
    private String fotoPerfil;

    public MisCitas(String strNombre){
        this.strNombre = strNombre;
    }

    public MisCitas() {

    }

    public String getStrIdCita() {
        return strIdCita;
    }
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTramite() {
        return strtramite;
    }

    public void setTramite(String tramite) {
        this.strtramite = tramite;
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
        return strRepuesta != null ? strRepuesta : " ";
    }

    public void setStrRepuesta(String strRepuesta) {
        this.strRepuesta = strRepuesta;
    }

    public String getStrFechaHoraReservada() {
        return strFechaHoraReservada;
    }

    public String getStrtramite() {
        return strtramite;
    }

    public void setStrtramite(String strtramite) {
        this.strtramite = strtramite;
    }

    public String getStrNombre() {
        return strNombre;
    }

    public void setStrNombre(String strNombre) {
        this.strNombre = strNombre;
    }

    public String getStrPrograma() {
        return strPrograma;
    }

    public void setStrPrograma(String strPrograma) {
        this.strPrograma = strPrograma;
    }

    public String getStrHora() {
        return strHora;
    }

    public void setStrHora(String strHora) {
        this.strHora = strHora;
    }

    public void setStrFechaHoraReservada(String strFechaHoraReservada) { this.strFechaHoraReservada = strFechaHoraReservada;}

    public String getStrEstatus() {
        return strEstatus;
    }

    public void setStrEstatus(String strEstatus) {
        this.strEstatus = strEstatus;
    }

    public String getStrUser() {
        return strUser;
    }

    public void setStrUser(String strUser) {
        this.strUser = strUser;
    }


    @Override
    public String toString() {
        return "MisCitas{" +
                "strIdCita='" + strIdCita + '\'' +
                ", strDescripcionCita='" + strDescripcionCita + '\'' +
                ", strRepuesta='" + strRepuesta + '\'' +
                ", strFechaHoraReservada='" + strFechaHoraReservada + '\'' +
                ", strEstatus='" + strEstatus + '\'' +
                ", strUser='" + strUser + '\'' +
                ", area='" + area + '\'' +
                ", strtramite='" + strtramite + '\'' +
                ", strNombre='" + strNombre + '\'' +
                ", strPrograma='" + strPrograma + '\'' +
                ", strHora='" + strHora + '\'' +
                '}';
    }
}
