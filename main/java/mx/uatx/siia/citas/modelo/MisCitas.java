package mx.uatx.siia.citas.modelo;

public class MisCitas {

    private String strIdCita;
    private String strDescripcionCita;
    private String strRepuesta;
    private String strFechaHoraReservada;
    private String strEstatus;
    private String strUser;

    public MisCitas(String strIdCita, String strDescripcionCita, String strRepuesta, String strFechaHoraReservada, String strEstatus, String strUser) {
        this.strIdCita = strIdCita;
        this.strDescripcionCita = strDescripcionCita;
        this.strRepuesta = strRepuesta;
        this.strFechaHoraReservada = strFechaHoraReservada;
        this.strEstatus = strEstatus;
        this.strUser = strUser;
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

    public String getStrFechaHoraReservada() {
        return strFechaHoraReservada;
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
}
