package mx.uatx.siia.citas.modelo.areas.business;

public class SiPaAreasConfiguraciones {

    private String horaServicioInicio;
    private String horaServicioFin;
    private String strNameJefe;
    private String duracionCitas;

    public SiPaAreasConfiguraciones(String horaServicioInicio, String horaServicioFin, String strNameJefe, String duracionCitas) {
        this.horaServicioInicio = horaServicioInicio;
        this.horaServicioFin = horaServicioFin;
        this.strNameJefe = strNameJefe;
        this.duracionCitas = duracionCitas;
    }

    public SiPaAreasConfiguraciones(String horaServicioInicio, String horaServicioFin, String duracionCitas) {
        this.horaServicioInicio = horaServicioInicio;
        this.horaServicioFin = horaServicioFin;
        this.duracionCitas = duracionCitas;
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

    public String getStrNameJefe() {
        return strNameJefe;
    }

    public void setStrNameJefe(String strNameJefe) {
        this.strNameJefe = strNameJefe;
    }

    public String getDuracionCitas() {
        return duracionCitas;
    }

    public void setDuracionCitas(String duracionCitas) {
        this.duracionCitas = duracionCitas;
    }
}
