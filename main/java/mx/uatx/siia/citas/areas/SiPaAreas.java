package mx.uatx.siia.citas.areas;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class SiPaAreas {
    @Id
    @Column(name = "CLAREA")
    private Integer idarea;

    @Column(name = "NBAREA")
    private String strNombreArea;

    /*GETTES AND SETTER*/

    public Integer getIdarea() {
        return idarea;
    }

    public void setIdarea(Integer idarea) {
        this.idarea = idarea;
    }

    public String getStrNombreArea() {
        return strNombreArea;
    }

    public void setStrNombreArea(String strNombreArea) {
        this.strNombreArea = strNombreArea;
    }
}
