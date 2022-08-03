package mx.uatx.siia.citas.modelo.enums;

public enum Requisitos {

    BajaDefinitiva(new String[]{"https://uatx.mx/secretaria/tecnica/cyre/tramites#collapseFour","2"}),
    BajaTemporal(new String[]{"https://uatx.mx/secretaria/tecnica/cyre/tramites#collapseFour","1"}),
    CartaPasante(new String[]{"https://uatx.mx/secretaria/tecnica/cyre/tramites#collapseThree","3"}),
    CertificadoPasante(new String[]{"https://uatx.mx/secretaria/tecnica/cyre/tramites#collapseThree","4"}),
    ConstanciaEstudiosDigital(new String[]{"https://uatx.mx/secretaria/tecnica/cyre/tramites#collapseTwo","5"}),
    ConstanciaExpedienteCompleto(new String[]{"https://uatx.mx/secretaria/tecnica/cyre/tramites#collapseOne","6"}),
    Cartadenodeudas(new String[]{"https://uatx.mx/secretaria/tecnica/cyre/tramites#collapseOne","7"});

    private String[] url;

    Requisitos(String[] url){
        this.url = url;
    }

    public String[] getUrl() {
        return url;
    }

    public void setUrl(String[] url) {
        this.url = url;
    }
}
