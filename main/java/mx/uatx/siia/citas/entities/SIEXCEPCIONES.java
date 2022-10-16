package mx.uatx.siia.citas.entities;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class SIEXCEPCIONES {
    @Id
    @SequenceGenerator(name = "IDEXCEPCION", sequenceName = "IDEXCEPCION", allocationSize = 1)
    @Column(name="IDEXCEPCION")
    private Integer intId;
    @Column(name="FECHAEXCEPCION")
    private String strFechaEXC;
    @Column(name="HORAEXEPCION")
    private String strHoraEXC;
    @Column(name="USERAUDIT")
    private String strUserAUDIT;


    public Integer getIntId() {
        return intId;
    }

    public void setIntId(Integer intId) {
        this.intId = intId;
    }

    public String getStrFechaEXC() {
        return strFechaEXC;
    }

    public void setStrFechaEXC(String strFechaEXC) {
        this.strFechaEXC = strFechaEXC;
    }

    public String getStrHoraEXC() {
        return strHoraEXC;
    }

    public void setStrHoraEXC(String strHoraEXC) {
        this.strHoraEXC = strHoraEXC;
    }

    public String getStrUserAUDIT() {
        return strUserAUDIT;
    }

    public void setStrUserAUDIT(String strUserAUDIT) {
        this.strUserAUDIT = strUserAUDIT;
    }
}
