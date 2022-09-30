package mx.uatx.siia.citas.modelo;

public class MisCitas {

    private String strIdCita;
    private String strDescripcionCita;
    private String strRepuesta;
    private String strFechaHoraReservada;
    private String strEstatus;
    private String strUser;
    private String area;
    private String strtramite;
    
    private String strNombre;
    private String strPrograma;
    private String strHora;

    private String facultad;
    private String semestre;
    private String grupo;
    private String fotoPerfil;


    public MisCitas(String strIdCita, String strUser, String strDescripcionCita, String strRepuesta, String strtramite,  String strNombre, String strFechaHoraReservada, String strEstatus) {

        String[] datosCita = strDescripcionCita.split(",");
        String[] fechaHora = strFechaHoraReservada.split(",");

        this.strtramite = strtramite;
        this.strIdCita = strIdCita;
        this.strDescripcionCita = "<b>Descripción: </b>"+datosCita[0]+"<br>"+"<b>Area: </b>"+datosCita[1]+"<br>"+"<b>Tramite: </b>"+datosCita[2];
        this.strRepuesta = strRepuesta;
        this.strFechaHoraReservada = fechaHora[0]+"<br>"+fechaHora[1];
        this.strUser =  strUser;
        this.strNombre = strNombre;
        this.strEstatus = strEstatus;
    }

    public MisCitas(String strIdCita, String strUser, String strDescripcionCita, String tramite,  String strNombre, String strRepuesta, String strFechaHoraReservada, String strPrograma, String strHora) {
        this.strIdCita = strIdCita;
        this.strDescripcionCita = strDescripcionCita;
        this.strRepuesta = strRepuesta;
        this.strFechaHoraReservada = strFechaHoraReservada;
        this.strUser = strUser;
        this.strtramite = tramite;
        this.strNombre = strNombre;
        this.strHora = strHora;
        this.strPrograma = strPrograma;
    }

    public MisCitas(String strIdCita, String strUser, String strDescripcionCita, String tramite,  String strNombre, String strRepuesta, String strFechaHoraReservada, String strPrograma, String strHora, String facultad, String semestre, String grupo) {
        this.strIdCita = strIdCita;
        this.strDescripcionCita = strDescripcionCita;
        this.strRepuesta = strRepuesta;
        this.strFechaHoraReservada = strFechaHoraReservada;
        this.strUser = strUser;
        this.strtramite = tramite;
        this.strNombre = strNombre;
        this.strHora = strHora;
        this.strPrograma = strPrograma;
        this.facultad = facultad;
        this.semestre = semestre;
        this.grupo = grupo;
    }

    public MisCitas(){

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
