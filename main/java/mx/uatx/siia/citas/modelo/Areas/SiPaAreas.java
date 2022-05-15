package mx.uatx.siia.citas.modelo.Areas;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SiPaAreas {
    @Id
    @Column(name = "idarea", nullable = false)
    private Integer idarea;

    @Column(name = "nombrearea")
    private Integer strNombreArea;

    @Column(name = "idcalendario")
    private Integer intIdCalendario;

    @Column(name = "idtramites")
    private Integer intIdTramite;

    @Column(name = "idhorarios")
    private Integer intIdHorarios;

    @Column(name = "idpersonalarea")
    private Integer intPersonalArea;


    /*GETTES AND SETTER*/

    public Integer getIdarea() {
        return idarea;
    }

    public void setIdarea(Integer idarea) {
        this.idarea = idarea;
    }

    public Integer getStrNombreArea() {
        return strNombreArea;
    }

    public void setStrNombreArea(Integer strNombreArea) {
        this.strNombreArea = strNombreArea;
    }

    public Integer getIdCalendario() {
        return intIdCalendario;
    }

    public void setIdCalendario(Integer idCalendario) {
        this.intIdCalendario = idCalendario;
    }

    public Integer getIdTramite() {
        return intIdTramite;
    }

    public void setIdTramite(Integer idTramite) {
        this.intIdTramite = idTramite;
    }

    public Integer getIdHorarios() {
        return intIdHorarios;
    }

    public void setIdHorarios(Integer idHorarios) {
        this.intIdHorarios = idHorarios;
    }

    public Integer getPersonalArea() {
        return intPersonalArea;
    }

    public void setPersonalArea(Integer personalArea) {
        this.intPersonalArea = personalArea;
    }
}
