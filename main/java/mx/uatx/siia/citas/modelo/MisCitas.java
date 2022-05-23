package mx.uatx.siia.citas.modelo;

public class MisCitas {

    private String strIdCita;
    private String strDescripcionCita;
    private String strRepuesta;
    private String strFechaHoraReservada;
    private String strEstatus;
    private String strUser;

    private String area;
    private String tramite;


    public MisCitas(String strIdCita, String strUser, String strDescripcionCita, String strRepuesta, String strFechaHoraReservada, String strEstatus) {

        String[] datosCita = strDescripcionCita.split(",");
        String[] fechaHora = strFechaHoraReservada.split(",");

        System.out.println("Split =>"+datosCita);

        this.strIdCita = datosCita[3]+strIdCita+datosCita[4];
        this.strDescripcionCita = "<b>Descripción: </b>"+datosCita[0]+"<br>"+"<b>Area: </b>"+datosCita[1]+"<br>"+"<b>Tramite: </b>"+datosCita[2];
        this.strRepuesta = strRepuesta;
        this.strFechaHoraReservada = fechaHora[0]+"<br>"+fechaHora[1];
        this.strEstatus = strEstatus;
        this.strUser =  strUser;
    }

    public String getStrIdCita() {
        return "CI"+strIdCita;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getTramite() {
        return tramite;
    }

    public void setTramite(String tramite) {
        this.tramite = tramite;
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
        String[] fechaHora = strFechaHoraReservada.split(",");
        return fechaHora[0]+"\n"+fechaHora[1];
    }

    public void setStrFechaHoraReservada(String strFechaHoraReservada) {
        this.strFechaHoraReservada = strFechaHoraReservada;
    }

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
}
