package mx.uatx.siia.citas.Tramites;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SiPaTramites {
    @Id
    @Column(name = "IDTRAMITE")
    private Integer idTramite;

    @Column(name = "IDAREACAMPUS")
    private Integer intIdArea;

    @Column(name = "NOMBRETRAMITE")
    private String strNombreTramite;

    @Column(name = "DESCRIPCIONTRAMITE")
    private String strDescripcionTramite;

    @Column(name = "REQUISITOSTRAMITE")
    private String strRequisitos;



    /* getters and setter */

    public Integer getIdTramites() {
        return idTramite;
    }

    public void setIdTramites(Integer idTramites) {
        this.idTramite = idTramites;
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

    public String getStrDescripcionTramite() {
        return strDescripcionTramite;
    }

    public void setStrDescripcionTramite(String strDescripcionTramite) {
        this.strDescripcionTramite = strDescripcionTramite;
    }

    public String getStrRequisitos() {
        return strRequisitos;
    }

    public void setStrRequisitos(String strRequisitos) {
        this.strRequisitos = strRequisitos;
    }
}
