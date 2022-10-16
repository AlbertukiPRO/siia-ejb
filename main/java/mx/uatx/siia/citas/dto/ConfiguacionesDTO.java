package mx.uatx.siia.citas.dto;


public class ConfiguacionesDTO {

    private Integer idConfiguraciones;
    private String horaServicioInicio;
    private String horaServicioFin;
    private String duracionCitas;
    private Integer idAreas;

   public ConfiguacionesDTO(){

   }

    public Integer getIdConfiguraciones() {
        return idConfiguraciones;
    }

    public void setIdConfiguraciones(Integer idConfiguraciones) {
        this.idConfiguraciones = idConfiguraciones;
    }

    public String getHoraServicioInicio() {
        return horaServicioInicio;
    }

    public void setHoraServicioInicio(String horaServicioInicio) {
        this.horaServicioInicio = horaServicioInicio;
    }

    public String getHoraServicioFin() {
        return horaServicioFin;
    }

    public void setHoraServicioFin(String horaServicioFin) {
        this.horaServicioFin = horaServicioFin;
    }

    public String getDuracionCitas() {
        return duracionCitas;
    }

    public void setDuracionCitas(String duracionCitas) {
        this.duracionCitas = duracionCitas;
    }

    public Integer getIdAreas() {
        return idAreas;
    }

    public void setIdAreas(Integer idAreas) {
        this.idAreas = idAreas;
    }

    @Override
    public String toString() {
        return "ConfiguacionesDTO{" +
                "idConfiguraciones=" + idConfiguraciones +
                ", horaServicioInicio='" + horaServicioInicio + '\'' +
                ", horaServicioFin='" + horaServicioFin + '\'' +
                ", duracionCitas='" + duracionCitas + '\'' +
                ", idAreas=" + idAreas +
                '}';
    }
}
