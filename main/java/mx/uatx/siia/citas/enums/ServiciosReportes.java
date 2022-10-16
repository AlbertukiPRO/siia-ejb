package mx.uatx.siia.citas.enums;

public enum ServiciosReportes {
    GetAllCitasOnTramite(1),
    GetAllCitasOnArea(2),
    GetAllCitasOnFecha(3);
    private int valor;

    ServiciosReportes(int valor){
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
