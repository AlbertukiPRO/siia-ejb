package mx.uatx.siia.citas.enums;

public enum EstatusCitas {

    CitaAgendada("Agendada"),
    CitaCancelada("Cancelada"),
    CitaPospuesta("Pospuesta"),
    CitaCaducada("Expirada"),
    CitaCompleta("Completada");

    private String valor;

    EstatusCitas(String valor){
        this.valor = valor;
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
