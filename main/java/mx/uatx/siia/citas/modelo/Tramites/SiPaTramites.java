package mx.uatx.siia.citas.modelo.Tramites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SiPaTramites {
    @Id
    @Column(name = "idtramites", nullable = false)
    private Integer idTramites;

    @Column(name = "idarea")
    private Integer intIdArea;

    @Column(name = "nombretramite")
    private String strNombreTramite;

    @Column(name = "descripcióntramite")
    private String strDescripciónTramite;

    @Column(name = "requisitos")
    private String strRequisitos;



    /* getters and setter */

    public Integer getIdTramites() {
        return idTramites;
    }

    public void setIdTramites(Integer idTramites) {
        this.idTramites = idTramites;
    }

    public Integer getIntIdArea() {
        return intIdArea;
    }

    public void setIntIdArea(Integer intIdArea) {
        this.intIdArea = intIdArea;
    }

    public String getStrNombreTramite() {
        return strNombreTramite;
    }

    public void setStrNombreTramite(String strNombreTramite) {
        this.strNombreTramite = strNombreTramite;
    }

    public String getStrDescripciónTramite() {
        return strDescripciónTramite;
    }

    public void setStrDescripciónTramite(String strDescripciónTramite) {
        this.strDescripciónTramite = strDescripciónTramite;
    }

    public String getStrRequisitos() {
        return strRequisitos;
    }

    public void setStrRequisitos(String strRequisitos) {
        this.strRequisitos = strRequisitos;
    }
}
