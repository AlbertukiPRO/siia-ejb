package mx.uatx.siia.citas.modelo.enums;

/**
 * @author Alberto Noche Rosas
 * @apiNote Enum para almacenar las API's temporales del servicio backend en php y mysql
 */
public enum URLs {
    Areas("http://localhost/siiaServices/apis/getAreas.php"),
    Tramites("http"),
    FechasReservadas("http://localhost/siiaServices/apis/getFechasReservadas.php");

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