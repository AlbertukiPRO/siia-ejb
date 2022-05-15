package mx.uatx.siia.citas.modelo.enums;

import java.io.Serializable;

public enum EstadosCitas implements Serializable {

    AGENDADA(1),
    CANCELADA(2),
    CADUCADA(3);

    private int intActivo;

    EstadosCitas(int activo){
        this.intActivo=activo;
    }

    public int getIntActivo() {
        return intActivo;
    }

    public void setIntActivo(int intActivo) {
        this.intActivo = intActivo;
    }
}
