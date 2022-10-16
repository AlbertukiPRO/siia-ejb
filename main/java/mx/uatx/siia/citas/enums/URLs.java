package mx.uatx.siia.citas.enums;

/**
 * @author Alberto Noche Rosas
 * @apiNote Enum para almacenar las API's temporales del servicio backend en php y mysql
 */
public enum URLs {
    Areas("http://localhost/siiaServices/apis/getAreas.php"),
    Tramites("http://localhost/siiaServices/apis/getTramites.php"),
    FechasReservadas("http://localhost/siiaServices/apis/getFechasReservadas.php"),
    HorariosReservados("http://localhost/siiaServices/apis/getFechasReservadas.php"),
    InsertData("http://localhost/siiaServices/apis/Insert.php"),
    MiCita("http://localhost/siiaServices/apis/misCitas.php"),
    Commun("http://localhost/siiaServices/apis/commun.php"),
    CancelarMiCita("http://localhost/siiaServices/apis/deleteCita.php"),
    ReportesGeneric("http://localhost/siiaServices/apis/reportesServices.php?typeService="),
    Eventos("http://localhost/siiaServices/apis/Eventos.php");

    private String valor;
    URLs(String valor) {
        this.valor = valor;
    }

    public URLs[] getValues() {
        return URLs.values();
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
