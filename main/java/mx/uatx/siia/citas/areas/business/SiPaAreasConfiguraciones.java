package mx.uatx.siia.citas.areas.business;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SiPaAreasConfiguraciones {

    @Id
    @Column(name = "IDCONFIGURACIONES")
    private Integer idConfiguraciones;

    @Column(name = "HORAINICIO")
    private String horaServicioInicio;

    @Column(name = "HORAFIN")
    private String horaServicioFin;

    @Column(name = "NOMBREJEFE")
    private String duracionCitas;

    @Column(name = "IDAREA")
    private Integer idAreas;


    public long getIdConfiguraciones() {
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
}
